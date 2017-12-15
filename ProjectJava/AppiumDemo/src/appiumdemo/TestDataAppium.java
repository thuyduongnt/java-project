package appiumdemo;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

public class TestDataAppium {
	@DataProvider(name = "data")
	public Object[][] GetData(Method name) {
		Object[][] obj = new Object[1][1];
		
		if (name.getName().equals("testFullName")) {
			obj = new Object[2][2];

			obj[0][0] = "";
			obj[0][1] = "Mục này còn thiếu";

			obj[1][0] = "duongntt";
			obj[1][1] = "";
		}
		
		
		else if (name.getName().equals("validateEmail")) {
			obj = new Object[7][2];

			obj[0][0] = "";
			obj[0][1] = "Mục này còn thiếu";

			obj[1][0] = "thuyduonghp@";
			obj[1][1] = "Vui lòng nhập một email hợp lệ";

			obj[2][0] = "thuyduonghp@hotmail";
			obj[2][1] = "Vui lòng nhập một email hợp lệ";

			obj[3][0] = "thuyduonghp@hotmail.";
			obj[3][1] = "Vui lòng nhập một email hợp lệ";

			obj[4][0] = "thuyduonghp@hotmail.c";
			obj[4][1] = "Vui lòng nhập một email hợp lệ";

			obj[5][0] = "thuyduonghphotmail.com";
			obj[5][1] = "Vui lòng nhập một email hợp lệ";
			
			obj[6][0] = "thuyduonghp@hotmail.com";
			obj[6][1] = "";
		} 
		
		else if (name.getName().equals("validatePassword")) {
			obj = new Object[6][2];

			obj[0][0] = "";
			obj[0][1] = "Mục này còn thiếu";

			obj[1][0] = "123";
			obj[1][1] = "Mật khẩu phải chứa ít nhất 6 ký tự";

			obj[2][0] = "abc abc";
			obj[2][1] = "Mật khẩu phải chứa ít nhất một chữ và số.";

			obj[3][0] = "123 123";
			obj[3][1] = "Mật khẩu phải chứa ít nhất một chữ và số.";

			obj[4][0] = "123ab";
			obj[4][1] = "Mật khẩu phải chứa ít nhất 6 ký tự";

			obj[5][0] = "abc123   ";
			obj[5][1] = "";
		}
		
		//else if()
		

		return obj;
	}
}
