--  alt + x  &&  alt + c
SELECT * FROM tbl_user;

CREATE TABLE ban_ip(
	ip varchar(50) primary key,
	cnt int default 1,
	bandate timestamp default now()
);

INSERT INTO ban_ip(ip) VALUES('192.168.0.254');

SELECT * FROM ban_ip;

DESC re_tbl_board;

ALTER TABLE re_tbl_board ADD COLUMN uno int not null default 1 AFTER showboard;

ALTER TABLE re_tbl_board add constraint fk_re_board_uno 
FOREIGN KEY (uno) REFERENCES tbl_user(uno);

SELECT * FROM re_tbl_board WHERE uno = 2;

DESC tbl_comment;

ALTER TABLE tbl_comment ADD COLUMN uno int not null default 1 AFTER updatedate;

ALTER TABLE tbl_comment add constraint fk_comment_uno 
FOREIGN KEY (uno) REFERENCES tbl_user(uno);






