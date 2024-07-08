<template>
    <div class="app-container">
        <el-container>
            <!-- 头部导航栏 -->
            <AppHeader />
            <!-- 图片-->
            <img src="/images/0_mylm.jpg" />
            <!--news  -->
            <el-row :gutter="10">
                <span class="title">招标采标</span>
            </el-row>
            <el-row :gutter="10">
                <!-- <NewsList /> -->
                <el-row class="title"> 新闻资讯</el-row>
                <el-row>
                    <el-table :data="newsList" style="font-size: 1.3rem;color:black">
                        <el-table-column prop="title">
                            <template #default="{ row }">
                                <router-link :to="{ path: '/detail', params: { id: row.id } }">
                                    {{ row.title }}
                                </router-link>
                            </template>
                        </el-table-column>
                        <el-table-column prop="publishDate" width="180">
                            <template #default="{ row }">
                                <router-link :to="{ path: '/detail', params: { id: row.id } }">
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
            </el-row>
            <!-- 底部 -->
            <el-footer>
                <Footer />
            </el-footer>
        </el-container>
    </div>
</template>
  
<script>
import NewsList from '@/views/hetai/NewsList.vue';
import AppHeader from '@/views/hetai/Header.vue';
import Footer from '@/views/hetai/footer.vue';
import { listNews } from "@/api/system/news";
export default {
    name: "callBids",
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
            // 总条数
            total: 10,
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
        //获取页面的所有数据
        getList() {
            this.queryParams.kinds=1;
            listNews(this.queryParams).then(response => {
                this.newsList = response.rows;
                this.total = response.total;
                this.loading = false;
            });
        },

    }
};
</script>
  
<style scoped>
.title {
    font-size: 2.5rem;
    margin-left: 1rem;
    margin-top: 2rem;
    color: rgb(15, 15, 128);
    font-weight: bolder;
    width: 80%;
    position: relative;
    float: left;
}
</style>