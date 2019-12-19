import java.util.*;

public class PersonneSorter {

  public ArrayList<Personne> listPer = new ArrayList<>();

  public PersonneSorter(ArrayList<Personne> listOfPer) {
    this.listPer = listOfPer;
  }

  public ArrayList<Personne> getSortedListPersonne() {
    Collections.sort(listPer,Personne.stepComparator);
    return listPer;

  }

}
