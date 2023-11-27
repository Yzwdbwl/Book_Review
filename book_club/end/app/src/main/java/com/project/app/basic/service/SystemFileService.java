package com.project.app.basic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.app.basic.entity.SysFile;
import com.project.app.enums.FileTypeEnum;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 文件表 服务类
 * </p>
 *
 * @author admin
 * @since 2023
 */
public interface SystemFileService extends IService<SysFile> {

    List<SysFile> upload(MultipartFile[] files, FileTypeEnum fileTypeEnum);


    void download(String fileId);

    boolean delete(String fileId);

}
