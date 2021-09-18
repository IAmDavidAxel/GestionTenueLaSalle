package domain.uniform;

public class Uniform {

	private String model;
	private String type;
	private String sous_type;
	private float price;
	private  String size;

	public Uniform(){

	}

	public Uniform(String model, String type, String sous_type, float price, String size) {
		this.model = model;
		this.type = type;
		this.sous_type = sous_type;
		this.price = price;
		this.size = size;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSous_type() {
		return sous_type;
	}

	public void setSous_type(String sous_type) {
		this.sous_type = sous_type;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
}
