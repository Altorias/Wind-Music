<template>
  <div class="container">
    <div class="function">
      <div class="function-left">
        <el-button type="primary" @click="handleAdd">添加歌手</el-button>
      </div>
      <div class="function-right">
        <el-input placeholder="搜索歌手名" v-model="searchWord"></el-input>
      </div>
    </div>
    <div class="table">
      <el-table :data="displayData">
<!--        <el-table-column type="selection" width="50" align="center"></el-table-column>-->
        <el-table-column label="序号" type="index" width="100" align="center"/>
        <el-table-column label="歌手图片" prop="pic" width="150" align="center">
          <template v-slot="scope">
            <div class="singer-img">
              <img :src="handleStaticResources(scope.row.pic)" style="width: 100%"/>
            </div>
            <el-upload :action="uploadUrl(scope.row.id)" :show-file-list="false" :on-success="handleImgSuccess"
                       :before-upload="checkUploadImg">
              <el-button>更新图片</el-button>
            </el-upload>
          </template>
        </el-table-column>
        <el-table-column label="歌手" prop="name" width="150" align="center"></el-table-column>
        <el-table-column label="性别" prop="gender" width="60" align="center">
          <template v-slot="scope">
            <div>{{ handleGender(scope.row.gender) }}</div>
          </template>
        </el-table-column>
        <el-table-column label="出生日期" prop="birth" width="150" align="center">
          <template v-slot="scope">
            <div>{{ handleDate(scope.row.birthDate) }}</div>
          </template>
        </el-table-column>
        <el-table-column label="地区" prop="location" width="150" align="center"></el-table-column>
        <el-table-column label="简介" prop="description">
          <template v-slot="scope">
            <p style="height: 100px;">
              {{ scope.row.description }}
            </p>
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
        :title="action === 'add'? '新增用户':'编辑用户'"
        width="35%"
        :before-close="handleClose">
      <el-form :inline="true" :model="formArtist" ref="artistForm">
        <el-row>
          <el-col :span="12">
            <el-form-item label="姓名" prop="name"
                          :rules="[{required: true, message: '姓名必填'}]">
              <el-input v-model="formArtist.name" placeholder="请输入姓名"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="性别" prop="gender" :rules="[{required: true, message: '性别必填'}]">
              <el-select v-model="formArtist.gender" placeholder="请选择性别">
                <el-option label="女性" value="0"/>
                <el-option label="男性" value="1"/>
                <el-option label="乐队" value="2"/>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="出生日期" prop="birthDate">
              <el-date-picker
                  v-model="formArtist.birthDate"
                  type="date"
                  placeholder="请选择出生日期"
                  style="width: 100%"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="地区" prop="location">
              <el-input v-model="formArtist.location" placeholder="请输入地址"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col>
            <el-form-item label="歌手介绍" prop="description">
              <el-input type="textarea" v-model="formArtist.description"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
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

export default defineComponent({
  setup() {
    const {proxy} = getCurrentInstance();
    const {handleGender, handleDate, handleStaticResources, checkUploadImg} = utils()

    const tableData = ref([]); // 记录歌曲，用于显示
    const tempDate = ref([]); // 记录歌曲，用于搜索时能临时记录一份歌曲列表
    const pageSize = ref(5); // 页数
    const currentPage = ref(1); // 当前页
    const dialogVisible = ref(false); //Dialog
    const action = ref('add');     //区分编辑和新增
    const formArtist = reactive({
      id: "",
      name: "",
      gender: "",
      pic: "",
      birthDate: new Date(),
      location: "",
      description: "",
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
          if (item.name.includes(searchWord.value)) {
            tableData.value.push(item);
          }
        }
      }
    });

    getArtistData();

    async function getArtistData() {
      tableData.value = [];
      tempDate.value = [];
      const result = await proxy.$api.getAllArtist()
      tableData.value = result.data;
      tempDate.value = result.data;
      currentPage.value = 1;
    }

    function handleCurrentChange(val) {
      currentPage.value = val;
    }

    const uploadUrl = (id) => {
      let url = `static/image/singer/update?id=${id}`
      return `${getBaseURL()}${url}`
    }

    function handleImgSuccess() {
      getArtistData();
    }

    const handleClose = (done) => {
      ElMessageBox.confirm('确定关闭此对话框?')
          .then(() => {
            proxy.$refs.artistForm.resetFields();
            done()
          })
          .catch(() => {
            // catch error
          })
    }
    //dialog的取消
    const handleCancel = () => {
      proxy.$refs.artistForm.resetFields();
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
      if (row.gender === 1) {
        row.gender = "男"
      } else if (row.gender === 0) {
        row.gender = "女"
      } else {
        row.gender = "乐队"
      }

      proxy.$nextTick(() => {
        Object.assign(formArtist, row)
      })
    }
    // 提交
    const handleSubmit = () => {
      proxy.$refs.artistForm.validate(async (valid) => {
        if (valid) {
          formArtist.birthDate = handleDate(formArtist.birthDate)
          console.log(formArtist.birthDate)
          if (formArtist.gender === "男") {
            formArtist.gender = 1
          } else if (formArtist.gender === "女") {
            formArtist.gender = 0
          } else if (formArtist.gender === "乐队") {
            formArtist.gender = 2
          }

          if (action.value === "add") {
            let res = await proxy.$api.insertArtist(formArtist)
            if (res) {
              proxy.$refs.artistForm.resetFields();
              dialogVisible.value = false
              await getArtistData()
            }
          } else if (action.value === "edit") {
            let res = await proxy.$api.updateArtist(formArtist)
            if (res) {
              proxy.$refs.artistForm.resetFields();
              dialogVisible.value = false
              await getArtistData()
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
            await proxy.$api.deleteArtist({
              id: id
            })
            ElMessage({
              showClose: true,
              message: "删除成功",
              type: 'success'
            })
            getArtistData()
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
      formArtist,
      handleSubmit,
      handleDelete,
      uploadUrl

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

.singer-img {
  width: 100%;
  height: 100px;
  border-radius: 5px;
  margin-bottom: 5px;
  overflow: hidden;
}
</style>
