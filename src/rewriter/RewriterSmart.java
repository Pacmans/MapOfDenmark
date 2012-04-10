package rewriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import dataStructure.Point;

public class RewriterSmart {
  private String[][] array = new String[812302][5], 
      points = new String[675903][2];
  
  public RewriterSmart(){
    try{
      load();
    } catch (FileNotFoundException e) {
      System.out.println("File not found: " + e);
    } catch (IOException e) {
      System.out.println(e);
    }
  }
  
  private void load() throws IOException{
    File in1 = new File("C:/Users/Admin/MapOfDenmark/src/Files/kdv_node_unload.txt");
    BufferedReader ps = new BufferedReader(new FileReader(in1));
    File in2 = new File("C:/Users/Admin/MapOfDenmark/src/Files/kdv_unload.txt");
    BufferedReader cs = new BufferedReader(new FileReader(in2));
    File out = new File("C:/Users/Admin/MapOfDenmark/src/Files/smart_data.txt");
    PrintWriter output = new PrintWriter(out);

    System.out.println("Start");
    System.out.println("Reading points");
    
    String line = null;
    int index = 0;
    while ((line = ps.readLine()) != null){
      if(index > 0){
        String[] split = line.split(",");
        points[index][0] = split[3];   //X
        points[index++][1] = split[4]; //Y
      }else{
        index++;
      }
    }
    
    System.out.println("Points read");
    System.out.println("Reading roads");
    
    index = 0;
    while ((line = cs.readLine()) != null){
      if(index > 0){
        String[] split = line.split(",");
        
        array[index][0] = points[Integer.parseInt(split[0])][0]; //X1
        array[index][1] = points[Integer.parseInt(split[0])][1]; //Y1
        array[index][2] = points[Integer.parseInt(split[1])][0]; //X2
        array[index][3] = points[Integer.parseInt(split[1])][1]; //Y2
        array[index++][4] = split[5]; //Type
      }else{
        index++;
      }
    }

    System.out.println("Roads read");
    System.out.println("Sorting roads");
    
//    sort(array);
    
    System.out.println("Roads sorted");
    System.out.println("Writing file");
    
    boolean a = true;
    int mid = array.length/2;
    int i = mid, k = mid+1;
    while(a){
      a = false;
      if(i >= 0){
        output.println(array[i][0] + "," + 
            array[i][1] + "," +
            array[i][2] + "," + 
            array[i][3] + "," + 
            array[i][4]);
        a = true;
        i--;
      }
      if(k < array.length-1){
        output.println(array[k][0] + "," + 
            array[k][1] + "," +
            array[k][2] + "," +
            array[k][3] + "," +
            array[k][4]);
        a = true;
        k++;
      }
    }
    output.close();
    System.out.println("Done");
  }
  
  //Multi dimensional merge-sort
  private void sort(String[][] a){
    String[][] aux = new String[a.length][a[0].length];
    sort(a, aux, 0 , a.length-1, 2);
    sort(a, aux, 0 , a.length-1, 0);
  }
  
  private void sort(String[][] a, String[][] aux, int lo, int hi, int d){
    if (hi <= lo) return;
    int mid = lo + (hi - lo) / 2;
    sort(a, aux, lo, mid, d);
    sort(a, aux, mid + 1, hi, d);
    merge(a, aux, lo, mid, hi, d);
  }
  
  private void merge(String[][] a, String[][] aux, int lo, int mid, int hi, int d) {
    // copy to aux[]
    for (int k = lo; k <= hi; k++) {
      for(int l = 0; l < aux[0].length; l++)
        aux[k][l] = a[k][l];
    }

    // merge back to a[]
    int i = lo, j = mid+1;
    for (int k = lo; k <= hi; k++) {
        if      (i > mid)               a[k] = aux[j++];
        else if (j > hi)                a[k] = aux[i++];
        else if (Double.parseDouble(aux[j][d]) < Double.parseDouble(aux[i][d])) a[k] = aux[j++];
        else                            a[k] = aux[i++];
    }
}
  
  public static void main(String[] args){
    RewriterSmart r = new RewriterSmart();
  }
}
