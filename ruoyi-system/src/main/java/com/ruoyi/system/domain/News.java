package com.ruoyi.system.domain;

import java.util.Date;

import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.springframework.data.annotation.Transient;

/**
 * news对象 news
 *
 * @author ruoyi
 * @date 2024-08-21
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class News extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 新闻标题 */
    @Excel(name = "新闻标题")
    private String title;

    /** 新闻内容 */
    @Excel(name = "新闻内容")
    private String content;

    /** 作者 */
    @Excel(name = "作者")
    private Long author;

    /** 发布日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发布日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date publishDate;

    /** 浏览量 */
    @Excel(name = "浏览量")
    private Long viewCount;

    /**文章类型 **/
    @Excel(name = "文章类型")
    private Long kinds;

    /**附件路径*/
    @Transient
    private String attachmentUrl;

    /**上一条数据、下一条数据*/
    @Transient
    private JSONObject nextNews;
    /**
     * 附件名称
     */
    @Transient
    private String attachmentName;
}
