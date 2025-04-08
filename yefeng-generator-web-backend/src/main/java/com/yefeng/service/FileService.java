package com.yefeng.service;

import com.yefeng.common.BaseResponse;
import com.yefeng.model.dto.file.UploadFileRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 叶枫
 * @Date: 2025/04/08/17:34
 * @Description:
 */
public interface FileService {
    BaseResponse<String> uploadFile(MultipartFile multipartFile, UploadFileRequest uploadFileRequest, HttpServletRequest request);
}
