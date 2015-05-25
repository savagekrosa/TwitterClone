DROP TABLE HashtagsPosts;
DROP TABLE hashtags;
DROP TABLE comments;
DROP TABLE posts;
DROP TABLE users;
DROP TABLE groups;
CREATE TABLE Groups (
  GroupID VARCHAR(30) PRIMARY KEY
);
CREATE TABLE Users (
  UserID    SERIAL PRIMARY KEY,
  Mail      VARCHAR(80) UNIQUE NOT NULL,
  Username  VARCHAR(50) UNIQUE NOT NULL,
  Password  VARCHAR(255)       NOT NULL,
  UserGroup VARCHAR(30)        NOT NULL REFERENCES Groups
);
CREATE TABLE Posts (
  PostId       SERIAL PRIMARY KEY,
  Content      VARCHAR(300) NOT NULL,
  Author       INTEGER      NOT NULL REFERENCES Users,
  CreationDate TIMESTAMP    NOT NULL
);
CREATE TABLE Comments (
  CommentID    SERIAL PRIMARY KEY,
  Content      VARCHAR(300) NOT NULL,
  PostID       INTEGER      NOT NULL REFERENCES Posts,
  Author       INTEGER      NOT NULL REFERENCES Users,
  CreationDate TIMESTAMP    NOT NULL
);
CREATE TABLE Hashtags (
  Hashtag VARCHAR(30) PRIMARY KEY
);
CREATE TABLE HashtagsPosts (
  Hashtag VARCHAR(30) REFERENCES Hashtags,
  PostID  INTEGER REFERENCES Posts,
  PRIMARY KEY (Hashtag, PostID)
);
CREATE INDEX CostCreationDateIndex ON Posts (creationdate DESC);
CREATE INDEX CommentCreationDateIndex ON Comments (creationdate DESC);
CREATE INDEX CommentAuthorIndex ON Comments (author ASC);
CREATE INDEX PostAuthorIndex ON Posts (author ASC);
CREATE INDEX CommentPostFKeyIndex ON Comments (postid ASC);
INSERT INTO groups (groupid) VALUES ('admin'), ('moderator'), ('blogger');
--login: admin pass: admin
INSERT INTO users (mail, username, password, usergroup)
VALUES ('admin', 'admin', 'jGl25bVBBBW96Qi9Te4V37Fnqchz/Eu4qB9vKrRIqRg=', 'admin');
--login: blogger pass: blogger
INSERT INTO users (mail, username, password, usergroup)
VALUES ('blogger', 'blogger', 'B1XdNqAjjlzY+Kos7g3gKPkOpz7lVKGFJ4aN26lSIUg=', 'blogger');
--login: moderator pass: moderator
INSERT INTO users (mail, username, password, usergroup)
VALUES ('moderator', 'moderator', 'z94spRiK+3vdBpHHvviHurp4twmq3ejoxTUynVdR5v4=', 'moderator');