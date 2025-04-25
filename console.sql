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
       ('ptrunghieu2507@gmail.com', '123456', 'student'),
       ('tranthib@gmail.com', '123456', 'student'),
       ('levanc@gmail.com', '123456', 'student'),
       ('phamthid@gmail.com', '123456', 'student'),
       ('hoangvane@gmail.com', '123456', 'student'),
       ('nguyenthif@gmail.com', '123456', 'student'),
       ('tranvang@gmail.com', '123456', 'student'),
       ('dothih@gmail.com', '123456', 'student'),
       ('nguyenvani@gmail.com', '123456', 'student'),
       ('lethij@gmail.com', '123456', 'student'),
       ('nguyenvana@gmail.com', '123456', 'student');

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
values ('Phạm Trung Hếu', '2004-07-25', 1, '0901234567', 2, '2024-01-10'),
       ('Trần Thị B', '2000-11-15', 0, '0912345678', 3, '2024-01-12'),
       ('Lê Văn C', '2002-07-30', 1, '0923456789', 4, '2024-01-15'),
       ('Phạm Thị D', '1999-02-25', 0, '0934567890', 5, '2024-01-18'),
       ('Hoàng Văn E', '2003-08-10', 1, '0945678901', 6, '2024-01-20'),
       ('Nguyễn Thị F', '2001-03-05', 0, '0956789012', 7, '2024-01-22'),
       ('Trần Văn G', '2000-12-12', 1, '0967890123', 8, '2024-01-25'),
       ('Đỗ Thị H', '2002-09-18', 0, '0978901234', 9, '2024-01-28'),
       ('Nguyễn Văn I', '2001-10-05', 1, '0989012345', 10, '2024-02-01'),
       ('Lê Thị J', '2000-04-22', 0, '0990123456', 11, '2024-02-03'),
       ('Nguyễn Văn A', '2001-05-20', 1, '0901234567', 12, '2024-01-10');

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
       (1, 2, 'CONFIRM'),
       (1, 3, 'CONFIRM'),
       (2, 1, 'CONFIRM'),
       (2, 2, 'CONFIRM'),
       (2, 3, 'CONFIRM'),
       (3, 1, 'CONFIRM'),
       (3, 2, 'CONFIRM'),
       (3, 3, 'CONFIRM'),
       (4, 1, 'CONFIRM'),
       (4, 2, 'CONFIRM'),
       (4, 3, 'CONFIRM'),
       (5, 1, 'CONFIRM'),
       (5, 2, 'CONFIRM'),
       (5, 3, 'CONFIRM'),
       (6, 1, 'CONFIRM'),
       (6, 2, 'CONFIRM'),
       (6, 3, 'CONFIRM'),
       (7, 1, 'CONFIRM'),
       (7, 2, 'CONFIRM'),
       (7, 3, 'CONFIRM'),
       (8, 1, 'CONFIRM'),
       (8, 2, 'CONFIRM'),
       (8, 3, 'CONFIRM'),
       (9, 1, 'CONFIRM'),
       (9, 2, 'CONFIRM'),
       (9, 3, 'CONFIRM'),
       (10, 1, 'CONFIRM'),
       (10, 2, 'CONFIRM'),
       (10, 3, 'CONFIRM'),
       (11, 1, 'CONFIRM'),
       (11, 2, 'CONFIRM'),
       (11, 3, 'CONFIRM'),
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
SELECT a.*, s.id as student_id
from accounts a
         left join students s on a.id = s.account_id
where email = email_in
  and password = password_in;
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

