package auto.framework.appium;

//package common.methods;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.android.AndroidDriver;
//import utilities.functions.Screenshots;


public class MobileWebActions {
	AndroidDriver<WebElement> driver;
	protected static WebDriverWait wait;
	protected static Actions action;
	Dimension screen;

	public MobileWebActions(AndroidDriver<WebElement> driver){
		this.driver = driver;
		action = new Actions(driver);
		wait = new WebDriverWait(driver,100);
		screen = driver.manage().window().getSize();
//		Screenshots.initialize(driver);
	}

	//Open Website
	public void openSite(String website){
		driver.get(website);
	}

	//Find Object by Name
	public void selectObjByName(String objName){
		driver.findElement(By.name(objName)).click();
	}

	//Find Object by ClassName
	public void selectObjByClassName(String objName){
		driver.findElement(By.className(objName)).click();
	}

	//Find Object by Id
	public void selectObjById(String objID){
		driver.findElement(By.id(objID)).click();
	}

	//Find Object by Xpath:Resource-Id
	public void selectByResId(String objClass, String objID){
		driver.findElement(By.xpath("//"+objClass+"[contains(@resource-id,'"+objID+"')]")).click();
	}

	//Find Object by Xpath:Content Description
	public void selectByContDesc(String objClass, String contentDescription){
		driver.findElement(By.xpath("//"+objClass+"[contains(@content-desc,'"+contentDescription+"')]")).click();
	}

	//Input Text by Name
	public void inputtextByName(String objName, String inputText){
		driver.findElement(By.name(objName)).sendKeys(inputText);
	}

	//Input Text by ClassName
	public void inputtextByClassName(String objName, String inputText){
		driver.findElement(By.className(objName)).sendKeys(inputText);
	}

	//Input Text by Id
	public void inputtextById(String objID, String inputText){
		driver.findElement(By.id(objID)).sendKeys(inputText);
	}
		
	//Input Text by Xpath:Resource-ID
	public void inputtextByResId(String objClass, String objID, String inputText){
		driver.findElement(By.xpath("//"+objClass+"[contains(@resource-id,'"+objID+"')]")).sendKeys(inputText);
	}

	//Input Text by Xpath:Resource-ID
	public void inputtextByContDesc(String objClass, String contentDescription, String inputText){
		driver.findElement(By.xpath("//"+objClass+"[contains(@content-desc,'"+contentDescription+"')]")).sendKeys(inputText);
	}
	
}
