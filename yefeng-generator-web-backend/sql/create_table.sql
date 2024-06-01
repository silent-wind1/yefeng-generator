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

-- 模拟 用户数据
INSERT INTO my_yefeng.user(id,userAccount,userPassword,userName,userAvatar,userProfile,userRole) VALUES (1,'claw','df3f595406c59c46af221924d171f30f','管理员豹警官','https://i0.hdslb.com/bfs/archive/502c3df0d2d64d7f60819e8dda97e3c1e59efdf2.jpg','豹警官：勇敢维护动物城和平，夏奇羊粉丝，甜甜圈的忠实爱好者。','admin');
INSERT INTO my_yefeng.user(id,userAccount,userPassword,userName,userAvatar,userProfile,userRole) VALUES (2,'claw2','df3f595406c59c46af221924d171f30f','档案室豹警官','https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png','豹警官：勇敢维护动物城和平，夏奇羊粉丝，甜甜圈的忠实爱好者。','user');

-- 模拟 代码生成器数据
INSERT INTO my_yefeng.generator(id,name,description,basePackage,version,author,tags,picture,fileConfig,modelConfig,distPath,status,userId) VALUES (1,'ACM 模板项目','ACM 模板项目生成器','com.dexcode','1.0','管理员豹警官','["Java"]','https://pic.yefeng.icu/1/_r0_c1851-bf115939332e.jpg','{}','{}',null,0,1);
INSERT INTO my_yefeng.generator(id,name,description,basePackage,version,author,tags,picture,fileConfig,modelConfig,distPath,status,userId) VALUES (2,'Spring Boot 初始化模板','Spring Boot 初始化模板项目生成器','com.dexcode','1.0','管理员豹警官','["Java"]','https://pic.yefeng.icu/1/_r0_c0726-7e30f8db802a.jpg','{}','{}',null,0,1);
INSERT INTO my_yefeng.generator(id,name,description,basePackage,version,author,tags,picture,fileConfig,modelConfig,distPath,status,userId) VALUES (3,'鱼皮外卖','鱼皮外卖项目生成器','com.dexcode','1.0','管理员豹警官','["Java", "前端"]','https://pic.yefeng.icu/1/_r1_c0cf7-f8e4bd865b4b.jpg','{}','{}',null,0,1);
INSERT INTO my_yefeng.generator(id,name,description,basePackage,version,author,tags,picture,fileConfig,modelConfig,distPath,status,userId) VALUES (4,'鱼皮用户中心','鱼皮用户中心项目生成器','com.dexcode','1.0','管理员豹警官','["Java", "前端"]','https://pic.yefeng.icu/1/_r1_c1c15-79cdecf24aed.jpg','{}','{}',null,0,1);
INSERT INTO my_yefeng.generator(id,name,description,basePackage,version,author,tags,picture,fileConfig,modelConfig,distPath,status,userId) VALUES (5,'鱼皮API开放平台','鱼皮API开放平台项目生成器','com.dexcode','1.0','管理员豹警官','["Java", "前端"]','https://pic.yefeng.icu/1/_r1_c0709-8e80689ac1da.jpg','{}','{}',null,0,1);
INSERT INTO my_yefeng.generator (id, name, description, basePackage, version, author, tags, picture, fileConfig, modelConfig, distPath, status, userId, createTime, updateTime, isDelete) VALUES (18, 'acm-template-pro-generator', 'ACM 示例模板生成器', 'com.yupi', '1.0', 'yupi', '["Java"]', 'https://yuzi-1256524210.cos.ap-shanghai.myqcloud.com/generator_picture/1738875515482562562/U7uDBXC3-test (1).jpg', '{"files": [{"groupKey": "git","groupName": "开源","type": "group","condition": "needGit","files": [{"inputPath": ".gitignore","outputPath": ".gitignore","type": "file","generateType": "static"},{"inputPath": "README.md","outputPath": "README.md","type": "file","generateType": "static"}]},{"inputPath": "src/com/yupi/acm/MainTemplate.java.ftl","outputPath": "src/com/yupi/acm/MainTemplate.java","type": "file","generateType": "dynamic"}]}', '{"models":[{"fieldName":"needGit","type":"boolean","description":"是否生成 .gitignore 文件","defaultValue":true},{"fieldName":"loop","type":"boolean","description":"是否生成循环","defaultValue":false,"abbr":"l"},{"type":"MainTemplate","description":"用于生成核心模板文件","groupKey":"mainTemplate","groupName":"核心模板","models":[{"fieldName":"author","type":"String","description":"作者注释","defaultValue":"yupi","abbr":"a"},{"fieldName":"outputText","type":"String","description":"输出信息","defaultValue":"sum = ","abbr":"o"}],"condition":"loop"}]}', '/generator_dist/1738875515482562562/kLbG2yGh-acm-template-pro-generator.zip', 0, 1738875515482562562, '2024-01-06 23:00:17', '2024-01-08 18:50:12', 0);