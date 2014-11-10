--create database if not exists bantrip;
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
  name VARCHAR(500) NULL,
  type bigint NULL,
  owner bigint NULL,
  PRIMARY KEY (id)
)ENGINE = InnoDB;





create table ss_user (
	id bigint auto_increment,
	login_name varchar(200) not null unique,
	name varchar(200) not null,
	password varchar(255) not null,
	salt varchar(64) not null,
	roles varchar(3000) not null,
	create_time timestamp not null default 0,
	merchant_id bigint;
	primary key (id)
) engine=InnoDB;


create table product_desc (
  id bigint auto_increment,
  content TEXT NULL,
  product_id BIGINT NOT NULL,
  desc_index BIGINT NULL COMMENT "排序号",
  type BIGINT NULL COMMENT "0：desc  1:image",
  PRIMARY KEY (id),
  INDEX fk_productdesc_pid_idx (product_id ASC)
)ENGINE = InnoDB;




create table product_has_tag (
  id bigint auto_increment,
  product_id BIGINT NOT NULL,
  tag_id BIGINT NOT NULL,
  PRIMARY KEY (id)
)ENGINE = InnoDB;

create table tag (
  id bigint auto_increment,
  name VARCHAR(300) NULL COMMENT 'tag名',
  group_id bigint NOT NULL,
  PRIMARY KEY (id)
)ENGINE = InnoDB;


create table tag_group (
  id bigint auto_increment,
  name VARCHAR(300) NULL comment 'tag组名',
  PRIMARY KEY (id)
)ENGINE = InnoDB;


create table product_has_dest (
  product_id BIGINT NOT NULL,
  dest_id BIGINT NOT NULL,
  id bigint auto_increment NOT NULL,
  PRIMARY KEY (id)
)ENGINE = InnoDB;


create table dest (
  id bigint auto_increment,
  name VARCHAR(200)  COMMENT '地点名',
  type bigint  COMMENT '类型 0 国内 1 境外',
  PRIMARY KEY (id)
 )ENGINE = InnoDB;



create table product (
  id bigint auto_increment,
  name VARCHAR(200)  COMMENT '商品名',
  category_id bigint  COMMENT '类目id',
  recommand VARCHAR(500) NULL COMMENT '卖点',
  status bigint COMMENT '状态 0:发布中 1审核中 2 已发布',
  price DECIMAL(10,2)  COMMENT '价格',
  merchant_id bigint COMMENT '卖家id',
  url VARCHAR(2000)  COMMENT '目标url',
  stock DECIMAL(10,0) ,
  pics text,
  PRIMARY KEY (id)
 )ENGINE = InnoDB;




