package hello.greetings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class HalGreetings {
	public static void main(String[] args) throws IOException {
		String[] fullname;
		if (args == null || args.length == 0) {
			System.out.println("Hello, I am Hal, and you are?");
			fullname = readFullName(System.in);
		} else {
			System.out.println("Hello, I am Hal.");
			fullname = args;
		}
		echoGreetings(System.out, fullname);
	}

	private static String[] readFullName(InputStream in) throws IOException{
//		byte[] input_du_Buffer = new byte[20];
//		int nbr_lu = 0;
		// permet de tester sans erreur

//		try {
//			nbr_lu = in.read(input_du_Buffer);
			// stock le nombre de caractere dans nbr_lu
			// stock les caracteres dans input_du_Buffer
			// read lit ce que l'on tape
			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

//		String fullName = new String(input_du_Buffer, 0, nbr_lu).trim();
//		// crée un string avec toute les info d'avant
//		//.trim() supp les espaces en trop
//		return fullName.split(" ");
		// decoupe ce string par espace
		
		
		InputStreamReader r = new InputStreamReader(in);
		BufferedReader br = new BufferedReader(r);
		String line = br.readLine();
		int compteur = 0;
		String[] names;
		
		
		//va compter les espaces dans l'input pour pouvoir init un tableau d'index
		for (int j = 0; j < line.length(); j++) {
            if (line.charAt(j) == ' ') {
                compteur++;
            }
        }
		
		int[] index = new int[compteur];
		int compteur_leretour = 0; // la uniquement pour donner un index croissant
		
		for(int j=0; j<line.length();j++) {
			if (line.charAt(j) == ' ') {
				index[compteur_leretour] = j;
				compteur_leretour++;
			}
		}
		
		
		int Indexdavant = 0; //prend l'index pour le debut de mot
		names = new String[compteur+1]; // stockera les noms
		
		for (int i=0;i<compteur;i++) {
			names[i] = line.substring(Indexdavant, index[i]); 
            Indexdavant = index[i] + 1;//actualise la position pour le prochain mot
			
		}
		names[compteur] = line.substring(Indexdavant); // dernier mot
		
		
		
		return names;
	}

	private static void echoGreetings(PrintStream ps, String[] names) {
		ps.print("Greetings ");
		for (int i = 0; i < names.length; i++) {
			ps.printf("%s ", names[i]);
		}
		ps.print("!");
	}
}