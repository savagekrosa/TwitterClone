create table Groups (
  GroupID varchar(30) primary key
);
create table Users (
  UserID serial primary key,
  Mail varchar(50) unique not null,
  Username varchar(20) unique not null,
  Password varchar(30) not null,
  UserGroup varchar(30) not null references Groups
);
create table Posts (
  PostId serial primary key,
  Content varchar(300) not null,
  Author integer not null references Users,
  CreationDate timestamp not null
);
create table Comments (
  CommentID serial primary key,
  Content varchar(300) not null,
  PostID integer not null references Posts,
  Author integer not null references Users,
  CreationDate timestamp not null
);
create table Hashtags (
  Hashtag varchar(30),
  PostID integer references Posts,
  primary key(Hashtag, PostID)
);
create index CostCreationDateIndex on Posts(creationdate desc);
create index CommentCreationDateIndex on Comments(creationdate desc);
create index CommentAuthorIndex on Comments(author asc);
create index PostAuthorIndex on Posts(author asc);
create index CommentPostFKeyIndex on Comments(postid asc);
insert into groups(groupid) values('admin'), ('moderator'), ('blogger');
