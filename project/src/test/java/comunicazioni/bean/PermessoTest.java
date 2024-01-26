package comunicazioni.bean;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PermessoTest {

    private Permesso permesso;

    @BeforeEach
    public void setUp() {
        permesso = new Permesso(1, "2022-01-01", "2022-01-10", "Vacanza", true, "richiedente@example.com");
    }

    @Test
    public void testGetId() {
        assertEquals(1, permesso.getId());
    }

    @Test
    public void testSetId() {
        permesso.setId(2);
        assertEquals(2, permesso.getId());
    }

    @Test
    public void testGetDalGiorno() {
        assertEquals("2022-01-01", permesso.getDalGiorno());
    }

    @Test
    public void testSetDalGiorno() {
        permesso.setDalGiorno("2022-02-01");
        assertEquals("2022-02-01", permesso.getDalGiorno());
    }

    @Test
    public void testGetAlGiorno() {
        assertEquals("2022-01-10", permesso.getAlGiorno());
    }

    @Test
    public void testSetAlGiorno() {
        permesso.setAlGiorno("2022-02-10");
        assertEquals("2022-02-10", permesso.getAlGiorno());
    }

    @Test
    public void testGetMotivo() {
        assertEquals("Vacanza", permesso.getMotivo());
    }

    @Test
    public void testSetMotivo() {
        permesso.setMotivo("Malattia");
        assertEquals("Malattia", permesso.getMotivo());
    }

    @Test
    public void testIsStato() {
        assertEquals(true, permesso.isStato());
    }

    @Test
    public void testSetStato() {
        permesso.setStato(false);
        assertEquals(false, permesso.isStato());
    }

    @Test
    public void testGetRichiedenteEmail() {
        assertEquals("richiedente@example.com", permesso.getRichiedenteEmail());
    }

    @Test
    public void testSetRichiedenteEmail() {
        permesso.setRichiedenteEmail("nuovo_richiedente@example.com");
        assertEquals("nuovo_richiedente@example.com", permesso.getRichiedenteEmail());
    }

    @Test
    public void testToString() {
        String expectedString = "Permesso [id=1, dalGiorno=2022-01-01, alGiorno=2022-01-10, motivo=Vacanza, stato=true, richiedenteEmail=richiedente@example.com]";
        assertEquals(expectedString, permesso.toString());
    }
}
