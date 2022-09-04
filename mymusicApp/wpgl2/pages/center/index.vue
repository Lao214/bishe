<template>
	<view>
		<u-navbar :is-back="false" title="　" :border-bottom="false">
			<view class="u-flex u-row-right" style="width: 100%;">
				<view class="camera u-flex u-row-center">
					<u-icon name="camera-fill" color="#000000" size="48"></u-icon>
				</view>
			</view>
		</u-navbar>
		<view class="u-flex user-box u-p-t-30 u-p-l-30 u-p-r-20 u-p-b-30">
			<view class="u-m-r-10 avatar-body" >
				<!-- <u-avatar :src="pic" size="140"></u-avatar> -->
				<!--头像src-->
<!-- 				<u-avatar :src="vuex_user.headPath" size="140"></u-avatar> -->
					<uploadHead></uploadHead>
			</view>
			<view class="u-flex-1">
				<view class="u-font-18 u-p-b-20">{{vuex_user.memberName}}</view>
				<view class="u-font-14 u-tips-color" v-if="this.vuex_user.memberLevel == 1">角色:普通</view>
				<view class="u-font-14 u-tips-color" v-else>角色:审核员</view>
			</view>
	<!-- 		<view class="u-m-l-10 u-p-10">
				<u-icon name="scan" color="#969799" size="28"></u-icon>
			</view>
			<view class="u-m-l-10 u-p-10">
				<u-icon name="arrow-right" color="#969799" size="28"></u-icon>
			</view> -->
		</view>
		

		
		<view class="u-m-t-20">
			<u-cell-group>
				<u-cell-item icon="setting" title="个人信息" @click="toBaseInfo"></u-cell-item>
				<u-cell-item icon="list" title="审核员申请"  v-if="this.vuex_user.memberLevel == 1" @click="showpop = true" ></u-cell-item>			
			    <u-cell-item icon="email" title="我的消息" @click="toMyMessage"></u-cell-item>
			    <u-cell-item icon="order" title="我的申请" @click="toMyAudit"></u-cell-item>
			</u-cell-group>	
		</view>
		
		<view class="u-m-t-20">
			<u-cell-group>
				<u-cell-item icon="reload" title="退出登录" @click="logout"></u-cell-item>
			</u-cell-group>
		</view>
		
      <view>
		<u-popup v-model="showpop" mode="center" border-radius="14" width="500rpx" height="280rpx">
			<view class="content">
				确定要申请成为审核员吗
			</view>
			<view class="buttonbox">
			    <u-button :custom-style="customStyle" @click="showpop = false" >取消</u-button>
				<u-button :custom-style="customStyle" :ripple="true" ripple-bg-color="#cfa62b" @click="applyAudit" >确定</u-button>
			</view>
			
		</u-popup>
		
	  </view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				pic:'https://uviewui.com/common/logo.png',
				show:true,
				showpop:false,
				customStyle: {
					width: '160rpx', // 注意驼峰命名，并且值必须用引号包括，因为这是对象
					color: '#cf7f0f',
					
				}
			}
		},
        onLoad() {
         if(! this.$u.utils.isLogin()) return
		
		   
		},
		methods: {
			toBaseInfo(){
				this.$u.route({
					url:'pages/center/baseInfo'
				})
			},
			toMyAudit(){
				this.$u.route({
					url:'pages/center/myAudit?id='+this.vuex_user.id
				})
			},
			toMyMessage(){
				this.$u.route({
					url:'pages/center/myMessage?id='+this.vuex_user.id
				})
			},
			async logout(){
				//请求退出登录
			
				this.$u.toast('退出登录成功')
				
				setTimeout(() =>{
					//清除缓存的token和用户信息
					this.$u.vuex('vuex_token', null)
					this.$u.vuex('vuex_user', {})
					
					//跳转到首页
					this.$u.route({
						type:'reLaunch',
						url:'pages/index/index'
					})
				},1500)
			},
			async applyAudit(){
				const params ={
					memberId:this.vuex_user.id,
					memberNo:this.vuex_user.memberNo,
					memberName:this.vuex_user.memberName,
				}
			    const res =   await this.$u.api.submitApplyMember(params);
				if(res.data.status==1){
					this.$u.toast('申请成功')
					this.showpop =false
				}
			}
		}
	}
</script>

<style lang="scss">
page{
	background-color: #ededed;
}

.camera{
	width: 54px;
	height: 44px;
	
	&:active{
		background-color: #ededed;
	}
}
.user-box{
	background-color: #fff;
}
.avatar-body{
	height: 70px;
	width: 70px;
}
	.content {
		padding: 64rpx;
		text-align: center;
	}
	.buttonbox {
		display: flex;
	}
</style>


