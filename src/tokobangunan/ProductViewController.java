/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokobangunan;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import database.KoneksiDatabase;
import java.sql.Connection;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Kiara Yasmin
 */
public class ProductViewController implements Initializable {
    ObservableList<getProductData> getProductDataObservableList = FXCollections.observableArrayList();
    
    @FXML
    private TableView<getProductData> TableViews;
    
    @FXML
    private TableColumn<getProductData, Integer> c_product_id;

    @FXML
    private TableColumn<getProductData, String> c_product_name;

    @FXML
    private TableColumn<getProductData, Integer> c_product_stock;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.getData();
    }  
    
    //    get data
    public void getData(){
           try{
            
            java.sql.Connection conn = (Connection)KoneksiDatabase.koneksiDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet rst = stm.executeQuery("select * from product");
            
            while(rst.next()){
                int Product_ID = rst.getInt("Product_ID");
                String Product_Name = rst.getString("Product_name");
                int Stock = rst.getInt("Stock");
                getProductDataObservableList.add(new getProductData(Product_ID, Product_Name, Stock));
            }
            
             c_product_id.setCellValueFactory(new PropertyValueFactory<>("Product_ID"));
             c_product_name.setCellValueFactory(new PropertyValueFactory<>("Product_name"));
             c_product_stock.setCellValueFactory(new PropertyValueFactory<>("Stock"));
             TableViews.setItems(getProductDataObservableList);
               
        }catch(SQLException e){
            System.out.println(e);
        }  
    }
    
}
