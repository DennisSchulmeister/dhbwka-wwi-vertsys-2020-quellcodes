-- Copyright © 2020 Dennis Schulmeister-Zimolong
-- 
-- E-Mail: dhbw@windows3.de
-- Webseite: https://www.wpvs.de/
-- 
-- Dieser Quellcode ist lizenziert unter einer
-- Creative Commons Namensnennung 4.0 International Lizenz.

INSERT INTO DEVICE_CLASS(NAME) VALUES ('Notebooks');
INSERT INTO DEVICE(DEVICE_CLASS_ID, MANUFACTURER, MODEL, BUYING_DATE, IMG_URL) VALUES (1, 'Lenovo', 'Thinkpad E580', '2020-01-20', 'img/thinkpad-e580.png');
INSERT INTO DEVICE(DEVICE_CLASS_ID, MANUFACTURER, MODEL, BUYING_DATE, IMG_URL) VALUES (1, 'Lenovo', 'Thinkpad E590', '2020-01-22', 'img/thinkpad-e590.png');
INSERT INTO DEVICE(DEVICE_CLASS_ID, MANUFACTURER, MODEL, BUYING_DATE, IMG_URL) VALUES (1, 'Sony', 'Vaio Z13V9E', '2019-06-03', 'img/sony-vaio.jpg');
INSERT INTO DEVICE(DEVICE_CLASS_ID, MANUFACTURER, MODEL, BUYING_DATE, IMG_URL) VALUES (1, 'Asus', 'Zenbook Duo', '2019-11-14', 'img/asus-zenbook-duo.jpg');

INSERT INTO DEVICE_CLASS(NAME) VALUES ('Smartphones');
INSERT INTO DEVICE(DEVICE_CLASS_ID, MANUFACTURER, MODEL, BUYING_DATE, IMG_URL) VALUES (2, 'Jolla', 'Jolla C', '2019-11-14', 'img/jolla-c.jpg');
INSERT INTO DEVICE(DEVICE_CLASS_ID, MANUFACTURER, MODEL, BUYING_DATE, IMG_URL) VALUES (2, 'Apple', 'iPhone 7', '2020-02-08', 'img/iphone7.jpg');
INSERT INTO DEVICE(DEVICE_CLASS_ID, MANUFACTURER, MODEL, BUYING_DATE, IMG_URL) VALUES (2, 'Samsung', 'Galaxy S9', '2020-03-01', 'img/samsung-galaxy-s9.jpg');
INSERT INTO DEVICE(DEVICE_CLASS_ID, MANUFACTURER, MODEL, BUYING_DATE, IMG_URL) VALUES (2, 'Planet Devices', 'Cosmo', '2019-12-01', 'img/planet-cosmo.jpg');
