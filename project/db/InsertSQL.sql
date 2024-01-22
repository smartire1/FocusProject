USE focusproject;

INSERT INTO Azienda (piva, nome) VALUES
('12345678901', 'Azienda1'),
('98765432101', 'Azienda2'),
('11223344556', 'Azienda3');

-- Inserisci Dirigenti, Subordinati e Responsabili per Azienda1
INSERT INTO Utente (email, pwd, nome, cognome, idAzienda, ruolo) VALUES
('dirigente1@example.com', 'Password1_', 'Marco', 'Rossi', '12345678901', 'dirigente'),
('responsabile1@example.com', 'Password1_', 'Luca', 'Verdi', '12345678901', 'responsabile'),
('responsabile2@example.com', 'Password1_', 'Giulia', 'Gallo', '12345678901', 'responsabile'),
('subordinato1@example.com', 'Password1_', 'Matteo', 'Ferrari', '12345678901', 'subordinato'),
('subordinato2@example.com', 'Password1_', 'Chiara', 'Rizzo', '12345678901', 'subordinato'),
('subordinato3@example.com', 'Password1_', 'Davide', 'Romano', '12345678901', 'subordinato'),
('subordinato4@example.com', 'Password1_', 'Sara', 'Moretti', '12345678901', 'subordinato'),
('subordinato5@example.com', 'Password1_', 'Alessandro', 'Conti', '12345678901', 'subordinato');

-- Inserisci Dirigenti, Subordinati e Responsabili per Azienda2
INSERT INTO Utente (email, pwd, nome, cognome, idAzienda, ruolo) VALUES
('dirigente2@example.com', 'Password1_', 'Paolo', 'Marchesi', '98765432101', 'dirigente'),
('responsabile3@example.com', 'Password1_', 'Andrea', 'De Luca', '98765432101', 'responsabile'),
('responsabile4@example.com', 'Password1_', 'Monica', 'Pellegrini', '98765432101', 'responsabile'),
('subordinato6@example.com', 'Password1_', 'Federico', 'Villa', '98765432101', 'subordinato'),
('subordinato7@example.com', 'Password1_', 'Eleonora', 'Mancini', '98765432101', 'subordinato'),
('subordinato8@example.com', 'Password1_', 'Giovanni', 'Fabbri', '98765432101', 'subordinato'),
('subordinato9@example.com', 'Password1_', 'Francesca', 'Vitali', '98765432101', 'subordinato'),
('subordinato10@example.com', 'Password1_', 'Roberto', 'Ferraro', '98765432101', 'subordinato');

-- Inserisci Dirigenti, Subordinati e Responsabili per Azienda3
INSERT INTO Utente (email, pwd, nome, cognome, idAzienda, ruolo) VALUES
('dirigente3@example.com', 'Password1_', 'Enrico', 'Morelli', '11223344556', 'dirigente'),
('responsabile5@example.com', 'Password1_', 'Stefano', 'Ferrara', '11223344556', 'responsabile'),
('responsabile6@example.com', 'Password1_', 'Laura', 'Lombardi', '11223344556', 'responsabile'),
('subordinato11@example.com', 'Password1_', 'Michele', 'Ricci', '11223344556', 'subordinato'),
('subordinato12@example.com', 'Password1_', 'Silvia', 'Russo', '11223344556', 'subordinato'),
('subordinato13@example.com', 'Password1_', 'Claudio', 'Barbieri', '11223344556', 'subordinato'),
('subordinato14@example.com', 'Password1_', 'Elena', 'Bruno', '11223344556', 'subordinato'),
('subordinato15@example.com', 'Password1_', 'Riccardo', 'Vitale', '11223344556', 'subordinato');

INSERT INTO Turno (giorno, ora_inizio, ora_fine) VALUES
('2024-01-16', '08:00', '12:00'),
('2024-01-17', '13:00', '17:00'),
('2024-01-18', '09:30', '16:30');

INSERT INTO AssegnatoA (id_turno, id_utente) VALUES
(1, 'responsabile1@example.com'),
(2, 'subordinato6@example.com'),
(3, 'subordinato12@example.com'),
(3, 'subordinato4@example.com');

INSERT INTO Permesso (dal_giorno, al_giorno, motivo, stato, richiedente_email) VALUES
('2024-01-20', '2024-01-22', 'Vacanza', true, 'responsabile1@example.com'),
('2024-02-05', '2024-02-08', 'Formazione', null, 'subordinato6@example.com'),
('2024-03-10', '2024-03-12', 'Malattia', false, 'subordinato12@example.com'),
('2024-04-15', '2024-04-17', 'Conferenza', null, 'subordinato4@example.com'),
('2024-03-01', '2024-03-05', 'Vacanza', null, 'responsabile1@example.com'),
('2024-04-10', '2024-04-15', 'Formazione', null, 'responsabile1@example.com');


