<template>
	<view class="wrap">
		<view class="top"></view>
		<view class="content">
			<view class="title">注册</view>
			<input class="u-border-bottom"  v-model="tel" placeholder="请输入账号" />
			<input class="u-border-bottom"  v-model="name" placeholder="请输入昵称" />
			<input class="u-border-bottom" type="password" v-model="password" placeholder="请输入密码" />
			<input class="u-border-bottom" type="password" v-model="passwordConfirmation" placeholder="请确认密码" />
			<view class="tips"></view>
			<button @tap="submit" :style="[inputStyle]" class="getCaptcha">注册</button>
<!-- 			<view class="alternative">
				<view class="password">找回密码</view>
				<view class="issue">注册</view>
			</view> -->
		</view>
		<view class="buttom">
			<view class="loginType">
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				tel:'',
				name:'',
				password:'',
				passwordConfirmation:'',
			}
		},
		computed: {
			inputStyle() {
				let style = {};
				if(this.tel && this.name && this.password && this.passwordConfirmation) {
					style.color = "#fff";
					style.backgroundColor = this.$u.color['warning'];
				}
				return style;
			}
		},
		methods: {
			async submit(){
				//表单验证
				 if(this.passwordConfirmation!= this.password){
					  this.$u.toast('密码输入不一致')
					   return 
				 }
				
				//提交参数
				const params ={
					memberName : this.name,
					memberNo: this.tel,
					password: this.password,
					passwordConfirmation: this.passwordConfirmation
				}
				//请求API 执行注册
				const res = await this.$u.api.authRegister(params)
				//重定向到登录（redirect）
				if(res.data.status==1){
					this.$u.toast('注册成功')
					setTimeout(() =>{		
						//跳转到首页
						this.$u.route({
							type:'reLaunch',
							url:'pages/auth/login/login'
						})
					},1500)	
				}
				else if(res.data.status==3){
					this.$u.toast(res.data.msg)
			        return
				}
				else{
					this.$u.toast('注册失败，'+res.data.msg)
				}
			},
			
		}
	}
</script>

<style lang="scss" scoped>
	.u-border-bottom{
		margin-bottom: 55rpx !important;
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
