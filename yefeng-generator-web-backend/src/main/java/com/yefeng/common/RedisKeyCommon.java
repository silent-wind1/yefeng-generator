package com.yefeng.common;

import cn.hutool.core.codec.Base64Encoder;
import cn.hutool.json.JSONUtil;
import com.yefeng.model.dto.generator.GeneratorQueryRequest;

/**
 * @Author: 叶枫
 * @Date: 2024/05/16/14:01
 * @Description: 生成RedisKey
 */
public class RedisKeyCommon {
    /**
     * 获取分页Key
     *
     * @param generatorQueryRequest 生成器查询请求
     * @return key
     */
    public static String getPageCacheKey(GeneratorQueryRequest generatorQueryRequest) {
        String JsonStr = JSONUtil.toJsonStr(generatorQueryRequest);
        String base64 = Base64Encoder.encode(JsonStr);
        return "generator:page:" + base64;
    }
}
