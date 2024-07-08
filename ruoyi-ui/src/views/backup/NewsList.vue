<template>
  <el-table :data="tableData" class="table" :row-class-name="tableRowClassName" show-header="false">
    <el-table-column prop="name" @click="toDetails()">
      <template #default="{ row }">
        <span class="bullet"></span>{{ row.address }}
      </template>
    </el-table-column>
    <el-table-column prop="date" width="180">
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
export default {
  methods: {
    tableRowClassName({ row, rowIndex }) {
      return 'row-height'; // 设置行高类名
    }
  },
  data() {
    return {
      tableData: [{
        date: '2016-05-02',
        name: '王小虎',
        address: '新闻标题11111',
      }, {
        date: '2016-05-04',
        name: '王小虎',
        address: '新闻标题11111'
      }, {
        date: '2016-05-01',
        name: '王小虎',
        address: '新闻标题11111',
      }, {
        date: '2016-05-03',
        name: '王小虎',
        address: '新闻标题11111'
      }]
    }
  },
  methods: {
    toDetails() {
      this.$router.push("/details");
    }
  }
}
</script>