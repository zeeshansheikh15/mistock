package mistock;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class userstocks {
	private int sno;
	private String name;
	private String time;
	private String price;
	private String currentprice;
	private String quantity;
	private String profit;
	
	
	

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public String getCurrentprice() {
		return currentprice;
	}

	public void setCurrentprice(String currentprice) {
		this.currentprice = currentprice;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getProfit() {
		return profit;
	}

	public void setProfit(String profit) {
		this.profit = profit;
	}

	public userstocks(int sno, String name, String time, String price, String currentprice, String quantity,
			String profit) {
		super();
		this.sno = sno;
		this.name = name;
		this.time = time;
		this.price = price;
		this.currentprice = currentprice;
		this.quantity = quantity;
		this.profit = profit;
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
		return "userstocks [sno=" + sno + ", name=" + name + ", time=" + time + ", price=" + price + ", currentprice="
				+ currentprice + ", quantity=" + quantity + ", profit=" + profit + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
