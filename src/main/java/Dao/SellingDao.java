package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Connection.DBConnection;
import Model.Selling;

public class SellingDao {
	public static void insertSelling(Selling w) {
		try {
			Connection conn = DBConnection.createConnection();
			String sql="insert into sellingdata(sid,name,category,price,status) values(?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, w.getSid());
			pst.setString(2, w.getName());
			pst.setString(3, w.getCategory());
			pst.setInt(4, w.getPrice());
			pst.setString(5, w.getStatus());
			pst.executeUpdate();
			System.out.println("data inserted");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static List<Selling> SellingList(int id){
		List<Selling> list = new ArrayList<Selling>();
		try {
			Connection conn = DBConnection.createConnection();
			String sql="select * from sellingdata where status='unsold'";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				Selling w = new Selling();
				w.setId(rs.getInt(1));
				w.setSid(rs.getInt(2));
				w.setName(rs.getString(3));
				w.setCategory(rs.getString(4));
				w.setPrice(rs.getInt(5));
				w.setStatus(rs.getString(6));
				list.add(w);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public static List<Selling> ProductList(){
		List<Selling> list = new ArrayList<Selling>();
		try {
			Connection conn = DBConnection.createConnection();
			String sql="select * from sellingdata where status='unsold'";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				Selling w = new Selling();
				w.setId(rs.getInt(1));
				w.setSid(rs.getInt(2));
				w.setName(rs.getString(3));
				w.setCategory(rs.getString(4));
				w.setPrice(rs.getInt(5));
				w.setStatus(rs.getString(6));
				list.add(w);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
