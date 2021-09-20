import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class PasswordCheckerTest {

    private PasswordChecker passwordChecker = new PasswordChecker();

    @Test
    void testPasswordLength_validLength() {
        assertTrue(passwordChecker.checkPassword("password", 8));
    }

    @Test
    void testPasswordLength_invalidLength() {
        assertFalse(passwordChecker.checkPassword("pass", 8));
    }

    @Test
    void testPasswordForUppercase_hasUppercase() {
        assertTrue(passwordChecker.checkPassword("pAssword123", 8));
    }

    @Test
    void testPasswordForUppercase_noUppercase() {
        assertFalse(passwordChecker.checkPassword("password123", 8));
    }

    @Test
    void testPasswordForSpecialSymbols_noSpecialSymbols() {
        assertFalse(passwordChecker.checkPassword("password", 8));
    }

    @Test
    void testPasswordForSpecialSymbols_hasSpecialSymbols() {
        assertTrue(passwordChecker.checkPassword("password!", 8));
    }
}
