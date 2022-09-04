<template>
<div class="background">
<div id="loginBox">
  <h1>LOGIN</h1>
  <el-form ref="form" :model="form" label-width="60px" >
    <div class="inputBox">
       <div class="inputText">
            <el-input prefix-icon="el-icon-user" aria-placeholder="请输入账号" v-model="form.loginNo" style="width:300px" size="normal"></el-input>
       </div>
       <div class="inputText">
            <el-input prefix-icon="el-icon-lock" aria-placeholder="请输入密码" v-model="form.password" style="width:300px" size="normal" show-password></el-input>
       </div>
       <button type="button" id="submit" class="ok" @click="onSubmit">LOGIN</button>
    </div>
  </el-form>
</div>
</div>
</template>

<script>
import request from "@/utils/request";

export default {

  name:"Login",
    data() {
      return {
        form: {}
      }
    },
    methods: {
      onSubmit() {
        request.post("/admin/login",this.form).then(res=>{
              if(res.status===1){
             this.$message({
             message: '登录成功',
               type: 'success'
            });
            sessionStorage.setItem("admin",JSON.stringify(res.data));   //缓存用户信息
            this.$router.push("/home");   //路由在方法中的使用跳转
           }else{
                this.$message({
             message: '登录失败',
               type: 'warning'
            });
           }
        })
      }
    }
  }
</script>

<style >
    * {
      /*初始化 取消页面的内外边框 盒子模型*/
      margin: 0;
      padding: 0;
      /*告诉浏览器 你想要设置的边框和内边距的值包含在总宽高内*/
      box-sizing: border-box;
    }

    #loginBox {
      margin: 0 auto;
      margin-top: 200px;
      padding: 20px 50px;
  
      width: 500px;
      height: 300px;
      text-align: center;
      border-radius: 10px;
      background-color: #00000099;
    }

    #loginBox h1 {
      color: white;
    }

    #loginBox .inputBox {
      margin-top: 20px;
    }

    #loginBox .inputBox .inputText {
      margin-top: 20px;
    }

    #loginBox .inputBox .inputText input {
      background-color: #00000000;
      border: 0;
      border-bottom: 1px solid white;
     
      color: white;
    }

    #loginBox .inputBox .ok {
      margin-top: 30px;
      width: 120px;
      height: 32px;
      border: 0;
      border-radius: 10px;
      color: white;
      background-image: linear-gradient(to right, #e7c9d2 0%, #dcc3e5 15%, #b9bce1 35%, #b9d3d9 55%, #add9aa 75%, #d4dcad 88%, #e4d3c7 100%);
    }
 
 .background{
   background-size: cover;
    width:100%;  
    height:100vh;  /**宽高100%是为了图片铺满屏幕 */
    background-image:url(../assets/xiyang.png) ;
    overflow: hidden;
}
</style>