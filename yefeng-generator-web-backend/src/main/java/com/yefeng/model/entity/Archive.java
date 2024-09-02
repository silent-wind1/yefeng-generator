package com.yefeng.web.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 附件表
 */
@Data
@Accessors(chain = true)
@TableName(value = "archive")
public class Archive {

    /**
     * string id, 类似老系统的RUID
     */
    @TableId(value = "sid", type = IdType.ASSIGN_UUID)
    private String sid;

    /**
     * 文件路径
     * 示例：2024/04/30/361dc49a-b477-4ab2-937a-a35bd7004738.xlsx
     */
    private String path;

    /**
     * 文件大小(字节 byte)
     */
    private long size;

    /**
     * 文件时长（秒 second）
     */
    private Long duration;

    /**
     * 文件附件名,上传后生成的文件名
     * 示例：30307b4d-c05d-423d-9322-6a7f4f175dc7.pdf
     */
    private String fileName;

    /**
     * 文件附件原始名,带扩展名
     */
    private String originalName;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 服务器储存路径
     * 示例： D:/webupload/webuploadfile/pdf/2024/0513/08/30307b4d-c05d-423d-9322-6a7f4f175dc7.pdf
     */
    private String fullPath;

    /**
     * 是否合并
     */
    private boolean mergeFlag;

    /**
     * 文件MD5值
     */
    private String md5Value;

    /**
     * 是否分片 0 否 1 是
     */
    private boolean zoneFlag;

    /**
     * 分片总数
     */
    private Integer zoneTotal;

    /**
     * 分片时间
     */
    private LocalDateTime zoneDate;

    /**
     * 分片合并时间
     */
    private LocalDateTime zoneMergeDate;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}