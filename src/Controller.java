import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Controller{
  ArrayList<Elevator> listElevator;
  ArrayList <Personne> listPersonne;

  public Controller(){
    listElevator = new ArrayList<>();
    listPersonne = new ArrayList<>();
  }

  public void addElevators(){
    Map<String, Integer> configValues = new HashMap<>();
    try{
      configValues = FileRead.readConfig();
      int maxFloor = configValues.get("nbFloors");
      int velocity = configValues.get("velocity");
      int capMax = configValues.get("capacity");

      for (int i=0 ; i<=configValues.get("nbLifts"); i++) {
        this.listElevator.add( new Elevator (maxFloor, capMax, velocity));
      }

    }
    catch(Exception e){
      System.out.println(e.getMessage());
    }
    finally{
      for(Elevator e : this.listElevator){
        System.out.println(e);
      }
    }


  }
  
  // ajout random loi de poisson
  private void addPersonnes(ArrayList lP){
    this.listPersonne= lP
  }

  // ajout from file
  private void addPersonnesFromFile(){
    ArrayList<Personne> fileList = new ArrayList<>();
    try{
      fileList = FileRead.readPersonFile();
      this.listPersonne= lP
    }
    catch(Exception e){
      System.out.println(e.getMessage());
    }
  }

}
