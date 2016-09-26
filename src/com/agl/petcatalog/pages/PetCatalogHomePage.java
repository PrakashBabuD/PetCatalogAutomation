package com.agl.petcatalog.pages;


import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.agl.petcatalog.utils.DatabaseUtils;
import com.agl.petcatalog.utils.ElementFormatter;
import com.agl.petcatalog.utils.SeleniumUtils;
import com.agl.petcatalog.utils.TestNGUtils;


public class PetCatalogHomePage {
	public static  ElementFormatter PETCATALOG_HOME_PAGE_TITLE = new ElementFormatter("petcatalog-home-page-title","//a[@class='navbar-brandd']",ElementFormatter.XPATH);
	public static  ElementFormatter PETCATALOG_HOME_PAGE_MALE_PANEL = new ElementFormatter("petcatalog-home-page-male-panel","//pet-list[@gender-type='Male']",ElementFormatter.XPATH);
	public static  ElementFormatter PETCATALOG_HOME_PAGE_CAT_PET1 = new ElementFormatter("petcatalog-home-page-pet-cat1","//pet-list[@gender-type='Male']//li[ text()='Garfield']",ElementFormatter.XPATH);
	public static  ElementFormatter PETCATALOG_HOME_PAGE_CAT_PET2 = new ElementFormatter("petcatalog-home-page-pet-cat2","//pet-list[@gender-type='Male']//li[ text()='Jim']",ElementFormatter.XPATH);
	

	public static  ElementFormatter PETCATALOG_HOME_PAGE_FEMALE_PANEL = new ElementFormatter("petcatalog-home-page-female-panel","//pet-list[@gender-type='Female']",ElementFormatter.XPATH);



	private  WebDriver driver ;

	public PetCatalogHomePage(WebDriver driver) {
		this.driver=driver;
	}
	
	public PetCatalogHomePage validatePetCatalogHomePage(){

		SeleniumUtils.waitForElement(driver, PETCATALOG_HOME_PAGE_MALE_PANEL);
		return this;
	}
	
public PetCatalogHomePage validateMaleOwnerPetsLoaded(){
	   TestNGUtils.reportLog("Validating whether pets of the male owner are loaded ");
		TestNGUtils.reportLog("Validating whether pets of the male owner are loaded ");
		SeleniumUtils.waitForElement(driver, PETCATALOG_HOME_PAGE_MALE_PANEL);
		SeleniumUtils.waitForElement(driver, PETCATALOG_HOME_PAGE_CAT_PET1);
		SeleniumUtils.waitForElement(driver, PETCATALOG_HOME_PAGE_CAT_PET2);
		
		return this;
	}
	public PetCatalogHomePage validateFeMaleOwnerPetsLoaded(){
		TestNGUtils.reportLog("Validating whether pets of the female owner are loaded ");
		SeleniumUtils.waitForElement(driver, PETCATALOG_HOME_PAGE_FEMALE_PANEL);
		return this;
	}
	

}
