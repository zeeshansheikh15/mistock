package mistock;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@SessionScoped

@ManagedBean
public class usereg {
	
	private Double BALANCE;
	private String name;
	private String name2;
	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public Double getBALANCE() {
		return BALANCE;
	}

	public void setBALANCE(Double bALANCE) {
		BALANCE = bALANCE;
	}

	private Double mobile;
	private String email;
	private String address; 	
	private String user;
	private String passw;
	double t = 0;
	private String username;
	private String password;
	private String pass;
	double iduser;
	
	public double getIduser() {
		return iduser;
	}

	public void setIduser(double iduser) {
		this.iduser = iduser;
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getMobile1() {
		return mobile1;
	}

	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}

	public String getEmail1() {
		return email1;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	private String name1;
	private String mobile1;
	private String email1;
	private String address1; 
	private Double mobile2;
	private String email2;
	private String address2; 
	private String username2;
	private String password2;

	

	public String getUsername2() {
		return username2;
	}

	public void setUsername2(String username2) {
		this.username2 = username2;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public Double getMobile2() {
		return mobile2;
	}

	public void setMobile2(Double mobile2) {
		this.mobile2 = mobile2;
	}

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
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

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String doregister() {
		t = Math.random();
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

			String sql = "insert into user " + "(iduser,name,mobile,email,address,username,password)"
					+ "values (?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDouble(1, t);
			ps.setString(2, name);
			ps.setDouble(3, mobile);
			ps.setString(4, email);
			ps.setString(5, address);
			ps.setString(6, user);
			ps.setString(7, passw);

			ps.executeUpdate();
			
			String sql2 = "insert into useraccounts " + "(iduseraccounts,balance)"
					+ "values (?,?)";
			PreparedStatement ps2 = con.prepareStatement(sql2);
			ps2.setDouble(1, t);
			ps2.setDouble(2, 100000);

			ps2.executeUpdate();
		} catch (Exception e) {
			System.out.println("error" + e.getMessage());

		}
		FacesMessage fs = new FacesMessage("Successfully registered", "ERROR_MSG");
		
		FacesContext.getCurrentInstance().addMessage(null, fs);
		return ("userlogin");
	}

	public static HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

	}

	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
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

			String sql = "select iduser,name,email,address,mobile from user where username = ? and password = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				setPass("true");
				iduser=rs.getDouble(1);
				name1=rs.getString(2);
				email1=rs.getString(3);
				address1=rs.getString(4);
				mobile1=rs.getString(5);
				this.table();
				this.getmanbalance();
			} else {
				setPass("false");
			}

		} catch (Exception e) {
			System.out.println("error" + e.getMessage());

		}
		if (getPass() == "true") {
			HttpSession ss = getSession();
			ss.setAttribute("username", username);
			this.tableman();
			this.tablemanmessage();
			this.stockprice();
			return ("userpage");
		} else {
			FacesMessage fs = new FacesMessage("login error:Invalid username or password", "ERROR_MSG");
			
			FacesContext.getCurrentInstance().addMessage(null, fs);
			return ("userlogin");
		}
		
	}

	public ArrayList<messages> getXyzmanmessage() {
		return xyzmanmessage;
	}

	public void setXyzmanmessage(ArrayList<messages> xyzmanmessage) {
		this.xyzmanmessage = xyzmanmessage;
	}

	public String invalidity() {
		HttpSession ss = getSession();
		ss.invalidate();
		return ("homepage");
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



	public  usereg() {
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

	public void updatename() {
	
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

			String sql = "update user " + "SET name = ? WHERE iduser = ?";
					
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, name2);
			ps.setDouble(2, iduser);
			
			ps.executeUpdate();

		} catch (Exception e) {
			System.out.println("error" + e.getMessage());

		}
		FacesMessage fs = new FacesMessage("Successfully updated", "ERROR_MSG");
		
		FacesContext.getCurrentInstance().addMessage(null, fs);
		
	}
	public void updateemail() {
		
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

			String sql = "update user " + "SET email = ? WHERE iduser = ?";
					
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, email2);
			ps.setDouble(2, iduser);
			
			ps.executeUpdate();

		} catch (Exception e) {
			System.out.println("error" + e.getMessage());

		}
		FacesMessage fs = new FacesMessage("Successfully updated", "ERROR_MSG");
		
		FacesContext.getCurrentInstance().addMessage(null, fs);
		
	}
	public void updateaddress() {
		
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

			String sql = "update user " + "SET address = ? WHERE iduser = ?";
					
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, address2);
			ps.setDouble(2, iduser);
			
			ps.executeUpdate();

		} catch (Exception e) {
			System.out.println("error" + e.getMessage());

		}
		FacesMessage fs = new FacesMessage("Successfully updated", "ERROR_MSG");
		
		FacesContext.getCurrentInstance().addMessage(null, fs);
		
	}
	public void updatemobile() {
		
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

			String sql = "update user " + "SET mobile = ? WHERE iduser = ?";
					
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setDouble(1, mobile2);
			ps.setDouble(2, iduser);
			
			ps.executeUpdate();

		} catch (Exception e) {
			System.out.println("error" + e.getMessage());

		}
		FacesMessage fs = new FacesMessage("Successfully updated", "ERROR_MSG");
		
		FacesContext.getCurrentInstance().addMessage(null, fs);
		
	}
