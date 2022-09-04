<template>
	<view>
		<u-image width="100%" height="300rpx" :src="article.cover"></u-image>
		 <view style="text-align: center;padding: 20rpx;">
			<h2>{{article.title}}</h2>
		</view>
		 <view style="padding:20rpx;">
			 <u-parse :html="article.content"></u-parse>
		 </view>
		 <view style="text-align: center;padding: 20rpx; display: flex;">
		 	<p style="margin-right:180rpx;">{{article.author}}</p>
			<p style="margin-left:100rpx;">{{article.createTimeStr}}</p>
		 </view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				src: 'https://cdn.uviewui.com/uview/swiper/1.jpg',
				articleId:null,
				article:{},
				articleInfo:''
			}
		},
		onLoad(option) {
			console.log(option)
			this.articleId =option.id
			this.getArticle()
		},
		methods: {
			async getArticle(){
				const params ={
					id: this.articleId 
				}
				const res = await this.$u.api.indexArticleDetails(params)
				if(res.data.status==1){
					this.article =res.data.data
					console.log(this.article)
				}
			}
		}
	}
</script>

<style>

</style>
