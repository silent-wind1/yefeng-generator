package com.yefeng.web.model.bo.file;

import com.yefeng.web.model.entity.Archive;
import lombok.Data;

@Data
public class FileUploadResultBO {

    /**
     * 文件网络访问路径
     * 拼接后端请求url或者域名就可以访问
     */
    private String networkPath;

    /**
     * 文件详细信息
     */
    private Archive fileInfo;

    /**
     * 文件id
     */
    private String fileId;
}
