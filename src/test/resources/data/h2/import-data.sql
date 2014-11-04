


insert into ss_user (id, login_name, name, password, salt, roles, register_date) values(1,'admin','Admin','691b14d79bf0fa2215f155235df5e670b64394cc','7efbd59d9741d34f','admin','2012-06-04 01:00:00');
insert into ss_user (id, login_name, name, password, salt, roles, register_date) values(2,'user','Calvin','2488aa0c31c624687bd9928e0a5d29e7d1ed520b','6d65d24122c30500','user','2012-06-04 02:00:00');

insert into product (id,name, recommand , status ,  price,url,stock) values (1,'18岁大学生陪你游山玩水','no zuo no die',1,22.3,'http://www.yhd.com',0);
insert into product (id,name, recommand , status ,  price,url,stock) values (2,'高科技产品','why you try',0,1222.3,'htp://www.yhd.com',0);

insert into dest(id,name,type) values (1,'三亚',0);
insert into dest(id,name,type) values (2,'长白山',0);
insert into dest(id,name,type) values (3,'丽江',0);
insert into dest(id,name,type) values (4,'桂林',0);
insert into dest(id,name,type) values (5,'厦门',0);
insert into dest(id,name,type) values (6,'大连',0);
insert into dest(id,name,type) values (7,'青岛',0);
insert into dest(id,name,type) values (8,'成都',0);
insert into dest(id,name,type) values (9,'九寨沟',0);
insert into dest(id,name,type) values (10,'西双版纳',0);
insert into dest(id,name,type) values (11,'昆明',0);
insert into dest(id,name,type) values (12,'曼谷',1);
insert into dest(id,name,type) values (13,'大阪',1);
insert into dest(id,name,type) values (14,'首尔',1);
insert into dest(id,name,type) values (15,'东京',1);
insert into dest(id,name,type) values (16,'京都',1);
insert into dest(id,name,type) values (17,'普吉岛',1);
insert into dest(id,name,type) values (18,'巴厘岛',1);
insert into dest(id,name,type) values (19,'新加坡',1);
insert into dest(id,name,type) values (20,'清迈',1);
insert into dest(id,name,type) values (21,'柬埔寨/吴哥窟',1);
insert into dest(id,name,type) values (22,'香港',1);
insert into dest(id,name,type) values (23,'澳门',1);
insert into dest(id,name,type) values (24,'台北/高雄/垦丁',1);
insert into dest(id,name,type) values (25,'希腊',1);
insert into dest(id,name,type) values (26,'马尔代夫',1);
insert into dest(id,name,type) values (27,'夏威夷',1);
insert into dest(id,name,type) values (28,'美国',1);
insert into dest(id,name,type) values (29,'欧洲',1);
insert into dest(id,name,type) values (30,'尼泊尔',1);
insert into dest(id,name,type) values (31,'济州岛',1);

insert into product_has_dest(dest_id,product_id)values(1,1);
insert into product_has_dest(dest_id,product_id)values(1,18);
insert into product_has_dest(dest_id,product_id)values(2,1);
insert into product_has_dest(dest_id,product_id)values(2,27);

insert into tag_group(id,name)values(1,'主题');
insert into tag_group(id,name)values(2,'品类');
insert into tag_group(id,name)values(3,'人群');

insert into tag(id,name,tag_group_id)values(1,'登山',1);
insert into tag(id,name,tag_group_id)values(2,'露营',1);
insert into tag(id,name,tag_group_id)values(3,'玩水',1);
insert into tag(id,name,tag_group_id)values(4,'潜水',1);
insert into tag(id,name,tag_group_id)values(5,'滑雪',1);
insert into tag(id,name,tag_group_id)values(6,'自驾',1);
insert into tag(id,name,tag_group_id)values(7,'徒步',1);
insert into tag(id,name,tag_group_id)values(8,'骑行',1);
insert into tag(id,name,tag_group_id)values(9,'美食',1);
insert into tag(id,name,tag_group_id)values(10,'购物',1);

insert into tag(id,name,tag_group_id)values(11,'电子',2);
insert into tag(id,name,tag_group_id)values(12,'服饰',2);
insert into tag(id,name,tag_group_id)values(13,'户外',2);
insert into tag(id,name,tag_group_id)values(14,'收纳',2);
insert into tag(id,name,tag_group_id)values(15,'日用',2);
insert into tag(id,name,tag_group_id)values(16,'日化',2);
insert into tag(id,name,tag_group_id)values(17,'穿戴',2);
insert into tag(id,name,tag_group_id)values(18,'医护',2);
insert into tag(id,name,tag_group_id)values(19,'娱乐',2);

insert into tag(id,name,tag_group_id)values(20,'男士',3);
insert into tag(id,name,tag_group_id)values(21,'儿童',3);
insert into tag(id,name,tag_group_id)values(22,'女士',3);
insert into tag(id,name,tag_group_id)values(23,'老人',3);


insert into product_has_tag(product_id,tag_id)values(1,1);
insert into product_has_tag(product_id,tag_id)values(1,11);
insert into product_has_tag(product_id,tag_id)values(1,21);
insert into product_has_tag(product_id,tag_id)values(2,3);
insert into product_has_tag(product_id,tag_id)values(2,15);
insert into product_has_tag(product_id,tag_id)values(2,22);



insert into prod_imgs(id,url,product_id,index)values(1,'http://d7.yihaodianimg.com/N07/M01/02/26/ChEbvFRW9E2Aca1_AAFD67mmpHU46600.jpg',1,1);
insert into prod_imgs(id,url,product_id,index)values(2,'http://d9.yihaodianimg.com/N07/M0B/14/A6/CgQI0FRR5zeAMuOeAABA6o7BXsA62300.jpg',1,2);
insert into prod_imgs(id,url,product_id,index)values(3,'http://d9.yihaodianimg.com/N05/M03/7E/FC/CgQI0lRTZf6AYV8jAAFkCYOAHJI56000.jpg',1,3);
insert into prod_imgs(id,url,product_id,index)values(4,'http://d7.yihaodianimg.com/N05/M03/84/97/CgQI0lRW4ueAVzWAAAA7gYPVO4U94500.jpg',2,1);
insert into prod_imgs(id,url,product_id,index)values(5,'"http://d8.yihaodianimg.com/V00/M08/37/74/CgQDsFQrdMyAbyBCAAA2fEMvzqc32800.jpg',2,2);







commit;
