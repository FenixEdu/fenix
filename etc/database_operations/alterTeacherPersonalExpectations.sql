alter table TEACHER_PERSONAL_EXPECTATION add column AUTO_EVALUATION longtext ;

create table TEACHER_AUTO_EVALUATION_DEFINITION_PERIOD (
        ID_INTERNAL int(11) NOT NULL auto_increment,
        ACK_OPT_LOCK int(11) default NULL,
        START_DATE_YEAR_MONTH_DAY VARCHAR(255) NOT NULL DEFAULT '00-00-00',
        END_DATE_YEAR_MONTH_DAY VARCHAR(255) NOT NULL DEFAULT '00-00-00',
        KEY_EXECUTION_YEAR int(11) NOT NULL,
        KEY_DEPARTMENT int(11) NOT NULL,
	KEY_ROOT_DOMAIN_OBJECT int(11) NOT NULL DEFAULT '1',
        PRIMARY KEY (ID_INTERNAL),
        UNIQUE U1 (KEY_DEPARTMENT,KEY_EXECUTION_YEAR)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

