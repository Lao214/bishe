package com.example.mymusic.dao;
import com.example.mymusic.entity.Article;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (Article)表数据库访问层
 *
 * @author makejava
 * @since 2022-02-08 17:55:21
 */
@Repository
public interface ArticleDao {

    /**
     * 通过ID查询单条数据
     *
     * @param articleNo 主键
     * @return 实例对象
     */
    Article queryArticleByArticleNo(String articleNo);

    Article queryArticleByArticleId(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Article> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param article 实例对象
     * @return 对象列表
     */
    List<Article> queryAll(Article article);

    /**
     * 新增数据
     *
     * @param article 实例对象
     * @return 影响行数
     */
    int addArticle(Article article);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Article> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Article> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Article> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Article> entities);

    /**
     * 修改数据
     *
     * @param article 实例对象
     * @return 影响行数
     */
    int updateArticle(Article article);

    /**
     * 通过主键删除数据
     *
     * @param articleNo 主键
     * @return 影响行数
     */
    int deleteByArticleNo(String articleNo);


    /**
     * 假删除
     *
     * @param articleNo 主键
     * @return 是否成功
     */
    String deleteArticleByArticleNo(String articleNo);


    Long    queryAllCount();

}

