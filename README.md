# Cat Gym - CS

### Notice

## Patch List
    ● memberValidator 버그 (add)
    ● 트레이너 등록시 pt회원 미기입시 오류 발생
        (null 가능으로 바꾸기)
    ● 질문 통일

    /board/add
    id 기입하면 이름으로 출력됨

    /trainer/detail
    PT회원 ID목록 한명만 출력됨
    /trainer/update
    계약 종료일(시작일이 나옴),
    현재 PT회원 ...(종료일이 나옴)
    /trainer/delete, add
    삭제된 번호가 다시 생성됨

    /pay/...
    잔 버그 많음

## Patch Note

### Create Table

```
create table gym_board(
    no int not null,
    title varchar(255) not null,
    content text not null,
    writer varchar(30) not null,
    cdt datetime default now(),
    vw_cnt int default 0,
    like_cnt int default 0
);

alter table gym_board
    add constraint gym_board_pk primary key(no);

alter table gym_board
    modify column no int not null auto_increment;
```

```
create table gym_member(
    no int not null,
    name varchar(30) not null,
    email varchar(50) not null,
    password varchar(50) not null,
    photo varchar(255),
    tel varchar(20),
    cdt datetime default now()
);

alter table gym_member
    add constraint gym_member_pk primary key(no);

alter table gym_member
    modify column no int not null auto_increment;

alter table gym_member
    add constraint gym_member_uk unique (email);
```

<!-- ```
create table gym_pay(
    no int not null,
    name varchar(30) not null,
    select int not null,
    join varchar(10) not null,
    rental varchar(10) not null,
    locker varchar(10) not null,
    sdt date not null,
    edt date not null
);

alter table gym_pay
    add constraint gym_pay_pk primary key(no);

alter table gym_pay
    modify column no int not null auto_increment;
``` --> 
