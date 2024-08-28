package com.yefeng.web.model.bo.file;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZoneUploadResultBO {

    /**
     * 分片记录
     */
    private ArchiveZoneRecord zoneRecord;

    /**
     * 是否存在
     */
    private boolean isExist;

    /**
     * 当前分片索引
     */
    private Integer zoneNowIndex;
}
