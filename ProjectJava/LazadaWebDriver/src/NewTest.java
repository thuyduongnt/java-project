import org.testng.*;
import org.testng.annotations.*;

public class NewTest {
  @Parameters({"username"})
  @Test(groups= {"V1"})
  public void Test1(String username) {
	  Assert.assertEquals(username, "duongntt");
  }
  
  
  @Test(groups= {"V2"})
  public void Test2() {
	  Assert.assertTrue(false);
  }
  
  @Test(groups= {"V1"})
  public void Test3() {
	  Assert.assertTrue(false);
  }
  
  @BeforeTest
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() {
  }

}
