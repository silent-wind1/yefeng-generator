package com.yefeng.controller;

import com.qcloud.cos.model.COSObject;
import com.qcloud.cos.model.COSObjectInputStream;
import com.qcloud.cos.utils.IOUtils;
import com.yefeng.annotation.AuthCheck;
import com.yefeng.common.BaseResponse;
import com.yefeng.common.ErrorCode;
import com.yefeng.common.ResultUtils;
import com.yefeng.constant.UserConstant;
import com.yefeng.exception.BusinessException;
import com.yefeng.manager.CosManager;
import com.yefeng.model.dto.file.UploadFileRequest;
import com.yefeng.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * 文件接口
 */
@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {

    @Resource
    private CosManager cosManager;

    @Resource
    private FileService fileService;


    /**
     * 测试文件上传
     *
     * @param multipartFile
     * @return
     */
    @PostMapping("/test/upload")
    public BaseResponse<String> testUploadFile(@RequestPart("file") MultipartFile multipartFile) {
        // 文件目录
        String fileName = multipartFile.getOriginalFilename();
        String filePath = String.format("test/%s", fileName);
        File file = null;
        try {
            file = File.createTempFile(filePath, null);
            multipartFile.transferTo(file);
            cosManager.putObject(filePath, file);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (file != null) {
                boolean delete = file.delete();
                if (delete) {
                    log.error("file delete error, filepath = {}", filePath);
                }
            }
        }

        return ResultUtils.success(filePath);
    }

    /**
     * 文件上传
     *
     * @param multipartFile 文件
     * @param uploadFileRequest 上传文件请求
     * @param request 请求信息
     * @return 上传结果
     */
    @PostMapping("/upload")
    public BaseResponse<String> uploadFile(@RequestPart("file") MultipartFile multipartFile, UploadFileRequest uploadFileRequest, HttpServletRequest request) {
        return fileService.uploadFile(multipartFile, uploadFileRequest, request);

    }

    /**
     * 测试文件下载
     *
     * @param filepath 文件路径
     * @param response 响应信息
     */
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    @GetMapping("/test/download/")
    public void testDownloadFile(String filepath, HttpServletResponse response) throws IOException {
        COSObjectInputStream cosObjectInput = null;
        try {
            COSObject cosObject = cosManager.getObject(filepath);
            cosObjectInput = cosObject.getObjectContent();
            // 处理下载到的流
            byte[] bytes = IOUtils.toByteArray(cosObjectInput);
            // 设置响应头
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + filepath);
            // 写入响应
            response.getOutputStream().write(bytes);
            response.getOutputStream().flush();
        } catch (Exception e) {
            log.error("file download error, filepath = {}", filepath);
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "下载失败");
        } finally {
            if (cosObjectInput != null) {
                cosObjectInput.close();
            }
        }
    }
}
