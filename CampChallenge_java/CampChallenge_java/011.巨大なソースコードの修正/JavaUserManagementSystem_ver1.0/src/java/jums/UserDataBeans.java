/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;
import java.io.Serializable;

/**
 *
 * @author kurikura
 */
public class UserDataBeans implements Serializable{
    private String name;
    private String year;
    private String month;
    private String day;
    private String type;
    private String type_st;
    private String tell;
    private String comment;
    private String err;
    
    public UserDataBeans(){}
    
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        if(this.name==null){ //初期表示の処理
            this.name="";
        }
        return this.name;
    }
    
    public void setYear(String year){ this.year=year;}
    public String getYear(){
        if(this.year==null) this.year="----"; //初期表示の処理
        return this.year;
    }
    
    public void setMonth(String month){ this.month=month;}
    public String getMonth(){
        if(this.month==null) this.month="--"; //初期表示の処理
        return this.month;
    }
    
    public void setDay(String day){ this.day=day;}
    public String getDay(){
        if(this.day==null) this.day="--"; //初期表示の処理
        return this.day;
    }
    
    public void setType(String type){
        this.type=type;
        setType_st(type);
    }
    public String getType(){ return this.type;}
    
    public void setType_st(String type){
        if(type!=null){ //typeの数値を日本語に変換
                //String型のままだと判定できない場合があるのでint型に変換
                int i= Integer.valueOf(type);
                switch (i){
                    case 1:
                        this.type_st="エンジニア";
                        break;
                    case 2:
                        this.type_st="営業";
                        break;
                    case 3:
                        this.type_st="その他";
                        break;
                }
            }
    }
    public String getType_st(){ return this.type_st;}
    
    public void setTell(String tell){ this.tell=tell;}
    public String getTell(){
        if(this.tell==null) this.tell=""; //初期表示の処理
        return this.tell;}
    
    public void setCom(String comment){ this.comment=comment;}
    public String getCom(){
        if(this.comment==null) this.comment=""; //初期表示の処理
        return this.comment;
    }
    
    public void setErr(String err){ this.err=err;}
    public String getErr(){
        if(this.err==null) this.err="";
        return this.err;
    }
}
