package com.example.mymusic.op;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.json.JSONUtil;
import com.example.mymusic.common.FdServiceException;
import com.example.mymusic.common.StringUtils;
import com.example.mymusic.common.req.WebPageResponse;
import com.example.mymusic.common.req.WebResponseCode;
import com.example.mymusic.entity.EcahrtsClass;
import com.example.mymusic.entity.EcahrtsPie;
import com.example.mymusic.entity.Stock;
import com.example.mymusic.entity.param.StockParam;
import com.example.mymusic.service.IStockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Controller
@RequestMapping("/api/ecahrts")
public class EcahrtsAction {

    private static Logger logger = LoggerFactory.getLogger(EcahrtsAction.class);

    @Resource
    private IStockService stockService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public static final String  FILES_KEY ="FILES_FRONT_ALL";



    /**
     *
     * @return  统计库存数据
     * @Author 劳威锟
     */
    @GetMapping(value = "/countStockRedis")
    public WebPageResponse queryStockListRedis() {
        WebPageResponse response =new WebPageResponse();
        try {
            //1.从Redis中取出缓存数据
            String jsonStr=  stringRedisTemplate.opsForValue().get(FILES_KEY);
            if(StringUtils.isEmpty(jsonStr)){ //2.取出来的json是空的
                Map<String,Object> map =new HashMap<>();
                map.put("x", CollUtil.newArrayList("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"));
                map.put("y",CollUtil.newArrayList(150, 230, 224, 218, 135, 147, 260));
                //3.从数据库中取出数据，再去缓存到redis
                stringRedisTemplate.opsForValue().set(FILES_KEY,JSONUtil.toJsonStr(map));
                response.setData(map);
            }else {
                //  如果该Key有数据，直接通过该Key从redis缓存中获取数据
                List<Object> map =  JSONUtil.toBean(jsonStr, new TypeReference<List<Object>>() {
                },true);
                response.setData(map);
            }
            response.setStatus(WebResponseCode.SUCCESS);
            response.setMsg("查询成功");
        } catch (FdServiceException e) {
            response.setStatus(WebResponseCode.FAIL);
            response.setMsg(e.toString());
            return response;
        }
        return response;
    }



    @GetMapping(value = "/countStock")
    public WebPageResponse queryStockList() {
        WebPageResponse response =new WebPageResponse();
        try {
            List<EcahrtsClass> ecahrtsClassList = stockService.queryClassParent();
            List<String> daysArray = new ArrayList<>();
            List<String> stockArray = new ArrayList<>();
            for (int i = 0; i <ecahrtsClassList.size() ; i++) {
                if(StringUtils.isEmpty(ecahrtsClassList.get(i).getClassParent())){
                    ecahrtsClassList.get(i).setClassParent("0");
                }
                daysArray.add(ecahrtsClassList.get(i).getDays());
                stockArray.add(ecahrtsClassList.get(i).getClassParent());
            }
            Map<String,Object> map =new HashMap<>();
            map.put("x", CollUtil.newArrayList(daysArray));
            map.put("y",CollUtil.newArrayList(stockArray));
            response.setData(map);
            response.setStatus(WebResponseCode.SUCCESS);
            response.setMsg("查询成功");
        } catch (FdServiceException e) {
            response.setStatus(WebResponseCode.FAIL);
            response.setMsg(e.toString());
            return response;
        }
        return response;
    }



    @GetMapping(value = "/countStockClass")
    public WebPageResponse countStockClass() {
        WebPageResponse response =new WebPageResponse();
        Map<String,Object> map   =new HashMap<>();
        try {
            List<EcahrtsPie> ecahrtsClassList = stockService.queryClassType();
            response.setData(ecahrtsClassList);
            response.setStatus(WebResponseCode.SUCCESS);
            response.setMsg("查询成功");
        } catch (FdServiceException e) {
            response.setStatus(WebResponseCode.FAIL);
            response.setMsg(e.toString());
            return response;
        }
        return response;
    }
}
