package mistock;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class stockprice {
	private String name;
	private String time;
	private String price;
	
	
	

	public stockprice(String name, String time, String price) {
		super();
		this.name = name;
		this.time = time;
		this.price = price;
	}
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "stockprice [name=" + name + ", time=" + time + ", price=" + price + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
