create table if not exists experiences
(
    id      bigserial not null,
    company varchar(255),
    time    varchar(255),
    primary key (id)
);

create table if not exists users_experiences
(
    user_id       bigint,
    foreign key (user_id) references users (id),
    experience_id bigint,
    foreign key (experience_id) references experiences (id)

);

INSERT INTO experiences (id, company,time) VALUES (1, 'sigma','1 year');
INSERT INTO experiences (id, company,time) VALUES (2, 'epam','2 year');
INSERT INTO experiences (id, company,time) VALUES (3, 'dev','3 year');
INSERT INTO experiences (id, company,time) VALUES (4, 'qwerty','4 year');


INSERT INTO users_experiences (user_id, experience_id) VALUES (1,1);
INSERT INTO users_experiences (user_id, experience_id) VALUES (2,2);
INSERT INTO users_experiences (user_id, experience_id) VALUES (3,3);
INSERT INTO users_experiences (user_id, experience_id) VALUES (4,4);




