public class Personne{
  private static int count = 0;
  private int id, initStep,finalStep ;

  public Personne(){
    setId(count++);
  }

  public int getId(){
    return this.id;
  }
  private void setId(){
    this.id= id;
  }
}
