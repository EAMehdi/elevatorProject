import java.util.*;

public class PersonneSorter {

  public ArrayList<Personne> listPer = new ArrayList<>();

  public PersonneSorter(ArrayList<Personne> listOfPer) {
    this.listPer = listOfPer;
  }

  public ArrayList<Personne> getSortByStep() {
    Collections.sort(listPer,Personne.stepComparator);
    return listPer;

  }

}
