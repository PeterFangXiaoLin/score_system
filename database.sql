create database scoreSystem;
drop table Admin;
drop database scoreSystem;
create database ScoreManagement;

use ScoreManagement;

create table Admin
(
    admin_id varchar(255) primary key ,
    admin_password varchar(255)
);

insert into Admin values ('admin', '123456');
insert into Admin values ('张三', 'abc');

create index idx_major_name on major(major_name);
create index idx_class_name on class(class_name);
create index idx_college_name on college(college_name);

create table student
(
    student_id int(9) auto_increment primary key ,
    student_name varchar(255),
    student_age int,
    student_sex varchar(255),
    student_grade varchar(255),
    student_major varchar(255),
    student_class varchar(255),
    student_college varchar(255),
    student_score float default 0,
    foreign key (student_major) references major(major_name),
    foreign key (student_class) references class(class_name),
    foreign key (student_college) references college(college_name)
)auto_increment=100000000;

create table course
(
    course_id int auto_increment primary key ,
    course_name varchar(255),
    course_theory int,
    course_practice int,
    course_score float,
    course_type varchar(255)
)auto_increment=10000;

create index score_index on course(course_score);

create table teacher
(
    teacher_id int auto_increment primary key ,
    teacher_name varchar(255) ,
    teacher_sex varchar(255),
    teacher_age int,
    teacher_title varchar(255)
)auto_increment=10001;

create index teacher_name_index on teacher(teacher_name);

create table major
(
    major_id varchar(255) primary key ,
    major_name varchar(255)
);


create table class
(
    class_id varchar(255) primary key ,
    class_name varchar(255),
    major_name varchar(255),
    foreign key (major_name) references major(major_name)
);

create table college
(
    college_id varchar(255) primary key ,
    college_name varchar(255)
);

create table student_account
(
    student_id int primary key ,
    student_password varchar(255) not null default '123456',
    foreign key (student_id) references Student(student_id)
);

create table teacher_account
(
    teacher_id int primary key ,
    teacher_password varchar(255) not null default '123456',
    foreign key (teacher_id) references teacher(teacher_id)
);

drop table class_course;
create table class_course
(
    class_id varchar(255) ,
    course_id int,
    foreign key (class_id) references class(class_id),
    foreign key (course_id) references course(course_id),
    primary key (class_id, course_id)
);

create table teacher_class
(
    teacher_id int primary key ,
    class_id varchar(255) ,
    foreign key (teacher_id) references teacher(teacher_id),
    foreign key (class_id) references class(class_id)
);

create table teacher_course
(
    teacher_id int,
    course_id int ,
    foreign key (teacher_id) references teacher(teacher_id),
    foreign key (course_id) references course(course_id),
    primary key (teacher_id, course_id)
);

drop table reports;

create table reports
(
    student_id int,
    course_id int,
    course_name varchar(255),
    usual_grades int default null,
    final_grades int default null,
    total_grades int default null,
    gpa float default null,
    score float ,
    staus int default 0,
    foreign key (student_id) references student(student_id),
    foreign key (course_id) references course(course_id),
    primary key (student_id, course_id)
);

drop trigger delete_student;
drop trigger delete_teacher;

create trigger delete_student before delete
    on student for each row
    begin
        delete from student_account where student_id = OLD.student_id;
        delete from reports where student_id = OLD.student_id;
    end;

create trigger delete_teacher before delete
    on Teacher for each row
    begin
        delete from teacher_account where teacher_id = OLD.teacher_id;
        delete from teacher_course where teacher_id = OLD.teacher_id;
    end;

drop trigger student_account_create;
drop trigger teacher_account_create;

create trigger student_account_create after insert
    on Student for each row
    insert into student_account (student_id) values (NEW.student_id);

create trigger teacher_account_create after insert
    on Teacher for each row
    insert into teacher_account(teacher_id) values (NEW.teacher_id);

drop trigger teacher_update;

create trigger teacher_update after update
    on teacher for each row
    begin
        update course set teacher_name = NEW.teacher_name where teacher_name = OLD.teacher_name;
    end;

