<template>
  <div style="padding:10px">
      <!--功能区-->
  <div style="margin:10px 0">
    <el-button type="goon" @click="add" v-if="admin.adminLevel===2">新增</el-button>
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
      label="名称"
      prop="wareHouseName"
       align="center"
     >
      <template slot-scope="scope">
        <el-popover trigger="hover" placement="top">
          <p>省份: {{ scope.row.province }}</p>
          <p>城市: {{ scope.row.city }}</p>
          <p>区域: {{ scope.row.area }}</p>
          <p>街道: {{ scope.row.address }}</p>
          <div slot="reference" class="name-wrapper">
            <el-tag size="medium">{{ scope.row.wareHouseName }}</el-tag>
          </div>
        </el-popover>
      </template>
    </el-table-column>

     <el-table-column label="管理者"  align="center" >
      <template slot-scope="scope">
        <span style="margin-left: 10px">{{ scope.row.houseManager }}</span>
      </template>
    </el-table-column >
<!-- 
     <el-table-column label="库存量"  align="center" >
      <template slot-scope="scope">
        <span style="margin-left: 10px">{{ scope.row.goodsCount }}</span>
      </template>
    </el-table-column >

    <el-table-column label="库存类别数"  align="center" >
      <template slot-scope="scope">
        <span style="margin-left: 10px">{{ scope.row.goodsTypeCount }}</span>
      </template>
    </el-table-column > -->

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
        <!-- <el-button size="mini"  @click="handleEdit(scope.$index, scope.row)" v-if="admin.adminLevel===2">编辑</el-button> -->
          <el-popconfirm title="这是一段内容确定删除吗？"  @confirm="handleDelete(scope.row.id)">
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
    <el-pagination  @size-change="handleSizeChange"  @current-change="handleCurrentChange"  :current-page="currentPage"  :page-sizes="[5, 10, 15, 20]" :page-size="pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total">
    </el-pagination>
  </div>
<!--添加表单-->
   <el-dialog title="添加仓库" :visible.sync="dialogFormVisible">
  <el-form :model="form">
    <el-form-item label="仓库名" :label-width="formLabelWidth">
      <el-input v-model="form.wareHouseName" autocomplete="off"></el-input>
    </el-form-item>
      <el-form-item label="省份" :label-width="formLabelWidth">
      <el-select v-model="form.provinceNo"  placeholder="请选择" @change="changeValue" style="width:27%">
    <el-option
      v-for="item in provinces"
      :key="item.areaNo"
      :label="item.areaName"
      :value="item.areaNo">
      <span style="float: left">{{ item.areaName }}</span>
      <span style="float: right; color: #8492a6; font-size: 13px">{{ item.areaPinyin }}</span>
    </el-option>
  </el-select>
    </el-form-item>

   <el-form-item label="城市" :label-width="formLabelWidth" >
      <el-select v-model="form.cityNo"  placeholder="请选择" @change="changeCityValue" style="width:27%">
    <el-option
      v-for="items in cities"
      :key="items.areaNo"
      :label="items.areaName"
      :value="items.areaNo">
      <span style="float: left">{{ items.areaName }}</span>
      <span style="float: right; color: #8492a6; font-size: 13px">{{ items.areaPinyin }}</span>
    </el-option>
   </el-select>
    </el-form-item>

    <el-form-item label="辖区" :label-width="formLabelWidth">
      <el-select v-model="valueArea"  placeholder="请选择" style="width:27%">
    <el-option
      v-for="itemA in areas"
      :key="itemA.areaNo"
      :label="itemA.areaName"
      :value="itemA.areaNo">
      <span style="float: left">{{ itemA.areaName }}</span>
      <span style="float: right; color: #8492a6; font-size: 13px">{{ itemA.areaPinyin }}</span>
    </el-option>
   </el-select>
    </el-form-item>
    
    <el-form-item label="详细地址" :label-width="formLabelWidth">
      <el-input v-model="form.address" autocomplete="off"></el-input>
    </el-form-item>

       <el-form-item label="管理者" :label-width="formLabelWidth" >
    <el-select v-model="form.houseManager"  placeholder="请选择" style="width:27%">
    <el-option
      v-for="item in wareHouseManager"
      :key="item.nickname"
      :label="item.areaName"
      :value="item.nickname">
      <span style="float: left">{{ item.nickname }}</span>
      <span style="float: right; color: #8492a6; font-size: 13px">电话：{{ item.mobileNo }}</span>
    </el-option>
   </el-select>
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
<el-dialog title="编辑仓库" :visible.sync="dialogFormVisibles">
  <el-form :model="forms">
