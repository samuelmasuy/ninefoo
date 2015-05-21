DROP TABLE IF EXISTS status, member, project, activity, activitylog, config, member_works_project, member_manages_project, activity_prerequisite, member_config;

CREATE TABLE status(
	statid INT(11) AUTO_INCREMENT,
	value VARCHAR(150) NOT NULL,
	PRIMARY KEY (statid)
);

CREATE TABLE member(
	memid INT(11) AUTO_INCREMENT,
	fname VARCHAR(50) NOT NULL,
	lname VARCHAR(50) NOT NULL,
	username VARCHAR(50) NOT NULL,
	password VARCHAR(50) NOT NULL,
	reg_date DATETIME DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (memid),
	UNIQUE (username)
);

CREATE TABLE project (
	proid INT(11) AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
	create_date DATETIME DEFAULT CURRENT_TIMESTAMP,
	update_date DATETIME,
	budget DECIMAL(14,2),
	deadline_date DATETIME,
	description VARCHAR(255),
	creator INT(11) NOT NULL,
	PRIMARY KEY (proid),
	FOREIGN KEY (creator) REFERENCES member(memid) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE activity(
	actid INT(11) AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
	duration_day INT(11),
	description VARCHAR(255),
	create_date DATETIME DEFAULT CURRENT_TIMESTAMP,
	project INT(11) NOT NULL,
	member INT(11) NOT NULL,
	PRIMARY KEY (actid),
	FOREIGN KEY (project) REFERENCES project(proid) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (member) REFERENCES member(memid) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE activitylog(
	actlogid INT(11) AUTO_INCREMENT,
	proid INT(11) NOT NULL,
	memid INT(11) NOT NULL,
	statid INT(11) NOT NULL,
	actid INT(11),
	create_date DATETIME DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (actlogid),
	FOREIGN KEY (proid) REFERENCES project(proid) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (memid) REFERENCES member(memid) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (statid) REFERENCES status(statid) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (actid) REFERENCES activity(actid) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE config(
	confid INT(11) AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
	PRIMARY KEY (confid)
);

CREATE TABLE member_works_project(
	member INT(11) NOT NULL,
	project INT(11) NOT NULL,
	PRIMARY KEY (member, project),
	FOREIGN KEY (member) REFERENCES member(memid) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (project) REFERENCES project(proid) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE member_manages_project(
	member INT(11) NOT NULL,
	project INT(11) NOT NULL,
	PRIMARY KEY (member, project),
	FOREIGN KEY (member) REFERENCES member(memid) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (project) REFERENCES project(proid) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE activity_prerequisite(
	activity1 INT(11) NOT NULL,
	activity2 INT(11) NOT NULL,
	PRIMARY KEY (activity1, activity2),
	FOREIGN KEY (activity1) REFERENCES activity(actid) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (activity2) REFERENCES activity(actid) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE member_config(
	member INT(11) NOT NULL,
	config INT(11) NOT NULL,
	value INT(11) NOT NULL,
	PRIMARY KEY (member, config),
	FOREIGN KEY (member) REFERENCES member(memid) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (config) REFERENCES config(confid) ON UPDATE CASCADE ON DELETE CASCADE
);