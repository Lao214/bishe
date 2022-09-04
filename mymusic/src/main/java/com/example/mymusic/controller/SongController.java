package com.example.mymusic.controller;
;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.example.mymusic.common.Random2Utils;
import com.example.mymusic.common.enums.Status;
import com.example.mymusic.common.req.WebPageResponse;
import com.example.mymusic.common.req.WebResponse;
import com.example.mymusic.common.req.WebResponseCode;
import com.example.mymusic.entity.Admin;
import com.example.mymusic.entity.Song;
import com.example.mymusic.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 歌曲(Song)表控制层
 *
 * @author makejava
 * @since 2022-01-19 21:22:50
 */
@RestController
@Controller
@RequestMapping("api/song")
public class SongController {
    @Value("${server.port}")
    private String port;

    private  static  final  String ip="http://localhost";
    /**
     * 服务对象
     */
    @Autowired
    private SongService songService;

    /**
     * 通过主键查询单条数据
     *
     * @param songNo 主键
     * @return 单条数据
     * @Author 劳威锟
     */
    @RequestMapping(value = "/querySongBySongNo", method = RequestMethod.POST)
    @ResponseBody
    public Song querySongbySongNo(String songNo) {
        return this.songService.querySongBySongNo(songNo);
    }


    /**
     * 修改Song
     *

     * @return 单条数据
     * @Author 劳威锟
     */
    @RequestMapping(value = "/updateSong", method = RequestMethod.POST)
    @ResponseBody
    public WebResponse updateSong(@RequestBody Song song) {
        WebResponse webResponse =new WebResponse();
        byte[] imagedata = DatatypeConverter.parseBase64Binary(song.getImageUrl().substring(song.getImageUrl().indexOf(",") + 1));
        String originalFilename =  song.getFilename();         //获取源文件名称
        String flag   = IdUtil.fastSimpleUUID();               // 定义文件唯一标识（前缀）
        String rootFilePath=  System.getProperty("user.dir")+"/src/main/resources/files/"+flag+"_"+ originalFilename;  //files文件夹绝对路径+文件名  =文件路径
        FileUtil.writeBytes(imagedata,rootFilePath);  //文件写入到上传的路径
        song.setImg(ip+":"+port+"/api/file/"+flag);
        songService.updateSong(song);
        webResponse.setStatus(WebResponseCode.SUCCESS);
        webResponse.setData(song);
        webResponse.setMsg("修改成功");
        return webResponse;
    }


    /**
     * 添加(Admin)
     *
     * @param song 主键
     * @return 单条数据
     * @Author 劳威锟
     */
    @PostMapping(value = "/addSong")
    public WebResponse addSong(@RequestBody Song song) throws IOException {
        WebResponse webResponse =new WebResponse();
        byte[] imagedata = DatatypeConverter.parseBase64Binary(song.getImageUrl().substring(song.getImageUrl().indexOf(",") + 1));
        String originalFilename =  song.getFilename();         //获取源文件名称
        String flag   = IdUtil.fastSimpleUUID();               // 定义文件唯一标识（前缀）
        String rootFilePath=  System.getProperty("user.dir")+"/src/main/resources/files/"+flag+"_"+ originalFilename;  //files文件夹绝对路径+文件名  =文件路径
        FileUtil.writeBytes(imagedata,rootFilePath);  //文件写入到上传的路径
        song.setImg(ip+":"+port+"/api/file/"+flag);
        song.setSongNo(Random2Utils.orderBuildSn("song"));
        song.setStatus(Status.common.name());
        songService.insert(song);
        webResponse.setStatus(WebResponseCode.SUCCESS);
        webResponse.setData(song);
        webResponse.setMsg("添加成功");
        return webResponse;
    }

    /**
     * 添加(Admin)
     *
     * @return 单条数据
     * @Author 劳威锟
     */
    @GetMapping(value = "/querySongList")
    public WebPageResponse querySongList(@RequestParam(defaultValue = "0") Integer offset, @RequestParam(defaultValue = "10") Integer limit) {
        WebPageResponse response =new WebPageResponse();
        Long total= songService.queryAllCount();
        int start =(offset)*limit;
        if(offset!=0){
            start =(offset-1)*limit;
        }
        List<Song> pages=  songService.queryAllByLimit(start,limit);
        response.setData(pages);
        response.setOffset(start);
        response.setLimit(limit);
        response.setTotal(total);
        response.setStatus(WebResponseCode.SUCCESS);
        response.setMsg("查询成功");
        return response ;
    }

}