<el-form-item label="仓库名" :label-width="formLabelWidth">
      <el-input v-model="forms.wareHouseName" autocomplete="off"></el-input>
    </el-form-item>

    <el-form-item label="省份" :label-width="formLabelWidth" style="width:30%">
      <el-select v-model="valueEdit"  placeholder="请选择" @change="changeValue">
    <el-option
      v-for="item in provinces"
      :key="item.areaNo"
      :label="item.areaName"
      :value="item.areaNo">
      <span style="float: left">{{item.areaName }}</span>
      <span style="float: right; color: #8492a6; font-size: 13px">{{ item.areaPinyin }}</span>
    </el-option>
  </el-select>
    </el-form-item>

   <el-form-item label="城市" :label-width="formLabelWidth" style="width:30%">
      <el-select v-model="valueCitiesEdit"  placeholder="请选择" @change="changeCityValue">
    <el-option
      v-for="items in cities"
      :key="items.areaNo"
      :label="items.areaName"
      :value="items.areaNo">
      <span style="float: left">{{ items.areaName }}</span>
      <span style="float: right; color: #8492a6; font-size: 13px">{{ items.areaPinyin }}</span>
    </el-option>
   </el-select>
    </el-form-item>

    <el-form-item label="辖区" :label-width="formLabelWidth" style="width:30%">
      <el-select v-model="valueArea"  placeholder="请选择">
    <el-option
      v-for="itemArea in areas"
      :key="itemArea.areaNo"
      :label="itemArea.areaName"
      :value="itemArea.areaNo">
      <span style="float: left">{{ itemArea.areaName }}</span>
      <span style="float: right; color: #8492a6; font-size: 13px">{{ itemArea.areaPinyin }}</span>
    </el-option>
   </el-select>
    </el-form-item>
      <el-form-item label="管理者" :label-wisdth="formLabelWidth">
      <el-input v-model="forms.houseManager" autocomplete="off"></el-input>
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
    name: 'Warehouse',
    compoments:{

    },
    data(){
      return{
        provinces: [],
        cities:[],
        areas:[],
        wareHouseManager:[],
        valueArea: '',
        valueEdit: '',
        valueCitiesEdit: '',
        valueAreaEdit: '',
        form:{
          imageUrl:'',
        },
        forms:{
          imageUrl:'',
        },
        inputs:{},
        file:{},
        input:'',
        formLabelWidth: '80px',
        currentPage:0,
        pageSize:5,
        dialogTableVisible: false,
        dialogFormVisible: false,
        dialogFormVisibleExport: false,
        dialogFormVisibles: false,
        total:0,
        tableData:[],
         title: '',
        imageUrl:''
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
      });
      //页面加载方法
      this.loadTable();
      this.loadProvince();
      this.loadManager();
    }, 
        methods:
      {
          loadTable(){
              request.get("/warehouse/queryWarehouseList",{
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
          loadManager(){
              request.get("/area/queryAdminList",{
                params:{
                offset:0,
                limit:10,
                }  
              }).then(res => {
                 console.log(res);
                 this.wareHouseManager=res.data;
              })
          },
          loadProvince(){
              request.get("/area/queryProvinceList",{
                params:{
                offset:0,
                limit:999,
                }  
              }).then(res => {
                 console.log(res);    
                 this.provinces =res.data 
              })
          },
          loadCity(areaNo){
              request.get("/area/queryCityList",{
                params:{
                offset:0,
                limit:999,
                areaNo:areaNo
                }  
              }).then(res => {
                 console.log(res);   
                 this.cities =res.data   
              })
          },
          loadArea(areaNo){
              request.get("/area/queryAreaList",{
                params:{
                offset:0,
                limit:999,
                areaNo:areaNo
                }  
              }).then(res => {
                 console.log(res);   
                 this.areas =res.data   
              })
          },
        add(){
         this.form={};
         this.imageUrl=''
         this.valueArea=''
         this.dialogFormVisible=true;
        },
        save(){       
         this.form.imageUrl=this.imageUrl;
         this.form.areaNoA =this.valueArea;
         request.post("/warehouse/addWarehouse",this.form).then(res =>{
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
          request.post("/warehouse/updateWarehouse",this.forms).then(res =>{
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
      handleDelete(id) {
           this.id=id
           request.post("/warehouse/deleteWarehouse/"+this.id).then(res =>{
           console.log(res)
           console.log(this.id)
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
      changeValue(){
          console.log(this.form.provinceNo);
          this.loadCity(this.form.provinceNo);
          this.form.cityNo='';
          this.valueArea='';
      },
       changeCityValue(){
       console.log(this.form.cityNo);
       this.loadArea(this.form.cityNo);
       this.valueArea='';
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