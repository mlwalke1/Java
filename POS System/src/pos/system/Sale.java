/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 *
 * @author user
 */
class Sale {
   private ArrayList<SalesLineItem> saleLineItems;
   //private boolean isComplete = false;
   private Date date;
   int totalItems;
   private Payment payment;

   public Sale() {
    saleLineItems = new ArrayList();
    date = new Date();
    totalItems = 0;
   }
   
   public SalesLineItem makeLineItem(ProductSpec spec, int quantity) {
      totalItems += quantity;
      SalesLineItem line = new SalesLineItem(spec,quantity);
      saleLineItems.add(line);
      return line;
   }
   
   //public void becomeComplete() { isComplete = true; }

   public double getTotal() {
      double total = 0;
      Iterator it = saleLineItems.iterator();
      while (it.hasNext()) {
         SalesLineItem sli = (SalesLineItem) it.next();
         total += sli.getSubtotal();
      }
      return total;
   }

   public Payment makePayment(double cashTendered) {
      payment = new Payment(cashTendered);
      return payment;
   }

   public double getBalance() {
      return payment.getAmount() - getTotal();
   }
   
   public Date getDate() {return date;}
   
   public int getTotalItems() {return totalItems;}
   
   public ArrayList<SalesLineItem> getLineItems() {return saleLineItems;}
   
   @Override
   public String toString() {
       return date.toString() + "\n   Items: " + totalItems + "  Total: " 
               + String.format("%.2f",getTotal()) + "  Tendered: " 
               + String.format("%.2f",payment.getAmount()) + "  Change: " 
               + String.format("%.2f",getBalance());
   }
   
}

class SalesLineItem {
   private ProductSpec spec;
   private int quant;

   public SalesLineItem(ProductSpec s, int q) {
      spec = s;
      quant = q;
   }

   public double getSubtotal() { return spec.getPrice() * quant; }
   
   @Override
   public String toString() {
       return spec.getName() + "     " + spec.getDescription() + "     " + 
               spec.getPrice() + "     " + quant + "     " 
               + String.format("%.2f",getSubtotal());
   }
}

class Payment {
   private double amount;
   public Payment(double cashTendered) { amount = cashTendered; }
   public double getAmount() { return amount; }
}


