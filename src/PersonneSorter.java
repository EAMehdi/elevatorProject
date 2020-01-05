import java.util.*;

/**
 * Class to help sort Personne by their step
 */

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
