-- FishCloud v0.1.0
-- Copyright (c) 2022-present, dayang.

-- 类型名serial和serial4是等效的： 两个都创建integer列。类型名bigserial和serial8也一样，只不过它们创建一个 bigint列。
-- 如果你预计在表的生存期中使用的标识符数目超过 2147483648 个，那么你应该使用bigserial。
-- 类型名smallserial和serial2也以相同方式工作，只不过它们创建一个smallint列。

-- 新建模式（如果不存在）
-- create schema if not exists "fish" authorization "postgres";

-- 创建用户表
drop table if exists "t_user";
create table "t_user"
(
    "id"           serial4      not null primary key,
    "nick_name"    varchar(255),
    "user_account" varchar(255),
    "user_pwd"     varchar(255),
    "gender"       int2,
    "age"          int2,
    "phone"        varchar(20),
    "email"        varchar(100),
    "admin_flag"   int2         not null default 0,
    "create_time"  timestamp(3) not null default current_timestamp,
    "update_time"  timestamp(3) not null default current_timestamp,
    "delete_flag"  bool         not null default false
)
;

comment on column "t_user"."id" is '主键';
comment on column "t_user"."nick_name" is '昵称';
comment on column "t_user"."user_account" is '账户';
comment on column "t_user"."user_pwd" is '密码';
comment on column "t_user"."gender" is '性别：0---女，1---男';
comment on column "t_user"."age" is '年龄';
comment on column "t_user"."phone" is '手机号';
comment on column "t_user"."email" is '邮箱';
comment on column "t_user"."admin_flag" is '0-普通用户，1-管理员';
comment on column "t_user"."create_time" is '创建时间';
comment on column "t_user"."update_time" is '更新时间';
comment on column "t_user"."delete_flag" is '是否逻辑删除';
comment on table "t_user" is '用户表';

-- ----------------------------
-- Records of t_user
-- ----------------------------
insert into "t_user"
values (default, 'zhangSan', '19542', '123456', 0, 10, '18855558888', '123@qq.com', 0, default,
        default, default);
insert into "t_user"
values (default, 'liSi', '222580', 'qwer1234', 0, 11, '19925415871', '456@163.com', 0, '2023-07-13 22:28:21.167398',
        '2023-07-13 22:28:21.167398', 'yes');

