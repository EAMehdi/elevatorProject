public class Elevator
{
  private static int count = 0;
  private int idElevator, currentFloor, minFloor, maxFloor, nbPassagers, maxPassagers, velocity,timeStep;
  private boolean working;
  private enum ElevatorState {wait,goUp,goDown,open};
  private ElevatorState state;

  public Elevator(int currentF, int maxF, int nbrPa, int maxP, int vly)
  {
    this.currentFloor=currentF;
    this.state=ElevatorState.wait;
    this.timeStep=0;
    this.nbPassagers=nbrPa;

    this.idElevator= this.count++;
    this.minFloor= 0; // les étages sont numérotés de 0 à n
    this.maxFloor=maxF;
    this.maxPassagers=maxP;
    this.velocity=vly;
  }

  public Elevator(int maxF, int maxP, int vly)
  {
    this.currentFloor=0;
    this.nbPassagers=0;
    this.state=ElevatorState.wait;  
    this.timeStep=0;

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

  public int getNbPassagers() //Retourne le nombre de passagers dans cet ascenseur (int)
  {
    return this.nbPassagers;
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


  public void setTimeStep(int newTimeStep){
    if(newTimeStep <0)
    {
      this.timeStep=0;
    }
    else
    this.timeStep = newTimeStep;
  }


  public void oneStepDone(){
    if(this.timeStep-- <=0)
    {
      this.timeStep=0;
    }
    else
    this.timeStep--;
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
    if(this.state == ElevatorState.goUp)
    return true;
    return false;
  }


  @Override
  public String toString(){
    return this.idElevator + " " + this.velocity;
  }

}
