<template>
    <el-container>
        <!-- 头部导航栏 -->
        <AppHeader />
        <!-- 图片-->
        <img src="/images/3gvw.jpg" />
        <br /><br /> <br /><br />
        <!--  中间部分  -->

        <el-row :gutter="12">
            <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="6">
                <el-row class="grid-content bg-purple">新闻资讯</el-row>
                <el-row class="grid-content bg-purple1">公司资讯</el-row>
            </el-col>
            <el-col :xs="16" :sm="16" :md="16" :lg="16" :xl="18">
                <el-row class="title"> 新闻资讯</el-row>
                <el-row>
                    <el-table :data="newsList" style="font-size: 1.3rem;color:black">
                        <el-table-column prop="title">
                            <template #default="{ row }">
                                <router-link :to="{ path: '/detail', query: { id: row.id } }">
                                    {{ row.title }}
                                </router-link>
                            </template>
                        </el-table-column>
                        <el-table-column prop="publishDate" width="180">
                            <template #default="{ row }">
                                <router-link :to="{ path: '/detail', query: { id: row.id } }">
                                    {{ row.publishDate }}
                                </router-link>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-row>
                <el-row>
                    <el-col :span="4"></el-col>
                    <el-col :span="16">
                        <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum"
                            :limit.sync="queryParams.pageSize" @pagination="getList" style="font-size: 1.3rem;" />
                    </el-col>
                    <el-col :span="4"></el-col>
                </el-row>
            </el-col>
        </el-row>
        <!-- 底部 -->
        <el-footer>
            <Footer />
        </el-footer>
    </el-container>
</template>
  
<script>
import NewsList from '@/views/hetai/NewsList.vue';
import AppHeader from '@/views/hetai/Header.vue';
import Footer from '@/views/hetai/footer.vue';
import { listNews } from "@/api/system/news";
export default {
    name: "News",
    components: {
        AppHeader,
        NewsList,
        Footer
    },

    data() {
        return {
            // 遮罩层
            loading: true,
            // 总条数
            total: 10,
            //新闻列表
            newsList: [],
            // 查询参数
            queryParams: {
                pageNum: 1,
                pageSize: 10,
            },
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
        };
    },
    created() {
        this.getList();
    },
    methods: {
        getList() {
            this.queryParams.kinds = 2;
            listNews(this.queryParams).then(response => {
                this.newsList = response.rows;
                this.total = response.total;
                this.loading = false;
            });
        }
    }
};
</script>
  
<style scoped>
.el-col {
    border-radius: 4px;
}

.bg-purple {
    color: white;
    height: 5.6rem;
    font-size: 1.5rem;
    background-image: url('/images/image_yl5v.png');
}

.bg-purple1 {
    height: 5rem;
    font-size: 1.4rem;
    background-image: url('/images/image_bxwg.png');
    background-repeat: no-repeat;
}

.grid-content {
    width: 100%;
    display: flex;
    justify-content: center;
    /* 水平居中 */
    align-items: center;
    margin-top: 1rem;
}

.row-bg {
    padding: 0.67rem 0;
    background-color: #f9fafc;
}

/* 设置行高 */
.row-height {
    height: 7rem;
}

/* 优化表格样式 */
.el-table__body tr {
    height: 6rem;
}

.title {
    font-size: 2rem;
    margin-left: 0rem;
    margin-top: 1rem;
    color: rgb(15, 15, 128);
    font-weight: bold;
    width: 100%;
    position: relative;
    float: left;
}
</style>