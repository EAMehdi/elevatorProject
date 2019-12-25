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
      displayWaitingList(waitingList);

      for(Elevator el : getListElevator_Controller()){
        if(step_sim > el.getLockTime()){
          System.out.println("Step: " + step_sim + " et dans NotLockTime "+el);
          notBlockedElevators.add(el);
        }
      }

      listBestE = new HashMap<>();
      // Une personne ne peut pas entrer car il y  a trop de monde, elle rappelle ==> Changer Step++

      for(Personne p : waitingList){
        if(p.getStep() == step_sim){
          Elevator theBest = bestElevator(p,getListElevator_Controller());
          theBest.addDestination(p.getEndFloor());
          System.out.println(theBest);
          listBestE.put(p,theBest);
          //destinations.put(bestElevator(p,getListElevator_Controller()).getIdElevator(),p.getIdPersonne());
        }
      }
      System.out.println("ListeBE");
      for(Map.Entry<Personne,Elevator> entry : listBestE.entrySet()){
        int nbLoadPers=0, nbUnLoadPers=0;
        Personne keyP = entry.getKey();
        Elevator valueE = entry.getValue();
        System.out.println(keyP + "  " +  valueE);
      }


      //
      // for(Elevator elv : destinations.entrySet())not {
      //   Integer keyIdElevator = entry.getKey();
      //   List<Integer> listePersDest = entry.getValue();
      //
      // }


      for(Elevator el : notBlockedElevators){
        if(el.getListDestinations().isEmpty()){
          el.setState("wait");
        }
        else{
          if(el.getCurrentFloor() < el.getNextDestination()){
            el.stepUp();
            System.out.println("hop hop hop");
            el.setState("up");
          }
          else if(el.getCurrentFloor() > el.getNextDestination()){
            System.out.println("descend !");
            el.stepDown();
            el.setState("down");
          }
          else if(el.getCurrentFloor() == el.getNextDestination()){
            el.setState("wait");

            el.removeDestination();
            System.out.println("YOLO");

            for(Map.Entry<Personne,Elevator> entry : listBestE.entrySet()){
              int nbLoadPers=0, nbUnLoadPers=0;
              Personne keyP = entry.getKey();
              Elevator valueE = entry.getValue();

              for(Personne p : el.getListPassagers()){
                if(el.getCurrentFloor() == p.getEndFloor()){
                  el.removePassager(p);

                  System.out.println("remove " + p);
                  waitingList.remove(p);
                  
                  displayWaitingList(waitingList);

                  nbUnLoadPers++;
                }
              }

              if(el.getIdElevator() == valueE.getIdElevator()){
                boolean b = el.addPassager(keyP);

                if(!b){
                  for (Personne p : el.getListPassagers()){
                    if (p.getIdPersonne() == keyP.getIdPersonne())
                    {
                      p.addNextStep();
                    }
                  }
                }

                else{
                  nbLoadPers++;
                }
                System.out.println("Step:" + step_sim + " " + el +" avec " + keyP);

              }
              int waitLoad = compute(nbLoadPers, nbUnLoadPers, el.getNbPassagers());
              el.setLockTime(step_sim+waitLoad);
            }



            // loadUnloadPassagers(listBestE,el,waitingList,step_sim);
            // Pour ajouter les personnes dans leurs ascenceurs

            //
            // for(Map.Entry<Personne,Elevator> entry : listBestE.entrySet()){
            //   int nbLoadPers=0, nbUnLoadPers=0;
            //   Personne keyP = entry.getKey();
            //   Elevator valueE = entry.getValue();
            //
            //   for(Personne p : el.getListPassagers()){
            //     if(el.getCurrentFloor() == p.getEndFloor()){
            //       el.removePassager(p);
            //       waitingList.remove(p);
            //       nbUnLoadPers++;
            //     }
            //   }
            //
            //   if(el.getIdElevator() == valueE.getIdElevator()){
            //     boolean b = el.addPassager(keyP);
            //     if(!b){
            //       el.getListPassagers().get(keyP).addNextStep();
            //     }
            //     else{
            //       nbLoadPers++;
            //     }
            //     System.out.println("Step:" + step_sim + " " + el + "         " + keyP.getIdPersonne());
            //
            //   }
            //   int waitLoad = compute(nbLoadPers, nbUnLoadPers, el.getNbPassagers());
            //
            // }
            //


          }
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


  private void loadUnloadPassagers(Map<Personne,Elevator> listBestE, Elevator el,LinkedList<Personne> waitingList,int step_sim){

  }

  private void displayWaitingList(LinkedList<Personne> list){
    System.out.println("WAITING LIST");
    for(Personne o : list){
      System.out.println("Coucou "+o);
    }

    System.out.println("--------------");
  }

}
