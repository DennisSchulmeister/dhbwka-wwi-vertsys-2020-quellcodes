

 -- Copyright © 2020 Martin Kutscher

 -- E-Mail: martin.kutscher@exxeta.com
 
 -- Dieser Quellcode ist lizenziert unter einer
 -- Creative Commons Namensnennung 4.0 International Lizenz.


-- User hinzufuegen
INSERT INTO "demo_user" VALUES ('42', 'Nudel', 'Suppe');
INSERT INTO "demo_user" VALUES ('1337', 'Nerd', 'It');

-- Gruppen hinzufuegen
INSERT INTO "demo_group" VALUES ('404', 'Key User', 'Hauptanwender');
INSERT INTO "demo_group" VALUES ('667', 'Admins', 'Systemadministratoren');

-- Zuordnungen von User zu Gruppe
INSERT INTO "demo_user_group" VALUES (42, 404);
INSERT INTO "demo_user_group" VALUES (1337, 667);