import java.util.Comparator;
/** Personne who goes into the elevator
* @author Mehdi EL AYADI
* @author Zakarya BOUALI
*/
public class Personne{
  private static int count = 0;
  private int idPersonne, step , startFloor, endFloor;
  private enum Direction {UP,DOWN};
  private Direction dir;
  // step = pas de temps à partir de l'ascenceur

  public Personne(int theStep, int theStartFloor, int theEndFloor){
    this.idPersonne= this.count++;
    this.step=theStep;
    this.startFloor=theStartFloor;
    this.endFloor=theEndFloor;
    setDirection();
  }

  public Personne(int id, int theStep, int theStartFloor, int theEndFloor){
    this.idPersonne= id;
    this.step=theStep;
    this.startFloor=theStartFloor;
    setEndFloor(theEndFloor);
    setDirection();
  }

  public int getIdPersonne(){
    return this.idPersonne;
  }

  private void setIdPersonne(int id){
    this.idPersonne= id;
  }

    public int getStep()
    {
      return this.step;
    }

    public void addNextStep(){
      this.step++;
    }

  public int getStartFloor()
  {
    return this.startFloor;
  }

  public int getEndFloor()
  {
    return this.endFloor;
  }

  private void setEndFloor(int i){
    if(this.getStartFloor() == i){
      i++;
    }
    this.endFloor=i;
  }

  private void setDirection(){
    if(getStartFloor() < getEndFloor()){
      this.dir=Direction.UP;
    }
    else{
      this.dir=Direction.DOWN;
    }
  }
  public boolean isGoingUp(){
    if(this.dir == Direction.UP)
      return true;
    return false;
  }


  public static Comparator<Personne> stepComparator = new Comparator<Personne>() {
    @Override
    public int compare(Personne p1, Personne p2) {
      return (p2.getStep() > p1.getStep() ? -1 :
      (p2.getStep() == p1.getStep() ? 0 : 1));
    }
  };

  @Override
  public String toString(){
    return ("Personne " +  getIdPersonne() + ", my state " + this.dir + ", at step: " + getStep() + "\n\tfrom: "+ getStartFloor() + " to: " + getEndFloor());
  }
}
