# Cat Gym - CS

### Notice
    ● 구동 테스트, 공백 테스트 ServerApp, ClientApp 복습

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
