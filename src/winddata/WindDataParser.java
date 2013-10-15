/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package winddata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Johnny
 */
public class WindDataParser {
    private File file;
    
    private String location;
    private String date;
    
    private HashMap<Double, Double> cumulativeData;
    private HashMap<Double, ArrayList<Double>> roseData;
    
    public WindDataParser(File file) {
        this.file = file;
        cumulativeData = new HashMap<>();
        roseData = new HashMap<>();
    }
    
    protected void parse() throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = "";
        for (int i = 0; i <= 2; i++) line = reader.readLine();
        
        extractLocationAndDate(line);
        
        for (int i = 2; i < 10; i++) line = reader.readLine();
        
        for (int i = 11; i <= 38; i++) {
            line = reader.readLine();
            extractCumulative(line);
            extractRoseData(line);
        }
        
        
    }
    
    protected void extractLocationAndDate(String line) {
        String[] locationAndDate = line.split("Year");
        location = locationAndDate[0].trim();
        date = locationAndDate[1].trim();
        //System.out.println("date: " + date + "Loc:  " + location);
    }
    
    protected void extractCumulative(String line) {
      String dist[] = line.trim().split(" ");
      
      double fromWindSpeed = Double.parseDouble(dist[0]);
      double cumWind;
      try {
          cumWind = Double.parseDouble(dist[dist.length - 1]);
      }
      catch (NumberFormatException ex) {
          cumWind = 0;
      }
      cumulativeData.put(fromWindSpeed, cumWind);
      
    }
    
    protected double getCumulativeAtWindSpeed(double windSpeed) {
        return cumulativeData.get(Math.floor(windSpeed));
    }
    
    protected void extractRoseData(String line) {
        String dist[] = line.trim().split(" ");
        double fromWindSpeed = Double.parseDouble(dist[0]);

        double rose[] = new double[12];
        
        ArrayList<Double> distData = new ArrayList<>();
       
        
        for (int i = 5; i < dist.length; i++) {
            if (!dist[i].isEmpty()) {
                try {
                    double d = Double.parseDouble(dist[i]);
                    distData.add(d);
                }
                catch (NumberFormatException e) {
                    distData.add(0.0);
                }
            } 
        }
        
        //for(int i = 0; i < distData.size(); i++) System.out.println(distData.get(i));
        
        roseData.put(fromWindSpeed, new ArrayList<>(distData.subList(1, distData.size() - 2)));
    }
    
    /*protected double getPercentageAtWindSpeedAndDirection(double windSpeed, double direction) {
        ArrayList<Double> dist = roseData.get(Math.floor(windSpeed));
        
    }*/
}
