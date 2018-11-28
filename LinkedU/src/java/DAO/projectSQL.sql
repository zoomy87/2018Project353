
-- ALTER TABLE images
--     ALTER COLUMN USERNAME SET DATA TYPE VARCHAR(100);

-- create video table

CREATE TABLE videos(
    videoId INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    postId INTEGER,
    CONSTRAINT videos_videoId_pk PRIMARY KEY (videoId));
	
--

CREATE TABLE users(
    userName VARCHAR(100),
    password VARCHAR(100) NOT NULL,
    firstName VARCHAR(100) NOT NULL,
    lastName VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    securityQuestion VARCHAR(100),
    securityAnswer VARCHAR(100),
    userType VARCHAR(20) NOT NULL,
    CONSTRAINT users_userName_pk PRIMARY KEY (userName));

ALTER TABLE USERS
    ADD COLUMN PROFILEPICTUREID INTEGER;
ALTER TABLE USERS
    ADD COLUMN EMAILSUBSCRIPTION CHAR(1);

CREATE TABLE images(
    imageId INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    image BLOB,
    USERNAME VARCHAR(100),
    CONSTRAINT images_imageId_pk PRIMARY KEY (imageId),
    CONSTRAINT images_USERNAME_fk FOREIGN KEY (USERNAME)
        REFERENCES USERS(userName));
	
CREATE TABLE student(
    profileId INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),
    dateOfBirth VARCHAR(50),
    height VARCHAR(4),
    weight VARCHAR(4),
    address VARCHAR(500),
    country VARCHAR(20),
    zipcode VARCHAR(20),
    phone VARCHAR(13),
    school VARCHAR(20),
    endYear VARCHAR(4),
    sat VARCHAR(4),
    act VARCHAR(4),
    psat VARCHAR(4),
    certification VARCHAR(500),
    essay VARCHAR(1200),
    userName VARCHAR(100),
    hobbies VARCHAR(500),
    CONSTRAINT student_profileId_pk PRIMARY KEY (profileId),
    CONSTRAINT student_username_fk  FOREIGN KEY (userName)
        REFERENCES users(username));
		

CREATE TABLE universityschedule(
    universityname VARCHAR(100),
    date VARCHAR(100));
		
CREATE TABLE university(
    profileId VARCHAR(50),
    universityEmail VARCHAR(50),
    username VARCHAR(100),
    CONSTRAINT university_profileId_pk PRIMARY KEY (profileId),
    CONSTRAINT university_username_fk FOREIGN KEY (username)
        REFERENCES users(username));

CREATE TABLE universityBridge(
    bridgeId INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    profileId VARCHAR(50),
    username VARCHAR(100),
    CONSTRAINT universityBridge_bridgeId_pk PRIMARY KEY (bridgeId),
    CONSTRAINT universityBridge_profileId_fk FOREIGN KEY (profileId)
        REFERENCES university(profileId),
    CONSTRAINT universityBridge_username_fk FOREIGN KEY (username)
        REFERENCES users(username));

CREATE TABLE recruiter(
    profileId INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),
    university VARCHAR(50),
    username VARCHAR(100),
    department VARCHAR(20),
    phone VARCHAR(13),
    CONSTRAINT recruiter_profileId_pk PRIMARY KEY (profileId),
    CONSTRAINT recruiter_username_fk FOREIGN KEY (username)
        REFERENCES users(username));
		

CREATE TABLE major(
    major VARCHAR(20),
    majorDesc VARCHAR(100),
    CONSTRAINT major_major_pk PRIMARY KEY (major));

CREATE TABLE majorBridge(
    bridgeId INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    major VARCHAR(20),
    username VARCHAR(100),
    CONSTRAINT majorList_bridgeId_pk PRIMARY KEY (bridgeId),
    CONSTRAINT majorList_major_fk FOREIGN KEY (major)
        REFERENCES major(major),
    CONSTRAINT majorList_username_fk FOREIGN KEY (username)
        REFERENCES users(username));
	
	
