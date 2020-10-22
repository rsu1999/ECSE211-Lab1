package ca.mcgill.ecse211.project;

import static ca.mcgill.ecse211.project.Resources.*;
import java.util.ArrayList;
import ca.mcgill.ecse211.playingfield.Point;

/**
 * The Navigation class is used to make the robot navigate around the playing field.
 */
public class Navigation {
  
  /** Do not instantiate this class. */
  private Navigation() {}
  
  /** Travels to the given destination. */
  public static void travelTo(Point destination) {
    double currX = odometer.getXyt()[0] / TILE_SIZE;
    System.out.println("CurrX : " + currX);
    double currY = odometer.getXyt()[1] / TILE_SIZE;
    System.out.println("CurrY : " + currY);
    Point myP = new Point(currX, currY);
    double thetaDegrees = getDestinationAngle(myP, destination);
    turnTo(thetaDegrees);
    // divide by tile_size to convert to m
    double dist = distanceBetween(myP, destination) * TILE_SIZE; 
    driver.moveStraightFor(dist);
    LightLocalizer.calibrate();
  }
  
  /**
   * Turns the robot with a minimal angle towards the given input angle in degrees, no matter what
   * its current orientation is. This method is different from {@code turnBy()}
   */
  public static void turnTo(double angle) {
    // odometer.getXyt()[2] = current theta, the initial angle
    System.out.println("CurrTheta : " + odometer.getXyt()[2]);
    driver.turnBy(minimalAngle(odometer.getXyt()[2], angle));
  }

  /** Returns the angle that the robot should point towards to face the destination in degrees. */
  public static double getDestinationAngle(Point current, Point destination) {
    double diffY = destination.y - current.y;
    double diffX = destination.x - current.x;
    System.out.println("diffX : " + diffX + ", diffY : " + diffY);
    double tanTheta = diffY / diffX;
    double theta = Math.atan(tanTheta);
    double thetaDegrees = theta * 180 / Math.PI;
    System.out.println(thetaDegrees);
    if (diffX < 0 && diffY < 0) {
      thetaDegrees += 180;
    } else if (diffY < 0) {
      thetaDegrees += 360;
    } else if (diffX < 0) {
      thetaDegrees += 180;
    }
    return thetaDegrees;
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
  
}
