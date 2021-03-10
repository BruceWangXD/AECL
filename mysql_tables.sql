create schema if not exists aecl collate utf8_general_ci;

create table if not exists ADMIN
(
    admin_id int auto_increment
        primary key,
    email varchar(255) null,
    password varchar(255) null
);

create table if not exists CATEGORY_EXERCISE
(
    category_exercise_id int auto_increment
        primary key,
    content varchar(255) null
);

create table if not exists COURSE
(
    course_id int auto_increment
        primary key,
    course_name varchar(255) null,
    course_code varchar(255) null,
    population int null,
    instructor_name varchar(255) null
);

create table if not exists EXERCISE
(
    exercise_id int auto_increment
        primary key,
    ecategory_id int not null,
    `like` int null,
    difficulty int null,
    solution varchar(10000) null,
    content varchar(10000) null,
    title varchar(255) null,
    constraint EXERCISE_CATEGORY_EXERCISE_category_exercise_id_fk
        foreign key (ecategory_id) references CATEGORY_EXERCISE (category_exercise_id)
);

create table if not exists PERSON_INFO
(
    person_id int auto_increment
        primary key,
    email varchar(255) null,
    user_name varchar(255) null,
    password varchar(255) null,
    academic_year int null,
    pcourse_1 int not null,
    pcourse_2 int not null,
    pcourse_3 int not null,
    pcourse_4 int not null,
    theme tinyint(1) null,
    constraint PERSON_INFO_COURSE_course_id_fk
        foreign key (pcourse_1) references COURSE (course_id),
    constraint PERSON_INFO_COURSE_course_id_fk_2
        foreign key (pcourse_2) references COURSE (course_id),
    constraint PERSON_INFO_COURSE_course_id_fk_3
        foreign key (pcourse_3) references COURSE (course_id),
    constraint PERSON_INFO_COURSE_course_id_fk_4
        foreign key (pcourse_4) references COURSE (course_id)
);

create table if not exists FAVOURITE_EXERCISE
(
    favourite_id int auto_increment
        primary key,
    fexercise_id int not null,
    fperson_id int not null,
    constraint FAVOURITE_EXERCISE_EXERCISE_exercise_id_fk
        foreign key (fexercise_id) references EXERCISE (exercise_id),
    constraint FAVOURITE_EXERCISE_PERSON_INFO_person_id_fk
        foreign key (fperson_id) references PERSON_INFO (person_id)
);

create table if not exists MESSAGE
(
    message_id int auto_increment
        primary key,
    sender_id int not null,
    receiver_id int not null,
    title varchar(255) null,
    content varchar(10000) null,
    time timestamp null,
    constraint MESSAGE_PERSON_INFO_person_id_fk
        foreign key (sender_id) references PERSON_INFO (person_id),
    constraint MESSAGE_PERSON_INFO_person_id_fk_2
        foreign key (receiver_id) references PERSON_INFO (person_id)
);

create table if not exists POST
(
    post_id int auto_increment
        primary key,
    pcourse_id int not null,
    `like` int null,
    is_pinned tinyint(1) null,
    pperson_id int not null,
    post_time timestamp null,
    post_title varchar(255) null,
    post_content varchar(10000) null,
    constraint POST_COURSE_course_id_fk
        foreign key (pcourse_id) references COURSE (course_id),
    constraint POST_PERSON_INFO_person_id_fk
        foreign key (pperson_id) references PERSON_INFO (person_id)
);

create table if not exists COMMENT
(
    comment_id int auto_increment
        primary key,
    cpost_id int not null,
    cperson_id int not null,
    comment_time timestamp null,
    content varchar(10000) null,
    constraint COMMENT_PERSON_INFO_person_id_fk
        foreign key (cperson_id) references PERSON_INFO (person_id),
    constraint COMMENT_POST_post_id_fk
        foreign key (cpost_id) references POST (post_id)
);

create table if not exists FAVOURITE_POST
(
    favourite_id int auto_increment
        primary key,
    fpost_id int not null,
    fperson_id int not null,
    constraint FAVOURITE_POST_PERSON_INFO_person_id_fk
        foreign key (fperson_id) references PERSON_INFO (person_id),
    constraint FAVOURITE_POST_POST_post_id_fk
        foreign key (fpost_id) references POST (post_id)
);

