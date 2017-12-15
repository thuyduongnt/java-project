package dataprovider;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

public class TestData {

	@DataProvider(name = "data")
	public Object[][] GetData(Method name) {
		Object[][] obj = new Object[1][1];

		if (name.getName().equals("ValidateEmail")) {
			obj = new Object[6][2];

			obj[0][0] = "khanh.tx";
			obj[0][1] = "Email không là một email hợp lệ";

			obj[1][0] = "khanh.tx@";
			obj[1][1] = "Email không là một email hợp lệ";

			obj[2][0] = "khanh.tx@gmail";
			obj[2][1] = "Email không là một email hợp lệ";

			obj[3][0] = "khanh.tx@gmail.";
			obj[3][1] = "Email không là một email hợp lệ";

			obj[4][0] = "khanh.tx@gmail.c";
			obj[4][1] = "Email không là một email hợp lệ";

			obj[5][0] = "khanh.txlive.com";
			obj[5][1] = "Email không là một email hợp lệ";
		} 
		else if (name.getName().equals("ValidatePassword")) {
			obj = new Object[6][2];

			obj[0][0] = "";
			obj[0][1] = "Mật khẩu phải có ít nhất 1 chữ số";

			obj[1][0] = "123";
			obj[1][1] = "Mật khẩu phải có ít nhất 01 chữ số (hoặc kí tự chữ)";

			obj[2][0] = "abc abc";
			obj[2][1] = "Mật khẩu phải có ít nhất 1 chữ số";

			obj[3][0] = "123 123";
			obj[3][1] = "Mật khẩu phải có ít nhất 01 chữ số (hoặc kí tự chữ)";

			obj[4][0] = "123ab";
			obj[4][1] = "Mật khẩu quá ngắn (tối thiểu phải 6 kí tự)";

			obj[5][0] = "abc123   ";
			obj[5][1] = "";
		}
		else if(name.getName().equals("testLogin")) {
			obj = new Object[6][4];
			
			obj[0][0] = "";
			obj[0][1] = "Thông tin bắt buộc";
			obj[0][2] = "";
			obj[0][3] = "Thông tin bắt buộc";
			
			obj[1][0] = "sstdnguyen@gmail";
			obj[1][1] = "Địa chỉ mail không đúng";
			obj[1][2] = "";
			obj[1][3] = "Thông tin bắt buộc";
			
			obj[2][0] = "sstdnguyen@gmail.com";
			obj[2][1] = "";
			obj[2][2] = "";
			obj[2][3] = "Thông tin bắt buộc";
			
			obj[3][0] = "sstdnguyen@gmail.com";
			obj[3][1] = "";
			obj[3][2] = "123";
			obj[3][3] = "Tên đăng nhập hoặc mật khẩu không hợp lệ";
			
			obj[4][0] = "thuyduonghp@hotmail.com";
			obj[4][1] = "";
			obj[4][2] = "123456";
			obj[4][3] = "Tên đăng nhập hoặc mật khẩu không hợp lệ";

			obj[5][0] = "sstdnguyen@gmail.com";
			obj[5][1] = "";
			obj[5][2] = "Shinobi#12";
			obj[5][3] = "";
		}

		return obj;
	}
}
