package lazada;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

public class TestData {
	
	@DataProvider(name="dataHomepage")
	public Object[][] GetDataHomepage(Method name){
		Object[][] obj = new Object[1][1];
		
		if(name.getName().equals("TestLinkTopNavigate")) {
			obj = new Object[3][2];
			
			obj[0][0] = "Bán hàng cùng Lazada";
			obj[0][1] = "Đăng Ký Bán Hàng Trên Lazada MIỄN PHÍ | Lazada.vn";
			
			obj[1][0] = "Đăng nhập";
			obj[1][1] = "Lazada - Login";
			
			obj[2][0] = "Đăng ký";
			obj[2][1] = "Lazada - Create new customer account";
		}
		else if(name.getName().equals("CheckDisplayPopup")) {
			obj = new Object[3][1];
			
			obj[0][0] = "Chăm sóc khách hàng";
			obj[1][0] = "Kiểm tra đơn hàng";
			obj[2][0] = "Change language";
		}
		else if(name.getName().equals("CheckItemCustomerCare")) {
			obj = new Object[5][2];
			
			obj[0][0] = "Trung tâm hỗ trợ";
			obj[0][1] = "Hãy liên hệ ngay Helpcenter để được trợ giúp đỡ tại Lazada";

			obj[1][0] = "Đơn hàng & Thanh toán";
			obj[1][1] = "Trung tâm hỗ trợ | Đơn hàng, Thanh toán | Lazada Vietnam";

			obj[2][0] = "Giao hàng & Nhận hàng";
			obj[2][1] = "Trung tâm hỗ trợ | Giao hàng & Nhận hàng | Lazada Vietnam";

			obj[3][0] = "Đổi trả hàng & Hoàn tiền";
			obj[3][1] = "Trung tâm hỗ trợ | Đổi trả hàng & Hoàn tiền | Lazada Vietnam";

			obj[4][0] = "Tuyệt chiêu mua sắm";
			obj[4][1] = "Tuyệt chiêu mua sắm trên Lazada.vn";
		}
		else if(name.getName().equals("CheckTrachMyOrder")) {
			obj = new Object[3][3];
			
			obj[0][0] = "";
			obj[0][1] = "";
			obj[0][2] = "Vui lòng điền đầy đủ thông tin";
			
			obj[1][0] = "test2.duongntt";
			obj[1][1] = "12345";
			obj[1][2] = "Xin vui lòng điền thông tin chính xác";
			
			obj[2][0] = "test2.duongntt@gmail.com";
			obj[2][1] = "123456789";
			obj[2][2] = "Không có đơn hàng liên quan đến địa chỉ email này, vui lòng kiểm tra lại email và mã đơn hàng của bạn.";
		}
		
		return obj;
	}
	
	//Data - login function
	@DataProvider(name="dataLogin")
	public Object[][] GetDataLogin(Method name) {
		Object[][] obj = new Object[1][1];
		
		if(name.getName().equals("LoginForm")) {
			obj = new Object[8][4];
			
			obj[0][0] = "";
			obj[0][1] = "Thông tin bắt buộc";
			obj[0][2] = "";
			obj[0][3] = "Thông tin bắt buộc";
			
			obj[1][0] = "test.duongntt@gmail";
			obj[1][1] = "Địa chỉ mail không đúng";
			obj[1][2] = "";
			obj[1][3] = "Thông tin bắt buộc";
			
			obj[2][0] = "test.duongntt@gmail.com";
			obj[2][1] = "";
			obj[2][2] = "";
			obj[2][3] = "Thông tin bắt buộc";
			
			obj[3][0] = "test.duongntt@gmail.com";
			obj[3][1] = "";
			obj[3][2] = "123";
			obj[3][3] = "Tên đăng nhập hoặc mật khẩu không hợp lệ";
			
			obj[4][0] = "test.duongntt@hotmail.com";
			obj[4][1] = "";
			obj[4][2] = "Test#123456";
			obj[4][3] = "Tên đăng nhập hoặc mật khẩu không hợp lệ";
			
			obj[5][0] = "test3.duongntt@gmail.com";
			obj[5][1] = "";
			obj[5][2] = "    Test#123456       ";
			obj[5][3] = "Tên đăng nhập hoặc mật khẩu không hợp lệ";
			
			obj[6][0] = "test3.duongntt@gmail.com";
			obj[6][1] = "";
			obj[6][2] = "Select Password from User where Email='test3.duongntt@gmail.com'";
			obj[6][3] = "Tên đăng nhập hoặc mật khẩu không hợp lệ";

			obj[7][0] = "test3.duongntt@gmail.com";
			obj[7][1] = "";
			obj[7][2] = "Test#123456";
			obj[7][3] = "";
		}
		else if(name.getName().equals("CheckLink")) {
			obj = new Object[2][2];
			
			obj[0][0] = "Quên mật khẩu?";
			obj[0][1] = "Lazada - Forgot password?";
			
			obj[1][0] = "Đăng kí ngay!";
			obj[1][1] = "Lazada - Create new customer account";
		}
		else if(name.getName().equals("LoginWithGoogle")) {
			obj = new Object[1][2];
			
			obj[0][0] = "test2.duongntt@gmail.com";
			obj[0][1] = "Test#123456";
		}
		else if(name.getName().equals("LoginWithFacebook")) {
			obj = new Object[1][2];
			
			obj[0][0] = "test2.duongntt@gmail.com";
			obj[0][1] = "Test#123456";
		}

		return obj;
	}
	
