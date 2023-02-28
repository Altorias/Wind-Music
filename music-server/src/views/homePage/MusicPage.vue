<template>
  <div class="container">
    <div class="function">
      <div class="function-left">
        <el-button type="primary" @click="handleAdd">添加歌曲</el-button>
      </div>
      <div class="function-right">
        <el-input placeholder="搜索歌曲名" v-model="searchWord"></el-input>
      </div>
    </div>
    <div class="table">
      <el-table :data="displayData">
        <!--        <el-table-column type="selection" width="50" align="center"></el-table-column>-->
        <el-table-column label="序号" type="index" width="100" align="center"/>
        <el-table-column label="播放歌曲" width="150" align="center">
          <template v-slot="scope">
            <el-button class="play-music" @click="handlePlayMusic(scope.row)">
              <el-icon size="large" v-if="toggle === scope.row.name">
                <VideoPause/>
              </el-icon>
              <el-icon size="large" v-else>
                <VideoPlay/>
              </el-icon>
            </el-button>
          </template>
        </el-table-column>
        <el-table-column label="歌曲图片" prop="pic" width="150" align="center">
          <template v-slot="scope">
            <div class="music-img">
              <img :src="handleStaticResources(scope.row.pic)" style="width: 100%"/>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="歌名" prop="name" width="150" align="center"></el-table-column>
        <el-table-column label="歌手名" prop="artistName" width="150" align="center"></el-table-column>
        <el-table-column label="专辑" prop="album" width="150" align="center"></el-table-column>
        <!--todo 歌词 日期-->
        <el-table-column label="歌词" align="center">
          <template v-slot="scope">
            <ul style="height: 100px; overflow: scroll">
              <li v-for="(item, index) in handleLyric(scope.row.lyrics)" :key="index">
                {{ item }}
              </li>
            </ul>
          </template>
        </el-table-column>
        <el-table-column label="添加时间" prop="birth" width="150" align="center">
          <template v-slot="scope">
            <div>{{ handleDate(scope.row.createTime) }}</div>
          </template>
        </el-table-column>
        <el-table-column label="修改时间" prop="birth" width="150" align="center">
          <template v-slot="scope">
            <div>{{ handleDate(scope.row.updateTime) }}</div>
          </template>
        </el-table-column>
        <!--        fin-->
        <el-table-column class="resource" label="更新资源" width="180" align="center">
          <template v-slot="scope">
            <el-upload :action="uploadMusicPic(scope.row.id)" :show-file-list="false" :on-success="handleImgSuccess"
                       :before-upload="checkUploadImg">
              <el-button>更新图片</el-button>
            </el-upload>
            <br>
            <el-upload :action="uploadMusicUrl(scope.row.id)" :show-file-list="false" :on-success="handleImgSuccess"
                       :before-upload="checkUploadMusic">
              <el-button>更新歌曲</el-button>
            </el-upload>
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
        :title="action === 'add'? '新增歌曲':'编辑歌曲'"
        width="35%"
        :before-close="handleClose">
      <el-form :model="formMusic" ref="musicForm">
        <el-form-item label="歌名" prop="name"
                      :rules="[{required: true, message: '歌名必填'}]">
          <el-input v-model="formMusic.name" placeholder="请输入歌名"/>
        </el-form-item>
        <el-form-item label="专辑名" prop="album">
          <el-input v-model="formMusic.album" placeholder="请输入专辑名"/>
        </el-form-item>
        <!--        todo 选择歌手-->
        <el-form-item label="歌手" prop="artistId">
          <el-select style="width: 10rem;" v-model="formMusic.artistId" placeholder="请选择">
            <el-option  v-for="(item,index) in artistData"
                        :key="index"
                        :label="item.name"
                        :value="item.id">
              <span>{{ item.name }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="歌词" prop="lyrics">
          <el-input type="textarea" name="lyrics" v-model="formMusic.lyrics"></el-input>
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
import {useStore} from "vuex";

export default defineComponent({
  setup() {
    const {proxy} = getCurrentInstance();
    const store = useStore();
    const {handleGender, handleDate, handleStaticResources, checkUploadImg, handleLyric, checkUploadMusic} = utils()
    const tableData = ref([]); // 记录歌曲，用于显示
    const tempDate = ref([]); // 记录歌曲，用于搜索时能临时记录一份歌曲列表
    const pageSize = ref(5); // 页数
    const currentPage = ref(1); // 当前页
    const dialogVisible = ref(false); //Dialog
    const action = ref('add');     //区分编辑和新增
    const toggle = ref(false)     // 播放状态
    const artistData = ref([])
    const musicPlay = computed(() => store.getters.musicPlay);
    const formMusic = reactive({
      id: "",
      name: "",
      album: "",
      lyrics: "",
      artistId: ""
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
        console.log("watch")
        for (let item of tempDate.value) {
          if (item.name.includes(searchWord.value) || item.artistName.includes(searchWord.value)) {
            tableData.value.push(item);
          }
        }
      }
    });

    proxy.$store.commit("setMusicPlay")
    getMusicData();
    getArtistData()

    async function getMusicData() {
      tableData.value = [];
      tempDate.value = [];
      const result = await proxy.$api.getAllMusic()
      tableData.value = result.data;
      tempDate.value = result.data;
      currentPage.value = 1;
    }

    async function getArtistData() {
      let res = await proxy.$api.getAllArtist()
      artistData.value = res.data
    }

    function handleCurrentChange(val) {
      currentPage.value = val;
    }

    const uploadMusicPic = (id) => {
      let url = `static/image/music/update?id=${id}`
      return `${getBaseURL()}${url}`
    }

    const uploadMusicUrl = (id) => {
      let url = `static/music/update?id=${id}`
      return `${getBaseURL()}${url}`
    }

    function handleImgSuccess() {
      getMusicData();
    }

    const handleClose = (done) => {
      ElMessageBox.confirm('确定关闭此对话框?')
          .then(() => {
            proxy.$refs.musicForm.resetFields();
            done()
          })
          .catch(() => {
            // catch error
          })
    }
    //dialog的取消
    const handleCancel = () => {
      proxy.$refs.musicForm.resetFields();
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
        formMusic.artistId = row.artistId
        formMusic.name = row.name
        formMusic.id = row.id
        formMusic.lyrics = row.lyrics
        formMusic.album = row.album
        // Object.assign(formMusic, row)
      })
    }
    // 提交
    const handleSubmit = () => {
      proxy.$refs.musicForm.validate(async (valid) => {
        if (valid) {
          if (action.value === "add") {
            let res = await proxy.$api.insertMusic(formMusic)
            if (res) {
              proxy.$refs.musicForm.resetFields();
              dialogVisible.value = false
              await getMusicData()
            }
          } else if (action.value === "edit") {
            let res = await proxy.$api.updateMusicInfo(formMusic)
            if (res) {
              proxy.$refs.musicForm.resetFields();
              dialogVisible.value = false
              await getMusicData()
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
            await proxy.$api.deleteMusic({
              id: id
            })
            ElMessage({
              showClose: true,
              message: "删除成功",
              type: 'success'
            })
            getMusicData()
          })
          .catch(() => {
            // catch error
          })
    }
    // todo
    const handlePlayMusic = (row) => {
      console.log('111')
      if (musicPlay.value) {
        toggle.value = false
        proxy.$store.commit("setMusicUrl", null);
      } else {
        toggle.value = row.name
      }
      proxy.$store.commit("setMusicUrl", row.url);
      proxy.$store.commit("setMusicPlay")
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
      formMusic,
      handleSubmit,
      handleDelete,
      uploadMusicPic,
      uploadMusicUrl,
      handlePlayMusic,
      handleLyric,
      toggle,
      artistData,
      checkUploadMusic
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
