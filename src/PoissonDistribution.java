import java.util.*;
import java.lang.Math;

/** Poisson Distribution Model for arrived Personne
* @author Mehdi EL AYADI
* @author Zakarya BOUALI
*
 */
public class PoissonDistribution{
  private double randomNumber;
  /**
   * average inter-arrival time
   */
  private long averageTime;
  private double p;

  public PoissonDistribution(long averageTime){
      this.averageTime= averageTime;
      randomNumber = (double) Math.random();
      p= Math.exp((double) averageTime);
      p=1/p;
  }

  private double getNextDouble() {
        Random n = new Random();
        return n.nextDouble();
    }

  public long next(){
    int r=0;
    double q= 1.0*getNextDouble();
    while(q>p){
      r=r+1;
      q= q*getNextDouble();
    }
    return r;
  }

}