public void updateusername() {
		
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

			String sql = "update user " + "SET username = ? WHERE iduser = ?";
					
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, username2);
			ps.setDouble(2, iduser);
			
			ps.executeUpdate();

		} catch (Exception e) {
			System.out.println("error" + e.getMessage());

		}
		FacesMessage fs = new FacesMessage("Successfully updated", "ERROR_MSG");
		
		FacesContext.getCurrentInstance().addMessage(null, fs);
		
	}
public void updatepassword() {
	
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

		String sql = "update user " + "SET password = ? WHERE iduser = ?";
				
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, password2);
		ps.setDouble(2, iduser);
		
		ps.executeUpdate();

	} catch (Exception e) {
		System.out.println("error" + e.getMessage());

	}
	FacesMessage fs = new FacesMessage("Successfully updated", "ERROR_MSG");
	
	FacesContext.getCurrentInstance().addMessage(null, fs);
	
}

public ArrayList<mantemp> xyz=null;
private Map<Integer,Double> numbers;
public Double selected;
public Double getSelected() {
	return selected;
}

public void setSelected(Double selected) {
	this.selected = selected;
}

@PostConstruct
public void table()
{
	xyz = new ArrayList<mantemp>();
	numbers = new LinkedHashMap<Integer,Double>();
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

		PreparedStatement stmt=con.prepareStatement("select name,mobile,email,address,iduser from manager");  
		ResultSet rs=stmt.executeQuery();  
		while(rs.next()){  
		
		xyz.add(new mantemp(i,rs.getString(1), rs.getInt(2),rs.getString(3),rs.getString(4)));
		numbers.put(i,rs.getDouble(5) );
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

public Map<Integer, Double> getNumbers() {
	return numbers;
}

public void setNumbers(Map<Integer, Double> numbers) {
	this.numbers = numbers;
}

private String name3;
private Double mobile3;
private String email3;
private String address3;
private Double iduser3;
private Double fee3;

public Double getFee3() {
	return fee3;
}

public void setFee3(Double fee3) {
	this.fee3 = fee3;
}

public String getName3() {
	return name3;
}

public void setName3(String name3) {
	this.name3 = name3;
}

public Double getMobile3() {
	return mobile3;
}

public void setMobile3(Double mobile3) {
	this.mobile3 = mobile3;
}

public String getEmail3() {
	return email3;
}

public void setEmail3(String email3) {
	this.email3 = email3;
}

public String getAddress3() {
	return address3;
}

public void setAddress3(String address3) {
	this.address3 = address3;
}

public Double getIduser3() {
	return iduser3;
}

public void setIduser3(Double iduser3) {
	this.iduser3 = iduser3;
}

public String selectedmanager() {
	String url1 = System.getenv("ICSI518_SERVER");
	String url2 = System.getenv("ICSI518_PORT");
	String url3 = System.getenv("ICSI518_DB");
	String use = System.getenv("ICSI518_USER");
	String passwrd = System.getenv("ICSI518_PASSWORD");
	String url = "jdbc:mysql://" + url1 +":"+url2+"/"+url3;
	Connection con = null;

	System.out.println(this.selected);
	
	try {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url, use, passwrd);

		String sql = "select iduser,name,mobile,email,address,fee from manager where iduser = ? ";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setDouble(1, selected);

		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			this.iduser3=rs.getDouble(1);
			this.name3=rs.getString(2);
			this.mobile3=rs.getDouble(3);
			this.email3=rs.getString(4);
			this.address3=rs.getString(5);
			this.fee3=rs.getDouble(6);
			
		}
		

	} catch (Exception e) {
		System.out.println("error" + e.getMessage());

	}
	return("viewmanager");
	}

