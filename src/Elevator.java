public class Elevator{
  private static int count = 0;
  private int id, currentStep, minStep, maStep, nbPassagers;
  private boolean working;
  private enum state

  public Elevator(){
    setId(count++);
    working = true;
  }
  public Elevator(int min, int max){
    this();
    this.minStep=min;
    this.minStep=max;
  }

  public void setId(){
    return this.id;
  }
  public void setId(){
    this.id = id;
  }

  public int getCurrentStep(){
    return currentStep;
  }

  public enum getState(){
    return state;
  }

}
