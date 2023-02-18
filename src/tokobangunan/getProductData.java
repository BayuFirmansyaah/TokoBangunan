/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokobangunan;

/**
 *
 * @author Bayu Sevima
 */
public class getProductData {
    int Product_ID,Stock;
    String Product_name;
    
    getProductData(int Product_ID, String Product_Name, int Stock){
        this.Product_ID = Product_ID;
        this.Product_name = Product_Name;
        this.Stock = Stock;
    }
    
    public int getProduct_ID(){
        return this.Product_ID;
    }
    
    public void setProduct_ID(int Product_ID){
        this.Product_ID = Product_ID;
    }
    
    public String getProduct_name(){
        return this.Product_name;
    }
    
    public void setProduct_Name(String Product_Name){
        this.Product_name = Product_Name;
    }
    
    public int getStock(){
        return this.Stock;
    }
    
    public void setStock(int Stock){
        this.Stock = Stock;
    }
}
