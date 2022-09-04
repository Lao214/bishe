<template>
  <div>
    <!--头部-->
    <Header />
    <!--主体-->
  <div class="main">
    <!--侧边栏-->
    <Aside />
    <!--内容区域-->
    <router-view class="main-context" />
  </div>
  </div>
</template>

<script>
import Header from '@/components/Header'
import Aside from '@/components/Aside'

export default {
  name:"Layout",
    components:{
    Header,
    Aside,
  }
}
</script>

<style>

</style>