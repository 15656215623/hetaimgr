package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Attachments;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

/**
 * attachmentsMapper接口
 * 
 * @author tian
 * @date 2024-08-20
 */

public interface AttachmentsMapper 
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
    @Select("select * from attachments where id = #{newsId};")
    @ResultMap("AttachmentsResult")
    public Attachments selectAttachmentsByNewsId(Long newsId);
    /**
     * 查询attachments列表
     * 
     * @param attachments attachments
     * @return attachments集合
     */
    @Select("select a.*,n.title as news_title from attachments a left join news n on a.news_id = n.id;")
    @ResultMap("AttachmentsResult")
    public List<Attachments> selectJoinList(Attachments attachments);

    public List<Attachments> selectAttachmentsList(Attachments attachments);

    /**
     * 新增attachments
     * 
     * @param attachments attachments
     * @return 结果
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insertAttachments(Attachments attachments);

    /**
     * 修改attachments
     * 
     * @param attachments attachments
     * @return 结果
     */
    public int updateAttachments(Attachments attachments);

    /**
     * 删除attachments
     * 
     * @param id attachments主键
     * @return 结果
     */
    public int deleteAttachmentsById(Long id);

    /**
     * 批量删除attachments
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAttachmentsByIds(Long[] ids);
}
