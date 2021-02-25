-- 내 스키마
DROP SCHEMA IF EXISTS native_jdbc2;

-- 내 스키마
CREATE SCHEMA native_jdbc2;

-- 직책
CREATE TABLE native_jdbc2.Title (
	tno   INT(10)     NOT NULL COMMENT '직책코드', -- 직책코드
	tname VARCHAR(20) NULL     COMMENT '직책명' -- 직책명
)
COMMENT '직책';

-- 직책
ALTER TABLE native_jdbc2.Title
	ADD CONSTRAINT PK_Title -- 직책 기본키
		PRIMARY KEY (
			tno -- 직책코드
		);

-- 부서
CREATE TABLE native_jdbc2.Department (
	deptno   INT(10)     NOT NULL COMMENT '부서번호', -- 부서번호
	deptname VARCHAR(20) NULL     COMMENT '부서명', -- 부서명
	floor    INT(10)     NULL     COMMENT '위치' -- 위치
)
COMMENT '부서';

-- 부서
ALTER TABLE native_jdbc2.Department
	ADD CONSTRAINT PK_Department -- 부서 기본키
		PRIMARY KEY (
			deptno -- 부서번호
		);

-- 사원
CREATE TABLE native_jdbc2.Employee (
	empno   INT(10)     NOT NULL COMMENT '사원번호', -- 사원번호
	empname VARCHAR(20) NULL     COMMENT '사원명', -- 사원명
	tno     INT(10)     NULL     COMMENT '직책', -- 직책
	manager INT(10)     NULL     COMMENT '직속상사', -- 직속상사
	salary  INT(10)     NULL     COMMENT '급여', -- 급여
	deptno  INT(10)     NULL     COMMENT '부서' -- 부서
)
COMMENT '사원';

-- 사원
ALTER TABLE native_jdbc2.Employee
	ADD CONSTRAINT PK_Employee -- 사원 기본키
		PRIMARY KEY (
			empno -- 사원번호
		);

-- 사원
ALTER TABLE native_jdbc2.Employee
	ADD CONSTRAINT FK_Title_TO_Employee -- 직책 -> 사원
		FOREIGN KEY (
			tno -- 직책
		)
		REFERENCES native_jdbc2.Title ( -- 직책
			tno -- 직책코드
		);

-- 사원
ALTER TABLE native_jdbc2.Employee
	ADD CONSTRAINT FK_Employee_TO_Employee -- 사원 -> 사원
		FOREIGN KEY (
			manager -- 직속상사
		)
		REFERENCES native_jdbc2.Employee ( -- 사원
			empno -- 사원번호
		);

-- 사원
ALTER TABLE native_jdbc2.Employee
	ADD CONSTRAINT FK_Department_TO_Employee -- 부서 -> 사원
		FOREIGN KEY (
			deptno -- 부서
		)
		REFERENCES native_jdbc2.Department ( -- 부서
			deptno -- 부서번호
		);
	
	
	-- 학생관리
CREATE TABLE native_jdbc2.Student(
	stdNo   INT(10)     NOT NULL COMMENT '학번', -- 학번
	stdName VARCHAR(10) NULL     COMMENT '학생이름', -- 학생이름
	kor     INT(10)     NULL     COMMENT '국어', -- 국어
	eng     INT(10)     NULL     COMMENT '영어', -- 영어
	math    INT(10)     NULL     COMMENT '수학' -- 수학
)
COMMENT '학생관리';

-- 학생관리
ALTER TABLE native_jdbc2.Student
	ADD CONSTRAINT PK_student -- 학생관리 기본키
		PRIMARY KEY (
			stdNo -- 학번
		);
	
		