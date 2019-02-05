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
class Store {
   private ProductCatalog catalog;
   private Register register;
   public Store() {
       catalog = new ProductCatalog();
       register = new Register(catalog);
   }
   public Register getRegister() { return register; }
   public ProductSpec getSpec(String name) { return catalog.getSpec(name); }
   public ArrayList<String> SearchItems(String nameString) {
       ArrayList<String> items = catalog.SearchItems(nameString.toLowerCase());
       return items;
   }
}

