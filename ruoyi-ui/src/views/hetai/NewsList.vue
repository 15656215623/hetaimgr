<template>
  <el-table v-loading="loading" :data="newsList" class="table" :row-class-name="tableRowClassName" :show-header="false">
    <el-table-column prop="title">
      <template #default="{ row }">
        <span class="bullet"></span>{{ row.title }}
      </template>
    </el-table-column>
    <el-table-column prop="publishDate" width="180">
    </el-table-column>
  </el-table>
</template>

<style>
.table {
  width: 90%;
  margin-left: 4%;
  display: inline-block;
  font-family: Microsoft YaHei;
  font-size: 1.4rem;
  color: #5A5A5A;
  font-weight: normal;
  text-decoration: none;
  line-height: 1.8rem;
}

.bullet {
  display: inline-block;
  width: 8px;
  height: 8px;
  background-color: rgb(72, 128, 146);
  border-radius: 50%;
  margin-right: 5px;
}

/* 设置行高 */
.row-height {
  height: 8rem;
}

/* 优化表格样式 */
.el-table__body tr {
  height: 6.67rem;
}
</style>

<script>
import { listCurrentNews } from "@/api/system/news";
export default {
  name: "NewsList",
  props: {
    kinds: {
      type: String,
      default: "1"
    },
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      queryParams: {
        title: '',
        content: '',
        kinds: parseInt(this.kinds),
      },
      //新闻列表
      newsList: [],
    }
  },
  created() {
    //1、获取资讯列表
    this.getList();
  },
  methods: {
    getList() {
      this.loading = true;
      listCurrentNews(this.queryParams).then(response => {
        console.log("获取资讯列表");
        console.log(response.rows);
        this.newsList = response.rows;
        this.loading = false;
      });
    },
    tableRowClassName({ row, rowIndex }) {
      return 'row-height'; // 设置行高类名
    }
  }
}
</script>