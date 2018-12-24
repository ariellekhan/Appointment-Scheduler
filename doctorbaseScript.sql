#schema
CREATE DATABASE doctorbase;

#login users table
create table doctorbase.users(
username VARCHAR(60) NOT NULL,
loginpassword VARCHAR(50) NOT NULL,
usertype VARCHAR(20) Not NULL,
PRIMARY KEY (username)
);

#patients table
create table doctorbase.patients(
email VARCHAR(60) NOT NULL,
firstName VARCHAR(30) NOT NULL,
lastName VARCHAR(30) NOT NULL,
dateOfBirth VARCHAR(15) NOT NULL,
gender VARCHAR(10) NOT NULL,
homeAddress VARCHAR(100),
contactNumber VARCHAR(20),
PRIMARY KEY (email),
FOREIGN KEY (email) REFERENCES users(username)
);

#doctors table
create table doctorbase.doctors(
email VARCHAR(60) NOT NULL,
firstName VARCHAR(30) NOT NULL,
lastName VARCHAR(30) NOT NULL,
dateOfBirth VARCHAR(15) NOT NULL,
gender VARCHAR(10) NOT NULL,
homeAddress VARCHAR(100),
contactNumber VARCHAR(20),
PRIMARY KEY (email),
FOREIGN KEY (email) REFERENCES users(username)
);

#staff table
create table doctorbase.staff(
email VARCHAR(60) NOT NULL,
firstName VARCHAR(30) NOT NULL,
lastName VARCHAR(30) NOT NULL,
dateOfBirth VARCHAR(15) NOT NULL,
gender VARCHAR(10) NOT NULL,
homeAddress VARCHAR(100),
contactNumber VARCHAR(20),
PRIMARY KEY (email),
FOREIGN KEY (email) REFERENCES users(username)
);

#appointments table
create table doctorbase.appointments(
email VARCHAR(60) NOT NULL,
appNum INT NOT NULL AUTO_INCREMENT,
appDate Date NOT NULL,
appTime VARCHAR(30) NOT NULL,
PRIMARY KEY (appNum),
FOREIGN KEY (email) REFERENCES users(username)
);


#reports table
create table doctorbase.reports(
email VARCHAR(60) NOT NULL,
medHistory VARCHAR(2000),
currMeds VARCHAR(2000),
conditioncl VARCHAR(2000),
height VARCHAR(20),
weightcl VARCHAR(20),
appNum INT NOT NULL AUTO_INCREMENT,
PRIMARY Key(appNum),
FOREIGN KEY (appNum) REFERENCES appointments(appNum),
FOREIGN KEY (email) REFERENCES users(username)
);


