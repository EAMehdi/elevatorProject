import java.util.*;
import java.lang.Math;
/**
* Collector of DataCollection
*/
public class DataCollector{
  private List<Integer> timePassedList;
  private List<Integer> nbPeopleWaiting;

  public DataCollector(){
    timePassedList = new ArrayList<>();
    nbPeopleWaiting = new ArrayList<>();
  }
  
  public void addSimData(DataCollection c){
    timePassedList.add(c.getTime());
    nbPeopleWaiting.add(c.getNbWaitPeople());
  }

  /**
  * Get the average Number of Steps using streams
  * @return [description]
  */
  public double getAverageTimePassed(){
    return timePassedList.stream().mapToDouble(a -> a).average().orElse(0.0);
  }

  /**
  * Get the average Number of people who didn't get to their destination using streams
  * @return [description]
  */
  public double getAveragePeopleWaiting(){
    return nbPeopleWaiting.stream().mapToDouble(a -> a).average().orElse(0.0);
  }

  /**
  * Get the Standard Distribution (Ecart-Type) of the Time passing by
  * We use the Two-pass algorithm
  * @see <a href="https://en.wikipedia.org/wiki/Algorithms_for_calculating_variance#Two-pass_algorithm">wikipedia: Two-pass_algorithm</a>
  * @return [description]
  */
  public double getStandardDistTime(){
    double n, sum1, sum2;
    n= sum1 = sum2 =0;

    for(int var : timePassedList){
      n+=1;
      sum1+=var;
    }

    double moy = sum1/n;

    for(int var2 : timePassedList){
      sum2 += (var2-moy) * (var2 - moy);
    }
    double variance =sum2/(n-1);
    return Math.sqrt(variance);
  }


  /**
  * Get the Standard Distribution (Ecart-Type) Number of People Waiting
  * We use the Two-pass algorithm
  * @see <a href="https://en.wikipedia.org/wiki/Algorithms_for_calculating_variance#Two-pass_algorithm">wikipedia: Two-pass_algorithm</a>
  * @return [description]
  */
  public double getStandardDistWait(){
    double n, sum1, sum2;
    n= sum1 = sum2 =0;

    for(int var : nbPeopleWaiting){
      n+=1;
      sum1+=var;
    }

    double moy = sum1/n;

    for(int var2 : nbPeopleWaiting){
      sum2 += (var2-moy) * (var2 - moy);
    }
    double variance =sum2/(n-1);
    return Math.sqrt(variance);
  }


  private int getNumberOfSimulation(){
    return timePassedList.size();
  }

  @Override
  public String toString(){
    String msg = "\n\nData Collected of the simulation";
    msg += "\n\tNumber of simulation: " + getNumberOfSimulation();
    msg += "\n\tAverage number of Step: " + getAverageTimePassed() + " Steps";
    msg += "\n\t  -> The Standard Distribution: " + getStandardDistTime() + " Steps";
    msg += "\n\tAverage number of Passager still waiting: " + getAveragePeopleWaiting() + " Passager(s)";
    msg += "\n\t  -> The Standard Distribution: " + getStandardDistWait() + " Passager(s)";
    return msg;
  }
}
