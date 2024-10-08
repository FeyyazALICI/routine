
CREATE DATABASE IF NOT EXISTS routine_db;
USE routine_db;


CREATE TABLE log(
id BIGINT AUTO_INCREMENT PRIMARY KEY,
status_message INT,	# 0-1
record_date TIMESTAMP,
http_status VARCHAR(255),
request_type varchar(255),
data LONGTEXT,
user VARCHAR(50)
);
ALTER TABLE log MODIFY COLUMN id BIGINT AUTO_INCREMENT;
INSERT INTO log (status_message, record_date, http_status, request_type, data, user)
VALUES (1, current_timestamp(), 'OK', 'GET', null, 'Bro');

CREATE TABLE dummy_job(
id BIGINT AUTO_INCREMENT PRIMARY KEY,
record_date TIMESTAMP
);
ALTER TABLE dummy_job MODIFY COLUMN id BIGINT AUTO_INCREMENT;
INSERT INTO dummy_job (record_date) VALUES (current_timestamp());

CREATE TABLE sport(
id BIGINT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(255)
);
ALTER TABLE sport MODIFY COLUMN id BIGINT AUTO_INCREMENT;
INSERT INTO sport (name) VALUES 
('Push-up'),			#1
('Squat'),  			#2
('Equestrian-Squat'),	#3
('Biceps'),				#4
('Triceps'),			#5
('Bow-Draw'),			#6
('Run'),				#7 -> rep is km
('Wrist'),				#8 -> no reps nor sets its fixed 15*10
('Swim'),				#9 -> no reps nor sets
('Equestrianism');		#10

CREATE TABLE category(
id BIGINT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(255)
);
ALTER TABLE category MODIFY COLUMN id BIGINT AUTO_INCREMENT;
INSERT INTO category (name) VALUES 
('art'),
('sport'),
('work');

CREATE TABLE art(
id BIGINT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(255)
);
ALTER TABLE art MODIFY COLUMN id BIGINT AUTO_INCREMENT;
INSERT INTO art (name) VALUES 
('book'),
('latin'),
('movie_and_theater'),
('music');

CREATE TABLE project(
id BIGINT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(255)
);
ALTER TABLE project MODIFY COLUMN id BIGINT AUTO_INCREMENT;
INSERT INTO project (name) VALUES 
('java'),
('angular'),
('python'),
('fullstack');

CREATE TABLE dailyiteration(
id BIGINT AUTO_INCREMENT PRIMARY KEY,
category_id 			BIGINT,
sport_id 				BIGINT,
project_id 				BIGINT,
art_id 					BIGINT,
min_or_pages_or_rep 	DECIMAL,
registration 			date,
week_r 					INT,
month_r 				INT,
year_r 					INT
);
ALTER TABLE dailyiteration MODIFY COLUMN id BIGINT AUTO_INCREMENT;
ALTER TABLE dailyiteration ADD CONSTRAINT FK_dailyiteration_category_id FOREIGN KEY (category_id) 	REFERENCES category(id);
ALTER TABLE dailyiteration ADD CONSTRAINT FK_dailyiteration_sport_id 	FOREIGN KEY (sport_id) 		REFERENCES sport(id);
ALTER TABLE dailyiteration ADD CONSTRAINT FK_dailyiteration_project_id  FOREIGN KEY (project_id) 	REFERENCES project(id);
ALTER TABLE dailyiteration ADD CONSTRAINT FK_dailyiteration_art_id 		FOREIGN KEY (art_id) 		REFERENCES art(id);

DROP USER IF EXISTS 'myuser'@'%';
CREATE USER 'myuser'@'%' IDENTIFIED BY 'Light80s!';
GRANT ALL PRIVILEGES ON routine_db.* TO 'myuser'@'%';
FLUSH PRIVILEGES;
COMMIT;
