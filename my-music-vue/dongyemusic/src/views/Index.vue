<template>
  <div>
           <input type="checkbox" id="menu_btn">
    <label class="menu_btn" for="menu_btn">
      <i class="fa fa-bars" aria-hidden="true"></i>
    </label>
    <div class="con">
      <h1>欢迎进入物品管理系统</h1>
      <h3>软件V183 劳威锟</h3>
    </div>
    <div class="wrapper">
      <ul class="menu">
        <li><a  href="http://localhost:8081/#/login">后台系统</a></li>
        <li><a >企业官网</a></li>
        <li><a >关于我们</a></li>
        <li><a >一起交流</a></li>
      </ul>
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
    *{
      /*初始化 取消页面的内外边框 盒子模型*/
      margin: 0;
      padding: 0;
      /*告诉浏览器 你想要设置的边框和内边距的值包含在总宽高内*/
      box-sizing: border-box;
    }

    .con{
      /*弹性布局*/
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      /*百分百窗口高达*/
      height: 100vh;
      line-height: 80px;
      font-size: 30px;
      /*字间距*/
      letter-spacing: 15px;
    }

    .wrapper{
      position: fixed;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      /*渐变都用这个*/
      background: linear-gradient(200deg,#031e3b,#306baa);
      /*将元素切成一个圆形*/
      /*25px是元的直径 calc（表示圆心）*/
      clip-path: circle(25px at calc(100% - 45px) 45px);

      /*过渡动画*/
      transition: all 0.3s ease-in-out;
    }

    .menu_btn{
      position: absolute;
      right: 20px;
      top: 20px;
      z-index: 2;
      /*渐变背景*/
      background:linear-gradient(200deg,#c1c4da,#606b4b) ;
      width: 50px;
      height: 50px;
      text-align: center;
      line-height: 50px;
      border-radius: 50%;
      color: rgb(203, 231, 255);
      font-size: 20px;
      cursor: pointer;

      transition:all 0.3s ease;
    }

    /*把复选框隐藏*/
    #menu_btn{
      display: none;
    }

    #menu_btn:checked ~ .wrapper{
      clip-path: circle(75%);
    }

    #menu_btn:checked ~ .menu_btn{
      color: #959bd1;
      background: #e6cc5c;
    }

    #menu_btn:checked ~ .menu_btn i::before{
      content: "\f00d";
    }



    .wrapper ul li a::after{
      content: "";
      position: absolute;
      width: 100%;
      height: 50px;
      background: rgb(237, 133, 14);
      z-index: -1;
      border-radius: 50px;
      left: 0px;
      transform: scaleY(0);
      /*动画过渡*/
      transform: transform 0.3s ease;
    }

    .wrapper ul li a:hover::after{
      transform: scaleY(1);
    }

    .wrapper ul li a:hover{
      color: rgba(2, 19, 10, 0.767);
    }

    .wrapper ul{
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%,-50%);
      list-style: none;
      text-align: center;
    }

    .wrapper ul li{
      margin: 30px 0px;
    }

    .wrapper ul li a{
      color: #fff;
      text-decoration: none;
      font-size: 30px;
      font-weight: 500;
      padding: 5px 50px;
      position: relative;
      line-height: 50px;
    }
</style>