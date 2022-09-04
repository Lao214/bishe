<template>
      <u-upload 
	  ref="upload"
	  :action="action" 
	  :max-size="5*1024*1024"
	  max-count="1"
	  :before-upload="beforeUpload"
	  :custom-btn="true"
	  :form-data ="formData"
	  :show-progress="false"
	  :multiple="false"
	  :deletable ="false"
	  :show-upload-list="true"
	  @on-success="onSuccess"

	  >
	  	<u-avatar slot="addBtn" :src="vuex_user.headPath" size="140"></u-avatar>
	  </u-upload>
</template>

<script>
	/**
	 * 处理小程序问题
	 * let _this
	 * 然后 
	 * created(){
	 *  _this =this
	 * }
	 */
	let _this = {}
	export default {
		name:"uploadHead",
		data() {
			return {
				action:'http://localhost:9090/api/file/upload',
				formData:{},
				upFileName:'',
				headPath:''
			};
		},
		created(){
			_this =this
		},
        methods: {
			/**
			 * 上传成功之后
			 */
		async onSuccess(res) {
			    //移除预览区域的图片
				this.$refs.upload.remove(0)
			
			    console.log("这是刚上传的头像:"+res.data);
				//请求API更新头像
				await this.$u.api.updateMemberInfo({headPath:res.data,id:this.vuex_user.id})
				 
				//更新缓存的用户信息
				this.$u.utils.updateMember();
				
				this.$u.toast('更新头像成功')
			},
			
			/**
			 * 上传前的钩子
			 */
	        async  beforeUpload(index, list) {
				// //list 是文件全部东西
				// const {file} =list[0]
				// //如果是H5
				// // #ifdef H5
				// const fileName =file.name
				// // #endif
				// //如果不是H5
				// // #ifndef H5
				// const fileName =file.path
				// // #endif
				 
				// //请求前获取osstoken
				// const ossToken =await _this.$u.api.authOssToken();
				
				// _this.action =ossToken.host  //设置上传域名
				
				// //获取后缀名
				// const suffix =fileName.slice(fileName.lastIndexOf('.'))
				// //处理唯一文件名
				// const upFileName = _this.$u.guid(20) + suffix
				
				// _this.upFileName =upFileName
				
				// //额外的上传参数
				// _this.formData ={
				// 	'key': upFileName,//上传后的文件名
				// 	'policy':ossToken.policy,
				// 	'OSSAccessKeyId':ossToken.accessid,
				// 	'success_action_status':'200', //让服务端返回200，不然会默认204
				// 	'signature':ossToken.signature    //签名
				// }	
			 return true;		
			}
		}
	}
</script>

<style>

</style>