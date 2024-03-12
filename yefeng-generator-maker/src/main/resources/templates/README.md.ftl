# ${name}

> ${description}
>
> 作者：${author}
>
> [yefeng代码生成器项目](https://github.com/silent-wind1/)，感谢您的使用！

可以通过命令行交互式输入的方式动态生成想要的项目代码

## 使用说明

执行项目根目录下的脚本文件：

```
generator <命令> <选项参数>
```

示例命令：

```
generator generate <#list modelConfig.models as modelInfo><#-- 有分组 --><#if modelInfo.groupKey??><#list modelInfo.models as modelInfos>${modelInfos.abbr} </#list><#else>-${modelInfo.abbr} </#if></#list>
```

## 参数说明

<#list modelConfig.models as modelInfo>
<#if modelInfo.groupKey??>
<#-- 有分组 -->
<#list modelInfo.models as modelInfos>
${modelInfos?index + 1}）${modelInfos.fieldName}
- 类型：${modelInfos.type}

- 描述：${modelInfos.description}

- 默认值：${modelInfos.defaultValue?c}

- 缩写： -${modelInfos.abbr}

</#list>

<#else>

${modelInfo?index + 1}）${modelInfo.fieldName}
- 类型：${modelInfo.type}

- 描述：${modelInfo.description}

- 默认值：${modelInfo.defaultValue?c}

- 缩写： -${modelInfo.abbr}

</#if>

</#list>


> 如果觉得好用请点一个关注吧~