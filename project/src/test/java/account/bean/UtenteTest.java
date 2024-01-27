package account.bean;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UtenteTest {
    
    private Utente utente;

    @BeforeEach
    void setUp() throws Exception {
        utente = new Utente("test@example.com", "Test123!", "Mario", "Rossi", "azienda1", true, "UtenteNormale");
    }

    @Test
    void testIsValidInput() {
        assertTrue(utente.isValidInput("Mario", "Rossi", "test@example.com", "Test123!"));
        assertFalse(utente.isValidInput("123", "Rossi", "test@example.com", "Test123!"));
        assertFalse(utente.isValidInput("Mario", "123", "test@example.com", "Test123!"));
        assertFalse(utente.isValidInput("Mario", "Rossi", "invalidemail", "Test123!"));
        assertFalse(utente.isValidInput("Mario", "Rossi", "test@example.com", "weakpassword"));
    }

    @Test
    void testToString() {
        String expected = "Utente [email=test@example.com, password=Test123!, nome=Mario, cognome=Rossi, idAzienda=azienda1, stato=true, ruolo=UtenteNormale]";
        assertEquals(expected, utente.toString());
    }
}
