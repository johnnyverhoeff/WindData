/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package winddata;

import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Johnny
 */
public class WindDataParserTest {
    
    public WindDataParserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of extractLocationAndDate method, of class WindDataParser.
     * With good date and location line
     */
    @Test
    public void testExtractLocationAndDate1() {
        String line = "                                 260 De Bilt              Year              1971-2000";
        WindDataParser instance = new WindDataParser(new File(""));
        instance.extractLocationAndDate(line);
        assertEquals("Location not correct", "260 De Bilt", instance.getLocation());
        assertEquals("Date not correct", "1971-2000", instance.getDate());
    }
    
    /**
     * Test of extractLocationAndDate method, of class WindDataParser.
     * With incorrect date and location line
     */
    @Test
    public void testExtractLocationAndDate2() {
        String line = "";
        WindDataParser instance = new WindDataParser(new File(""));
        instance.extractLocationAndDate(line);
        assertEquals("Location not correct", "", instance.getLocation());
        assertEquals("Date not correct", "", instance.getDate());
    }

    /**
     * Test of extractCumulative method, of class WindDataParser.
     * With correct line
     */
    @Test
    public void testExtractCumulative() {
        String line = " 0.0 -  0.9      2.48    0.10  0.18  0.24  0.37  0.35  0.36  0.28  0.18  0.11  0.13  0.19  0.18     5.15";
        WindDataParser instance = new WindDataParser(new File(""));
        instance.extractCumulative(line);
        
        for (double i = 0.0 ; i < 1.0; i += 0.1) {
            assertEquals("Cumulative speed not correct", 5.15, instance.getCumulativeAtWindSpeed(i), 0);
        }
    }
}