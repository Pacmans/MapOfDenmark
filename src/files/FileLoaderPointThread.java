package files;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import controller.Controller;

import dataStructure.Point;

public class FileLoaderPointThread implements Runnable {
  
  private double xMin, xMax, yMin, yMax, Scale;
  private Point[] points;
  private Controller controller = Controller.getInstance();
  
  public FileLoaderPointThread(Point[] points, double xMin, double xMax, double yMin, double yMax, double Scale){
    this.points = points;
    this.xMin = xMin;
    this.xMax = xMax;
    this.yMin = yMin;
    this.yMax = yMax;
    this.Scale = Scale;
  }

  @Override
  public void run() {
 // loads the file "kdv_node_unload"
    InputStream pointFile = getClass().getResourceAsStream(
        "kdv_node_unload.txt");
    BufferedReader pointInput = new BufferedReader(new InputStreamReader(
        pointFile));

    String line = null;
    double x, y;
    int index = -1;
    try {
      if (pointInput.ready()) { // if file is loaded
        while ((line = pointInput.readLine()) != null) {
          if (index >= 0) { // does nothing at the first line
            // creates the point
            String[] info = line.split(",");
            x = (Double.parseDouble(info[3]) / Scale);
            y = (Double.parseDouble(info[4]) / Scale);
            points[index] = new Point(index + 1, x, y);

            // sets max and min coordinates
            if (x < xMin)
              xMin = x;
            if (x > xMax)
              xMax = x;
            if (y < yMin)
              yMin = y;
            if (y > yMax)
              yMax = y;
          }
          index++;
        }
      }
    } catch (IOException e) {
      Controller.catchException(e);
    }
    controller.setxMax(xMax);
    controller.setxMin(xMin);
    controller.setyMax(yMax);
    controller.setyMin(yMin);
  }
}
