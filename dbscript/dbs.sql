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

create table edition (
version varchar(50) not null unique,
ifrequire TINYINT default 0 
);
alter table edition add url varchar(50);
insert into edition (version , ifrequire) values ('0.0.1' ,0 );
insert into edition (version , ifrequire) values ('0.0.2' ,1 );
insert into edition (version , ifrequire) values ('0.0.3' ,0 );
update edition set url = 'www.163.com';
commit;

create table photo (
id int(16) auto_increment primary key,
img longblob
);