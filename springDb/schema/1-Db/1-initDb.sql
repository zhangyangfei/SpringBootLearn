/*创建数据库 SpringBootLearn*/
CREATE SCHEMA SpringBootLearn; 

/*创建用户*/
CREATE USER 'springdb'@'%' IDENTIFIED BY 'springdb';

/*授予所有的权限,但不能给其它用户授权*/
GRANT ALL ON *.* TO 'springdb'@'%';

/*设置时区（在mysql中设置时区，默认为SYSTEM）*/
set global time_zone='+8:00';