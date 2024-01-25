DROP DATABASE focusproject;
CREATE DATABASE IF NOT EXISTS focusproject;
USE focusproject;

DROP TABLE IF EXISTS Azienda;
CREATE TABLE Azienda (
    piva VARCHAR(11) NOT NULL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS Utente;
CREATE TABLE Utente (
    email VARCHAR(255) PRIMARY KEY,
    pwd VARCHAR(255) NOT NULL,
    nome VARCHAR(255) NOT NULL,
    cognome VARCHAR(255) NOT NULL,
    idAzienda VARCHAR(11) NOT NULL,
    stato BOOLEAN DEFAULT TRUE,
    ruolo VARCHAR(20) NOT NULL,
    FOREIGN KEY (idAzienda) REFERENCES Azienda(piva)
    ON UPDATE CASCADE
	ON DELETE RESTRICT
);

DROP TABLE IF EXISTS Comunicazione;
CREATE TABLE Comunicazione (
    id INT PRIMARY KEY AUTO_INCREMENT,
    titolo VARCHAR(255),
    corpo TEXT,
    mittente_email VARCHAR(255) NOT NULL,
    FOREIGN KEY (mittente_email) REFERENCES Utente(email)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
);

DROP TABLE IF EXISTS Turno;
CREATE TABLE Turno (
    id INT PRIMARY KEY AUTO_INCREMENT,
    giorno VARCHAR(255) NOT NULL,
    ora_inizio VARCHAR(255) NOT NULL,
    ora_fine VARCHAR(255) NOT NULL
);

-- Relazione Molti-a-Molti tra Turno e Utente
DROP TABLE IF EXISTS AssegnatoA;
CREATE TABLE AssegnatoA (
    id_turno INT NOT NULL,
    id_utente VARCHAR(255) NOT NULL,
    FOREIGN KEY (id_turno) REFERENCES Turno(id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT,
    FOREIGN KEY (id_utente) REFERENCES Utente(email)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
);

DROP TABLE IF EXISTS Permesso;
CREATE TABLE Permesso (
    id INT PRIMARY KEY AUTO_INCREMENT,
    dal_giorno VARCHAR(255) NOT NULL,
    al_giorno VARCHAR(255) NOT NULL,
    motivo VARCHAR(255) NOT NULL,
    stato BOOLEAN DEFAULT NULL,
    richiedente_email VARCHAR(255) NOT NULL,
    FOREIGN KEY (richiedente_email) REFERENCES Utente(email)
	ON UPDATE CASCADE
	ON DELETE RESTRICT
);

DROP TABLE IF EXISTS Progetto;
CREATE TABLE Progetto (
    id_progetto INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    descrizione VARCHAR(255) NOT NULL,
    obbiettivi VARCHAR(255) NOT NULL,
    scadenza VARCHAR(255) NOT NULL,
    avvisi VARCHAR(255),
    budget DOUBLE NOT NULL,
    responsabile_email VARCHAR(255) NOT NULL,
    stato BOOLEAN DEFAULT FALSE,
    idAzienda VARCHAR(11) NOT NULL,
    FOREIGN KEY (idAzienda) REFERENCES Azienda(piva)
    ON UPDATE CASCADE
	ON DELETE RESTRICT
);

-- Relazione Molti-a-Molti tra Subordinato e Progetto
DROP TABLE IF EXISTS LavoraA;
CREATE TABLE LavoraA (
	email VARCHAR(255) NOT NULL,
    id_progetto INT NOT NULL,
    FOREIGN KEY (email) REFERENCES Utente(email)
	ON UPDATE CASCADE
	ON DELETE RESTRICT,
    FOREIGN KEY (id_progetto) REFERENCES Progetto(id_progetto)
	ON UPDATE CASCADE
	ON DELETE RESTRICT
);

DROP TABLE IF EXISTS Task;
CREATE TABLE Task (
    id_task INT PRIMARY KEY AUTO_INCREMENT,
    descrizione VARCHAR(255) NOT NULL,
    stato BOOLEAN DEFAULT FALSE,
    id_progetto INT NOT NULL,
    subordinato_email VARCHAR(255) NOT NULL,
    FOREIGN KEY (id_progetto) REFERENCES Progetto(id_progetto)
        ON UPDATE CASCADE
        ON DELETE RESTRICT,
    FOREIGN KEY (subordinato_email) REFERENCES Utente(email)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
);

DROP TABLE IF EXISTS StatsResponsabile;
CREATE TABLE StatsResponsabile (
    email VARCHAR(255) PRIMARY KEY,
	num_progetti_completati INT DEFAULT 0,
    num_progetti_in_corso INT DEFAULT 0,
    num_permessi_richiesti INT DEFAULT 0,
    num_subordinati_gestiti INT DEFAULT 0,
    FOREIGN KEY (email) REFERENCES Utente(email)
	ON UPDATE CASCADE
	ON DELETE RESTRICT
);

DROP TABLE IF EXISTS StatsSubordinato;
CREATE TABLE StatsSubordinato (
    email VARCHAR(255) PRIMARY KEY,
	num_progetti_completati INT DEFAULT 0,
    num_progetti_in_corso INT DEFAULT 0,
    num_permessi_richiesti INT DEFAULT 0,
    num_task_completati INT DEFAULT 0,
    num_task_in_corso INT DEFAULT 0,
    FOREIGN KEY (email) REFERENCES Utente(email)
	ON UPDATE CASCADE
	ON DELETE RESTRICT
);