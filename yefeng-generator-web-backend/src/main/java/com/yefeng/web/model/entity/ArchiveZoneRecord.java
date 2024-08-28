package com.yefeng.web.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author yefeng
 * @desciption 文件分片记录
 * @date 2024/5/14 16:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("archive_zone_record")
public class ArchiveZoneRecord {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识：分片记录ID
     */
    @TableId(value = "sid", type = IdType.ASSIGN_UUID)
    private String sid;

    /**
     * webupload生成的分片文件ID
     */
    private String id;

    /**
     * 分片名称
     */
    @TableField(value = "zone_name")
    private String name;

    /**
     * 分片的文件路径
     */
    private String zonePath;

    /**
     * 分片所属的文件类型
     */
    private String type;

    /**
     * 文件后缀
     */
    private String zoneSuffix;

    /**
     * 获取文件后缀
     * getter方法，根据type的值，获取'/'后面的值
     *
     * @return
     */
    public String getZoneSuffix() {
        if (StringUtils.isNotBlank(type)) {
            this.zoneSuffix = StringUtils.substringAfterLast(type, "/");
        }
        return this.zoneSuffix;
    }

    /**
     * 当前分片的MD5
     */
    private String zoneMd5;

    /**
     * 分片记录MD5校验日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastModifiedDate;

    /**
     * 上传完成校验日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date zoneCheckDate;

    /**
     * 全部分片数量
     */
    private Integer zoneTotalCount;

    /**
     * 总分片的MD5值
     */
    private String zoneTotalMd5;

    /**
     * 当前分片序号
     */
    private Integer zoneNowIndex;

    /**
     * 文件开始位置
     */
    private Long zoneStartSize;

    /**
     * 文件结束位置
     */
    private Long zoneEndSize;

    /**
     * 文件总大小
     */
    private Long zoneTotalSize;

    /**
     * 文件的md5
     */
    private String fileMd5;

    /**
     * 文件记录ID
     */
    private String archiveSid;

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

    /**
     * 构造方法,用于创建分片记录
     *
     * @param id
     * @param name
     * @param type
     * @param lastModifiedDate
     * @param fileMd5
     * @param zoneTotalMd5
     * @param zoneMd5
     * @param zoneTotalCount
     * @param zoneNowIndex
     * @param zoneTotalSize
     * @param zoneStartSize
     * @param zoneEndSize
     */
    public ArchiveZoneRecord(String id, String name, String type, Date lastModifiedDate, String fileMd5,
                             String zoneTotalMd5, String zoneMd5, Integer zoneTotalCount, Integer zoneNowIndex,
                             Long zoneTotalSize, Long zoneStartSize, Long zoneEndSize) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.lastModifiedDate = lastModifiedDate;
        this.fileMd5 = fileMd5;
        this.zoneTotalMd5 = zoneTotalMd5;
        this.zoneMd5 = zoneMd5;
        this.zoneTotalCount = zoneTotalCount;
        this.zoneNowIndex = zoneNowIndex;
        this.zoneTotalSize = zoneTotalSize;
        this.zoneStartSize = zoneStartSize;
        this.zoneEndSize = zoneEndSize;
    }

}
