package streams.task5.hm;

import oop.streams.InputStream;
import oop.streams.OutputStream;
import oop.streams.wjs.WrappedJavaInputStream;
import oop.streams.wjs.WrappedJavaOutputStream;

public class Task5Main {
  static OutputStream out;
  static InputStream in;
  static String words[];
  static boolean scripting;
  public static void main(String[] args)
      throws Exception {
    out = new WrappedJavaOutputStream(System.out);
    in = new WrappedJavaInputStream(System.in);
    parse(args);
    if (scripting) {
      Script script = new Script(words, in, out);
      script.play();
    } else {
      Game game = new Game(words, in, out);
      game.play();
    }
  } 
  /*
   * Parse the given arguments:
   *    -words <words-file> [-script <script-file>]
   *
   * Creates the array of secret words, from the given file,
   * using an instance of the class WordReader to read and
   * parse the file.
   *
   * If this session is scripted, the input stream will be
   * changed to a stream wrapping the given script file. 
  */
  public static void parse(String[] args) throws Exception {
}
}