public void requestman() {
	
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
		String sql = "insert into tempuserman " + "(iduser,name,mobile,idman,manname,manmobile)"
				+ "values (?,?,?,?,?,?)";		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setDouble(1, iduser);								
		ps.setString(2, name1);								
		ps.setString(3, mobile1);								
		ps.setString(5, name3);									
		ps.setDouble(6, mobile3);										
		ps.setDouble(4, iduser3);										
		ps.executeUpdate();
									
	} catch (Exception e) {
		System.out.println("error" + e.getMessage());

	}
	FacesMessage fs = new FacesMessage("Successfully requested", "ERROR_MSG");
	
	FacesContext.getCurrentInstance().addMessage(null, fs);
	}

public ArrayList<usermantemp> xyzman=null;
private Map<Integer,Double> numbersman;
public Double selectedman;


public void tableman()
{
	xyzman=new ArrayList<usermantemp>();
	numbersman=new LinkedHashMap<Integer,Double>();
	int i=0;
	String url1 = System.getenv("ICSI518_SERVER");
	String url2 = System.getenv("ICSI518_PORT");
	String url3 = System.getenv("ICSI518_DB");
	String use = System.getenv("ICSI518_USER");
	String passwrd = System.getenv("ICSI518_PASSWORD");
	String url = "jdbc:mysql://" + url1 +":"+url2+"/"+url3;
	Connection con = null;
	System.out.println("connected");


	try {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url, use, passwrd);		
		String sql = "select idman,manname,manmobile from usermanager where iduser = ? ";
		System.out.println("conn");
		PreparedStatement ps = con.prepareStatement(sql);
		System.out.println("ps"+iduser);
		ps.setDouble(1, iduser);
		
		
		ResultSet rs = ps.executeQuery();
		System.out.println("executed");
		while(rs.next()){  
			System.out.println("entered");
			xyzman.add(new usermantemp(i,rs.getString(2), rs.getDouble(3)));
			numbersman.put(i,rs.getDouble(1) );
			i++;
		System.out.println("added in list");
		}

	} catch (Exception e) {
		System.out.println("error" + e.getMessage());

	}

	
}

public ArrayList<usermantemp> getXyzman() {
	return xyzman;
}

public void setXyzman(ArrayList<usermantemp> xyzman) {
	this.xyzman = xyzman;
}

public Map<Integer, Double> getNumbersman() {
	return numbersman;
}

public void setNumbersman(Map<Integer, Double> numbersman) {
	this.numbersman = numbersman;
}

public Double getSelectedman() {
	return selectedman;
}

public void setSelectedman(Double selectedman) {
	this.selectedman = selectedman;
}

private String message;
public void sendmessage()
{
	String url1 = System.getenv("ICSI518_SERVER");
	String url2 = System.getenv("ICSI518_PORT");
	String url3 = System.getenv("ICSI518_DB");
	String use = System.getenv("ICSI518_USER");
	String passwrd = System.getenv("ICSI518_PASSWORD");
	String url = "jdbc:mysql://" + url1 +":"+url2+"/"+url3;
	Connection con = null;
	System.out.println("connected");


	try {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url, use, passwrd);	
		System.out.println("connected message"+message);
		String sql = "insert into message " + "(senderid,name,recieverid,manname,message)"
				+ "values (?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		System.out.println("prepared");
		ps.setDouble(1, iduser);
		System.out.println(iduser+"added iduser"+"------"+selectedman+"manid");
		ps.setDouble(3, selectedman);
		System.out.println("added manid");
		ps.setString(5, message);
		ps.setString(2, name1);
		ps.setString(4, name3);
		System.out.println("added in message");
		ps.executeUpdate();
		
		String sql1 = "insert into tempmessage " + "(senderid,name,recieverid,manname,message)"
				+ "values (?,?,?,?,?)";
		PreparedStatement ps1 = con.prepareStatement(sql1);
		System.out.println("prepared");
		ps1.setDouble(1, iduser);
		System.out.println(iduser+"added iduser"+"------"+selectedman+"manid");
		ps1.setDouble(3, selectedman);
		System.out.println("added manid");
		ps1.setString(5, message);
		ps1.setString(2, name1);
		ps1.setString(4, name3);
		System.out.println("added in tempmessage");
		ps1.executeUpdate();
		
		System.out.println("updated");
	} catch (Exception e) {
		System.out.println("error" + e.getMessage());

	}

	
}

