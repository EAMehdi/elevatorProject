public class Elevator{
  private static int count = 0;
  private int idElevator, currentStep, minStep, maStep, nbPassagers;
  private boolean working;
  private enum state{wait,goUp,goDown};

  public Elevator(){
    setIdElevator(count++);
    working = true;
  }
  public Elevator(int min, int max){
    this();
    this.minStep=min;
    this.minStep=max;
  }

  public int getIdElevator(){
    return this.idElevator;
  }

  public void setIdElevator(int id){
    this.idElevator = id;
  }

  public int getCurrentStep(){
    return currentStep;
  }
}
