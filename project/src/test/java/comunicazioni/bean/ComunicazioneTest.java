package comunicazioni.bean;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ComunicazioneTest {

    private Comunicazione comunicazione;

    @BeforeEach
    public void setUp() {
        comunicazione = new Comunicazione(1, "Titolo", "Corpo del messaggio", "mittente@example.com");
    }

    @Test
    public void testGetId() {
        assertEquals(1, comunicazione.getId());
    }

    @Test
    public void testSetId() {
        comunicazione.setId(2);
        assertEquals(2, comunicazione.getId());
    }

    @Test
    public void testGetTitolo() {
        assertEquals("Titolo", comunicazione.getTitolo());
    }

    @Test
    public void testSetTitolo() {
        comunicazione.setTitolo("Nuovo Titolo");
        assertEquals("Nuovo Titolo", comunicazione.getTitolo());
    }

    @Test
    public void testGetCorpo() {
        assertEquals("Corpo del messaggio", comunicazione.getCorpo());
    }

    @Test
    public void testSetCorpo() {
        comunicazione.setCorpo("Nuovo corpo del messaggio");
        assertEquals("Nuovo corpo del messaggio", comunicazione.getCorpo());
    }

    @Test
    public void testGetMittenteEmail() {
        assertEquals("mittente@example.com", comunicazione.getMittenteEmail());
    }

    @Test
    public void testSetMittenteEmail() {
        comunicazione.setMittenteEmail("nuovo_mittente@example.com");
        assertEquals("nuovo_mittente@example.com", comunicazione.getMittenteEmail());
    }

    @Test
    public void testToString() {
        String expectedString = "Comunicazione{id=1, titolo='Titolo', corpo='Corpo del messaggio', mittenteEmail='mittente@example.com'}";
        assertEquals(expectedString, comunicazione.toString());
    }
}