public String getMessage() {
	return message;
}

public void setMessage(String message) {
	this.message = message;
}

public String viewmymanager() {
	String url1 = System.getenv("ICSI518_SERVER");
	String url2 = System.getenv("ICSI518_PORT");
	String url3 = System.getenv("ICSI518_DB");
	String use = System.getenv("ICSI518_USER");
	String passwrd = System.getenv("ICSI518_PASSWORD");
	String url = "jdbc:mysql://" + url1 +":"+url2+"/"+url3;
	Connection con = null;

	System.out.println(this.selectedman);
	
	try {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url, use, passwrd);

		String sql = "select iduser,name,mobile,email,address from manager where iduser = ? ";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setDouble(1, selectedman);

		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			this.iduser3=rs.getDouble(1);
			this.name3=rs.getString(2);
			this.mobile3=rs.getDouble(3);
			this.email3=rs.getString(4);
			this.address3=rs.getString(5);
			
		}
		

	} catch (Exception e) {
		System.out.println("error" + e.getMessage());

	}
	this.tablemanmessage2();
	return("mymanager");
	}

public ArrayList<messages> xyzmanmessage=null;


public void tablemanmessage()
{
	xyzmanmessage=new ArrayList<messages>();
	String url1 = System.getenv("ICSI518_SERVER");
	String url2 = System.getenv("ICSI518_PORT");
	String url3 = System.getenv("ICSI518_DB");
	String use = System.getenv("ICSI518_USER");
	String passwrd = System.getenv("ICSI518_PASSWORD");
	String url = "jdbc:mysql://" + url1 +":"+url2+"/"+url3;
	Connection con = null;
	System.out.println("connected");


	try {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url, use, passwrd);		
		String sql = "select name,message from tempmessage where recieverid = ? ";
		System.out.println("conn");
		PreparedStatement ps = con.prepareStatement(sql);
		System.out.println("ps"+iduser);
		ps.setDouble(1, iduser);
		
		
		ResultSet rs = ps.executeQuery();
		System.out.println("executed");
		while(rs.next()){  
		
		xyzmanmessage.add(new messages(rs.getString(1), rs.getString(2)));
		System.out.println("added in list");
		}

	} catch (Exception e) {
		System.out.println("error" + e.getMessage());

	}

	
}

public ArrayList<messages> xyzmanmessage2=null;


public void tablemanmessage2()
{
	xyzmanmessage2=new ArrayList<messages>();
	String url1 = System.getenv("ICSI518_SERVER");
	String url2 = System.getenv("ICSI518_PORT");
	String url3 = System.getenv("ICSI518_DB");
	String use = System.getenv("ICSI518_USER");
	String passwrd = System.getenv("ICSI518_PASSWORD");
	String url = "jdbc:mysql://" + url1 +":"+url2+"/"+url3;
	Connection con = null;
	System.out.println("connected");


	try {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url, use, passwrd);		
		String sql = "select name,message from message where recieverid = ? and senderid = ?";
		System.out.println("conn");
		PreparedStatement ps = con.prepareStatement(sql);
		System.out.println("ps"+iduser);
		ps.setDouble(1, iduser);
		ps.setDouble(2, selectedman);
		
		
		ResultSet rs = ps.executeQuery();
		System.out.println("executed");
		while(rs.next()){  
		
		xyzmanmessage2.add(new messages(rs.getString(1), rs.getString(2)));
		System.out.println("added in list");
		}

	} catch (Exception e) {
		System.out.println("error" + e.getMessage());

	}

	
}

public ArrayList<messages> getXyzmanmessage2() {
	return xyzmanmessage2;
}

public void setXyzmanmessage2(ArrayList<messages> xyzmanmessage2) {
	this.xyzmanmessage2 = xyzmanmessage2;
}


