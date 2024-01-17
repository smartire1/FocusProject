USE focusproject;

INSERT INTO Azienda (piva, nome) VALUES
('12345678901', 'Azienda1'),
('98765432101', 'Azienda2'),
('11223344556', 'Azienda3');

INSERT INTO Utente (email, pwd, nome, cognome, idAzienda, ruolo) VALUES
('user1@example.com', 'Password1_', 'John', 'Doe', '12345678901', 'dirigente'),
('user2@example.com', 'Password1_', 'Jane', 'Smith', '12345678901', 'responsabile'),
('user3@example.com', 'Password1_', 'Bob', 'Johnson', '98765432101', 'subordinato'),
('user4@example.com', 'Password1_', 'Alice', 'Brown', '11223344556', 'dirigente');

INSERT INTO Turno (giorno, ora_inizio, ora_fine) VALUES
('2024-01-16', '08:00', '12:00'),
('2024-01-17', '13:00', '17:00'),
('2024-01-18', '09:30', '16:30');

INSERT INTO AssegnatoA (id_turno, id_utente) VALUES
(1, 'user2@example.com'),
(2, 'user3@example.com'),
(3, 'user4@example.com'),
(3, 'user1@example.com');

INSERT INTO Permesso (dal_giorno, al_giorno, motivo, stato, richiedente_email) VALUES
('2024-01-20', '2024-01-22', 'Vacanza', true, 'user2@example.com'),
('2024-02-05', '2024-02-08', 'Formazione', null, 'user3@example.com'),
('2024-03-10', '2024-03-12', 'Malattia', false, 'user4@example.com'),
('2024-04-15', '2024-04-17', 'Conferenza', null, 'user1@example.com');

INSERT INTO Progetto (nome, descrizione, obbiettivi, scadenza, avvisi, budget, numDipendenti, responsabile_email, stato, idAzienda) VALUES
('Progetto1', 'Descrizione del Progetto1', 'Obiettivi del Progetto1', '2024-05-31', 'Avvisi Progetto1', 50000.00, 5, 'user1@example.com', false, '12345678901'),
('Progetto2', 'Descrizione del Progetto2', 'Obiettivi del Progetto2', '2024-06-30', 'Avvisi Progetto2', 75000.00, 8, 'user2@example.com', true, '98765432101'),
('Progetto3', 'Descrizione del Progetto3', 'Obiettivi del Progetto3', '2024-07-15', 'Avvisi Progetto3', 100000.00, 10, 'user3@example.com', false, '11223344556');

INSERT INTO LavoraA (email, id_progetto) VALUES
('user1@example.com', 1),
('user2@example.com', 2),
('user3@example.com', 3),
('user4@example.com', 1),
('user4@example.com', 3);

INSERT INTO Task (descrizione, stato, id_progetto, subordinato_email) VALUES
('Task1 del Progetto1', false, 1, 'user2@example.com'),
('Task2 del Progetto2', true, 2, 'user3@example.com'),
('Task3 del Progetto3', false, 3, 'user4@example.com'),
('Task4 del Progetto1', true, 1, 'user1@example.com'),
('Task5 del Progetto2', false, 2, 'user2@example.com');