create view view_reports2 as
    select reports.student_id, reports.course_id, teacher_id from reports, course, teacher where teacher.teacher_name = course.teacher_name and course.course_id = reports.course_id;


drop view view_reports;
create view view_reports as
    select reports.*, course.course_type from reports, course where reports.course_id = course.course_id;

insert into major (major_id, major_name) VALUES ('M01','人工智能');
insert into major (major_id, major_name) values ('M02', '计科');
insert into major (major_id, major_name) VALUES ('M03','软工');
insert into major (major_id, major_name) VALUES ('M04','网工');
insert into major (major_id, major_name) VALUES ('M05', '外语');

insert into class(class_id, class_name, major_name) VALUES ('CL01', '人工智能191', '人工智能');
insert into class(class_id, class_name, major_name) values ('CL02','人工智能192','人工智能');
insert into class(class_id, class_name, major_name) VALUES ('CL03', '计科211', '计科');
insert into class(class_id, class_name, major_name) VALUES ('CL04', '计科211', '计科');
insert into class(class_id, class_name, major_name) VALUES ('CL05', '软工201', '软工');
insert into class(class_id, class_name, major_name) VALUES ('CL06', '软工231','软工');
insert into class(class_id, class_name, major_name) VALUES ('CL07', '网工181', '网工');
insert into class(class_id, class_name, major_name) VALUES ('CL08', '网工192','网工');
insert into class(class_id, class_name, major_name) VALUES ('CL09', '网工241', '网工');
insert into class(class_id, class_name, major_name) VALUES ('CL10', '外语181', '外语');
insert into class(class_id, class_name, major_name) VALUES ('CL11', '计科212', '计科');
insert into class(class_id, class_name, major_name) VALUES ('CL12', '计科213', '计科');
insert into class(class_id, class_name, major_name) VALUES ('CL13', '计科214', '计科');

insert into college(college_id, college_name) VALUES ('CO01', '信科');
insert into college(college_id, college_name) VALUES ('CO02', '外语');
insert into college(college_id, college_name) VALUES ('CO03', '人文');
insert into college(college_id, college_name) VALUES ('C004', '经贸');

delete from student;
delete from student_account;

insert into student (student_name, student_age, student_sex, student_grade, student_major, student_class, student_college) values
                    ('张三', 20, '男', '21级','计科','计科211','信科');
insert into student(student_name, student_age, student_sex, student_grade, student_major, student_class, student_college) VALUES
                    ('李四', 21, '男', '20级', '人工智能', '人工智能191','信科');
insert into student(student_name, student_age, student_sex, student_grade, student_major, student_class, student_college) VALUES
                    ('王五', 19, '女', '23级', '软工', '软工231', '信科');
insert into student(student_name, student_age, student_sex, student_grade, student_major, student_class, student_college) VALUES
                    ('洛克', 18, '男', '24级', '网工', '网工241','信科');
insert into student (student_name, student_age, student_sex, student_grade, student_major, student_class, student_college) VALUES
                    ('尼克', 22, '男', '19级','外语', '外语181', '外语');

insert into teacher(teacher_name, teacher_sex, teacher_age, teacher_title) VALUES
                    ('叶文洁', '女', 40, '教授');
insert into teacher(teacher_name, teacher_sex, teacher_age, teacher_title) VALUES
                    ('周文', '女', 35, '讲师');
insert into teacher(teacher_name, teacher_sex, teacher_age, teacher_title) VALUES
                    ('陈亮','男', 45, '副教授');
insert into teacher(teacher_name, teacher_sex, teacher_age, teacher_title) VALUES
                    ('Alice', '女', 30, '助教');
insert into teacher(teacher_name, teacher_sex, teacher_age, teacher_title) VALUES
                    ('Bob', '男', 50, '高级工程师');
insert into teacher(teacher_name, teacher_sex, teacher_age, teacher_title) VALUES
                    ('Amino', '女', 35, '教授');
insert into teacher(teacher_name, teacher_sex, teacher_age, teacher_title) VALUES
                    ('寒冰掌', '男', 80, '教授');