public void deltempmessage()
{
	String url1 = System.getenv("ICSI518_SERVER");
	String url2 = System.getenv("ICSI518_PORT");
	String url3 = System.getenv("ICSI518_DB");
	String use = System.getenv("ICSI518_USER");
	String passwrd = System.getenv("ICSI518_PASSWORD");
	String url = "jdbc:mysql://" + url1 +":"+url2+"/"+url3;
	Connection con = null;
	System.out.println("connected");


	try {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url, use, passwrd);		
		String sql = "delete from tempmessage where recieverid = ? ";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setDouble(1, iduser);
		ps.executeUpdate();
	
		System.out.println("executed");
		
	} catch (Exception e) {
		System.out.println("error" + e.getMessage());

	}

	
}
private String n;
private String time;
public void  callapi()
{
System.out.println("Calling AlphaVantage API...");
Client client= ClientBuilder.newClient();

// Core settings are here, put what ever API parameter you want to use
WebTarget target= client.target("https://www.alphavantage.co/query")
   .queryParam("function", "TIME_SERIES_WEEKLY")
   .queryParam("symbol", "AAPL")
   .queryParam("apikey", "8LY5VJQYBZTYS262");
// Actually calling API here, Use HTTP GET method
// data is the response JSON string
String data = target.request(MediaType.APPLICATION_JSON).get(String.class);

try {
	// Use Jackson to read the JSON into a tree like structure
	ObjectMapper mapper = new ObjectMapper();
	JsonNode root = mapper.readTree(data);
	
	// Make sure the JSON is an object, as said in their documents
	assert root.isObject();
	// Read the "Meta Data" property of JSON object
	JsonNode metadata = root.get("Meta Data");
	assert metadata.isObject();
	// Read "2. Symbol" property of "Meta Data" property
	if (metadata.get("2. Symbol").isValueNode()) {
		System.out.println(metadata.get("2. Symbol").asText());
	}
	// Print "4. Time Zone" property of "Meta Data" property of root JSON object
	System.out.println(root.at("/Meta Data/4. Time Zone").asText());
	// Read "Weekly Time Series" property of root JSON object
	Iterator<String> dates = root.get("Weekly Time Series").fieldNames();
	while(dates.hasNext()) {
		// Read the first date's open price
		 n = root.at("/Weekly Time Series/" + dates.next() + "/1. open").asText();
		System.out.println(Double.parseDouble(n));
		// remove break if you wan't to print all the open prices.
		break;
	}
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
}

public String getN() {
	return n;
}

public void setN(String n) {
	this.n = n;
}

private Map<String,String> stocknames;
private Map<String,String> stockintervals;
public String selectedstock;
public String selectedinterval;
public Map<String, String> getStocknames() {
	return stocknames;
}

public void setStocknames(Map<String, String> stocknames) {
	this.stocknames = stocknames;
}

public Map<String, String> getStockintervals() {
	return stockintervals;
}

public void setStockintervals(Map<String, String> stockintervals) {
	this.stockintervals = stockintervals;
}

public String getSelectedstock() {
	return selectedstock;
}

public void setSelectedstock(String selectedstock) {
	this.selectedstock = selectedstock;
}

public String getSelectedinterval() {
	return selectedinterval;
}

public void setSelectedinterval(String selectedinterval) {
	this.selectedinterval = selectedinterval;
}

public void stockprice()
	{
	stocknames=new LinkedHashMap<String,String>();
	stockintervals=new LinkedHashMap<String,String>();
	stocknames.put( "Apple","AAPL");
	stocknames.put( "Amazon LLC","AMZN");
	stocknames.put( "Antero Resources","AR");
	stocknames.put( "Ebay","EBAY");
	stocknames.put( "Facebook, Inc.","FB");
	stocknames.put( "Gold","GOLD");
	stocknames.put( "Google","GOOGL");
	stocknames.put( "Microsoft","MSFT");
	stocknames.put( "Silver","SLV");
	stocknames.put( "Twitter, Inc.","TWTR");
	
	stockintervals.put("1min", "1min");
	stockintervals.put("5min", "5min");
	stockintervals.put("15min", "15min");
	stockintervals.put("30min", "30min");
	stockintervals.put("60min", "60min");
	
	this.sello();
	}
public ArrayList<stockprice> stockprice=null;

public ArrayList<stockprice> getStockprice() {
	return stockprice;
}

public void setStockprice(ArrayList<stockprice> stockprice) {
	this.stockprice = stockprice;
}

public void  callapi2()
{
System.out.println("Calling AlphaVantage API...");
Client client= ClientBuilder.newClient();
stockprice=new ArrayList<stockprice>();

//WebTarget target= client.target("https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol="+selectedstock+"&interval=1min"+"&apikey=8LY5VJQYBZTYS262");
  WebTarget target= client.target("https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol="+selectedstock+"&interval="+selectedinterval+"&apikey=8LY5VJQYBZTYS262");

String data = target.request(MediaType.APPLICATION_JSON).get(String.class);

try {	
	ObjectMapper mapper = new ObjectMapper();
	JsonNode root = mapper.readTree(data);
	assert root.isObject();
	JsonNode metadata = root.get("Meta Data");
	assert metadata.isObject();
	if (metadata.get("2. Symbol").isValueNode()) {
		System.out.println(metadata.get("2. Symbol").asText());
	}
	System.out.println(root.at("/Meta Data/6. Time Zone").asText());
	Iterator<String> dates = root.get("Time Series ("+selectedinterval+")").fieldNames();
	while(dates.hasNext()) {
		time = dates.next();
		 n = root.at("/Time Series ("+selectedinterval+")/" + dates.next() + "/1. open").asText();
		System.out.println(Double.parseDouble(n));
		stockprice.add(new stockprice(selectedstock,dates.next(),n));
		break;
	}
} catch (IOException e) {
	e.printStackTrace();
}
}

public void  callapi3()
{
System.out.println("Calling AlphaVantage API...");
Client client= ClientBuilder.newClient();
stockprice=new ArrayList<stockprice>();

//WebTarget target= client.target("https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol="+selectedstock+"&interval=1min"+"&apikey=8LY5VJQYBZTYS262");
  WebTarget target= client.target("https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol="+selectedstock+"&interval="+selectedinterval+"&apikey=8LY5VJQYBZTYS262");

String data = target.request(MediaType.APPLICATION_JSON).get(String.class);

try {	
	ObjectMapper mapper = new ObjectMapper();
	JsonNode root = mapper.readTree(data);
	assert root.isObject();
	JsonNode metadata = root.get("Meta Data");
	assert metadata.isObject();
	if (metadata.get("2. Symbol").isValueNode()) {
		System.out.println(metadata.get("2. Symbol").asText());
	}
	System.out.println(root.at("/Meta Data/6. Time Zone").asText());
	Iterator<String> dates = root.get("Time Series ("+selectedinterval+")").fieldNames();
	while(dates.hasNext()) {
		 n = root.at("/Time Series ("+selectedinterval+")/" + dates.next() + "/1. open").asText();
		System.out.println(Double.parseDouble(n));
		stockprice.add(new stockprice(selectedstock,dates.next(),n));
		
	}
} catch (IOException e) {
	e.printStackTrace();
}
}

private String quantity;
public String getQuantity() {
	return quantity;
}

public void setQuantity(String quantity) {
	this.quantity = quantity;
}

public void buy()
{
	this.callapi2();
	String url1 = System.getenv("ICSI518_SERVER");
	String url2 = System.getenv("ICSI518_PORT");
	String url3 = System.getenv("ICSI518_DB");
	String use = System.getenv("ICSI518_USER");
	String passwrd = System.getenv("ICSI518_PASSWORD");
	String url = "jdbc:mysql://" + url1 +":"+url2+"/"+url3;
	Connection con = null;
	System.out.println("connected");
	Double x = Math.random();;
	


	try {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url, use, passwrd);

		String sql = "insert into userstocks " + "(sno,iduserstocks,stockname,stockprice,currentprice,time,quantity)"
				+ "values (?,?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setDouble(1, x);
		ps.setDouble(2, iduser);
		ps.setString(3, selectedstock);
		ps.setString(4, n);
		ps.setString(5, n);
		ps.setString(6, time);
		ps.setString(7, quantity);
		
		

		ps.executeUpdate();
		this.updateaccount();

	} catch (Exception e) {
		System.out.println("error" + e.getMessage());

	}

	
}
Double balance;
public Double getBalance() {
	return balance;
}

