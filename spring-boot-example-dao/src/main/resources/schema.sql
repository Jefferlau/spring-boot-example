-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema spring_boot_example
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema spring_boot_example
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `spring_boot_example` DEFAULT CHARACTER SET utf8mb4 ;
USE `spring_boot_example` ;

-- -----------------------------------------------------
-- Table `spring_boot_example`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `spring_boot_example`.`user`;

CREATE TABLE IF NOT EXISTS `spring_boot_example`.`user` (
  `userId` VARCHAR(36) NOT NULL,
  `username` VARCHAR(16) NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(60) DEFAULT NULL COMMENT '密码',
  `realName` VARCHAR(64) NOT NULL COMMENT '姓名',
  `idCard` VARCHAR(18) NOT NULL COMMENT '身份证号码',
  `avatar` VARCHAR(255) NULL DEFAULT NULL COMMENT '用户头像地址',
  `status` TINYINT(4) NOT NULL DEFAULT '0',
  `createTime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`userId`),
  UNIQUE INDEX `name_idCard_UNIQUE` (`realName`, `idCard` ASC)
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COMMENT = '用户表';

-- -----------------------------------------------------
-- Table `spring_boot_example`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `spring_boot_example`.`role` ;

CREATE TABLE IF NOT EXISTS `spring_boot_example`.`role` (
  `roleId` INT(11) NOT NULL AUTO_INCREMENT,
  `roleName` VARCHAR(20) NULL DEFAULT NULL COMMENT '角色名称',
  `roleDesc` VARCHAR(32) NULL DEFAULT NULL COMMENT '角色描述',
  `roleParentId` INT(11) NULL COMMENT '父角色',
  `permissionName` VARCHAR(32) NOT NULL DEFAULT 'ROLE_DEFAULT' COMMENT '权限标识',
  `status` TINYINT(4) NOT NULL DEFAULT '0',
  `createTime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`roleId`),
  UNIQUE INDEX `roleName_UNIQUE` (`roleName` ASC))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COMMENT = '系统角色表';

-- -----------------------------------------------------
-- Table `spring_boot_example`.`user_has_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `spring_boot_example`.`user_has_role` ;

CREATE TABLE IF NOT EXISTS `spring_boot_example`.`user_has_role` (
  `userId` VARCHAR(36) NOT NULL,
  `roleId` INT(11) NOT NULL,
  PRIMARY KEY (`userId`, `roleId`),
  INDEX `fk_user_role_role_idx` (`roleId` ASC),
  INDEX `fk_user_role_user_idx` (`userId` ASC))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COMMENT '用户角色对应表';

-- -----------------------------------------------------
-- Table `spring_boot_example`.`module`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `spring_boot_example`.`module` ;

CREATE TABLE IF NOT EXISTS `spring_boot_example`.`module` (
  `moduleId` INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `moduleName` VARCHAR(32) NOT NULL COMMENT '模块名称',
  `moduleParentId` INT(11) NULL DEFAULT NULL COMMENT '父角色',
  `moduleUrl` VARCHAR(64) NULL,
  `status` TINYINT(4) NOT NULL DEFAULT '0',
  `createTime` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `updateTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`moduleId`))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4
  COMMENT = '系统模块表';

-- -----------------------------------------------------
-- Table `spring_boot_example`.`role_has_module`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `spring_boot_example`.`role_has_module` ;

CREATE TABLE IF NOT EXISTS `spring_boot_example`.`role_has_module` (
  `roleId` INT(11) NOT NULL,
  `moduleId` INT(11) NOT NULL,
  PRIMARY KEY (`roleId`, `moduleId`),
  INDEX `fk_role_module_module_idx` (`moduleId` ASC),
  INDEX `fk_role_module_role_idx` (`roleId` ASC))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Spring Security OAuth2 Tables Begin
-- -----------------------------------------------------
DROP TABLE IF EXISTS `oauth_client_details` ;
create table oauth_client_details (
  client_id VARCHAR(256) PRIMARY KEY,
  resource_ids VARCHAR(256),
  client_secret VARCHAR(256),
  scope VARCHAR(256),
  authorized_grant_types VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(256)
);

DROP TABLE IF EXISTS `oauth_client_token` ;
create table oauth_client_token (
  token_id VARCHAR(256),
  token BLOB,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256)
);

DROP TABLE IF EXISTS `oauth_access_token` ;
create table oauth_access_token (
  token_id VARCHAR(256),
  token BLOB,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication BLOB,
  refresh_token VARCHAR(256)
);

DROP TABLE IF EXISTS `oauth_refresh_token` ;
create table oauth_refresh_token (
  token_id VARCHAR(256),
  token BLOB,
  authentication BLOB
);

DROP TABLE IF EXISTS `oauth_code` ;
create table oauth_code (
  code VARCHAR(256), authentication BLOB
);

DROP TABLE IF EXISTS `oauth_approvals` ;
create table oauth_approvals (
  userId VARCHAR(256),
  clientId VARCHAR(256),
  scope VARCHAR(256),
  status VARCHAR(10),
  expiresAt TIMESTAMP,
  lastModifiedAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


-- customized oauth_client_details table
DROP TABLE IF EXISTS `ClientDetails` ;
create table ClientDetails (
  appId VARCHAR(256) PRIMARY KEY,
  resourceIds VARCHAR(256),
  appSecret VARCHAR(256),
  scope VARCHAR(256),
  grantTypes VARCHAR(256),
  redirectUrl VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation VARCHAR(4096),
  autoApproveScopes VARCHAR(256)
);
-- -----------------------------------------------------
-- Spring Security OAuth2 Tables End
-- -----------------------------------------------------


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
