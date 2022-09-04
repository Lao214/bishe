<template>
  <div style="padding:10px">
      <!--功能区-->
  <div style="margin:10px 0">
    <el-button type="goon" @click="add" v-if="admin.adminLevel===2">新增</el-button>
     <el-button type="goon" @click="dialogFormVisibleExport=true" v-if="admin.adminLevel===2">导入</el-button>
      <el-button type="goon" @click="exportStock">导出</el-button>
       <el-button type="goon" @click="loadTable">查询</el-button>
        <el-input style="width:20%" placeholder="请输入物品名称"  clearable  v-model="input">
  </el-input>
  </div>
    <el-table
    :data="tableData"
    style="width: 100%">
    <el-table-column
      label="ID"
      prop="id"
      align="center"
      width="50%"
      >
      <template slot-scope="scope">
      <!-- <i class="el-icon-time"></i>--> 
        <span style="margin-left: 10px">{{ scope.row.id }}</span>
      </template>
    </el-table-column>
    <el-table-column
      label="名称"
      prop="name"
       align="center"
     >
      <template slot-scope="scope">
        <el-popover trigger="hover" placement="top">
          <p>地区: {{ scope.row.stockName }}</p>
          <p>品牌: {{ scope.row.brand }}</p>
          <p>规格: {{ scope.row.specs }}</p>
          <div slot="reference" class="name-wrapper">
            <el-tag size="medium">{{ scope.row.name }}</el-tag>
          </div>
        </el-popover>
      </template>
    </el-table-column>
     <el-table-column label="一级分类"  align="center" >
      <template slot-scope="scope">
      <!-- <i class="el-icon-time"></i>-->
        <span style="margin-left: 10px">{{ scope.row.oneType }}</span>
      </template>
    </el-table-column >
    <el-table-column label="二级分类"  align="center" >
      <template slot-scope="scope">
      <!-- <i class="el-icon-time"></i>-->
        <span style="margin-left: 10px">{{ scope.row.secondType }}</span>
      </template>
    </el-table-column >
      <el-table-column label="数量"  align="center" >
      <template slot-scope="scope">
      <!-- <i class="el-icon-time"></i>-->
        <span style="margin-left: 10px">{{ scope.row.count }}</span>
      </template>
    </el-table-column >

      <!-- <el-table-column  align="center" label="封面">
        <template #default="scope">
       <el-image 
          style="width: 100px; height: 100px"
          :src="scope.row.img" 
          :preview-src-list="[scope.row.img]">
       </el-image>
        </template>
      </el-table-column> -->  
    <el-table-column label="状态"  align="center" >
      <template slot-scope="scope">
      <!-- <i class="el-icon-time"></i>-->
        <span style="margin-left: 10px">{{ scope.row.status }}</span>
      </template>
    </el-table-column>
    <el-table-column label="操作"  align="center">
      <template slot-scope="scope">
        <el-button
          size="mini"
          @click="handleEdit(scope.$index, scope.row)" v-if="admin.adminLevel===2">编辑</el-button>
          <el-popconfirm title="这是一段内容确定删除吗？"  @confirm="handleDelete(scope.row.stockNo)">
        <el-button
          slot="reference"
          size="mini"
          type="danger"
          v-if="admin.adminLevel===2"
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
<!--添加表单-->
   <el-dialog title="添加物品" :visible.sync="dialogFormVisible">
  <el-form :model="form">
    <el-form-item label="物品名" :label-width="formLabelWidth">
      <el-input v-model="form.name" autocomplete="off"></el-input>
    </el-form-item>
      <el-form-item label="一级分类" :label-width="formLabelWidth">
      <el-input v-model="form.oneType" autocomplete="off"></el-input>
    </el-form-item>
      <el-form-item label="二级分类" :label-width="formLabelWidth">
      <el-input v-model="form.secondType" autocomplete="off"></el-input>
    </el-form-item>
       <el-form-item label="地区" :label-width="formLabelWidth">
      <el-input v-model="form.stockName" autocomplete="off"></el-input>
    </el-form-item>
      <el-form-item label="品牌" :label-width="formLabelWidth">
      <el-input v-model="form.brand" autocomplete="off"></el-input>
    </el-form-item>
     <el-form-item label="规格" :label-width="formLabelWidth">
      <el-input v-model="form.specs" autocomplete="off"></el-input>
    </el-form-item>
     <el-form-item label="数量" :label-width="formLabelWidth">
      <el-input v-model="form.count" autocomplete="off"></el-input>
    </el-form-item>
   <!-- <el-form-item label="封面" :label-width="formLabelWidth">
      <el-upload
     class="avatar-uploader"
     action="#"
     :http-request="httpRequest"
     :show-file-list="false"
     :on-success="handleAvatarSuccess"
     :before-upload="beforeAvatarUpload">

     <img v-if="imageUrl" :src="imageUrl" class="avatar">
     <i v-else class="el-icon-plus avatar-uploader-icon"></i>
     </el-upload>
    </el-form-item>-->
    <el-form-item >
    <el-input v-model="form.filename" type="hidden" autocomplete="off"></el-input>
    </el-form-item> 
  </el-form>
  <div slot="footer" class="dialog-footer">
    <el-button @click="dialogFormVisible = false">取 消</el-button>
    <el-button type="primary" @click="save">确 定</el-button>
  </div>
</el-dialog>


