package account.bean;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AziendaTest {

    @Test
    void testGetPiva() {
        Azienda azienda = new Azienda("12345678901", "ExampleCompany");
        assertEquals("12345678901", azienda.getPiva());
    }

    @Test
    void testSetPiva() {
        Azienda azienda = new Azienda("12345678901", "ExampleCompany");
        azienda.setPiva("98765432109");
        assertEquals("98765432109", azienda.getPiva());
    }

    @Test
    void testGetNome() {
        Azienda azienda = new Azienda("12345678901", "ExampleCompany");
        assertEquals("ExampleCompany", azienda.getNome());
    }

    @Test
    void testSetNome() {
        Azienda azienda = new Azienda("12345678901", "ExampleCompany");
        azienda.setNome("NewCompany");
        assertEquals("NewCompany", azienda.getNome());
    }

    @Test
    void testIsValidPiva_ValidPiva() {
        Azienda azienda = new Azienda("12345678901", "ExampleCompany");
        assertTrue(azienda.isValidPiva("12345678901"));
    }

    @Test
    void testIsValidPiva_InvalidPiva() {
        Azienda azienda = new Azienda("12345678901", "ExampleCompany");
        assertFalse(azienda.isValidPiva("invalid_piva"));
    }

    @Test
    void testIsValidCompanyName_ValidName() {
        Azienda azienda = new Azienda("12345678901", "ExampleCompany");
        assertTrue(azienda.isValidCompanyName("ExampleCompany"));
    }

    @Test
    void testIsValidCompanyName_InvalidName() {
        Azienda azienda = new Azienda("12345678901", "ExampleCompany");
        assertFalse(azienda.isValidCompanyName("Invalid@Name"));
    }

    @Test
    void testIsValidInput_ValidInput() {
        Azienda azienda = new Azienda("12345678901", "ExampleCompany");
        assertTrue(azienda.isValidInput("12345678901", "ExampleCompany"));
    }

    @Test
    void testIsValidInput_InvalidPiva() {
        Azienda azienda = new Azienda("12345678901", "ExampleCompany");
        assertFalse(azienda.isValidInput("invalid_piva", "ExampleCompany"));
    }

    @Test
    void testIsValidInput_InvalidName() {
        Azienda azienda = new Azienda("12345678901", "ExampleCompany");
        assertFalse(azienda.isValidInput("12345678901", "Invalid@Name"));
    }

    @Test
    void testToString() {
        Azienda azienda = new Azienda("12345678901", "ExampleCompany");
        assertEquals("Azienda [piva=12345678901, nome=ExampleCompany]", azienda.toString());
    }
}
