package com.example.mymusic.api;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import com.example.mymusic.common.Random2Utils;
import com.example.mymusic.common.StringUtils;
import com.example.mymusic.common.req.AppResponseCode;
import com.example.mymusic.common.req.WebPageResponse;
import com.example.mymusic.common.req.WebResponse;
import com.example.mymusic.common.req.WebResponseCode;
import com.example.mymusic.entity.Article;
import com.example.mymusic.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * (Article)表控制层
 *
 * @author makejava
 * @since 2022-02-08 17:55:24
 */
@RestController
@Controller
@RequestMapping("app/article")
public class AppArticleController {

    @Value("${server.port}")
    private String port;

    private  static  final  String ip="http://localhost";

    /**
     * 服务对象
     */
    @Autowired
    private ArticleService articleService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public static final String  FILES_KEY ="ARTICLE_ALL";


    /**
     * 通过主键查询单条数据
     *
     * @param articleNo 主键
     * @return 单条数据
     * @Author 劳威锟
     */
    @RequestMapping(value = "/queryArticleByArticleNo", method = RequestMethod.POST)
    @ResponseBody
    public Article queryArticleByArticleNo(String articleNo) {
        return this.articleService.queryArticleByArticleNo(articleNo);
    }


    @RequestMapping(value = "/queryArticleByArticleId", method = RequestMethod.GET)
    @ResponseBody
    public WebPageResponse queryArticleByArticleId(@RequestParam Long id) {
        WebPageResponse response =new WebPageResponse();
        Article article = articleService.queryArticleByArticleId(id);
        SimpleDateFormat sim2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        article.setCreateTimeStr(sim2.format(article.getCreateTime()));
        response.setData(article);
        response.setMsg("查询成功");
        response.setStatus(AppResponseCode.SUCCESS);
        return response;
    }


    /**
     * 添加(Admin)
     *
     * @return 单条数据
     * @Author 劳威锟
     */
    @GetMapping(value = "/queryArticleListRedis")
    public @ResponseBody WebPageResponse queryArticleListRedis(@RequestParam(defaultValue = "0") Integer offset, @RequestParam(defaultValue = "10") Integer limit) {
        WebPageResponse response =new WebPageResponse();
        //1.从Redis中取出缓存数据
        String jsonStr=  stringRedisTemplate.opsForValue().get(FILES_KEY);
        if(StringUtils.isEmpty(jsonStr)){//2.取出来的json是空的
            Long total= articleService.queryAllCount();
            int start =(offset)*limit;
            if(offset!=0){
                start =(offset-1)*limit;
            }
            List<Article> pages=  articleService.queryAllByLimit(start,limit);
            /**转换时间戳**/
            SimpleDateFormat sim2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if(total>0){
                for (Article article:pages) {
                    article.setCreateTimeStr(sim2.format(article.getCreateTime()));
                }
            }
//          response.setData(pages);
//          response.setOffset(start);
//          response.setLimit(limit);
//          response.setTotal(total);
            //3.从数据库中取出数据，再去缓存到redis
            stringRedisTemplate.opsForValue().set(FILES_KEY,JSONUtil.toJsonStr(pages));
            response.setData(pages);
        }else {
            //4  如果该Key有数据，直接通过该Key从redis缓存中获取数据
            List<Object> pages =  JSONUtil.toBean(jsonStr, new TypeReference<List<Object>>() {
            },true);
            response.setData(pages);
        }
        response.setStatus(WebResponseCode.SUCCESS);
        response.setMsg("查询成功");
        return response ;
    }

    /**
     * 添加(Admin)
     *
     * @return 单条数据
     * @Author 劳威锟
     */
    @GetMapping(value = "/queryArticleList")
    public @ResponseBody WebPageResponse queryArticleList(@RequestParam(defaultValue = "0") Integer offset, @RequestParam(defaultValue = "10") Integer limit) {
        WebPageResponse response =new WebPageResponse();
        Long total= articleService.queryAllCount();
        int start =(offset)*limit;
        if(offset!=0){
            start =(offset-1)*limit;
        }
        List<Article> pages=  articleService.queryAllByLimit(start,limit);
        /**转换时间戳**/
        SimpleDateFormat sim2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(total>0){
            for (Article article:pages) {
                article.setCreateTimeStr(sim2.format(article.getCreateTime()));
            }
        }
        response.setData(pages);
        response.setOffset(start);
        response.setLimit(limit);
        response.setTotal(total);
        response.setStatus(WebResponseCode.SUCCESS);
        response.setMsg("查询成功");
        return response ;
    }



    /**
     * 修改Article
     *
     * @param article 实体类
     * @return 单条数据
     * @Author 劳威锟
     */
    @RequestMapping(value = "/updateArticle", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse updateArticle(@RequestBody Article article) {
        WebResponse webResponse =new WebResponse();
        byte[] imagedata = DatatypeConverter.parseBase64Binary(article.getImageUrl().substring(article.getImageUrl().indexOf(",") + 1));
        String originalFilename =  article.getFilename();         //获取源文件名称
        String flag   = IdUtil.fastSimpleUUID();               // 定义文件唯一标识（前缀）
        String rootFilePath=  System.getProperty("user.dir")+"/src/main/resources/files/"+flag+"_"+ originalFilename;  //files文件夹绝对路径+文件名  =文件路径
        FileUtil.writeBytes(imagedata,rootFilePath);
        article.setCover(ip+":"+port+"/api/file/"+flag);
        Article upA = articleService.updateArticle(article);
        webResponse.setStatus(WebResponseCode.SUCCESS);
        webResponse.setData(upA);
        webResponse.setMsg("添加成功");
        return webResponse;
    }


    /**
     * 添加Article
     *
     * @param article 实体类
     * @return 单条数据
     * @Author 劳威锟
     */
    @RequestMapping(value = "/addArticle", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse insertArticle(@RequestBody Article article)throws IOException {
        article.setArticleNo(Random2Utils.buildSn("ATL"));
        WebResponse webResponse =new WebResponse();
        byte[] imagedata = DatatypeConverter.parseBase64Binary(article.getImageUrl().substring(article.getImageUrl().indexOf(",") + 1));
        String originalFilename =  article.getFilename();         //获取源文件名称
        String flag   = IdUtil.fastSimpleUUID();               // 定义文件唯一标识（前缀）
        String rootFilePath=  System.getProperty("user.dir")+"/src/main/resources/files/"+flag+"_"+ originalFilename;  //files文件夹绝对路径+文件名  =文件路径
        FileUtil.writeBytes(imagedata,rootFilePath);
        article.setCover(ip+":"+port+"/api/file/"+flag);
        Article A = articleService.insert(article);
        webResponse.setStatus(WebResponseCode.SUCCESS);
        webResponse.setData(A);
        webResponse.setMsg("添加成功");
        return webResponse;
    }

}
