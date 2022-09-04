
//自定义工具
const install = (Vue, vm) => {
	
	/**
	 * 检验是否已登录
	 */
	const isLogin =() => {
		const token =vm.vuex_token
		if(!token){
			//获取来源页面
			const currentPage =getCurrentPages().pop()
			//获取页面路径和请求参数
			const {options, route} =currentPage
			//获取所有参数的key
			const optionKeys =Object.keys(options)
			let params ='';
			if(optionKeys.length!==0){
				params =optionKeys.reduce((pre,current) =>{
					return pre + current + '=' + options[current] + '&'
					//'${pre}${current}=${options[current]}&'  另一种写法
				},'?').slice(0,-1)
			}
			
			//缓存当前页 用于登录或注册后跳转
			uni.setStorageSync('back_url',route + params )
		
			vm.$u.toast('请登录')
			setTimeout(() => {
			vm.$u.route({
			type:'redirect',
			url:'pages/auth/login/login'
			})
		  },1500)
		  return false;
		}
		return true;
	}
	
	/**
	 * 更新用户信息
	 */
	const updateMember = async () => {
		//请求用户信息
		const memberInfo =await vm.$u.api.memberInfo();
		//缓存用户信息 更新vuex_user
		vm.$u.vuex('vuex_user',memberInfo.data.data);
	}
	 
	vm.$u.utils={
		//检验是否已登录
		isLogin,
		updateMember
	}
	
}

export default{
	install
}