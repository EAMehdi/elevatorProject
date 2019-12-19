import java.io.File;
import java.util.*;

public abstract class FileRead{

  private static boolean isConfigHere(){
    String path = new File("").getAbsolutePath();
    boolean b = new File(path + "/config/config.txt").exists();
    return b;
  }

  private static boolean isPersonFileHere(){
    String path = new File("").getAbsolutePath();
    boolean b = new File(path + "/config/personne.txt").exists();
    return b;
  }

  public static Map readConfig() throws Exception{
    if(!isConfigHere()){
      throw new Exception("Error FileRead: Le fichier config.txt n'est pas dans le dossier config");
    }
    else{
      Map<String, Integer> configValues = new HashMap<>();
      String path = new File("").getAbsolutePath();
      File file = new File(path+"/config/config.txt");
      Scanner sc = new Scanner(file);
      String line;

      while (sc.hasNextLine()){
        line = sc.nextLine();
        int value = Integer.valueOf(line.replaceAll("[^0-9]", ""));
        String key = line.replaceAll("[^a-zA-Z]", "");
        configValues.put(key, value);
      }
      return configValues;
    }
  }

  public static ArrayList readPersonFile() throws Exception{
    if(!isPersonFileHere()){
      throw new Exception("Error FileRead: Le fichier personne.txt n'est pas dans le dossier config");
    }
    else{
      ArrayList<Personne> mylistePersonne = new ArrayList<>();

      String path = new File("").getAbsolutePath();
      File file = new File(path+"/config/personne.txt");

      Scanner sc = new Scanner(file);
      String line;

      while (sc.hasNextLine()){
        line = sc.nextLine();
        System.out.println(line);
        String[] result = line.split(",");
        int[] tabInt = new int[result.length] ;
        int i = 0;
        for(String s : result){
          tabInt[i]=Integer.valueOf(s);
          i++;
        }
        mylistePersonne.add(new Personne (tabInt[0],tabInt[1],tabInt[2],tabInt[3]));
      }

      Comparator<Personne> comp = new Comparator<Personne>(){
        public int compare(Personne a, Personne b){
          return a.getStep()- b.getStep();
        }
      };
      
      return sortedList;
    }
  }

}
