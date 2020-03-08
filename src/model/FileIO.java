package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@SuppressWarnings("serial")
public class FileIO implements Serializable{
	public ArrayList<Member> list;
	public ObservableList<Member> oblist;
	public void savedata() {
		try {
			File f = new File("C:\\Users\\Public\\Member.dat"); 
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(list);
			oos.close();
		} catch (Exception e) {}
	}
	public void savedata(File file) {
		try {
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(list);
			oos.close();
		} catch (Exception e) {}
	}
	@SuppressWarnings("unchecked")
	public void loaddata() {
		try {
			File f = new File("C:\\Users\\Public\\Member.dat"); 
			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(fis);
			list = (ArrayList<Member>)ois.readObject();
			ois.close();
		} catch (Exception e) {}
	}
	@SuppressWarnings("unchecked")
	public void loaddata(File file) {
		try {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			list = (ArrayList<Member>)ois.readObject();
			ois.close();
		} catch (Exception e) {}
	}
	public void initModel() {
		oblist = FXCollections.observableArrayList();
		for(int i = 0 ; i < list.size() ; i++) {
			oblist.add(list.get(i));
		}
	}
}

