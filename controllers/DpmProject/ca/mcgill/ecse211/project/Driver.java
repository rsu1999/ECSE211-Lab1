package ca.mcgill.ecse211.project;

import static ca.mcgill.ecse211.project.Resources.*;


/**
 * This class is used to drive the robot on the demo floor.
 */
public class Driver {
  
  private static Driver driver;

  /** Do not instantiate this class. */
  private Driver() {}
  
  /**
   * Returns the Driver Object. Use this method to obtain an instance of Driver.
   * 
   * @return the Driver Object
   */
  public static synchronized Driver getDriver() {
    if (driver == null) {
      driver = new Driver();
    }
    return driver;
  }
  
  /**
   * Moves the robot straight for the given distance.
   * If given distance is negative, moves robot in a straight line
   * backwards
   * 
   * @param distance in m, may be negative
   */
  public void moveStraightFor(double distance) {
    setSpeed(FORWARD_SPEED);
    int move = convertDistance(distance);
    leftMotor.rotate(move, true);
    rightMotor.rotate(move, false);

  }
  
  /**
   * Turns the robot by a specified angle. Note that this method is different from
   * {@code Navigation.turnTo()}. For example, if the robot is facing 90 degrees, calling
   * {@code turnBy(90)} will make the robot turn to 180 degrees, but calling
   * {@code Navigation.turnTo(90)} should do nothing (since the robot is already at 90 degrees).
   * 
   * @param angle the angle by which to turn, in degrees
   */
  public void turnBy(double angle) {
    int turn = convertAngle(angle);
    //Negative speed for the right motor since we want the robot to turn clockwise when angle > 0
    // and counterclockwise when angle < 0
    leftMotor.rotate(turn, true);
    rightMotor.rotate(-turn, false);
  }
  
//  /**
//   * Converts input distance to the total rotation of each wheel needed to cover that distance.
//   * 
//   * @param distance the input distance in meters
//   * @return the wheel rotations necessary to cover the distance
//   */
//  private int convertDistance(double distance) {
//    //we decide to round to the nearest number instead of casting it to get more accuracy
//    return (int) Math.round(distance / (2 * WHEEL_RAD * Math.PI) * 360);
//  }
  
  
  /**
   * Converts input distance to the total rotation of each wheel needed to cover that distance.
   * 
   * @param distance the input distance in feet
   * @return the wheel rotations necessary to cover the distance
   */
  public static int convertDistance(double distance) {
    distance *= 0.3048;
    int rot = (int) (180 * distance / (Math.PI * WHEEL_RAD) * TILE_SIZE);
    return rot;
  }
  

  /**
   * Converts input angle to the total rotation of each wheel needed to rotate the robot by that
   * angle.
   * 
   * @param angle the input angle in degrees
   * @return the wheel rotations necessary to rotate the robot by the angle
   */
  private int convertAngle(double angle) {
    return convertDistance(BASE_WIDTH / 2 * Math.toRadians(angle));
  }
  
  /**
   * Stops both motors.
   */
  public void stopMotors() {
    leftMotor.stop();
    rightMotor.stop();
  }
  
  /**
   * Starts both motors.
   */
  public void startMotors() {
    setSpeed(FORWARD_SPEED);
    leftMotor.forward();
    rightMotor.forward();
  }
  
 
  /**
   * Sets the speed of both motors to the same values.
   * 
   * @param speed the speed in degrees per second
   */
  public void setSpeed(int speed) {
    setSpeeds(speed, speed);
  }
  
  /**
   * Sets the speed of both motors to different values.
   * 
   * @param leftSpeed the speed of the left motor in degrees per second
   * @param rightSpeed the speed of the right motor in degrees per second
   */
  public void setSpeeds(int leftSpeed, int rightSpeed) {
    leftMotor.setSpeed(leftSpeed);
    rightMotor.setSpeed(rightSpeed);
  }
  
  /**
   * Sets the acceleration of both motors.
   * 
   * @param acceleration the acceleration in degrees per second squared
   */
  public void setAcceleration(int acceleration) {
    leftMotor.setAcceleration(acceleration);
    rightMotor.setAcceleration(acceleration);
  }

}
