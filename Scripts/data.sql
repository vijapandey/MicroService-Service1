INSERT INTO TBL_EMPLOYEES (first_name, last_name, email) VALUES
  ('vijay', 'pandey', 'vijapandey@gmail.com'),
  ('sankar', 'ss', 'ss@email.com'),
  ('anand', 'tiwari', 'ap@yahoo.com');
  
  
  -- Dumping data for table springbootdb.userinfo: ~5 rows (approximately)
/*!40000 ALTER TABLE `userinfo` DISABLE KEYS */;
INSERT INTO `userinfo` (`id`, `role`, `enabled`, `username`, `password`) VALUES
	(01, 'ROLE_ADMIN', '1', 'admin_user3', '$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu'),
	(02, 'ROLE_USER', '1', 'user1', '$2a$10$7/wqZq31iMl1dETBhlSGZOrtK3FtOf8dJrzW3cdSCB2j0K/q7f1C2'),
	(03, 'ROLE_ADMIN', '1', 'admin_user2', '{noop}password'),
	(04, 'ROLE_USER', '1', 'user2', '$2a$10$7/wqZq31iMl1dETBhlSGZOrtK3FtOf8dJrzW3cdSCB2j0K/q7f1C2'),
	(05, 'ROLE_ADMIN', '1', 'hendi', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG'),
	(07, 'ROLE_USER', '1', 'user3', '$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu');
  