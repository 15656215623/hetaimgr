<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="新闻标题" prop="title">
        <el-input v-model="queryParams.title" placeholder="请输入新闻标题" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="发布日期" prop="publishDate">
        <el-date-picker clearable v-model="queryParams.publishDate" type="date" value-format="yyyy-MM-dd"
          placeholder="请选择发布日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['system:news:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['system:news:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['system:news:remove']">删除</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="newsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="id" />
      <el-table-column label="新闻标题" align="center" prop="title" />
      <el-table-column label="作者" align="center" prop="author" :formatter="authorFormat" />
      <el-table-column label="类型" align="center" prop="kinds" :formatter="dataTypeFormat" />
      <el-table-column label="发布日期" align="center" prop="publishDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.publishDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="浏览量" align="center" prop="viewCount" />
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改news对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="100%" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="新闻标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入新闻标题" />
        </el-form-item>
        <el-form-item label="新闻内容">
          <editor v-model="form.content" :min-height="192" />
        </el-form-item>
        <el-form-item label="作者" prop="author">
          <el-select v-model="form.author">
            <el-option v-for="dict in authorOptions" :key="dict.dictValue" :label="dict.dictLabel"
              :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="发布日期" prop="publishDate">
          <el-date-picker clearable v-model="form.publishDate" type="date" value-format="yyyy-MM-dd"
            placeholder="请选择发布日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="附件" prop="attachmentUrl">
          <FileUpload v-model="form.attachmentUrl" />
        </el-form-item>
        <el-form-item label="浏览量" prop="viewCount">
          <el-input v-model="form.viewCount" placeholder="请输入浏览量" />
        </el-form-item>
        <el-form-item label="类型" prop="kinds">
          <el-select v-model="form.kinds">
            <el-option v-for="dict in dataTypeOptions" :key="dict.dictValue" :label="dict.dictLabel"
              :value="dict.dictValue"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listNews, getNews, delNews, addNews, updateNews } from "@/api/system/news";
import FileUpload from '@/components/FileUpload';
export default {
  name: "News",
  components: {
    FileUpload
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // news表格数据
      newsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: null,
        content: null,
        author: null,
        publishDate: null,
        attachmentId: null,
        viewCount: null
      },
      // 表单参数
      form: {},
      //数据类型
      dataTypeOptions: [],
      //作者类型
      authorOptions: [],
      // 表单校验
      rules: {
        title: [
          { required: true, message: "新闻标题不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  mounted() {
    //1、新闻类型
    this.getDicts("ht_content_type").then(response => {
      this.dataTypeOptions = response.data;
    });
    //2、作者
    this.getDicts("ht_author").then(response => {
      this.authorOptions = response.data;
    });
  },
  methods: {
    // 数据类型字典翻译
    dataTypeFormat(row, column) {
      //查找dataTypeOptions里dictVal等于row.kinds的row的dictLabel
      var name = "";
      this.dataTypeOptions.forEach(item => {
        if (item.dictValue == row.kinds) {
          name = item.dictLabel;
          return name;
        }
      });
      return name;
    },
    //作者字典翻译
    authorFormat(row, column) {
      //查找 authorOptions里dictVal等于row.kinds的row的dictLabel
      var name = "";
      this.authorOptions.forEach(item => {
        if (item.dictValue == row.author) {
          name = item.dictLabel;
          return name;
        }
      });
      return name;
    },
    /** 查询news列表 */
    getList() {
      this.loading = true;
      listNews(this.queryParams).then(response => {
        this.newsList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        title: null,
        content: null,
        author: null,
        publishDate: null,
        attachmentId: null,
        viewCount: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加news";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getNews(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改news";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateNews(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addNews(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除news编号为"' + ids + '"的数据项？').then(function () {
        return delNews(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/news/export', {
        ...this.queryParams
      }, `news_${new Date().getTime()}.xlsx`)
    }
  }
};

</script>
