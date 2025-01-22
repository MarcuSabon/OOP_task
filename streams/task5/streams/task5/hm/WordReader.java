package streams.task5.hm;

import oop.streams.InputStream;

public class WordReader {
    private InputStream is;

    public WordReader(InputStream is) {
        this.is = is;
    }

    public String[] parse(){
        CharReader charReader = new CharReader(is);
        String line = charReader.readLine();
        int nb_words = Integer.parseInt(line);
        String[] words = new String[nb_words];
        for (int i = 0; i < nb_words; i++) {
            String word = charReader.readLine();
            words[i] = word;
        }
        return words;
    }
}
