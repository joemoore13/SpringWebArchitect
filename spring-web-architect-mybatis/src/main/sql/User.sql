CREATE  TABLE `user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(55) NULL ,
  `password` VARCHAR(55) NULL ,
  `email` VARCHAR(55) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;
