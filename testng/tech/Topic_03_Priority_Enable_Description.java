package tech;

import org.testng.annotations.Test;

public class Topic_03_Priority_Enable_Description {

	@Test(enabled = true)
	public void Order_01_View_Product() {
		
	}
	
	@Test(enabled = false)
	public void Order_02_Add_To_Cart() {
		
	}
	
	@Test(enabled = true, description = "Payment by Visa, Napas ...")
	public void Order_03_Add_Payment_Method() {
		
	}
	
	@Test(enabled = true)
	public void Order_04_Checkout() {
		
	}
	
}
