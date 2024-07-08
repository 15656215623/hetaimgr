<template>
  <div class="home">
    <div class="top">
      <div class="top-left">
        <div class="logo" @click="clickMe">铜陵合泰项目管理有限公司</div>
      </div>
      <div class="top-right">
        <el-menu mode="horizontal" router background-color="#34495E" text-color="#fff" active-text-color="#40B180"
          default-active="/home" close="top-menu">
          <el-submenu v-if="isMobile" index="">
            <template slot="title">更多</template>
            <el-menu-item index="/home">网站首页</el-menu-item>
            <el-menu-item index="/callBids">招标采标</el-menu-item>
            <el-menu-item index="/news">新闻资讯</el-menu-item>
            <el-menu-item index="/login">登录</el-menu-item>
            <el-menu-item index="/about">关于</el-menu-item>
          </el-submenu>
          <el-menu-item v-if="!isMobile" index="/home">网站首页</el-menu-item>
          <el-menu-item v-if="!isMobile" index="/callBids">招标采标</el-menu-item>
          <el-menu-item v-if="!isMobile" index="/news">新闻资讯</el-menu-item>
          <el-menu-item v-if="!isMobile" index="/login">登录</el-menu-item>
          <el-menu-item v-if="!isMobile" index="/about">关于</el-menu-item>
        </el-menu>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Home",
  components: {

  },
  data() {
    return {
      isMobile: false,
      width: 0,
      height: 0,
      screenWidth: document.body.clientWidth,
    };
  },
  methods: {
    clickMe() {
      this.$router.push("/");
    },
    getScreen() {
      let screenWidth = document.body.clientWidth;
      let screenHeight = document.body.clientHeight;
      return screenWidth / screenHeight;
    },
    listenScreen() {
      let initScale = this.getScreen();
      if (initScale < 1) {
        this.isMobile = true;
      }
      window.addEventListener("resize", () => {
        this.isMobile = this.getScreen() < 1 ? true : false;
      });
    },
  },
  mounted() {
    this.listenScreen();
  },
};
</script>
<style lang="scss" scoped>
.home {
  padding-top: 4rem;
}

.avatar {
  cursor: pointer;
}

.top {
  display: flex;
  width: 100%;
  align-items: center;
  justify-content: space-between;
  background-color: #34495E;
  position: fixed;
  top: 0;
  z-index: 15001;

  .top-left {
    display: flex;
    align-items: center;
    padding-left: 10px;
  }

  .top-right {
    padding-right: 10px;
    color: white;
  }

  .logo {
    color: white;
    cursor: pointer;
    font-weight: bold;
    font-size: 18px;
    padding-bottom: 4px;
  }
}

.home-box {
  overflow: auto;
  min-height: calc(100vh - 8rem);
}

.footer {
  position: relative;
  bottom: 0;
}

.home .el-menu.el-menu--horizontal {
  border-bottom: none;
}

.home .el-menu--horizontal>.el-menu-item {
  border-bottom: none;
}

.home .el-menu--horizontal>.el-menu-item.is-active {
  font-weight: bold;
}
</style>
