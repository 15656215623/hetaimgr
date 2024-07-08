package com.ruoyi.system.service;

import java.util.List;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.system.domain.News;

/**
 * newsService接口
 * 
 * @author tian
 * @date 2024-08-20
 */
public interface INewsService 
{
    /**
     * 查询news
     * 
     * @param id news主键
     * @return news
     */
    public News selectNewsById(Long id);

    /**
     * 查询news列表
     * 
     * @param news news
     * @return news集合
     */
    public List<News> selectNewsList(News news);

    /**
     * 查询最新的7条数据
     *
     * @param news
     * @return news集合
     */
    public List<News> getCurrentList(News news);
    /**
     * 获取上一条与下一条数据
     *
     * @param id
     * @return json
     */
    JSONObject getPreviousAndNext(Long id);
    /**
     * 新增news
     * 
     * @param news news
     * @return 结果
     */
    public int insertNews(News news);

    /**
     * 修改news
     * 
     * @param news news
     * @return 结果
     */
    public int updateNews(News news);

    /**
     * 批量删除news
     * 
     * @param ids 需要删除的news主键集合
     * @return 结果
     */
    public int deleteNewsByIds(Long[] ids);

    /**
     * 删除news信息
     * 
     * @param id news主键
     * @return 结果
     */
    public int deleteNewsById(Long id);
}
