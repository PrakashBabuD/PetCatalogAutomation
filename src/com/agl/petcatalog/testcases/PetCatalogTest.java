
package com.agl.petcatalog.testcases;

import com.agl.petcatalog.pages.PetCatalogHomePage;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.agl.petcatalog.utils.TestInitializeUtils;



public class PetCatalogTest {
	@Test (groups={"Pet_Catalog_Test_1","Pet_Catalog_Testcases","PetCatalog_Regression_Testcases"})
	public void Pet_Catalog_Test_1() throws Exception{
		/*
		Test Case		:	Pet_Catalog_Test_1
		Test Module		:	Pet_Catalog_Testcases
		Description    	:	To validate whether the Pet Catalog Page is loaded 
		Author 			:   Prakash
		Date			:   24-Sep-16
		 */
		String testCaseName = new Exception().getStackTrace()[0].getMethodName();
		TestInitializeUtils testStart= new TestInitializeUtils();
		WebDriver driver=testStart.initializeDriver();
		try{
			PetCatalogHomePage petCalalogHomePage=new PetCatalogHomePage(driver);
			petCalalogHomePage
				.validatePetCatalogHomePage()
			    .validateMaleOwnerPetsLoaded()
			    .validateFeMaleOwnerPetsLoaded();
				
	
			testStart.closeDriver(driver);
			TestInitializeUtils.onTestPass(testCaseName);
		}
		catch(Exception e){
			TestInitializeUtils.onTestFailureScreenshot(driver, testCaseName,e);
			testStart.closeDriver(driver);
			throw new Exception(e.getMessage());
		}
	}


}
