create table resume
(
    uuid      char(36) not null
        constraint resume_pk
            primary key,
    full_name text     not null
);

create table contacts
(
    id          serial   not null
        constraint contacts_pk
            primary key,
    type        text     not null,
    value       text     not null,
    resume_uuid char(36) not null
        constraint contacts_resume_uuid_fk
            references resume
            on update restrict on delete cascade
);

create unique index contacts_uuid_index
    on contacts (resume_uuid, type);
