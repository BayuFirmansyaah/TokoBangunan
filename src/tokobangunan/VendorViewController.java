/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokobangunan;

import database.KoneksiDatabase;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Kiara Yasmin
 */
public class VendorViewController implements Initializable {
    
    ObservableList<getVendorData> getVendorDataObservableList = FXCollections.observableArrayList();
    
     @FXML
    private TableView<getVendorData> TableViews;
    
     @FXML
    private TableColumn<getVendorData, Integer> c_vendor_id;

    @FXML
    private TableColumn<getVendorData, String> c_vendor_name;

    @FXML
    private TableColumn<getVendorData, String> c_vendor_add;
    
      @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.getData();
    }    
    
    //    get data
    public void getData(){
           try{
            
            java.sql.Connection conn = (Connection)KoneksiDatabase.koneksiDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet rst = stm.executeQuery("select * from vendor");
            
            while(rst.next()){
                int Vendor_ID = rst.getInt("Vendor_ID");
                String Vendor_Name = rst.getString("Vendor_Name");
                String Vendor_Add = rst.getString("Vendor_Add");
                getVendorDataObservableList.add(new getVendorData(Vendor_ID, Vendor_Name, Vendor_Add));
            }
            
             c_vendor_id.setCellValueFactory(new PropertyValueFactory<>("Vendor_ID"));
             c_vendor_name.setCellValueFactory(new PropertyValueFactory<>("Vendor_Name"));
             c_vendor_add.setCellValueFactory(new PropertyValueFactory<>("Vendor_Add"));
             TableViews.setItems(getVendorDataObservableList);
               
        }catch(SQLException e){
            System.out.println(e);
        }  
    }

}