-- Inserisci Progetti per Azienda1
INSERT INTO Progetto (nome, descrizione, obbiettivi, scadenza, avvisi, budget, numDipendenti, responsabile_email, stato, idAzienda) VALUES
('Progetto1Azienda1', 'Descrizione del Progetto1Azienda1', 'Obiettivi del Progetto1Azienda1', '2024-05-31', 'Avvisi Progetto1Azienda1', 50000.00, 5, 'responsabile1@example.com', false, '12345678901'),
('ProgettoInCorso1', 'Descrizione del ProgettoInCorso1', 'Obiettivi del ProgettoInCorso1', '2024-05-30', 'Avvisi ProgettoInCorso1', 60000.00, 6, 'responsabile1@example.com', false, '12345678901'),
('ProgettoInCorso2', 'Descrizione del ProgettoInCorso2', 'Obiettivi del ProgettoInCorso2', '2024-06-25', 'Avvisi ProgettoInCorso2', 80000.00, 8, 'responsabile1@example.com', false, '12345678901'),
('Progetto2Azienda1', 'Descrizione del Progetto2Azienda1', 'Obiettivi del Progetto2Azienda1', '2024-06-30', 'Avvisi Progetto2Azienda1', 75000.00, 8, 'responsabile2@example.com', false, '12345678901'),
('Progetto3Azienda1', 'Descrizione del Progetto3Azienda1', 'Obiettivi del Progetto3Azienda1', '2024-07-15', 'Avvisi Progetto3Azienda1', 100000.00, 10, 'subordinato1@example.com', false, '12345678901'),
('ProgettoCompletato1', 'Descrizione del ProgettoCompletato1', 'Obiettivi del ProgettoCompletato1', '2024-03-15', 'Avvisi ProgettoCompletato1', 30000.00, 3, 'responsabile1@example.com', true, '12345678901'),
('ProgettoCompletato2', 'Descrizione del ProgettoCompletato2', 'Obiettivi del ProgettoCompletato2', '2024-04-20', 'Avvisi ProgettoCompletato2', 45000.00, 5, 'responsabile1@example.com', true, '12345678901');


-- Inserisci Progetti per Azienda2
INSERT INTO Progetto (nome, descrizione, obbiettivi, scadenza, avvisi, budget, numDipendenti, responsabile_email, stato, idAzienda) VALUES
('Progetto1Azienda2', 'Descrizione del Progetto1Azienda2', 'Obiettivi del Progetto1Azienda2', '2024-05-31', 'Avvisi Progetto1Azienda2', 50000.00, 5, 'responsabile3@example.com', false, '98765432101'),
('Progetto2Azienda2', 'Descrizione del Progetto2Azienda2', 'Obiettivi del Progetto2Azienda2', '2024-06-30', 'Avvisi Progetto2Azienda2', 75000.00, 8, 'responsabile4@example.com', true, '98765432101'),
('Progetto3Azienda2', 'Descrizione del Progetto3Azienda2', 'Obiettivi del Progetto3Azienda2', '2024-07-15', 'Avvisi Progetto3Azienda2', 100000.00, 10, 'subordinato6@example.com', false, '98765432101');

-- Inserisci Progetti per Azienda3
INSERT INTO Progetto (nome, descrizione, obbiettivi, scadenza, avvisi, budget, numDipendenti, responsabile_email, stato, idAzienda) VALUES
('Progetto1Azienda3', 'Descrizione del Progetto1Azienda3', 'Obiettivi del Progetto1Azienda3', '2024-05-31', 'Avvisi Progetto1Azienda3', 50000.00, 5, 'responsabile5@example.com', false, '11223344556'),
('Progetto2Azienda3', 'Descrizione del Progetto2Azienda3', 'Obiettivi del Progetto2Azienda3', '2024-06-30', 'Avvisi Progetto2Azienda3', 75000.00, 8, 'responsabile6@example.com', true, '11223344556'),
('Progetto3Azienda3', 'Descrizione del Progetto3Azienda3', 'Obiettivi del Progetto3Azienda3', '2024-07-15', 'Avvisi Progetto3Azienda3', 100000.00, 10, 'subordinato11@example.com', false, '11223344556');

-- LavoraA per azienda 1
INSERT INTO LavoraA (email, id_progetto) VALUES
('subordinato1@example.com', 1),
('subordinato2@example.com', 1),
('subordinato3@example.com', 2),
('subordinato4@example.com', 2),
('subordinato5@example.com', 3),
('subordinato6@example.com', 3),
('subordinato2@example.com', 4),
('subordinato3@example.com', 5),
('subordinato4@example.com', 5),
('subordinato5@example.com', 6),
('subordinato5@example.com', 7),
('subordinato1@example.com', 7),

-- LavoraA per azienda 2
('subordinato6@example.com', 8),
('subordinato7@example.com', 8),
('subordinato8@example.com', 8),
('subordinato7@example.com', 9),
('subordinato7@example.com', 9),
('subordinato6@example.com', 10),

-- LavoraA per azienda 3
('subordinato11@example.com', 11),
('subordinato12@example.com', 11),
('subordinato13@example.com', 12),
('subordinato14@example.com', 13),
('subordinato15@example.com', 13),
('subordinato11@example.com', 13);

INSERT INTO Task (descrizione, stato, id_progetto, subordinato_email) VALUES
-- LavoraA per azienda 1
('Task1', false, 1, 'subordinato2@example.com'),
('Task2', true, 2, 'subordinato2@example.com'),
('Task3', false, 3, 'subordinato3@example.com'),
('Task4', true, 1, 'subordinato4@example.com'),
('Task5', false, 2, 'subordinato5@example.com'),
('Task6', true, 2, 'subordinato1@example.com'),
('Task7', true, 2, 'subordinato1@example.com'),
('Task8', false, 2, 'subordinato1@example.com'),

-- LavoraA per azienda 2
('Task1 del Progetto1', false, 8, 'subordinato6@example.com'),
('Task2 del Progetto2', true, 8, 'subordinato7@example.com'),
('Task3 del Progetto3', false, 9, 'subordinato8@example.com'),
('Task4 del Progetto1', true, 10, 'subordinato8@example.com'),
('Task5 del Progetto2', false, 10, 'subordinato6@example.com'),

-- Tasks per azienda 3
('Task1 del Progetto1', false, 11, 'subordinato11@example.com'),
('Task2 del Progetto2', true, 11, 'subordinato12@example.com'),
('Task3 del Progetto3', false, 12, 'subordinato13@example.com'),
('Task4 del Progetto1', true, 12, 'subordinato14@example.com'),
('Task5 del Progetto2', false, 13, 'subordinato15@example.com');
