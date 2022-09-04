<template>
	<view class="u-p-l-40 u-p-r-40">
	<u-form :model="form" ref="uForm">
		<u-form-item label="地区">
			<u-select v-model="show"  mode="mutil-column-auto" :list="list" @confirm="confirm"></u-select>
			<u-input v-model="form.address" @click="show = true" />
		</u-form-item>
		
		<u-form-item label="仓库">
			<u-select v-model="show2"  mode="single-column" :list="CangKulist" @confirm="confirm2"></u-select>
			<u-input v-model="form.stockAuditName" @click="show2 = true" />
		</u-form-item>
		
		<u-form-item label="一级分类" label-width="150">
			<block v-if="radio=='入库'">
			<u-select v-model="show3"  mode="single-column" :list="oneTypeList" @confirm="confirm3"></u-select>
			<u-input v-model="form.oneType" />
			<u-button @click="show3 = true" size="medium">选择</u-button>
			</block>
			<block v-else>
			<u-select v-model="show3"  mode="single-column" :list="oneTypeList" @confirm="confirm3"></u-select>
			<u-input v-model="form.oneType" @click="show3 = true" />
			</block>
		</u-form-item>
		
		<u-form-item label="二级分类" label-width="150">
			<block v-if="radio=='入库'">
			<u-select v-model="show4"  mode="single-column" :list="secondTypeList" @confirm="confirm4"></u-select>
			<u-input v-model="form.secondType" />
			<u-button @click="show4 = true" size="medium">选择</u-button>
			</block>
			<block v-else>
			<u-select v-model="show4"  mode="single-column" :list="secondTypeList" @confirm="confirm4"></u-select>
			<u-input v-model="form.secondType" @click="show4 = true" />
			</block>
		</u-form-item>
		
		<u-form-item label="名称">
			<block v-if="radio=='入库'">
			<u-select v-model="show5"  mode="single-column" :list="nameList" @confirm="confirm5"></u-select>
			<u-input v-model="form.name" />
			<u-button @click="show5 = true" size="medium">选择</u-button>
			</block>
			<block v-else>
			<u-select v-model="show5"  mode="single-column" :list="nameList" @confirm="confirm5"></u-select>
			<u-input v-model="form.name" @click="show5 = true" />
			</block>
		</u-form-item>
		
		<u-form-item label="品牌">
			<block v-if="radio=='入库'">
			<u-select v-model="show6"  mode="single-column" :list="brandList" @confirm="confirm6"></u-select>
			<u-input v-model="form.brand" />
			<u-button @click="show6 = true" size="medium">选择</u-button>
			</block>
			<block v-else>
			<u-select v-model="show6"  mode="single-column" :list="brandList" @confirm="confirm6"></u-select>
			<u-input v-model="form.brand" @click="show6 = true" />
			</block>
		</u-form-item>
		
		<u-form-item label="规格">
			<block v-if="radio=='入库'">
			<u-select v-model="show7"  mode="single-column" :list="specsList" @confirm="confirm7"></u-select>
			<u-input v-model="form.specs" />
			<u-button @click="show7 = true" size="medium">选择</u-button>
			</block>
			<block v-else>
			<u-select v-model="show7"  mode="single-column" :list="specsList" @confirm="confirm7"></u-select>
			<u-input v-model="form.specs" @click="show7 = true" />
			</block>
		</u-form-item>
		
		<u-form-item label="数量">
			<u-input v-model="form.count" />
		</u-form-item>

		<u-form-item label="类型">
			<u-radio-group v-model="radio">
				<u-radio @change="radioChange"  v-for="(item, index) in radioList" :key="index" :name="item.name" :disabled="item.disabled">
					{{ item.name }}
				</u-radio>
			</u-radio-group>
		</u-form-item>
		
		<u-button @click="submit">提交</u-button>
	</u-form>
	</view>
</template>

