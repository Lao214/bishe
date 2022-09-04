package com.example.mymusic.service.impl;

import com.example.mymusic.common.enums.Status;
import com.example.mymusic.dao.ArticleDao;
import com.example.mymusic.entity.Article;
import com.example.mymusic.service.ArticleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (Article)表服务实现类
 *
 * @author makejava
 * @since 2022-02-08 17:55:24
 */
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleDao articleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param articleNo 主键
     * @return 实例对象
     */
    @Override
    public Article queryArticleByArticleNo(String articleNo) {
        return this.articleDao.queryArticleByArticleNo (articleNo);
    }

    @Override
    public Article queryArticleByArticleId(Long id) {
        return this.articleDao.queryArticleByArticleId (id);
    }


    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Article> queryAllByLimit(int offset, int limit) {
        return this.articleDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param article 实例对象
     * @return 实例对象
     */
    @Override
    public Article insert(Article article) {
        if(StringUtils.isEmpty(article.getStatus())){
            article.setStatus(Status.common.name());
        }
        article.setCreateTime(new Date());
        this.articleDao.addArticle(article);
        return article;
    }

    /**
     * 修改数据
     *
     * @param article 实例对象
     * @return 实例对象
     */
    @Override
    public Article updateArticle(Article article) {
        this.articleDao.updateArticle(article);
        return   article;
    }

    /**
     * 通过主键删除数据
     *
     * @param articleNo 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteByArticleNo(String articleNo) {
        return this.articleDao.deleteByArticleNo(articleNo) > 0;
    }


    /**
     * 假删除
     *
     * @param articleNo 主键
     * @return 是否成功
     */
    @Override
    public String deleteArticleByArticleNo(String articleNo) {
        return this.articleDao.deleteArticleByArticleNo(articleNo);
    }

    @Override
    public Long queryAllCount() {
        return this.articleDao.queryAllCount();
    }
}
