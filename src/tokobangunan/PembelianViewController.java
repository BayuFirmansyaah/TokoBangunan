/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokobangunan;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import database.KoneksiDatabase;
import java.sql.Connection;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Kiara Yasmin
 */
public class PembelianViewController implements Initializable {
    @FXML
    private TextField input_date;

    @FXML
    private TextField input_purchase_id;

    @FXML
    private TextField input_vendor_id;

    @FXML
    private TextField input_vendor_ref;
    
     @FXML
    private TextField input_vendor_name;

    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    
//    get detail vendor
    public void getDetailVendor(){
        String id = input_vendor_id.getText();
        int Vendor_ID = Integer.parseInt(id);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
        try{
            java.sql.Connection conn = (Connection)KoneksiDatabase.koneksiDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet rst = stm.executeQuery("select * from vendor where Vendor_ID="+Vendor_ID+"");
            
            if(rst.next()){
                input_vendor_name.setText(""+rst.getString("Vendor_Name"));
            }else{
                input_vendor_name.setText("");
                alert.setTitle("Warning!!");
                alert.setHeaderText(null);
                alert.setContentText("Vendor Belum terdaftar");
                alert.showAndWait();
            }
                 
        }catch(SQLException e){
            System.out.println(e);
        }  
        
    }
    
}
