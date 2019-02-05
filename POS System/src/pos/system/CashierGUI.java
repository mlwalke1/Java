/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.system;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.geometry.Insets; 
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author user
 */
public class CashierGUI extends Application{
    Store store = new Store();
    
    @Override
    public void start(Stage primaryStage) {    
        ImageView imgStore = new ImageView("pos/system/store.png");
        
        Button btnNew = new Button();
        btnNew.setText("New Sale");
        
        Button btnTotal = new Button();
        btnTotal.setText("Total Sales");

        btnNew.setOnAction((ActionEvent event) -> {
            newSaleGUI();
        });
        
        btnTotal.setOnAction((ActionEvent event) -> {
            //show total sales window
            totalSalesGUI();
        });
        VBox vbox = new VBox();
        GridPane grid = new GridPane();
        grid.add(btnNew,0,0);
        grid.add(btnTotal,1,0);
        grid.setHgap(15);
        grid.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(15, 0, 0, 15));
        
        //retrieving the observable list of the HBox 
        ObservableList list = vbox.getChildren();

        //Adding all the nodes to the observable list (HBox) 
        list.addAll(grid,imgStore);
        
        Scene scene = new Scene(vbox, 425, 500);
        
        primaryStage.setTitle("General Store");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void newSaleGUI() {
        Stage saleStage = new Stage();
        
        Register register = store.getRegister();
        register.makeNewSale();
        
        // search elements
        TextField searchField = new TextField();
        Button btnSearch = new Button("Search Item");
        
        // add to cart elements
        TextField fieldAddName = new TextField();
        TextField fieldAddQty = new TextField();
        Button btnAdd = new Button("Add to Cart");
        
        // total elements
        TextField totalField = new TextField();
        TextField cashField = new TextField();
        totalField.setEditable(false);
        Button btnEnd = new Button("End Sale");
        
        TextArea shoppingCart = new TextArea();
        shoppingCart.setMaxWidth(550);
        shoppingCart.setEditable(false);
        
        btnSearch.setOnAction((ActionEvent event) -> {
            String toSearch = searchField.getText();
            if(!toSearch.isEmpty()){
                ArrayList<String> matches = store.SearchItems(toSearch);
                Alert alert = new Alert(AlertType.INFORMATION);
                String contentText = "";
                if(!matches.isEmpty()) {
                  for(int i = 0; i < matches.size(); i++) {
                      contentText += matches.get(i) + "\n";
                  }
                } else {contentText = "No Matches! :(";}
                alert.setTitle("Search Results");
                alert.setHeaderText("Matching Items");
                alert.setContentText(contentText);
                alert.showAndWait();
            }
        });
        
        btnAdd.setOnAction((ActionEvent event) -> {
            String itemName = fieldAddName.getText();
            int itemQty = 0;
            try {
                itemQty = Integer.parseInt(fieldAddQty.getText());
            }
            catch(NumberFormatException | NullPointerException nfe) {
                return;
            }
            boolean success = register.enterItem(itemName, itemQty);
            if(success) {
              String currentText = shoppingCart.getText();
              shoppingCart.setText(currentText + register.getLineItem() + "\n" );
              totalField.setText(String.format("%.2f",register.getSaleTotal()));
            } else {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setContentText("Item Doesn't Exist! (Hint: Use Search)");
                alert.showAndWait();
                fieldAddName.clear();
            }
        });
        
        btnEnd.setOnAction((ActionEvent event) -> {
            double cashTendered = 0.00;
            if(!cashField.getText().isEmpty()) {
                cashTendered =  Double.parseDouble(cashField.getText());
            }
            if(register.getSaleTotal() <= cashTendered) {
                register.makePayment(cashTendered);
                receiptGUI();
                saleStage.close();
            } else {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setContentText("Insufficient Funds!");
                alert.showAndWait();
                cashField.clear();
            }
        });
        VBox vbox = new VBox();
        GridPane grid1 = new GridPane();
        GridPane grid2 = new GridPane();
        GridPane grid3 = new GridPane();
        
        grid1.add(searchField,0,0);
        grid1.add(btnSearch,1,0);
        grid2.add(new Label("Name:"),0,0);
        grid2.add(fieldAddName,1,0);
        grid2.add(new Label("Qty:"),0,1);
        grid2.add(fieldAddQty,1,1);
        grid2.add(btnAdd,2,1);
        grid3.add(new Label("Total:"),0,0);
        grid3.add(totalField,1,0);
        grid3.add(new Label("Cash Tendered:"),0,1);
        grid3.add(cashField,1,1);
        grid3.add(btnEnd,0,2);
        
        grid1.setHgap(10);
        grid2.setHgap(10);
        grid3.setHgap(10);
        
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(grid1,grid2,shoppingCart,grid3);
        
        Scene scene = new Scene(vbox,600,700);
        
        saleStage.setTitle("New Sale");
        saleStage.setScene(scene);
        saleStage.show();
    }
    
