package domain.uniform;

public class UniformStock {
	private String type;
	private String model;
	private float prix;
	private int nombre;
	private String taille;

	public UniformStock(){

	}

	public UniformStock(String type, String model, String taille,float prix, int nombre) {
		this.type = type;
		this.model = model;
		this.prix = prix;
		this.nombre = nombre;
		this.taille = taille;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public int getNombre() {
		return nombre;
	}

	public void setNombre(int nombre) {
		this.nombre = nombre;
	}

	public String getTaille() {
		return taille;
	}

	public void setTaille(String taille) {
		this.taille = taille;
	}
}
