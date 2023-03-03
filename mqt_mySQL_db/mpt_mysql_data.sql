--------------------------------------------------------------------------------------
-- Project	       : Movie Progress Tracker
--------------------------------------------------------------------------------------
USE mpt;


--------------------------------------------------------
--  mpt
--------------------------------------------------------
-- insert user
insert into user values(null, 'Jesus', 'Ramos', 'jesusramos.jr1999@gmail.com', 'jramos', 'Barcelona');
insert into user values(null, 'Adan', 'Vivero', 'adan_vivero1@gmail.com', 'avivero', 'isellfoodstamps');
insert into user values(null, 'Kaleb', 'McDonnell', 'kalebmcdonnell24@gmail.com', 'kmcdonnell', 'moose');

--------------------------------------------------------
-- insert movies

insert into movies values(null, 'Shrek', 'Comdedy/Fantasy', 90, 'Dreamworks');
insert into movies values(null, 'The Avengers', 'Adenture/Action', 183, 'Disney');
insert into movies values(null, 'Batman Begins', 'Action/Fantasy', 180, 'Warner Bros');
insert into movies values(null, 'The Dark Knight Rises', 'Action/Fantasy', 205, 'Warner Bros');
insert into movies values(null, 'Minions', 'Family/Comedy', 91, 'Universal Pictures');
insert into movies values(null, 'The Town', 'Crime/Thriller', 124, 'Warner Bros');
insert into movies values(null, 'Ralph Breaks the Internet', 'Family/Comedy', 114, 'Disney');
insert into movies values(null, 'Pulp Fiction', 'Crime/Drama', 154, 'Miramax Films');
insert into movies values(null, 'Avatar: The Way of Water', 'Sci-fi/Action', 192, '20th Century Studios');
insert into movies values(null, 'Mad Max: Fury Road', 'Crime/Drama', 154, 'Warner Bros');
insert into movies values(null, 'Spider-Man: Into the Spider Verse', 'Family/Action', 116, 'Sony Pictures');
insert into movies values(null, 'Get Out', 'Horror/Thriller', 104, 'Universal Pictures');
insert into movies values(null, 'Black Panther', 'Action/Adventure', 135, 'Disney');
insert into movies values(null, 'The Social Network', 'Drama', 120, 'Columbia Pictures');
insert into movies values(null, 'Inception', 'Action/Sci-fi', 148, 'Warner Bros');
insert into movies values(null, 'Avengers: Infinity War', 'Action/Sci-fi', 149, 'Disney');
select * from movies;





--------------------------------------------------------
-- insert user_movie
insert into user_movie values(1, 1, 'NC');
insert into user_movie values(1, 3, 'NC');
insert into user_movie values(1, 5, 'NC');
insert into user_movie values(1, 8, 'NC');
insert into user_movie values(2, 1, 'NC');
insert into user_movie values(2, 2, 'NC');
insert into user_movie values(2, 4, 'NC');
insert into user_movie values(3, 2, 'NC');
insert into user_movie values(3, 3, 'NC');
insert into user_movie values(3, 8, 'NC');
select * from user_movie;

