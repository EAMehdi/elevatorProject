import java.util.*;

public class Main {
  public static void main (String[] args){

    // Open Menu Principale
    menu();
  }

  public static void menu(){

    // Set all the displayed String
    String choix1 = "Read Config.txt, Tapez 1";
    String choix2 = "Tapez 2";
    String choix3 = "Tapez 3";
    String choix4 = "Tapez 4";
    String choix10 = "EXIT : Tapez 10 or 0";
    String header = "===================== S I M U L A T E U R    A S C E N C E U R ===================";

    // Print all String
    System.out.println(header);
    System.out.println(choix1);
    System.out.println(choix2);
    System.out.println(choix3);
    System.out.println(choix4);
    System.out.println(choix10 + "\n-------------");

    // Int Getter
    Scanner scanner = new Scanner(System.in);
    int choice=1;

    while(choice != 0){
      try{

        choice = scanner.nextInt();
        switch (choice) {
          case 1:
          System.out.println("Bravo choix = 1\n");
          try{
            Map<String, Integer> test = new HashMap<>();
            test = FileRead.readConfig();

            for(Map.Entry<String, Integer> entry : test.entrySet()) {
              System.out.println(entry.getKey());
              System.out.println(entry.getValue());
            }

          }
          catch(Exception e){
            System.out.println(e);
          }
          break;
          case 2:
          System.out.println("Super choix = 2\n");
          break;
          case 3:
          System.out.println("Incroyable choxi = 3\n");
          break;
          case 4:
          System.out.println("Mazette ! choix=4 !\n");
          break;
          case 10:
          System.out.println("Fin");
          System.exit(0);
          default:
          System.out.println("Numero Invalide !\n");
          break;
        }

        System.out.println("-------------\n"+choix1);
        System.out.println(choix2);
        System.out.println(choix3);
        System.out.println(choix4 + "\n-------------");
      }

      catch(InputMismatchException  e){
        System.out.println("Vous n'avez pas entrer un int !");
        scanner.next(); // clear le scanner
        continue;
      }
    }

  }
}
