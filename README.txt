This repository server as backend service for EMS_frontEND code.
The database used to develop is Oracle 12c
Data usedis as part of Oracle sample Data.

Sample Database Link:
https://www.oracletutorial.com/getting-started/oracle-sample-database/

Kinldy create below tables for user login details:


create table user_details_login ( user_id number(8) not null, email_id varchar2(35) not null, emp_id varchar2(35) not null, last_login date);
create table user_pass_login (user_id number(8), pass_key varchar2 (25));
create table user_session(user_id number(8), token varchar2(35), session_start_time date);

alter table user_details_login add primary key (user_id);





alter table user_pass_login add foreign key (user_id) references user_details_login(user_id)
alter table user_session add foreign key (user_id) references user_details_login(user_id)

commit;


INSERT
----------------------------------------------------------------
insert into user_details_login values (1,'test@abc','v123',null)
insert into user_pass_login values (1,'admin')


 Below is the package which was created :
 
 Package Spec:
 create or replace 
package EMP_PKG
  AS
  PROCEDURE GET_ALL_EMP(p_recordset OUT sys_refcursor);
  PROCEDURE GET_EMP_ID(i_employeeID IN NUMBER,
                      p_recordset OUT sys_refcursor);
  PROCEDURE GET_ORDER_DETAILS(i_orderid IN NUMBER,
                            p_recordset OUT sys_refcursor);
                            
  PROCEDURE VALIDATE_USER(i_pass IN VARCHAR2,i_user_id IN number, 
                            p_token OUT varchar2);
  END EMP_PKG;
  
 --------------------------------------------------------------------
 Package BODY:
 create or replace 
package body EMP_PKG
AS
PROCEDURE GET_ALL_EMP(p_recordset OUT sys_refcursor)
IS
BEGIN
  OPEN p_recordset FOR
  select e.employee_id,
          e.first_name,
          e.last_name,
          e.email,
          e.phone,
          e.job_title,ee.last_name||','||ee.first_name,ee.employee_id
  from employees e 
        join employees ee 
        on e.manager_id = ee.employee_id;
END;

PROCEDURE GET_EMP_ID(i_employeeID IN NUMBER,
                      p_recordset OUT sys_refcursor)
IS
BEGIN
  OPEN p_recordset FOR
    select e.employee_id,
          e.first_name,
          e.last_name,
          e.email,
          e.phone,
          e.job_title,ee.last_name||','||ee.first_name,ee.employee_id
    from employees e 
          join employees ee 
          on e.manager_id = ee.employee_id
          where e.employee_id = i_employeeID;
END;

PROCEDURE GET_ORDER_DETAILS(i_orderid IN NUMBER,
                            p_recordset OUT sys_refcursor)
IS
BEGIN
  OPEN p_recordset FOR 
      select oe.order_id,
              oe.item_id,
              p.product_name,
              oe.quantity,
              oe.unit_price 
          from order_items oe 
                join products p 
                on oe.product_id = p.product_id
                where oe.order_id = i_orderid;
END;


PROCEDURE VALIDATE_USER(i_pass IN VARCHAR2,i_user_id IN number, 
                            p_token OUT varchar2)
IS
count_user number;
BEGIN
 select count(user_id) into count_user 
    from vrajnees.user_pass_login 
      where user_id = i_user_id and pass_key = i_pass;
      
  IF count_user > 0
  THEN
    SELECT DBMS_RANDOM.STRING('A', 20) into p_token FROM DUAL;
       
    MERGE into user_session aa 
      using (select i_user_id as user_id from dual) b 
        on (aa.user_id = b.user_id) 
        when matched then
          update set session_start_time = sysdate,token = p_token
        when not matched then
        insert(user_id,token,session_start_time) values (i_user_id,p_token ,sysdate);
  END IF;
END;




END EMP_PKG;
