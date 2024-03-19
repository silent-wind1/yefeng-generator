package com.yefeng.maker.template;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import com.yefeng.maker.template.enums.FileFilterRangeEnum;
import com.yefeng.maker.template.enums.FileFilterRuleEnum;
import com.yefeng.maker.template.model.FileFilterConfig;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 过滤文件
 */
public class FileFilter {
    /**
     * 对某个文件或目录进行过滤，返回文件列表
     *
     * @param filePath 文件路径
     * @param filterConfigList 筛选器配置列表
     * @return
     */
    public static List<File> doFilter(String filePath, List<FileFilterConfig> filterConfigList) {
        // 根据路径获取所有文件
        List<File> fileList = FileUtil.loopFiles(filePath);
        return fileList.stream()
                .filter(file -> doSingleFileFilter(file, filterConfigList))
                .collect(Collectors.toList());
    }

    /**
     * 单个文件过滤
     *
     * @param file             单个文件
     * @param filterConfigList 筛选器配置列表
     * @return 是否保留
     */
    public static boolean doSingleFileFilter(File file, List<FileFilterConfig> filterConfigList) {
        String fileName = file.getName();
        String fileContent = FileUtil.readUtf8String(file);
        boolean result = true;
        if (CollUtil.isEmpty(filterConfigList)) {
            return true;
        }
        for (FileFilterConfig fileFilterConfig : filterConfigList) {
            String range = fileFilterConfig.getRange();
            String rule = fileFilterConfig.getRule();
            String value = fileFilterConfig.getValue();
            // 范围枚举
            FileFilterRangeEnum fileFilterRangeEnum = FileFilterRangeEnum.getEnumByValue(range);
            if (fileFilterRangeEnum == null) {
                continue;
            }
            // 要校验啥
            String content = fileName;
            switch (fileFilterRangeEnum) {
                case FILE_NAME:
                    content = fileName;
                    break;
                case FILE_CONTENT:
                    content = fileContent;
                    break;
                default:
            }
            // 规则枚举
            FileFilterRuleEnum fileFilterRuleEnum = FileFilterRuleEnum.getEnumByValue(rule);
            if (fileFilterRuleEnum == null) {
                continue;
            }
            switch (fileFilterRuleEnum) {
                case CONTAINS:
                    result = content.contains(value);
                    break;
                case STARTS_WITH:
                    result = content.startsWith(value);
                    break;
                case ENDS_WITH:
                    result = content.endsWith(value);
                    break;
                case REGEX:
                    result = content.matches(value);
                    break;
                case EQUALS:
                    result = content.equals(value);
                    break;
                default:
            }

            // 有一个不满足，返回false，文件不保留
            if (!result) {
                return false;
            }
        }

        // 都满足
        return true;
    }

}
