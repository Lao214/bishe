<template>
  <div style="padding:10px">
      <!--功能区-->
  <div style="margin:10px 0">
    <el-button type="goon" @click="add" v-if="admin.adminLevel===2">新增</el-button>
     <el-button type="goon">导入</el-button>
      <el-button type="goon">导出</el-button>
       <el-button type="goon">查询</el-button>
        <el-input style="width:20%" placeholder="请输入内容"  clearable  v-model="input">
  </el-input>
  </div>
    <el-table
    :data="tableData"
    style="width: 100%">
    <el-table-column
      label="ID"
      prop="id"
      >
      <template slot-scope="scope">
      <!-- <i class="el-icon-time"></i>-->
        <span style="margin-left: 10px">{{ scope.row.id }}</span>
      </template>
    </el-table-column>
    <el-table-column
      label="会员"
      prop="nickname"
     >
      <template slot-scope="scope">
        <el-popover trigger="hover" placement="top">
          <p>昵称: {{ scope.row.nickname }}</p>
          <p>编号: {{ scope.row.adminNo }}</p>
          <div slot="reference" class="name-wrapper">
            <el-tag size="medium">{{ scope.row.nickname }}</el-tag>
          </div>
        </el-popover>
      </template>
    </el-table-column>
          <el-table-column label="状态" >
      <template slot-scope="scope">
      <!-- <i class="el-icon-time"></i>-->
        <span style="margin-left: 10px">{{ scope.row.status }}</span>
      </template>
    </el-table-column>
     <el-table-column label="等级" >
      <template slot-scope="scope">
        <span style="margin-left: 10px" v-if="scope.row.adminLevel===1">一级管理员</span>
         <span style="margin-left: 10px" v-if="scope.row.adminLevel===2">二级管理员</span>
      </template>
    </el-table-column>
    <el-table-column label="操作">
      <template slot-scope="scope">
        <el-button
          size="mini"
          @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-popconfirm title="这是一段内容确定删除吗？"  @confirm="handleDelete(scope.row.adminNo)">
        <el-button
          slot="reference"
          size="mini"
          type="danger"
          >删除</el-button>
          </el-popconfirm>
      </template>
    </el-table-column>
  </el-table>

      <div class="block">
    <el-pagination  @size-change="handleSizeChange"  @current-change="handleCurrentChange"  :current-page="currentPage"  :page-sizes="[10, 15, 20, 30]" :page-size="pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total">
    </el-pagination>
  </div>

   <el-dialog title="添加管理员" :visible.sync="dialogFormVisible">
  <el-form :model="form">
    <el-form-item label="昵称" :label-width="formLabelWidth">
      <el-input v-model="form.nickname" autocomplete="off"></el-input>
    </el-form-item>
      <el-form-item label="登录账户" :label-width="formLabelWidth">
      <el-input v-model="form.loginNo" autocomplete="off"></el-input>
    </el-form-item>
      <el-form-item label="密码" :label-width="formLabelWidth">
      <el-input v-model="form.password" autocomplete="off"></el-input>
    </el-form-item>
     <el-form-item label="手机号" :label-width="formLabelWidth">
      <el-input v-model="form.mobileNo" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item label="管理员级别" :label-width="formLabelWidth">
      <el-select v-model="form.adminLevel" placeholder="--请选择--">
        <el-option label="一级管理员" value="1"></el-option>
        <el-option label="二级管理员" value="2"></el-option>
      </el-select>
    </el-form-item>
  </el-form>
  <div slot="footer" class="dialog-footer">
    <el-button @click="dialogFormVisible = false">取 消</el-button>
    <el-button type="primary" @click="save">确 定</el-button>
  </div>
</el-dialog>

