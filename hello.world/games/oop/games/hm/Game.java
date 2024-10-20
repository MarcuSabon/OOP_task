package oop.games.hm;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Random;

public class Game {

	PrintStream ps;
	Keyboard kbd;
	HangedMan hm;
	Random rnd;
	int more_words;

	public Game(String[] words,InputStream in, PrintStream out) {
		this.ps=out;
		this.kbd= new Keyboard(in,out);
		this.rnd = new Random();
		this.hm = new HangedMan(words);
		this.more_words = words.length;
		
		
		
		
	}
	
	public void play() throws IOException {
		
		this.ps.println("Salut jouons au pendu !!");
		while (true) {
			int n = rnd.nextInt(more_words);
			Continue(n);
			char c = kbd.read("Tu veux rejouer ? (O/n)");
			if (c == 'n' || c == 'N')
				break;
		}
		this.ps.println("Au revoir boss !");
		return;
    }
	
	public void Continue(int n) throws IOException {
		char c;
		hm.newGame(n);
		ps.println("secret: " + hm.guessed());
		while (!hm.won() && !hm.lost()) {
			c = kbd.read("Donne une lettre : ");
			hm.play(c);
			ps.println("Mot a deviner: " + hm.guessed());
		}
		if (hm.won())
			ps.println("Gagné !!");
		else
			ps.println("Perdu !!");
	}

}
