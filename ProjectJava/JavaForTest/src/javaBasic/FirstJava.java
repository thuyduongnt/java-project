package javaBasic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FirstJava {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Student> lstStudent = new ArrayList();
		Random rdm = new Random();
		for(int i=0; i<10; i++){
			Student obj = new Student();
			obj.Name = "Học sinh thứ:" + i;
			obj.Mark = rdm.nextInt(10);
		}
		Boolean Existed = false;
		//int count = 0;
		for(Student s: lstStudent){
			if(s.Name.indexOf("Hùng") != -1){
				Existed = true;
				break;
				//count ++;
			}
		}
		//Kiểm tra
		if(Existed == true){
			System.out.println("Trong lớp có người tên Hùng");
		}
		
		//Sắp xếp
		List<Student> orderedList = new ArrayList();
		for(int i=0; i<10; i++){
			Student currentStudent = lstStudent.get(i);
			for(int j=i+1; j< lstStudent.size(); j++){
				Student cmpStudent = lstStudent.get(j);
				if(cmpStudent.Mark < currentStudent.Mark)
				{
					Student tmp = new Student();
					tmp = currentStudent;
					currentStudent = cmpStudent;
					cmpStudent = tmp;
				}
			}
		}
		/*String name = "Nguyễn Xuân Hùng";
		String[] arrNamePart = name.split(" ");
		String FirstName = arrNamePart[arrNamePart.length-1];*/
	}

}
