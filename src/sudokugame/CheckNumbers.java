/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudokugame;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Tshi
 */
public class CheckNumbers {
    
    
    int retriveValue(int row, int col){
        Connection con = null;
        PreparedStatement pre = null;
        
        int val = 0;
        
         try {
            con = DBManager.getConnection();
            String sql = "SELECT val FROM gametable WHERE row =? AND col =?";
            pre = con.prepareStatement(sql);
            
            pre.setInt(1, row);
            pre.setInt(2, col);
            
            ResultSet rs = pre.executeQuery();
            if(rs.next()){
                return rs.getInt("val");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
           
            if (pre != null) {
                try {
                    pre.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        
        return val;
    }
    
    boolean isExist(int row, int col){
        Connection con = null;
        PreparedStatement pre = null;
        
         try {
            con = DBManager.getConnection();
            String sql = "SELECT val FROM gametable WHERE row =? AND col =?";
            pre = con.prepareStatement(sql);
            
            pre.setInt(1, row);
            pre.setInt(2, col);
            
            ResultSet rs = pre.executeQuery();
            if(rs.next()){
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
           
            if (pre != null) {
                try {
                    pre.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    
        return false;
    }
    
    void insertVal(int row, int col, int val){
        Connection con = null;
        PreparedStatement pre = null;
        String query = "";
        try {
            
            con = DBManager.getConnection();
            if (isExist(row, col)) {
                query = "UPDATE gametable SET val=? WHERE row=? AND col=?";
                pre = con.prepareStatement(query);

                pre.setInt(1, val);
                pre.setInt(2, row);
                pre.setInt(3, col);
            }else {
                query = " INSERT INTO gametable (row, col, val) VALUES(?,?,?)";
                pre = con.prepareStatement(query);

                pre.setInt(1, row);
                pre.setInt(2, col);
                pre.setInt(3, val);
            }
            
            int i = pre.executeUpdate();

            
        } catch (SQLException e) {
            System.out.println("can NOT insert val error: ");
        } // closing all connection if they were opened 
        finally {
            if (pre != null) {
                try {
                    pre.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    boolean foundInCol(int row, int col){
        Connection con = null;
        PreparedStatement pre = null;

        try {
            con = DBManager.getConnection();
            String sql = "SELECT val FROM gametable WHERE row =? AND col =? AND val NOT IN("
                        + "SELECT val FROM gametable WHERE row <> ? AND col = ?)";
            pre = con.prepareStatement(sql);
            pre.setInt(1, row);
            pre.setInt(2, col);
            pre.setInt(3, row);
            pre.setInt(4, col);
            ResultSet rs = pre.executeQuery();
            if(rs.next()){
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
           
            if (pre != null) {
                try {
                    pre.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    
        return false;
    }
    
    boolean foundInRow(int row, int col){
        Connection con = null;
        PreparedStatement pre = null;

        try {
            
            con = DBManager.getConnection();
            String sql = "SELECT val FROM gametable WHERE row =? AND col =? AND val NOT IN("
                        + "SELECT val FROM gametable WHERE row = ? AND col <> ?)";
            pre = con.prepareStatement(sql);
            pre.setInt(1, row);
            pre.setInt(2, col);
            pre.setInt(3, row);
            pre.setInt(4, col);
            ResultSet rs = pre.executeQuery();
            if(rs.next()){
                return true;
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
           
            if (pre != null) {
                try {
                    pre.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    
        return false;
    }

    ArrayList<Integer> foundInRigon(int row1,int row2, int col){
        Connection con = null;
        PreparedStatement pre = null;
        ArrayList<Integer> arLis = new ArrayList<Integer>();
        String signe ="<";
        
        if (col == 2) {
            signe =">";
        }
        
        try {
            
            con = DBManager.getConnection();
            String sql = "select COUNT(val) as countOfVal, val from gametable where (row = ? or row = ?) "
                         +"and col "+ signe +" ? group by val order by countOfVal desc";
            pre = con.prepareStatement(sql);
          
            pre.setInt(1, row1);
            pre.setInt(2, row2);
            pre.setInt(3, col);
            ResultSet rs = pre.executeQuery();
            
            while(rs.next()){
                if (rs.getInt("countOfVal") > 1) { // save num of val if that val was redundncy
                    arLis.add(rs.getInt("val")); 
                }else if(rs.getInt("countOfVal") == 1){ // no val redundncy
                    return arLis;
                }
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
           
            if (pre != null) {
                try {
                    pre.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    
        return arLis;
    }
    
}
