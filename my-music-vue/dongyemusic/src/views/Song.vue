<template>
  <div style="padding:10px">
      <!--功能区-->
  <div style="margin:10px 0">
    <el-button type="primary" @click="add" v-if="admin.adminLevel===2">新增</el-button>
     <el-button type="primary">导入</el-button>
      <el-button type="primary">导出</el-button>
       <el-button type="primary">查询</el-button>
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
      label="歌曲"
      prop="songName"
       align="center"
     >
      <template slot-scope="scope">
        <el-popover trigger="hover" placement="top">
          <p>歌手: {{ scope.row.singer }}</p>
          <p>专辑: {{ scope.row.album }}</p>
          <p>中文名: {{ scope.row.songCnName }}</p>
          <div slot="reference" class="name-wrapper">
            <el-tag size="medium">{{ scope.row.songName }}</el-tag>
          </div>
        </el-popover>
      </template>
    </el-table-column>
      <el-table-column label="时长"  align="center" >
      <template slot-scope="scope">
      <!-- <i class="el-icon-time"></i>-->
        <span style="margin-left: 10px">{{ scope.row.songTime }}</span>
      </template>
    </el-table-column >
      <el-table-column  align="center" label="封面">
        <template #default="scope">
       <el-image 
          style="width: 100px; height: 100px"
          :src="scope.row.img" 
          :preview-src-list="[scope.row.img]">
       </el-image>
        </template>
      </el-table-column>
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
          <el-popconfirm title="这是一段内容确定删除吗？"  @confirm="handleDelete(scope.row.songNo)">
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
   <el-dialog title="添加歌曲" :visible.sync="dialogFormVisible">
  <el-form :model="form">
    <el-form-item label="歌名" :label-width="formLabelWidth">
      <el-input v-model="form.songName" autocomplete="off"></el-input>
    </el-form-item>
      <el-form-item label="歌手" :label-width="formLabelWidth">
      <el-input v-model="form.singer" autocomplete="off"></el-input>
    </el-form-item>
      <el-form-item label="时间" :label-width="formLabelWidth">
      <el-input v-model="form.songTime" type="time" autocomplete="off"></el-input>
    </el-form-item>
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
  </el-form>
  <div slot="footer" class="dialog-footer">
    <el-button @click="dialogFormVisible = false">取 消</el-button>
    <el-button type="primary" @click="save">确 定</el-button>
  </div>
</el-dialog>


<!--编辑表单-->
<el-dialog title="编辑歌曲" :visible.sync="dialogFormVisibles">
  <el-form :model="forms">
    <el-form-item label="歌名" :label-width="formLabelWidth">
      <el-input v-model="forms.songName" autocomplete="off"></el-input>
    </el-form-item>
      <el-form-item label="歌手" :label-width="formLabelWidth">
      <el-input v-model="forms.singer" autocomplete="off"></el-input>
    </el-form-item>
      <el-form-item label="时间" :label-width="formLabelWidth">
      <el-input v-model="forms.songTime" type="time" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item label="封面" :label-width="formLabelWidth">
        <el-upload
     class="avatar-uploader"
     action="http://localhost:9090/api/file/upload"
     :http-request="editRequest"
     :show-file-list="false"
     :on-success="handleAvatarSuccess"
     :before-upload="beforeAvatarUpload">
     <img v-if="forms.imageUrl" :src="forms.imageUrl" class="avatar">
     <i v-else class="el-icon-plus avatar-uploader-icon"></i>
     </el-upload>
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
    name: 'Song',
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
        total:0,
        tableData:[],
         title: '',
        imageUrl:''
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
              request.get("/song/querySongList",{
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
        },
        save(){       
         this.form.imageUrl=this.imageUrl;
         request.post("/song/addSong",this.form).then(res =>{
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
          request.post("/song/updateSong",this.forms).then(res =>{
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