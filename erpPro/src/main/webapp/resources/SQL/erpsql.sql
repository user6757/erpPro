create table freeboard(
 seq number(5) PRIMARY KEY,
 title VARCHAR2(210),
 writer VARCHAR2(21),
 content VARCHAR2(2000),
 regdate date default sysdate,
 cnt number(5) default 0
);

ALTER TABLE freeboard ADD filename VARCHAR(100);
ALTER TABLE freeboard ADD filedate VARCHAR(300);

ALTER TABLE freeboard DROP COLUMN filename;

ALTER TABLE freeboard ADD files VARCHAR(300);

CREATE TABLE erpmember (
    account VARCHAR(50),
    password VARCHAR(150) NOT NULL,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    auth VARCHAR(20) DEFAULT 'COMMON',
    reg_date DATE DEFAULT current_timestamp,
    CONSTRAINT pk_member PRIMARY KEY (account)
);

CREATE TABLE meetingroom (
    mrNo int,
    mrUsers VARCHAR(60) NOT NULL,
    mrTime DATE NOT NULL,
    mrTitle VARCHAR(200) NOT NULL,
    mrContent VARCHAR(1000)NOT NULL,
    mrMax int,
    mrRegDate DATE,
    userId VARCHAR(100),
    fileNo number(5)
);


create table qnaboard(
 qnano number(5) PRIMARY KEY,
 title VARCHAR2(210),
 writer VARCHAR2(21),
 content VARCHAR2(2000),
 qnaregdate date default sysdate,
 qnacnt number(5) default 0
);

commit;

select * from freeboard;
select * from qnaboard;

CREATE SEQUENCE meetingroom_mrNo --시퀀스이름 WDRL_SEQ
INCREMENT BY 1 --증감숫자 1
START WITH 1 --시작숫자 1
MINVALUE 1 --최소값 1
MAXVALUE 9999999999 --최대값 
NOCYCLE --순한하지않음
NOCACHE; --메모리에 시퀀스값 미리할당

CREATE SEQUENCE freeboard_seq --시퀀스이름 WDRL_SEQ
INCREMENT BY 1 --증감숫자 1
START WITH 1 --시작숫자 1
MINVALUE 1 --최소값 1
MAXVALUE 9999999999 --최대값 
NOCYCLE --순한하지않음
NOCACHE; --메모리에 시퀀스값 미리할당

CREATE SEQUENCE qnaboard_seq --시퀀스이름 WDRL_SEQ
INCREMENT BY 1 --증감숫자 1
START WITH 1 --시작숫자 1
MINVALUE 1 --최소값 1
MAXVALUE 9999999999 --최대값 
NOCYCLE --순한하지않음
NOCACHE; --메모리에 시퀀스값 미리할당