<template>
    <div id="car">
        <el-carousel indicator-position="outside" arrow="always">
            <el-carousel-item v-for="(item, index) in items" :key="index" class="ht_carousel">
                <img :src="item.image" alt="" style="width: 100%; height: 800px">
                <div class="carousel-caption">
                    <h1>{{ item.title }}</h1>
                </div>
            </el-carousel-item>
        </el-carousel>
    </div>
</template>
  
<script>
export default {
    name: 'Carousel',
    data() {
        return {
            isMobile: false,
            width: 0,
            height: 0,
            screenWidth: document.body.clientWidth,
            items: [
                {
                    image: '/images/c4.png',
                    title: '效率成就品质 诚信铸就未来'
                },
                {
                    image: '/images/c2.jpg',
                    title: '公平、公正、合理'
                },
                {
                    image: '/images/c3.jpg',
                    title: '诚信、守法'
                },
                {
                    image: '/images/c5.jpg',
                    title: '踔厉奋发，勇毅前行'
                },
                {
                    image: '/images/c1.jpg',
                    title: '为您提供优质、高效服务'
                }
            ]
        };
    },
    methods: {
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
  
<style scoped>
#car {
    display: flex;
    overflow: hidden;
    width: 100%;
    margin: auto;
    height: 40rem;
}

.carousel-caption {
    position: absolute;
    bottom: 0;
    left: 0;
    right: 0;
    background-color: rgba(0, 0, 0, 0.5);
    color: #fff;
    padding: 10px;
    text-align: center;
}

.carousel-caption h1 {
    font-size: 3em;
    margin: 0;
    padding: 10px;
}

.el-carousel {
    width: 100%;
    height: 40rem;
}

::v-deep .el-carousel__container {
    position: relative;
    height: 40rem !important;
}
</style>