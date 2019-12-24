import java.util.*;
import java.lang.Math;

public class Controller_Politique1 extends Controller{

  public Controller_Politique1(){
    super();
    super.addElevators();
    super.addPersonnesFromFile();
  }

  // Stop => Plus personne qui attendre
  // Politique 1 => Ascenseur monte/descend, prend que les personnes dans le sens dans lequel elle est
  public void simulationPolitique1Until(){
    boolean stopSimulation=false;
    int step_sim=1; // pas de temps
    Queue<Personne>  waitingList = new Queue<>(super.getListPersonne());
    // (K,V) ==> (idElevator , Liste<idPersonne>)
    Map<Integer,List<Integer>> inElevator = new HashMap<Integer, List<Integer>>();
    ArrayList<Personne,Elevator> listBestE;

    while(!stopSimulation){
      listBestE = new ArrayList<>();
      for(Personne p : waitingList){
        if(p.getStep() == step_sim){
          listBestE.add(p,bestElevator(p));
        }
      }




      if(waitingList.isEmpty()){
        stopSimulation = true;
      }
      step_sim++;
    }
  }

  private Elevator bestElevator(Personne p){
    ArrayList<Elevator> myListElevator= new ArrayList<Elevator>(super.getListElevator());
    Elevator bestElevator;
    int diffStep =this.mylistElevator.get(0).getMaxFloor();
    for(Elevator e : myListElevator){
      int calc= calcDiffStep(e,p);
      if(calc == 0){
        diffStep= calc;
        bestElevator = e;
      }
      else if(calc < 0){
        if(calc < diffStep){
          if(p.isGoingUp() && e.isGoUp()){
            bestElevator = e;
            diffStep= calc;
          }
        }
      }
      else if(calc > 0){
        if(calc < diffStep){
          if(!p.isGoingUp() && e.isGoDown()){
            bestElevator = e;
            diffStep= calc;
          }
        }
      }
      else if(e.isWaiting()){
          bestElevator = e;
          diffStep = calc;
      }
    }

    return bestElevator;
  }

  private int calcDiffStep(Elevator e, Personne p){
    return e.getCurrentFloor() - p.getStartFloor();
  }

  // private int blockStep(){
  //   return e.getCurrentFloor() - p.getStartFloor();
  // }


}


//Remplacer la liste bestElevator par un array.