public void setBalance(Double balance) {
	this.balance = balance;
}

public void getaccbalance()
{
	String url1 = System.getenv("ICSI518_SERVER");
	String url2 = System.getenv("ICSI518_PORT");
	String url3 = System.getenv("ICSI518_DB");
	String use = System.getenv("ICSI518_USER");
	String passwrd = System.getenv("ICSI518_PASSWORD");
	String url = "jdbc:mysql://" + url1 +":"+url2+"/"+url3;
	Connection con = null;
	System.out.println("connected");
	
	try {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url, use, passwrd);

		String sql = "select balance from useraccounts where iduseraccounts = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setDouble(1, iduser);
		
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			
			balance=rs.getDouble(1);
			System.out.println(balance);
			
		}
	} catch (Exception e) {
		System.out.println("error" + e.getMessage());

	}

}
	
public void updateaccount()
{
	this.getaccbalance();
	String url1 = System.getenv("ICSI518_SERVER");
	String url2 = System.getenv("ICSI518_PORT");
	String url3 = System.getenv("ICSI518_DB");
	String use = System.getenv("ICSI518_USER");
	String passwrd = System.getenv("ICSI518_PASSWORD");
	String url = "jdbc:mysql://" + url1 +":"+url2+"/"+url3;
	Connection con = null;
	System.out.println("connected");
	int q = Integer.parseInt(quantity);
	Double buyprice = Double.parseDouble(n);
	Double balance1 = balance - q*buyprice;
	System.out.println(q);
	System.out.println(buyprice);
	System.out.println(balance1);
	
	try {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url, use, passwrd);

		String sql = "update useraccounts " + "SET balance = ? WHERE iduseraccounts = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setDouble(1, balance1);
		ps.setDouble(2, iduser);
		
		ps.executeUpdate();
		System.out.println("inserted "+balance1);
					
		
	} catch (Exception e) {
		System.out.println("error" + e.getMessage());

	}

}



