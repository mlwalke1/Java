/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.system;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class ProductCatalog {
   private Map<String,ProductSpec> itemList = new HashMap<String,ProductSpec>();  
   public ProductCatalog() {
       try {
           Scanner scanner = new Scanner(new File("items.csv"));
           scanner.useDelimiter(",");
           while(scanner.hasNext()){
               String name = scanner.next();
               String description = scanner.next();
               double price = Double.parseDouble(scanner.next());
               itemList.put(name, new ProductSpec(name,description,price));
           }
           
           scanner.close();
       } catch (FileNotFoundException ex) {
           Logger.getLogger(ProductCatalog.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
   public ProductSpec getSpec(String name) {
      return (ProductSpec) itemList.get(name);
   }
   
   public ArrayList<String> SearchItems(String nameString) {
       ArrayList<String> items = new ArrayList<>();
       for (Map.Entry pair : itemList.entrySet()) {
           String key = (String) pair.getKey();
           int index = key.indexOf(nameString);
           if(index > -1) {
               items.add(getSpec(key).toString());
           }
       }
       return items;
   }

}

class ProductSpec {
   private String name;
   private String description;
   private double price;

   public ProductSpec(String name, String description,double price) {
      this.name = name;
      this.price = price;
      this.description = description;
   }
   public String getName() { return name; }
   public double getPrice() { return price; }
   public String getDescription() { return description; }
   
   @Override
   public String toString() {
       return name + "  " + description + "  " + price;
   }
}

