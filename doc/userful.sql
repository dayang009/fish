-- 将已有的序列和主键ID关联的SQL语句

alter table fish.t_user
    alter column id set default nextval('fish.t_user_id_seq')