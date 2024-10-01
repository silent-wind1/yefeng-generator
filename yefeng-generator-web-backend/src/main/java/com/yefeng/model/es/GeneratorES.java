package com.yefeng.model.es;


import lombok.Data;

import java.io.Serializable;

/**
 * 生成器es
 *
 */
@Data
public class GeneratorES implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 作者
     */
    private String author;

    /**
     * 标签列表（json 数组）
     */
    private String tags;

    /**
     * 图片
     */
    private String picture;
    /**
     * 用户头像
     */
    private String userAvatar;

}