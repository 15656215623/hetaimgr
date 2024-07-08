package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.Attachments;
import com.ruoyi.system.service.IAttachmentsService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.News;
import com.ruoyi.system.service.INewsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * newsController
 * 
 * @author tian
 * @date 2024-08-20
 */
@RestController
@RequestMapping("/system/news")
public class NewsController extends BaseController
{
    @Autowired
    private INewsService newsService;

    @Autowired
    private IAttachmentsService attachmentsService;


    /**
     * 查询news列表
     */
    @Anonymous
    @GetMapping("/list")
    public TableDataInfo list(News news)
    {
        startPage();
        List<News> list = newsService.selectNewsList(news);
        return getDataTable(list);
    }
    /***
     * 查询最新的7条数据
     */
    @Anonymous
    @GetMapping("/getCurrentList")
    public TableDataInfo getCurrentList(News news)
    {
        List<News> list = newsService.getCurrentList(news);
        return getDataTable(list);
    }

    /**
     * 获取上一条数据与下一条数据
     */
    @Anonymous
    @GetMapping(value = "/getPreAndNextNews/{id}")
    public AjaxResult getPreviousAndNext(@PathVariable("id") Long id){
     //1、获取当前的数据
      News news = newsService.selectNewsById(id);
      //2、获取上下一条数据
      JSONObject jsonobject = newsService.getPreviousAndNext(id);
      news.setNextNews(jsonobject);
      //3、看一次更新一次浏览量
        News uNews = new News();
        uNews.setId(id);
        uNews.setViewCount(news.getViewCount()+1);
        newsService.updateNews(uNews);
        //4、获取附件信息
        Attachments attachments = attachmentsService.selectAttachmentsByNewsId(news.getId());
        if(attachments!= null){
            news.setAttachmentName(attachments.getFileName());
            news.setAttachmentUrl(attachments.getFilePath());
        }
        return success(news);
  }

    /**
     * 获取news详细信息
     */
    @Anonymous
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        //1、查看一次，就累加浏览量
        News news = newsService.selectNewsById(id);
        News uNews = new News();
        uNews.setId(id);
        uNews.setViewCount(news.getViewCount()+1);
        newsService.updateNews(uNews);
        //2、获取附件信息
        return success(news);
    }



    /**
     * 新增news
     */
    @PreAuthorize("@ss.hasPermi('system:news:add')")
    @Log(title = "news", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(News news) throws JsonProcessingException {
       //1、判断对象是否为空
        if(news == null){
            return error("抱歉,发布的内容不能为空");
        }
        //2、如果包含图片路径
        String content = news.getContent();
        if(StringUtils.isNotEmpty(content)){
            // 第一步: 替换 src=\" 成 src="
            content = content.replaceAll("src=\\\"", "src=\"");
            // 第二步: 替换所有的 \" 成 "
            content = content.replaceAll("\\\\\"", "\"");
            news.setContent(content);
        }
            System.out.println(content);
            //3、插入数据到表中去
            newsService.insertNews(news);
            //1、存入附件信息
        if(StringUtils.isNotEmpty(news.getAttachmentUrl())){
            Attachments attachments = new Attachments();
            //"/profile/upload/2024/08/21/密码_20240821162024A003.xls"
            attachments.setFilePath(news.getAttachmentUrl());
            attachments.setFileName(news.getAttachmentUrl());
            attachments.setFileSize(1L);
            attachments.setFileType("xls");
            attachments.setNewsId(news.getId());
            return toAjax(attachmentsService.insertAttachments(attachments));
        }

       return toAjax(true);
    }

    /**
     * 修改news
     */
    @PreAuthorize("@ss.hasPermi('system:news:edit')")
    @Log(title = "news", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(News news) {
        //1、删除附件信息
        Attachments attachments = new Attachments();
        if(news.getId() == null){

        }else{
            attachmentsService.deleteAttachmentsById(news.getId());
            if(StringUtils.isNotEmpty(news.getAttachmentUrl())){
                //2、添加附件信息
                attachments = new Attachments();
                //"/profile/upload/2024/08/21/密码_20240821162024A003.xls"
                attachments.setFilePath(news.getAttachmentUrl());
                attachments.setFileName(news.getAttachmentUrl());
                attachments.setFileSize(1L);
                attachments.setFileType("xls");
                attachments.setNewsId(news.getId());
                attachmentsService.insertAttachments(attachments);
            }
        }
        //3、修改新闻表信息
        return toAjax(newsService.updateNews(news));
    }

    /**
     * 删除news
     */
    @PreAuthorize("@ss.hasPermi('system:news:remove')")
    @Log(title = "news", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(newsService.deleteNewsByIds(ids));
    }
}
