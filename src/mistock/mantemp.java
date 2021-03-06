package mistock;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class mantemp {
	private int sno;
	private String name;
	private int mobile;
	private String email;
	private String address;
	private String user;
	private String passw;
	private String license;
	private Double fee;
	

	
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	public Double getFee() {
		return fee;
	}
	public void setFee(Double fee) {
		this.fee = fee;
	}
	public mantemp(int sno, String name, int mobile, String email, String address) {
		super();
		this.sno = sno;
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.address = address;
	}
	
	public mantemp(int sno, String name, int mobile, String email, String address, String license, Double fee) {
		super();
		this.sno = sno;
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.address = address;
		this.license = license;
		this.fee = fee;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassw() {
		return passw;
	}
	public void setPassw(String passw) {
		this.passw = passw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMobile() {
		return mobile;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public void setMobile(int mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "mantemp [sno=" + sno + ", name=" + name + ", mobile=" + mobile + ", email=" + email + ", address="
				+ address + ", user=" + user + ", passw=" + passw + ", license=" + license + ", fee=" + fee + "]";
	}


}
