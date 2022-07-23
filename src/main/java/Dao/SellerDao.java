package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Connection.DBConnection;
import Model.Seller;

public class SellerDao {
	public static void insertSeller(Seller v) {
		try {
			Connection conn = DBConnection.createConnection();
			String sql="insert into sellerdata(name,contact,address,pincode,email,password) values(?,?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, v.getName());
			pst.setLong(2, v.getContact());
			pst.setString(3, v.getAddress());
			pst.setInt(4, v.getPincode());
			pst.setString(5, v.getEmail());
			pst.setString(6, v.getPassword());
			pst.executeUpdate();
			System.out.println("data inserted");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static Seller checkSellerlogin (Seller v) {
		Seller v1 = null;
		try {
			Connection conn = DBConnection.createConnection();
			String sql = "select * from sellerdata where email=? and password=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, v.getEmail());
			pst.setString(2, v.getPassword());
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				v1 = new Seller();
				v1.setId(rs.getInt(1));
				v1.setName(rs.getString(2));
				v1.setContact(rs.getLong(3));
				v1.setAddress(rs.getString(4));
				v1.setPincode(rs.getInt(5));
				v1.setEmail(rs.getString(6));
				v1.setPassword(rs.getString(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return v1;
	}
	public static void changePassword(Seller v) {
		try {
			Connection conn = DBConnection.createConnection();
			String sql = "update sellerdata set password=? where email=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, v.getNpassword());
			pst.setString(2, v.getEmail());
			pst.executeUpdate();
			System.out.println("Password Changed");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void updateSeller(Seller v) {
		try {
			Connection conn = DBConnection.createConnection();
			String sql = "update sellerdata set name=?,contact=?,address=?,pincode=?,email=? where id=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, v.getName());
			pst.setLong(2, v.getContact());
			pst.setString(3, v.getAddress());
			pst.setInt(4, v.getPincode());
			pst.setString(5, v.getEmail());
			pst.setInt(6, v.getId());
			pst.executeUpdate();
			System.out.println("data updated");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
