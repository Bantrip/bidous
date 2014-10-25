drop table if exists ss_user;
drop table if exists product;

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