-- demo.dbo.books definition

-- Drop table

-- DROP TABLE demo.dbo.books;

CREATE TABLE demo.dbo.books (
	book_id int NULL,
	title varchar(128) NULL,
	author varchar(50) NULL,
	rating real NULL,
	description varchar(512) NULL,
	[language] varchar(50) NULL,
	isbn varchar(50) NULL,
	book_format varchar(50) NULL,
	edition varchar(50) NULL,
	pages int NULL,
	publisher varchar(50) NULL,
	publish_date date NULL,
	first_publish_date date NULL,
	liked_percent int NULL,
	price real NULL
);