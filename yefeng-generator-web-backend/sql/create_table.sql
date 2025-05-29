-- 创建库
create database if not exists my_yefeng;

-- 切换库
use my_yefeng;

-- 用户表
create table if not exists user
(
    id           bigint auto_increment comment 'id' primary key,
    userAccount  varchar(256)                           not null comment '账号',
    userPassword varchar(512)                           not null comment '密码',
    userName     varchar(256)                           null comment '用户昵称',
    userAvatar   varchar(1024)                          null comment '用户头像',
    userProfile  varchar(512)                           null comment '用户简介',
    userRole     varchar(256) default 'user'            not null comment '用户角色：user/admin/ban',
    createTime   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint      default 0                 not null comment '是否删除',
    index idx_userAccount (userAccount)
) comment '用户' collate = utf8mb4_unicode_ci;


-- 模拟 用户数据

INSERT INTO `user` VALUES (1, 'claw', 'df3f595406c59c46af221924d171f30f', '管理员豹警官', 'https://avatars.githubusercontent.com/u/62734268?v=4', '豹警官：勇敢维护动物城和平，夏奇羊粉丝，甜甜圈的忠实爱好者。', 'admin', '2024-04-09 18:25:09', '2024-04-09 18:25:09', 0);
INSERT INTO `user` VALUES (2, 'claw2', 'df3f595406c59c46af221924d171f30f', '档案室豹警官', 'https://avatars.githubusercontent.com/u/62734268?v=4', '豹警官：勇敢维护动物城和平，夏奇羊粉丝，甜甜圈的忠实爱好者。', 'user', '2024-04-09 18:25:09', '2024-04-09 18:25:09', 0);
INSERT INTO `user` VALUES (1777702612496613378, 'yefeng', '553c4e984a3d1d599ab662025154ef26', '叶枫', 'https://avatars.githubusercontent.com/u/62734268?v=4', '豹警官：勇敢维护动物城和平，夏奇羊粉丝，甜甜圈的忠实爱好者。', 'admin', '2024-04-09 22:18:26', '2024-04-11 22:50:22', 0);
INSERT INTO `user` VALUES (1778382765258219522, 'rainy', '553c4e984a3d1d599ab662025154ef26', '叶枫', 'https://avatars.githubusercontent.com/u/62734268?v=4', NULL, 'user', '2024-04-11 19:21:11', '2024-04-11 19:21:11', 0);


-- 代码生成器表
create table if not exists generator
(
    id          bigint auto_increment comment 'id' primary key,
    name        varchar(128)                       null comment '名称',
    description text                               null comment '描述',
    basePackage varchar(128)                       null comment '基础包',
    version     varchar(128)                       null comment '版本',
    author      varchar(128)                       null comment '作者',
    tags        varchar(1024)                      null comment '标签列表（json 数组）',
    picture     varchar(256)                       null comment '图片',
    fileConfig  text                               null comment '文件配置（json 字符串）',
    modelConfig text                               null comment '模型配置（json 字符串）',
    distPath    text                               null comment '代码生成器产物路径',
    status      int      default 0                 not null comment '状态',
    userId      bigint                             not null comment '创建用户 id',
    createTime  datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime  datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete    tinyint  default 0                 not null comment '是否删除',
    index idx_userId (userId)
) comment '代码生成器' collate = utf8mb4_unicode_ci;

