package streams.task5.hm;

import oop.streams.InputStream;

class Keyboard {
	
	InputStream my_is;
	
    Keyboard(oop.streams.InputStream is){
    	my_is = is;
    }
    
    char readChar() {
    	
    	return (char) my_is.read();
    }
    
    String readLine() {
    	StringBuilder lire_ligne = new StringBuilder();
        while (my_is.available() != 0) {
            lire_ligne.append(readChar());
        }
        return lire_ligne.toString();
    }
  }

