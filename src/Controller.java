import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Map;
import java.util.HashMap;

public class Controller{
    ArrayList<Elevator> listElevator;
    TreeSet <Personne> listPersonne;

   public Controller(){
     listElevator = new ArrayList<>();
     listPersonne = new TreeSet<>();
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
   //
   // private void addPersonnes(){
   //
   // }

}
