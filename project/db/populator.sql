USE focusproject;
-- AZIENDA 1
-- Inserimento Utenti
INSERT INTO Utente (nome, cognome, email, pwd, piva) VALUES ('Dirigente', 'Azienda', 'dirigente@azienda.com', 'Password_dirigente1', '12345678901');
INSERT INTO Utente (nome, cognome, email, pwd, piva) VALUES ('ResponsabileA', 'CognomeA', 'responsabile1@azienda.com', 'Password_responsabile1', '12345678901');
INSERT INTO Utente (nome, cognome, email, pwd, piva) VALUES ('ResponsabileB', 'CognomeB', 'responsabile2@azienda.com', 'Password_responsabile2', '12345678901');
INSERT INTO Utente (nome, cognome, email, pwd, piva) VALUES ('SubordinatoA', 'CognomeA', 'subordinato1@azienda.com', 'Password_subordinato1', '12345678901');
INSERT INTO Utente (nome, cognome, email, pwd, piva) VALUES ('SubordinatoB', 'CognomeB', 'subordinato2@azienda.com', 'Password_subordinato2', '12345678901');
INSERT INTO Utente (nome, cognome, email, pwd, piva) VALUES ('SubordinatoV', 'CognomeC', 'subordinato3@azienda.com', 'Password_subordinato3', '12345678901');
INSERT INTO Utente (nome, cognome, email, pwd, piva) VALUES ('SubordinatoD', 'CognomeD', 'subordinato4@azienda.com', 'Password_subordinato4', '12345678901');
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

-- Inserimento Progetti
INSERT INTO Progetto (nome, descrizione, obbiettivi, stato, scadenza, budget, avvisi, numDipendenti, piva, responsabile_email) VALUES ('Progetto A', 'Descrizione A', 'Obiettivi A', FALSE, '2024-12-31', 100000.0, 'Avviso A', 5, '12345678901','responsabile1@azienda.com');
INSERT INTO Progetto (nome, descrizione, obbiettivi, stato, scadenza, budget, avvisi, numDipendenti, piva, responsabile_email) VALUES ('Progetto B', 'Descrizione B', 'Obiettivi B', TRUE, '2024-02-28', 150000.0, 'Avviso B', 8, '12345678901','responsabile1@azienda.com');
INSERT INTO Progetto (nome, descrizione, obbiettivi, stato, scadenza, budget, avvisi, numDipendenti, piva, responsabile_email) VALUES ('Progetto C', 'Descrizione C', 'Obiettivi C', FALSE, '2024-05-15', 120000.0, 'Avviso C', 6, '12345678901','responsabile2@azienda.com');

-- Inserimento subordinati ai progetti
INSERT INTO Lavora (id_progetto, email) VALUES (1,'subordinato1@azienda.com');
INSERT INTO Lavora (id_progetto, email) VALUES (1,'subordinato2@azienda.com');

-- AZIENDA 2
-- Inserimento Utenti
INSERT INTO Utente (nome, cognome, email, pwd, piva) VALUES ('Dirigente', 'Azienda', 'dirigente2@azienda.com', 'Password_dirigente2', '12345624900');
INSERT INTO Utente (nome, cognome, email, pwd, piva) VALUES ('SubordinatoD', 'CognomeD', 'subordinato8@azienda.com', 'Password_subordinato4', '12345624900');
-- Inserimento Dirigente
INSERT INTO Dirigente (email, nomeAzienda) VALUES ('dirigente2@azienda.com', 'Azienda SpA');
-- Inserimento Subordinati
INSERT INTO Subordinato (email) VALUES ('subordinato8@azienda.com');
-- Inserimento Permessi
INSERT INTO Permesso (giorno, motivo, stato, richiedente_email)
VALUES ('2024-04-25', 'Formazione', false, 'subordinato8@azienda.com');

