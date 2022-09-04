<template>
	<view>
		
	</view>
</template>

<script>
	export default {
		name:"life",
		data() {
			return {
				
			};
		},
		//在实例初始化之后执行，在数据观测和event/watcher事件配置之前。
		beforeCreate() {
			console.log('beforeCreate')
		},
		//在实例创建完之后执行，然而，挂载阶段还没开始。
		created(){
			console.log('created')
		},
		//实例挂载完之后执行
		mounted(){
			console.log('created')
		},
		//Vue实例销毁之后执行
		destroyed(){
			console.log('destroyed')
		}
		
	}
</script>

<style>

</style>