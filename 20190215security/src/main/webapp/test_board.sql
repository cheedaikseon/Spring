  create table security_board(
	bno int primary key auto_increment,
    title varchar(200) not null,
    content text not null,         
    writer varchar(50) not null,
    regdate timestamp not null default now(),
    viewcnt int default 0
);