// /common/http.api.js

// 如果没有通过拦截器配置域名的话，可以在这里写上完整的URL(加上域名部分)

// let indexUrl = '/app/article/queryArticleList';  API数量多的话就不写在这了

// 此处第二个参数vm，就是我们在页面使用的this，你可以通过vm获取vuex等操作，更多内容详见uView对拦截器的介绍部分：
// https://uviewui.com/js/http.html#%E4%BD%95%E8%B0%93%E8%AF%B7%E6%B1%82%E6%8B%A6%E6%88%AA%EF%BC%9F
const install = (Vue, vm) => {
	 vm.$u.api = {}
	
	// 此处没有使用传入的params参数
	
	//首页文章资讯列表API
	vm.$u.api.indexArticleList = (params = {}) => vm.$u.get('/app/article/queryArticleList',params);
	vm.$u.api.indexArticleListRedis = (params = {}) => vm.$u.get('/app/article/queryArticleListRedis',params);
	vm.$u.api.indexArticleDetails = (params = {}) => vm.$u.get('/app/article/queryArticleByArticleId',params);
	//仓库相关API
	vm.$u.api.indexWarehouseList = (params = {}) => vm.$u.get('/app/warehouse/queryWarehouseList',params);
	vm.$u.api.stockListByConditions = params => vm.$u.post('/app/stock/queryStockListByConditions',params); //请求常规库存数据
	
	//认证相关的
	vm.$u.api.authLogin    =params => vm.$u.post('/app/member/login',params);   //登录接口
	vm.$u.api.authRegister =params => vm.$u.post('/app/member/register',params);//注册接口
	vm.$u.api.authLogout =() => vm.$u.post('/app/member/logout');//退出登录
	vm.$u.api.authOssToken =() => vm.$u.get('/app/auth/oss/token');//获取osstoken
	vm.$u.api.memberNameHaved =params => vm.$u.get('/app/member/memberName',params);
	vm.$u.api.memberNoHaved =params => vm.$u.get('/app/member/memberNo',params);
	
	//请求用户信息
	vm.$u.api.memberInfo =() => vm.$u.get('/app/member/member');
	vm.$u.api.updateMemberInfo = params  => vm.$u.put('/app/member/updateMember',params );
	vm.$u.api.updateMemberHead = params  => vm.$u.patch('/app/member/updateMemberHead',params );
	
	//库存相关
	vm.$u.api.queryWarehouseByAddress = (params = {}) => vm.$u.get('/app/warehouse/queryWarehouseByAddress',params); //根据地址获取仓库
	vm.$u.api.stockListByCondition = params => vm.$u.post('/app/stock/queryStockListByCondition',params); //请求库存数据
	vm.$u.api.addStockAudit = params => vm.$u.post('/app/stockAudit/addStockAudit',params); //提交出入库申请
	
	//地区数据
	vm.$u.api.areaList = () => vm.$u.get('/app/stock/queryAreaList');    //获取地区数据
	
	//提交出入库申请
	vm.$u.api.submitOutOrInterStock = params => vm.$u.post('/app/stockAudit/addStockAudit',params);    //获取地区数据
	
	//获取申请列表
	vm.$u.api.queryAuditList = params => vm.$u.get('/app/stockAudit/queryAuditList',params);    //获取地区数据
	vm.$u.api.queryMyAuditList = params => vm.$u.get('/app/stockAudit/queryMyAuditList',params);    //获取地区数据
	
	vm.$u.api.queryAuditDetails = params => vm.$u.get('/app/stockAudit/queryAuditDetails',params);
	
	vm.$u.api.stockAuditPass = stockAuditNo => vm.$u.post('/app/stockAudit/stockAuditPass/'+stockAuditNo);
	
	vm.$u.api.stockAuditReject = stockAuditNo => vm.$u.post('/app/stockAudit/stockAuditReject/'+stockAuditNo);
	//提交审核员申请
	vm.$u.api.submitApplyMember = params => vm.$u.post('/app/memberApply/addMemberApply',params);    //获取地区数据
	
	//消息相关
	vm.$u.api.queryMessageList = params => vm.$u.get('/app/message/queryMessageList',params); 
	vm.$u.api.queryMessageById = params => vm.$u.get('/app/message/queryMessageById',params);	
	// 将各个定义的接口名称，统一放进对象挂载到vm.$u.api(因为vm就是this，也即this.$u.api)下
	// vm.$u.api = {
	// 	indexArticleList,
	// 	authLogin
	// 	};
}

export default {
	install
}