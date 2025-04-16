create database course_management;

use course_management;

create table admin (
    id int primary key auto_increment,
    username varchar(50) unique not null,
    password varchar(255) not null
);

create table students (
    id int primary key auto_increment,
    name varchar(100) not null,
    dob date not null,
    email varchar(100) unique not null,
    password varchar(255) not null,
    sex bit not null,
    phone varchar(15),
    create_at date
);

create table courses(
    id int primary key auto_increment,
    name varchar(100) not null,
    duration int not null,
    instructor varchar(100) not null,
    create_at date
);

create table enrollments(
    id int primary key auto_increment,
    student_id int,
    course_id int,
    registered_at datetime default current_timestamp,
    status enum('WAITING', 'DENIED', 'CANCEL', 'CONFIRM') default 'WAITING',
    unique(student_id, course_id),
    foreign key (student_id) references students(id) on delete cascade,
    foreign key (course_id) references courses(id) on delete cascade
);