INSERT INTO `generator` VALUES (1, 'ACM 模板项目', 'ACM 模板项目生成器cccc', 'com.yefeng', '1.0', 'yefeng', '[\"Java\"]', 'https://pic.yupi.icu/1/_r0_c1851-bf115939332e.jpg', '{}', '{\"models\":[{\"fieldName\":\"needGit\",\"type\":\"boolean\",\"description\":\"是否生成 .gitignore 文件\",\"defaultValue\":true,\"abbr\":\"ng\"},{\"fieldName\":\"loop\",\"type\":\"boolean\",\"description\":\"是否生成循环\",\"defaultValue\":false,\"abbr\":\"l\"},{\"type\":\"MainTemplate\",\"description\":\"用于生成核心模板文件\",\"groupKey\":\"mainTemplate\",\"groupName\":\"核心模板\",\"models\":[{\"fieldName\":\"author\",\"type\":\"String\",\"description\":\"作者注释\",\"defaultValue\":\"yefeng\",\"abbr\":\"a\"},{\"fieldName\":\"outputText\",\"type\":\"String\",\"description\":\"输出信息\",\"defaultValue\":\"yefeng = \",\"abbr\":\"o\"}],\"condition\":\"loop\"}]}', '/generator_dist/1777702612496613378/aBBEUeph-Java - 副本.md', 0, 1777702612496613378, '2024-05-15 22:44:35', '2024-05-15 22:44:35', 0);
INSERT INTO `generator` VALUES (2, 'Spring Boot 初始化模板', 'Spring Boot 初始化模板项目生成器', 'com.yefeng', '1.0', 'yefeng', '[\"Java\"]', 'https://pic.yupi.icu/1/_r0_c0726-7e30f8db802a.jpg', '{}', '{\"models\":[{\"fieldName\":\"needGit\",\"type\":\"boolean\",\"description\":\"是否生成 .gitignore 文件\",\"defaultValue\":true,\"abbr\":\"ng\"},{\"fieldName\":\"loop\",\"type\":\"boolean\",\"description\":\"是否生成循环\",\"defaultValue\":false,\"abbr\":\"l\"},{\"type\":\"MainTemplate\",\"description\":\"用于生成核心模板文件\",\"groupKey\":\"mainTemplate\",\"groupName\":\"核心模板\",\"models\":[{\"fieldName\":\"author\",\"type\":\"String\",\"description\":\"作者注释\",\"defaultValue\":\"yefeng\",\"abbr\":\"a\"},{\"fieldName\":\"outputText\",\"type\":\"String\",\"description\":\"输出信息\",\"defaultValue\":\"yefeng = \",\"abbr\":\"o\"}],\"condition\":\"loop\"}]}', '/generator_dist/1777702612496613378/GMGgybAW-acm-template-pro-generator-dist.zip', 0, 1777702612496613378, '2025-04-18 12:54:40', '2025-04-18 12:54:40', 0);
INSERT INTO `generator` VALUES (3, '瑞幸咖啡', 'CodeGenX项目生成器', 'com.yefeng', '1.0', 'yefeng', '[\"Java\",\"前端\"]', 'https://pic.yupi.icu/1/_r1_c0cf7-f8e4bd865b4b.jpg', '{}', '{\"models\":[{\"fieldName\":\"needGit\",\"type\":\"boolean\",\"description\":\"是否生成 .gitignore 文件\",\"defaultValue\":true,\"abbr\":\"ng\"},{\"fieldName\":\"loop\",\"type\":\"boolean\",\"description\":\"是否生成循环\",\"defaultValue\":false,\"abbr\":\"l\"},{\"type\":\"MainTemplate\",\"description\":\"用于生成核心模板文件\",\"groupKey\":\"mainTemplate\",\"groupName\":\"核心模板\",\"models\":[{\"fieldName\":\"author\",\"type\":\"String\",\"description\":\"作者注释\",\"defaultValue\":\"yefeng\",\"abbr\":\"a\"},{\"fieldName\":\"outputText\",\"type\":\"String\",\"description\":\"输出信息\",\"defaultValue\":\"yefeng = \",\"abbr\":\"o\"}],\"condition\":\"loop\"}]}', '/generator_dist/1777702612496613378/GMGgybAW-acm-template-pro-generator-dist.zip', 0, 1777702612496613378, '2025-04-18 12:54:40', '2025-04-18 12:54:40', 0);
INSERT INTO `generator` VALUES (4, '鱼皮用户中心', '鱼皮用户中心项目生成器', 'com.yefeng', '1.0', 'yefeng', '[\"Java\", \"前端\"]', 'https://pic.yupi.icu/1/_r1_c1c15-79cdecf24aed.jpg', '{}', '{\"models\":[{\"fieldName\":\"needGit\",\"type\":\"boolean\",\"description\":\"是否生成 .gitignore 文件\",\"defaultValue\":true,\"abbr\":\"ng\"},{\"fieldName\":\"loop\",\"type\":\"boolean\",\"description\":\"是否生成循环\",\"defaultValue\":false,\"abbr\":\"l\"},{\"type\":\"MainTemplate\",\"description\":\"用于生成核心模板文件\",\"groupKey\":\"mainTemplate\",\"groupName\":\"核心模板\",\"models\":[{\"fieldName\":\"author\",\"type\":\"String\",\"description\":\"作者注释\",\"defaultValue\":\"yefeng\",\"abbr\":\"a\"},{\"fieldName\":\"outputText\",\"type\":\"String\",\"description\":\"输出信息\",\"defaultValue\":\"yefeng = \",\"abbr\":\"o\"}],\"condition\":\"loop\"}]}', '/generator_dist/1777702612496613378/GMGgybAW-acm-template-pro-generator-dist.zip', 0, 1777702612496613378, '2025-04-18 12:54:40', '2025-04-18 12:54:40', 0);
INSERT INTO `generator` VALUES (5, '鱼皮API开放平台', '鱼皮API开放平台项目生成器', 'com.yefeng', '1.0', 'yefeng', '[\"Java\", \"前端\"]', 'https://pic.yupi.icu/1/_r1_c0709-8e80689ac1da.jpg', '{}', '{\"models\":[{\"fieldName\":\"needGit\",\"type\":\"boolean\",\"description\":\"是否生成 .gitignore 文件\",\"defaultValue\":true,\"abbr\":\"ng\"},{\"fieldName\":\"loop\",\"type\":\"boolean\",\"description\":\"是否生成循环\",\"defaultValue\":false,\"abbr\":\"l\"},{\"type\":\"MainTemplate\",\"description\":\"用于生成核心模板文件\",\"groupKey\":\"mainTemplate\",\"groupName\":\"核心模板\",\"models\":[{\"fieldName\":\"author\",\"type\":\"String\",\"description\":\"作者注释\",\"defaultValue\":\"yefeng\",\"abbr\":\"a\"},{\"fieldName\":\"outputText\",\"type\":\"String\",\"description\":\"输出信息\",\"defaultValue\":\"yefeng = \",\"abbr\":\"o\"}],\"condition\":\"loop\"}]}', NULL, 0, 1777702612496613378, '2024-05-15 22:44:35', '2024-05-15 22:44:35', 0);
INSERT INTO `generator` VALUES (6, 'Code', 'Genx', 'com.yefeng', '1.0', 'yefeng', '[\"Go\"]', 'https://yefeng-1308017062.cos.ap-guangzhou.myqcloud.com/generator_picture/1777702612496613378/Ijfuqusc-2022-03-10 152719.jpg', '{}', '{\"models\":[{\"fieldName\":\"needGit\",\"type\":\"boolean\",\"description\":\"是否生成 .gitignore 文件\",\"defaultValue\":true,\"abbr\":\"ng\"},{\"fieldName\":\"loop\",\"type\":\"boolean\",\"description\":\"是否生成循环\",\"defaultValue\":false,\"abbr\":\"l\"},{\"type\":\"MainTemplate\",\"description\":\"用于生成核心模板文件\",\"groupKey\":\"mainTemplate\",\"groupName\":\"核心模板\",\"models\":[{\"fieldName\":\"author\",\"type\":\"String\",\"description\":\"作者注释\",\"defaultValue\":\"yefeng\",\"abbr\":\"a\"},{\"fieldName\":\"outputText\",\"type\":\"String\",\"description\":\"输出信息\",\"defaultValue\":\"yefeng = \",\"abbr\":\"o\"}],\"condition\":\"loop\"}]}', '/generator_dist/1777702612496613378/hnyOTqBr-yf.txt', 0, 1777702612496613378, '2024-05-15 22:44:35', '2024-05-15 22:44:35', 0);
INSERT INTO `generator` VALUES (7, '瑞幸咖啡', '瑞幸咖啡', 'com.yefeng', '1.2', 'yefeng', '[\"Java\"]', 'https://yefeng-1308017062.cos.ap-guangzhou.myqcloud.com/generator_picture/1777702612496613378/cSePXgAU-1712061208890.jpg', '{}', '{\"models\":[{\"fieldName\":\"needGit\",\"type\":\"boolean\",\"description\":\"是否生成 .gitignore 文件\",\"defaultValue\":true,\"abbr\":\"ng\"},{\"fieldName\":\"loop\",\"type\":\"boolean\",\"description\":\"是否生成循环\",\"defaultValue\":false,\"abbr\":\"l\"},{\"type\":\"MainTemplate\",\"description\":\"用于生成核心模板文件\",\"groupKey\":\"mainTemplate\",\"groupName\":\"核心模板\",\"models\":[{\"fieldName\":\"author\",\"type\":\"String\",\"description\":\"作者注释\",\"defaultValue\":\"yefeng\",\"abbr\":\"a\"},{\"fieldName\":\"outputText\",\"type\":\"String\",\"description\":\"输出信息\",\"defaultValue\":\"yefeng = \",\"abbr\":\"o\"}],\"condition\":\"loop\"}]}', '/generator_dist/1777702612496613378/0I1MAYkf-新建 文本文档.txt', 0, 1777702612496613378, '2024-05-15 22:44:35', '2024-05-15 22:44:35', 0);


-- 模拟 代码生成器数据
