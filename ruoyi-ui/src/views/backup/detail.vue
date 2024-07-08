<template>
    <div id="app">
        <el-container>
            <!-- 头部导航栏 -->
            <AppHeader />
            <!--导航部分  -->
            <el-breadcrumb separator-class="el-icon-arrow-right">
                <el-breadcrumb-item :to="{ path: '/home' }">网站首页</el-breadcrumb-item>
                <el-breadcrumb-item :to="{ path: '/news' }">新闻资讯</el-breadcrumb-item>
                <el-breadcrumb-item :to="{ path: '/news' }">具体的新闻</el-breadcrumb-item>
            </el-breadcrumb>
            <!--导航部分  -->
            <!--文章标题  -->
            <div v-if="hasData">
                <el-row :gutter="10">
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="grid-content bg-purple"></div>
                    </el-col>
                    <el-col :xs="12" :sm="12" :md="12" :lg="12" :xl="12">
                        <div class="grid-content bg-purple-light" style="font-size:1.8rem;font-weight: bold;">
                            {{ data.title }} </div>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="grid-content"></div>
                    </el-col>
                </el-row>
                <!--文章标题  -->
                <!--文章作者 -->
                <el-row :gutter="10" style="font-size:1.2rem;margin-top:1rem;color:#5A5A5A">
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="grid-content bg-purple"></div>
                    </el-col>
                    <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4">
                        <div class="grid-content bg-purple-light">作者:{{ data.author }}</div>
                    </el-col>
                    <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4">
                        <div class="grid-content bg-purple-light">发布时间:{{ data.publishDate }}</div>
                    </el-col>
                    <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4">
                        <div class="grid-content bg-purple-light">{{ data.viewCount }}次浏览</div>
                    </el-col>
                    <el-col :xs="6" :sm="6" :md="6" :lg="6" :xl="6">
                        <div class="grid-content bg-purple-light"></div>
                    </el-col>
                </el-row>
                <!--文章作者  -->
                <!-- 文章内容 -->
                <el-row :gutter="10" style="margin-left: 5rem;margin-top:4rem;font-size:1.3rem;color:#5A5A5A">
                    <el-col :xs="22" :sm="22" :md="22" :lg="22" :xl="22">
                        <div class="grid-content bg-purple">
                            {{ data.content }}
                        </div>
                    </el-col>
                </el-row>
                <!-- 文章内容 -->

                <!--上一篇 -->
                <el-row :gutter="10"
                    style="margin-top: 2rem; display: flex;align-items: center;justify-content: center;font-size:1.3rem;">
                    <el-col :xs="1" :sm="1" :md="1" :lg="1" :xl="1">
                        <div class="grid-content bg-purple"></div>
                    </el-col>
                    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5">
                        <div class="grid-content bg-purple" style=" overflow: hidden;white-space: nowrap; "> {{
                            data.nextNews.lastTitle }}</div>
                    </el-col>
                    <el-col :xs="13" :sm="13" :md="13" :lg="13" :xl="13">
                        <div class="grid-content bg-purple-light"></div>
                    </el-col>
                    <el-col :xs="5" :sm="5" :md="5" :lg="5" :xl="5">
                        <div class="grid-content bg-purple-light" style=" overflow: hidden;white-space: nowrap; ">{{
                            data.nextNews.nextTitle }}</div>
                    </el-col>
                </el-row>
                <!--下一篇  -->
            </div>


            <div v-if="!hasData">
                暂时没有数据
            </div>
            <!-- 底部 -->
            <el-footer>
                <Footer />
            </el-footer>
        </el-container>
    </div>
</template>
  

  
<style scoped>
.el-icon-arrow-right {
    font-size: 1.5rem;
    background-color: aqua;
}

::v-deep .el-breadcrumb {
    font-size: 1.1rem !important;
    line-height: 3rem !important;
    margin-left: 2rem !important;
}

/* 行 列 */
.el-col {
    border-radius: 4px;
}

.bg-purple-dark {
    background: white;
}

.bg-purple {
    background: white;
}

.bg-purple-light {
    background: white;
}

.grid-content {
    border-radius: 4px;
    min-height: 36px;
}
</style>


<script>
import AppHeader from '@/views/hetai/Header.vue';
import Footer from '@/views/hetai/footer.vue';
import { getPreAndNextNews } from "@/api/system/news";
export default {
    name: "Detail",
    components: {
        AppHeader,
        Footer
    },
    data() {
        return {
            id: this.$route.query.id,
            data: {},
            hasData: false,
        };
    },
    created() {
        this.getList();
    },
    methods: {
        getList() {
            if (this.id == null || this.id == undefined) {
                return;
            }
            getPreAndNextNews(this.id).then(response => {
                console.log(response);
                this.data = response.data;
                this.hasData = true;

            });
        },
    }
}
</script>