<el-dialog title="编辑管理员" :visible.sync="dialogFormVisibles">
  <el-form :model="forms">
    <el-form-item label="昵称" :label-width="formLabelWidth">
      <el-input v-model="forms.nickname" autocomplete="off"></el-input>
    </el-form-item>
      <el-form-item label="登录账户" :label-width="formLabelWidth">
      <el-input v-model="forms.loginNo" autocomplete="off"></el-input>
    </el-form-item>
      <el-form-item label="密码" :label-width="formLabelWidth">
      <el-input v-model="forms.password" autocomplete="off"></el-input>
    </el-form-item>
     <el-form-item label="手机号" :label-width="formLabelWidth">
      <el-input v-model="forms.mobileNo" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item label="管理员级别" :label-width="formLabelWidth">
      <el-select v-model="forms.adminLevel" placeholder="--请选择--">
        <el-option label="一级管理员" value="1"></el-option>
        <el-option label="二级管理员" value="2"></el-option>
      </el-select>
    </el-form-item>
  </el-form>
  <div slot="footer" class="dialog-footer">
    <el-button @click="dialogFormVisibles = false">取 消</el-button>
    <el-button type="primary" @click="edit">确 定</el-button>
  </div>
</el-dialog>
  </div>
</template>

<script>
import request from "@/utils/request";
import Qs from 'qs';

export default {
    name: 'Home',
    compoments:{

    },
    data(){
      return{
        form:{},
        forms:{},
        inputs:{},
        input:'',
        formLabelWidth: '80px',
        currentPage:0,
        pageSize:10,
        dialogTableVisible: false,
        dialogFormVisible: false,
        dialogFormVisibles: false,
        total:0,
        tableData:[

        ],
        admin:{}
      }
    },
  created(){
        let  adminStr =sessionStorage.getItem('admin')||'{}';
        this.admin= eval('('+adminStr+')');

        //请求服务端，确认当前用户的合法信息
        request.get("/admin/"+this.admin.id).then(res=>{
          if(res.code==='0'){
            this.admin =res.data
          }
          });
    //页面加载方法
    this.loadTable();
   }, 
        methods: {
          loadTable(){
              request.get("/admin/queryAdminList",{
                params:{
                offset:this.currentPage,
                limit:this.pageSize,
                }  
              }).then(res => {
                 console.log(res);
                 this.tableData=res.data;
                 this.total=res.total;
              })
          },
        add(){
         this.dialogFormVisible=true;
         this.form={};
        },
        save(){
         console.log(this.form)
         request.post("/admin/addAdmin",this.form).then(res =>{
             if(res.status===1){
             this.$message({
             message: '添加成功',
               type: 'success'
            });
           }else{
                this.$message({
             message: '添加失败',
               type: 'warning'
            });
           }
         })
         this.loadTable();
         this.dialogFormVisible=false;
        },
      handleEdit(index, row) {
       this.dialogFormVisibles=true;
       this.forms=JSON.parse(JSON.stringify(row));  //深拷贝
      },
      edit(){
          request.post("/admin/updateAdmin",this.forms).then(res =>{
           console.log(res)
           if(res.status===1){
             this.$message({
             message: '更新成功',
               type: 'success'
            });
           }else{
                this.$message({
             message: '更新失败',
               type: 'warning'
            });
           }
           this.loadTable();
         })
           this.dialogFormVisibles=false;  
      },
      handleDelete(adminNo) {
           this.adminNo=adminNo
           request.post("/admin/deleteAdmin/"+this.adminNo).then(res =>{
           console.log(res)
           if(res.status===1){
             this.$message({
             message: '删除成功',
               type: 'success'
            });
           }else{
                this.$message({
             message: '删除失败',
               type: 'warning'
            });
           }
           this.loadTable();
         })
      },
         handleSizeChange(pageSize) {    //改变当前页的个数触发
         this.pageSize =pageSize
    
         this.loadTable();
      },
      handleCurrentChange(currentPage) {   //改变当前页码触发
      
        this.currentPage=currentPage
        this.loadTable();
      }
    }

}

</script>

<style>


/* 按钮风格重定义 */
 .el-button--goon {
   color: rgb(255, 255, 255);
  background-color: #031e3b;
  border-color: #031e3b;
  height: 34px;
  line-height: 34px;
  padding: 0 20px;
}
.el-button--goon:focus,
.el-button--goon:hover {
  color: rgb(237, 133, 14);
  background-color: #0b3869;
  border-color: #0b3869;
  height: 34px;
  line-height: 34px;
  padding: 0 20px;
}
.el-button--goon:active {
  color: rgb(237, 133, 14);
  background-color: #084383;
  border-color: #084383;
  height: 34px;
  line-height: 34px;
  padding: 0 20px;
}
/* 按钮风格重定义 */

 .block{
   margin: 10px 0;
   position: fixed;
   bottom: 0;
 }
</style>