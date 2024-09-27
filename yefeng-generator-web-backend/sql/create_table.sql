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


CREATE TABLE IF NOT EXISTS `coupon`
(
    `id`                  bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '优惠券id',
    `name`                varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '优惠券名称，可以和活动名称保持一致',
    `type`                tinyint                                                       NOT NULL DEFAULT '1' COMMENT '优惠券类型，1：普通券。目前就一种，保留字段',
    `discount_type`       tinyint                                                       NOT NULL COMMENT '折扣类型，1：每满减，2：折扣，3：无门槛，4：普通满减',
    `specific`            bit(1)                                                        NOT NULL DEFAULT b'0' COMMENT '是否限定作用范围，false：不限定，true：限定。默认false',
    `discount_value`      int                                                           NOT NULL DEFAULT '1' COMMENT '折扣值，如果是满减则存满减金额，如果是折扣，则存折扣率，8折就是存80',
    `threshold_amount`    int                                                           NOT NULL DEFAULT '0' COMMENT '使用门槛，0：表示无门槛，其他值：最低消费金额',
    `max_discount_amount` int                                                           NOT NULL DEFAULT '0' COMMENT '最高优惠金额，满减最大，0：表示没有限制，不为0，则表示该券有金额的限制',
    `obtain_way`          tinyint                                                       NOT NULL DEFAULT '0' COMMENT '获取方式：1：手动领取，2：兑换码',
    `issue_begin_time`    datetime                                                               DEFAULT NULL COMMENT '开始发放时间',
    `issue_end_time`      datetime                                                               DEFAULT NULL COMMENT '结束发放时间',
    `term_days`           int                                                           NOT NULL DEFAULT '0' COMMENT '优惠券有效期天数，0：表示有效期是指定有效期的',
    `term_begin_time`     datetime                                                               DEFAULT NULL COMMENT '优惠券有效期开始时间',
    `term_end_time`       datetime                                                               DEFAULT NULL COMMENT '优惠券有效期结束时间',
    `status`              tinyint                                                                DEFAULT '1' COMMENT '优惠券配置状态，1：待发放，2：未开始   3：进行中，4：已结束，5：暂停',
    `total_num`           int                                                           NOT NULL DEFAULT '0' COMMENT '总数量，不超过5000',
    `issue_num`           int                                                           NOT NULL DEFAULT '0' COMMENT '已发行数量，用于判断是否超发',
    `used_num`            int                                                           NOT NULL DEFAULT '0' COMMENT '已使用数量',
    `user_limit`          int                                                           NOT NULL DEFAULT '1' COMMENT '每个人限领的数量，默认1',
    `ext_param`           json                                                                   DEFAULT NULL COMMENT '拓展参数字段，保留字段',
    `create_time`         datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`         datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `creater`             bigint                                                        NOT NULL COMMENT '创建人',
    `updater`             bigint                                                        NOT NULL COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE
)  COLLATE = utf8mb4_0900_ai_ci COMMENT ='优惠券的规则信息';

CREATE TABLE IF NOT EXISTS `coupon_scope`
(
    `id`        bigint  NOT NULL AUTO_INCREMENT,
    `type`      tinyint NOT NULL COMMENT '范围限定类型：1-分类，2-课程，等等',
    `coupon_id` bigint  NOT NULL COMMENT '优惠券id',
    `biz_id`    bigint  NOT NULL COMMENT '优惠券作用范围的业务id，例如分类id、课程id',
    PRIMARY KEY (`id`),
    KEY `idx_coupon` (`coupon_id`)
)  COLLATE = utf8mb4_0900_ai_ci COMMENT ='优惠券作用范围信息';

-- 导出  表 tj_promotion.exchange_code 结构
CREATE TABLE IF NOT EXISTS `exchange_code`
(
    `id`                 int                                                          NOT NULL COMMENT '兑换码id',
    `code`               varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '兑换码',
    `status`             tinyint                                                      NOT NULL DEFAULT '1' COMMENT '兑换码状态， 1：待兑换，2：已兑换，3：兑换活动已结束',
    `user_id`            bigint                                                       NOT NULL DEFAULT '0' COMMENT '兑换人',
    `type`               tinyint                                                      NOT NULL DEFAULT '1' COMMENT '兑换类型，1：优惠券，以后再添加其它类型',
    `exchange_target_id` bigint                                                       NOT NULL DEFAULT '0' COMMENT '兑换码目标id，例如兑换优惠券，该id则是优惠券的配置id',
    `create_time`        datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `expired_time`       datetime                                                     NOT NULL COMMENT '兑换码过期时间',
    `update_time`        datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `index_status` (`status`) USING BTREE,
    KEY `index_config_id` (`exchange_target_id`) USING BTREE
)  COLLATE = utf8mb4_0900_ai_ci COMMENT ='兑换码';


CREATE TABLE IF NOT EXISTS `promotion`
(
    `id`          bigint                                                       NOT NULL COMMENT '促销活动id',
    `name`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '活动名称',
    `type`        tinyint                                                      NOT NULL DEFAULT '1' COMMENT '促销活动类型：1-优惠券，2-分销',
    `hot`         tinyint                                                      NOT NULL DEFAULT '0' COMMENT '是否是热门活动：true或false，默认false',
    `begin_time`  datetime                                                     NOT NULL COMMENT '活动开始时间',
    `end_time`    datetime                                                     NOT NULL COMMENT '活动结束时间',
    `create_time` datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `creater`     bigint                                                       NOT NULL COMMENT '创建人',
    `updater`     bigint                                                       NOT NULL COMMENT '更新人',
    PRIMARY KEY (`id`) USING BTREE
)  COLLATE = utf8mb4_0900_ai_ci COMMENT ='促销活动，形式多种多样，例如：优惠券';