	//Data - register function
	@DataProvider(name="dataRegister")
	public Object[][] GetDataRegister(Method name){
		Object[][] obj = new Object[1][1];
		
		if(name.getName().equals("CheckPolicyLink")) {
			obj = new Object[1][2];
			
			obj[0][0] = "chính sách";
			obj[0][1] = "Mua hàng trên Lazada - Chính sách chung | Lazada.vn";
		}
		else if(name.getName().equals("CheckRequireField")) {
			obj = new Object[4][2];
			
			obj[0][0] = "Email";
			obj[0][1] = "Thông tin bắt buộc";
			
			obj[1][0] = "Tên";
			obj[1][1] = "Thông tin bắt buộc";
			
			obj[2][0] = "Mật khẩu";
			obj[2][1] = "Mật khẩu phải có ít nhất 1 chữ số";
			
			obj[3][0] = "Nhập lại mật khẩu";
			obj[3][1] = "Mật khẩu phải có ít nhất 1 chữ số";
		}
		else if (name.getName().equals("CheckEmailFormat")) {
			obj = new Object[6][2];

			obj[0][0] = "test3.duongntt";
			obj[0][1] = "Email không là một email hợp lệ";

			obj[1][0] = "test3.duongntt@";
			obj[1][1] = "Email không là một email hợp lệ";

			obj[2][0] = "test3.duongntt@gmail";
			obj[2][1] = "Email không là một email hợp lệ";

			obj[3][0] = "test3.duongntt@gmail.";
			obj[3][1] = "Email không là một email hợp lệ";

			obj[4][0] = "test3.duongntt@gmail.c";
			obj[4][1] = "Email không là một email hợp lệ";

			obj[5][0] = "test3.duongnttgmail.com";
			obj[5][1] = "Email không là một email hợp lệ";
		} 
		else if (name.getName().equals("CheckPasswordFormat")) {
			obj = new Object[5][3];

			obj[0][0] = "123";
			obj[0][1] = "Mật khẩu phải có ít nhất 01 chữ số (hoặc kí tự chữ)";
			obj[0][2] = "Mật khẩu phải có ít nhất 01 chữ số (hoặc kí tự chữ)";

			obj[1][0] = "abc abc";
			obj[1][1] = "Mật khẩu phải có ít nhất 1 chữ số";
			obj[1][2] = "Mật khẩu phải có ít nhất 1 chữ số";

			obj[2][0] = "123 123";
			obj[2][1] = "Mật khẩu phải có ít nhất 01 chữ số (hoặc kí tự chữ)";
			obj[2][2] = "Mật khẩu phải có ít nhất 01 chữ số (hoặc kí tự chữ)";

			obj[3][0] = "123ab";
			obj[3][1] = "Mật khẩu quá ngắn (tối thiểu là 6 kí tự).";
			obj[3][2] = "Nhập lại mật khẩu quá ngắn (tối thiểu là 6 kí tự).";
			
			obj[4][0] = "  123abc   ";
			obj[4][1] = "";
			obj[4][2] = "";
		}
		else if(name.getName().equals("RegisterForm")) {
			obj = new Object[5][8];
			//bo trong cac truong
			obj[0][0] = "";
			obj[0][1] = "Thông tin bắt buộc";
			obj[0][2] = "";
			obj[0][3] = "Thông tin bắt buộc";
			obj[0][4] = "";
			obj[0][5] = "Mật khẩu phải có ít nhất 1 chữ số";
			obj[0][6] = "";
			obj[0][7] = "Mật khẩu phải có ít nhất 1 chữ số";
			//email da dang ky
			obj[1][0] = "sstdnguyen@gmail.com";
			obj[1][1] = "'sstdnguyen@gmail.com' đã tồn tại trong hệ thống";
			obj[1][2] = "duongntt";
			obj[1][3] = "";
			obj[1][4] = "test123";
			obj[1][5] = "";
			obj[1][6] = "test123";
			obj[1][7] = "";
			//nhap do dai ten >50 ky tu
			obj[2][0] = "duongtest1@gmail.com";
			obj[2][1] = "";
			obj[2][2] = "aaaaaaaaaabbbbbbbbbbccccccccccddddddddddeeeeeeeeeefff";
			obj[2][3] = "";
			obj[2][4] = "test123";
			obj[2][5] = "";
			obj[2][6] = "test123";
			obj[2][7] = "";
			//nhap mat khau khong khop nhau
			obj[3][0] = "test321@gmail.com";
			obj[3][1] = "";
			obj[3][2] = "duongntt";
			obj[3][3] = "";
			obj[3][4] = "test123";
			obj[3][5] = "Mật khẩu không khớp";
			obj[3][6] = "test12";
			obj[3][7] = "Mật khẩu không khớp";
			//nhap cac truong hop le
			
			obj[4][0] = "testduong1@gmail.com";
			obj[4][1] = "";
			obj[4][2] = "duongntt";
			obj[4][3] = "";
			obj[4][4] = "test123";
			obj[4][5] = "";
			obj[4][6] = "test123";
			obj[4][7] = "";
			
		}
		else if(name.getName().equals("RegisterWithFacebook")) {
			obj = new Object[1][2];
			
			obj[0][0] = "test2.duongntt@gmail.com";
			obj[0][1] = "Test#123456";
		}
		else if(name.getName().equals("RegisterWithGoogle")) {
			obj = new Object[1][2];
			
			obj[0][0] = "test2.duongntt@gmail.com";
			obj[0][1] = "Test#123456";
		}
		
		return obj;
	}
	
