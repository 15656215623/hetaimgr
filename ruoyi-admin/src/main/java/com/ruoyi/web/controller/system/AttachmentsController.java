package com.ruoyi.web.controller.system;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.ruoyi.common.annotation.Anonymous;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Attachments;
import com.ruoyi.system.service.IAttachmentsService;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * attachmentsController
 * 
 * @author tian
 * @date 2024-08-20
 */
@RestController
@RequestMapping("/system/attachments")
public class AttachmentsController extends BaseController
{
    @Autowired
    private IAttachmentsService attachmentsService;

    /**
     * 查询attachments列表
     */
    @PreAuthorize("@ss.hasPermi('system:attachments:list')")
    @GetMapping("/list")
    public TableDataInfo list(Attachments attachments)
    {
        startPage();
        List<Attachments> list = attachmentsService.selectAttachmentsList(attachments);
        return getDataTable(list);
    }

    /**
     * 根据id查询attachments详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:attachments:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id){
        return AjaxResult.success(attachmentsService.selectAttachmentsById(id));
    }

    /**
     * 新增attachments
     */
    @PreAuthorize("@ss.hasPermi('system:attachments:add')")
    @Log(title = "attachments", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestParam("file") MultipartFile file)
    {
        Attachments attachment = null;
        try {
            // 获取文件名
            String fileName = file.getOriginalFilename();
            // 指定文件保存路径
            Path filePath = Paths.get("E:/var/uploads", fileName);
            // 保存文件到指定路径
            Files.write(filePath, file.getBytes());
            // 返回文件的元数据
            attachment = new Attachments();
            attachment.setFileName(fileName);
            attachment.setFilePath(filePath.toString());
            attachment.setFileType(file.getContentType());
            attachment.setFileSize(file.getSize());
        } catch (IOException e) {
            return AjaxResult.error("文件上传失败"+e.getMessage());
        }
        return toAjax(attachmentsService.insertAttachments(attachment));
    }

}
