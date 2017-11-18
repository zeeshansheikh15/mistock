package mistock;

public class messages {
	
	public messages(String name, String message) {
		super();
		this.name = name;
		this.message = message;
	}
	private String name;
	private String message;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "messages [name=" + name + ", message=" + message + "]";
	}

}
