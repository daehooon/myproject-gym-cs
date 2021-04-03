# Cat Gym - CS

### Notice
    ● 30-c-client ~ d, 31-c 까지 만들기

## Patch List

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

```
create table gym_pay(
    no int not null,
    mrn varchar(30) not null,
    mrs int not null default 0,
    nmr int not null default 0,
    rental int not null default 0,
    locker int not null default 0,
    pt int not null default 0,
    sdt date not null default now(),
    edt date not null
);

alter table gym_pay
    add constraint gym_pay_pk primary key(no);

alter table gym_pay
    modify column no int not null auto_increment;
```

```
create table gym_trainer(
    no int not null,
    bag varchar(30),
    photo varchar(255) not null,
    name varchar(30) not null,
    tel varchar(20) not null,
    cts date not null,
    cte date not null,
    members varchar(255)
);

alter table gym_trainer
    add constraint gym_trainer_pk primary key(no);

alter table gym_trainer
    modify column no int not null auto_increment;
```
