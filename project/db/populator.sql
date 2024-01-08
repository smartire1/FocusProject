USE focusproject;
-- AZIENDA 1
-- Inserimento Utenti
INSERT INTO Utente (nome, cognome, email, password, piva) VALUES ('Dirigente', 'Azienda', 'dirigente@azienda.com', 'Password_dirigente1', '12345678901');
INSERT INTO Utente (nome, cognome, email, password, piva) VALUES ('ResponsabileA', 'CognomeA', 'responsabile1@azienda.com', 'Password_responsabile1', '12345678901');
INSERT INTO Utente (nome, cognome, email, password, piva) VALUES ('ResponsabileB', 'CognomeB', 'responsabile2@azienda.com', 'Password_responsabile2', '12345678901');
INSERT INTO Utente (nome, cognome, email, password, piva) VALUES ('SubordinatoA', 'CognomeA', 'subordinato1@azienda.com', 'Password_subordinato1', '12345678901');
INSERT INTO Utente (nome, cognome, email, password, piva) VALUES ('SubordinatoB', 'CognomeB', 'subordinato2@azienda.com', 'Password_subordinato2', '12345678901');
INSERT INTO Utente (nome, cognome, email, password, piva) VALUES ('SubordinatoV', 'CognomeC', 'subordinato3@azienda.com', 'Password_subordinato3', '12345678901');
INSERT INTO Utente (nome, cognome, email, password, piva) VALUES ('SubordinatoD', 'CognomeD', 'subordinato4@azienda.com', 'Password_subordinato4', '12345678901');
-- Inserimento Dirigente
INSERT INTO Dirigente (email, nomeAzienda) VALUES ('dirigente@azienda.com', 'Azienda SpA');
-- Inserimento Responsabili
INSERT INTO Responsabile (email) VALUES ('responsabile1@azienda.com');
INSERT INTO Responsabile (email) VALUES ('responsabile2@azienda.com');
-- Inserimento Subordinati
INSERT INTO Subordinato (email) VALUES ('subordinato1@azienda.com');
INSERT INTO Subordinato (email) VALUES ('subordinato2@azienda.com');
INSERT INTO Subordinato (email) VALUES ('subordinato3@azienda.com');
INSERT INTO Subordinato (email) VALUES ('subordinato4@azienda.com');
-- Inserimento Permessi
INSERT INTO Permesso (giorno, motivo, stato, richiedente_email)
VALUES ('2024-01-10', 'Vacanza', true, 'subordinato2@azienda.com');

-- AZIENDA 2
-- Inserimento Utenti
INSERT INTO Utente (nome, cognome, email, password, piva) VALUES ('Dirigente', 'Azienda', 'dirigente2@azienda.com', 'Password_dirigente2', '12345624900');
INSERT INTO Utente (nome, cognome, email, password, piva) VALUES ('SubordinatoD', 'CognomeD', 'subordinato8@azienda.com', 'Password_subordinato4', '12345624900');
-- Inserimento Dirigente
INSERT INTO Dirigente (email, nomeAzienda) VALUES ('dirigente2@azienda.com', 'Azienda SpA');
-- Inserimento Subordinati
INSERT INTO Subordinato (email) VALUES ('subordinato8@azienda.com');
-- Inserimento Permessi
INSERT INTO Permesso (giorno, motivo, stato, richiedente_email)
VALUES ('2024-04-25', 'Formazione', false, 'subordinato8@azienda.com');
