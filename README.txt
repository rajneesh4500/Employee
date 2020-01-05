This repository server as backend service for EMS_frontEND code.
The database used to develop is Oracle 12c
Data usedis as part of Oracle sample Data.
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
END EMP_PKG;
