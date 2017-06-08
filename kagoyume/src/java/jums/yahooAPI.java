/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;

import java.util.ArrayList;

/**
 *
 * @author kurikura
 */
public class yahooAPI {
    private static final String APP_ID="dj0zaiZpPUdtRGR5VnJNNXR2OSZzPWNvbnN1bWVyc2VjcmV0Jng9ZjY-"; //固有のID
    private static final String BASE_URL="http://shopping.yahooapis.jp/ShoppingWebService/V1/json/itemSearch"; //商品検索用
    private static final String CODE_URL="https://shopping.yahooapis.jp/ShoppingWebService/V1/json/itemLookup"; //商品コード検索用
    private String word; //検索ワード
    private int total; //検索件数
    private String headline; //キャッチコピー
    private String name; //商品名
    private String id; //商品id
    private String img; //商品画像
    private int value; //値段
    private ArrayList cart; //商品かご
    private ArrayList list; //検索結果
    private int type; //発送方法
    
    public String getAPP_ID(){
        return APP_ID;
    }
    public String getBASE_URL(){
        return BASE_URL;
    }
    public String getCODE_URL(){
        return CODE_URL;
    }
    
    public String getWord(){
        return this.word;
    }
    public void setWord(String word){
        if(word==null) word="";
        this.word=word;
    }
    
    public int getTotal(){
        return this.total;
    }
    public void setTotal(int total){
        this.total=total;
    }
    
    public String getHeadline(){
        return this.headline;
    }
    public void setHeadline(String headline){
        this.headline=headline;
    }
    
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name=name;
    }
    
    public String getID(){
        return this.id;
    }
    public void setID(String id){
        this.id=id;
    }
    
    public String getImg(){
        return this.img;
    }
    public void setImg(String img){
        this.img=img;
    }
    
    public int getValue(){
        return this.value;
    }
    public void setValue(int value){
        this.value=value;
    }
    
    public ArrayList getCart(){
        return this.cart;
    }
    public void setCart(ArrayList cart){
        this.cart=cart;
    }
    public void setCart(String st,String id){
        if(st.equals("add")){
            cart.add(id);
            System.out.println("add!");
        }
    }
    
    public ArrayList getList(){
        return this.list;
    }
    public void setList(ArrayList list){
        this.list=list;
    }
    
    public void setType(int type){
        this.type=type;
    }
    
    public void buyMapping(UserDataDTO udd){
        udd.setBuyID(this.id);
        udd.setType(this.type);
        udd.setTotal(this.value);
    }
}