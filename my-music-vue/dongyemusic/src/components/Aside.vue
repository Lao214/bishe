<template>
  <div>
     <el-menu
      :default-active="$route.path.substr(1)"
      class="el-menu-vertical-demo"
      style="width:200px;height:calc(120vh - 50px)"
      background-color="#031e3b"
      text-color="#fff"
      router
      active-text-color="rgb(237, 133, 14)">

      <el-submenu index="home" v-if="admin.adminLevel===2">
        <template slot="title">
          <i class="el-icon-user"></i>
          <span>管理员与用户</span>
        </template>
        <el-menu-item-group>
          <template slot="title">管理员</template>
          <el-menu-item index="home">管理员</el-menu-item>
        </el-menu-item-group>
        <el-menu-item-group title="会员">
          <el-menu-item index="memberApply">会员申请</el-menu-item>
          <!-- <el-menu-item index="role">会员角色</el-menu-item> -->
        </el-menu-item-group>
      <!--<el-submenu index="1-4">
          <template slot="title">选项4</template>
          <el-menu-item index="1-4-1">选项1</el-menu-item>
        </el-submenu>-->
      </el-submenu> 

        <!-- <el-submenu index="song">
        <template slot="title">
          <i class="el-icon-location"></i>
          <span>音乐专区</span>
        </template>
        <el-menu-item-group>
          <template slot="title">歌曲与歌手</template>
          <el-menu-item index="song">歌曲</el-menu-item>
          <el-menu-item index="5-2">选项2</el-menu-item>
        </el-menu-item-group>
        <el-menu-item-group title="分组2">
          <el-menu-item index="5-3">选项3</el-menu-item>
        </el-menu-item-group>
        <el-submenu index="5-4">
          <template slot="title">选项4</template>
          <el-menu-item index="5-4-1">选项1</el-menu-item>
        </el-submenu>
      </el-submenu> -->

       <el-submenu index="stock">
        <template slot="title">
          <i class="el-icon-location"></i>
          <span>物品</span>
        </template>
        <el-menu-item-group>
          <template slot="title">库存与审核</template>
          <el-menu-item index="stock">库存</el-menu-item>
          <el-menu-item index="stockAudit">审核</el-menu-item>
           <el-menu-item index="warehouse">仓库</el-menu-item>
        </el-menu-item-group>
      </el-submenu>

     <el-submenu index="2-4">
          <template slot="title"><i class="el-icon-setting"></i>基础</template>
          <el-menu-item index="article">文章</el-menu-item>
          <el-menu-item index="count">统计</el-menu-item>
          <el-menu-item index="cangMap">地图</el-menu-item>
     </el-submenu>

      <!-- <el-menu-item index="3" disabled>
        <i class="el-icon-document"></i>
        <span slot="title">导航三</span>
      </el-menu-item> -->

      <!-- <el-menu-item index="4">
        <i class="el-icon-setting"></i>
        <span slot="title">导航四</span>
      </el-menu-item> -->
    </el-menu>
  </div>
</template>

<script>
import request from "@/utils/request"

export default {
  name:"Aside",
   compoments:{

    },
    data(){
      return {
        admin:{}
      }
    },
     created() {
        let  adminStr =sessionStorage.getItem('admin')||'{}';
        this.admin= eval('('+adminStr+')');

        //请求服务端，确认当前用户的合法信息
        request.get("/admin/"+this.admin.id).then(res=>{
          if(res.code==='0'){
            this.admin =res.data
          }
        })
    },
     methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      }
    }
  }
</script>

<style>

</style>