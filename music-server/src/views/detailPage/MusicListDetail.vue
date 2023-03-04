<template>
  <div class="container">
    <div class="function">
      <div class="function-left">
        <el-button type="primary" @click="handleAdd">添加歌曲</el-button>
      </div>
      <div class="function-right">
        <el-input placeholder="搜索歌曲或歌手名" v-model="searchWord"></el-input>
      </div>
    </div>
    <div class="table">
      <el-table :data="displayData">
        <el-table-column label="序号" type="index" width="100" align="center"/>
        <el-table-column label="歌名" prop="musicName" width="150" align="center"></el-table-column>
        <el-table-column label="歌手名" prop="artistName" width="150" align="center"></el-table-column>
        <el-table-column label="专辑" prop="musicAlbum" width="150" align="center"></el-table-column>
        <el-table-column label="歌词" align="center">
          <template v-slot="scope">
            <ul style="height: 100px; overflow: scroll">
              <li v-for="(item, index) in handleLyric(scope.row.musicLyrics)" :key="index">
                {{ item }}
              </li>
            </ul>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="180" align="center">
          <template v-slot="scope">
            <el-button size="large" type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
          class="pagination"
          background
          layout="total, prev, pager, next"
          :current-page="currentPage"
          :page-size="pageSize"
          :total="tableData.length"
          @current-change="handleCurrentChange"
      >
      </el-pagination>
    </div>

    <el-dialog
        v-model="dialogVisible"
        title="新增歌曲"
        width="800px"
        :before-close="handleClose">
      <el-input style="width: 200px; margin-bottom: 1rem" placeholder="搜索歌曲或歌手名" v-model="dialogSearchWord"></el-input>
      <div class="add-table">
        <el-table :data="dialogDisplayData">
          <el-table-column label="序号" type="index" width="100" align="center"/>
          <el-table-column label="歌名" prop="name" width="150" align="center"></el-table-column>
          <el-table-column label="歌手名" prop="artistName" width="150" align="center"></el-table-column>
          <el-table-column label="专辑" prop="album" width="150" align="center"></el-table-column>
          <el-table-column label="操作" width="180" align="center">
            <template v-slot="scope">
              <el-button size="large" type="primary"  @click="handleAddMusic(scope.row.id)">添加</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination
            class="pagination"
            background
            layout="total, prev, pager, next"
            :current-page="dialogCurrentPage"
            :page-size="dialogPageSize"
            :total="dialogTableData.length"
            @current-change="handleDialogCurrentChange"
        >
        </el-pagination>

      </div>
    </el-dialog>
  </div>

</template>

<script>
import {computed, defineComponent, getCurrentInstance, reactive, ref, watch} from "vue";
import {useStore} from "vuex";
import utils from "../../utils/utils";

export default defineComponent({
  setup() {
    const {proxy} = getCurrentInstance();
    const {handleLyric} = utils()
    const store = useStore();
    const listId = proxy.$route.query.id
    const tableData = ref([]); // 记录歌曲，用于显示
    const tempData = ref([]); // 记录歌曲，用于搜索时能临时记录一份歌曲列表
    const pageSize = ref(5); // 页数
    const currentPage = ref(1); // 当前页
    const dialogVisible = ref(false); //Dialog
    const dialogTableData = ref([])
    const dialogTempData = ref([])
    const dialogSearchWord = ref("")
    const dialogCurrentPage = ref(1);
    const dialogPageSize = ref(5); // 页数

    // 计算当前表格中的数据
    const displayData = computed(() => {
      return tableData.value.slice((currentPage.value - 1) * pageSize.value, currentPage.value * pageSize.value);
    });

    const searchWord = ref(""); // 记录输入框输入的内容
    watch(searchWord, () => {
      if (searchWord.value === "") {
        tableData.value = tempData.value;
      } else {
        tableData.value = [];
        for (let item of tempData.value) {
          if (item.musicName.includes(searchWord.value) || item.artistName.includes(searchWord.value)) {
            tableData.value.push(item);
          }
        }
      }
    });

    getData();

    async function getData() {
      tableData.value = [];
      tempData.value = [];
      const result = await proxy.$api.selectMusicByListId(
          {
            id: listId
          })
      tableData.value = result.data;
      tempData.value = result.data;
      currentPage.value = 1;
    }
    function handleCurrentChange(val) {
      currentPage.value = val;
    }
    const handleClose = (done) => {
      ElMessageBox.confirm('确定关闭此对话框?')
          .then(() => {
            dialogSearchWord.value = ""
            getData()
            done()
          })
          .catch(() => {
            // catch error
          })
    }
    //新增
    const handleAdd = () => {
      dialogVisible.value = true
      getDialogTableData()
    }
    // 删除
    const handleDelete = (id) => {
      ElMessageBox.confirm('确定删除吗?')
          .then(async () => {
            await proxy.$api.deleteMusicFromList({
              id: id
            })
            ElMessage({
              showClose: true,
              message: "删除成功",
              type: 'success'
            })
            getData()
          })
          .catch(() => {
            // catch error
          })
    }
    // 添加相关数据
    async function getDialogTableData() {
      dialogTableData.value = [];
      dialogTempData.value = [];
      const result = await proxy.$api.getMusicNotInList(
          {
            id: listId
          })
      dialogTableData.value = result.data;
      dialogTempData.value = result.data;
      dialogCurrentPage.value = 1;
    }

    const dialogDisplayData = computed(() => {
      return dialogTableData.value.slice((dialogCurrentPage.value - 1) * pageSize.value, dialogCurrentPage.value * pageSize.value);
    });
    function handleDialogCurrentChange(val) {
      dialogCurrentPage.value = val;
    }
    watch(dialogSearchWord, () => {
      if (dialogSearchWord.value === "") {
        dialogTableData.value = dialogTempData.value;
      } else {
        dialogTableData.value = [];
        for (let item of dialogTempData.value) {
          if (item.name.includes(dialogSearchWord.value) || item.album.includes(dialogSearchWord.value) ||
              item.artistName.includes(dialogSearchWord.value)) {
            dialogTableData.value.push(item);
          }
        }
      }
    });
    const handleAddMusic = async (id) =>{
      let res = await proxy.$api.insertMusicToList({
        musicListId: listId,
        musicId: id
      })
      if (res) {
        ElMessage({
          message:res.message,
          type:res.type,
        })
        await getDialogTableData()
      }
    }

    return {
      tableData,
      searchWord,
      currentPage,
      pageSize,
      handleCurrentChange,
      displayData,
      dialogVisible,
      handleClose,
      handleAdd,
      handleDelete,
      handleLyric,
      dialogTableData,
      dialogCurrentPage,
      dialogPageSize,
      dialogSearchWord,
      handleDialogCurrentChange,
      dialogDisplayData,
      handleAddMusic
    }
  }
})
</script>

<style lang="less" scoped>
.function {
  width: 100%;
  display: flex;
  flex-wrap: nowrap;
  flex-direction: row;
  justify-content: space-between;

  .function-left {
    display: inline-block;
  }

  .function-right {
    display: inline-block;
    margin-right: 0;
  }
}

.table {
  margin-top: 2rem;
}

.music-img {
  width: 100%;
  height: 100px;
  border-radius: 5px;
  margin-bottom: 5px;
}

</style>
