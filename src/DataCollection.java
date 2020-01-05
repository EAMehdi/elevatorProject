public class DataCollection{

  int timePassed,nbWaiting;

  public DataCollection(){
  }

  public int getTime(){
    return this.timePassed;
  }

  public int getNbWaitPeople(){
    return this.nbWaiting;
  }

  public void setDataCollection(int timePassed, int nbWaiting){
    this.timePassed = timePassed;
    this.nbWaiting = nbWaiting;
  }
}
