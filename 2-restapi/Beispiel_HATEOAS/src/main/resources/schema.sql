
 -- Copyright © 2020 Martin Kutscher

 -- E-Mail: martin.kutscher@exxeta.com
 
 -- Dieser Quellcode ist lizenziert unter einer
 -- Creative Commons Namensnennung 4.0 International Lizenz.

 
-- User speichern
CREATE TABLE "demo_user" (
    id INT PRIMARY KEY,
    first_name VARCHAR(255) NULL,
    last_name VARCHAR(255) NULL
);
 
-- Gruppen speichern
CREATE TABLE "demo_group" (
    id INT PRIMARY KEY,
    group_name VARCHAR(255) NULL,
    description VARCHAR(300) NULL
);
 
 -- Join Tabelle zum Speichern der Zuordnungen
CREATE TABLE "demo_user_group" (
    user_id INT,
    group_id INT
);