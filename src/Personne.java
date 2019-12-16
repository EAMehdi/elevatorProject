public class Personne{
  private static int count = 0;
  private int idPersonne, initStep,finalStep ;

  public Personne(){
    setIdPersonne(count++);
  }

  public int getIdPersonne(){
    return this.idPersonne;
  }
  private void setIdPersonne(int id){
    this.idPersonne= id;
  }
}
