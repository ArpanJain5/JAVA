package Model;

public class Customer {
	private int id,pincode;
	private String name,address,email,password,npassword,cnpassword;
	private long contact;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNpassword() {
		return npassword;
	}
	public void setNpassword(String npassword) {
		this.npassword = npassword;
	}
	public String getCnpassword() {
		return cnpassword;
	}
	public void setCnpassword(String cnpassword) {
		this.cnpassword = cnpassword;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getContact() {
		return contact;
	}
	public void setContact(long l) {
		this.contact = l;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", pincode=" + pincode + ", name=" + name + ", address=" + address + ", email="
				+ email + ", password=" + password + ", npassword=" + npassword + ", cnpassword=" + cnpassword
				+ ", contact=" + contact + "]";
	}
}
