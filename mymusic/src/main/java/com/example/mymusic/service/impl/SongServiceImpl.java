package com.example.mymusic.service.impl;



import com.example.mymusic.dao.SongDao;
import com.example.mymusic.entity.Song;
import com.example.mymusic.service.SongService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 歌曲(Song)表服务实现类
 *
 * @author makejava
 * @since 2022-01-19 21:22:50
 */
@Service("songService")
public class SongServiceImpl implements SongService {
    @Resource
    private SongDao songDao;

    /**
     * 通过ID查询单条数据
     *
     * @param songNo 主键
     * @return 实例对象
     */
    @Override
    public Song querySongBySongNo(String songNo) {
        return this.songDao.querySongBySongNo (songNo);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Song> queryAllByLimit(int offset, int limit) {
        return this.songDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param song 实例对象
     * @return 实例对象
     */
    @Override
    public Song insert(Song song) {
        this.songDao.addSong(song);
        return song;
    }

    /**
     * 修改数据
     *
     * @param song 实例对象
     * @return 实例对象
     */
    @Override
    public Song updateSong(Song song) {
        this.songDao.updateSong(song);
        return null;
    }

    /**
     * 通过主键删除数据
     *
     * @param songNo 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteBySongNo(String songNo) {
        return this.songDao.deleteBySongNo(songNo) > 0;
    }


    /**
     * 假删除
     *
     * @param songNo 主键
     * @return 是否成功
     */
    @Override
    public String deleteSongBySongNo(String songNo) {
        return this.songDao.deleteSongBySongNo(songNo);
    }

    @Override
    public Long queryAllCount() {
        return this.songDao.queryAllCount();
    }
}
