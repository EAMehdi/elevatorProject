// import java.util.*;
// import java.lang.Math;
//
// public class Controller_Politique1 extends Controller{
//
//   public Controller_Politique1(){
//     super();
//     addElevators();
//     addPersonnesFromFile();
//   }
//
//   // Stop => Plus personne qui attendre
//   // Politique 1 => Ascenseur monte/descend, prend que les personnes dans le sens dans lequel elle est
//   public void simulationPolitique1Until(){
//     boolean stopSimulation=false;
//     int step_sim=1; // pas de temps
//
//     Queue<Personne>  waitingList = new Queue<>(this.listPersonne);
//     // (K,V) ==> (idElevator , Liste<idPersonne>)
//     Map<Integer,List<Integer>> inElevator = new HashMap<Integer, List<Integer>>();
//
//     while(!stopSimulation){
//
//       if(waitingList.isEmpty()){
//         stopSimulation = true;
//       }
//
//       ArrayList<Personne,Elevator> listBestE = new ArrayList<>();
//       for(Personne p : waitingList){
//         if(p.getStep() == step_sim){
//           listBestE.add(p,bestElevator(p));
//
//         }
//       }
//
//
//
//
//       step_sim++;
//     }
//   }
//
//   private Elevator bestElevator(Personne p){
//     ArrayList<Elevator> myListElevator= new ArrayList<>(this.listElevator);
//     Elevator bestElevator;
//     int diffStep=(mylistElevator.get(0)).getMaxFloor();
//
//
//     for(Elevator e : myListElevator){
//       int calc= calcDiffStep(e,p)
//       if(calc == 0){
//         diffStep= calc;
//         bestElevator = e;
//       }
//       else if(calc < 0){
//         if(calc < diffStep){
//           if(p.isGoingUp() && e.isGoUp()){
//             bestElevator = e;
//             diffStep= calc;
//           }
//         }
//       }
//       else if(calc > 0){
//         if(calc < diffStep){
//           if(!p.isGoingUp() && e.isGoDown()){
//             bestElevator = e;
//             diffStep= calc;
//           }
//         }
//       }
//       else if(e.isWaiting()){
//           bestElevator = e;
//           diffStep = calc;
//       }
//     }
//
//     return bestElevator;
//   }
//
//   private int calcDiffStep(Elevator e, Personne p){
//     return e.getCurrentFloor() - p.getStartFloor();
//   }
//
//
// }