private Double sellstock;
public ArrayList<userstocks> userstockslist=null;
private Map<Integer,Double> numberslist;
public void sello()
{
	userstockslist=new ArrayList<userstocks>();
	numberslist=new LinkedHashMap<Integer,Double>();
	int i=0;
	String url1 = System.getenv("ICSI518_SERVER");
	String url2 = System.getenv("ICSI518_PORT");
	String url3 = System.getenv("ICSI518_DB");
	String use = System.getenv("ICSI518_USER");
	String passwrd = System.getenv("ICSI518_PASSWORD");
	String url = "jdbc:mysql://" + url1 +":"+url2+"/"+url3;
	Connection con = null;
	System.out.println("connected");


	try {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url, use, passwrd);		
		String sql = "select sno,stockname,time,stockprice,quantity from userstocks where iduserstocks = ? ";
		System.out.println("conn");
		PreparedStatement ps = con.prepareStatement(sql);
		System.out.println("ps"+iduser);
		ps.setDouble(1, iduser);
		
		
		ResultSet rs = ps.executeQuery();
		System.out.println("executed");
		while(rs.next()){  
			
			
			userstockslist.add(new userstocks(i,rs.getString(2), rs.getString(3),rs.getString(4),Integer.toString(rs.getInt(5)),Integer.toString(rs.getInt(5)),Integer.toString(rs.getInt(5))));
			numberslist.put(i,rs.getDouble(1) );
			System.out.println("numberslist values "+rs.getDouble(1));
			i++;
		System.out.println("added in list");
		}

	} catch (Exception e) {
		System.out.println("error" + e.getMessage());

	}

}

public Double getSellstock() {
	return sellstock;
}

public void setSellstock(Double sellstock) {
	this.sellstock = sellstock;
}

public ArrayList<userstocks> getUserstockslist() {
	return userstockslist;
}

public void setUserstockslist(ArrayList<userstocks> userstockslist) {
	this.userstockslist = userstockslist;
}

public Map<Integer, Double> getNumberslist() {
	return numberslist;
}

public void setNumberslist(Map<Integer, Double> numberslist) {
	this.numberslist = numberslist;
}

private String currentprice;
private String currentstock;
private int buyquantity;
private String buyprice;

public int getBuyquantity() {
	return buyquantity;
}

public void setBuyquantity(int buyquantity) {
	this.buyquantity = buyquantity;
}

public String getBuyprice() {
	return buyprice;
}

public void setBuyprice(String buyprice) {
	this.buyprice = buyprice;
}


public String getCurrentprice() {
	return currentprice;
}

public void setCurrentprice(String currentprice) {
	this.currentprice = currentprice;
}

public String getCurrentstock() {
	return currentstock;
}

