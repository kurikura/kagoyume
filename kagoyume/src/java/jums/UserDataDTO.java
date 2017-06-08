/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;
import java.sql.Timestamp;

/**
 *
 * @author kurikura
 */
public class UserDataDTO {
    private int userID;
    private String name;
    private String password;
    private String mail;
    private String address;
    private int total;
    private Timestamp newDate;
    private Timestamp buyDate;
    private int deleteFlg;
    private int rs;
    private int type;
    private String buyID;
    
    public int getUserID(){
        return this.userID;
    }
    public void setUserID(int userID){
        this.userID=userID;
    }
    
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name=name;
    }
    
    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password=password;
    }
    
    public String getMail(){
        return this.mail;
    }
    public void setMail(String mail){
        this.mail=mail;
    }
    
    public String getAddress(){
        return this.address;
    }
    public void setAddress(String address){
        this.address=address;
    }
    
    public int getTotal(){
        return this.total;
    }
    public void setTotal(int total){
        this.total+=total;
    }
    
    public Timestamp getNewDate(){
        return this.newDate;
    }
    public void setNewDate(Timestamp newDate){
        this.newDate=newDate;
    }
    
    public Timestamp getBuyDate(){
        return this.buyDate;
    }
    public void setBuyDate(Timestamp buyDate){
        this.buyDate=buyDate;
    }
    
    public int getDeleteFlg(){
        return deleteFlg;
    }
    public void setDeleteFlg(int deleteFlg){
        this.deleteFlg=deleteFlg;
    }
    
    public int getResult(){
        return rs;
    }
    public void setResult(int rs){
        this.rs=rs;
    }
    
    public int getType(){
        return type; 
    }
    public void setType(int type){
        this.type=type;
    }
    
    public String getBuyID(){
        return buyID;
    }
    public void setBuyID(String buyID){
        this.buyID=buyID;
    }
}