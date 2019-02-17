--  alt + x  &&  alt + c
SELECT * FROM tbl_user;

CREATE TABLE ban_ip(ip varchar(50) primary key,cnt int default 1,bandate timestamp default now());

insert into ban_ip(ip) values('132.168.0.254');

select * from ban_ip;

ALTER TABLE re_tbl_board ADD COLUMN uno int not null default 1 AFTER showboard;
ALTER TABLE re_tbl_board ADD CONSTRAINT fk_re_board_uno FOREIGN KEY(uno) REFERENCES tbl_user(uno);
desc tbl_comment;

ALTER TABLE tbl_comment ADD COLUMN uno int not null default 1 AFTER updatedate;
ALTER TABLE tbl_comment ADD CONSTRAINT fk_comment_uno FOREIGN KEY(uno) REFERENCES tbl_user(uno);