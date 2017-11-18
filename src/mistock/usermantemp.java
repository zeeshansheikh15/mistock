package mistock;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class usermantemp {
	private int sno;
	private String name;
	private Double mobile;
	
	
	

	public usermantemp(int sno, String name, Double mobile) {
		super();
		this.sno = sno;
		this.name = name;
		this.mobile = mobile;
		
	}
	@Override
	public String toString() {
		return "usermantemp [sno=" + sno + ", name=" + name + ", mobile=" + mobile + "]";
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
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public void setMobile(Double mobile) {
		this.mobile = mobile;
	}
	
	

}
