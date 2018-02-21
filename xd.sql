drop schema practica_test;

create schema practica_test;

use practica_test;

create table if not exists user (
	id int primary key not null auto_increment,
    username varchar(25) not null,
    name varchar(40),
    create_date date not null,
    points int not null default 0
);

create table if not exists category (
	id int primary key not null auto_increment,
    title varchar(30) not null
);

create table if not exists routine (
	id int primary key not null auto_increment,
    title varchar(20) not null,
    description varchar(300),
    type enum('time', 'reps'),
    user_id int not null,
	foreign key (user_id) references user(id)
);

create table if not exists comment (
	id int primary key not null auto_increment,
    user_id int not null,
    routine_id int not null,
    date date not null,
    votes int default 0,
    content varchar(50) not null,
    foreign key (user_id) references user(id),
    foreign key (routine_id) references routine(id)
);

create table if not exists category_routine (
	id int primary key not null auto_increment,
    category_id int not null,
    routine_id int not null,
    foreign key (category_id) references category(id),
    foreign key (routine_id) references routine(id)
);

create table if not exists user_does_routine (
	id int primary key not null auto_increment,
    user_id int not null,
    routine_id int not null,
    date date not null,
    foreign key (user_id) references user(id),
    foreign key (routine_id) references routine(id)
);

insert into user values (default, 'Joan Piza Ferra', null, '2018-01-01', 5);

select * from user;