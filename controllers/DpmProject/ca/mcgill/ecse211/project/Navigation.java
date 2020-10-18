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
    double currX = odometer.getXyt()[0];
    double currY = odometer.getXyt()[1];
    Point myP = new Point(currX, currY);
    double dist = distanceBetween(myP, destination);
    driver.moveStraightFor(dist);
    // TODO
    // Think carefully about how you would integrate line detection here, if necessary
    // Don't forget that destination.x and destination.y are in feet, not meters
  }
  
  /**
   * Turns the robot with a minimal angle towards the given input angle in degrees, no matter what
   * its current orientation is. This method is different from {@code turnBy()}
   */
  public static void turnTo(double angle) {
    // odometer.getXyt()[2] = current theta, the initial angle
    driver.turnBy(minimalAngle(odometer.getXyt()[2], angle));
  }

  /** Returns the angle that the robot should point towards to face the destination in degrees. */
  public static double getDestinationAngle(Point current, Point destination) {
    return 0; // TODO
  }
  
  /** Returns the signed minimal angle in degrees from initial angle to destination angle (deg). */
  public static double minimalAngle(double initialAngle, double destAngle) {
    double deltaAngle = destAngle - initialAngle;
    //
    if (deltaAngle > 180) {
      deltaAngle -= 360;
    } else if (deltaAngle < -180) {
      deltaAngle += 360;
    }
    return deltaAngle;
  }
  
  /** Returns the distance between the two points in tile lengths (feet). */
  public static double distanceBetween(Point p1, Point p2) {
    double distX;
    double distY;
    double distT;
    
    distX = p1.x - p2.x;
    distY = p1.y - p2.y;
    distT = Math.sqrt(distX * distX + distY * distY);
    
    return distT;
  }
  
  
  
  
  
  // TODO Bring Navigation-related helper methods from Labs 2 and 3 here
  // You can also add other helper methods here, but remember to document them with Javadoc (/**)!
  
//  /**
//   * Moves the robot straight for the given distance.
//   * 
//   * @param distance in feet (tile sizes), may be negative
//   */
//  
//  public static void moveStraightFor(double distance) {
//    leftMotor.setSpeed(FORWARD_SPEED);
//    rightMotor.setSpeed(FORWARD_SPEED);
//    
//    leftMotor.rotate(convertDistance(distance), true);
//    rightMotor.rotate(convertDistance(distance), false);
// 
//  }
//  
//  
//  /**
//   * Converts input distance to the total rotation of each wheel needed to cover that distance.
//   * 
//   * @param distance the input distance in feet
//   * @return the wheel rotations necessary to cover the distance
//   */
//  public static int convertDistance(double distance) {
//    distance *= 0.3048;
//    int rot = (int) (180 * distance / (Math.PI * WHEEL_RAD) * TILE_SIZE);
//    return rot;
//  }
  
}