    public void receiptGUI() {
        Register register = store.getRegister();
        Stage receiptStage = new Stage();
        
        TextArea lineItems = new TextArea();
        lineItems.setEditable(false);
        lineItems.setMaxWidth(400);
        
        ArrayList<SalesLineItem> listItems = register.getAllItems();
        for(int i = 0; i < listItems.size(); i++) {
            String currentText = lineItems.getText();
            lineItems.setText(currentText + listItems.get(i).toString() + "\n");
        }
        
        String total = String.format("%.2f",register.getSaleTotal());
        String tendered = String.format("%.2f",register.getCashTendered());
        String change = String.format("%.2f",register.getChange());
        
        GridPane grid = new GridPane();
        grid.add(new Label("Total: "),0,0);
        grid.add(new Label("Tendered: "),0,1);
        grid.add(new Label("Change: "),0,2);
        
        grid.add(new Label(total),1,0);
        grid.add(new Label(tendered),1,1);
        grid.add(new Label(change),1,2);
        
        Button btnOK = new Button("OK");
        btnOK.setOnAction((ActionEvent event) -> {
            receiptStage.close();
        });
        
        Scene scene = new Scene(new Group());
        receiptStage.setTitle("Receipt");
        receiptStage.setWidth(425);
        receiptStage.setHeight(600);
 
        final Label label = new Label("Items");
 
 
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label,lineItems,grid,btnOK);
 
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
 
        receiptStage.setScene(scene);
        receiptStage.show();
    }
    
    public void totalSalesGUI() {
        Register register = store.getRegister();
        ArrayList<Sale> listSales = register.getAllSales();
        if(!listSales.isEmpty()) {
            Stage salesStage = new Stage();

            TextArea totalSales = new TextArea();
            totalSales.setEditable(false);
            totalSales.setMaxWidth(400);


            for(int i = 0; i < listSales.size(); i++) {
                String currentText = totalSales.getText();
                totalSales.setText(currentText + listSales.get(i).toString() + "\n\n");
            }

            String total = String.format("%.2f",register.getSaleTotal());
            String tendered = String.format("%.2f",register.getCashTendered());
            String change = String.format("%.2f",register.getChange());

            Button btnOK = new Button("OK");
            btnOK.setOnAction((ActionEvent event) -> {
                salesStage.close();
            });

            Scene scene = new Scene(new Group());
            salesStage.setTitle("Total Sales");
            salesStage.setWidth(425);
            salesStage.setHeight(600);

            final Label label = new Label("Sales");


            final VBox vbox = new VBox();
            vbox.setSpacing(5);
            vbox.setPadding(new Insets(10, 0, 0, 10));
            vbox.getChildren().addAll(label,totalSales,btnOK);

            ((Group) scene.getRoot()).getChildren().addAll(vbox);

            salesStage.setScene(scene);
            salesStage.show();
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("No Sales");
            alert.setContentText("No Sales! (Hint: New Sale)");
            alert.showAndWait();
        }
    }
    
    public void addPressed() {
        
    }
    
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
