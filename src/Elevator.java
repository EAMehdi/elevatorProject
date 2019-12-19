public class Elevator
{
  private static int count = 0;
  private int idElevator, currentFloor, minFloor, maxFloor, nbPassagers, maxPassagers, velocity;
  private boolean working;
  private enum state{wait,goUp,goDown};

  public Elevator(int currentF, int maxF, int nbrPa, int maxP, int vly)
  {
    this.idElevator= this.count++;
    this.currentFloor=currentF;
    this.minFloor= 0; // les étages sont numérotés de 0 à n
    this.maxFloor=maxF;
    this.nbPassagers=nbrPa;
    this.maxPassagers=maxP;
    this.velocity=vly;
  }

  public Elevator(int maxF, int maxP, int vly)
  {
    this.idElevator= this.count++;
    this.currentFloor=0;
    this.minFloor= 0; // les étages sont numérotés de 0 à n
    this.maxFloor=maxF;
    this.nbPassagers=0;
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
    int nbPassagerPasMonter=newNbPassagers -(this.getMaxPassagers());
    if( newNbPassagers > this.getMaxPassagers())
    {
        this.nbPassagers=this.getMaxPassagers();
        throw new IllegalArgumentException("L'ascenseur ne peut pas prendre tout le monde !! Il y " + nbPassagerPasMonter + " passagers qui n'ont pas pu monter.");
    }
    else
        this.nbPassagers=newNbPassagers;
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

  @Override
  public String toString(){
    return this.idElevator + " " + this.velocity;
  }
}
