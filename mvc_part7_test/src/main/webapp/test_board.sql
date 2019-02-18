  create table test_board(
	bno int primary key auto_increment,
    title varchar(200) not null,
    content text not null,         
    writer varchar(50) not null,
    regdate timestamp not null default now(),
    viewcnt int default 0
);


CREATE TABLE test_member(
  userid VARCHAR(50) NOT NULL,
  userpw VARCHAR(45) NOT NULL,
  username VARCHAR(45) NOT NULL,
  email VARCHAR(45) NULL,
  regdate TIMESTAMP NOT NULL default now(),
  updatedate TIMESTAMP NOT NULL default now()
);

DESC tbl_user;

select * from tbl_user;

ALTER table test_board ADD COLUMN uno int default 1 AFTER viewcnt;

ALTER table test_board add constraint fk_user_uno foreign key(uno) references tbl_user(uno);