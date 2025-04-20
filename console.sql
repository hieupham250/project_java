create database course_management;

# drop database course_management;

use course_management;

create table accounts
(
    id       int primary key auto_increment,
    email    varchar(100) unique       not null,
    password varchar(255)              not null,
    role     enum ('admin', 'student') not null,
    status   enum ('active', 'inactive', 'blocked') default 'active'
);

insert into accounts (email, password, role)
values ('admin@gmail.com', '123', 'admin'),
       ('hieupt@gmail.com', '123456', 'student'),
       ('tranthib@gmail.com', '123456', 'student'),
       ('levanc@gmail.com', '123456', 'student'),
       ('phamthid@gmail.com', '123456', 'student'),
       ('hoangvane@gmail.com', '123456', 'student'),
       ('nguyenthif@gmail.com', '123456', 'student'),
       ('tranvang@gmail.com', '123456', 'student'),
       ('dothih@gmail.com', '123456', 'student'),
       ('nguyenvani@gmail.com', '123456', 'student'),
       ('lethij@gmail.com', '123456', 'student');

create table students
(
    id         int primary key auto_increment,
    name       varchar(100) not null,
    dob        date         not null,
    sex        bit          not null,
    phone      varchar(15),
    account_id int,
    foreign key (account_id) references accounts (id) on delete cascade,
    create_at  date
);

insert into students (name, dob, sex, phone, account_id, create_at)
values ('Nguyễn Văn A', '2001-05-20', 1, '0901234567', 2, '2024-01-10'),
       ('Trần Thị B', '2000-11-15', 0, '0912345678', 3, '2024-01-12'),
       ('Lê Văn C', '2002-07-30', 1, '0923456789', 4, '2024-01-15'),
       ('Phạm Thị D', '1999-02-25', 0, '0934567890', 5, '2024-01-18'),
       ('Hoàng Văn E', '2003-08-10', 1, '0945678901', 6, '2024-01-20'),
       ('Nguyễn Thị F', '2001-03-05', 0, '0956789012', 7, '2024-01-22'),
       ('Trần Văn G', '2000-12-12', 1, '0967890123', 8, '2024-01-25'),
       ('Đỗ Thị H', '2002-09-18', 0, '0978901234', 9, '2024-01-28'),
       ('Nguyễn Văn I', '2001-10-05', 1, '0989012345', 10, '2024-02-01'),
       ('Lê Thị J', '2000-04-22', 0, '0990123456', 11, '2024-02-03');

create table courses
(
    id         int primary key auto_increment,
    name       varchar(100) not null,
    duration   int          not null,
    instructor varchar(100) not null,
    create_at  date
);

insert into courses (name, duration, instructor, create_at)
values ('Lập trình Web', 12, 'Nguyễn Văn A', '2024-01-15'),
       ('Khoa học Dữ liệu', 16, 'Trần Thị B', '2024-02-01'),
       ('Học Máy', 20, 'Lê Văn C', '2024-02-20'),
       ('Python Cơ Bản', 8, 'Phạm Thị D', '2024-03-05'),
       ('Thiết kế Cơ sở Dữ liệu', 10, 'Hoàng Văn E', '2024-03-18'),
       ('Phát triển Ứng dụng Di động', 14, 'Nguyễn Thị F', '2024-04-01'),
       ('Điện toán Đám mây', 18, 'Trần Văn G', '2024-04-12'),
       ('An ninh mạng', 15, 'Đỗ Thị H', '2024-04-20'),
       ('Kiến thức Cơ bản về DevOps', 12, 'Nguyễn Văn I', '2024-05-01'),
       ('Trí tuệ Nhân tạo cho Người mới bắt đầu', 10, 'Lê Thị J', '2024-05-10'),
       ('Thiết kế UI/UX', 9, 'Phạm Văn K', '2024-05-15'),
       ('Lập trình Java', 16, 'Hoàng Thị L', '2024-05-22'),
       ('C# cho Lập trình viên', 14, 'Nguyễn Văn M', '2024-06-01'),
       ('Kiến thức cơ bản về ReactJS', 10, 'Trần Thị N', '2024-06-10'),
       ('Mạng máy tính cơ bản', 11, 'Lê Văn O', '2024-06-20');

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

insert into enrollments (student_id, course_id, status)
values (1, 1, 'CONFIRM'),
       (2, 2, 'WAITING'),
       (3, 3, 'CONFIRM'),
       (4, 4, 'CONFIRM'),
       (5, 5, 'CANCEL'),
       (6, 6, 'WAITING'),
       (7, 7, 'CONFIRM'),
       (8, 8, 'DENIED'),
       (9, 9, 'CONFIRM'),
       (10, 10, 'WAITING');

delimiter //
create procedure login(
    email_in varchar(100),
    password_in varchar(255)
)
begin
SELECT * from accounts where email = email_in and password = password_in;
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

create procedure search_courses_by_name(
    keyword_in varchar(100),
    page_size_in int,
    offset_in int,
    OUT total_records int
)
begin
select count(*)
into total_records
from courses
where name like concat('%', keyword_in, '%');

select *
from courses
where name like concat('%', keyword_in, '%')
    limit page_size_in offset offset_in;
end;

create procedure get_courses_sorted_paginated(
    sort_option varchar(20),
    page_size_in int,
    offset_in int
)
begin
    if lower(sort_option) = 'id_asc' then
select * from courses order by id asc limit page_size_in offset offset_in;
elseif lower(sort_option) = 'id_desc' then
select * from courses order by id desc limit page_size_in offset offset_in;
elseif lower(sort_option) = 'name_asc' then
select * from courses order by name asc limit page_size_in offset offset_in;
elseif lower(sort_option) = 'name_desc' then
select * from courses order by name desc limit page_size_in offset offset_in;
end if;
end;

create procedure is_exist_name(name_in varchar(100))
begin
select count(*) > 0 as is_exist from courses where name = name_in;
end;

create procedure check_course_has_students(course_id_in int)
begin
select count(*) > 0 as has_students from enrollments where course_id = course_id_in;
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
set name       = name_in,
    duration   = duration_in,
    instructor = instructor_in
where id = id_in;
end;

create procedure delete_course(id_in int)
begin
delete from courses where id = id_in;
end;

create procedure get_students()
begin
select s.*, a.email, a.password
from students s
         left join accounts a on a.id = s.account_id;
end;

create procedure is_exist_email(email_in varchar(100))
begin
select count(*) > 0 as is_exist from accounts where email = email_in;
end;

create procedure create_student(
    name_in varchar(100),
    dob_in date,
    sex_in bit,
    phone_in varchar(15),
    email_in varchar(100),
    password_in varchar(255)
)
begin
    declare acc_id int;

insert into accounts(email, password, role)
values (email_in, password_in, 'student');

set acc_id = last_insert_id();

insert into students(name, dob, sex, phone, account_id, create_at)
values (name_in, dob_in, sex_in, phone_in, acc_id, curdate());
end;
delimiter //