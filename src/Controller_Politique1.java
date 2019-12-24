import java.util.*;
import java.lang.Math;

public class Controller_Politique1 extends Controller{


  //  Queue<Integer> pickupLocations;

  public Controller_Politique1(){
    super();
    super.addElevators();
    super.addPersonnesFromFile();
  }

  public void destination(Integer elevatorId, Integer destinationFloor) {
    getListElevator_Controller().get(elevatorId).addDestination(destinationFloor);
  }


  // Stop => Plus personne qui attendre
  // Politique 1 => Ascenseur monte/descend, prend que les personnes dans le sens dans lequel elle est
  public void simulationPolitique1Until(){
    boolean stopSimulation=false;
    int step_sim=1; // pas de temps

    LinkedList<Personne>  waitingList = new LinkedList<>(getListPersonne_Controller());

    // (K,V) ==> (idElevator , Liste<idPersonne>)
    //Map<Integer,List<Integer>> destinations = new HashMap<Integer, List<Integer>>();

    // (K,V) ==> (idElevator , Liste<idPersonne>)
    //Map<Integer,Integer> inElevator = new HashMap<>();

    ArrayList<Elevator> notBlockedElevators= new ArrayList<>();

    Map<Personne,Elevator> listBestE;


    while(!stopSimulation){
      if(waitingList.isEmpty()){
        stopSimulation = true;
      }

      for(Elevator el : getListElevator_Controller()){
        if(step_sim > el.getLockTime()){
          notBlockedElevators.add(el);
        }
      }

      listBestE = new HashMap<>();
      // Une personne ne peut pas entrer car il y  a trop de monde, elle rappelle ==> Changer Step++

      for(Personne p : waitingList){
        if(p.getStep() == step_sim){
          listBestE.put(p,bestElevator(p,getListElevator_Controller()));
          //destinations.put(bestElevator(p,getListElevator_Controller()).getIdElevator(),p.getIdPersonne());
          bestElevator(p,getListElevator_Controller()).addDestination(p.getStartFloor());
        }
      }
      //
      // for(Elevator elv : destinations.entrySet())not {
      //   Integer keyIdElevator = entry.getKey();
      //   List<Integer> listePersDest = entry.getValue();
      //
      // }


      for(Elevator el : notBlockedElevators){
        if(el.getCurrentFloor() < el.getNextDestination()){
          el.stepUp();
          el.setState("up");
        }
        else if(el.getCurrentFloor() > el.getNextDestination()){
          el.stepDown();
          el.setState("down");
        }
        else if(el.getCurrentFloor() == el.getNextDestination()){
          el.setState("wait");
          //
          // for(Personne p : listBestE){
          //   bestElevator(p,getListElevator_Controller()).addDestination(p.getStartFloor());
          //   }
          // }
        }
      }
      step_sim++;
    }
  }

    private Elevator bestElevator(Personne p, ArrayList<Elevator> myListElevator){
      // ArrayList<Elevator> myListElevator= new ArrayList<>(getListElevator_Controller());
      Elevator max = myListElevator.get(0);
      Elevator bestElevator = max;
      int diffStep=(max.getMaxFloor());


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


  }
