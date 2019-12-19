import java.util.Comparator;

public class Personne{
  private static int count = 0;
  private int idPersonne, step , startFloor, endFloor;
  // step = pas de temps à partir de l'ascenceur

  public Personne(int theStep, int theStartFloor, int theEndFloor){
    this.idPersonne= this.count++;
    this.step=theStep;
    this.startFloor=theStartFloor;
    this.endFloor=theEndFloor;
  }
  public Personne(int id, int theStep, int theStartFloor, int theEndFloor){
    this.idPersonne= id;
    this.step=theStep;
    this.startFloor=theStartFloor;
    this.endFloor=theEndFloor;
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

  public int getStartFloor()
  {
    return this.startFloor;
  }

  public int getEndFloor()
  {
    return this.endFloor;
  }

  public static Comparator<Personne> stepComparator = new Comparator<Personne>() {

  @Override
  public int compare(Personne p1, Personne p2) {

    return (p2.getStep() < p1.getStep() ? -1 :

            (p2.getStep() == p1.getStep() ? 0 : 1));

  }

};


}
