-- utf8mb4_unicode_ci로 한글 설정
CREATE DATABASE IF NOT EXISTS search CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE search;

create table category
(
    category_id   bigint auto_increment primary key,
    category_name varchar(255) not null,
    parent_id     bigint       null,
    foreign key (parent_id) references category (category_id)
);

create table product
(
    product_id   bigint auto_increment primary key,
    description  varchar(2000) null,
    image_url    varchar(255)  not null,
    price        int           not null,
    product_name varchar(255)  not null
);

create table product_category
(
    product_category_id bigint auto_increment primary key,
    category_id         bigint null,
    product_id          bigint null,
    foreign key (product_id) references product (product_id),
    foreign key (category_id) references category (category_id)
);
