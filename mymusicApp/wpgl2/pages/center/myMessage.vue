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
					<view @click="readMessage(item.id)"   style="display: flex; flex-wrap: wrap; padding-top: 17rpx;padding-bottom: 17rpx;">
						<view class="item1">{{item.content}}<a  style="font-size: 15rpx;">(
						<a style="color: red;"   v-if="item.status=='noread'">未读</a>
						<a style="color: green;" v-if="item.status=='read'">已读</a>)</a></view>
						<view class="item2">{{item.createTimeStr}}</view>
					</view>
				</view>
			</u-card>
			
		<u-popup border-radius=20 v-model="show" width="500rpx" height="280rpx" mode="center">
			<view class="colorkuai"></view>
			<view style="padding-top:10rpx;padding-left:20rpx;padding-right:20rpx;text-align: center;">{{this.message}}</view>
			<view style="padding-left:150rpx ;">
			<u-button size="medium" @click="close" :custom-style="customStyle">关闭</u-button>	
			</view>
			
		</u-popup>	
	</view>
</template>

<script>
	export default {
		data() {
			return {
				form:{},
				title: '我的消息',
				subTitle: '全部',
				show:false,
				message:'',
				list: [
						{
							name: '已读',
							disabled: false
						},
						{
							name: '未读',
							disabled: false
						},
					],
				// u-radio-group的v-model绑定的值如果设置为某个radio的name，就会被默认选中
				value: '',
				valueE:'',
				memberId:'',
				customStyle: {
					marginTop: '20px', // 注意驼峰命名，并且值必须用引号包括，因为这是对象
					color: 'red',
					
				}
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
						
						console.log(e)
					},
					// 选中任一radio时，由radio-group触发
					radioGroupChange(e) {
						// console.log(e);
						this.subTitle =e;
						if(e=='已读'){
							this.valueE ='read'
						}
						if(e=='未读'){
							this.valueE='noread'
						}
						this.getData()
					},
					async getData(){
						const params ={
							status:this.valueE,
							memberId:this.memberId,
						}
						const res= await this.$u.api.queryMessageList(params);
						if(res.data.status==1){
							this.form =res.data.data
							console.log(this.form)
						}
					},
					async readMessage(id){
						const params ={
							id:id,
						}
						const res= await this.$u.api.queryMessageById(params);
						if(res.data.status==1){
							console.log(res.data.data.details)
							this.message=res.data.data.details
							this.show=true;	
						}
					},
					close(){
						this.show=false;
						this.getData()
					}

		}
	}
</script>

<style scoped lang="scss">
	.u-card-wrap { 
		background-color: $u-bg-color;
		padding: 1px;
	}
	.colorkuai{
		background-color: #ff9008;
		height: 70rpx;
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
