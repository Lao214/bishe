<template>
  <div class="header">
  <div  class="header-left">后台管理系统</div>
  <div  class="header-center" ></div>
  <div  class="header-right">
    <el-dropdown :hide-on-click="false">
  <span class="el-dropdown-link" style="color: rgb(237, 133, 14);">
    My<i class="el-icon-arrow-down el-icon--right"></i>
  </span>
  <el-dropdown-menu slot="dropdown">
    <el-dropdown-item>个人信息</el-dropdown-item>
    <el-dropdown-item  @click.native="toLogin">退出系统</el-dropdown-item>
  </el-dropdown-menu>
</el-dropdown>
  </div>
  </div>
</template>

<script>
export default {
  name:"Header",
    methods:{
    toLogin:function(){
      this.$router.push("/login")
    }
  }
}
</script>

<style scoped>


.header{
    height: 50px;
    line-height: 50px; 
  
    display: flex;
}

.header-left{
    padding-left: 30px;
    font-weight: bold;
    font-size: 16px;
    color:  rgb(237, 133, 14);
    background: #031e3b;
    width: 200px;
}


.header-center{
    flex: 1;
     background: #031e3b;
}

.header-right{
    width: 100px;
    background: #031e3b;
}

.el-dropdown-link {
    cursor: pointer;
    color: #031e3b;
  }
  .el-icon-arrow-down {
    font-size: 12px;
  }
</style>