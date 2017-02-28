CREATE TABLE `user_info` (
`suspend_time`  datetime NULL ,
`update_time`  datetime NULL ,
`create_time`  datetime NULL ,
`gender`  char(1) NULL ,
`status`  int(8) NULL ,
`cellphone`  varchar(16) NULL ,
`email`  varchar(128) NULL ,
`nick_name`  varchar(64) NULL ,
`password`  varchar(128) NOT NULL ,
`login_name`  varchar(64) NOT NULL ,
`user_id`  varchar(64) NOT NULL
)
;

ALTER TABLE `user_info`
MODIFY COLUMN `user_id`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL FIRST ,
MODIFY COLUMN `login_name`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL AFTER `user_id`,
MODIFY COLUMN `password`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL AFTER `login_name`,
MODIFY COLUMN `nick_name`  varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL AFTER `password`,
MODIFY COLUMN `gender`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL AFTER `nick_name`,
MODIFY COLUMN `cellphone`  varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL AFTER `gender`,
MODIFY COLUMN `email`  varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL AFTER `cellphone`,
MODIFY COLUMN `status`  int(8) NULL DEFAULT NULL AFTER `email`,
MODIFY COLUMN `create_time`  datetime NULL DEFAULT NULL AFTER `status`,
MODIFY COLUMN `update_time`  datetime NULL DEFAULT NULL AFTER `create_time`,
ADD PRIMARY KEY (`user_id`);

ALTER TABLE `user_info`
DROP PRIMARY KEY,
ADD PRIMARY KEY (`user_id`, `login_name`);
