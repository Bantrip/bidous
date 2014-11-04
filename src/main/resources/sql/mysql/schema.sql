drop table if exists ss_user;
drop table if exists product;
drop table if exists dest;
drop table if exists product_has_dest;
drop table if exists upload_pic;

CREATE TABLE prod_imgs (
  id BIGINT auto_increment,
  url VARCHAR(2000) NULL,
  product_id BIGINT NOT NULL,
  index BIGINT NULL,
  PRIMARY KEY (id),
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
  PRIMARY KEY (id),
)ENGINE = InnoDB;


create table tag_group (
  id bigint auto_increment,
  name VARCHAR(300) NULL comment 'tag组名',
  PRIMARY KEY (id)
)ENGINE = InnoDB;


CREATE TABLE product_has_dest (
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
  PRIMARY KEY (id)
 )ENGINE = InnoDB;




create table ss_user (
	id bigint auto_increment,
	login_name varchar(64) not null unique,
	name varchar(64) not null,
	password varchar(255) not null,
	salt varchar(64) not null,
	roles varchar(255) not null,
	register_date timestamp not null default 0,
	primary key (id)
) engine=InnoDB;