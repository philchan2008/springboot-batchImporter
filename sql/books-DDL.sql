-- demo.dbo.books definition

-- Drop table

-- DROP TABLE demo.dbo.books;

CREATE TABLE demo.dbo.books (
	bookId int NULL,
	title varchar(128) NULL,
	author varchar(50) NULL,
	rating real NULL,
	description varchar(512) NULL,
	[language] varchar(50) NULL,
	isbn varchar(50) NULL,
	bookFormat varchar(50) NULL,
	edition varchar(50) NULL,
	pages int NULL,
	publisher varchar(50) NULL,
	publishDate date NULL,
	firstPublishDate date NULL,
	likedPercent int NULL,
	price real NULL
);