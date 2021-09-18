package domain.transaction;

import java.time.Instant;
import java.util.Date;

public class Transaction {

	private Instant createdAt;
	private float transactionAmount;
	private String studentLastName;
	private String studentFirstName;
	private String studentClassroom;
	private String modelTenue;
	private String taille;
	private String type;

	public Transaction( ) {
		this.createdAt = Instant.now();
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public float getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(float transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getStudentLastName() {
		return studentLastName;
	}

	public void setStudentLastName(String studentLastName) {
		this.studentLastName = studentLastName;
	}

	public String getStudentFirstName() {
		return studentFirstName;
	}

	public void setStudentFirstName(String studentFirstName) {
		this.studentFirstName = studentFirstName;
	}

	public String getStudentClassroom() {
		return studentClassroom;
	}

	public void setStudentClassroom(String studentClassroom) {
		this.studentClassroom = studentClassroom;
	}


	public void setModelTenue(String modelTenue) {
		this.modelTenue = modelTenue;
	}

	public String getModelTenue() {
		return modelTenue;
	}

	public void setTaille(String taille) {
		this.taille = taille;
	}

	public String getTaille() {
		return taille;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