insert into teacher (teacher_name, teacher_sex, teacher_age, teacher_title) VALUES
                    ('景天', '男', 45, '讲师');
insert into teacher (teacher_name, teacher_sex, teacher_age, teacher_title) VALUES
                    ('龙葵', '女', 35, '助教');

delete from course;

insert into course(course_name, course_theory, course_practice, course_score, course_type, teacher_name) VALUES
                ('离散数学', 24, 32, 3, '专业必修', '叶文洁');
insert into course(course_name, course_theory, course_practice, course_score, course_type, teacher_name) VALUES
                ('机器学习', 24, 12, 4, '专业必修', '陈亮');
insert into course(course_name, course_theory, course_practice, course_score, course_type, teacher_name) VALUES
                ('数字电路', 32, 12, 4, '专业选修', 'Bob1');
insert into course(course_name, course_theory, course_practice, course_score, course_type, teacher_name) VALUES
                ('数字电路', 32, 12, 4, '专业选修', 'Alice');
insert into course(course_name, course_theory, course_practice, course_score, course_type, teacher_name) values
                ('马原', 32, 12, 4, '公共必修', '周文');
insert into course(course_name, course_theory, course_practice, course_score, course_type,teacher_name) values
                ('毛概', 32, 12, 4, '公共必修', '周文');
insert into course(course_name, course_theory, course_practice, course_score, course_type, teacher_name) values
                ('思政', 32, 12, 4, '公共必修', '周文');
insert into course(course_name, course_theory, course_practice, course_score, course_type, teacher_name) values
                ('高等数学', 48, 0, 4, '公共必修', '景天');
insert into course(course_name, course_theory, course_practice, course_score, course_type, teacher_name) values
                ('大学英语', 48, 12, 4, '公共必修', '龙葵');



create index teacher_name_course on course(teacher_name);

drop trigger teacher_course_delete;
drop trigger teacher_course_insert;

create trigger teacher_course_insert after insert
    on course for each row
    insert into teacher_course values ((select teacher_id from teacher where teacher.teacher_name = NEW.teacher_name), New.course_id);

create trigger teacher_course_delete before delete
    on course for each row
    delete from teacher_course where teacher_course.course_id = OLD.course_id;

insert into class_course (class_id, course_id) values ('CL01', 117);
insert into class_course (class_id, course_id) VALUES ('CL02', 118);
insert into class_course (class_id, course_id) VALUES ('CL03', 120);

insert into reports (student_id, course_id, course_name, score) VALUES (1007, 117, '离散数学', 3);
insert into reports (student_id, course_id, course_name, score) VALUES (1007, 118, '机器学习', 4);
insert into reports (student_id, course_id, course_name, score) VALUES (1008, 119, '数字电路', 4);
insert into reports (student_id, course_id, course_name, score) VALUES (1009, 119, '数字电路', 4);
insert into reports (student_id, course_id, course_name, score) VALUES (1010, 121, '马原', 4);
insert into reports (student_id, course_id, course_name, score) VALUES (1010, 122, '毛概', 4);
insert into reports (student_id, course_id, course_name, score) VALUES (1008, 117, '离散数学', 3);
insert into reports (student_id, course_id, course_name, score) VALUES (1009, 117, '离散数学', 3);
insert into reports (student_id, course_id, course_name, score) VALUES (1010, 117, '离散数学', 3);
insert into reports (student_id, course_id, course_name, score) VALUES (1011, 117, '离散数学', 3);
insert into reports (student_id, course_id, course_name, score) VALUES (1007, 124, '高等数学', 4);

insert into reports (student_id, course_id, course_name, score) VALUES (1008, 124, '高等数学', 4);
insert into reports (student_id, course_id, course_name, score) VALUES (1009, 124, '高等数学', 4);
insert into reports (student_id, course_id, course_name, score) VALUES (1010, 124, '高等数学',4);
insert into reports (student_id, course_id, course_name, score) values (1011, 124, '高等数学', 4);

