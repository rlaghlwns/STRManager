package model;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;

public class Member implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String age;
	private String gender;
	private String address;
	private String phonenumber;
	private String email;
	private double weight;		// Body Weight
	private double bp1rm;		// Bench Press 1RM
	private double sq1rm;		// Squat 1RM
	private double dl1rm;		// Deadlift 1RM
	private double ohp1rm;		// Overhead Press 1RM
	private double pr1rm;		// Pendlay Row 1RM
	private ArrayList<String[]> wendlerRoutine;
	private ArrayList<String[]> strongliftRoutine;
	public ArrayList<String[]> getWendlerRoutine() {
		return wendlerRoutine;
	}
	public void setWendlerRoutine(ArrayList<String[]> wendlerRoutine) {
		this.wendlerRoutine = wendlerRoutine;
	}
	public ArrayList<String[]> getStrongliftRoutine() {
		return strongliftRoutine;
	}
	public void setStrongliftRoutine(ArrayList<String[]> strongliftRoutine) {
		this.strongliftRoutine = strongliftRoutine;
	}
	public Member(String name, String age, String gender, String address, String phonenumber, String email,
			double weight, double bp1rm, double sq1rm, double dl1rm, double ohp1rm, double pr1rm) {
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.address = address;
		this.phonenumber = phonenumber;
		this.email = email;
		this.weight = weight;
		this.bp1rm = bp1rm;
		this.sq1rm = sq1rm;
		this.dl1rm = dl1rm;
		this.ohp1rm = ohp1rm;
		this.pr1rm = pr1rm;
	}
	public SimpleStringProperty getNameSSP() {
		return new SimpleStringProperty(name);
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getPr1rm() {
		return pr1rm;
	}
	public void setPr1rm(double pr1rm) {
		this.pr1rm = pr1rm;
	}
	public double getOhp1rm() {
		return ohp1rm;
	}
	public void setOhp1rm(double ohp1rm) {
		this.ohp1rm = ohp1rm;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getBp1rm() {
		return bp1rm;
	}
	public void setBp1rm(double bp1rm) {
		this.bp1rm = bp1rm;
	}
	public double getSq1rm() {
		return sq1rm;
	}
	public void setSq1rm(double sq1rm) {
		this.sq1rm = sq1rm;
	}
	public double getDl1rm() {
		return dl1rm;
	}
	public void setDl1rm(double dl1rm) {
		this.dl1rm = dl1rm;
	}
}
