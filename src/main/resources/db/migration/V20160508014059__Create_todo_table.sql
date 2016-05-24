-- For H2 Database
create table todo (
  id bigserial not null primary key,
  title varchar(512) not null,
  tag varchar(512) not null,
  priority varchar(512) not null,
  limit_date date,
  content varchar(512) not null,
  status varchar(512) not null,
  created_at timestamp not null,
  updated_at timestamp not null
)
