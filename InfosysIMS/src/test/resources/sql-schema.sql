drop schema ims;

CREATE SCHEMA IF NOT EXISTS `ims`;

USE `ims` ;

DROP TABLE IF EXISTS `customers`;

CREATE TABLE IF NOT EXISTS `customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);
CREATE TABLE IF NOT EXISTS `ims`.`items` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(40) DEFAULT NULL,
    `price` DOUBLE DEFAULT NULL,
    PRIMARY KEY (`id`)
);
CREATE TABLE IF NOT EXISTS `ims`.`orders` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `fkCustomerId` INT(11) NOT NULL,
    PRIMARY KEY (`id`)
    FOREIGN KEY (`fkCustomerId`)
);
CREATE TABLE IF NOT EXISTS `ims`.`orderItems` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `fkOrderId` INT(11) NOT NULL,
    `fkItemId` INT(11) NOT NULL
    PRIMARY KEY (`id`)
    FOREIGN KEY (`fkOrderId`)
    FOREIGN KEY (`fkItemId`)
);