USE focusproject;

-- Inserimento Utenti
INSERT INTO Utente (nome, cognome, email, password) VALUES ('Dirigente', 'Azienda', 'dirigente@azienda.com', 'Password_dirigente1');
INSERT INTO Utente (nome, cognome, email, password) VALUES ('ResponsabileA', 'CognomeA', 'responsabile1@azienda.com', 'Password_responsabile1');
INSERT INTO Utente (nome, cognome, email, password) VALUES ('ResponsabileB', 'CognomeB', 'responsabile2@azienda.com', 'Password_responsabile2');
INSERT INTO Utente (nome, cognome, email, password) VALUES ('SubordinatoA', 'CognomeA', 'subordinato1@azienda.com', 'Password_subordinato1');
INSERT INTO Utente (nome, cognome, email, password) VALUES ('SubordinatoB', 'CognomeB', 'subordinato2@azienda.com', 'Password_subordinato2');
INSERT INTO Utente (nome, cognome, email, password) VALUES ('SubordinatoV', 'CognomeC', 'subordinato3@azienda.com', 'Password_subordinato3');
INSERT INTO Utente (nome, cognome, email, password) VALUES ('SubordinatoD', 'CognomeD', 'subordinato4@azienda.com', 'Password_subordinato4');

-- Inserimento Dirigente
INSERT INTO Dirigente (email, nomeAzienda, piva) VALUES ('dirigente@azienda.com', 'Azienda SpA', '12345678901');

-- Inserimento Responsabili
INSERT INTO Responsabile (email) VALUES ('responsabile1@azienda.com');
INSERT INTO Responsabile (email) VALUES ('responsabile2@azienda.com');

-- Inserimento Subordinati
INSERT INTO Subordinato (email) VALUES ('subordinato1@azienda.com');
INSERT INTO Subordinato (email) VALUES ('subordinato2@azienda.com');
INSERT INTO Subordinato (email) VALUES ('subordinato3@azienda.com');
INSERT INTO Subordinato (email) VALUES ('subordinato4@azienda.com');

