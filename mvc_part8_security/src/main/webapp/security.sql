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

SELECT * FROM security_member_auth;

SELECT 
mem.uid, upw, uname, regdate, updatedate, auth 
FROM 
security_member mem 
LEFT OUTER JOIN 
security_member_auth auth 
on mem.uid = auth.uid 
WHERE mem.uid = 'user0';




