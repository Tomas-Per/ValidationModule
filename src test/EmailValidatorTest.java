import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class EmailValidatorTest {

    private EmailValidator emailValidator = new EmailValidator();

    @Test
    void testForAtSymbol_hasValidAtSymbol() {
        assertTrue(emailValidator.validateEmail("tom@gmail.com"));
    }

    @Test
    void testForAtSymbol_noValidAtSymbol() {
        assertFalse(emailValidator.validateEmail("tomgmail.com"));
    }

    @Test
    void testForIllegalSymbols_hasIllegalSymbols() {
        assertFalse(emailValidator.validateEmail("tom!@gmail.com"));
    }

    @Test
    void testForIllegalSymbols_noIllegalSymbols() {
        assertTrue(emailValidator.validateEmail("tom@gmail.com"));
    }

    @Test
    void testForDomainAndTLD_validDomainAndTLD() {
        assertTrue(emailValidator.validateEmail("tom@gmail.com"));
    }

    @Test
    void testForDomainAndTLD_invalidDomainAndTLD() {
        assertFalse(emailValidator.validateEmail("tom@a.a"));
    }
}
