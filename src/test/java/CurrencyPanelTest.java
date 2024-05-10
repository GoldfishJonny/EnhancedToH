import com.team4.CurrencyPanel;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class CurrencyPanelTest {

    @Test
    void testInitialCoins() {
        CurrencyPanel currencyPanel = new CurrencyPanel();

        assertEquals(0, currencyPanel.getCoins());
    }

    @Test
    void testIncrementCoins() {
        CurrencyPanel currencyPanel = new CurrencyPanel();

        simulateTimePassing(currencyPanel, 3);

        assertTrue(currencyPanel.getCoins() > 0);
    }

    @Test
    void testDeductCoins() {
        CurrencyPanel currencyPanel = new CurrencyPanel();

        simulateTimePassing(currencyPanel, 3);

        int initialCoins = currencyPanel.getCoins();
        currencyPanel.deductCoins(2);
        assertEquals(initialCoins - 2, currencyPanel.getCoins());
    }

    // Helper method to simulate the passage of time for the currency panel
    private void simulateTimePassing(CurrencyPanel currencyPanel, int seconds) {
        Timer timer = new Timer(1000, e -> {
            for (int i = 0; i < seconds; i++) {
                currencyPanel.incrementCoins(20);
            }
        });
        timer.setRepeats(false); // Only execute once
        timer.setInitialDelay(0); // Start immediately
        timer.start();

        // Wait for the specified number of seconds
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
