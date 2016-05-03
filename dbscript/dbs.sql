drop table user , namecard;

create table user (
	id int(16) auto_increment primary key,
	username varchar(50) ,
	mobile varchar(50) ,
	password varchar(50)
);

ALTER TABLE user ADD UNIQUE (mobile);
insert into user (username , mobile , password ) values ('admin','13588775566','123456');

commit;


create table namecard (
id int(16) ,
name  varchar(50),
photoLink  varchar(50),
company  varchar(50),
title  varchar(50),
email  varchar(50),
phone1  varchar(50),
phone2  varchar(50),
phone3  varchar(50),
mobile  varchar(50),
address  varchar(50),
website  varchar(50),
uid  int(16),
primary key (id , uid)
);

alter table namecard add seriesNumber int(16) default 0 ;
alter table namecard add selected TINYINT default 0 ;
alter table namecard add fax varchar(50) ;
alter table namecard add language TINYINT default 0 ;
alter table namecard add themeType TINYINT default 0 ;
alter table namecard add phone varchar(50) ;
alter table namecard drop phone1;
alter table namecard drop phone2;
alter table namecard drop phone3;
alter table namecard modify id int(16) auto_increment primary key;

alter table namecard drop  primary key;
alter table namecard drop id;
alter table namecard add id varchar(64) primary key;
alter table namecard drop photoLink;
alter table namecard add photolink varchar(100);
alter table namecard modify address varchar(200);

create table feedback (
uid int(16),
feed varchar(100),
updt timestamp
);





