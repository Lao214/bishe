<template>
	<view>
		<u-radio-group style="padding-left:200rpx;padding-top:25rpx ; " v-model="value" @change="radioGroupChange">
					<u-radio 	@change="radioChange" 
						v-for="(item, index) in list" :key="index" 
						:name="item.name"
						:disabled="item.disabled"
					>
						{{item.name}}
					</u-radio>
		</u-radio-group>
		<u-card :title="title" title-size=40 :sub-title="subTitle">
				<view class="" slot="body"  v-for="item in form">
					<view   style="display: flex; flex-wrap: wrap; padding-top: 17rpx;padding-bottom: 17rpx;">
						<view class="item1">{{item.name}}<a  style="font-size: 15rpx;">(
						<a style="color: red;" v-if="item.status=='待审核'">{{item.status}}</a>
						<a style="color: green;" v-if="item.status=='审核通过'">{{item.status}}</a>)</a></view>
						<view class="item2">{{item.stockAuditName}}</view>
						<view class="item3">{{item.stockAuditNo}}</view>
						<view class="item4">{{item.createTimeStr}}</view>
					</view>
				</view>
			</u-card>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				form:{},
				title: '我的出入库申请',
				subTitle: '出入库',
				
				list: [
						{
							name: '出库',
							disabled: false
						},
						{
							name: '入库',
							disabled: false
						},
					],
					// u-radio-group的v-model绑定的值如果设置为某个radio的name，就会被默认选中
				value: '',
				memberId:'',
				
			}
		},
		onReady() {
			
		},
		onLoad(option) {
			console.log(option)
			this.memberId= option.id
			this.getData()
		},
		methods: {
					// 选中某个单选框时，由radio时触发
					radioChange(e) {
						
						// console.log(this.value)
					},
					// 选中任一radio时，由radio-group触发
					radioGroupChange(e) {
						// console.log(e);
						console.log(this.value)
						this.subTitle =e;
						this.getData()
					},
					async getData(){
						const params ={
							orderNo:this.value,
							applyUser:this.memberId,
						}
						const res= await this.$u.api.queryMyAuditList(params);
						if(res.data.status==1){
							this.form =res.data.data
							console.log(this.form)
						}
					},
					async detailsGo(id){
						console.log(id)
							this.$u.route({
							type:'navigateTo',
							url:'pages/audit/auditDetails?id='+id
						})
					}
		}
	}
</script>

<style scoped lang="scss">
	.u-card-wrap { 
		background-color: $u-bg-color;
		padding: 1px;
	}
	
	.u-body-item {
		font-size: 32rpx;
		color: #333;
		padding: 20rpx 10rpx;
	}
		
	.u-body-item image {
		width: 120rpx;
		flex: 0 0 120rpx;
		height: 120rpx;
		border-radius: 8rpx;
		margin-left: 12rpx;
	}
	
	.item1{
		width: 60%;
		padding-bottom: 24rpx;
		color: darkblue;
		font-size: 40rpx;
		margin-left:20rpx ;
	}
	.item2{
		width: 30%;
		padding-bottom: 14rpx;
	}
	.item3{
		width: 60%;
		padding-bottom: 24rpx;
		font-size: 25rpx;
	}
	.item4{
		width: 40%;
		padding-bottom: 14rpx;
		font-size: 20rpx;
	}

</style>