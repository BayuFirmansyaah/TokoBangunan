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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;

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
    
     @FXML
    private TextField input_vendor_addres;

    @FXML
    private TextField input_vendor_id;

    @FXML
    private TextField input_vendor_name;
    
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

    
//    insert data
    public void insertData(){
        String id = input_vendor_id.getText();
        String Vendor_Name = input_vendor_name.getText();
        String Vendor_Address = input_vendor_addres.getText();
        int Vendor_Id = Integer.parseInt(id);
        
        String sql = "INSERT INTO vendor (Vendor_ID, Vendor_Name, Vendor_Add) VALUES("+Vendor_Id+",'"+Vendor_Name+"','"+ Vendor_Address  +"')";
        
        Alert alert = new Alert(AlertType.INFORMATION);
        
        try{
            java.sql.Connection conn = (Connection)KoneksiDatabase.koneksiDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();

            alert.setTitle("Berhasil!!");
            alert.setHeaderText(null);
            alert.setContentText("Berhasil Menambahkan Data Vendor");
            alert.showAndWait();
            
            this.Clean();
            
            TableViews.getItems().clear();
            this.getData();

        }catch(SQLException e){
            alert.setTitle("Warning!!");
            alert.setHeaderText(null);
            alert.setContentText(""+e);
            alert.showAndWait();
        }
    }
    
    public void Clean(){
        input_vendor_id.setText("");
        input_vendor_name.setText("");
        input_vendor_addres.setText("");
    }
    
     @FXML
    private Button closeButton;
     
    public void keluar(){
       Stage stage = (Stage) closeButton.getScene().getWindow();
       stage.close();
    }
}
