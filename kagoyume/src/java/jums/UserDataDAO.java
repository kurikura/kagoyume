/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;

import base.DBManager;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author kurikura
 */
public class UserDataDAO {
    public static UserDataDAO getInstance(){
        return new UserDataDAO();
    }
    
    public void login(UserDataDTO udd)throws SQLException{
        Connection con=null;
        PreparedStatement st;
        try{
            con=DBManager.getConnection();
            st=con.prepareStatement("SELECT * FROM user_t WHERE mail like ? AND password like ?");
            st.setString(1,udd.getMail());
            st.setString(2,udd.getPassword());
            
            ResultSet rs=st.executeQuery();
            while(rs.next()){
                udd.setUserID(rs.getInt(1));
                udd.setName(rs.getString(2));
                udd.setPassword(rs.getString(3));
                udd.setMail(rs.getString(4));
                udd.setAddress(rs.getString(5));
                udd.setTotal(rs.getInt(6));
                udd.setNewDate(rs.getTimestamp(7));
                udd.setDeleteFlg(rs.getInt(8));
            }
            System.out.println("login search completed");
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
    
    public int registration(UserDataDTO udd)throws SQLException{
        Connection con=null;
        PreparedStatement st;
        Timestamp ts=new Timestamp(System.currentTimeMillis()); //登録日時
        udd.setNewDate(ts);
        try{
            con=DBManager.getConnection();
            st=con.prepareStatement("INSERT INTO user_t(name,mail,password,address,total,newDate,deleteFlg) VALUES(?,?,?,?,?,?,?)");
            st.setString(1,udd.getName());
            st.setString(2,udd.getMail());
            st.setString(3,udd.getPassword());
            st.setString(4,udd.getAddress());
            st.setInt(5,0);
            st.setTimestamp(6,ts);
            st.setInt(7,0);
            
            int rs=st.executeUpdate();
            System.out.println("registration completed");
            return rs;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
    
    public int update(UserDataDTO udd)throws SQLException{
        Connection con=null;
        PreparedStatement st;
        try{
            con=DBManager.getConnection();
            st=con.prepareStatement("UPDATE user_t SET name=?,password=?,mail=?,address=? where userID=?");
            st.setString(1,udd.getName());
            st.setString(2,udd.getPassword());
            st.setString(3,udd.getMail());
            st.setString(4,udd.getAddress());
            st.setInt(5,udd.getUserID());
            
            int rs=st.executeUpdate();
            System.out.println("update completed");
            return rs;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
    
    public int delete(UserDataDTO udd)throws SQLException{
        Connection con=null;
        PreparedStatement st;
        try{
            con=DBManager.getConnection();
            st=con.prepareStatement("UPDATE user_t SET deleteFlg=? where userID=?");
            st.setInt(1,1);
            st.setInt(2,udd.getUserID());
            
            int rs=st.executeUpdate();
            System.out.println("delete completed");
            return rs;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
    
    public int buy(UserDataDTO udd)throws SQLException{
        Connection con=null;
        PreparedStatement st;
        Timestamp ts=new Timestamp(System.currentTimeMillis()); //登録日時
        udd.setNewDate(ts);
        try{
            con=DBManager.getConnection();
            st=con.prepareStatement("INSERT INTO buy_t(userID,itemCode,Type,buyDate) VALUES(?,?,?,?)");
            st.setInt(1,udd.getUserID());
            st.setString(2,udd.getBuyID());
            st.setInt(3,udd.getType());
            st.setTimestamp(4,ts);
            
            int rs=st.executeUpdate();
            System.out.println("buy completed");
            return rs;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
    
    public int total(UserDataDTO udd)throws SQLException{
        Connection con=null;
        PreparedStatement st;
        try{
            con=DBManager.getConnection();
            st=con.prepareStatement("UPDATE user_t SET total=? where userID=?");
            st.setInt(1,udd.getTotal());
            st.setInt(2,udd.getUserID());
            
            int rs=st.executeUpdate();
            return rs;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
    
    public ArrayList history(UserDataDTO udd)throws SQLException{
        Connection con=null;
        PreparedStatement st;
        try{
            con=DBManager.getConnection();
            st=con.prepareStatement("SELECT itemCode,buyDate FROM buy_t where userID=?");
            st.setInt(1,udd.getUserID());
            
            ResultSet rs = st.executeQuery();
            ArrayList<UserDataDTO> history=new ArrayList<>();
            while(rs.next()){ //検索結果が1件以上あった場合、arraylistに保存
                UserDataDTO result = new UserDataDTO();
                result.setBuyID(rs.getString(1));
                result.setBuyDate(rs.getTimestamp(2));
                history.add(result); //arraylistのn番目にn行のデータを保存する
            }
            return history;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
}