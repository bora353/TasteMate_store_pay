-- menu 테이블 생성
-- drop table munu;
-- drop table menu_seq;

CREATE TABLE MENU
(
    MENU_IDX NUMBER NOT NULL
    , STORE_IDX NUMBER NOT NULL
    , FOOD_NAME VARCHAR2(100 BYTE) NOT NULL
    , PRICE NUMBER NOT NULL
    , CONSTRAINT MENU_PK PRIMARY KEY
    (
     MENU_IDX
        )
);

CREATE SEQUENCE munu_seq;

INSERT INTO MENU(MENU_IDX, STORE_IDX, FOOD_NAME, PRICE)
VALUES (munu_seq.NEXTVAL, 1, '국밥', 9000);

INSERT INTO MENU(MENU_IDX, STORE_IDX, FOOD_NAME, PRICE)
VALUES (munu_seq.NEXTVAL, 1, '국밥(특)', 11000);

INSERT INTO MENU(MENU_IDX, STORE_IDX, FOOD_NAME, PRICE)
VALUES (munu_seq.NEXTVAL, 1, '정식', 14000);

INSERT INTO MENU(MENU_IDX, STORE_IDX, FOOD_NAME, PRICE)
VALUES (munu_seq.NEXTVAL, 1, '술국', 18000);


COMMIT;
------------------------


--drop table store;
--drop sequence store_seq;

CREATE TABLE STORE
(
    STORE_IDX NUMBER NOT NULL
    , USER_IDX NUMBER NOT NULL
    , STORE_NAME VARCHAR2(100 BYTE) NOT NULL
    , CATEGORY1 VARCHAR2(50 BYTE) NOT NULL
    , STORE_ADDRESS VARCHAR2(200 BYTE) NOT NULL
    , STORE_API VARCHAR2(200 BYTE)
    , PHONE_NUMBER VARCHAR2(100 BYTE)
    , STORE_COUNT NUMBER DEFAULT 0
    , FILENAME VARCHAR2(200 BYTE)
    , ORI_FILENAME VARCHAR2(200 BYTE)
    , CONSTRAINT TABLE1_PK PRIMARY KEY
    (
     STORE_IDX
        )
);


CREATE SEQUENCE store_seq;

INSERT INTO STORE (STORE_IDX, USER_IDX, STORE_NAME, CATEGORY1,
                   STORE_ADDRESS, STORE_API, PHONE_NUMBER, STORE_COUNT,
                   FILENAME, ORI_FILENAME)
VALUES (store_seq.NEXTVAL, 1, '농민백암순대', '한식',
        '서울 강남구 역삼로3길 20-4', '...', '02-501-2772', 0,
        'tastemate.jpg', 'tastemate.jpg');

INSERT INTO STORE (STORE_IDX, USER_IDX, STORE_NAME, CATEGORY1,
                   STORE_ADDRESS, STORE_API, PHONE_NUMBER, STORE_COUNT,
                   FILENAME, ORI_FILENAME)
VALUES (store_seq.NEXTVAL, 1, '청목', '한식',
        '서울 강남구 테헤란로 124 삼원타워', '...', '02-557-5534', 0,
        'tastemate.jpg', 'tastemate.jpg');

INSERT INTO STORE (STORE_IDX, USER_IDX, STORE_NAME, CATEGORY1,
                   STORE_ADDRESS, STORE_API, PHONE_NUMBER, STORE_COUNT,
                   FILENAME, ORI_FILENAME)
VALUES (store_seq.NEXTVAL, 1, '슈퍼심플', '양식',
        '서울 강남구 테헤란로8길 28 1층', '...', '0507-1352-4838', 0,
        'tastemate.jpg', 'tastemate.jpg');

INSERT INTO STORE (STORE_IDX, USER_IDX, STORE_NAME, CATEGORY1,
                   STORE_ADDRESS, STORE_API, PHONE_NUMBER, STORE_COUNT,
                   FILENAME, ORI_FILENAME)
VALUES (store_seq.NEXTVAL, 1, '먼키 강남역점', '푸드코트',
        '서울 강남구 테헤란로 123 여삼빌딩 B1층', '...', '0507-1477-7117', 0,
        'tastemate.jpg', 'tastemate.jpg');

INSERT INTO STORE (STORE_IDX, USER_IDX, STORE_NAME, CATEGORY1,
                   STORE_ADDRESS, STORE_API, PHONE_NUMBER, STORE_COUNT,
                   FILENAME, ORI_FILENAME)
VALUES (store_seq.NEXTVAL, 1, '오미라식당', '퓨전',
        '서울 강남구 테헤란로 124 삼원타워 지하1층', '...', '0507-1384-0510', 0,
        'tastemate.jpg', 'tastemate.jpg');

COMMIT;

select * from store;
