CREATE  TABLE IF NOT EXISTS employees(
    id integer not null PRIMARY KEY  AUTO_INCREMENT,
    first_name varchar(255),
    last_name varchar(255),
    mail varchar(255),
    password varchar(255),
    etablissements_id int not null,
    KEY `FK_etablissementsEmployee` (`etablissements_id`),
    CONSTRAINT FK_etablissementsEmployee FOREIGN KEY (`etablissements_id`) REFERENCES `etablissements` (`id`)
);



   CREATE  TABLE IF NOT EXISTS etablissements(
     id integer PRIMARY KEY AUTO_INCREMENT ,
     nameEtablissement varchar (255)
);

-- ALTER TABLE employees
-- KEY `FK_etablissementsEmployee` (`etablissements_id`)
-- ADD CONSTRAINT FK_etablissementsEmployee
-- ADD FOREIGN KEY (`etablissements_id`) REFERENCES etablissements (`etablissements_id`);


-- INSERT IGNORE INTO `employee` (`ID_Employee`, `FirstName`, `last_name`, `Mail`, `password`)
-- VALUES (1, 'daoud', 'bilel', 'daoudbilel@gmail.com', 'test');

