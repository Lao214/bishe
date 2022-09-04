<template>
	<view class="u-skeleton">
		<u-swiper :list="article" name="cover" @click="bannerGo"  height="320"></u-swiper>
		 <view class="u-text-center u-m-t-30">
		 <u-tabs :list="tablist"  bar-width="100" item-width="120" :current="currentTabs" @change="changeTabs"></u-tabs>
		 </view>
		 	<u-row gutter="16">
		 			<u-col span="6" v-for="item in warehouseList.length!== 0 ? warehouseList : [{},{}]">
		 					<navigator class="cangku-item" :url="'/pages/index/stockList?wareHouseName=' + item.wareHouseName ">
		 						<u-image class="u-skeleton-fillet" width="100%" height="300rpx" :src="item.cover"></u-image>
		 						<view class="title u-line-1">{{item.wareHouseName}}</view>
		 						<view class="u-flex u-row-between">
		 							<view class="distance u-skeleton-rect">{{item.houseManager}}</view>
		 						</view>
		 						<view class="address u-line-2 u-skeleton-rect">{{item.province}}{{item.city}}{{item.area}}{{item.address}}</view>
		 					</navigator>
		 			</u-col>
		 	</u-row>
			
	     <!--骨架屏-->
		<u-skeleton :loading="loading" :animation="true" bgColor="#FFF"></u-skeleton>

	</view>
</template>

<script>
	export default {
		data() {
			return {
			   tablist:[
				    {name:'默认'},
				    {name:'时间'}
			   ],
			   currentTabs:0,
			   warehouseList:[],
			   article:[],
			   goods:[],
			   start:0,
			   loading:false
			}
		},
		async onLoad() {
			
		this.getWarehouse()
		this.getArticle()
		},
		methods: {
          changeTabs(index){
			 this.currentTabs =index;
			 //置空数组
			 this.warehouseList=[];
			 this.start=0;
	         this.getWarehouse();
		  },
		  async getArticle(){
			const article = await this.$u.api.indexArticleListRedis()
			this.article=article.data.data
		  },
		  async getWarehouse(){
			  this.loading= true;  //显示骨架屏
			  const params ={
				  start: this.start,
				  sort:this.currentTabs	   
			  }
			  const res = await this.$u.api.indexWarehouseList(params)
			  this.loading=false;  //隐藏骨架屏
			  console.log(res.data.data)
			  if(res.data.data==""){
				  uni.showToast({
				  	title:'已经到底了',
					icon:"none"
				  })	
			  }
			  this.warehouseList=[...this.warehouseList,...res.data.data]
			  console.log(this.warehouseList)
		  },
		  async bannerGo(index){
			  this.$u.route({
			  	type:'navigateTo',
			  	url:'pages/index/article?id='+this.article[index].id
			  })
		  }
		},
		//触底onReachBottom
		onReachBottom(){
			//重新请求数据，带上分页参数
			this.start=this.start+4
			this.getWarehouse()
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
