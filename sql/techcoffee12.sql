create database techcoffee;
use techcoffee;

create table if not exists users(
	iduser int not null  AUTO_INCREMENT,
	username char(16) not null unique,
    passwd char(16) not null,
    check_admin char(1) default "0",
    tichDiem int default 0,
    primary key (iduser)
);

create table if not exists datBan (
	idDatban int not null primary key AUTO_INCREMENT,
    soBan char(4) not null,
    userName char(16) not null,
    soNguoi nvarchar(15) not null,
    timeDatban nvarchar(30) not null,
    yeuCau nvarchar(1000)
);


create table if not exists history_users (
	idHistory int not null primary key AUTO_INCREMENT,
    userNameH char(16) not null,
    TimeH nvarchar(100) not null,
    noiDung nvarchar(100) not null
);
create table if not exists feedback (
	idfeedback int not null primary key AUTO_INCREMENT,
    userNameF char(16) not null,
    TimeF nvarchar(100) not null,
    noiDung nvarchar(500) not null
);


insert into feedback values(null,"username1","12h","hahahhahahhahha");

insert into users(username,passwd,check_admin) values("username1","123","0");
insert into users(username,passwd,check_admin) values("admin1","123","1");
insert into users(username,passwd,check_admin) values("username2","456","0");
insert into users(username,passwd,check_admin) values("admin2","456","1");


create table if not exists employee (
	idemployee char(4) not null,
    emplyeename char(35) not null,
    phonenumber char(10) not null,
    wage char(10) not null,
    workingday date not null,
	birthday date not null,
    primary key(idemployee)
);

create table if not exists save_add(
	idsave_add int not null auto_increment,
    idemployee_saveadd char(4) ,
    statuses char(10) not null, 
    date varchar(255) ,
    primary key (idsave_add)
);

create table if not exists save_delete(
	idsave_delete int auto_increment,
	idemployee_savedelete char(4) ,
    statuses char(20) not null, 
    date varchar(255) ,
	primary key (idsave_delete)
);

-- drop trigger add_history;
-- trigger luu lich su them
delimiter $$
create trigger add_history before insert
on employee 
for each row
begin
	insert into save_add(idsave_add,idemployee_saveadd,statuses,date) values(null,new.idemployee,"Added", now());
end$$
delimiter ;

-- drop trigger delete_history;
-- trigger luu lich su xoa
delimiter $$
create trigger delete_history before delete
on employee 
for each row
begin
	insert into save_delete(idsave_delete,idemployee_savedelete,statuses,date) values(null,old.idemployee,"Deleted", now());
end$$
delimiter ;

-- thu tuc them
delimiter $$
create procedure add_employee(idemployee1 char(4),emplyeename1 char(35), phonenumber1 char(10),  wage1 char(11),  workingday1 date, birthday1 date)
begin
	if exists (select idemployee from employee)
    then
		insert into employee(idemployee,emplyeename,phonenumber,wage,workingday,birthday) values(idemployee1,emplyeename1,phonenumber1,wage1,workingday1,birthday1) ;
    end if;
end$$
delimiter ;

insert into employee(idemployee,emplyeename,phonenumber,wage,workingday,birthday) values("nv1","nguyen van a","0947123456","2000000","2019-06-06","1992-06-15");
insert into employee(idemployee,emplyeename,phonenumber,wage,workingday,birthday) values("bv1","nguyen van b","0909654123","3000000","2019-07-06","1993-03-22");
insert into employee(idemployee,emplyeename,phonenumber,wage,workingday,birthday) values("bv2","nguyen van c","0909669636","3000000","2019-08-06","1993-03-22");

-- thu tuc update du lieu
delimiter $$
create procedure update_employee(idemployee1 char(4),emplyeename1 char(35), phonenumber1 char(10),  wage1 char(11),  workingday1 date, birthday1 date, idemployeenew char(4))
begin
	if exists (select idemployee from employee)
    then
		update employee set idemployee = idemployee1 , emplyeename=emplyeename1, phonenumber=phonenumber1, wage=wage1, workingday=workingday1, birthday=birthday1 where idemployee=idemployeenew ;
    end if;
end$$
delimiter ;

-- thu tuc xoa nhan vien
delimiter $$
create procedure delete_employee(idemployee1 char(4))
begin
	if exists (select idemployee from employee)
    then
		delete from employee where idemployee=idemployee1;
    end if;
end$$
delimiter ;



create table if not exists ban (
    tenBan char(8) primary key,
    trangThai int not null 
);

insert into ban values('so1',1);
insert into ban values('so2',1);
insert into ban values('so3',1);
insert into ban values('so4',1);
insert into ban values('so5',1);
insert into ban values('so6',1);
insert into ban values('so7',1);
insert into ban values('so8',1);
insert into ban values('so9',1);
insert into ban values('so10',1);
delimiter $$

create procedure dangdat(ban char(8))
begin
	update ban set trangThai = 2 where  tenBan=ban;
end$$

create procedure datXong(ban char(8))
begin
	update ban set trangThai = 3 where  tenBan=ban;
end$$

create procedure dungXong(ban char(8))
begin
	update ban set trangThai = 1 where  tenBan=ban;
end$$


-- call dangdat('so1')$$
-- call dungXong('so2')$$
-- call datXong('so1')$$

create function xuatTT(ban char(8))
returns int
reads sql data deterministic
begin
 declare tt int;
	set tt=(select trangThai from techcoffee.ban where tenBan =ban);
return tt;
end$$
SET GLOBAL log_bin_trust_function_creators = 1;

create function xuatTichDiem(userTD char(16))
returns int
reads sql data deterministic
begin
 declare tt int;
	set tt=(select tichDiem from techcoffee.users where username =userTD);
return tt;
end$$

-- thu tuc create user du lieu
create procedure create_user(idemployee1 char(4),emplyeename1 char(35), phonenumber1 char(10),  wage1 char(11),  workingday1 date, birthday1 date, idemployeenew char(4))
begin
	if exists (select idemployee from employee)
    then
		update employee set idemployee = idemployee1 , emplyeename=emplyeename1, phonenumber=phonenumber1, wage=wage1, workingday=workingday1, birthday=birthday1 where idemployee=idemployeenew ;
    end if;
end$$

create procedure update_pass(username1 char(16),passwd1 char(16))
begin
	update users set passwd = passwd1 where username =username1;
end$$

-- call update_pass('username1','1234')$$
create procedure update_tichdiem(username1 char(16),point1 int)
begin
	update users set tichDiem = point1 where username =username1;
end$$

