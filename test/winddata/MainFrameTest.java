/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package winddata;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
public class MainFrameTest {
    
    public MainFrameTest() {
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
     * Test of main method, of class MainFrame.
     */
    /*@Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        MainFrame.main(args);
        // TODO review the generated test code and remove the default call to fail.
        
    }*/
    
    /**
     * Test if the frame is in the middle of the screen
     */
    @Test
    public void testMiddleLocation() {
        MainFrame frame = new MainFrame();
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double screenWidth = screenSize.getWidth();
        double screenHeight = screenSize.getHeight();
        
        double frameWidth = frame.getWidth();
        double frameHeight = frame.getHeight();
        
        double supposedX = (screenWidth - frameWidth) / 2;
        double supposedY = (screenHeight - frameHeight) / 2;
        
        double actualX = frame.getLocation().x;
        double actualY = frame.getLocation().y;
        
        assertEquals("Y coordinat failed", supposedY, actualY, 0);
        assertEquals("X coordinat failed", supposedX, actualX, 0);
    }
    
    
    /**
     * Checks if the routine throws FileNotFoundException
     */
    @Test(expected = FileNotFoundException.class)
    public void checkForRightFileTest1() throws FileNotFoundException, IOException {
        MainFrame frame = new MainFrame();
        frame.checkForRightFile(new File(""));
    }
    
    /**
     * Checks if a correct file is correct
     * @throws IOException 
     */
    @Test
    public void checkForRightFileTest2() throws IOException {
        MainFrame frame = new MainFrame();
        BufferedWriter writer = new BufferedWriter(new FileWriter("test2.txt"));
        writer.write("                            FREQUENCY TABLE OF POTENTIAL WIND SPEED - DISTRIBUTIVE RELATIVE\n");
        writer.close();
        
        assertTrue("File IS Right", frame.checkForRightFile(new File("test2.txt")));
        
        new File("test2.txt").delete();
    }
    
    /**
     * Checks if a wrong file is wrong
     * @throws IOException 
     */
    @Test
    public void checkForRightFileTest3() throws IOException {
        MainFrame frame = new MainFrame();
        BufferedWriter writer = new BufferedWriter(new FileWriter("test3.txt"));
        writer.write("                            FREQUENCY TABLE OF POTENTIAL WIND SPEED - DISTRIBUTIVE ABSOLUTE\n");
        writer.close();
        
        assertFalse("File IS Wrong", frame.checkForRightFile(new File("test3.txt")));
        
        new File("test3.txt").delete();
    }
    
    
}