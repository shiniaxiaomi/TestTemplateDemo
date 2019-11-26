create table user
(
    id    int unsigned auto_increment
        primary key,
    name  varchar(30) null,
    age   int         null,
    hobby varchar(20) null
)
    charset = utf8;
