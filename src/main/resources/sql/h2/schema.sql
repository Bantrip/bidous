drop table if exists ss_user;
drop table if exists product;
drop table if exists dest;
drop table if exists product_has_dest;
drop table if exists tag_group;
drop table if exists tag;
drop table if exists product_has_tag;
drop table if exists product_desc;
drop table if exists merchant;


create table merchant (
	id bigint auto_increment,
	name varchar(500) NULL,
	type bigint NULL,
	owner bigint NULL,
	creater bigint,
	create_time timestamp not null default 0,
	primary key (id)
);


create table ss_user (
	id bigint auto_increment,
	login_name varchar(64) not null unique,
	name varchar(64) not null,
	password varchar(255) not null,
	salt varchar(64) not null,
	roles varchar(255) not null,
	creater bigint,
	create_time timestamp,
	merchant_id bigint,
	primary key (id)
);


CREATE TABLE product_desc (
  id bigint  generated by default as identity,
  content TEXT NULL,
  product_id BIGINT NOT NULL,
  desc_index BIGINT NULL COMMENT '排序号',
  type BIGINT NULL COMMENT '0：desc  1:image',
  PRIMARY KEY (id)
);






create table product_has_tag (
  product_id BIGINT NOT NULL,
  tag_id BIGINT NOT NULL,
  id bigint generated by default as identity,
  PRIMARY KEY (id)
);

create table tag (
  id bigint generated by default as identity,
  name VARCHAR(300) NULL COMMENT 'tag名',
  group_id bigint NOT NULL,
  PRIMARY KEY (id),
);


create table tag_group (
  id bigint generated by default as identity,
  name VARCHAR(300) NULL comment 'tag组名',
  PRIMARY KEY (id)
);


create table product_has_dest (
  product_id BIGINT NOT NULL,
  dest_id BIGINT NOT NULL,
  id bigint generated by default as identity, 
  PRIMARY KEY (id)
);

create table dest (
 id bigint generated by default as identity,
  name VARCHAR(200)  ,
  type bigint,
  PRIMARY KEY (id)
 );

create table product (
  id bigint generated by default as identity,
  name varchar(200),
  category_id bigint ,
  recommand varchar(500) ,
  status bigint  ,
  price DECIMAL(10,2)  ,
  merchant_id bigint ,
  url varchar(2000)  ,
  stock DECIMAL(10,0) ,
  pics text,
  creater bigint,
  create_time timestamp not null default 0,
  primary key (id)
 );



