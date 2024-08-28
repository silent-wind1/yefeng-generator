package com.yefeng.web.service;

import com.yefeng.web.common.BaseResponse;
import com.yefeng.web.model.bo.file.FileUploadResultBO;
import com.yefeng.web.model.bo.file.ZoneUploadResultBO;
import com.yefeng.web.model.entity.ArchiveZoneRecord;
import com.yefeng.web.model.enums.CheckType;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 叶枫
 * @Date: 2024/08/28/15:06
 * @Description:
 */
public interface FileService {
    /**
     * 分片上传
     *
     * @param request
     * @param ArchiveZoneRecord
     * @return
     */
    ZoneUploadResultBO zoneUpload(HttpServletRequest request, ArchiveZoneRecord ArchiveZoneRecord, MultipartFile multipartFile);

    /**
     * 校验分片
     *
     * @param ArchiveZoneRecord
     * @param checkType
     * @param request
     * @return
     */
    BaseResponse<?> md5Check(ArchiveZoneRecord ArchiveZoneRecord, CheckType checkType, HttpServletRequest request);

    /**
     * 合并分片
     *
     * @param totalMd5
     * @param request
     * @return
     */
    FileUploadResultBO mergeZoneFile(String totalMd5, HttpServletRequest request);

}
