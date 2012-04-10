package rewriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import dataStructure.Point;

public class RewriterThatWorks {
  private Point[] array; 

  public RewriterThatWorks() {
    try {
      points();
    } catch (FileNotFoundException e) {
      System.out.println("File not found: " + e);
    } catch (IOException e) {
      System.out.println(e);
    }
  }

  private void points() throws IOException {
    File in = new File(
        "C:/Users/Admin/MapOfDenmark/src/Union/kdv_node_unload.txt");
    File out = new File("C:/Users/Admin/MapOfDenmark/src/Union/points.txt");
    BufferedReader input = new BufferedReader(new FileReader(in));
    PrintWriter output = new PrintWriter(out);

    System.out.println("Start");
    System.out.println("Reading");

    // Read file
    array = new Point[675902];
    String line = null;
    int index = -1;
    while ((line = input.readLine()) != null){
      
      //debug
      
      if(index > -1){
        String[] split = line.split(",");
        array[index++] = new Point(Integer.parseInt(split[2]), //ID 
            new BigDecimal(Double.parseDouble(split[3])),      //X
            new BigDecimal(Double.parseDouble(split[4])));     //y
      }else{
        index++;
      }
    }
    System.out.println("Read");
    System.out.println("Sorting");
    
    sort(array);
    
    System.out.println("Sorted");
    System.out.println("Writing");
    
    boolean a = true;
    int mid = array.length/2;
    int i = mid, k = mid+1;
    while(a){
      a = false;
      if(i >= 0){
        output.println(array[i].getID() + "," + 
            array[i].getX().toString() + "," + 
            array[i].getY().toString());
        a = true;
        i--;
      }
      if(k < array.length-1){
        output.println(array[k].getID() + "," + 
            array[k].getX().toString() + "," + 
            array[k].getY().toString());
        a = true;
        k++;
      }
    }
    output.close();
    System.out.println("Done");
  }
  
  private void sort(Point[] a){
    Point[] aux = new Point[array.length];
    sort(a, aux, 0, a.length-1);
  }
  
  private void sort(Point[] a, Point[] aux, int lo, int hi){
    if (hi <= lo) return;
    int mid = lo + (hi - lo) / 2;
    sort(a, aux, lo, mid);
    sort(a, aux, mid + 1, hi);
    merge(a, aux, lo, mid, hi);
  }
  
  private void merge(Point[] a, Point[] aux, int lo, int mid, int hi) {
    // copy to aux[]
    for (int k = lo; k <= hi; k++) {
        aux[k] = a[k];
    }

    // merge back to a[]
    int i = lo, j = mid+1;
    for (int k = lo; k <= hi; k++) {
        if      (i > mid)               a[k] = aux[j++];
        else if (j > hi)                a[k] = aux[i++];
        else if (aux[j].getX().doubleValue() < aux[i].getX().doubleValue()) a[k] = aux[j++];
        else                            a[k] = aux[i++];
    }
}
  
  private int binarySearch(Point[] a, double val){
    int lo = 0, hi = a.length-1;
    while(lo <= hi){
      int m = lo + (hi - lo) /2;
      double cmp = val - array[m].getX().doubleValue();
      if      (cmp < 0) hi = m - 1; 
      else if (cmp > 0) lo = m + 1; 
      else return m;
    }
    return lo;
  }

  public static void main(String[] arge) {
    RewriterThatWorks r = new RewriterThatWorks();
  }
}
