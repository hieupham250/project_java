create database course_management;

use course_management;

create table admin
(
    id       int primary key auto_increment,
    username varchar(50) unique not null,
    password varchar(255)       not null
);

create table students
(
    id        int primary key auto_increment,
    name      varchar(100)        not null,
    dob       date                not null,
    email     varchar(100) unique not null,
    password  varchar(255)        not null,
    sex       bit                 not null,
    phone     varchar(15),
    create_at date
);

create table courses
(
    id         int primary key auto_increment,
    name       varchar(100) not null,
    duration   int          not null,
    instructor varchar(100) not null,
    create_at  date
);

create table enrollments
(
    id            int primary key auto_increment,
    student_id    int,
    course_id     int,
    registered_at datetime                                        default current_timestamp,
    status        enum ('WAITING', 'DENIED', 'CANCEL', 'CONFIRM') default 'WAITING',
    unique (student_id, course_id),
    foreign key (student_id) references students (id) on delete cascade,
    foreign key (course_id) references courses (id) on delete cascade
);

delimiter //
create procedure login_admin(
    username_in varchar(50),
    password_in varchar(255)
)
begin
SELECT * from admin where username = username_in and password = password_in;
end;

create procedure login_student(
    email_in varchar(100),
    password_in varchar(255)
)
begin
select * from students where email = email_in and password = password_in;
end;

create procedure get_courses()
begin
select * from courses;
end;

create procedure get_courses_by_page(
    page_size_in int,
    offset_in int
)
begin
select * from courses limit page_size_in offset offset_in;
end;

create procedure get_course_by_id(id_in int)
begin
select * from courses where id = id_in;
end;

create procedure create_course(
    name_in varchar(100),
    duration_in int,
    instructor_in varchar(100)
)
begin
insert into courses(name, duration, instructor, create_at)
values (name_in, duration_in, instructor_in, curdate());
end;

create procedure update_course(
    id_in int,
    name_in varchar(100),
    duration_in int,
    instructor_in varchar(100)
)
begin
update courses
set
    name = name_in,
    duration = duration_in,
    instructor = instructor_in
where id = id_in;
end;
delimiter //