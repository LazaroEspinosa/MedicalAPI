ALTER TABLE medicos ADD telefono VARCHAR(64) NOT NULL;
UPDATE medicos SET telefono = "0000000000";