<script>
export default {
	data() {
		return {
			form: {
				name: '',
				stockAuditName: '',
				oneType: '',
				secondType:'',
				address:'',
				brand:'',
				specs:'',
				count:0,
				type:'',
				orderNo:'',
			},
			radioList: [
				{
					name: '出库',
					disabled: false
				},
				{
					name: '入库',
					disabled: false
				}
			],
			radio: '',
			switchVal: false,
			show: false,
			show2: false,
			show3: false,
			show4: false,
			show5: false,
			show6: false,
			show7: false,
			oneTypeList:[],
			secondTypeList:[],
			nameList:[],
			brandList:[],
			specsList:[],
			CangKulist:[],
			list:[],
			province:'',
			city:'',
			area:''
			
		};
	},
	onLoad() {
	 if(! this.$u.utils.isLogin()) return
	
	   
	},
	async onReady() {
		 const arealist =  await this.$u.api.areaList();
		 this.list =arealist.data.data;
		 console.log(this.list);
	},
	methods: {
		async queryWarehouseListByAddress(){
			const params ={
				province: this.province,
				city:this.city,
				area:this.area		 
			}
			const warehouselist =  await this.$u.api.queryWarehouseByAddress(params);
			this.CangKulist =warehouselist.data.data;
		},
		async stockListByOneType(){
			this.form.type='queryOneType'
			const params ={
				type:this.form.type,
				stockName: this.form.stockAuditName,
				name:this.form.name,
				oneType: this.form.oneType,
				secondType:this.form.secondType,
				brand:this.form.brand
			}
			const oneTypeList =  await this.$u.api.stockListByCondition(params);
			this.oneTypeList =oneTypeList.data.data;
		},
		async stockListBySecondType(){
			this.form.type='querySecondType'
			const params ={
				type:this.form.type,
				stockName: this.form.stockAuditName,
				name:this.form.name,
				oneType: this.form.oneType,
				secondType:this.form.secondType,
				brand:this.form.brand
			}
			const secondTypeList =  await this.$u.api.stockListByCondition(params);
			this.secondTypeList =secondTypeList.data.data;
		},
		async stockListByName(){
			this.form.type='queryName'
			const params ={
				type:this.form.type,
				stockName: this.form.stockAuditName,
				name:this.form.name,
				oneType: this.form.oneType,
				secondType:this.form.secondType,
				brand:this.form.brand
			}
			const nameList =  await this.$u.api.stockListByCondition(params);
		    console.log(nameList.data.data);
			this.nameList =nameList.data.data;
		},
		async stockListByName(){
			this.form.type='queryName'
			const params ={
				type:this.form.type,
				stockName: this.form.stockAuditName,
				name:this.form.name,
				oneType: this.form.oneType,
				secondType:this.form.secondType,
				brand:this.form.brand
			}
			const nameList =  await this.$u.api.stockListByCondition(params);
			this.nameList =nameList.data.data;
		},
		async stockListByBrand(){
			this.form.type='queryBrand'
			const params ={
				type:this.form.type,
				stockName: this.form.stockAuditName,
				name:this.form.name,
				oneType: this.form.oneType,
				secondType:this.form.secondType,
				brand:this.form.brand
			}
			const brandList =  await this.$u.api.stockListByCondition(params);
		    console.log(brandList.data.data);
			this.brandList =brandList.data.data;
		},
		async stockListBySpecs(){
			this.form.type='querySpecs'
			const params ={
				type:this.form.type,
				stockName: this.form.stockAuditName,
				name:this.form.name,
				oneType: this.form.oneType,
				secondType:this.form.secondType,
				brand:this.form.brand
			}
			const specsList =  await this.$u.api.stockListByCondition(params);
		    console.log(specsList.data.data);
			this.specsList =specsList.data.data;
		},
		confirm(e) {
			this.form.address=e[0].label+'-'+e[1].label+'-'+e[2].label;
			this.province =e[0].label;
			this.city =e[1].label;
			this.area =e[2].label;
			this.queryWarehouseListByAddress();
		},
		confirm2(e) {
			this.form.stockAuditName=e[0].label;
			this.stockListByOneType();
		},
		confirm3(e) {
			this.form.oneType=e[0].label;
			this.stockListBySecondType();
		},
	    confirm4(e) {
			this.form.secondType=e[0].label;
			this.stockListByName();
		},
	    confirm5(e) {
			this.form.name=e[0].label;
			this.stockListByBrand();
		},
	    confirm6(e) {
			this.form.brand=e[0].label;
			this.stockListBySpecs();
		},
	    confirm7(e) {
			this.form.specs=e[0].label;
		},											
		radioChange(e) {
			this.radio= e
			console.log(e)
			this.form.orderNo=e
		},
		async submit() {
			const params ={
				stockAuditName: this.form.stockAuditName,
				name:this.form.name,
				oneType: this.form.oneType,
				secondType:this.form.secondType,
				brand:this.form.brand,
				count:this.form.count,
				applyUser:this.vuex_user.id,
				orderNo:this.form.orderNo,
				specs:this.form.specs
			}
			const res =  await this.$u.api.submitOutOrInterStock(params);
			console.log(res.data.status);
			if(res.data.status==1){
				this.$u.toast('提交申请成功')
				setTimeout(() =>{		
					//跳转到首页
					this.$u.route({
						type:'reLaunch',
						url:'pages/index/index'
					})
				},1500)	
			}
			else{
				this.$u.toast('提交申请失败，'+res.data.msg)
			}
		},
	}

};
</script>
