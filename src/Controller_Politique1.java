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

    ArrayList<Elevator> notBlockedElevators;

    Map<Personne,Elevator> listBestE;
    listBestE = new HashMap<>();


    while(!stopSimulation){
      notBlockedElevators= new ArrayList<>();

      System.out.println("\n===========Step : " + step_sim + "========================");

      if(waitingList.isEmpty()){
        System.out.println("\n °°°°°°°°°°°°°°°°°°°°°°END °°°°°°°°°°°°°°°°°°°");
        stopSimulation = true;
      }
      displayWaitingList(waitingList);

      for(Elevator el : getListElevator_Controller()){
        if(step_sim > el.getLockTime()){
          System.out.println("Step: " + step_sim + " et dans NotLockTime "+el);
          notBlockedElevators.add(el);
        }
      }

      // Une personne ne peut pas entrer car il y  a trop de monde, elle rappelle ==> Changer Step++

      // Add the next destinations for the elevator
      for(Personne p : waitingList){
        if(p.getStep() == step_sim){
          Elevator theBest = bestElevator(p,getListElevator_Controller());
          // If 2 personnes start from the same Floor, we remove it (doublon)
          theBest.addDestinationNoDuplicate(p.getStartFloor());
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
      System.out.println("--- FIn ListeBE");



      //
      // for(Elevator elv : destinations.entrySet())not {
      //   Integer keyIdElevator = entry.getKey();
      //   List<Integer> listePersDest = entry.getValue();
      //
      // }

      for(Elevator el : notBlockedElevators){
        System.out.println("In Not Blocked " + el);

      }


      for(Elevator el : notBlockedElevators){
        System.out.println("\nDeplace or Not Elevator");
        if(el.getListDestinations().isEmpty()){
          el.setState("wait");
        }
        else{
          if(el.getCurrentFloor() == el.getNextDestination()){
            System.out.println("Current Floor == NexDestination de " + el);
            el.setState("wait");

            el.removeDestination();
            System.out.println("YOLO");

            for(Map.Entry<Personne,Elevator> entry : listBestE.entrySet()){
              System.out.println(entry.getKey() + " ######## " + entry.getValue());
            }
            for(Map.Entry<Personne,Elevator> entry : listBestE.entrySet()){

              int nbLoadPers=0, nbUnLoadPers=0;
              Personne keyP = entry.getKey();
              Elevator valueE = entry.getValue();

              // debarque tout les personnes de l'elevator qui sont déjà arrivé


              // for(Personne p : el.getListPassagers()){
              //   if(el.getCurrentFloor() == p.getEndFloor()){
              //     el.getListPassagers().remove(p);
              //     System.out.println("\n\nDebarquement de personne id=" + p.getIdPersonne());
              //     System.out.println("remove " + p);
              //     waitingList.remove(p);
              //
              //     displayWaitingList(waitingList);
              //
              //     nbUnLoadPers++;
              //   }
              // }

              Iterator<Personne> i = el.getListPassagers().iterator();

              while (i.hasNext()) {
                Personne s = i.next(); // must be called before you can call i.remove()
                if(el.getCurrentFloor() == s.getEndFloor()){
                    i.remove();
                    waitingList.remove(s);
                    nbUnLoadPers++;
                    System.out.println("\n\nDebarquement de personne id=" + s.getIdPersonne());
                }// Do something
              }

              if(el.getIdElevator() == valueE.getIdElevator() && el.getCurrentFloor() == keyP.getStartFloor()){
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
                  el.addDestinationNoDuplicate(keyP.getEndFloor());
                  nbLoadPers++;
                }
                System.out.println("Step:" + step_sim + " " + el +" avec " + keyP);

              }
              int waitLoad = compute(nbLoadPers, nbUnLoadPers, el.getNbPassagers());
              el.setLockTime(step_sim+waitLoad);
            }

          }

          else if(el.getCurrentFloor() < Collections.max(el.getListDestinations()) ){
            Collections.sort(el.getListDestinations());
            el.setState("up");
            el.stepUp();
            System.out.println("Monte ! pour ascenceur" + el);

          }
          else if(el.getCurrentFloor() >  Collections.max(el.getListDestinations()) ){
            Collections.sort(el.getListDestinations(),Collections.reverseOrder());
            System.out.println("descend !");
            el.setState("down");
            el.stepDown();
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

    if(p.isGoingUp()){
      for(Elevator e : myListElevator){
        int calc= calcDiffStep(e,p);
        if(e.isGoUp() || e.isWaiting()){
          if(calc <= 0){
            if(calc <= diffStep){
              diffStep=calc;
              bestElevator=e;
            }
          }
        }
      }
    }
    else{
      for(Elevator e : myListElevator){
        int calc= calcDiffStep(e,p);
        if(e.isGoDown() || e.isWaiting()){
          if(calc >= 0){
            if(calc <= diffStep){
              diffStep=calc;
              bestElevator=e;

            }
          }
        }
      }
    }

    return bestElevator;
  }

// bestElevator Version 1
    // for(Elevator e : myListElevator){
    //   int calc= calcDiffStep(e,p);
    //   if(calc == 0){
    //     diffStep= calc;
    //     bestElevator = e;
    //   }
    //   else if(calc < 0){
    //     if(calc < diffStep){
    //       if(p.isGoingUp() && e.isGoUp()){
    //         bestElevator = e;
    //         diffStep= calc;
    //       }
    //     }
    //   }
    //   else if(calc > 0){
    //     if(calc < diffStep){
    //       if(!p.isGoingUp() && e.isGoDown()){
    //         bestElevator = e;
    //         diffStep= calc;
    //       }
    //     }
    //   }
    //   else if(e.isWaiting()){
    //     bestElevator = e;
    //     diffStep = calc;
    //   }
    // }
// ############################################################


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
