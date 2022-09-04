package com.example.mymusic.service;


import com.example.mymusic.entity.Song;

import java.util.List;

/**
 * 歌曲(Song)表服务接口
 *
 * @author makejava
 * @since 2022-01-19 21:22:48
 */
public interface SongService {

    /**
     * 通过ID查询单条数据
     *
     * @param songNo 主键
     * @return 实例对象
     */
    Song querySongBySongNo(String songNo);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Song> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param song 实例对象
     * @return 实例对象
     */
    Song insert(Song song);

    /**
     * 修改数据
     *
     * @param song 实例对象
     * @return 实例对象
     */
    Song updateSong(Song song);

    /**
     * 通过主键删除数据
     *
     * @param songNo 主键
     * @return 是否成功
     */
    boolean deleteBySongNo(String songNo);

    /**
     * 假删除
     *
     * @param songNo 主键
     * @return 是否成功
     */
    String deleteSongBySongNo(String songNo);


    Long    queryAllCount();


}