CREATE TABLE posts(
    postId INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    username VARCHAR(100),
    imageId INTEGER,
    videoId INTEGER,
    datesTime TIMESTAMP default CURRENT_TIMESTAMP,
    CONSTRAINT posts_postId_pk PRIMARY KEY (postId),
    CONSTRAINT posts_username_fk FOREIGN KEY (username)
        REFERENCES users(username));

ALTER TABLE posts
    ADD textcontent varchar(500);
		
--ALTER TABLE posts
  --  DROP CONSTRAINT posts_imageId_fk;

--ALTER TABLE posts
  --  DROP CONSTRAINT posts_videoId_fk;
		
CREATE TABLE comments(
    commentId INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    content VARCHAR(250),
    CONSTRAINT comments_commentId_pk PRIMARY KEY (commentId));

ALTER TABLE comments
    ADD datesTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

CREATE TABLE commentList(
    listId INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    commentId INTEGER,
    postId INTEGER,
    CONSTRAINT commentList_listId_pk PRIMARY KEY (listId),
    CONSTRAINT commentList_commentId_fk FOREIGN KEY (commentId)
        REFERENCES comments(commentId),
    CONSTRAINT commentList_postId_fk FOREIGN KEY (postId)
        REFERENCES posts(postId));

CREATE TABLE ShowcaseUniversity(
	ShowcaseID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),
	UniversityName VARCHAR(100),
    ImageName VARCHAR(50),
    IsShowCase CHAR(1),
    CONSTRAINT showcaseUniversity_showcaseID_pk PRIMARY KEY (ShowcaseID));
	
	
	INSERT into ShowcaseUniversity (UniversityName,ImageName,IsShowCase) VALUES ('Harvard University','harvard.png','1');
	INSERT into ShowcaseUniversity (UniversityName,ImageName,IsShowCase) VALUES ('Princeton University','princeton.png','1');
	INSERT into ShowcaseUniversity (UniversityName,ImageName,IsShowCase) VALUES ('Illinois State University','ISU1.jpg','0');
	INSERT into ShowcaseUniversity (UniversityName,ImageName,IsShowCase) VALUES ('University of Illinois Chicago','UIC.png','0');
	INSERT into ShowcaseUniversity (UniversityName,ImageName,IsShowCase) VALUES ('University of Illinois at Urbana-Champaign','UIUC.jpg','0');
	INSERT into ShowcaseUniversity (UniversityName,ImageName,IsShowCase) VALUES ('Yale University','yale.jpg','0');
	INSERT into ShowcaseUniversity (UniversityName,ImageName,IsShowCase) VALUES ('University of Chicago','universitychicago.jpg','0');
	INSERT into ShowcaseUniversity (UniversityName,ImageName,IsShowCase) VALUES ('Columbia University','columbia.jpg','0');
	INSERT into ShowcaseUniversity (UniversityName,ImageName,IsShowCase) VALUES ('Stanford University','stanford.png','0');
	INSERT into ShowcaseUniversity (UniversityName,ImageName,IsShowCase) VALUES ('Duke University','duke.jpg','0');
		
		------------------------ Insert Scripts-----------------
INSERT INTO USERS
      VALUES ('isu', 'isu', 'illinois state university', 'illinois state university', 'EMAIL', '', '', 'University',1,'0');

INSERT INTO USERS
      VALUES ('uic', 'uic', 'university of illinois chicago', 'university of illinois at chicago', 'EMAIL', '', '', 'University',1,'0');


INSERT INTO USERS
      VALUES ('uiuc', 'uiuc', 'university of illinois urbana champaign', 'university of illinois urbana champaign', 'EMAIL', '', '', 'University',1,'0');


INSERT INTO USERS
      VALUES ('harvard', 'harvard', 'harvard', 'harvard university', 'EMAIL', '', '', 'University',1,'0');

INSERT INTO USERS
      VALUES ('princeton', 'princeton', 'princeton', 'princeton university', 'EMAIL', '', '', 'University',1,'0');
		
