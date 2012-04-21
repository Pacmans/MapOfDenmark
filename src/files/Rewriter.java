package files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Rewriter {

  // TODO Should this class give connections ID and thereby make is possible to
  // keep connections in a single array?
  // Multiple arrays are only a problem when a class wants all connections.

  /**
   * Rewriter read input file and separates to multiple output files with one
   * Roadtype per file. This makes multithreaded reading of input possible. This
   * class is not responsible for checking whether rewritten files already
   * exists.
   */
  public Rewriter() {
    //Ready input and outputs
    InputStream b = getClass().getResourceAsStream("kdv_unload.txt");
    BufferedReader input = new BufferedReader(new InputStreamReader(b));
    File road_1 = new File("./src/Files/road_1.txt");
    File road_2 = new File("./src/Files/road_2.txt");
    File road_3 = new File("./src/Files/road_3.txt");
    File road_4 = new File("./src/Files/road_4.txt");
    File road_5 = new File("./src/Files/road_5.txt");
    File road_6 = new File("./src/Files/road_6.txt");
    File road_7 = new File("./src/Files/road_7.txt");
    try {
      PrintWriter r1 = new PrintWriter(road_1);
      PrintWriter r2 = new PrintWriter(road_2);
      PrintWriter r3 = new PrintWriter(road_3);
      PrintWriter r4 = new PrintWriter(road_4);
      PrintWriter r5 = new PrintWriter(road_5);
      PrintWriter r6 = new PrintWriter(road_6);
      PrintWriter r7 = new PrintWriter(road_7);
      System.out.println("Rewirter: Inputs and outputs ready");
      
      //Read and write
      System.out.println("Writting files...");
      String line = null;
      int index = -1;
      while((line = input.readLine()) != null){
        if(index == -1) index = 0; //We don't want to read the first line
        else{
          String split[] = line.split(",");
          switch(Integer.parseInt(split[5])){
          case 1: case 21: case 41:
            r1.println(line);
            break;
          case 2: case 22: case 23: case 31: case 42:
            r2.println(line);
            break;
          case 3: case 32: case 43:
            r3.println(line);
            break;
          case 4: case 24: case 33: case 44:
            r4.println(line);
            break;
          case 5: case 6: case 25: case 34: case 45: case 80:
            r5.println(line);
            break;
          case 10: case 11: case 26: case 35: case 46:
            r6.println(line);
            break;
          case 8: case 28: case 48: case 99:
            r7.println(line);
            break;
          default:
            r4.println(line);
            break;
          }
        }
      }
      r1.close();
      r2.close();
      r3.close();
      r4.close();
      r5.close();
      r6.close();
      r7.close();
      System.out.println("Rewriter: Files written");
    } catch (FileNotFoundException e) {
      // TODO Throw exception to Controller
    } catch (IOException e) {
      // TODO Throw exception to Controller
    }
  }

}
