-- Copyright © 2020 Dennis Schulmeister-Zimolong
-- 
-- E-Mail: dhbw@windows3.de
-- Webseite: https://www.wpvs.de/
-- 
-- Dieser Quellcode ist lizenziert unter einer
-- Creative Commons Namensnennung 4.0 International Lizenz.

INSERT INTO SONG (ARTIST, NAME, RELEASE_YEAR, SONGWRITERS) VALUES ('Elton John', 'Goodbye Yellow Brick Road', 1973, 'Bernie Taupin, Elton John');
INSERT INTO SONG (ARTIST, NAME, RELEASE_YEAR, SONGWRITERS) VALUES ('Elton John', 'Candle In The Wind', 1973, 'Bernie Taupin, Elton John');
INSERT INTO SONG (ARTIST, NAME, RELEASE_YEAR, SONGWRITERS) VALUES ('Elton John', 'Blue Wonderful', 2016, 'Bernie Taupin, Elton John');
INSERT INTO SONG (ARTIST, NAME, RELEASE_YEAR, SONGWRITERS) VALUES ('Dire Straits', 'Brothers In Arms', 1985, 'Mark Knopfler');
INSERT INTO SONG (ARTIST, NAME, RELEASE_YEAR, SONGWRITERS) VALUES ('Dire Straits', 'Sultans of Swing', 1978, 'Mark Knopfler');
INSERT INTO SONG (ARTIST, NAME, RELEASE_YEAR, SONGWRITERS) VALUES ('Dire Straits', 'Calling Elvis', 1991, 'Mark Knopfler');
INSERT INTO SONG (ARTIST, NAME, RELEASE_YEAR, SONGWRITERS) VALUES ('The Eagles', 'Tequila Sunrise', 1973, 'Don Henley, Glenn Frey');
INSERT INTO SONG (ARTIST, NAME, RELEASE_YEAR, SONGWRITERS) VALUES ('The Eagles', 'Busy Being Fabulous', 2007, 'Don Henley, Glenn Frey');
INSERT INTO SONG (ARTIST, NAME, RELEASE_YEAR, SONGWRITERS) VALUES ('O.M.D.', 'Walking On The Milky Way', 1996, 'McCluskey, Nigel Ipinson, Keith Small');
INSERT INTO SONG (ARTIST, NAME, RELEASE_YEAR, SONGWRITERS) VALUES ('Queen', 'I Want To Break Free', 1984, 'John Deacon');

INSERT INTO PLAYLIST (NAME) VALUES ('The Golden 70s');
INSERT INTO PLAYLIST_SONGS (PLAYLIST_ID, SONGS_ID) VALUES (1, 1); -- Goodbye Yellow Brick Road
INSERT INTO PLAYLIST_SONGS (PLAYLIST_ID, SONGS_ID) VALUES (1, 2); -- Candle In The Wind
INSERT INTO PLAYLIST_SONGS (PLAYLIST_ID, SONGS_ID) VALUES (1, 5); -- Sultans of Swing
INSERT INTO PLAYLIST_SONGS (PLAYLIST_ID, SONGS_ID) VALUES (1, 7); -- Tequila Sunrise