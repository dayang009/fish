-- FishCloud v0.1.0
-- Copyright (c) 2022-present, dayang.

-- 类型名serial和serial4是等效的： 两个都创建integer列。类型名bigserial和serial8也一样，只不过它们创建一个 bigint列。
-- 如果你预计在表的生存期中使用的标识符数目超过 2147483648 个，那么你应该使用bigserial。
-- 类型名smallserial和serial2也以相同方式工作，只不过它们创建一个smallint列。

-- 新建模式（如果不存在）
CREATE SCHEMA IF NOT EXISTS "fish" AUTHORIZATION "postgres";

-- 创建用户表
DROP TABLE IF EXISTS "fish"."t_user";
CREATE TABLE "fish"."t_user"
(
    "id"           serial4      NOT NULL,
    "nick_name"    varchar(255) COLLATE "pg_catalog"."default",
    "user_account" varchar(255) COLLATE "pg_catalog"."default",
    "user_pwd"     varchar(255) COLLATE "pg_catalog"."default",
    "gender"       int2,
    "age"          int2,
    "phone"        varchar(20) COLLATE "pg_catalog"."default",
    "email"        varchar(100) COLLATE "pg_catalog"."default",
    "admin_flag"   int2         NOT NULL DEFAULT 0,
    "create_time"  timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "update_time"  timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "delete_flag"  bool         NOT NULL DEFAULT false
)
;
COMMENT ON COLUMN "fish"."t_user"."id" IS '主键';
COMMENT ON COLUMN "fish"."t_user"."nick_name" IS '昵称';
COMMENT ON COLUMN "fish"."t_user"."user_account" IS '账户';
COMMENT ON COLUMN "fish"."t_user"."user_pwd" IS '密码';
COMMENT ON COLUMN "fish"."t_user"."gender" IS '性别：0---女，1---男';
COMMENT ON COLUMN "fish"."t_user"."age" IS '年龄';
COMMENT ON COLUMN "fish"."t_user"."phone" IS '手机号';
COMMENT ON COLUMN "fish"."t_user"."email" IS '邮箱';
COMMENT ON COLUMN "fish"."t_user"."admin_flag" IS '0-普通用户，1-管理员';
COMMENT ON COLUMN "fish"."t_user"."create_time" IS '创建时间';
COMMENT ON COLUMN "fish"."t_user"."update_time" IS '更新时间';
COMMENT ON COLUMN "fish"."t_user"."delete_flag" IS '是否逻辑删除';
COMMENT ON TABLE "fish"."t_user" IS '用户表';

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO "fish"."t_user"
VALUES (default, 'zhangSan', '19542', '123456', 0, 10, '18855558888', '123@qq.com', 0, default,
        default, default);
INSERT INTO "fish"."t_user"
VALUES (default, 'liSi', '222580', 'qwer1234', 0, 11, '19925415871', '456@163.com', 0, '2023-07-13 22:28:21.167398',
        '2023-07-13 22:28:21.167398', 'f');

-- ----------------------------
-- Primary Key structure for table t_user
-- ----------------------------
ALTER TABLE "fish"."t_user"
    ADD CONSTRAINT "t_user_pkey" PRIMARY KEY ("id");
