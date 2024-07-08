<template>
  <footer class="footer">
    <el-row :gutter="10">
      <el-col :xs="4" :sm="6" :md="8" :lg="9" :xl="11">
      </el-col>
      <el-col :xs="4" :sm="6" :md="8" :lg="9" :xl="11">
        <img src="/images/beian.png" />
        <a target="_blank" href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=11011502003826" style="
            display: inline-block;
            text-decoration: none;
            height: 20px;
            line-height: 20px;
          "></a>
      </el-col>
      <el-col :xs="16" :sm="12" :md="8" :lg="6" :xl="2">
        <a href="https://beian.miit.gov.cn/" target="_blank"
          style=" display: inline-block;  text-decoration: none;  height: 20px;  line-height: 20px; ">
          <span style="height: 20px; line-height: 20px; color: #030303">冀ICP备18032095号-2</span></a>
      </el-col>
    </el-row>

    <el-row :gutter="10">
      <el-tooltip v-for="(link, index) in links" :key="index" :content="link.tooltip" placement="top">
        <el-col :xs="4" :sm="4" :md="4" :lg="4" :xl="4">
          <a :href="link.url" target="_blank">
            <img :src="link.image" alt="友链" class="foot_img" />
          </a>
        </el-col>
      </el-tooltip>
    </el-row>
  </footer>
</template>
  
<script>
export default {
  name: "Footer",
  data() {
    return {
      links: [
        { url: 'http://www.chinabidding.cn', tooltip: '采购与招标网', image: '/images/btm1.png' },
        { url: 'http://www.ahtba.org.cn', tooltip: '安徽省招标协会', image: '/images/btm2.png' },
        { url: 'http://www.ccgp-anhui.gov.cn', tooltip: '安徽省政府采购网', image: '/images/btm3.png' },
        { url: 'http://www.cnaec.com.cn/', tooltip: '中国工程咨询协会', image: '/images/btm4.png' },
        { url: 'http://www.ahaec.com/', tooltip: '安徽工程咨询网', image: '/images/btm5.png' },
        { url: 'http://www.ccgp.gov.cn/', tooltip: '中国政府采购网', image: '/images/btm6.png' },
      ],
      copyright: '版权所有 © 2024 某公司'
    };
  }
};
</script>
  
<style scoped>
.footer {
  background-color: #f5f5f5;
  padding: 20px 0;
  text-align: center;
}

.friend-links a {
  display: inline-block;
  margin: 0 10px;
}

.friend-links img {
  width: 182px;
  height: 102px;
}

.copyright {
  margin-top: 10px;
  font-size: 14px;
  color: #666;
}

.beian {
  width: 100%;
  text-align: center;
  margin-bottom: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.el-col {
  border-radius: 4px;
}

.foot_img {
  width: 90%;
  height: 100%;
}
</style>