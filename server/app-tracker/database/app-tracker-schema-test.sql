drop database if exists application_tracker_test;
create database application_tracker_test;
use application_tracker_test;

-- create tables and relationships
create table skill (
    skill_id int primary key auto_increment,
    skill_name varchar(100) not null
);

create table company (
	company_id int primary key auto_increment,
    company_name varchar(250) not null,
    company_email varchar(250) not null,
    address varchar(125) null,
    city varchar(50) null,
    state varchar(25) null,
    postal_code varchar(15) null,
	company_phone varchar(50) null
);
create table job_posting (
	posting_id int primary key auto_increment,
    company_id int not null,
    `role` varchar(250) not null,
    `level` varchar(250) not null,
    visa_sponsorship bit not null,
    degree varchar(250) not null,
    constraint fk_job_posting_company
		foreign key(company_id)
        references company(company_id)
);
create table posting_skill (
	posting_id int not null,
    skill_id int not null,
    constraint fk_posting_skill_job_posting
		foreign key(posting_id)
        references job_posting(posting_id),
	constraint fk_posting_skill_skill
		foreign key(skill_id)
        references skill(skill_id)
);

create table `status`(
	status_id int primary key auto_increment,
    status_name varchar(100) not null
);

create table origin(
	origin_id int primary key auto_increment,
    origin_name varchar(100) not null
);

create table application(
	application_id int primary key auto_increment,
    posting_id int not null,
    status_id int not null,
    origin_id int not null,
    date_applied date not null,
    notes varchar(1000) null,
    constraint fk_application_posting_skill
		foreign key(posting_id)
        references posting_skill(posting_id),
	constraint fk_application_status
		foreign key(status_id)
		references status(status_id),
	constraint fk_application_origin
	 	foreign key(origin_id)
        references origin(origin_id)
);

create table interview_type(
	type_id int primary key auto_increment,
	`type` varchar(250) not null
);

create table result(
	result_id int primary key auto_increment,
    `name` varchar(250) not null
);

create table interview(
	interview_id int primary key auto_increment,
    application_id int,
    type_id int,
    result_id int,
    `when` datetime,
    note varchar(1000) null,
    constraint fk_interview_application
		foreign key (application_id)
        references application(application_id),
    constraint fk_interview_interview_type
		foreign key (type_id)
        references interview_type(type_id),
    constraint fk_interview_result
		foreign key (result_id)
        references result(result_id)
);

delimiter //
create procedure set_known_good_state()
begin

    delete from skill;
    alter table skill auto_increment = 1;
    delete from company;
	alter table company auto_increment = 1;
    delete from job_posting;
    alter table job_posting auto_increment = 1;
    delete from posting_skill;
	delete from `status`;
    alter table `status` auto_increment = 1;
	delete from origin;
    alter table origin auto_increment = 1;
    delete from application;
    alter table application auto_increment = 1;
    delete from interview_type;
    alter table interview_type auto_increment = 1;
    delete from result;
    alter table result auto_increment = 1;
    delete from interview;
    alter table interview auto_increment = 1;

	insert into skill(skill_name) values
		('Git'),
        ('Java'),
        ('Spring Boot'),
        ('React'),
        ('SQL'),
        ('Docker'),
        ('C++'),
        ('Haskell'),
        ('Excel');
        
	-- finish populating with values as needed
	-- insert into company
    
end //
-- 4. Change the statement terminator back to the original.
delimiter ;


