import java.util.*;

/** Main Class with the Menu
* @author Mehdi EL AYADI
* @author Zakarya BOUALI
*/
public class Main {
  public static void main (String[] args){
    // Open Menu Principale
    menu();

    // DataCollector dc = new DataCollector();
    // int i = 0;
    // while(i<5){
    //   Controller_Politique1 c1 = new Controller_Politique1(5,1);
    //   dc.addSimData(c1.simulationPolitique1Until());
    //   i++;
    // }
    // System.out.println(dc);
  }




  public static void menu(){
    // Set all the displayed String
    String choix1 = "1- Simulation avec une politique classique (Politique1)";
    String choix2 = "2- Simulation avec une polique haut/bas (Politique2)";
    String choix10 = "EXIT : Tapez 10 ou 0";
    String header = "===================== S I M U L A T E U R    A S C E N C E U R ===================";
    header += "\n\tFaites un choix (1/2/10)";

    // Print all String
    System.out.println(header);
    System.out.println(choix1);
    System.out.println(choix2);
    System.out.println(choix10 + "\n-------------");

    // Int Getter
    Scanner scanner = new Scanner(System.in);
    int choice=1;

    while(choice != 0){
      try{

        choice = scanner.nextInt();
        System.out.println("");
        switch (choice) {
          case 1:
          System.out.println("Choix Politique 1 - OK\n");
          //launch Menu Politique 1
          menu2(1);
          break;
          case 2:
          System.out.println("Choix Politique 2 - OK\n");
          //launch Menu Politique 2
          menu2(2);
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
        System.out.println(choix10 + "\n-------------");
      }

      catch(InputMismatchException  e){
        System.out.println("Vous n'avez pas entrer un int !");
        scanner.next(); // clear le scanner
        continue;
      }
    }

  }

  public static void menu2(int n){
    // Set all the displayed String
    String choix1 = "1- Lire depuis le fichier personne.txt";
    String choix2 = "2- Liste de personnes alétoire (Loi de Poisson)";
    String choix10 = "EXIT : Tapez 10 ou 0";

    System.out.println("\n-------------\n" + choix1);
    System.out.println(choix2);
    System.out.println(choix10 + "\n-------------");

    // Int Getter
    Scanner scanner = new Scanner(System.in);
    int choice=1;

    while(choice != 0){
      try{

        choice = scanner.nextInt();
        System.out.println("");
        switch (choice) {
          case 1:
          if(n==1){
            System.out.println("Politique 1 - Lecture depuis le fichier");
            Controller_Politique1 cp= new Controller_Politique1();
            cp.simulationPolitique1Until();
            System.exit(0);
          }
          else if(n==2){
            System.out.println("Politique 2 - Lecture depuis le fichier");
          }
          break;
          case 2:
          ArrayList<Integer> valeurs = new ArrayList<>();
          valeurs = getPoisson();
          if(n==1){
            int max=0;
            DataCollector dc = new DataCollector();

            System.out.println("Politique 1 - Liste Personne Alétoire");
            System.out.println("ok");
            while(max <= valeurs.get(2)){
              Controller_Politique1 cp= new Controller_Politique1(valeurs.get(0), valeurs.get(1));
              dc.addSimData(cp.simulationPolitique1Until());
              max++;
            }
            System.out.println(dc);
            System.exit(0);
          }
          else if(n==2){
            System.out.println("Politique 2 - Liste Personne Alétoire");
          }
          break;
          case 10:
          System.out.println("Fin");
          System.exit(0);
          default:
          System.out.println("Numero Invalide !\n");
          break;
        }
      }

      catch(InputMismatchException  e){
        System.out.println("Vous n'avez pas entrer un int !");
        scanner.next(); // clear le scanner
        continue;
      }
    }
  }



  public static ArrayList<Integer> getPoisson(){
    // Set all the displayed String
    String choix1 = "\nLe nombre de personne dans la simulation:";
    String choix2 = "\nIntervale de temps (Loi de Poison):";
    String choix3 = "\nNombre de simulation :";
    Scanner scanner = new Scanner(System.in);
    ArrayList<Integer> values = new ArrayList<>();
    int nombreP=0;
    int intervale=0;
    int nbSimulation=0;
    boolean isNumberOk = false;
    boolean isIntervaleOk = false;
    boolean isNbSimOk = false;

    try{
      System.out.println(choix1);
      while(!isNumberOk){

        nombreP = scanner.nextInt();
        if(nombreP <= 0){

          System.out.println("veuillez enter un chiffre au dessus de 0");
        }
        else{
          System.out.println("Etes vous sur de faire une simulation de " + nombreP + " personnes ? (y/n)");
          String s1= scanner.next();
          if(s1.equalsIgnoreCase("Y")){
            isNumberOk = true;
          }
        }
      }


      System.out.println(choix2);
      while(!isIntervaleOk){
        intervale = scanner.nextInt();
        if(intervale <= 0){
          System.out.println("veuillez enter un chiffre au dessus de 0");
        }
        else{
          System.out.println("Voulez vous prendre un intervale de " + intervale + " pas de temps ? (y/n)");
          String s2= scanner.next();
          if(s2.equalsIgnoreCase("Y")){
            isIntervaleOk = true;
          }
        }
      }
      System.out.println(choix3);
      while(!isNbSimOk){
        nbSimulation = scanner.nextInt();
        if(nbSimulation <= 0){
          System.out.println("veuillez enter un chiffre au dessus de 0");
        }
        else{
          System.out.println("Voulez vous faire :" + nbSimulation + " simulation ? (y/n)");
          String s2= scanner.next();
          if(s2.equalsIgnoreCase("Y")){
            isNbSimOk = true;
          }
        }
      }
      values.add(nombreP);
      values.add(intervale);
      values.add(nbSimulation);

    }
    catch(InputMismatchException  e){
      System.out.println("Vous n'avez pas entrer une valeur correcte !");
      scanner.next(); // clear le scanner
    }
    return values;
  }

}
