	CREATE TABLE `UserCredential` (
	  `username` varchar(20) NOT NULL,
	  `password` varchar(45) NOT NULL,
	  PRIMARY KEY (`username`)
	) ENGINE=InnoDB;
	
	CREATE TABLE `UserToken` (
	  `username` varchar(20) NOT NULL,
	  `token` varchar(555) NOT NULL,
	  PRIMARY KEY (`username`)
	) ENGINE=InnoDB;
	