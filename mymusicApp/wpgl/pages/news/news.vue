<template>
	<view>
		<view class="">
			新闻列表
		</view>
		<card color="red" size="20px">红莲</card>
		<card color="yellow">玄黄</card>
		<!-- #ifdef APP-PLUS -->
		<card @myClick="myClick" color="blue">苍蓝</card>
		<!-- #endif -->
	</view>
</template>

<script>
	export default {
		data() {
			return {
				 
			}
		},
		onLoad(){
			uni.showToast({
				title:'加载中...',
				icon:"loading",
			})
			// setTimeout(()=>{
			// 	uni.navigateTo({
			// 		url:'/pages/about/about'
			// 	})
			// },2000)
		},
		methods: {
			myClick(msg){
				alert('父组件的自定义事件：'+msg)
			}
		}
	}
</script>

<style>

</style>
