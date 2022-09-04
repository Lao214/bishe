package com.example.mymusic.dao;


import com.example.mymusic.entity.Song;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 歌曲(Song)表数据库访问层
 *
 * @author makejava
 * @since 2022-01-19 21:22:46
 */
@Repository
public interface SongDao {

    /**
     * 通过ID查询单条数据
     *
     * @param songNo 主键
     * @return 实例对象
     */
    Song querySongBySongNo(String songNo);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Song> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param song 实例对象
     * @return 对象列表
     */
    List<Song> queryAll(Song song);


    Long    queryAllCount();

    /**
     * 新增数据
     *
     * @param song 实例对象
     * @return 影响行数
     */
    int addSong(Song song);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Song> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Song> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Song> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Song> entities);

    /**
     * 修改数据
     *
     * @param song 实例对象
     * @return 影响行数
     */
    int updateSong(Song song);

    /**
     * 通过主键删除数据
     *
     * @param songNo 主键
     * @return 影响行数
     */
    int deleteBySongNo(String songNo);


    /**
     * 假删除
     *
     * @param songNo 主键
     * @return 是否成功
     */
    String deleteSongBySongNo(String songNo);

}

