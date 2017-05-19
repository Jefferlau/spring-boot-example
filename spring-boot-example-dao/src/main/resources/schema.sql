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

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
