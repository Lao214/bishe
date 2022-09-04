<template>
	<view class="u-p-l-40 u-p-r-40">
		<u-search :show-action="false" style="padding-top: 30rpx; padding-bottom: 10rpx;" @change="getListData" placeholder="可根据名称搜索" v-model="keyword"></u-search>
		<u-table>
			<u-tr class="u-tr">
				<u-th class="u-th">一级分类</u-th>
				<u-th class="u-th">二级分类</u-th>
				<u-th class="u-th">品牌</u-th>
				<u-th class="u-th">名称</u-th>
				<u-th class="u-th">规格</u-th>
				<u-th class="u-th">数量</u-th>
			</u-tr>
			<u-tr class="u-tr" v-for="item in stockList.length!== 0 ? stockList : [{},{}]">
				<u-td class="u-td">{{item.oneType}}</u-td>
				<u-td class="u-td">{{item.secondType}}</u-td>
				<u-td class="u-td">{{item.brand}}</u-td>
				<u-td class="u-td">{{item.name}}</u-td>
				<u-td class="u-td">{{item.specs}}</u-td>
				<u-td class="u-td">{{item.count}}</u-td>
			</u-tr>
		</u-table>
		
	</view>
</template>

<script>
	export default {
		data() {
			return {
				wareHouseName:null,
				stockList:{},
				keyword: ''
			}
		},
		onLoad(option) {
			if(! this.$u.utils.isLogin()) return
			console.log(option)
			this.wareHouseName =option.wareHouseName
			this.getListData()
		},
		methods: {
			async getListData(){
				const params ={
					stockName: this.wareHouseName,
				    name :this.keyword
				}
				const res = await this.$u.api.stockListByConditions(params)
				if(res.data.status==1){
					this.stockList =res.data.data
					console.log(this.stockList)
				}
			}
		}
	}
</script>

<style>

</style>
