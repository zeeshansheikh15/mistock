package mistock;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@SessionScoped

@ManagedBean
public class adminlog {	

	private Double iduser;
	private String name;
	private Double mobile;
	private String email;
	private String address;
	private String user;
	private String passw;
	private String license;
	private Double fee;
	
	private String username;
	private String password;
	private String pass;
	
	public ArrayList<mantemp> xyz=null;
	private Map<Integer,Integer> numbers;
	double t = 0;
	public int selected;
	
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

	public int getSelected() {
		return selected;
	}

	public void setSelected(int selected) {
		this.selected = selected;
	}

	public static HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

	}

	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}
	@PostConstruct
	public void table()
	{
		xyz=new ArrayList<mantemp>();
		numbers=new LinkedHashMap<Integer,Integer>();
		int i=0;
		String url1 = System.getenv("ICSI518_SERVER");
		String url2 = System.getenv("ICSI518_PORT");
		String url3 = System.getenv("ICSI518_DB");
		String use = System.getenv("ICSI518_USER");
		String passwrd = System.getenv("ICSI518_PASSWORD");
		String url = "jdbc:mysql://" + url1 +":"+url2+"/"+url3;
		Connection con = null;
	

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, use, passwrd);

			PreparedStatement stmt=con.prepareStatement("select name,mobile,email,address,license,fee from manregtemp");  
			ResultSet rs=stmt.executeQuery();  
			while(rs.next()){  
			
			xyz.add(new mantemp(i,rs.getString(1), rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDouble(6)));
			numbers.put(i,rs.getInt(2) );
			i++;
			}

		} catch (Exception e) {
			System.out.println("error" + e.getMessage());

		}
	
		
	}
	public ArrayList<mantemp> getXyz() {
		return xyz;
	}

	public void setXyz(ArrayList<mantemp> xyz) {
		this.xyz = xyz;
	}

	public String validity() {
		String url1 = System.getenv("ICSI518_SERVER");
		String url2 = System.getenv("ICSI518_PORT");
		String url3 = System.getenv("ICSI518_DB");
		String use = System.getenv("ICSI518_USER");
		String passwrd = System.getenv("ICSI518_PASSWORD");
		String url = "jdbc:mysql://" + url1 +":"+url2+"/"+url3;
		Connection con = null;
	

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, use, passwrd);

			String sql = "select iduser from admin where username = ? and password = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				setPass("true");
			} else {
				setPass("false");
			}

		} catch (Exception e) {
			System.out.println("error" + e.getMessage());

		}
		if (getPass() == "true") {
			HttpSession ss = getSession();
			ss.setAttribute("username", username);
		
			return ("adminpage");
		} else {
			FacesMessage fs = new FacesMessage("login error:Invalid username or password", "ERROR_MSG");
			
			FacesContext.getCurrentInstance().addMessage(null, fs);
			return ("adlog");
		}

	}

	public String invalidity() {
		HttpSession ss = getSession();
		ss.invalidate();
		return ("homepage");
	}

	public void selectedman() {
		String url1 = System.getenv("ICSI518_SERVER");
		String url2 = System.getenv("ICSI518_PORT");
		String url3 = System.getenv("ICSI518_DB");
		String use = System.getenv("ICSI518_USER");
		String passwrd = System.getenv("ICSI518_PASSWORD");
		String url = "jdbc:mysql://" + url1 +":"+url2+"/"+url3;
		Connection con = null;
	

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, use, passwrd);

			String sql = "select * from manregtemp where mobile = ? ";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, selected);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				this.iduser=rs.getDouble(1);
				this.name=rs.getString(2);
				this.mobile=rs.getDouble(3);
				this.email=rs.getString(4);
				this.address=rs.getString(5);
				this.user=rs.getString(6);
				this.passw=rs.getString(7);
				this.license=rs.getString(8);
				this.fee=rs.getDouble(9);
			}
			this.registerman();

		} catch (Exception e) {
			System.out.println("error" + e.getMessage());

		}
	
	}
	public void registerman() {
		System.out.println("registerman");
		String url1 = System.getenv("ICSI518_SERVER");
		String url2 = System.getenv("ICSI518_PORT");
		String url3 = System.getenv("ICSI518_DB");
		String use = System.getenv("ICSI518_USER");
		String passwrd = System.getenv("ICSI518_PASSWORD");
		String url = "jdbc:mysql://" + url1 +":"+url2+"/"+url3;
		System.out.println(url);
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, use, passwrd);

			String sql = "insert into manager " + "(iduser,name,mobile,email,address,username,password,license,fee)"
					+ "values (?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDouble(1, iduser);
			ps.setString(2, name);
			ps.setDouble(3, mobile);
			ps.setString(4, email);
			ps.setString(5, address);
			ps.setString(6, user);
			ps.setString(7, passw);
			ps.setString(8, license);
			ps.setDouble(9, fee);

			ps.executeUpdate();
			
			String sql2 = "insert into manageraccounts " + "(idmanageraccounts,balance)"
					+ "values (?,?)";
			PreparedStatement ps2 = con.prepareStatement(sql2);
			ps2.setDouble(1, iduser);
			ps2.setDouble(2, 0);
			

			ps2.executeUpdate();

		} catch (Exception e) {
			System.out.println("error" + e.getMessage());

		}
		FacesMessage fs = new FacesMessage("Successfully registered", "ERROR_MSG");
		
		FacesContext.getCurrentInstance().addMessage(null, fs);
		this.deletetempman();
	}
	public void deletetempman() {
		String url1 = System.getenv("ICSI518_SERVER");
		String url2 = System.getenv("ICSI518_PORT");
		String url3 = System.getenv("ICSI518_DB");
		String use = System.getenv("ICSI518_USER");
		String passwrd = System.getenv("ICSI518_PASSWORD");
		String url = "jdbc:mysql://" + url1 +":"+url2+"/"+url3;
		Connection con = null;
	

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, use, passwrd);

			String sql = "delete from manregtemp where mobile = ? ";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, selected);

			 ps.executeUpdate();
			

		} catch (Exception e) {
			System.out.println("error" + e.getMessage());

		}
	
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public  adminlog() {
	}

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Map<Integer, Integer> getNumbers() {
		return numbers;
	}

	public void setNumbers(Map<Integer, Integer> numbers) {
		this.numbers = numbers;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getMobile() {
		return mobile;
	}

	public void setMobile(Double mobile) {
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


	public Double getIduser() {
		return iduser;
	}

	public void setIduser(Double iduser) {
		this.iduser = iduser;
	}

	public void display()
	{
		System.out.println(selected);
	}

}
