package com.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class Connectiondb {

	public Connection connect;
	public Statement statement;
	public ResultSet rs;
	public ResultSetMetaData rsMetaData;
	public PreparedStatement ps;
	
	public Connectiondb() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbpudding", "root", "");
			
			statement = connect.createStatement();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Get All Food Data
	public ResultSet getMenuData() {
		try {
			ps = connect.prepareStatement("SELECT * FROM pudding");
			rs = ps.executeQuery();
			rs.getString("id_menu");
			rs.getString("menu_name");
			rs.getInt("menu_price");
			rs.getInt("menu_stock");
			rs.next();
			
			rsMetaData = rs.getMetaData();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public void insertFoodsData(String menuId, String menuName, int menuPrice, int menuStock) {
		
		try {
			ps = connect.prepareStatement("INSERT INTO pudding (id_menu, menu_name, menu_price, menu_stock) VALUES (?,?,?,?)");
			
			ps.setString(1, menuId);
			ps.setString(2, menuName);
			ps.setInt(3, menuPrice);
			ps.setInt(4, menuStock);
			
			ps.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void updateData(String idMenu, String menuName, int menuPrice, int menuStock ) {
		
		try {
			ps = connect.prepareStatement("UPDATE pudding SET menu_name = ?, menu_price = ?, menu_stock = ? WHERE id_menu = ?");
			ps.setString(1, menuName);
			ps.setInt(2, menuPrice);
			ps.setInt(3, menuStock);
			ps.setString(4, idMenu);
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteData(String menuId) {
		
		try {
			ps = connect.prepareStatement("DELETE FROM Pudding WHERE id_menu = ?");
			ps.setString(1, menuId);
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
