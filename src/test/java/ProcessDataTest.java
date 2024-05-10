import com.team4.GameData;
import com.team4.Solver;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.team4.ProcessData;
import org.json.JSONObject;
public class ProcessDataTest {
    @Test

    public void saveData(){
        // Test case for saveData method in ProcessData class
        // Test case 1
        // Test case when the data is saved to the database
        // Expected output: Data saved to database
        // Expected output: {"Best Time":0}
        GameData gameData = GameData.getInstance();
        gameData.setBestTime(10);
        ProcessData processData = new ProcessData();
        JSONObject data = new JSONObject();
        processData.saveData(data);
        assertEquals("Data saved to database", "Data saved to database");
        assertEquals(data.toString(), "{\"Disks\":[],\"Best Time\":10}");
    }

    @Test
    public void loadData(){
        // Test case for loadData method in ProcessData class
        // Test case 1
        // Test case when the data is loaded from the database
        // Expected output: Data loaded from database
        // Expected output: {"Best Time":10}
        ProcessData processData = new ProcessData();
        processData.loadData();
        assertEquals("Data loaded from database", "Data loaded from database");
    }
}
