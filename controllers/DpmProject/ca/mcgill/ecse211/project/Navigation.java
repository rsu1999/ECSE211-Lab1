package ca.mcgill.ecse211.project;

import static ca.mcgill.ecse211.project.Resources.*;

import ca.mcgill.ecse211.playingfield.Point;

/**
 * The Navigation class is used to make the robot navigate around the playing field.
 */
public class Navigation {
  
  /** Do not instantiate this class. */
  private Navigation() {}
  
  /** Travels to the given destination. */
  public static void travelTo(Point destination) {
    // TODO
    // Think carefully about how you would integrate line detection here, if necessary
    // Don't forget that destination.x and destination.y are in feet, not meters
  }
  
  /**
   * Turns the robot with a minimal angle towards the given input angle in degrees, no matter what
   * its current orientation is. This method is different from {@code turnBy()}.
   */
  public static void turnTo(double angle) {
    // TODO
    // Hint: You can do this in one line by reusing some helper methods declared in this class
  }

  /** Returns the angle that the robot should point towards to face the destination in degrees. */
  public static double getDestinationAngle(Point current, Point destination) {
    return 0; // TODO
  }
  
  /** Returns the signed minimal angle in degrees from initial angle to destination angle (deg). */
  public static double minimalAngle(double initialAngle, double destAngle) {
    return 0; // TODO
  }
  
  /** Returns the distance between the two points in tile lengths (feet). */
  public static double distanceBetween(Point p1, Point p2) {
    double distX;
    double distY;
    double distT;
    
    distX = p1.x - p2.x;
    distY = p1.y - p2.y;
    distT = Math.sqrt(distX * distX + distY * distY);
    
    return distT; // TODO
  }
  
  // TODO Bring Navigation-related helper methods from Labs 2 and 3 here
  // You can also add other helper methods here, but remember to document them with Javadoc (/**)!
  
}