	@DataProvider(name="dataForgotPassword")
	public Object[][] GetDataForgotPassword(Method name){
		Object[][] obj = new Object[1][1];
		
		if(name.getName().equals("CheckGoBackShopping")) {
			obj = new Object[1][2];
			
			obj[0][0] = "Quay lại mua sắm";
			obj[0][1] = "Lazada - Login";
		}
		else if(name.getName().equals("CheckEmailFormat")) {
			obj = new Object[6][2];
			
			obj[0][0] = "test.duongntt";
			obj[0][1] = "Vui lòng nhập một email hợp lệ";

			obj[1][0] = "test.duongntt@";
			obj[1][1] = "Vui lòng nhập một email hợp lệ";

			obj[2][0] = "test.duongntt@gmail";
			obj[2][1] = "Vui lòng nhập một email hợp lệ";

			obj[3][0] = "test.duongntt@gmail.";
			obj[3][1] = "Vui lòng nhập một email hợp lệ";

			obj[4][0] = "test.duongntt@gmail.c";
			obj[4][1] = "Vui lòng nhập một email hợp lệ";

			obj[5][0] = "test.duongnttgmail.com";
			obj[5][1] = "Vui lòng nhập một email hợp lệ";
		}
		else if(name.getName().equals("FillEmail")) {
			obj = new Object[3][2];
			
			obj[0][0] = "";
			obj[0][1] = "Vui lòng nhập một email hợp lệ";
			
			obj[1][0] = "duongtestabc@gmail.com";
			obj[1][1] = "Email không tồn tại trong hệ thống";
			
			obj[2][0] = "test2.duongntt@gmail.com";
			obj[2][1] = "";
			
		}
		else if(name.getName().equals("CheckReceivedEmail")) {
			obj = new Object[1][2];
			
			obj[0][0] = "test2.duongntt@gmail.com";
			obj[0][1] = "Test#123456";
		}
		
		return obj;
	}
	
}
