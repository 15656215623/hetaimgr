package com.ruoyi.system.domain;

import lombok.*;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * attachments对象 attachments
 * 
 * @author tian
 * @date 2024-08-20
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Attachments extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 新闻ID */
    @Excel(name = "新闻ID")
    private Long newsId;

    /** 文件名 */
    @Excel(name = "文件名")
    private String fileName;

    /** 文件路径 */
    @Excel(name = "文件路径")
    private String filePath;

    /** 文件类型 */
    @Excel(name = "文件类型")
    private String fileType;

    /** 文件大小 */
    @Excel(name = "文件大小")
    private Long fileSize;

}
