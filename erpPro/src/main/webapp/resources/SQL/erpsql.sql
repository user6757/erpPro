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

ALTER TABLE meetingroom modify mrTime int;

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

-- ÷������ ������ ������ ���̺� ����
CREATE TABLE erpFileUpload (
    fileName VARCHAR2(150), -- /2022/08/01/asdjlfkasjfd_���.jpg
    regDate DATE DEFAULT SYSDATE,
    bNo NUMBER(10) NOT NULL
);


ALTER TABLE freeboard DROP CONSTRAINT seq;

ALTER TABLE erpFileUpload
ADD CONSTRAINT pkFileName
PRIMARY KEY (fileName);

ALTER TABLE erpFileUpload
ADD CONSTRAINT fkFileUpload
FOREIGN KEY (bno)
REFERENCES freeboard (seq)
ON DELETE CASCADE;

commit;

drop table erpFileUpload;

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
select * from erpFileUpload;

CREATE SEQUENCE meetingroom_mrNo --�������̸� WDRL_SEQ
INCREMENT BY 1 --�������� 1
START WITH 1 --���ۼ��� 1
MINVALUE 1 --�ּҰ� 1
MAXVALUE 9999999999 --�ִ밪 
NOCYCLE --������������
NOCACHE; --�޸𸮿� �������� �̸��Ҵ�

CREATE SEQUENCE freeboard_seq --�������̸� WDRL_SEQ
INCREMENT BY 1 --�������� 1
START WITH 1 --���ۼ��� 1
MINVALUE 1 --�ּҰ� 1
MAXVALUE 9999999999 --�ִ밪 
NOCYCLE --������������
NOCACHE; --�޸𸮿� �������� �̸��Ҵ�

CREATE SEQUENCE qnaboard_seq --�������̸� WDRL_SEQ
INCREMENT BY 1 --�������� 1
START WITH 1 --���ۼ��� 1
MINVALUE 1 --�ּҰ� 1
MAXVALUE 9999999999 --�ִ밪 
NOCYCLE --������������
NOCACHE; --�޸𸮿� �������� �̸��Ҵ