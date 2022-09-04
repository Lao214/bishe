<template>
	<view>
		<view class="u-p-l-30 u-p-r-30">
		<u-form  :model="form">
			<u-form-item label="仓库" prop="stockAuditName" >
				<u-input  v-model="form.stockAuditName" disabled />
			</u-form-item>
			<u-form-item label="名称" prop="name" >
				<u-input  v-model="form.name" disabled />
			</u-form-item>
			<u-form-item label-width="130" label="一级分类" prop="oneType" >
				<u-input  v-model="form.oneType" disabled />
			</u-form-item>
			<u-form-item label-width="130" label="二级分类" prop="secondType" >
				<u-input  v-model="form.secondType" disabled />
			</u-form-item>
			<u-form-item label="品牌" prop="brand" >
				<u-input  v-model="form.brand" disabled />
			</u-form-item>
			<u-form-item label="规格" prop="specs" >
				<u-input  v-model="form.specs" disabled />
			</u-form-item>
			<u-form-item label="数量" prop="count" >
				<u-input  v-model="form.count" disabled />
			</u-form-item>
			<u-form-item label="类型" prop="orderNo" >
				<u-input  v-model="form.orderNo" disabled />
			</u-form-item>
			<view style="display: flex;">
			<u-button @click="auditReject" v-if="this.vuex_user.memberLevel==2">驳回</u-button>
			<u-button @click="auditPass" v-if="this.vuex_user.memberLevel==2">通过</u-button>
			</view>
		</u-form>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				form:{
					stockAuditName:'',
					name:'',
					oneType:'',
					secondType:'',
					brand:'',
					specs:'',
					count:'',
					orderNo:'',
					stockAuditNo:'',
				},
				auditId:''
			}
		},
		onLoad(option) {
			console.log(option.id)
			this.auditId =option.id
			this.getData()
		},
		methods: {
		    async	getData(){
				const params={
					id:this.auditId
				}
				const res = await this.$u.api.queryAuditDetails(params);
				if(res.data.status==1){
					this.form=res.data.data;
					console.log(this.form)
				}
			},
			async auditPass(){
				const res =await this.$u.api.stockAuditPass(this.form.stockAuditNo);
			    if(res.data.status==1){
				   this.$u.toast('审核通过成功')
			    }
				setTimeout(() =>{
					//跳转到首页
					this.$u.route({
						type:'reLaunch',
						url:'pages/audit/audit'
					})
				},1000)	
			},
			async auditReject(){
				const res =await this.$u.api.stockAuditReject(this.form.stockAuditNo);
				console.log(res)
				if(res.data.status==1){
					this.$u.toast('驳回成功')
				}
				setTimeout(() =>{
					//跳转到首页
					this.$u.route({
						type:'reLaunch',
						url:'pages/audit/audit'
					})
				},1000)	
			}
		}
	}
</script>

<style>

</style>
