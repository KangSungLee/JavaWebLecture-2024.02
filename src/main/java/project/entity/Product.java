package project.entity;

public class Product {
	private int pid;
	private String category;
	private String pname;
	private int price;
	private String description;
	private String pimage;
	
	public Product() { }
	public Product(int pid, String category, String pname, int price, String description, String pimage) {
		this.pid = pid;
		this.category = category;
		this.pname = pname;
		this.price = price;
		this.description = description;
		this.pimage = pimage;
	}
	// 인서트용
	public Product(String category, String pname, int price, String description, String pimage) {
		this.category = category;
		this.pname = pname;
		this.price = price;
		this.description = description;
		this.pimage = pimage;
	}
	
	@Override
	public String toString() {
		return "Product [pid=" + pid + ", category=" + category + ", pname=" + pname + ", price=" + price
				+ ", description=" + description + ", pimage=" + pimage + "]";
	}
	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPimage() {
		return pimage;
	}
	public void setPimage(String pimage) {
		this.pimage = pimage;
	}
	
}
