package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Connection.DBConnection;
import Model.Customer;

public class CustomerDao {
	public static void insertCustomer(Customer u) {
		try {
			Connection conn = DBConnection.createConnection();
			String sql="insert into customerdata(name,contact,address,pincode,email,password) values(?,?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, u.getName());
			pst.setLong(2, u.getContact());
			pst.setString(3, u.getAddress());
			pst.setInt(4, u.getPincode());
			pst.setString(5, u.getEmail());
			pst.setString(6, u.getPassword());
			pst.executeUpdate();
			System.out.println("data inserted");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static Customer checkCustomerlogin (Customer u) {
		Customer u1 = null;
		try {
			Connection conn = DBConnection.createConnection();
			String sql = "select * from customerdata where email=? and password=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, u.getEmail());
			pst.setString(2, u.getPassword());
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				u1 = new Customer();
				u1.setId(rs.getInt(1));
				u1.setName(rs.getString(2));
				u1.setContact(rs.getLong(3));
				u1.setAddress(rs.getString(4));
				u1.setPincode(rs.getInt(5));
				u1.setEmail(rs.getString(6));
				u1.setPassword(rs.getString(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u1;
	}
	public static void changePassword(Customer u) {
		try {
			Connection conn = DBConnection.createConnection();
			String sql = "update customerdata set password=? where email=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, u.getNpassword());
			pst.setString(2, u.getEmail());
			pst.executeUpdate();
			System.out.println("Password Changed");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void updateCustomer(Customer u) {
		try {
			Connection conn = DBConnection.createConnection();
			String sql = "update customerdata set name=?,contact=?,address=?,pincode=?,email=? where id=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, u.getName());
			pst.setLong(2, u.getContact());
			pst.setString(3, u.getAddress());
			pst.setInt(4, u.getPincode());
			pst.setString(5, u.getEmail());
			pst.setInt(6, u.getId());
			pst.executeUpdate();
			System.out.println("done");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
