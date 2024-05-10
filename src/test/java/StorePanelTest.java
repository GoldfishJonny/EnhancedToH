import com.team4.CurrencyPanel;
import com.team4.StorePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorePanelTest {

    private StorePanel storePanel;
    private CurrencyPanel currencyPanel;

    @BeforeEach
    public void setUp() {
        currencyPanel = new CurrencyPanel();
        storePanel = new StorePanel(currencyPanel);
    }

    @Test
    public void testBuyItem1WithSufficientCoins() {
        currencyPanel.incrementCoins(20);

        storePanel.buyItem1();

        assertEquals(10, currencyPanel.getCoins(), "Coins should be deducted by the item price after purchase");
    }

    @Test
    public void testBuyItem1WithInsufficientCoins() {
        currencyPanel.incrementCoins(5);

        storePanel.buyItem1();

        assertEquals(5, currencyPanel.getCoins(), "Coins should remain unchanged if insufficient to buy the item");
    }
}
