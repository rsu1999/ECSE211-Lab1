package ca.mcgill.ecse211.test;

import static ca.mcgill.ecse211.project.Navigation.*;
import static ca.mcgill.ecse211.project.Resources.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the Navigation class. This test runs in Eclipse (right-click > Run as > Unit test) and
 * on the command line, not in Webots!
 * 
 * @author Younes Boubekeur
 */
public class TestNavigation {
  
  /** Tolerate up to this amount of error due to double imprecision. */
  private static final double ERROR_MARGIN = 0.01;
  
  /**
   * @author: George Kontorousis
   */
  @Test 
  void testMinimalAngle() {
    // tests when initial angle is in 1st quadrant
    // try destAngle in all 4 quadrants
    
    // Going from 30 to 80 means turning by 50
    assertEquals(50, minimalAngle(30, 80), ERROR_MARGIN);
    
    // Going from 45 to 135 means turning by 90
    assertEquals(90, minimalAngle(45, 135), ERROR_MARGIN);
    
    // Going from 25 to 208 means turning by -177
    assertEquals(-177, minimalAngle(25, 208), ERROR_MARGIN);
    
    // Going from 75 to 350 means turning by -85
    assertEquals(-85, minimalAngle(75, 350), ERROR_MARGIN);
    
    
    // test when initAngle > destAngle 
    
    // Going from 255 to 135 means turning by -120
    assertEquals(-120, minimalAngle(255, 135), ERROR_MARGIN);
    
    //Going from 340 to 100 means turning by 120
    assertEquals(120, minimalAngle(340, 100), ERROR_MARGIN);
    
    // test when initAngle = 0
    // Going from 0 to 359 means turning by -1 
    assertEquals(-1, minimalAngle(0, 359), ERROR_MARGIN);
    
    // test when destAngle = 0 
    //Going from 275 to 0 means turning by 85
    assertEquals(85, minimalAngle(275, 0), ERROR_MARGIN);
    
    // test when using decimal values
    // Going from 45.57 to 322.07 means turning by -83.5
    assertEquals(-83.5, minimalAngle(45.57, 322.07), ERROR_MARGIN);
    
    //test when initAngle = destAngle
    // Going from 0 to 0 means turning by 0
    assertEquals(0, minimalAngle(0, 0), ERROR_MARGIN);
    
    //Going from 140 to 140 means turning by 0
    assertEquals(0, minimalAngle(140, 140), ERROR_MARGIN);
  }
  
  // TODO Think about testing your other Navigation functions here
  
  // We can add helper methods below to be used in the tests above

}
