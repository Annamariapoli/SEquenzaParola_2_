package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import bean.Parola;

public class Dao {
	
	public boolean isPresente(String parola){
		Connection conn = DBConnect.getConnection();
		String query ="select * from parola where nome=?";
		try{
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, parola);
			ResultSet res = st.executeQuery();
			if(res.next()){
				return true;
			} else return false;
		}catch(SQLException e ){
			e.printStackTrace();
			return false;
		}
	}
	
      public List<Parola> getParole(int lung){
        Connection conn = DBConnect.getConnection();
  		String query ="select * from parola where LENGTH(nome)=?";
  		try{
  			PreparedStatement st = conn.prepareStatement(query);
  			List<Parola> parole = new LinkedList<Parola>();
  			st.setInt(1, lung);
  			ResultSet res = st.executeQuery();
  			while(res.next()){
  				Parola p = new Parola(res.getInt("id"), res.getString("nome"));
  				parole.add(p);
  			}conn.close();
  			return parole;
  		}catch(SQLException e ){
  			e.printStackTrace();
  			return null;
  		}
	}
      
      public int getConta(int lung){
          Connection conn = DBConnect.getConnection();
    		String query ="select count(*) as conta from parola where LENGTH(nome)=?";
    		int conta =-1;
    		try{
    			PreparedStatement st = conn.prepareStatement(query);
    			st.setInt(1, lung);
    			ResultSet res = st.executeQuery();
    			if(res.next()){
    				conta = res.getInt("conta");
    			}
    			conn.close();
    		}catch(SQLException e ){
    			e.printStackTrace();
    		}
    		return conta;
      }
  		

}
