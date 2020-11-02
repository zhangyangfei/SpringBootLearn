/*用户表*/
CREATE TABLE t_user (
    id INT(12) NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    sex INT(1) NOT NULL,
    note VARCHAR(200) NULL,
    PRIMARY KEY (id)
)
COMMENT='用户表'
DEFAULT CHARACTER SET = utf8;