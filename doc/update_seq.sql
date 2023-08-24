
-- 将序列重置为当前表的最大值
select setval('t_user_id_seq', (select max(id) from t_user), true);

-- XxlJob Table
select setval('xxl_job_group_id_seq', (select max(id) from xxl_job_group), true);
select setval('xxl_job_info_id_seq', (select max(id) from xxl_job_info), true);
select setval('xxl_job_log_id_seq', (select max(id) from xxl_job_log), true);
select setval('xxl_job_log_report_id_seq', (select max(id) from xxl_job_log_report), true);
select setval('xxl_job_logglue_id_seq', (select max(id) from xxl_job_logglue), true);
select setval('xxl_job_registry_id_seq', (select max(id) from xxl_job_registry), true);
select setval('xxl_job_user_id_seq', (select max(id) from xxl_job_user), true);
