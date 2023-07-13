
-- 新建模式
CREATE SCHEMA "fish" AUTHORIZATION "postgres";

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

-- ----------------------------
-- Records of t_user
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_user
-- ----------------------------
ALTER TABLE "fish"."t_user"
    ADD CONSTRAINT "t_user_pkey" PRIMARY KEY ("id");
