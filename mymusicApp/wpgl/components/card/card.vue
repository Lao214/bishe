<template>
	<view  :style="{background:color,fontSize:size}" @click="handleClick" >
		<slot></slot>
	</view>
</template>

<script>
	export default {
		name:"card",
		props:{
			color:{
				type:String,
				default:'white'
			},
			size:{
				type:String,
				default:'15px'
			}
		},
		data() {
			return {
				
			};
		},
		methods:{
			handleClick(){
				this.$emit('myClick','这个数据来自子组件');
			}, 
		}
	}
</script>

<style>

</style>