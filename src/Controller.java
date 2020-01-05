import java.util.*;

/**Controller
* @author Mehdi EL AYADI
* @author Zakarya BOUALI
*/
public abstract class Controller implements TransfertTime{
  private ArrayList<Elevator> listElevator_Controller;
  private ArrayList <Personne> listPersonne_Controller;

  /**
  * Controller class constructor
  */
  public Controller(){
    listElevator_Controller = new ArrayList<>();
    listPersonne_Controller = new ArrayList<>();
  }

  /**
  * Add Elevators in listElevator_Controller (here from the file config.txt)
  */
  protected void addElevators(){
    Map<String, Integer> configValues = new HashMap<>();
    try{
      configValues = FileRead.readConfig();
      int maxFloor = configValues.get("nbFloors");
      int velocity = configValues.get("velocity");
      int capMax = configValues.get("capacity");

      for (int i=1 ; i<=configValues.get("nbLifts"); i++) {
        this.listElevator_Controller.add( new Elevator (maxFloor, capMax, velocity));
      }

    }
    catch(Exception e){
      System.out.println(e.getMessage());
    }
    finally{
      for(Elevator e : this.listElevator_Controller){
        //System.out.println(e);
      }
    }
  }

  /**
   * Add Personne in listPersonne_Controller randomly with Poisson Distribution
   * @param  n         nmber of personnes
   * @param  avg       Average time in Poisson Distribution
   * @throws Exception Send error if doesn't work
   */
  protected void addPersonnesRandom(int n,int avg) throws Exception{

    if(!this.listPersonne_Controller.isEmpty()){
      throw new Exception("List of Personne already completed");
    }
    else{
      try{
        ArrayList<Personne> mylistePersonne = new ArrayList<>();
        int maxFloor;
        Map<String, Integer> configValues = new HashMap<>();
        configValues = FileRead.readConfig();
        maxFloor = configValues.get("nbFloors");

        int i=0;
        ArrayList<Personne> randomList = new ArrayList<>();
        PoissonDistribution poissonStep = new PoissonDistribution(avg);
        i = n;
        while ( i > 0 ) {
          int start,end;
          start =new Random().nextInt(maxFloor);
          end =new Random().nextInt(maxFloor);
          while(end == start){
            end =new Random().nextInt(maxFloor);
          }
          mylistePersonne.add(new Personne ((int)poissonStep.next(),start,end));
          i--;
        }


          PersonneSorter sortedP = new PersonneSorter(mylistePersonne);
          this.listPersonne_Controller= sortedP.getSortByStep();
      }
      catch(Exception e){
        System.out.println(e.getMessage());
      }
      //System.out.println("displayed random list of personne");
      //displayListPersonne();
    }
}

/**
* Add Personnes in listPersonne_Controller (from a File)
*/
protected void addPersonnesFromFile() throws Exception{
  if(!this.listPersonne_Controller.isEmpty()){
    throw new Exception("List of Personne already completed");
  }
  else{
    ArrayList<Personne> fileList = new ArrayList<>();
    try{
      fileList = FileRead.readPersonFile();
      this.listPersonne_Controller= fileList;
    }
    catch(Exception e){
      System.out.println(e.getMessage());
    }
  }
}

public  ArrayList <Personne> getListPersonne(){
  return this.listPersonne_Controller;
}
public  ArrayList <Elevator> getListElevator(){
  return this.listElevator_Controller;
}

public int compute(int in, int out, int stay){
  // if(in+out > stay){
  //   return stay-in-out;
  // }
  return 0;
}


protected ArrayList<Elevator> getListElevator_Controller(){
  return this.listElevator_Controller;
}

protected ArrayList<Personne> getListPersonne_Controller(){
  return this.listPersonne_Controller;
}

private void displayListPersonne(){
  for(Personne p : this.listPersonne_Controller){
    System.out.println(p);
  }
}

}
