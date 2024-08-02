ALTER TABLE appointments ADD consultorio VARCHAR(128) NOT NULL;

ALTER TABLE appointments DROP piso, DROP numero;