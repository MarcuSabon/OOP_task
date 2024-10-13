package oop.games;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

public class HangedManSJ {
	
	
	private static String[] liste_de_mots; 
    private static String le_mot;
    private static char[] le_mot_prive;
    private static int la_vida;
    
  /*
   * The entry point, reduced to a minimum
   * like it should be, most of the time.
   * The list of possible words to guess
   * is given as the arguments.
   */
  public static void main(String[] args) {
	Scanner scan_air = new Scanner(System.in);
    init(args, System.in, System.out);
    play( System.in, System.out, scan_air);
  }

  /*
   * The initialization of the global variables (static fields).
   */
  
  
  public static void init(String[] args, InputStream is, PrintStream ps) {
	  if (args.length == 0) {
	        throw new IllegalArgumentException("La liste des mots ne peut pas être vide.");
	    }
	  liste_de_mots = new String[args.length];
	  for(int i=0; i<args.length;i++) {
		  liste_de_mots[i] = args[i];
	  }
	  ps.println("Bienvenue dans mon pendu :) !");
	  
	  la_vida = 10;
  }
  
  /*
   * Une fonction qui joue une partie et 
   * offre aux joueurs la possibilité de continuer à jouer ou non. 
   * La fonction qui choisit le mot à deviner
   * parmi la liste des mots possibles, en utilisant 
   * un nombre généré aléatoirement. Regardez la
   * classe Random dans le package java.util.
 */
  public static void play(InputStream is, PrintStream ps,Scanner scan_air) {
	  String veut_jouer = "oui";
	  Random ran_dome = new Random();
	  
	  
		    
	  
	  while(veut_jouer.equals("oui")) {
		  int in_de_x = ran_dome.nextInt(liste_de_mots.length);
		  le_mot = liste_de_mots[in_de_x];
		  ps.println(le_mot);
		  initWord(in_de_x);
		  
		  playGame(is, ps, scan_air);
		  
		  ps.print("Voulez-vous jouer à nouveau ? (oui/non): ");
		  veut_jouer = scan_air.nextLine().toLowerCase();
		  
		  
		  while (!veut_jouer.equals("oui") && !veut_jouer.equals("non")) {
			  ps.print("Réponse non valide. Veuillez entrer 'oui' ou 'non': ");
			  veut_jouer = scan_air.nextLine().toLowerCase();
		  }
			    
		  
	  }
	  ps.println("Merci bcp d'avoir joué !");
	  scan_air.close();
  }

  /*
   * The function initialize the game for the 
   * chosen word, given by its index in the 
   * array of words.
   */
  public static void initWord(int n) {
	  le_mot_prive = new char[liste_de_mots[n].length()];
	  for(int i=0;i<le_mot_prive.length;i++) {
		  le_mot_prive[i] = '_';
	  }
  }

  /*
   * A function to know if the current word
   * has been guessed yet or not. Returns
   * true if the current word has been guessed.
   */
  public static boolean guessed() {
	  for (char letter : le_mot_prive)
	  {
		  if(letter == '_') {
			  return false;
		  }
	  }
	  return true;
  }

  /*
   * A function to play one game, after a word
   * has been chosen. This function will allow only
   * a limited number of tries. 
   */
  public static void playGame(InputStream is, PrintStream ps, Scanner scan_air) {
	  
	  while(la_vida>0 && !guessed()) {
		  ps.println("Vous avez "+ la_vida + " vie(s)");
		  ps.println(le_mot_prive);
		  
		  ps.print("\n\nDonnez moi une lettre : ");
          char essai = scan_air.nextLine().charAt(0);
          
          
          
          if (essai >= 'A' && essai <= 'Z') {
        	  essai = Character.toLowerCase(essai);
          }
          
          
          
          while(essai < 'a' || essai > 'z') {
        	  
        	  
        	  ps.print("\n\nERREUR il me faut une lettre : ");
              essai = scan_air.nextLine().charAt(0);
              
              
              if (essai >= 'A' && essai <= 'Z') {
            	  essai = Character.toLowerCase(essai);
              }
          }
          
          
          boolean trouve = false;
          for (int i = 0; i < le_mot.length(); i++) {
              if (le_mot.charAt(i) == essai) {
                  le_mot_prive[i] = essai; 
                  trouve = true;
              }
          }
          if (!trouve) {
              la_vida--; 
              ps.println("Mauvaise réponse !");
          }
		  
		  
	  }
	  if (guessed()) {
          ps.println("Bien joué, ;) // le mot était : " + le_mot);
      } else {
          ps.println("PERDU !!! Le mot était : " + le_mot);
      }
	  
  }
}