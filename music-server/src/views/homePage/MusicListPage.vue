<template>
  <div class="container">
    <div class="function">
      <div class="function-left">
        <el-button type="primary" @click="handleAdd">添加歌单</el-button>
      </div>
      <div class="function-right">
        <el-input placeholder="搜索歌单" v-model="searchWord"></el-input>
      </div>
    </div>
    <div class="table">
      <el-table :data="displayData">
        <!--        <el-table-column type="selection" width="50" align="center"></el-table-column>-->
        <el-table-column label="序号" type="index" width="100" align="center"/>
        <el-table-column label="歌单图片" prop="pic" width="150" align="center">
          <template v-slot="scope">
            <div class="music-list-img">
              <img :src="handleStaticResources(scope.row.pic)" style="width: 100%"/>
            </div>
            <el-upload :action="uploadUrl(scope.row.id)" :show-file-list="false" :on-success="handleImgSuccess"
                       :before-upload="checkUploadImg">
              <el-button>更新图片</el-button>
            </el-upload>
          </template>
        </el-table-column>
        <el-table-column label="标题" prop="title" width="150" align="center"></el-table-column>
        <el-table-column label="标签" prop="tag" width="150" align="center"></el-table-column>
        <el-table-column label="简介" prop="introduction">
          <template v-slot="scope">
            <p style="height: 100px;">
              {{ scope.row.introduction }}
            </p>
          </template>
        </el-table-column>
        <el-table-column label="内容" width="150" align="center">
          <template v-slot="scope">
            <el-button @click="goDetailPage(scope.row.id)">管理歌单内容</el-button>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="180" align="center">
          <template v-slot="scope">
            <el-button size="large" @click="handleEdit(scope.row)">编辑</el-button>
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
        :title="action === 'add'? '新增歌单':'编辑歌单'"
        width="35%"
        :before-close="handleClose">
      <el-form :inline="true" :model="formMusicList" ref="musicListForm">
        <el-form-item label="标题" prop="title"
                      :rules="[{required: true, message: '标题必填'}]">
          <el-input v-model="formMusicList.title" placeholder="请输入标题"/>
        </el-form-item>
        <el-form-item label="歌单标签" prop="tag">
          <el-input v-model="formMusicList.tag"></el-input>
        </el-form-item>
        <el-form-item label="歌单简介" prop="introduction">
          <el-input type="textarea" v-model="formMusicList.introduction"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="handleCancel">取消</el-button>
        <el-button type="primary" @click="handleSubmit">
          确定
        </el-button>
      </span>
      </template>
    </el-dialog>
  </div>

</template>

<script>
import {computed, defineComponent, getCurrentInstance, reactive, ref, watch} from "vue";
import utils from "../../utils/utils";
import {getBaseURL} from "../../api/request";
import {RouterName} from "../../constant/constant";

export default defineComponent({
  setup() {
    const {proxy} = getCurrentInstance();
    const {handleGender, handleDate, handleStaticResources, checkUploadImg, handleRouter} = utils()

    const tableData = ref([]); // 记录歌单，用于显示
    const tempDate = ref([]); // 记录歌单，用于搜索时能临时记录一份歌单列表
    const pageSize = ref(5); // 页数
    const currentPage = ref(1); // 当前页
    const dialogVisible = ref(false); //Dialog
    const action = ref('add');     //区分编辑和新增
    const formMusicList = reactive({
      id: "",
      title: "",
      pic: "",
      introduction: "",
      tag: "",
    })

    // 计算当前表格中的数据
    const displayData = computed(() => {
      return tableData.value.slice((currentPage.value - 1) * pageSize.value, currentPage.value * pageSize.value);
    });

    const searchWord = ref(""); // 记录输入框输入的内容
    watch(searchWord, () => {
      if (searchWord.value === "") {
        tableData.value = tempDate.value;
      } else {
        tableData.value = [];
        for (let item of tempDate.value) {
          if (item.title.includes(searchWord.value)) {
            tableData.value.push(item);
          }
        }
      }
    });

    getMusicListData();

    async function getMusicListData() {
      tableData.value = [];
      tempDate.value = [];
      const result = await proxy.$api.getAllMusicList();
      tableData.value = result.data;
      tempDate.value = result.data;
      currentPage.value = 1;
    }

    function handleCurrentChange(val) {
      currentPage.value = val;
    }

    const uploadUrl = (id) => {
      let url = `static/image/musicList/update?id=${id}`
      return `${getBaseURL()}${url}`
    }

    function handleImgSuccess() {
      getMusicListData();
    }

    const goDetailPage = (id) =>{
      handleRouter(RouterName.MusicListDetail, {path: RouterName.MusicListDetail, query: {id}});
    }

    const handleClose = (done) => {
      ElMessageBox.confirm('确定关闭此对话框?')
          .then(() => {
            proxy.$refs.musicListForm.resetFields();
            done()
          })
          .catch(() => {
            // catch error
          })
    }
    //dialog的取消
    const handleCancel = () => {
      proxy.$refs.musicListForm.resetFields();
      dialogVisible.value = false
    }
    //新增
    const handleAdd = () => {
      dialogVisible.value = true
      action.value = 'add';
    }
    // 编辑
    const handleEdit = (row) => {
      dialogVisible.value = true;
      action.value = 'edit';
      proxy.$nextTick(() => {
        Object.assign(formMusicList, row)
      })
    }
    // 提交
    const handleSubmit = () => {
      proxy.$refs.musicListForm.validate(async (valid) => {
        if (valid) {

          if (action.value === "add") {
            let res = await proxy.$api.insertMusicList(formMusicList)
            if (res) {
              proxy.$refs.musicListForm.resetFields();
              dialogVisible.value = false
              await getMusicListData()
            }
          } else if (action.value === "edit") {
            let res = await proxy.$api.updateMusicListInfo(formMusicList)
            if (res) {
              proxy.$refs.musicListForm.resetFields();
              dialogVisible.value = false
              await getMusicListData()
            }
          }
        } else {
          ElMessage({
            showClose: true,
            message: "请输入正确的内容",
            type: "error",
          });
        }
      })
    }
    // 删除
    const handleDelete = (id) => {
      ElMessageBox.confirm('确定删除吗?')
          .then(async () => {
            await proxy.$api.deleteMusicList({
              id: id
            })
            ElMessage({
              showClose: true,
              message: "删除成功",
              type: 'success'
            })
            await getMusicListData()
          })
          .catch(() => {
            // catch error
          })
    }


    return {
      tableData,
      searchWord,
      handleStaticResources,
      handleImgSuccess,
      checkUploadImg,
      handleGender,
      handleDate,
      currentPage,
      pageSize,
      handleCurrentChange,
      displayData,
      dialogVisible,
      handleClose,
      handleCancel,
      action,
      handleAdd,
      handleEdit,
      formMusicList,
      handleSubmit,
      handleDelete,
      uploadUrl,
      goDetailPage

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

.music-list-img {
  width: 100%;
  height: 100px;
  border-radius: 5px;
  margin-bottom: 5px;
  overflow: hidden;
}
</style>
