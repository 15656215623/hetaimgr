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
                <NewsList />
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
export default {
    name: "Home",
    components: {
        AppHeader,
        NewsList,
        Footer
    },

    data() {
        return {
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
    methods: {
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