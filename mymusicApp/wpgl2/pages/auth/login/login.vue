<template>
	<view class="wrap">
		<view class="top"></view>
		<view class="content">
			<view class="title">欢迎登录物品管理系统</view>
			<input class="u-border-bottom"  v-model="tel" placeholder="请输入账号" />
			<input class="u-border-bottom" type="password" v-model="password" placeholder="请输入密码" />
			<view class="tips"></view>
			<button @tap="submit" :style="[inputStyle]" class="getCaptcha">登录</button>
			<view class="alternative">
				<view class="password">找回密码</view>
				<view class="issue" @click="toRegister">注册</view>
			</view>
		</view>
		<view class="buttom">
			<view class="loginType">
			<!-- 	<view class="wechat item">
					<view class="icon"><u-icon size="70" name="weixin-fill" color="rgb(83,194,64)"></u-icon></view>
					微信
				</view>
				<view class="QQ item">
					<view class="icon"><u-icon size="70" name="qq-fill" color="rgb(17,183,233)"></u-icon></view>
					QQ
				</view> -->
			</view>
			<!-- <view class="hint">
				登录代表同意
				<text class="link">美团点评用户协议、隐私政策，</text>
				并授权使用您的美团点评账号信息（如昵称、头像、收获地址）以便您统一管理
			</view> -->
		</view>
	</view>
</template>

<script>
export default {
	data() {
		return {
			tel: '',
			password:''
		}
	},
	computed: {
		inputStyle() {
			let style = {};
			if(this.tel && this.password) {
				style.color = "#fff";
				style.backgroundColor = this.$u.color['warning'];
			}
			return style;
		}
	},
	async onLoad(){
		//验证信息是否缓存成功
	// 	const memberInfo =await this.$u.api.memberInfo();
	
	// 	console.log(memberInfo);
	},
	methods: {
		async submit() {
	       if(!this.tel || !this.password) return 
		   //处理登录参数
		   const  paramsLogin ={
		      memberNo: this.tel,
			  password: this.password
		   }
		   //请求Api，执行登录
		   const loginRes =await this.$u.api.authLogin(paramsLogin);
		   console.log(loginRes);
		    console.log(loginRes.data.data.token);
		   //缓存token
		   this.$u.vuex('vuex_token',loginRes.data.data.token);
		   //请求用户信息
		   const memberInfo =await this.$u.api.memberInfo();
		   //缓存用户信息
		   this.$u.vuex('vuex_user',memberInfo.data.data);
		   //成功提醒
		   this.$u.toast('登录成功')
		   
		   //登录后跳转，跳转到来源页
		   const backUrl =uni.getStorageSync('back_url')||'pages/index/index'
		   console.log(backUrl)
		   setTimeout(() => {
			   this.$u.route({
			   	type:'reLaunch',
			   	url: backUrl
			   })
		   },1500)
		},
		toRegister(){
			this.$u.route({
				
				url:'pages/auth/register/register'
			})
		}
	}
};
</script>

<style lang="scss" scoped>
	.u-border-bottom{
		margin-bottom: 35rpx !important;
	}
.wrap {
	font-size: 28rpx;
	.content {
		width: 600rpx;
		margin: 80rpx auto 0;

		.title {
			text-align: left;
			font-size: 60rpx;
			font-weight: 500;
			margin-bottom: 100rpx;
		}
		input {
			text-align: left;
			margin-bottom: 10rpx;
			padding-bottom: 6rpx;
		}
		.tips {
			color: $u-type-info;
			margin-bottom: 60rpx;
			margin-top: 8rpx;
		}
		.getCaptcha {
			background-color: rgb(253, 243, 208);
			color: $u-tips-color;
			border: none;
			font-size: 30rpx;
			padding: 12rpx 0;
			
			&::after {
				border: none;
			}
		}
		.alternative {
			color: $u-tips-color;
			display: flex;
			justify-content: space-between;
			margin-top: 30rpx;
		}
	}
	.buttom {
		.loginType {
			display: flex;
			padding: 350rpx 150rpx 150rpx 150rpx;
			justify-content:space-between;
			
			.item {
				display: flex;
				flex-direction: column;
				align-items: center;
				color: $u-content-color;
				font-size: 28rpx;
			}
		}
		
		.hint {
			padding: 20rpx 40rpx;
			font-size: 20rpx;
			color: $u-tips-color;
			
			.link {
				color: $u-type-warning;
			}
		}
	}
}
</style>

