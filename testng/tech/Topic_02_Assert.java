package tech;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_02_Assert {

	@Test
	public void TC_01(){
		//Thư viện Assert : kiểm tra tính đúng đắn của dữ liệu
		//Mong đợ nó đúng/ sai/ đầu vào và đầu ra như nhau
		
		String addressCity = "Ha Noi";
		
		Assert.assertTrue(addressCity.equals("Ha Noi"));
		Assert.assertTrue(addressCity.contains("Ha"));
		Assert.assertTrue(addressCity.contains("Noi"));
		
		Assert.assertFalse(addressCity.contains("Ho Chi Minh"));
		
		Assert.assertEquals(addressCity, "Ha Noi");
		
		Object fullname = "quanganh";
		Assert.assertNotNull(fullname);
	}
	
}
