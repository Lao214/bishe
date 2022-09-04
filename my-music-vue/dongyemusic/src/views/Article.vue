<template>
  <div style="padding:10px">
      <!--功能区-->
  <div style="margin:10px 0">
    <el-button type="goon" @click="add">新增</el-button>
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
      align="center"
      width="50%"
      >
      <template slot-scope="scope">
      <!-- <i class="el-icon-time"></i>-->
        <span style="margin-left: 10px">{{ scope.row.id }}</span>
      </template>
    </el-table-column>
    <el-table-column
      label="标题"
      prop="title"
       align="center"
     >
      <template slot-scope="scope">
        <el-popover trigger="hover" placement="top">
          <p>作者: {{ scope.row.author }}</p>
          <p>状态: {{ scope.row.status }}</p>
          <div slot="reference" class="name-wrapper">
            <el-tag size="medium">{{ scope.row.title }}</el-tag>
          </div>
        </el-popover>
      </template>
    </el-table-column>
      <el-table-column label="创建时间"  align="center" >
      <template slot-scope="scope" >
      <!-- <i class="el-icon-time"></i>-->
        <span style="margin-left: 10px">{{ scope.row.createTimeStr }}</span>
      </template>
    </el-table-column >
      <el-table-column  align="center" label="封面">
        <template #default="scope">
       <el-image 
          style="width: 100px; height: 100px"
          :src="scope.row.cover" 
          :preview-src-list="[scope.row.cover]">
       </el-image>
        </template>
      </el-table-column>
  
    <el-table-column label="操作"  align="center">
      <template slot-scope="scope">
       
        <el-button size="mini"  @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-popconfirm title="这是一段内容确定删除吗？"  @confirm="handleDelete(scope.row.songNo)">
        <el-button slot="reference"  size="mini" type="danger"  >删除</el-button>
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
<!--添加表单 begin-->
   <el-dialog title="添加文章" :visible.sync="dialogFormVisible">
  <el-form :model="form">
    <el-form-item label="标题" :label-width="formLabelWidth">
      <el-input v-model="form.title" autocomplete="off"></el-input>
    </el-form-item>
      <!-- <el-form-item label="作者" :label-width="formLabelWidth">
      <el-input v-model="form.author" autocomplete="off"></el-input>
    </el-form-item> -->
      <!-- <el-form-item label="创建时间" :label-width="formLabelWidth">
      <el-input v-model="form.createTime" type="date" autocomplete="off"></el-input>
    </el-form-item> -->
    <el-form-item label="封面" :label-width="formLabelWidth">
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
    </el-form-item>
    <el-form-item >
    <el-input v-model="form.filename" type="hidden" autocomplete="off"></el-input>
    </el-form-item> 
    <div id="div1"></div>
  </el-form>
  <div slot="footer" class="dialog-footer">
    <el-button @click="dialogFormVisible = false">取 消</el-button>
    <el-button type="primary" @click="save">确 定</el-button>
  </div>
</el-dialog>
<!--添加表单 end-->

<!--编辑表单 begin-->
<el-dialog title="编辑文章" :visible.sync="dialogFormVisibles">
  <el-form :model="forms">
    <el-form-item label="标题" :label-width="formLabelWidth">
      <el-input v-model="forms.title" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item label="封面" :label-width="formLabelWidth">
        <el-upload
     class="avatar-uploader"
     action="http://localhost:9090/api/file/upload"
     :http-request="editRequest"
     :show-file-list="false"
     :on-success="handleAvatarSuccess"
     :before-upload="beforeAvatarUpload">
     <img v-if="forms.cover" :src="forms.cover" class="avatar">
     <i v-else class="el-icon-plus avatar-uploader-icon"></i>
     </el-upload>
    </el-form-item>
    <el-form-item >
    <el-input v-model="forms.filename" type="hidden" autocomplete="off"></el-input>
    </el-form-item>
    <div id="div2"></div>
  </el-form>
  <div slot="footer" class="dialog-footer">
    <el-button @click="dialogFormVisibles = false">取 消</el-button>
    <el-button type="primary" @click="edit">确 定</el-button>
  </div>
</el-dialog>
<!--编辑表单 end-->

<!--详情 begin-->
<el-dialog title="文章详情" v-model="vis" width="50%" >
  <el-card>
    <div v-html="detail.content" style="min-height: 120px"></div>
  </el-card>
</el-dialog>
<!--详情 end-->
  </div>
</template>

<script>
import request from "@/utils/request";
import Qs from 'qs';
import E from 'wangeditor';

let editor;

export default {
    name: 'Article',
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
        dialogFormVisibles: false,
        vis:false,
        total:0,
        tableData:[],
         title: '',
        imageUrl:'',
        detail:{

        }
      }
    },
  created(){
    //页面加载方法
    this.loadTable();
   }, 
   //mounted 所有页面元素加载完才会执行
   mounted(){

   },
        methods: {
          details(row){
           this.detail =row;
           this.vis=true;
          },
          loadTable(){
              request.get("/article/queryArticleList",{
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
         this.form={};
         this.imageUrl=''
         this.dialogFormVisible=true;
         //wangEditor富文本编辑器
         //$nextTick: 作用将回调延迟到下次DOM跟新周期之后执行
         this.$nextTick(()=>{
         if(editor==null){
            editor = new E('#div1');
            editor.config.uploadImgServer='http://localhost:9090/api/file/editorUpload'
            /**自定义参数名称**/
            editor.config.uploadFileName='file'
            editor.create();
         }else{
            editor.destroy();
            editor = new E('#div1');
            editor.config.uploadImgServer='http://localhost:9090/api/file/editorUpload'
            /**自定义参数名称**/
            editor.config.uploadFileName='file'
            editor.create();
         }     
         }); 
        },
        save(){   
        //  console.log(editor.txt.html());
         this.form.content=editor.txt.html();  // 富文本框里面的值 赋予 表单的 content  
         this.form.imageUrl=this.imageUrl;
         let adminStr = sessionStorage.getItem("admin")||"{}";
         var adminObject = eval('('+adminStr+')');
        //  console.log("打印对象："+adminObject);
        //  console.log("打印："+adminStr);
         this.form.author=adminObject.nickname;
         request.post("/article/addArticle",this.form).then(res =>{
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
         this.$nextTick(()=>{
         if(editor==null){
           editor = new E('#div2');
             editor.config.uploadImgServer='http://localhost:9090/api/file/editorUpload'
            /**自定义参数名称**/
            editor.config.uploadFileName='file'
            editor.create();
            editor.txt.html(row.content);
         }else{
            editor.destroy();
            editor = new E('#div2');
              editor.config.uploadImgServer='http://localhost:9090/api/file/editorUpload'
            /**自定义参数名称**/
            editor.config.uploadFileName='file'
            editor.create();
            editor.txt.html(row.content);
         }     
         }); 
      },
      edit(){
          this.forms.content=editor.txt.html();  // 富文本框里面的值 赋予 表单的 content  
          request.post("/article/updateArticle",this.forms).then(res =>{
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
           console.log(this.adminNo)
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
  color:  rgb(237, 133, 14);
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