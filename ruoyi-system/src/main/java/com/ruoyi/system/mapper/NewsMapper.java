package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.News;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

/**
 * newsMapper接口
 * 
 * @author tian
 * @date 2024-08-20
 */
public interface NewsMapper 
{
    /**
     * 查询news
     * 
     * @param id news主键
     * @return news
     */
 public News selectNewsById(Long id);

    /**
     * 查询最新的新闻列表
     * @param news
     * @return
     */
    @Select("select * from news where kinds=#{kinds} and publish_date <= NOW() order by publish_date desc limit 10;")
 public List<News> getCurrentList(News news);

/**
 * 获取上一条数据
 */
@Select("SELECT * FROM news WHERE id = (SELECT MIN(id) FROM news WHERE id > #{id})")
public News getLastNews(Long id);
/**
 * 获取下一条数据
 */
@Select("SELECT * FROM news WHERE id = (SELECT MAX(id) FROM news WHERE id <  #{id})")
public News getNextNews(Long id);

    /**
     * 查询news列表
     * 
     * @param news news
     * @return news集合
     */
 public List<News> selectNewsList(News news);

    /**
     * 新增news
     * 
     * @param news news
     * @return 结果
     */
@Options(useGeneratedKeys = true, keyProperty = "id")
    public int insertNews(News news);

    /**
     * 修改news
     * 
     * @param news news
     * @return 结果
     */
public int updateNews(News news);

    /**
     * 删除news
     * 
     * @param id news主键
     * @return 结果
     */
    public int deleteNewsById(Long id);

    /**
     * 批量删除news
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNewsByIds(Long[] ids);
}
