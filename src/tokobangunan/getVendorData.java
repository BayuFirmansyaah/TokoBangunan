/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokobangunan;

/**
 *
 * @author Kiara Yasmin
 */
public class getVendorData {
    int Vendor_ID;
    String Vendor_Name,Vendor_Add;
    
    getVendorData(int Vendor_ID, String Vendor_Name, String Vendor_Add){
        this.Vendor_ID = Vendor_ID;
        this.Vendor_Name = Vendor_Name;
        this.Vendor_Add = Vendor_Add;
    }
    
    public int getVendor_ID(){
       return this.Vendor_ID;
    }
    
    public void setVendor_ID(int Vendor_ID){
        this.Vendor_ID = Vendor_ID;
    }
    
    public String getVendor_Name()
    {
        return this.Vendor_Name;
    }
    
    public void setVendor_Name(String Vendor_Name){
        this.Vendor_Name = Vendor_Name;
    }
    
    public String getVendor_Add(){
        return this.Vendor_Add;
    }
    
    public void setVendor_Add(String Vendor_Add){
        this.Vendor_Add = Vendor_Add;
    }
}
