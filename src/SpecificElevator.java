import java.util.*;

/** SpecificElevator Class for the Politique2
* @author Mehdi EL AYADI
* @author Zakarya BOUALI
*/

public class SpecificElevator extends Elevator{
  private enum ElevatorSpec {high,low}; // Different state
  private ElevatorSpec spec;
  private int border; // Value to define the border of the zone elevator up & low

/**
 * Constructor
 * @param maxF maxFloor
 * @param maxP [description]
 * @param vly  [description]
 * @param spec [description]
 */
  public SpecificElevator(int maxF, int maxP, int vly, ElevatorSpec spec){
    super(maxF, maxP,vly);
    this.border= (int) Math.round(maxF/2);
    this.spec = spec;
  }


}
