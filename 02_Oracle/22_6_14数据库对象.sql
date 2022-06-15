create table myTestTable as
select rownum as id,
       to_char(sysdate + rownum/24/3600, 'yyyy-mm-dd hh24:mi:ss') as inc_datetime,
       trunc(dbms_random.value(0, 100)) as random_id,
       dbms_random.string('x', 20) random_string
from dual
    connect by level <= 1000000;