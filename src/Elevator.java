import java.util.*;

public class Elevator
{
  private static int count = 1;
  private int idElevator, currentFloor, minFloor, maxFloor, nbPassagers, maxPassagers, velocity;
  private int lockTime; // The step until the Elevator can move
  private ArrayList<Personne> listePassagers; // list of Personne inside the elevator
  private LinkedList<Integer> destinations; // list of floors destinations
  private enum ElevatorState {wait,goUp,goDown}; // Different state
  // wait == stay in place, goUp == going Up...
  private ElevatorState state;

  /**
  * Constructor of Elevator
  * @param currentF [The current floor]
  * @param maxF     [The top floor he can get in]
  * @param nbrPa    [Number of people inside the Elevator]
  * @param maxP     [Maximum authorized people inside the elevator]
  * @param vly      [Velocity of the elevator]
  */

  public Elevator(int currentF, int maxF, int nbrPa, int maxP, int vly)
  {
    this.currentFloor=currentF;
    this.state=ElevatorState.wait;
    this.lockTime=0;
    this.nbPassagers=nbrPa;
    this.destinations = new LinkedList<>();
    this.listePassagers = new ArrayList<>();

    this.idElevator= this.count++;
    this.minFloor= 0; // les étages sont numérotés de 0 à n
    this.maxFloor=maxF;
    this.maxPassagers=maxP;
    this.velocity=vly;
  }


  /**
  * [Elevator description]
  * @param maxF [Max Floor he can reach]
  * @param maxP [Maximum number of passanger]
  * @param vly  [Velocity of the elevator]
  */
  public Elevator(int maxF, int maxP, int vly)
  {
    this.currentFloor=0;
    this.nbPassagers=0;
    this.state=ElevatorState.wait;
    this.lockTime=0;
    this.destinations = new LinkedList<>();
    this.listePassagers = new ArrayList<>();

    this.idElevator= this.count++;
    this.minFloor= 0; // les étages sont numérotés de 0 à n
    this.maxFloor=maxF;
    this.maxPassagers=maxP;
    this.velocity=vly;
  }

  public int getIdElevator() // Retourne l'id de l'ascenseur (int)
  {
    return this.idElevator;
  }

  public int getCurrentFloor() // Retourne l'étage où se situe l'ascenseur (int)
  {
    return this.currentFloor;
  }


  public void setCurrentFloor(int newCurrentFloor) throws IllegalArgumentException
  {
    if (newCurrentFloor<this.getMinFloor() || newCurrentFloor>this.getMaxFloor())
    throw new IllegalArgumentException("Cet étage n'existe pas !!");
    else
    this.currentFloor=newCurrentFloor;
  }

  public int getMinFloor() //Retourne l'étage le plus bas (int)
  {
    return this.minFloor;
  }
  public void setMinFloor(int newMinFloor)
  {
    this.minFloor=newMinFloor;
  }

  public int getMaxFloor() //Retourne l'étage le plus haut (int)
  {
    return this.maxFloor;
  }
  public void setMaxFloor(int newMaxFloor)
  {
    this.maxFloor=newMaxFloor;
  }

  //Retourne le nombre de passagers dans cet ascenseur (int)
  public int getNbPassagers()
  {
    return this.listePassagers.size();
  }
  public void setNbPassagers(int newNbPassagers)throws IllegalArgumentException
  {
    int nbPassagerPasMonter= newNbPassagers - (this.getMaxPassagers());
    if( newNbPassagers > this.getMaxPassagers())
    {
      this.nbPassagers=this.getMaxPassagers();
      throw new IllegalArgumentException("L'ascenseur ne peut pas prendre tout le monde !! Il y " + nbPassagerPasMonter + " passagers qui n'ont pas pu monter.");
    }
    else
    this.nbPassagers=newNbPassagers;
  }

  public boolean addPassager(Personne p){
    if(this.listePassagers.size() >= this.getMaxPassagers())
    return false;
    this.listePassagers.add(p);
    setNbPassagers(getNbPassagers());
    return true;
  }

  public boolean removePassager(Personne p){
    for(Personne pe : this.getListPassagers()){
      if(pe.getIdPersonne() == p.getIdPersonne()){
        this.listePassagers.remove(p);
        return true;
      }
    }
    return false;
  }

  public ArrayList<Personne> getListPassagers(){
    return this.listePassagers;
  }

  public void addLockTime(int n){
    this.lockTime+=n;
  }

  public void setLockTime(int n){
    this.lockTime=n;
  }

  public int getLockTime(){
    return this.lockTime;
  }

  public void stepUp(){
    setCurrentFloor(getCurrentFloor()+1);
  }

  public void stepUpLockTime(int n){
    setCurrentFloor(getCurrentFloor()+1);
    setLockTime(n+getVelocity());
  }
  
  public void stepDown(){
    setCurrentFloor(getCurrentFloor()-1);
  }

  public void stepDownLockTime(int n){
    setCurrentFloor(getCurrentFloor()-1);
    setLockTime(n+getVelocity());
  }

  private void addDestination(int n){
    this.destinations.add(n);
  }

  public void addDestinationNoDuplicate(int n){
    boolean doublon=false;
    for(int i : this.destinations){
      if(i == n){
        doublon = true;
      }
    }
    if(!doublon){
      addDestination(n);
    }
  }

  public int getNextDestination(){
    return this.destinations.peek();
  }

  public void removeDestination(){
    this.destinations.remove();
  }

  public LinkedList<Integer> getListDestinations(){
    return this.destinations;
  }

  public int getMaxPassagers() //Retourne le nombre de passagers maximum par ascenseur (int)
  {
    return this.maxPassagers  ;
  }
  public void setMaxPassagers(int newMaxPassagers)
  {
    this.maxPassagers=newMaxPassagers;
  }

  public int getVelocity() //Retourne le nombre de pas de temps nécessaire passer à l'étage suivant ou précédant.
  {
    return this.velocity;
  }
  public void setVelocity(int newVelocity)
  {
    this.velocity=newVelocity;
  }


  public boolean isWaiting(){
    if(this.state == ElevatorState.wait)
    return true;
    return false;
  }

  public boolean isGoUp(){
    if(this.state == ElevatorState.goUp)
    return true;
    return false;
  }


  public boolean isGoDown(){
    if(this.state == ElevatorState.goDown)
    return true;
    return false;
  }

  public void setState(String s){
    switch(s){
      case "up":
      this.state= ElevatorState.goUp;
      break;
      case "down":
      this.state= ElevatorState.goDown;
      break;
      case "wait":
      this.state= ElevatorState.wait;
      break;
      default:
      this.state= ElevatorState.wait;
    }

  }

  public String toString(){
    String msg = "Elevator: "+this.idElevator;
    msg+= ", MyState: " + this.state;
    msg+= " currentFloor: " + getCurrentFloor();
    if(!this.listePassagers.isEmpty()){
      msg+=" listePas:(";
      for(Personne p : listePassagers){
        msg+= p.getIdPersonne() + ",";
      }
      msg+=")";
    }
    if(!this.destinations.isEmpty()){
      msg+=" + Destinations:(";
      for(Integer i : this.destinations){
        msg+= i + ",";
      }
      msg+=")";
    }
    return msg;
  }
}
