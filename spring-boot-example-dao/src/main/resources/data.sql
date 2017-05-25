INSERT INTO `user` (`userId`,`username`,`password`,`realName`,`idCard`,`avatar`,`status`,`createTime`,`updateTime`) VALUES ('93201214-3b98-11e7-8b90-efc0f68ee6d7','admin','$2a$04$xA.MDsq9QSAT5aGS32uWme2x/zCAD8mJvwMouFHl6uIuVkSF9Dolu','柳初雪','371082199006232567',NULL,0,'2017-05-18 15:06:54','2017-05-18 15:06:54');
INSERT INTO `user` (`userId`,`username`,`password`,`realName`,`idCard`,`avatar`,`status`,`createTime`,`updateTime`) VALUES ('93205ca6-3b98-11e7-8b90-efc0f68ee6d7','bhy','$2a$04$xA.MDsq9QSAT5aGS32uWme2x/zCAD8mJvwMouFHl6uIuVkSF9Dolu','鲍含烟','510601198705181303',NULL,0,'2017-05-18 15:06:54','2017-05-18 15:06:54');
INSERT INTO `user` (`userId`,`username`,`password`,`realName`,`idCard`,`avatar`,`status`,`createTime`,`updateTime`) VALUES ('93206660-3b98-11e7-8b90-efc0f68ee6d7','ggf','$2a$04$xA.MDsq9QSAT5aGS32uWme2x/zCAD8mJvwMouFHl6uIuVkSF9Dolu','葛格菲','420528199007169566',NULL,0,'2017-05-18 15:06:54','2017-05-18 15:06:54');
INSERT INTO `user` (`userId`,`username`,`password`,`realName`,`idCard`,`avatar`,`status`,`createTime`,`updateTime`) VALUES ('93206f66-3b98-11e7-8b90-efc0f68ee6d7','lhy','$2a$04$xA.MDsq9QSAT5aGS32uWme2x/zCAD8mJvwMouFHl6uIuVkSF9Dolu','柳虹影','140500197808158426',NULL,0,'2017-05-18 15:06:54','2017-05-18 15:06:54');
INSERT INTO `user` (`userId`,`username`,`password`,`realName`,`idCard`,`avatar`,`status`,`createTime`,`updateTime`) VALUES ('93207880-3b98-11e7-8b90-efc0f68ee6d7','hfs','$2a$04$xA.MDsq9QSAT5aGS32uWme2x/zCAD8mJvwMouFHl6uIuVkSF9Dolu','韩飞双','510112199009235108',NULL,0,'2017-05-18 15:06:54','2017-05-18 15:06:54');
INSERT INTO `user` (`userId`,`username`,`password`,`realName`,`idCard`,`avatar`,`status`,`createTime`,`updateTime`) VALUES ('9320810e-3b98-11e7-8b90-efc0f68ee6d7','ljw','$2a$04$xA.MDsq9QSAT5aGS32uWme2x/zCAD8mJvwMouFHl6uIuVkSF9Dolu','吕俊伟','37160219820517647X',NULL,0,'2017-05-18 15:06:54','2017-05-18 15:06:54');
INSERT INTO `user` (`userId`,`username`,`password`,`realName`,`idCard`,`avatar`,`status`,`createTime`,`updateTime`) VALUES ('932089b0-3b98-11e7-8b90-efc0f68ee6d7','fxj','$2a$04$xA.MDsq9QSAT5aGS32uWme2x/zCAD8mJvwMouFHl6uIuVkSF9Dolu','冯修杰','371501197407275779',NULL,0,'2017-05-18 15:06:54','2017-05-18 15:06:54');
INSERT INTO `user` (`userId`,`username`,`password`,`realName`,`idCard`,`avatar`,`status`,`createTime`,`updateTime`) VALUES ('93209a2c-3b98-11e7-8b90-efc0f68ee6d7','xxm','$2a$04$xA.MDsq9QSAT5aGS32uWme2x/zCAD8mJvwMouFHl6uIuVkSF9Dolu','许宣朗','653129197201147855',NULL,0,'2017-05-18 15:06:54','2017-05-18 15:06:54');
INSERT INTO `user` (`userId`,`username`,`password`,`realName`,`idCard`,`avatar`,`status`,`createTime`,`updateTime`) VALUES ('9320a468-3b98-11e7-8b90-efc0f68ee6d7','syb','$2a$04$xA.MDsq9QSAT5aGS32uWme2x/zCAD8mJvwMouFHl6uIuVkSF9Dolu','时彦博','210181197104107377',NULL,0,'2017-05-18 15:06:54','2017-05-18 15:06:54');
INSERT INTO `user` (`userId`,`username`,`password`,`realName`,`idCard`,`avatar`,`status`,`createTime`,`updateTime`) VALUES ('9320ac60-3b98-11e7-8b90-efc0f68ee6d7','cmz','$2a$04$xA.MDsq9QSAT5aGS32uWme2x/zCAD8mJvwMouFHl6uIuVkSF9Dolu','常明哲','211121198904261232',NULL,0,'2017-05-18 15:06:54','2017-05-18 15:06:54');

INSERT INTO `role` (`roleId`, `roleName`, `roleDesc`, `roleParentId`, `permissionName`, `status`, `createTime`, `updateTime`) VALUES (NULL, '超级管理员', NULL, NULL, 'ROLE_ADMIN', '0', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO `role` (`roleId`, `roleName`, `roleDesc`, `roleParentId`, `permissionName`, `status`, `createTime`, `updateTime`) VALUES (NULL, '普通用户', NULL, NULL, 'ROLE_USER', '0', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO `user_has_role` (`userId`, `roleId`)
VALUES
  ('93201214-3b98-11e7-8b90-efc0f68ee6d7', 1),
  ('93205ca6-3b98-11e7-8b90-efc0f68ee6d7', 2),
  ('93206660-3b98-11e7-8b90-efc0f68ee6d7', 2),
  ('93206f66-3b98-11e7-8b90-efc0f68ee6d7', 2),
  ('93207880-3b98-11e7-8b90-efc0f68ee6d7', 2),
  ('9320810e-3b98-11e7-8b90-efc0f68ee6d7', 2),
  ('932089b0-3b98-11e7-8b90-efc0f68ee6d7', 2),
  ('93209a2c-3b98-11e7-8b90-efc0f68ee6d7', 2),
  ('9320a468-3b98-11e7-8b90-efc0f68ee6d7', 2),
  ('9320ac60-3b98-11e7-8b90-efc0f68ee6d7', 2);

INSERT INTO `module` (`moduleId`, `moduleName`, `moduleParentId`, `moduleUrl`, `status`, `createTime`, `updateTime`) VALUES (1, '用户管理', NULL, '/admin/userManage', '0', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO `role_has_module` (`roleId`, `moduleId`) VALUES ('1', '1');
