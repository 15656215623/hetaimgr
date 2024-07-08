package com.ruoyi.system.service.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.NewsMapper;
import com.ruoyi.system.domain.News;
import com.ruoyi.system.service.INewsService;

/**
 * newsService业务层处理
 * 
 * @author tian
 * @date 2024-08-20
 */
@Service
public class NewsServiceImpl implements INewsService 
{
    @Autowired
    private NewsMapper newsMapper;

    /**
     * 查询news
     * 
     * @param id news主键
     * @return news
     */
    @Override
    public News selectNewsById(Long id)
    {
        return newsMapper.selectNewsById(id);
    }

    /**
     * 查询news列表
     * 
     * @param news news
     * @return news
     */
    @Override
    public List<News> selectNewsList(News news)
    {
        return newsMapper.selectNewsList(news);
    }

    /**
     * 获取最新的10条数据
     * @param news
     * @return
     */
    @Override
    public List<News> getCurrentList(News news) {
        return newsMapper.getCurrentList(news);
    }
/**
 * 获取上一条与下一条数据
 * @param  id
 * @return
 */
    @Override
    public JSONObject getPreviousAndNext(Long id) {
        News lastNews = newsMapper.getLastNews(id);
        News nextNews = newsMapper.getNextNews(id);
        JSONObject json = new JSONObject();
        if(lastNews == null){
            json.put("lastId",0);
            json.put("lastTitle","");
        }else {
            json.put("lastId", lastNews.getId());
            json.put("lastTitle", lastNews.getTitle());
        }
        if(nextNews == null){
            json.put("nextId",0);
            json.put("nextTitle","");
        }else {
            json.put("nextId", nextNews.getId());
            json.put("nextTitle", nextNews.getTitle());
        }
        return json;
    }

    /**
     * 新增news
     * 
     * @param news news
     * @return 结果
     */
    @Override
    public int insertNews(News news)
    {
        return newsMapper.insertNews(news);
    }

    /**
     * 修改news
     * 
     * @param news news
     * @return 结果
     */
    @Override
    public int updateNews(News news)
    {
        return newsMapper.updateNews(news);
    }

    /**
     * 批量删除news
     * 
     * @param ids 需要删除的news主键
     * @return 结果
     */
    @Override
    public int deleteNewsByIds(Long[] ids)
    {
        return newsMapper.deleteNewsByIds(ids);
    }

    /**
     * 删除news信息
     * 
     * @param id news主键
     * @return 结果
     */
    @Override
    public int deleteNewsById(Long id)
    {
        return newsMapper.deleteNewsById(id);
    }
}