<!--导入文件对话框-->
   <el-dialog title="导入物品" :visible.sync="dialogFormVisibleExport">
  <el-upload  class="upload-demo" drag action="http://localhost:9090/api/stock/import" :show-file-list="false" accept="xlsx" :on-success="handleExcelImportSuccess" multiple>
  <i class="el-icon-upload"></i>
  <div class="el-upload__text">请将Excel文件拖到此处，或<em>点击上传</em></div>
  </el-upload>
  <div slot="footer" class="dialog-footer">
    <el-button @click="dialogFormVisibleExport = false">取 消</el-button>
  </div>
</el-dialog>


<!--编辑表单-->
<el-dialog title="编辑物品" :visible.sync="dialogFormVisibles">
  <el-form :model="forms">
    <el-form-item label="物品名" :label-width="formLabelWidth">
      <el-input v-model="forms.name" autocomplete="off"></el-input>
    </el-form-item>
      <el-form-item label="一级分类" :label-width="formLabelWidth">
      <el-input v-model="forms.oneType" autocomplete="off"></el-input>
    </el-form-item>
      <el-form-item label="二级分类" :label-width="formLabelWidth">
      <el-input v-model="forms.secondType"  autocomplete="off"></el-input>
    </el-form-item>
       <el-form-item label="地区" :label-width="formLabelWidth">
      <el-input v-model="forms.stockName"  autocomplete="off"></el-input>
    </el-form-item>
      <el-form-item label="品牌" :label-width="formLabelWidth">
      <el-input v-model="forms.brand"  autocomplete="off"></el-input>
    </el-form-item>
      <el-form-item label="规格" :label-width="formLabelWidth">
      <el-input v-model="forms.specs"  autocomplete="off"></el-input>
    </el-form-item>
      <el-form-item label="数量" :label-width="formLabelWidth">
      <el-input v-model="forms.count"  autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item >
    <el-input v-model="forms.filename" type="hidden" autocomplete="off"></el-input>
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
    name: 'Stock',
    compoments:{

    },
    data(){
      return{
        form:{
          imageUrl:''
        },
        forms:{
          imageUrl:''
        },
        inputs:{},
        file:{},
        input:'',
        formLabelWidth: '80px',
        currentPage:0,
        pageSize:10,
        dialogTableVisible: false,
        dialogFormVisible: false,
        dialogFormVisibleExport: false,
        dialogFormVisibles: false,
        total:0,
        tableData:[],
         title: '',
        imageUrl:'',
        
      }
    },
    created()
    {
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
        methods:
      {
          loadTable(){
              request.get("/stock/queryStockList",{
                params:{
                offset:this.currentPage,
                limit:this.pageSize,
                name:this.input,
                }  
              }).then(res => {
                 console.log(res);
                 this.tableData=res.data;
                 this.total=res.total;
              })
          },
        add(){
         this.form={};
         this.imageUrl=''
         this.dialogFormVisible=true;
        },
        save(){       
         this.form.imageUrl=this.imageUrl;
         request.post("/stock/addStock",this.form).then(res =>{
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
             this.loadTable();
         }) 
         this.dialogFormVisible=false;
        },
      handleEdit(index, row) {
       this.dialogFormVisibles=true;
       this.forms=JSON.parse(JSON.stringify(row));  //深拷贝
      },
      edit(){
          request.post("/stock/updateStock",this.forms).then(res =>{
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
      handleDelete(stockNo) {
           this.stockNo=stockNo
           request.post("/stock/deleteStock/"+this.stockNo).then(res =>{
           console.log(res)
           console.log(this.stockNo)
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
      },
      exportStock(){
         window.open("http://localhost:9090/api/stock/export");
      },
        handleExcelImportSuccess(){
             this.$message({
             message: '文件导入成功',
               type: 'success'
            });
         //   this.dialogFormVisibleExport=false;
            this.loadTable();
      },
    /**图片相关方法**/
       handleAvatarSuccess(res, file) {
         console.log(res.data);
         console.log(file.raw);
         this.form.img  = res.data;
         this.form.rootFilePath=res.msg;
         this.form.imageUrl = URL.createObjectURL(file.raw);
      },
      beforeAvatarUpload(file) {
       // const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;
       /* if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!');
        }*/
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
       // return isJPG && isLt2M;
       return isLt2M;
      },
   httpRequest (form) {
      let _this = this
      let rd = new FileReader() // 创建文件读取对象
      let file = form.file
      this.form.filename=file.name
      console.log(this.form.filename)
      rd.readAsDataURL(file) // 文件读取装换为base64类型
      rd.onloadend = function (e) {
        _this.imageUrl = this.result // this指向当前方法onloadend的作用域
      }
    },
      editRequest (forms) {
      let _this = this
      let rd = new FileReader() // 创建文件读取对象
      let file = forms.file
      this.forms.filename=file.name
      console.log(this.forms.filename)
      rd.readAsDataURL(file) // 文件读取装换为base64类型
      rd.onloadend = function (e) {
        _this.forms.imageUrl = this.result // this指向当前方法onloadend的作用域
      }
    },
    
    }

}

</script>

<style>
 .block{
   margin: 10px 0;
   position: fixed;
   bottom: 0;
 }

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
  color:  rgb(237, 133, 14);
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



 .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 124px;
    height: 124px;
    line-height: 124px;
    text-align: center;
  }
  .avatar {
    width: 124px;
    height: 124px;
    display: block;
  }
</style>