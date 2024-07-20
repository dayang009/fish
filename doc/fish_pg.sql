-- FishCloud v0.1.0
-- Copyright (c) 2022-present, dayang.

-- 类型名serial和serial4是等效的： 两个都创建integer列。类型名bigserial和serial8也一样，只不过它们创建一个 bigint列。
-- 如果你预计在表的生存期中使用的标识符数目超过 2147483648 个，那么你应该使用bigserial。
-- 类型名smallserial和serial2也以相同方式工作，只不过它们创建一个smallint列。

-- 新建模式（如果不存在）
-- create schema if not exists "fish" authorization "postgres";

-- 创建用户表
create table if not exists user_info
(
    "id"            serial4 primary key,
    "nick_name"     varchar(50),
    "user_account"  varchar(50)
        constraint "uni_user_account" unique,
    "user_password" varchar(50),
    "avatar_url"    varchar(100),
    "gender"        int2,
    "age"           int2,
    "phone"         varchar(20),
    "email"         varchar(50)
        constraint "uni_email" unique,
    "admin_flag"    int2 not null default 0,
    "user_status"   int2 not null default 0,
    "planet_code"   varchar(20),
    "tags"          json,
    "create_time"   timestamp(0)  default current_timestamp,
    "update_time"   timestamp(0),
    "delete_flag"   bool not null default false
)
;

comment on column user_info."id" is '主键';
comment on column user_info."nick_name" is '昵称';
comment on column user_info."user_account" is '账户';
comment on column user_info."user_password" is '密码';
comment on column user_info."avatar_url" is '头像链接';
comment on column user_info."gender" is '性别：0---女，1---男';
comment on column user_info."age" is '年龄';
comment on column user_info."phone" is '手机号';
comment on column user_info."email" is '邮箱';
comment on column user_info."admin_flag" is '0-普通用户，1-管理员';
comment on column user_info."user_status" is '用户状态: 0-正常';
comment on column user_info."tags" is '标签Json列表';
comment on column user_info."create_time" is '创建时间';
comment on column user_info."update_time" is '更新时间';
comment on column user_info."delete_flag" is '是否逻辑删除';
comment on table user_info is '用户表';

-- ----------------------------
-- Records of t_user
-- ----------------------------
insert into user_info(nick_name, user_account, user_password, gender, age, phone, email)
values ('zhangSan', '1954254125', '123456', 0, 18, '18855558888', 'demo@qq.com');
