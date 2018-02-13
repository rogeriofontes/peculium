SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `peculium` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
USE `peculium` ;

-- -----------------------------------------------------
-- Table `peculium`.`region`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `peculium`.`region` (
  `id` bigint NOT NULL,
  `name` VARCHAR(155) NULL,
  `create_by` VARCHAR(255) NULL,
  `created_date` TIMESTAMP NULL,
  `last_modified_by` VARCHAR(255) NULL,
  `last_modified_date` DATETIME NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `peculium`.`country`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `peculium`.`country` (
  `id` bigint NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `acronym` CHAR(2) NULL,
  `region_id` bigint NOT NULL,
  `create_by` VARCHAR(255) NULL,
  `created_date` TIMESTAMP NULL,
  `last_modified_by` VARCHAR(255) NULL,
  `last_modified_date` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_country_region_idx` (`region_id` ASC),
  CONSTRAINT `fk_country_region`
    FOREIGN KEY (`region_id`)
    REFERENCES `peculium`.`region` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `peculium`.`state`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `peculium`.`state` (
  `id` bigint NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `acronym` CHAR(2) NULL,
  `country_id` bigint NOT NULL,
  `create_by` VARCHAR(255) NULL,
  `created_date` TIMESTAMP NULL,
  `last_modified_by` VARCHAR(255) NULL,
  `last_modified_date` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_state_country1_idx` (`country_id` ASC),
  CONSTRAINT `fk_state_country1`
    FOREIGN KEY (`country_id`)
    REFERENCES `peculium`.`country` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `peculium`.`city`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `peculium`.`city` (
  `id` bigint NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `state_id` bigint NOT NULL,
  `create_by` VARCHAR(255) NULL,
  `created_date` TIMESTAMP NULL,
  `last_modified_by` VARCHAR(255) NULL,
  `last_modified_date` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_city_state1_idx` (`state_id` ASC),
  CONSTRAINT `fk_city_state1`
    FOREIGN KEY (`state_id`)
    REFERENCES `peculium`.`state` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `peculium`.`district`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `peculium`.`district` (
  `id` bigint NOT NULL,
  `name` VARCHAR(255) NULL,
  `acronym` CHAR(2) NULL,
  `city_id` bigint NOT NULL,
  `create_by` VARCHAR(255) NULL,
  `created_date` TIMESTAMP NULL,
  `last_modified_by` VARCHAR(255) NULL,
  `last_modified_date` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_district_city1_idx` (`city_id` ASC),
  CONSTRAINT `fk_district_city1`
    FOREIGN KEY (`city_id`)
    REFERENCES `peculium`.`city` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `peculium`.`location`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `peculium`.`location` (
  `id` bigint NOT NULL,
  `street` VARCHAR(255) NOT NULL,
  `complement` VARCHAR(255) NULL,
  `zipcode` varchar(255) NOT NULL,
  `latitude` DOUBLE NULL,
  `longitude` DOUBLE NULL,
  `district_id` bigint NOT NULL,
  `create_by` VARCHAR(255) NULL,
  `created_date` TIMESTAMP NULL,
  `last_modified_by` VARCHAR(255) NULL,
  `last_modified_date` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_location_district1_idx` (`district_id` ASC),
  CONSTRAINT `fk_location_district1`
    FOREIGN KEY (`district_id`)
    REFERENCES `peculium`.`district` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `peculium`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `peculium`.`user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(60) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `peculium`.`user_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `peculium`.`user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_name` VARCHAR(60) NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_role_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_role_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `peculium`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `peculium`.`establishment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `peculium`.`establishment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL,
  `location_id` bigint NOT NULL,
  `create_by` VARCHAR(255) NULL,
  `created_date` TIMESTAMP NULL,
  `last_modified_by` VARCHAR(255) NULL,
  `last_modified_date` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Establishment_location1_idx` (`location_id` ASC),
  CONSTRAINT `fk_Establishment_location1`
    FOREIGN KEY (`location_id`)
    REFERENCES `peculium`.`location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `peculium`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `peculium`.`category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL,
  `create_by` VARCHAR(255) NULL,
  `created_date` TIMESTAMP NULL,
  `last_modified_by` VARCHAR(255) NULL,
  `last_modified_date` DATETIME NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `peculium`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `peculium`.`account` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL,
  `month` VARCHAR(45) NULL,
  `balance` decimal(19,2) NULL,
  `create_by` VARCHAR(255) NULL,
  `created_date` TIMESTAMP NULL,
  `last_modified_by` VARCHAR(255) NULL,
  `last_modified_date` DATETIME NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `peculium`.`movement`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `peculium`.`movement` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `date` TIMESTAMP NOT NULL,
  `value` decimal(19,2) NOT NULL,
  `interest` INT NULL,
  `total_value` decimal(19,2) NULL,
  `portion` INT NULL,
  `portion_total` INT NULL,
  `category_id` bigint NOT NULL,
  `account_id` bigint NOT NULL,
  `establishment_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `create_by` VARCHAR(255) NULL,
  `created_date` TIMESTAMP NULL,
  `last_modified_by` VARCHAR(255) NULL,
  `last_modified_date` DATETIME NULL,
  PRIMARY KEY (`id`, `category_id`),
  INDEX `fk_movement_category1_idx` (`category_id` ASC),
  INDEX `fk_movement_account1_idx` (`account_id` ASC),
  INDEX `fk_movement_Establishment1_idx` (`Establishment_id` ASC),
  INDEX `fk_movement_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_movement_category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `peculium`.`category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_movement_account1`
    FOREIGN KEY (`account_id`)
    REFERENCES `peculium`.`account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_movement_Establishment1`
    FOREIGN KEY (`Establishment_id`)
    REFERENCES `peculium`.`establishment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_movement_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `peculium`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
