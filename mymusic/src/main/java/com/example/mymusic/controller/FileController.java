package com.example.mymusic.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.server.HttpServerResponse;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.example.mymusic.common.Result;
import com.example.mymusic.common.req.WebResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

/**
 * 类说明：FileController
 *
 * @Author 劳威锟
 * @date 2022/1/2010:41
 **/
@RestController
@RequestMapping("api/file")
public class FileController {

    @Value("${server.port}")
    private String port;

    private  static  final  String ip="http://localhost";

    /**
     * @Author 劳威锟
     * @Description //方法说明： 上传文件接口
     * @Date 11:40 2022/1/20
     * @Param 
     * @return 
     **/
    @CrossOrigin
    @PostMapping("/upload")
    public WebResponse upload(MultipartFile file) throws IOException {
        WebResponse webResponse =new WebResponse();
       String originalFilename =  file.getOriginalFilename();  //获取源文件名称
       String flag   = IdUtil.fastSimpleUUID();                // 定义文件唯一标识（前缀）
       String rootFilePath=  System.getProperty("user.dir")+"/src/main/resources/files/"+flag+"_"+ originalFilename;  //files文件夹绝对路径+文件名  =文件路径
        //输入流和输出流 io
        //file.getBytes 获取字节流 写入 rootFilePath
        FileUtil.writeBytes(file.getBytes(),rootFilePath);  //文件写入到上传的路径
        webResponse.setData( ip + ":" + port + "/api/file/"+flag );
        webResponse.setMsg(rootFilePath);
        return webResponse;   //返回结果url
    }

    /**
     * @Author 劳威锟
     * @Description //方法说明： 富文本文件上传文件接口
     * @Date 11:40 2022/1/20
     * @Param
     * @return
     **/
    @CrossOrigin
    @PostMapping("/editorUpload")
    public JSON editorUpload(MultipartFile file) throws IOException {
        WebResponse webResponse =new WebResponse();
        String originalFilename =  file.getOriginalFilename();  //获取源文件名称
        String flag   = IdUtil.fastSimpleUUID();                // 定义文件唯一标识（前缀）
        String rootFilePath=  System.getProperty("user.dir")+"/src/main/resources/files/"+flag+"_"+ originalFilename;  //files文件夹绝对路径+文件名  =文件路径
        //输入流和输出流 io
        //file.getBytes 获取字节流 写入 rootFilePath
        FileUtil.writeBytes(file.getBytes(),rootFilePath);  //文件写入到上传的路径
        String url = ip + ":" + port + "/api/file/"+flag;
        JSONObject json =new JSONObject();
        JSONArray  arr =new JSONArray();
        JSONObject data =new JSONObject();
        data.set("url",url);
        arr.add(data);
        json.set("errno",0);
        json.set("data",arr);
//      webResponse.setData( ip + ":" + port + "/api/file/"+flag );
//      webResponse.setMsg(rootFilePath);
        return json;   //返回结果url
    }

    /**
     * @Author 劳威锟
     * @Description //方法说明： 下载文件接口
     * @Date 11:40 2022/1/20
     * @Param 
     * @return 
     **/
    @CrossOrigin
    @GetMapping("/{flag}")
    public void getFiles(HttpServletResponse response, @PathVariable String flag) {
        OutputStream os;    //新建一个输出流对象
        String basePath =System.getProperty("user.dir")+"/src/main/resources/files/";   //定义根路径
        List<String> flieNames =FileUtil.listFileNames(basePath);                       //获取根路径下所有文件名
        String fileName = flieNames.stream().filter(name -> name.contains(flag)).findAny().orElse("");  //把flag作为唯一标识 比对找到相应的文件
        try {
          if(StrUtil.isNotEmpty(fileName)){
               response.addHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(fileName,"UTF-8"));
               response.setContentType("application/octet-stream");
               byte[] bytes =FileUtil.readBytes(basePath+fileName);  //通过文件路径 读取文件字节流
               os= response.getOutputStream();                              //通过输出流返回文件
               os.write(bytes);
               os.flush();
               os.close();
          }
        }catch (Exception e){
            System.out.println("文件下载失败");
        }
    }
}
