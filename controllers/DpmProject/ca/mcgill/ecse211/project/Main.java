package ca.mcgill.ecse211.project;

import static ca.mcgill.ecse211.project.Resources.*;
import static simlejos.ExecutionController.*;

import ca.mcgill.ecse211.playingfield.Point;
import java.io.IOException;
import java.lang.Thread;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Main class of the program.
 */
public class Main {
  
  /**
   * The number of threads used in the program (main, odometer), other than the one used to
   * perform physics steps. It is possible to do this lab with 2 threads, but you can add more.
   */
  public static final int NUMBER_OF_THREADS = 2;
  
  /**
   * The start and end points for the blocks, read from the vectors file path defined in Resources.
   * Each vector entry has a number and a point array, where the first point is the vector head and
   * the second is its tail. To access these properties in your code, see the main method.
   * 
   */
  public static Map<Integer, Point[]> vectors;
  
  /** Main entry point. */
  public static void main(String[] args) {
    initialize();
    
    // Start the odometer thread
    new Thread(odometer).start();
    
    // TODO Localize in the corner like in the previous lab
    //UltrasonicLocalizer.localize();
    //LightLocalizer.localize();
    
    // TODO Iterate over the vectors like this (both of these ways work, choose one):
    for (var vector: vectors.entrySet()) {
      int number = vector.getKey();
      Point head = vector.getValue()[0];
      Point tail = vector.getValue()[1];
      System.out.println("Move block " + number + " from " + head + " to " + tail + ".");
    }
    
    vectors.forEach((number, points) -> {
      Point head = points[0];
      Point tail = points[1];
      System.out.println("Move block " + number + " from " + head + " to " + tail + ".");
    });
    
    odometer.printPositionInTileLengths();
    
    // TODO Determine which block number to print here based on the measurements taken above
    System.out.println("The heaviest block is...");
    
    Point A0 = new Point(2, 2.5);
    Point A1 = new Point(2, 6);
    Point B0 = new Point(4, 1.5);
    Point B1 = new Point(4, 6);
    Point C0 = new Point(6, 7);
    Point C1 = new Point(6, 3);
    Navigation.travelTo(A0);
    Navigation.travelTo(A1);
    Navigation.travelTo(B0);
    Navigation.travelTo(B1);
    Navigation.travelTo(C0);
    Navigation.travelTo(C1);
    
  }

  /**
   * Initializes the robot logic. It starts a new thread to perform physics steps regularly.
   */
  private static void initialize() {
    try {
      vectors = parseBlockVectors(Files.readAllLines(VECTORS_FILE));
    } catch (IOException e) {
      System.err.println("Could not open file: " + VECTORS_FILE);
      System.exit(-1);
    }
    
    // Run a few physics steps to make sure everything is initialized and has settled properly
    for (int i = 0; i < 50; i++) {
      performPhysicsStep();
    }

    // We are going to start two threads, so the total number of parties is 2
    setNumberOfParties(NUMBER_OF_THREADS);
    
    // Does not count as a thread because it is only for physics steps
    new Thread(() -> {
      while (performPhysicsStep()) {
        sleepFor(PHYSICS_STEP_PERIOD);
      }
    }).start();
  }
  
  /** Parses input lines into block vectors. */
  public static Map<Integer, Point[]> parseBlockVectors(List<String> lines) {
    var result = new HashMap<Integer, Point[]>();
    lines.forEach(line -> {
      if (!line.startsWith("#")) { // line is not a comment
        var n = Arrays.stream(line.split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
        result.put(n[0], new Point[] {new Point(n[1], n[2]), new Point(n[3], n[4])});
      }
    });
    return result;
  }

}
