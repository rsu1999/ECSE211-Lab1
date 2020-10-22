package ca.mcgill.ecse211.project;

import static ca.mcgill.ecse211.project.Resources.*;
import static simlejos.ExecutionController.sleepFor;

public class LightLocalizer {
  
  public static float[] sampleCSR = new float[colorSensorR.sampleSize()];
  public static float[] sampleCSL = new float[colorSensorL.sampleSize()];
  
  
  public static double leftDetected = 0;
  public static double rightDetected = 0;
  
  public static void localize() {
    
  }
  
  /**
   * Calibrate the odometer whenever the robot reaches a waypoint.
   */
  public static void calibrate() {
    System.out.println("X : " + odometer.getXyt()[0] / 0.3048 + ", Y : " + odometer.getXyt()[1] / 0.3048
        + ", theta : " + odometer.getXyt()[2]);
    System.out.println("localizing");
    double currTheta = odometer.getXyt()[2];
    double currThetaMod = odometer.getXyt()[2] % 90;
    System.out.println(currThetaMod);
    //double offset = Math.atan(2.7/6.2);
    //double rRadius = 
    if (currThetaMod >= 45) {
      driver.turnBy(90.0 - currThetaMod);
    } else {
      driver.turnBy(-currThetaMod);
      System.out.println("<45");
    }
    leftMotor.setAcceleration(ACCELERATION);
    rightMotor.setAcceleration(ACCELERATION);
    leftMotor.setSpeed(FORWARD_SPEED);
    rightMotor.setSpeed(FORWARD_SPEED);
//    leftMotor.backward();
//    rightMotor.backward();
    leftMotor.forward();
    rightMotor.forward();
    
    toLine();
    System.out.println("Found the first line");
    //sleepFor(20000);
    driver.turnBy(90.0);
    //sleepFor(20000);
    leftMotor.setSpeed(FORWARD_SPEED);
    rightMotor.setSpeed(FORWARD_SPEED);
    driver.moveStraightFor(-0.2);   
//    leftMotor.backward();
//    rightMotor.backward();
    leftMotor.forward();
    rightMotor.forward();
    toLine();    
    sleepFor(20000);
    driver.turnBy(-90.0);
    //moveStraightFor(0.22);
    sleepFor(20000);    
    
    odometer.setX((Math.round(odometer.getXyt()[0] / 0.3048)) * 0.3048);
    odometer.setY((Math.round(odometer.getXyt()[1] / 0.3048)) * 0.3048);
    odometer.setTheta(((Math.round(odometer.getXyt()[2] / 90)) * 90) % 360);
    System.out.println("X : " + odometer.getXyt()[0] / 0.3048 + ", Y : " + odometer.getXyt()[1] / 0.3048
        + ", theta : " + odometer.getXyt()[2]);
  }
  
  /**
   * Drive until robot reaches a line.
   */
  public static void toLine() {
    float prevReadingR = 0;
    float prevReadingL = 0;
    colorSensorR.fetchSample(sampleCSR, 0);
    float newReadingR = (sampleCSR[0] * 1000);
    colorSensorL.fetchSample(sampleCSL, 0);
    float newReadingL = (sampleCSL[0] * 1000);
    float diffReadingR = newReadingR - prevReadingR;
    float diffReadingL = newReadingL - prevReadingL;
    prevReadingR = newReadingR;
    prevReadingL = newReadingL;
    while (true) {
      colorSensorR.fetchSample(sampleCSR, 0);
      newReadingR = (sampleCSR[0] * 1000);
      colorSensorL.fetchSample(sampleCSL, 0);
      newReadingL = (sampleCSL[0] * 1000);
      diffReadingR = newReadingR - prevReadingR;
      diffReadingL = newReadingL - prevReadingL;
      prevReadingR = newReadingR;
      prevReadingL = newReadingL;
      //System.out.println("black value L= " + readingL);
      if (diffReadingL > 20000) {
        leftDetected = 1;
        
        leftMotor.stop();
        //System.out.println("black value L= " + readingL);
      }
      if (diffReadingR > 20000) {
        rightDetected = 1;       
        rightMotor.stop();
        //System.out.println("black value R= " + readingR);
      }
      if (leftDetected + rightDetected == 2) {
        leftDetected = 0;
        rightDetected = 0;
        break;
      }

    }

  }

}