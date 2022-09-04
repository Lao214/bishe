<template>
	<view>
		<view @click ="show">点击事件</view>
		<view @click ="flag =!flag">点击改变flag</view>
		
		<view @click="c1">
			我是父级
			<view @click.stop="c2">我是子级</view>
		</view>
		<scroll-view class="scroll" :scroll-y="true" @scroll>
			 <view v-for="i in 100">11</view>
		</scroll-view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				flag:false
			}
		},
		methods: {
			show(){
				alert("点击事件")
			},
			scroll(event){
				console.log(event);
				
			},
		}
	}
</script>

<style>
   .scroll{
	   height: 400rpx;
   }
</style>
