DROP TABLE IF EXISTS TBL_EMPLOYEES;
  
CREATE TABLE TBL_EMPLOYEES (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  email VARCHAR(250) DEFAULT NULL
);


-- Dumping structure for table springbootdb.userinfo
CREATE TABLE IF NOT EXISTS `userinfo` (
  `id` int(2) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `role` varchar(50) DEFAULT NULL,
  `enabled` char(50) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(800) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


