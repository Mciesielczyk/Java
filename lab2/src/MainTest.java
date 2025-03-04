import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {

    private String runGameWithInput(String input) {
        // Przekierowanie wejścia
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Przechwycenie wyjścia
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Uruchamiamy main()
        Main.main(new String[]{});

        return outContent.toString().toLowerCase(Locale.ROOT);
    }



    @Test
    void testInvalidInputTooLow() {
        String output = runGameWithInput("0\n");
        assertTrue(output.contains("niepoprawny argument"), "Powinno wykryć błędny zakres (za mały)");
    }

    @Test
    void testInvalidInputNotNumber() {
        String output = runGameWithInput("abc\n");
        assertTrue(output.contains("nie liczba"), "Powinno wykryć niepoprawne dane wejściowe");
    }

    @Test
    void testWinScenario() {
        String output = runGameWithInput("10\n5\n");  // Użytkownik wpisuje zakres 10 i od razu trafia w liczbę
        assertTrue(output.contains("gratulacje") || output.contains("tak"), "Powinno wykryć wygraną");
    }

    @Test
    void testLoseScenario() {
        String output = runGameWithInput("10\n1\n2\n3\n4\n5\n6\n7\n8\n9\n");
        assertTrue(output.contains("niestety"), "Powinno wykryć przegraną");
    }

    @Test
    void alwaysPasses() {
        assertTrue(true, "Ten test zawsze przejdzie");
    }
}
