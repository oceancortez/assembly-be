CREATE SCHEMA `omc` ;

CREATE TABLE `omc`.`agenda` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  `date_created` DATETIME NULL,
  `s_vote_total` INT,
  `n_vote_total` INT,
  `qtd_vote_total` INT,
  `date_counting` DATETIME NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) );



  CREATE TABLE `omc`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tax_id` VARCHAR(11) NOT NULL,
  `date_created` DATETIME NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `tax_id_UNIQUE` (`tax_id` ASC) );

CREATE TABLE `omc`.`session` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `agenda_id` INT NOT NULL,
  `opening_date` DATETIME NOT NULL,
  `closing_date` DATETIME NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `agenda_id_UNIQUE` (`agenda_id` ASC) ,
  INDEX `fk_agenda_id_idx` (`agenda_id` ASC) ,
  CONSTRAINT `fk_agenda_id`
    FOREIGN KEY (`agenda_id`)
    REFERENCES `omc`.`agenda` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


    CREATE TABLE `omc`.`vote` (
  `session_id` INT NOT NULL,
  `agenda_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `vote_status` ENUM('S', 'N') NOT NULL,
  `vote_date` DATETIME NOT NULL,
  PRIMARY KEY (`session_id`, `agenda_id`, `user_id`),
  INDEX `fk_agenda_id_idx` (`agenda_id` ASC) ,
  INDEX `fk_user_id_idx` (`user_id` ASC) ,
  CONSTRAINT `fk_session_id`
    FOREIGN KEY (`session_id`)
    REFERENCES `omc`.`session` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_vote_agenda_id`
    FOREIGN KEY (`agenda_id`)
    REFERENCES `omc`.`agenda` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `omc`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);