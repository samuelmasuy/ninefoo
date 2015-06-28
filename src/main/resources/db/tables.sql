DROP TABLE IF EXISTS member;
CREATE TABLE member(
        member_id          INTEGER     PRIMARY KEY         AUTOINCREMENT,
        first_name         VARCHAR(50),
        last_name          VARCHAR(50),
        username           VARCHAR(50),
        password           VARCHAR(50),
        register_date      DATETIME    DEFAULT (datetime(CURRENT_TIMESTAMP, 'localtime')),
        
        UNIQUE (username)  
);


DROP TABLE IF EXISTS role;
CREATE TABLE role(
        role_id            INTEGER     PRIMARY KEY         AUTOINCREMENT,
        role_name          VARCHAR(50),
        description        VARCHAR(255),
        
        UNIQUE (role_name) 
);
    

DROP TABLE IF EXISTS status;
CREATE TABLE status(
        status_id          INTEGER     PRIMARY KEY         AUTOINCREMENT,
        status_name        VARCHAR(50) 
);
    

DROP TABLE IF EXISTS project;
CREATE TABLE project(
        project_id         INTEGER     PRIMARY KEY         AUTOINCREMENT,
        project_name       VARCHAR(50) NOT NULL,
        create_date        DATETIME    DEFAULT (datetime(CURRENT_TIMESTAMP, 'localtime')),
        start_date         DATETIME,
        update_date        DATETIME    DEFAULT (datetime(CURRENT_TIMESTAMP, 'localtime')),
        budget             DECIMAL(14, 2),
        deadline_date      DATETIME,
        description        VARCHAR(255) 
);
    

DROP TABLE IF EXISTS project_member;
CREATE TABLE project_member(
        project_id         INT,
        member_id          INT,
        role_id            INT,

        PRIMARY KEY (project_id, member_id, role_id),
        FOREIGN KEY (project_id)       REFERENCES project(project_id)   ON UPDATE CASCADE ON DELETE CASCADE,
        FOREIGN KEY (member_id)        REFERENCES member(member_id)     ON UPDATE CASCADE ON DELETE CASCADE,
        FOREIGN KEY (role_id)          REFERENCES role(role_id)         ON UPDATE CASCADE ON DELETE CASCADE 
);
    

DROP TABLE IF EXISTS activity;
CREATE TABLE activity(
        activity_id            INTEGER     PRIMARY KEY         AUTOINCREMENT,
        activity_label         VARCHAR(50),
        description            VARCHAR(255),
        duration               INT,
        optimistic_duration    INT,
        likely_duration        INT,
        pessimistic_duration   INT,
        create_date            DATETIME    DEFAULT (datetime(CURRENT_TIMESTAMP, 'localtime')),
        update_date            DATETIME    DEFAULT (datetime(CURRENT_TIMESTAMP, 'localtime')),
        start_date             DATETIME,
        finish_date            DATETIME,
        project_id             INT         NOT NULL,
        member_id              INT,

        FOREIGN KEY (project_id)       REFERENCES project(project_id)   ON UPDATE CASCADE ON DELETE CASCADE,
        FOREIGN KEY (member_id)        REFERENCES member(member_id)     ON UPDATE CASCADE ON DELETE CASCADE 
);
    

DROP TABLE IF EXISTS activity_relation;
CREATE TABLE activity_relation(
        activity_id            INT,
        prereq_activity_id     INT,

        PRIMARY KEY (activity_id, prereq_activity_id),
        FOREIGN KEY (activity_id)          REFERENCES activity(activity_id)  ON UPDATE CASCADE ON DELETE CASCADE,
        FOREIGN KEY (prereq_activity_id)   REFERENCES activity(activity_id)  ON UPDATE CASCADE ON DELETE CASCADE 
);
    

DROP TABLE IF EXISTS activity_log;
CREATE TABLE activity_log(
        activity_log_id        INTEGER     PRIMARY KEY        AUTOINCREMENT,
        project_id             INT,
        member_id              INT,
        status_id              INT,
        activity_id            INT,
        create_date            DATETIME    DEFAULT (datetime(CURRENT_TIMESTAMP, 'localtime')),

        FOREIGN KEY (project_id)   REFERENCES project(project_id)       ON UPDATE CASCADE ON DELETE CASCADE,
        FOREIGN KEY (member_id)    REFERENCES member(member_id)         ON UPDATE CASCADE ON DELETE CASCADE,
        FOREIGN KEY (status_id)    REFERENCES status(status_id)         ON UPDATE CASCADE ON DELETE CASCADE,
        FOREIGN KEY (activity_id)  REFERENCES activity(activity_id)     ON UPDATE CASCADE ON DELETE CASCADE  
);
    

DROP TABLE IF EXISTS config;
CREATE TABLE config(
        config_id          INTEGER         PRIMARY KEY     AUTOINCREMENT,
        config_name        VARCHAR(50)     NOT NULL 
);
    