/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.system;

import java.util.ArrayList;

/**
 *
 * @author user
 */
class Register {
   private int currentSaleIndex;
   private Sale currentSale;
   private SalesLineItem currentLine; 
   private ProductSpec currentSpec;
   private ArrayList listOfSales;
   private ProductCatalog catalog;
   private Payment currentPayment;

    Register(ProductCatalog catalog) {
        currentSaleIndex = -1;
        listOfSales = new ArrayList();
        this.catalog = catalog;
    }
   public void makeNewSale() {
      currentSaleIndex ++;
      listOfSales.add(new Sale());
   }
   public boolean enterItem(String name, int quantity) {
      currentSale = (Sale)listOfSales.get(currentSaleIndex);
      currentSpec = catalog.getSpec(name);
      if(currentSpec != null) {
        currentLine = currentSale.makeLineItem(currentSpec, quantity);
        return true;
      } 
      return false;
   }
   public void makePayment(double cash) {
       currentPayment = currentSale.makePayment(cash);
   }
   
   public String getSpecName() {return currentSpec.getName();}
   public String getSpecDesc() {return currentSpec.getDescription();}
   public double getSpecPrice() {return currentSpec.getPrice();}
   public double getSaleTotal() { return currentSale.getTotal();}
   public String getLineItem() {return currentLine.toString();}
   public double getCashTendered(){ return currentPayment.getAmount(); }
   public double getChange() {return currentSale.getBalance();}
   public ArrayList<SalesLineItem> getAllItems() {return currentSale.getLineItems();}
   public ArrayList<Sale> getAllSales() {return listOfSales;}
   
}

