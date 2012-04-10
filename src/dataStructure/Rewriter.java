package dataStructure;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Rewriter {
  private double[][] array;
  
  public Rewriter(){
    try {
      points();
    } catch (FileNotFoundException e) {
      System.out.println("File not found: " + e);
    } catch (IOException e){
      System.out.println(e);
    }
  }
  
  private void points() throws IOException{
    File in = new File("C:/Users/Admin/MapOfDenmark/src/Union/kdv_node_unload.txt");
    File out = new File("C:/Users/Admin/MapOfDenmark/src/Union/points.txt");
    BufferedReader input =  new BufferedReader(new FileReader(in));
    PrintWriter output = new PrintWriter(out);
    
    System.out.println("Start");
    
    //Read file
    array = new double[675903][3];
    String line = null;
    int index = 0;
    while ((line = input.readLine()) != null){
      
      //debug
      
      if(index > 0){
        String[] split = line.split(",");
        
        array[index][0] = Double.parseDouble(split[2]); //ID
        array[index][1] = Double.parseDouble(split[3]); //X
        array[index++][2] = Double.parseDouble(split[4]); //Y
      }else{
        index++;
      }
    }
    
    System.out.println("Read");
    
    //2D mergesort;
    sort(array);
    
    System.out.println("Sorted");
    
    //find middle
    double val = array[array.length-1][0] - array[0][0];
    int med = binarySearch(array, val);
    
    //write
    boolean a = true, b = true;
    int i = med, k = med+1;
    while(a || b){
      a = false; b = false;
      if(i >= 0){
        output.println(array[i][0] + "," + array[i][1] + "," + array[i][2]);
        a = true;
        i--;
      }
      if(k < index-1){
        output.println(array[k][0] + "," + array[k][1] + "," + array[k][2]);
        a = true;
        k++;
      }
    }
    output.close();
    System.out.println("Written");
  }
  
  private int binarySearch(double[][] a, double val){
    int lo = 0, hi = a.length-1;
    while(lo <= hi){
      int m = lo + (hi - lo) /2;
      double cmp = val - array[m][1];
      if      (cmp < 0) hi = m - 1; 
      else if (cmp > 0) lo = m + 1; 
      else return m;
    }
    return lo;
  }
  
  private void sort(double[][] a){
    double[][] aux = new double[a.length][3];
    sort(a, aux, 0 , a.length-1);
  }
  
  private void sort(double[][] a, double[][] aux, int lo, int hi){
    if (hi <= lo) return;
    int mid = lo + (hi - lo) / 2;
    sort(a, aux, lo, mid);
    sort(a, aux, mid + 1, hi);
    merge(a, aux, lo, mid, hi);
  }
  
  public  void merge(double[][] a, double[][] aux, int lo, int mid, int hi) {
    // copy to aux[]
    for (int k = lo; k <= hi; k++) {
        aux[k][0] = a[k][0];
        aux[k][1] = a[k][1];
        aux[k][2] = a[k][2];
    }

    // merge back to a[]
    int i = lo, j = mid+1;
    for (int k = lo; k <= hi; k++) {
        if      (i > mid)               a[k] = aux[j++];
        else if (j > hi)                a[k] = aux[i++];
        else if (aux[j][1] < aux[i][1]) a[k] = aux[j++];
        else                            a[k] = aux[i++];
    }
}
  
  private double[][] resize(double[][] ar, int newsize){
    double[][] tmp = new double[newsize][2];
    for(int i = 0; i < ar.length; i++){
      tmp[i][0] = ar[i][0];
      tmp[i][1] = ar[i][1];
    }
    return tmp;
  }

  public static void main(String[] args) {
    Rewriter r = new Rewriter();
  }

}
