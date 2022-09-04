<template>
	<view>
			<view class="u-p-l-30 u-p-r-30">
				<u-form :model="form" :error-type="errorType" ref="uForm">
					<u-form-item label="姓名" prop="memberName" required>
						<u-input  v-model="form.memberName" />
					</u-form-item>
					<u-form-item label="手机" prop="mobileNo" required>
						<u-input  v-model="form.mobileNo" />
					</u-form-item>
				</u-form>

				<u-button @click="submit">提交</u-button>
			</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				errorType:['message'],
				form:{
					memberName:'',
					mobileNo:'',
				},
				rules:{
				memberName: [
					{ 
					 required: true, 
					 message: '请输入昵称', 
					 // 可以单个或者同时写两个触发验证方式 
					 trigger: ['change','blur'],
					}
				],
				mobileNo: [
						{
							required: true, 
							message: '请输入手机号',
							trigger: ['change','blur'],
						},
						{
							// 自定义验证函数，见上说明
							validator: (rule, value, callback) => {
								// 上面有说，返回true表示校验通过，返回false表示不通过
								// this.$u.test.mobile()就是返回true或者false的
								return this.$u.test.mobile(value);
							},
							message: '手机号码不正确',
							// 触发器可以同时用blur和change
							trigger: ['change','blur'],
						}
					]
				}
			}
		},
		// 必须要在onReady生命周期，因为onLoad生命周期组件可能尚未创建完毕
		onReady() {
			this.$refs.uForm.setRules(this.rules);
			this.form =this.vuex_user
		},
		methods: {
		submit() {
				this.$refs.uForm.validate(async (valid) => {
					if (valid) {
						//更新用户信息
					    await	this.$u.api.updateMemberInfo(this.form)
					    //刷新用户信息 更新 vuex_user
						this.$u.utils.updateMember()
						this.$u.toast('更新成功')
						console.log('验证通过');
					} 
				});
			}		
		}

	}
</script>

<style>

</style>
