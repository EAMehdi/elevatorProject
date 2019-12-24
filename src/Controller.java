import java.util.*;

public class Controller implements TransfertTime{
  private ArrayList<Elevator> listElevator_Controller;
  private ArrayList <Personne> listPersonne_Controller;

  public Controller(){
    listElevator_Controller = new ArrayList<>();
    listPersonne_Controller = new ArrayList<>();
  }

  protected void addElevators(){
    Map<String, Integer> configValues = new HashMap<>();
    try{
      configValues = FileRead.readConfig();
      int maxFloor = configValues.get("nbFloors");
      int velocity = configValues.get("velocity");
      int capMax = configValues.get("capacity");

      for (int i=0 ; i<=configValues.get("nbLifts"); i++) {
        this.listElevator_Controller.add( new Elevator (maxFloor, capMax, velocity));
      }

    }
    catch(Exception e){
      System.out.println(e.getMessage());
    }
    finally{
      for(Elevator e : this.listElevator_Controller){
        System.out.println(e);
      }
    }

  }

  // ajout random loi de poisson
  private void addPersonnesRandom(ArrayList lP){
    this.listPersonne_Controller= lP;
  }

  // ajout from file
  protected void addPersonnesFromFile(){
    ArrayList<Personne> fileList = new ArrayList<>();
    try{
      fileList = FileRead.readPersonFile();
      this.listPersonne_Controller= fileList;
    }
    catch(Exception e){
      System.out.println(e.getMessage());
    }
  }

  public  ArrayList <Personne> getListPersonne(){
    return this.listPersonne;
  }
  public  ArrayList <Elevator> getListElevator(){
    return this.listElevator;
  }

  public int compute(int in, int out, int stay){
    if(in+out > stay){
      return stay-in-out;
    }
    return in+out;
  }

  protected List getListElevator_Controller(){
    return this.listElevator_Controller;
  }

  protected List getListPersonne_Controller(){
    return this.listPersonne_Controller;
  }

}
