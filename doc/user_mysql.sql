
create table if not exists `t_user`
(
    `id`           bigint  not null auto_increment primary key ,
    `nick_name`    varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `user_account` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `user_pwd`     varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `gender`       varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `age`          int                                     DEFAULT NULL,
    `phone`        varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `email`        varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `admin_flag`   varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `create_time`  datetime                                DEFAULT NULL,
    `update_time`  datetime                                DEFAULT NULL,
    `delete_flag`  tinyint NOT NULL                        DEFAULT '0'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;