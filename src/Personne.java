public class Personne
{
  private static int count = 0;
  private int idPersonne, step, startFloor, endFloor;

  public Personne(int theStep, int theStartFloor, int theEndFloor){
    this.idPersonne= this.count++;
    this.step=theStep;
    this.startFloor=theStartFloor;
    this.endFloor=theEndFloor;
  }

  public int getIdPersonne()
  {
    return this.idPersonne;
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
}
