<template>
	<view>
		<life v-if="show"></life>
	</view>
</template>

<script>
	/**
	 *  应用的生命周期 写在App.vue的export default里
	 * 
	 *  页面的生命周期 写在页面的export default里
	 * 
	 *  组件的生命周期 写在组件的export default里
	 */

	export default {
		data(){
			return {
		      show: true
			}
		},
		//页面加载的时候触发
		onLoad() {
		   console.log('App-onLoad');	
		},
		//页面初次渲染完成触发
		onReady(){
		   console.log('App-onReady');	
		},
		//应用初始化完成时执行，全局只执行一次
		// 通常用来 登录的处理 全局变量等。
       onLaunch: function(){
		   console.log('App-Lanunch');
	   },
	   //应用显示的时候执行，或者从后台进入前台
	   onShow: function(){
	   	   console.log('App-onShow');
	   },
	   //应用隐藏的时候执行，或者从前台进入后台
	   onHide: function(){
	   	   console.log('App-onHide');
	   },
	   //页面卸载的时候触发
	   onUnLoad() {
	      console.log('App-onUnLoad');
	   },
	   //一般顺序为 Launch→onLoad→onShow→onReady   app的show比页面的show快，app的hide也比页面的hide快。跳转会执行卸载onUnLoad
	methods: {
	}
}
</script>

<style>

</style>
