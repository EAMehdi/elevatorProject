import java.io.File;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;


public abstract class FileRead{

  private static boolean isConfigHere(){
    String path = new File("").getAbsolutePath();
    boolean b = new File(path + "/config/config.txt").exists();
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
  }

  // public interface DataFile{
  //   public int nbFloors,nbLifts,capacity,velocity;
  // }
