package com.example.mymusic.entity;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.io.Serializable;

/**
 * 歌曲(Song)实体类
 *
 * @author makejava
 * @since 2022-01-19 21:22:52
 */
public class Song implements Serializable {
    private static final long serialVersionUID = 486960584348805179L;

    private Long id;
    /**
     * 歌曲编号
     */
    private String songNo;
    /**
     * 歌曲中文名
     */
    private String songCnName;
    /**
     * 歌曲名称
     */
    private String songName;
    /**
     * 歌手编号
     */
    private String singerNo;
    /**
     * 歌手
     */
    private String singer;
    /**
     * 专辑编号
     */
    private String albumNo;
    /**
     * 专辑
     */
    private String album;
    /**
     * 作曲家
     */
    private String composer;
    /**
     * 编曲家
     */
    private String arranger;
    /**
     * 歌曲时长
     */
    private Object songTime;
    /**
     * 歌曲链接
     */
    private String songUrl;
    /**
     * 作曲家编号
     */
    private String composerNo;
    /**
     * 编曲家编号
     */
    private String arrangerNo;
    /**
     * 点赞
     */
    private Long likes;
    /**
     * 评论
     */
    private String comment;
    /**
     * 评论数
     */
    private Long commentNum;
    /**
     * 收藏数
     */
    private Long collect;
    /**
     * 歌词
     */
    private String lyric;
    /**
     * 中文翻译
     */
    private String lyricCn;
    /**
     * 封面
     */
    private String img;
    /**
     * MV
     */
    private String mv;
    /**
     * 备注
     */
    private String remark;

    private String status;

    private String rootFilePath;

    private MultipartFile file;

    private Date createTime;

    private Date updateTime;
    /**
     * 发布时间
     */
    private Date issueTime;

    /**
     * 发布时间
     */
    private String imageUrl;

    /**
     * 发布时间
     */
    private String filename;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRootFilePath() {
        return rootFilePath;
    }

    public void setRootFilePath(String rootFilePath) {
        this.rootFilePath = rootFilePath;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSongNo() {
        return songNo;
    }

    public void setSongNo(String songNo) {
        this.songNo = songNo;
    }

    public String getSongCnName() {
        return songCnName;
    }

    public void setSongCnName(String songCnName) {
        this.songCnName = songCnName;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSingerNo() {
        return singerNo;
    }

    public void setSingerNo(String singerNo) {
        this.singerNo = singerNo;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getAlbumNo() {
        return albumNo;
    }

    public void setAlbumNo(String albumNo) {
        this.albumNo = albumNo;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public String getArranger() {
        return arranger;
    }

    public void setArranger(String arranger) {
        this.arranger = arranger;
    }

    public Object getSongTime() {
        return songTime;
    }

    public void setSongTime(Object songTime) {
        this.songTime = songTime;
    }

    public String getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }

    public String getComposerNo() {
        return composerNo;
    }

    public void setComposerNo(String composerNo) {
        this.composerNo = composerNo;
    }

    public String getArrangerNo() {
        return arrangerNo;
    }

    public void setArrangerNo(String arrangerNo) {
        this.arrangerNo = arrangerNo;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Long commentNum) {
        this.commentNum = commentNum;
    }

    public Long getCollect() {
        return collect;
    }

    public void setCollect(Long collect) {
        this.collect = collect;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public String getLyricCn() {
        return lyricCn;
    }

    public void setLyricCn(String lyricCn) {
        this.lyricCn = lyricCn;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getMv() {
        return mv;
    }

    public void setMv(String mv) {
        this.mv = mv;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getIssueTime() {
        return issueTime;
    }

    public void setIssueTime(Date issueTime) {
        this.issueTime = issueTime;
    }

}
