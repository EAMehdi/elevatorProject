import java.util.*;

public class DataCollector{
  List<Integer> timePassedList;
  List<Integer> nbPeopleWaiting;

  public DataCollector(){
    timePassedList = new ArrayList<>();
    nbPeopleWaiting = new ArrayList<>();
  }
  public void addSimData(DataCollection c){
    timePassedList.add(c.getTime());
    nbPeopleWaiting.add(c.getNbWaitPeople());
  }

  public double getAverageTimePassed(){
    return timePassedList.stream().mapToDouble(a -> a).average().orElse(0.0);
  }

  public double getAveragePeopleWaiting(){
    return nbPeopleWaiting.stream().mapToDouble(a -> a).average().orElse(0.0);
  }

  @Override
  public String toString(){
    String msg = "DATA COLLECTOR " + getAverageTimePassed() + " " + getAveragePeopleWaiting();
    return msg;
  }
}
