<template>
    <div id="app">
        <el-container>
            <!-- 头部导航栏 -->
            <AppHeader />
            <!-- 走马观灯 -->
            <Carousel />
            <!--news  -->
            <h1 style="font-size: 2rem;margin-left:2rem">资讯中心</h1>
            <el-tabs v-model="activeName" @tab-click="handleClick" class="tab-style">
                <el-tab-pane label="招标采标" name="first">
                    <NewsList />
                </el-tab-pane>
                <el-tab-pane label="公司新闻" name="second">
                    <NewsList />
                </el-tab-pane>
            </el-tabs>

            <!-- 底部 -->
            <el-footer>
                <Footer />
            </el-footer>
        </el-container>
    </div>
</template>
  
<script>
import NewsList from '@/views/hetai/NewsList.vue';
// import TenderList from '@/views/hetai/TenderList.vue';
import AppHeader from '@/views/hetai/Header.vue';
import Carousel from '@/views/hetai/carousel.vue';
import Footer from '@/views/hetai/footer.vue';
export default {
    name: "Home",
    components: {
        AppHeader,
        NewsList,
        // TenderList,
        Carousel,
        Footer
    },

    data() {
        return {
            activeName: 'first'
        };
    },
    methods: {
        handleClick(tab, event) {
            console.log(tab, event);
        }
    }
};
</script>
  
<style scoped>
element.style {
    width: 8rem;
    transform: translateX(0px);
}

/* 设置默认的文字大小 */
.el-tabs__item {
    font-size: 2.4rem;
}

/* 使用 !important */
::v-deep .el-tabs__nav .el-tabs__item {
    font-size: 1.6rem !important;
    height: 5rem;
    align-items: center;
}

/* 鼠标悬停时字体颜色变化 */
.el-tabs_item:hover {
    color: #3949b4;
}

/* 鼠标悬停时添加下划线 */
.el-tabs_item:hover {
    text-decoration: underline;
}

.tab-style {
    margin-left: 1.1rem;
}
</style>