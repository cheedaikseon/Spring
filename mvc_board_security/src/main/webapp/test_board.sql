  create table security_board(
	bno int primary key auto_increment,
    title varchar(200) not null,
    content text not null,         
    writer varchar(50) not null,
    regdate timestamp not null default now(),
    viewcnt int default 0,
    constraint fk_security_board_fk foreign key(uid)
	references security_member(uid)
);

ALTER TABLE security_board add column uid varchar(50) not null
default 'user1' after viewcnt;

ALTER TABLE security_board add constraint fk_security_board_fk foreign key(uid)
references security_member(uid);

DESC security_board;

SELECT * FROM security_board;


CREATE TABLE security_member(
	uno int primary key auto_increment,
	uid varchar(50) not null unique,
	upw varchar(100) not null,
	uname varchar(100) not null,
	regdate timestamp default now(),
	updatedate timestamp default now()
);

CREATE TABLE security_member_auth(
	uid varchar(50) not null,
	auth varchar(50) not null,
	constraint fk_member_auth foreign key(uid) references security_member(uid)
);


SELECT * FROM security_member;