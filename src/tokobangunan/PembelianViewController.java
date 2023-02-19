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
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
     
     @FXML
    private TextField input_list_id1;

    @FXML
    private TextField input_list_id2;

    @FXML
    private TextField input_list_id3;

    @FXML
    private TextField input_list_name1;

    @FXML
    private TextField input_list_name2;

    @FXML
    private TextField input_list_name3;

    @FXML
    private TextField input_list_price1;

    @FXML
    private TextField input_list_price2;

    @FXML
    private TextField input_list_price3;

    @FXML
    private TextField input_list_qty1;

    @FXML
    private TextField input_list_qty2;

    @FXML
    private TextField input_list_qty3;

    @FXML
    private TextField input_list_stotal1;

    @FXML
    private TextField input_list_stotal2;

    @FXML
    private TextField input_list_stotal3;
    
     @FXML
    private TextField grand_total;



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
    
//    insert data into table purchase and purchase detail
    public void insertData(){
        String purchase_id = input_purchase_id.getText();
        int Purchase_ID = Integer.parseInt(purchase_id);
        String Purchase_Date = input_date.getText();
        String vendor_id = input_vendor_id.getText();
        int Vendor_ID = Integer.parseInt(vendor_id);
        String Vendor_RefNo = input_vendor_ref.getText();
        
        int total = 0;
        
        String sql = "INSERT INTO purchase (Purchase_ID, Purchase_Date, Vendor_ID, Vendor_RefNo) VALUES("+Purchase_ID+",'"+Purchase_Date+"',"+ Vendor_ID  +",'"+ Vendor_RefNo +"')";
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
        try{
            java.sql.Connection conn = (Connection)KoneksiDatabase.koneksiDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            
//            insert data product ke 1
            String id1 = input_list_id1.getText();
            String name1 = input_list_name1.getText();
            String price1 = input_list_price1.getText();
            String qty1 = input_list_qty1.getText();
            String stotal1 = input_list_stotal1.getText();
            
            if(!id1.isEmpty() && !name1.isEmpty() && !price1.isEmpty() && !qty1.isEmpty() && !stotal1.isEmpty()){
                insertIntoPurchaseDetail(Purchase_ID, Integer.parseInt(id1), Integer.parseInt(qty1), Integer.parseInt(price1));
                total += Integer.parseInt(stotal1);
            }
            
//            insert data product ke 2
            String id2 = input_list_id2.getText();
            String name2 = input_list_name2.getText();
            String price2 = input_list_price2.getText();
            String qty2 = input_list_qty2.getText();
            String stotal2 = input_list_stotal2.getText();
            
            if(!id2.isEmpty() && !name2.isEmpty() && !price2.isEmpty() && !qty2.isEmpty() && !stotal2.isEmpty()){
                insertIntoPurchaseDetail(Purchase_ID, Integer.parseInt(id2), Integer.parseInt(qty2), Integer.parseInt(price2));
                total += Integer.parseInt(stotal2);
            }
            
//             insert data product ke 3
            String id3 = input_list_id3.getText();
            String name3 = input_list_name3.getText();
            String price3 = input_list_price3.getText();
            String qty3 = input_list_qty3.getText();
            String stotal3 = input_list_stotal3.getText();
            
            if(!id3.isEmpty() && !name3.isEmpty() && !price3.isEmpty() && !qty3.isEmpty() && !stotal3.isEmpty()){
                insertIntoPurchaseDetail(Purchase_ID, Integer.parseInt(id3), Integer.parseInt(qty3), Integer.parseInt(price3));
                total += Integer.parseInt(stotal3);
            }
            
            grand_total.setText(""+total);
            
        }catch(SQLException e){
            alert.setTitle("Warning!!");
            alert.setHeaderText(null);
            alert.setContentText(""+e);
            alert.showAndWait();
        }
    }
    
//    insert into table purchase detail
    public void insertIntoPurchaseDetail(int Purchase_ID, int Product_ID, int Qty, int Price){
        String sql = "INSERT INTO purchase_detail (Purchase_ID, Product_ID, Quantity, Price) VALUES("+Purchase_ID+","+Product_ID+","+ Qty  +","+ Price +")";
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
        try{
            java.sql.Connection conn = (Connection)KoneksiDatabase.koneksiDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            
            
//            update stock product
            updateProduct(Product_ID, Qty);
        }catch(SQLException e){
            alert.setTitle("Warning!!");
            alert.setHeaderText(null);
            alert.setContentText(""+e);
            alert.showAndWait();
        }
    }
    
//   update stock product
    public void updateProduct(int Product_ID, int Qty){
        int stock = getStock(Product_ID);
            stock = stock - Qty;
            
        String sql = "UPDATE product SET Stock="+ stock +" WHERE product_id ="+ Product_ID;
        
        try{
            java.sql.Connection conn = (Connection)KoneksiDatabase.koneksiDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            
        }catch(SQLException e){
            System.out.println(e);
        } 
    }
    
    public int getStock(int Product_ID){
        int stock = 0;
         try{
            java.sql.Connection conn = (Connection)KoneksiDatabase.koneksiDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet rst = stm.executeQuery("select * from product where Product_ID="+Product_ID+"");
            
            if(rst.next()){
                stock = rst.getInt("Stock");
            }
                 
        }catch(SQLException e){
            System.out.println(e);
        }
         
         return stock;
    }
    
   
    
    
}
