drop table HashtagsPosts;
drop table hashtags;
drop table comments;
drop table posts;
drop table users;
drop table groups;
create table Groups (
  GroupID varchar(30) primary key
);
create table Users (
  UserID serial primary key,
  Mail varchar(80) unique not null,
  Username varchar(50) unique not null,
  Password varchar(255) not null,
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
  Hashtag varchar(30) primary key
);
create table HashtagsPosts (
  Hashtag varchar(30) references Hashtags,
  PostID integer references Posts,
  primary key(Hashtag, PostID)
);
create index CostCreationDateIndex on Posts(creationdate desc);
create index CommentCreationDateIndex on Comments(creationdate desc);
create index CommentAuthorIndex on Comments(author asc);
create index PostAuthorIndex on Posts(author asc);
create index CommentPostFKeyIndex on Comments(postid asc);
insert into groups(groupid) values('admin'), ('moderator'), ('blogger');
--login: admin pass: admin
insert into users(mail, username, password, usergroup) values ('admin', 'admin', 'jGl25bVBBBW96Qi9Te4V37Fnqchz/Eu4qB9vKrRIqRg=', 'admin');
--login: blogger pass: blogger
insert into users(mail, username, password, usergroup) values ('blogger', 'blogger', 'B1XdNqAjjlzY+Kos7g3gKPkOpz7lVKGFJ4aN26lSIUg=', 'blogger');
--login: moderator pass: moderator
insert into users(mail, username, password, usergroup) values ('moderator', 'moderator', 'z94spRiK+3vdBpHHvviHurp4twmq3ejoxTUynVdR5v4=', 'moderator');