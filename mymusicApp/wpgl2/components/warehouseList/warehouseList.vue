<template>
	<view>
		<navigator class="cangku-item">
		  <u-image class="u-skeleton-fillet" width="100%" height="300rpx" :src="item.cover"></u-image>
		  <view class="title u-line-1">{{item.wareHouseName}}</view>
		  <view class="u-flex u-row-between">
		  <view class="distance u-skeleton-rect">{{item.houseManager}}</view>
		  </view>
		 <view class="address u-line-2">{{item.province}}{{item.city}}{{item.area}}{{item.address}}</view>
		</navigator>
	</view>
</template>

<script>
	export default {
		name:"warehouseList",
		props:{
			item:{
				type: Object,
				default(){
					return{
						
					}
				}
			}
		},
		data() {
			return {
				
			};
		}
	}
</script>

<style lang="scss" scoped>
 .cangku-item{
	   padding: 40rpx;
	   margin-top: 30rpx;
	   box-shadow: 5rpx 12rpx 20rpx 0rpx rgba(0,0,0,0.5);
	   .title{
		   margin-top: 20rpx;
		   font-weight: 500;
		   font-size: 32rpx;
	   }
	   .distance{
		   margin-top: 5rpx;
		   color: crimson;
	   }
	   .address{
		   margin-top: 5rpx;
		   font-size: 12rpx;
	   		color:seagreen;
	   }
   }
</style>