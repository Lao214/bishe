<template>
<div class="background">
<div id="loginBox">
  <h1>REGISTER</h1>
  <el-form ref="form" :model="form" label-width="60px" :rules="rules" inline >
    <div class="inputBox">
       <div class="inputText">
         <el-form-item prop="loginNo">
            <el-input prefix-icon="el-icon-user" v-model="form.loginNo" style="width:300px" size="normal"></el-input>
         </el-form-item>
       </div>
       <div class="inputText">
         <el-form-item prop="password">
            <el-input prefix-icon="el-icon-lock"  v-model="form.password" style="width:300px" size="normal" show-password></el-input>
          </el-form-item>
       </div>
       <div class="inputText">
         <el-form-item prop="confirm">
            <el-input prefix-icon="el-icon-lock"  v-model="form.confirm" style="width:300px" size="normal" show-password></el-input>
         </el-form-item>
       </div>
       <button type="button" id="submit" class="ok" @click="onSubmit">REGISTER</button>
    </div>
  </el-form>
</div>
</div>
</template>

<script>
import request from "@/utils/request";

export default {

  name:"Register",
    data() {
      return {
        form: {},
         rules: {
          loginNo: [
            { required: true, message: '请输入用户名', trigger: 'blur' },
            { min: 5, max: 12, message: '长度在 5 到 12 个字符', trigger: 'blur' }
          ],
           password: [
            { required: true, message: '请输入密码', trigger: 'blur' },
            { min: 5, max: 12, message: '长度在 3 到 5 个字符', trigger: 'blur' }
          ],
           confirm: [
            { required: true, message: '请输入确认密码', trigger: 'blur' }
           
          ],
        },
      }
    },
    methods: {

      onSubmit() {
      if(this.form.password!==this.form.confirm){
            this.$message({
             message: '两次密码输入不一致',
             type: 'warning' });
             return 
            }
             this.$refs['form'].validate((valid) => {
          if (valid) {
        request.post("/admin/register",this.form).then(res=>{
              if(res.status===1){
             this.$message({
             message: '注册成功',
               type: 'success'
            });
            this.$router.push("/login");   //路由在方法中的使用跳转
           }else{
                this.$message({
             message: res.msg,
               type: 'warning'
            });
           }
        })  
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
      height: 400px;
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
    width:100%;  
    height:100vh;  /**宽高100%是为了图片铺满屏幕 */
    background:url(../assets/ks.png) ;
    overflow: hidden;
}
</style>