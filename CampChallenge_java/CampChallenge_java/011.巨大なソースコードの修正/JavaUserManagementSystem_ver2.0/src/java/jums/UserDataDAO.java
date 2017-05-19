package jums;

import base.DBManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * ユーザー情報を格納するテーブルに対しての操作処理を包括する
 * DB接続系はDBManagerクラスに一任
 * 基本的にはやりたい1種類の動作に対して1メソッド
 * @author hayashi-s
 */
public class UserDataDAO {
    
    //インスタンスオブジェクトを返却させてコードの簡略化
    public static UserDataDAO getInstance(){
        return new UserDataDAO();
    }
    
    /**
     * データの挿入処理を行う。現在時刻は挿入直前に生成
     * @param ud 対応したデータを保持しているJavaBeans
     * @return 挿入件数
     * @throws SQLException 呼び出し元にcatchさせるためにスロー 
     */
    public int insert(UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st =  con.prepareStatement("INSERT INTO user_t(name,birthday,tell,type,comment,newDate) VALUES(?,?,?,?,?,?)");
            st.setString(1, ud.getName());
            st.setDate(2, new java.sql.Date(ud.getBirthday().getTime()));//指定のタイムスタンプ値からSQL格納用のDATE型に変更
            st.setString(3, ud.getTell());
            st.setInt(4, ud.getType());
            st.setString(5, ud.getComment());
            st.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            int rs=st.executeUpdate();
            System.out.println("insert completed");
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
    
    /**
     * データの検索処理を行う。
     * @param ud 対応したデータを保持しているJavaBeans
     * @throws SQLException 呼び出し元にcatchさせるためにスロー 
     * @return 検索結果
     */
    public ArrayList search(UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            String sql = "SELECT * FROM user_t";
            int cnt=0;
            boolean flag = false;
            /*   cnt
            名前  1
            生年  3
            種別  5 */
            if (!ud.getName().equals("")) { //名前
                sql += " WHERE name like ?";
                flag = true;
                cnt++;
            }
            if (ud.getBirthday()!=null) { //生年
                cnt=cnt+3;
                if(!flag){
                    sql += " WHERE birthday like ?";
                    flag = true;
                }else{
                    sql += " AND birthday like ?";
                }
            }
            if (ud.getType()!=0) { //種別
                cnt=cnt+5;
                if(!flag){
                    sql += " WHERE type like ?";
                }else{
                    sql += " AND type like ?";
                }
            }
            st =  con.prepareStatement(sql);
            
            //cnt=0 検索条件なしの場合は全件検索する
            //検索条件によってパラメータの数が変わるので、ここで分岐させる
            switch(cnt){ 
                case 1: //名前
                    st.setString(1, "%"+ud.getName()+"%");
                    break;
                case 3: //生年
                    st.setString(1, "%"+ new SimpleDateFormat("yyyy").format(ud.getBirthday())+"%");
                    break;
                case 5: //種別
                    st.setInt(1, ud.getType());
                    break;
                case 4: //名前、生年
                    st.setString(1, "%"+ud.getName()+"%");
                    st.setString(2, "%"+ new SimpleDateFormat("yyyy").format(ud.getBirthday())+"%");
                    break;
                case 6: //名前、種別
                    st.setString(1, "%"+ud.getName()+"%");
                    st.setInt(2, ud.getType());
                    break;
                case 8: //生年、種別
                    st.setString(1, "%"+ new SimpleDateFormat("yyyy").format(ud.getBirthday())+"%");
                    st.setInt(2, ud.getType());
                    break;
                case 9: //名前、生年、種別
                    st.setString(1, "%"+ud.getName()+"%");
                    st.setString(2, "%"+ new SimpleDateFormat("yyyy").format(ud.getBirthday())+"%");
                    st.setInt(3, ud.getType());
            }
            
            ResultSet rs = st.executeQuery();
            ArrayList<UserDataDTO> rsList=new ArrayList<UserDataDTO>();
            while(rs.next()){ //検索結果が1件以上あった場合、arraylistに保存
                UserDataDTO resultUd = new UserDataDTO();
                resultUd.setUserID(rs.getInt(1));
                resultUd.setName(rs.getString(2));
                resultUd.setBirthday(rs.getDate(3));
                resultUd.setTell(rs.getString(4));
                resultUd.setType(rs.getInt(5));
                resultUd.setComment(rs.getString(6));
                resultUd.setNewDate(rs.getTimestamp(7));
                rsList.add(resultUd); //arraylistのn番目にn行のデータを保存する
            }
            
            System.out.println("search completed");
            return rsList;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
    
    /**
     * ユーザーIDによる1件のデータの検索処理を行う。
     * @param ud 対応したデータを保持しているJavaBeans
     * @throws SQLException 呼び出し元にcatchさせるためにスロー 
     * @return 検索結果
     */
    public UserDataDTO searchByID(UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            
            String sql = "SELECT * FROM user_t WHERE userID = ?";
            
            st =  con.prepareStatement(sql);
            st.setInt(1, ud.getUserID());
            
            ResultSet rs = st.executeQuery();
            rs.next();
            UserDataDTO resultUd = new UserDataDTO();
            resultUd.setUserID(rs.getInt(1));
            resultUd.setName(rs.getString(2));
            resultUd.setBirthday(rs.getDate(3));
            resultUd.setTell(rs.getString(4));
            resultUd.setType(rs.getInt(5));
            resultUd.setComment(rs.getString(6));
            resultUd.setNewDate(rs.getTimestamp(7));
            
            System.out.println("searchByID completed");
            return resultUd;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
    
    /**
     * ユーザーIDによる1件のデータの更新処理を行う。
     * @param ud 対応したデータを保持しているJavaBeans
     * @throws SQLException 呼び出し元にcatchさせるためにスロー 
     * @return 更新結果
     */
    public int update(UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            String sql = "UPDATE user_t set name=?,birthday=?,tell=?,type=?,comment=?,newDate=? WHERE userID = ?";
            st =  con.prepareStatement(sql);
            
            st.setString(1, ud.getName());
            st.setDate(2, new java.sql.Date(ud.getBirthday().getTime()));
            st.setString(3, ud.getTell());
            st.setInt(4, ud.getType());
            st.setString(5, ud.getComment());
            st.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            st.setInt(7, ud.getUserID());
            int rs = st.executeUpdate();
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
    
    /**
     * ユーザーIDによる1件のデータの削除を行う。
     * @param ud 対応したデータを保持しているJavaBeans
     * @throws SQLException 呼び出し元にcatchさせるためにスロー 
     * @return 削除件数
     */
    public int delete(UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            String sql = "DELETE FROM user_t WHERE userID = ?";
            
            st =  con.prepareStatement(sql);
            st.setInt(1, ud.getUserID());
            int rs = st.executeUpdate();
            System.out.println("delete completed");
            return rs;
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
}
