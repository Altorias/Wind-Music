<template>
  <div class="container">
    <div class="function">
      <div class="function-left">
        <el-button @click="deleteAll">批量删除</el-button>
        <el-button type="primary" @click="addDialogVisible">添加歌手</el-button>
      </div>
      <div class="function-right">
        <el-input placeholder="搜索" v-model="searchWord"></el-input>
      </div>
    </div>
    <div class="table">
      <el-table :data="tableData">
        <el-table-column type="selection" width="40" align="center"></el-table-column>
        <el-table-column label="序号" type="index" width="100" align="center"/>
        <el-table-column label="歌手图片" prop="pic" width="110" align="center">
          <template v-slot="scope">
            <div class="singer-img">
              <img :src="attachImageUrl(scope.row.pic)" style="width: 100%" />
            </div>
            <el-upload :action="uploadUrl(scope.row.id)" :show-file-list="false" :on-success="handleImgSuccess" :before-upload="beforeImgUpload">
              <el-button>更新图片</el-button>
            </el-upload>
          </template>
        </el-table-column>
        <el-table-column label="歌手" prop="name" width="120" align="center"></el-table-column>
        <el-table-column label="性别" prop="sex" width="60" align="center">
          <template v-slot="scope">
            <div>{{ changeSex(scope.row.sex) }}</div>
          </template>
        </el-table-column>
        <el-table-column label="出生日期" prop="birth" width="120" align="center">
          <template v-slot="scope">
            <div>{{ getBirth(scope.row.birth) }}</div>
          </template>
        </el-table-column>
        <el-table-column label="地区" prop="location" width="100" align="center"></el-table-column>
        <el-table-column label="简介" prop="introduction">
          <template v-slot="scope">
            <p style="height: 100px; overflow: scroll">
              {{ scope.row.introduction }}
            </p>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>

</template>

<script>
import {computed, getCurrentInstance, ref, watch} from "vue";

export default {
  name: "ArtistPage",
  setup() {
    const {proxy} = getCurrentInstance();

    const tableData = ref([]); // 记录歌曲，用于显示
    const tempDate = ref([]); // 记录歌曲，用于搜索时能临时记录一份歌曲列表
    const pageSize = ref(5); // 页数
    const currentPage = ref(1); // 当前页

    // 计算当前表格中的数据
    const data = computed(() => {
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

    getData();

    const getData = async () => {
      tableData.value = [];
      tempDate.value = [];
      const result = await proxy.$api.getArtistList()
      tableData.value = result.data;
      tempDate.value = result.data;
      currentPage.value = 1;
    }

    return {
      tableData,

    }
  }
}
</script>

<style scoped>

</style>