-- Creazione del database
CREATE DATABASE IF NOT EXISTS focusproject;
USE focusproject;

DROP TABLE IF EXISTS Utente;
CREATE TABLE Utente (
    nome VARCHAR(255),
    cognome VARCHAR(255),
    email VARCHAR(255) PRIMARY KEY,
    password VARCHAR(255)
);

DROP TABLE IF EXISTS Dirigente;
CREATE TABLE Dirigente (
    email VARCHAR(255) PRIMARY KEY,
    nomeAzienda VARCHAR(255),
    piva VARCHAR(11),
    FOREIGN KEY (email) REFERENCES Utente(email)
	ON UPDATE CASCADE
	ON DELETE RESTRICT
);

DROP TABLE IF EXISTS Responsabile;
CREATE TABLE Responsabile (
    email VARCHAR(255) PRIMARY KEY,
    FOREIGN KEY (email) REFERENCES Utente(email)
    ON UPDATE CASCADE
	ON DELETE RESTRICT
);

DROP TABLE IF EXISTS Subordinato;
CREATE TABLE Subordinato (
    email VARCHAR(255) PRIMARY KEY,
    FOREIGN KEY (email) REFERENCES Utente(email)
	ON UPDATE CASCADE
	ON DELETE RESTRICT
);

DROP TABLE IF EXISTS Progetto;
CREATE TABLE Progetto (
    id_progetto INT PRIMARY KEY,
    nome VARCHAR(255),
    descrizione VARCHAR(255),
    obbiettivi VARCHAR(255),
    stato BOOLEAN,
    scadenza DATE,
    budget DOUBLE,
    avvisi VARCHAR(255),
    numDipendenti INT
);

DROP TABLE IF EXISTS Task;
CREATE TABLE Task (
    id_task INT PRIMARY KEY,
    id_progetto INT,
    descrizione VARCHAR(255),
    stato BOOLEAN,
    FOREIGN KEY (id_progetto) REFERENCES Progetto(id_progetto)
	ON UPDATE CASCADE
	ON DELETE RESTRICT
);

DROP TABLE IF EXISTS Turno;
CREATE TABLE Turno (
    id INT PRIMARY KEY AUTO_INCREMENT,
    giorno DATE,
    ora_inizio TIME,
    ora_fine TIME,
    responsabile_email VARCHAR(255),
    subordinato_email VARCHAR(255),
    FOREIGN KEY (responsabile_email) REFERENCES Utente(email)
	ON UPDATE CASCADE
	ON DELETE RESTRICT,
    FOREIGN KEY (subordinato_email) REFERENCES Utente(email)
	ON UPDATE CASCADE
	ON DELETE RESTRICT
);

DROP TABLE IF EXISTS Permesso;
CREATE TABLE Permesso (
    id INT PRIMARY KEY AUTO_INCREMENT,
    giorno DATE,
    motivo VARCHAR(255),
    stato BOOLEAN,
    richiedente_email VARCHAR(255),
    FOREIGN KEY (richiedente_email) REFERENCES Utente(email)
	ON UPDATE CASCADE
	ON DELETE RESTRICT
);

DROP TABLE IF EXISTS Comunicazione;
CREATE TABLE Comunicazione (
    id INT PRIMARY KEY AUTO_INCREMENT,
    titolo VARCHAR(255),
    corpo TEXT,
    mittente_email VARCHAR(255),
    FOREIGN KEY (mittente_email) REFERENCES Utente(email)
	ON UPDATE CASCADE
	ON DELETE RESTRICT
);

DROP TABLE IF EXISTS StatistichePersonale;
CREATE TABLE StatistichePersonale (
    email VARCHAR(255) PRIMARY KEY,
    num_progetti_completati INT DEFAULT 0,
    num_progetti_in_corso INT DEFAULT 0,
    num_permessi_richiesti INT DEFAULT 0,
    FOREIGN KEY (email) REFERENCES Utente(email)
	ON UPDATE CASCADE
	ON DELETE RESTRICT
);

DROP TABLE IF EXISTS StatisticheResponsabile;
CREATE TABLE StatisticheResponsabile (
    email VARCHAR(255) PRIMARY KEY,
    num_subordinati_gestiti INT DEFAULT 0,
    num_scadenze_rispettate INT DEFAULT 0,
    FOREIGN KEY (email) REFERENCES Utente(email)
	ON UPDATE CASCADE
	ON DELETE RESTRICT
);

DROP TABLE IF EXISTS StatisticheSubordinato;
CREATE TABLE StatisticheSubordinato (
    email VARCHAR(255) PRIMARY KEY,
    num_task_completati INT DEFAULT 0,
    num_task_in_corso INT DEFAULT 0,
    FOREIGN KEY (email) REFERENCES Utente(email)
	ON UPDATE CASCADE
	ON DELETE RESTRICT
);