alter table diaodu.xxl_job_registry
    alter column id set default nextval('diaodu.xxl_job_registry_id_sql')