package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class CreateMemberDefault {
	FileIO fio = new FileIO();
	public CreateMemberDefault() {
		try {
			Member member = new Member("name", "30", "Male", "address", "8210-nnnn-nnnn", 
					"email@mail.com", 100, 110, 130, 160, 80, 100);
			File f = new File("Member.dat");
			ArrayList<Member> memberList = new ArrayList<Member>();
			memberList.add(member);
			fio.list = memberList;
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(fio.list);
			oos.close();
		} catch (Exception e) {}
	}
}
