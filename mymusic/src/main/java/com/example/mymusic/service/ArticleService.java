package com.example.mymusic.service;

import com.example.mymusic.entity.Article;

import java.util.List;

/**
 * (Article)表服务接口
 *
 * @author makejava
 * @since 2022-02-08 17:55:22
 */
public interface ArticleService {

    /**
     * 通过ID查询单条数据
     *
     * @param articleNo 主键
     * @return 实例对象
     */
    Article queryArticleByArticleNo(String articleNo);

    Article queryArticleByArticleId(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Article> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param article 实例对象
     * @return 实例对象
     */
    Article insert(Article article);

    /**
     * 修改数据
     *
     * @param article 实例对象
     * @return 实例对象
     */
    Article updateArticle(Article article);

    /**
     * 通过主键删除数据
     *
     * @param articleNo 主键
     * @return 是否成功
     */
    boolean deleteByArticleNo(String articleNo);

    /**
     * 假删除
     *
     * @param articleNo 主键
     * @return 是否成功
     */
    String deleteArticleByArticleNo(String articleNo);

    Long    queryAllCount();

}
