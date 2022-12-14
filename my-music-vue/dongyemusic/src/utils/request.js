import axios from 'axios' 
import router from '@/router';
// import ElementUI from 'element-ui';

const request = axios.create({
	baseURL: '/api',  // 注意！！ 这里是全局统一加上了 '/api' 前缀，也就是说所有接口都会加上'/api'前缀在，页面里面写接口的时候就不要加 '/api'了，否则会出现2个'/api'，类似 '/api/api/user'这样的报错，切记！！！
    timeout: 10000
})

// request 拦截器
// 可以自请求发送前对请求做一些处理
// 比如统一加token，对请求参数统一加密
request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';    //请求头
  
    // let admin =localStorage.getItem("admin") ? JSON.parse(localStorage.getItem("admin")) : null 
    // if(admin){
       // config.headers['token'] = admin.token;  // 设置请求头
    // }

 // 取出sessionStorage里面缓存的用户信息
  let adminJson= sessionStorage.getItem("admin")
  if(!adminJson){
    // adminJson= "{}"             //设为空对象的方法
   router.push("/login");   //强跳login
  }

    return config
}, error => {
    return Promise.reject(error)
});
 
// response 拦截器
// 可以在接口响应后统一处理结果
request.interceptors.response.use(
    response => {
        let res = response.data;   //返回结果直接取response的data 存放到res中 
        // 如果是返回的文件
        if (response.config.responseType === 'blob') {
            return res
        }
        // 兼容服务端返回的字符串数据
        if (typeof res === 'string') {   //如果是string返回res时转换一下
            res = res ? JSON.parse(res) : res
        }

        // if ( res.code === '401') {   //当权限验证不通过时，给出提示
        //     ElementUI.Message({
        //         message : res.msg,
        //         type:'warning'
        //     })
        // }

        return res;
    },
    error => {
        console.log('err' + error) // for debug
        return Promise.reject(error)
    }
)


export default request
