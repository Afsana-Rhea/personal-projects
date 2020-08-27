drop database if exists rheaTheRhendiDB;

create database if not exists rheaTheRhendiDB;

use rheaTheRhendiDB;


create table if not exists `category`(
categoryId int not null auto_increment,
categoryName varchar(45) not null,
PRIMARY KEY (categoryId)
);

create table if not exists `blog`(
blogId int not null auto_increment,
blogTitle varchar(45) not null,
blogPost varchar(3000) not null,
isPublished bool,
categoryId int,
PRIMARY KEY (blogId),
FOREIGN KEY (categoryId) REFERENCES category(categoryId)
);