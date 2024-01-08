-- Creazione del database
CREATE DATABASE IF NOT EXISTS focusproject;
USE focusproject;

DROP TABLE IF EXISTS Turno;
DROP TABLE IF EXISTS Permesso;
DROP TABLE IF EXISTS StatistichePersonale;
DROP TABLE IF EXISTS StatisticheResponsabile;
DROP TABLE IF EXISTS StatisticheSubordinato;
DROP TABLE IF EXISTS Task;
DROP TABLE IF EXISTS Comunicazione;
DROP TABLE IF EXISTS Dirigente;
DROP TABLE IF EXISTS Responsabile;
DROP TABLE IF EXISTS Subordinato;
DROP TABLE IF EXISTS Utente;
DROP TABLE IF EXISTS Progetto;

CREATE TABLE Utente (
    nome VARCHAR(255) NOT NULL,
    cognome VARCHAR(255) NOT NULL,
    email VARCHAR(255) PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    piva VARCHAR(11) NOT NULL,
    INDEX idx_piva (piva)
);

CREATE TABLE Dirigente (
    email VARCHAR(255) PRIMARY KEY,
    nomeAzienda VARCHAR(255) NOT NULL,
    FOREIGN KEY (email) REFERENCES Utente(email)
	ON UPDATE CASCADE
	ON DELETE RESTRICT
);

CREATE TABLE Responsabile (
    email VARCHAR(255) PRIMARY KEY,
    FOREIGN KEY (email) REFERENCES Utente(email)
    ON UPDATE CASCADE
	ON DELETE RESTRICT
);

CREATE TABLE Subordinato (
    email VARCHAR(255) PRIMARY KEY,
    FOREIGN KEY (email) REFERENCES Utente(email)
	ON UPDATE CASCADE
	ON DELETE RESTRICT
);

CREATE TABLE Progetto (
    id_progetto INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    descrizione VARCHAR(255) NOT NULL,
    obbiettivi VARCHAR(255) NOT NULL,
    stato BOOLEAN DEFAULT FALSE,
    scadenza DATE NOT NULL,
    budget DOUBLE NOT NULL,
    avvisi VARCHAR(255),
    numDipendenti INT,
    
    piva VARCHAR(11) NOT NULL,
    FOREIGN KEY (piva) REFERENCES Utente(piva)
		ON UPDATE CASCADE
		ON DELETE RESTRICT
);


CREATE TABLE Task (
    id_task INT PRIMARY KEY AUTO_INCREMENT,
    id_progetto INT NOT NULL,
    descrizione VARCHAR(255) NOT NULL,
    stato BOOLEAN DEFAULT FALSE,
    assegnato_a_email VARCHAR(255) NOT NULL,
    FOREIGN KEY (id_progetto) REFERENCES Progetto(id_progetto)
        ON UPDATE CASCADE
        ON DELETE RESTRICT,
    FOREIGN KEY (assegnato_a_email) REFERENCES Utente(email)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
);

CREATE TABLE Turno (
    id INT PRIMARY KEY AUTO_INCREMENT,
    giorno DATE NOT NULL,
    ora_inizio TIME NOT NULL,
    ora_fine TIME NOT NULL,
    assegnato_a_email VARCHAR(255) NOT NULL,
    FOREIGN KEY (assegnato_a_email) REFERENCES Utente(email)
	ON UPDATE CASCADE
	ON DELETE RESTRICT
);

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

CREATE TABLE Comunicazione (
    id INT PRIMARY KEY AUTO_INCREMENT,
    titolo VARCHAR(255),
    corpo TEXT,
    mittente_email VARCHAR(255),
    FOREIGN KEY (mittente_email) REFERENCES Utente(email)
	ON UPDATE CASCADE
	ON DELETE RESTRICT
);

CREATE TABLE StatistichePersonale (
    email VARCHAR(255) PRIMARY KEY,
    num_progetti_completati INT DEFAULT 0,
    num_progetti_in_corso INT DEFAULT 0,
    num_permessi_richiesti INT DEFAULT 0,
    FOREIGN KEY (email) REFERENCES Utente(email)
	ON UPDATE CASCADE
	ON DELETE RESTRICT
);

CREATE TABLE StatisticheResponsabile (
    email VARCHAR(255) PRIMARY KEY,
    num_subordinati_gestiti INT DEFAULT 0,
    num_scadenze_rispettate INT DEFAULT 0,
    FOREIGN KEY (email) REFERENCES Utente(email)
	ON UPDATE CASCADE
	ON DELETE RESTRICT
);

CREATE TABLE StatisticheSubordinato (
    email VARCHAR(255) PRIMARY KEY,
    num_task_completati INT DEFAULT 0,
    num_task_in_corso INT DEFAULT 0,
    FOREIGN KEY (email) REFERENCES Utente(email)
	ON UPDATE CASCADE
	ON DELETE RESTRICT
);