create procedure get_courses_sorted(
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

create procedure delete_course(
    id_in int,
    OUT is_deleted boolean
)
begin
    declare student_count int;
select count(*)
into student_count
from enrollments
where course_id = id_in;

if student_count > 0 then
        set is_deleted = false;
else
delete from courses where id = id_in;
set is_deleted = true;
end if;
end;

create procedure get_students()
begin
select s.*, a.email, a.password, a.status
from students s
         left join accounts a on a.id = s.account_id;
end;

create procedure get_students_by_page(
    page_size_in int,
    offset_in int
)
begin
select s.*, a.email, a.password, a.status
from students s
         left join accounts a on a.id = s.account_id
    limit page_size_in offset offset_in;
end;

create procedure get_student_by_id(id_in int)
begin
select s.*, a.email, a.password, a.status
from students s
         left join accounts a on a.id = s.account_id
where s.id = id_in;
end;

create procedure search_students_by_name_or_email(
    keyword_in varchar(100),
    page_size_in int,
    offset_in int,
    OUT total_records int
)
begin
select count(*)
into total_records
from students s
         left join accounts a on a.id = s.account_id
where name like concat('%', keyword_in, '%')
   or a.email like concat('%', keyword_in, '%');

select s.*, a.email, a.password, a.status
from students s
         left join accounts a on a.id = s.account_id
where name like concat('%', keyword_in, '%')
   or a.email like concat('%', keyword_in, '%')
    limit page_size_in offset offset_in;
end;

create procedure get_students_sorted(
    sort_option varchar(20),
    page_size_in int,
    offset_in int
)
begin
    if lower(sort_option) = 'id_asc' then
select s.*, a.email, a.password, a.status
from students s
         left join accounts a on a.id = s.account_id
order by s.id asc
    limit page_size_in offset offset_in;
elseif lower(sort_option) = 'id_desc' then
select s.*, a.email, a.password, a.status
from students s
         left join accounts a on a.id = s.account_id
order by s.id desc
    limit page_size_in offset offset_in;
elseif lower(sort_option) = 'name_asc' then
select s.*, a.email, a.password, a.status
from students s
         left join accounts a on a.id = s.account_id
order by s.name asc
    limit page_size_in offset offset_in;
elseif lower(sort_option) = 'name_desc' then
select s.*, a.email, a.password, a.status
from students s
         left join accounts a on a.id = s.account_id
order by s.name desc
    limit page_size_in offset offset_in;
end if;
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

create procedure update_student(
    id_in int,
    name_in varchar(100),
    dob_in date,
    sex_in bit,
    phone_in varchar(15),
    status_in enum ('active', 'inactive', 'blocked')
)
begin
update students
set name  = name_in,
    dob   = dob_in,
    sex   = sex_in,
    phone = phone_in
where id = id_in;

update accounts
set status = status_in
where id = (select students.account_id from students where id = id_in);
end;

create procedure delete_student(
    id_in int,
    OUT is_deleted boolean
)
begin
    declare acc_id int;
    declare course_count int;

select account_id
into acc_id
from students
where id = id_in;

select count(*)
into course_count
from enrollments
where student_id = id_in;

if course_count > 0 then
        set is_deleted = false;
else
delete from accounts where id = acc_id;
set is_deleted = true;
end if;
end;

create procedure get_my_registered_courses(
    student_id_in int,
    page_size_in int,
    offset_in int,
    OUT total_records int
)
begin
select count(*)
into total_records
from enrollments e
         join courses c on e.course_id = c.id
where e.student_id = student_id_in;

select c.id as course_id, c.name as course_name, e.registered_at, e.status
from enrollments e
         join courses c on e.course_id = c.id
where e.student_id = student_id_in
    limit page_size_in offset offset_in;
end;

create procedure register_course(
    student_id_in int,
    course_id_in int,
    OUT is_success boolean
)
begin
    declare count_check int default 0;

select count(*)
into count_check
from enrollments
where student_id = student_id_in
  and course_id = course_id_in;

if count_check > 0 then
        set is_success = false;
else
        insert into enrollments(student_id, course_id)
        values (student_id_in, course_id_in);
        set is_success = true;
end if;
end;

create procedure cancel_course_registration(
    student_id_in int,
    course_id_in int,
    OUT is_canceled boolean
)
begin
    declare enrollment_count int;
    declare current_status varchar(20);

    -- Kiểm tra xem học viên đã đăng ký khóa học này chưa
select count(*)
into enrollment_count
from enrollments
where student_id = student_id_in
  and course_id = course_id_in;

-- Nếu học viên đã đăng ký khóa học, kiểm tra trạng thái
if enrollment_count > 0 then
        -- Lấy trạng thái hiện tại của đăng ký
select status
into current_status
from enrollments
where student_id = student_id_in
  and course_id = course_id_in;

-- Nếu trạng thái là 'CONFIRM', không cho phép hủy
if current_status = 'CONFIRM' then
            set is_canceled = false;
else
            -- Nếu không phải trạng thái 'CONFIRM', thực hiện hủy đăng ký
update enrollments
set status = 'CANCEL'
where student_id = student_id_in
  and course_id = course_id_in;
set is_canceled = true;
end if;
else
        set is_canceled = false;
end if;
end;

create procedure get_registered_students_by_course(
    course_id_in int,
    page_size_in int,
    offset_in int,
    OUT total_records int
)
begin
    -- Đếm tổng số bản ghi
select count(*)
into total_records
from enrollments e
         join students s on e.student_id = s.id
         join accounts a on s.account_id = a.id
where e.course_id = course_id_in;

-- Truy vấn danh sách sinh viên đăng ký
select s.id   as studentId,
       s.name as nameStudent,
       a.email,
       s.phone,
       c.id   as courseId,
       c.name as nameCourse,
       e.registered_at,
       e.status
from enrollments e
         join students s on e.student_id = s.id
         join accounts a on s.account_id = a.id
         join courses c on e.course_id = c.id
where e.course_id = course_id_in
    limit page_size_in offset offset_in;

end;

create procedure approve_or_deny_registration(
    student_id_in int,
    course_id_in int,
    new_status varchar(20)
)
begin
update enrollments
set status = new_status
where student_id = student_id_in
  and course_id = course_id_in
  and status = 'WAITING';
end;

create procedure delete_enrollment(
    student_id_in int,
    course_id_in int
)
begin
delete
from enrollments
where student_id = student_id_in
  and course_id = course_id_in
  and status in ('DENIED', 'CANCEL');
end;

# thống kê
create procedure get_total_courses_and_students(
    OUT total_courses int,
    OUT total_students int
)
begin
select count(*) into total_courses from courses;
select count(*) into total_students from students;
end;

create procedure get_students_count_by_course(
    page_size_in int,
    offset_in int,
    OUT total_records_out int
)
begin
    -- Đếm số lượng khóa học đã có học viên đăng ký (không trùng lặp)
select count(distinct c.id)
into total_records_out
from courses c
         left join enrollments e on c.id = e.course_id and e.status = 'CONFIRM';

-- Lấy danh sách khóa học và số học viên đã đăng ký
select c.id                as course_id,
       c.name              as course_name,
       count(e.student_id) as total_students
from courses c
         left join enrollments e on c.id = e.course_id and e.status = 'CONFIRM'
group by c.id, c.name
order by total_students desc
    limit page_size_in offset offset_in;
end;

create procedure get_top_5_most_popular_courses()
begin
select c.id                as course_id,
       c.name              as course_name,
       count(e.student_id) as total_students
from courses c
         join enrollments e on c.id = e.course_id and e.status = 'CONFIRM'
group by c.id, c.name
order by total_students desc
    limit 5;
end;

create procedure get_courses_with_more_than_10_students(
    page_size_in int,
    offset_in int,
    OUT total_courses int
)
begin
    -- Tính tổng số khóa học có hơn 10 sinh viên
select count(*)
into total_courses
from (select c.id
      from courses c
               join enrollments e on c.id = e.course_id and e.status = 'CONFIRM'
      group by c.id
      having count(e.student_id) > 10) as sub;

-- Trả về danh sách các khóa học với phân trang
select c.id                as course_id,
       c.name              as course_name,
       count(e.student_id) as total_students
from courses c
         join enrollments e on c.id = e.course_id and e.status = 'CONFIRM'
group by c.id, c.name
having total_students > 10
order by total_students desc
    limit page_size_in offset offset_in;
end;

create procedure update_password(
    acc_id_in int,
    new_password_in varchar(255)
)
begin
update accounts
set password = new_password_in
where id = acc_id_in;
end;
delimiter //