public void setCurrentstock(String currentstock) {
	this.currentstock = currentstock;
}
public void  callapi4()
{
System.out.println("Calling AlphaVantage API...");
Client client= ClientBuilder.newClient();
//WebTarget target= client.target("https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol="+selectedstock+"&interval=1min"+"&apikey=8LY5VJQYBZTYS262");
  WebTarget target= client.target("https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol="+currentstock+"&interval=1min&apikey=8LY5VJQYBZTYS262");

String data = target.request(MediaType.APPLICATION_JSON).get(String.class);

try {	
	ObjectMapper mapper = new ObjectMapper();
	JsonNode root = mapper.readTree(data);
	assert root.isObject();
	JsonNode metadata = root.get("Meta Data");
	assert metadata.isObject();
	if (metadata.get("2. Symbol").isValueNode()) {
		System.out.println(metadata.get("2. Symbol").asText());
	}
	System.out.println(root.at("/Meta Data/6. Time Zone").asText());
	Iterator<String> dates = root.get("Time Series (1min)").fieldNames();
	while(dates.hasNext()) {
		time = dates.next();
		 currentprice = root.at("/Time Series (1min)/" + dates.next() + "/1. open").asText();
		System.out.println(Double.parseDouble(currentprice));
		break;
	}
} catch (IOException e) {
	e.printStackTrace();
}
}
public void sellit()
{
	
	String url1 = System.getenv("ICSI518_SERVER");
	String url2 = System.getenv("ICSI518_PORT");
	String url3 = System.getenv("ICSI518_DB");
	String use = System.getenv("ICSI518_USER");
	String passwrd = System.getenv("ICSI518_PASSWORD");
	String url = "jdbc:mysql://" + url1 +":"+url2+"/"+url3;
	Connection con = null;
	System.out.println("connected");
	


	try {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url, use, passwrd);		
		String sql = "select stockname,stockprice,quantity from userstocks where sno = ? ";
		System.out.println("conn");
		PreparedStatement ps = con.prepareStatement(sql);
		System.out.println("sellstock"+sellstock);
		ps.setDouble(1, sellstock);
		
		
		ResultSet rs = ps.executeQuery();
		System.out.println("executed callapi4");
		while(rs.next()){  
			this.currentstock = rs.getString(1);
			this.buyprice = rs.getString(2);
			this.buyquantity = rs.getInt(3);
			
		}
		this.callapi4();
		System.out.println(currentstock+buyprice+buyquantity+currentprice);
		this.updatevalues();
	} catch (Exception e) {
		System.out.println("error" + e.getMessage());

	}

	
}

public void updatevalues()
{
	String url1 = System.getenv("ICSI518_SERVER");
	String url2 = System.getenv("ICSI518_PORT");
	String url3 = System.getenv("ICSI518_DB");
	String use = System.getenv("ICSI518_USER");
	String passwrd = System.getenv("ICSI518_PASSWORD");
	String url = "jdbc:mysql://" + url1 +":"+url2+"/"+url3;
	Connection con = null;
	System.out.println("connected");
	


	try {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url, use, passwrd);		
		String sql = "delete from userstocks where sno = ? ";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setDouble(1, sellstock);
		ps.executeUpdate();
	
		System.out.println("executed");
		this.updateaccountbalance();
	} catch (Exception e) {
		System.out.println("error" + e.getMessage());

	}
}

public void updateaccountbalance()
{
	this.getaccbalance();
	String url1 = System.getenv("ICSI518_SERVER");
	String url2 = System.getenv("ICSI518_PORT");
	String url3 = System.getenv("ICSI518_DB");
	String use = System.getenv("ICSI518_USER");
	String passwrd = System.getenv("ICSI518_PASSWORD");
	String url = "jdbc:mysql://" + url1 +":"+url2+"/"+url3;
	Connection con = null;
	System.out.println(balance);
	int q = this.buyquantity;
	Double currentprice99 = Double.parseDouble(this.currentprice);
	Double balance99 = balance + q*(currentprice99);
	System.out.println(q);
	System.out.println(buyprice);
	System.out.println(balance99);
	
	try {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url, use, passwrd);

		String sql = "update useraccounts " + "SET balance = ? WHERE iduseraccounts = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setDouble(1, balance99);
		ps.setDouble(2, iduser);
		
		ps.executeUpdate();
		System.out.println("inserted "+balance99);
					
		
	} catch (Exception e) {
		System.out.println("error" + e.getMessage());

	}

}


public void getmanbalance()
{
	String url1 = System.getenv("ICSI518_SERVER");
	String url2 = System.getenv("ICSI518_PORT");
	String url3 = System.getenv("ICSI518_DB");
	String use = System.getenv("ICSI518_USER");
	String passwrd = System.getenv("ICSI518_PASSWORD");
	String url = "jdbc:mysql://" + url1 +":"+url2+"/"+url3;
	Connection con = null;
	System.out.println("connected");
	
	try {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(url, use, passwrd);

		String sql = "select balance from useraccounts where iduseraccounts = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setDouble(1, iduser);
		
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			
			BALANCE=rs.getDouble(1);
			System.out.println(BALANCE);
			
		}
	} catch (Exception e) {
		System.out.println("error" + e.getMessage());

	}

} 







}
