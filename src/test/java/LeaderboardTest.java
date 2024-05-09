import static org.junit.jupiter.api.Assertions.assertEquals;

import com.team4.LeaderBoardManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.lang.reflect.Field;

class LeaderBoardTest {

    private LeaderBoardManager manager;
    @BeforeEach
    void setUp() throws IOException, NoSuchFieldException, IllegalAccessException {
        manager = new LeaderBoardManager();
        Field field = LeaderBoardManager.class.getDeclaredField("FILE_NAME");
        field.setAccessible(true);
        String fileName = (String) field.get(null);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, false))) {
            bw.write("");
        }
    }


    @Test
    void testAddTimeAndRetrieveTopTimes() throws IOException {
        manager.addTime("00:01.500");
        manager.addTime("00:03.200");
        manager.addTime("00:02.150");
        manager.addTime("00:00.990");
        manager.addTime("00:01.750");

        List<String> topTimes = manager.getTopTimes(3);
        assertEquals(3, topTimes.size());
        assertEquals("00:00.990", topTimes.get(0));
        assertEquals("00:01.500", topTimes.get(1));
        assertEquals("00:01.750", topTimes.get(2));
    }

    @Test
    void testAddTimeAndRetrieveTopTimes2() throws IOException {
        manager.addTime("00:10.500");
        manager.addTime("00:03.200");
        manager.addTime("00:02.150");
        manager.addTime("01:00.990");
        manager.addTime("00:04.750");

        List<String> topTimes = manager.getTopTimes(2);
        assertEquals(2, topTimes.size());
        assertEquals("00:02.150", topTimes.get(0));
        assertEquals("00:03.200", topTimes.get(1));
    }

    @Test
    void testAddTimeAndRetrieveTopTimes3() throws IOException {
        manager.addTime("00:10.000");
        manager.addTime("00:09.900");
        manager.addTime("00:08.800");
        manager.addTime("00:07.700");
        manager.addTime("00:06.600");
        manager.addTime("00:05.500");
        manager.addTime("00:04.400");
        manager.addTime("00:03.300");
        manager.addTime("00:02.200");
        manager.addTime("00:01.100");
        manager.addTime("00:11.000");
        manager.addTime("00:12.100");
        manager.addTime("00:13.200");
        manager.addTime("00:14.300");
        manager.addTime("00:15.400");
        manager.addTime("00:16.500");
        manager.addTime("00:17.600");
        manager.addTime("00:18.700");
        manager.addTime("00:19.800");
        manager.addTime("00:20.900");

        // Retrieve the top 5 times
        List<String> topTimes = manager.getTopTimes(5);

        // Assertions
        assertEquals(5, topTimes.size());
        assertEquals("00:01.100", topTimes.get(0));
        assertEquals("00:02.200", topTimes.get(1));
        assertEquals("00:03.300", topTimes.get(2));
        assertEquals("00:04.400", topTimes.get(3));
        assertEquals("00:05.500", topTimes.get(4));
    }
}
