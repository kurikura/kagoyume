/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;

import java.util.ArrayList;
import javax.servlet.http.HttpSession;

/**
 *
 * @author kurikura
 */
public class UserData {
    private String name;
    private String mail;
    private String password;
    private String address;
    private String url;
    
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name=name;
    }
    
    public String getMail(){
        return this.mail;
    }
    public void setMail(String mail){
        this.mail=mail;
    }
    
    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password=password;
    }
    
    public String getAddress(){
        return this.address;
    }
    public void setAddress(String address){
        this.address=address;
    }
    
    public String getUrl(){
        return this.url;
    }
    public void setUrl(String url){
        this.url=url;
        System.out.println(this.url);
    }
    
    public void updateMapping(UserDataDTO udd){
        setName(udd.getName());
        setMail(udd.getMail());
        setPassword(udd.getPassword());
        setAddress(udd.getAddress());
    }
    
    public void loginMapping(UserDataDTO udd){
        udd.setMail(this.mail);
        udd.setPassword(this.password);
    }
    
    public void regiMapping(UserDataDTO udd){
        udd.setName(this.name);
        udd.setMail(this.mail);
        udd.setPassword(this.password);
        udd.setAddress(this.address);
    }
    
    public ArrayList<String> chk(){
        ArrayList<String> chkList = new ArrayList<>();
        if(this.name.equals("")){
            chkList.add("名前");
        }
        if(this.mail.equals("")){
            chkList.add("メールアドレス");
        }
        if(this.password.equals("")){
            chkList.add("パスワード");
        }
        if(this.address.equals("")){
            chkList.add("住所");
        }
        return chkList;
    }
    
    public String header(String url,UserDataDTO udd,UserData ud,HttpSession hs){
        String msg="";
        if(udd.getName()==null){ //ログインしていないとき
            ud.setUrl(url); //現在のページをbeansに保存
            hs.setAttribute("ud", ud);
            msg="<a href='login'>ログイン</a>";
        }else{
            msg="ようこそ <a href='mydata'>"+udd.getName()+"</a> さん <a href='cart'>買い物かご</a> / <a href='login?sta=logout'>ログアウト</a>";
        }
        return msg;
    }
    
    public String top(){
        String msg="<a href=\"top.jsp\">トップに戻る</a>";
        return msg;
    }
}
