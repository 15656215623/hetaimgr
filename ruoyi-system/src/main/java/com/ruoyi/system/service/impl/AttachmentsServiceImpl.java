package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.AttachmentsMapper;
import com.ruoyi.system.domain.Attachments;
import com.ruoyi.system.service.IAttachmentsService;

/**
 * attachmentsService业务层处理
 * 
 * @author tian
 * @date 2024-08-20
 */
@Service
public class AttachmentsServiceImpl implements IAttachmentsService 
{
    @Autowired
    private AttachmentsMapper attachmentsMapper;

    /**
     * 查询attachments
     * 
     * @param id attachments主键
     * @return attachments
     */
    @Override
    public Attachments selectAttachmentsById(Long id)
    {
        return attachmentsMapper.selectAttachmentsById(id);
    }

    /**
     * 根据新闻id查询附件列表
     * @param newsId
     * @return
     */
    @Override
    public Attachments selectAttachmentsByNewsId(Long newsId) {
        return attachmentsMapper.selectAttachmentsByNewsId(newsId);
    }

    /**
     * 查询attachments列表
     * 
     * @param attachments attachments
     * @return attachments
     */
    @Override
    public List<Attachments> selectAttachmentsList(Attachments attachments)
    {
        return attachmentsMapper.selectAttachmentsList(attachments);
    }

    /**
     * 新增attachments
     * 
     * @param attachments attachments
     * @return 结果
     */
    @Override
    public int insertAttachments(Attachments attachments)
    {
        return attachmentsMapper.insertAttachments(attachments);
    }

    /**
     * 修改attachments
     * 
     * @param attachments attachments
     * @return 结果
     */
    @Override
    public int updateAttachments(Attachments attachments)
    {
        return attachmentsMapper.updateAttachments(attachments);
    }

    /**
     * 批量删除attachments
     * 
     * @param ids 需要删除的attachments主键
     * @return 结果
     */
    @Override
    public int deleteAttachmentsByIds(Long[] ids)
    {
        return attachmentsMapper.deleteAttachmentsByIds(ids);
    }

    /**
     * 删除attachments信息
     * 
     * @param id attachments主键
     * @return 结果
     */
    @Override
    public int deleteAttachmentsById(Long id)
    {
        return attachmentsMapper.deleteAttachmentsById(id);
    }
}
