-- tbl_member 
CREATE TABLE `mydata`.`tbl_member` (
  `userid` VARCHAR(50) NOT NULL,
  `userpw` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `regdate` DATE NULL,
  `updatedate` DATE NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SELECT * FROM tbl_member;

DESC tbl_member;

-- tbl_board
CREATE TABLE tbl_board(
	bno int primary key auto_increment,
    title varchar(200) not null,
    content text null,
    writer varchar(50) not null,
    regdate timestamp not null default now(),
    viewcnt int default 0
);


SELECT * FROM tbl_board;

INSERT INTO tbl_board(title,content,writer) 
select title,content,writer FROM tbl_board;

SELECT count(*) FROM tbl_board ;

SELECT bno FROM tbl_board ORDER BY bno DESC limit 10, 10;

DROP TABLE re_tbl_board;

CREATE TABLE `mydata`.`re_tbl_board` (
  `bno` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(200) NOT NULL,
  `content` TEXT NOT NULL,
  `writer` VARCHAR(45) NOT NULL,
  `origin` INT NULL DEFAULT 0,
  `depth` INT NULL DEFAULT 0,
  `seq` INT NULL DEFAULT 0,
  `regdate` TIMESTAMP NULL DEFAULT now(),
  `updatedate` TIMESTAMP NULL DEFAULT now(),
  `viewcnt` INT NULL DEFAULT 0,
  PRIMARY KEY (`bno`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

DESC re_tbl_board;

SELECT * FROM re_tbl_board;

INSERT INTO re_tbl_board(title,content,writer) 
SELECT title,content,writer FROM re_tbl_board;

UPDATE re_tbl_board SET origin = bno;

ALTER TABLE `mydata`.`re_tbl_board` 
ADD COLUMN `show` VARCHAR(45) NULL DEFAULT 'y' AFTER `viewcnt`;

UPDATE re_tbl_board SET showboard = 'n' WHERE bno= 1014;

CREATE TABLE `mydata`.`tbl_comment` (
  `cno` INT NOT NULL AUTO_INCREMENT,
  `bno` INT NOT NULL DEFAULT 0,
  `commentText` TEXT NOT NULL,
  `commentAuth` VARCHAR(45) NOT NULL,
  `regdate` TIMESTAMP NOT NULL DEFAULT now(),
  `updatedate` TIMESTAMP NOT NULL DEFAULT now(),
  PRIMARY KEY (`cno`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

ALTER TABLE tbl_comment add constraint fk_re_board
foreign key(bno) references re_tbl_board(bno);

SELECT * FROM tbl_comment;

INSERT INTO tbl_comment(bno,commentText,commentAuth)
 SELECT bno,commentText,commentAuth FROM tbl_comment;

CREATE TABLE `mydata`.`tbl_attach` (
  `fullname` VARCHAR(200) NOT NULL,
  `bno` INT NOT NULL,
  `regdate` TIMESTAMP NULL DEFAULT now(),
  INDEX `fk_board_attach_idx` (`bno` ASC) VISIBLE,
  CONSTRAINT `fk_board_attach`
    FOREIGN KEY (`bno`)
    REFERENCES `mydata`.`re_tbl_board` (`bno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SELECT * FROM tbl_attach;

DELETE FROM tbl_attach;

CREATE TABLE tbl_user(
	uno int primary key auto_increment,
    uid varchar(50) not null unique,
    upw varchar(50) not null,
    uname varchar(50) not null,
    upoint int not null default 0
);

CREATE TABLE tbl_message(
	mno int primary key auto_increment,
    targetid varchar(50) not null,
    sender varchar(50) not null,
    message text not null,
    opendate timestamp,
    senddate timestamp not null default now()
);

alter table tbl_message add constraint fk_user_targetid 
foreign key(targetid) references tbl_user(uid);

alter table tbl_message add constraint fk_user_sender 
foreign key(sender) references tbl_user(uid);

INSERT INTO tbl_user(uid,upw,uname)
VALUES('id001','id001','IRON MAN');

INSERT INTO tbl_user(uid,upw,uname)
VALUES('id002','id002','THOR');

INSERT INTO tbl_user(uid,upw,uname)
VALUES('id003','id003','DR.strange');

SELECT * FROM tbl_message;

SELECT * FROM tbl_user;



