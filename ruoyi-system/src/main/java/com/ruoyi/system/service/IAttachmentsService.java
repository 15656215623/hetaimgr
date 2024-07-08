package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Attachments;

/**
 * attachmentsService接口
 * 
 * @author tian
 * @date 2024-08-20
 */
public interface IAttachmentsService 
{
    /**
     * 查询attachments
     * 
     * @param id attachments主键
     * @return attachments
     */
    public Attachments selectAttachmentsById(Long id);
    /***
     * 根据新闻id查询附件列表
     *
     */
    public Attachments selectAttachmentsByNewsId(Long newsId);
    /**
     * 查询attachments列表
     * 
     * @param attachments attachments
     * @return attachments集合
     */
    public List<Attachments> selectAttachmentsList(Attachments attachments);

    /**
     * 新增attachments
     * 
     * @param attachments attachments
     * @return 结果
     */
    public int insertAttachments(Attachments attachments);

    /**
     * 修改attachments
     * 
     * @param attachments attachments
     * @return 结果
     */
    public int updateAttachments(Attachments attachments);

    /**
     * 批量删除attachments
     * 
     * @param ids 需要删除的attachments主键集合
     * @return 结果
     */
    public int deleteAttachmentsByIds(Long[] ids);

    /**
     * 删除attachments信息
     * 
     * @param id attachments主键
     * @return 结果
     */
    public int deleteAttachmentsById(Long id);
}
