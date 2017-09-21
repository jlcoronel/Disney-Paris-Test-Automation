package auto.framework.appium;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
//import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import auto.framework.web.Element;
//import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
//import utilities.functions.Screenshots;

public class MobileAppActions {
	AndroidDriver<WebElement> driver;
	protected static WebDriverWait wait;
	protected static Actions action;
	Dimension screen;

	public MobileAppActions(AndroidDriver<WebElement> driver){
		this.driver = driver;
		action = new Actions(driver);
		wait = new WebDriverWait(driver,100);
		screen = driver.manage().window().getSize();
//		Screenshots.initialize(driver);
	}

	public void selectObjByName(String objName){
		driver.findElement(By.name(objName)).click();

	}

	public void selectByXpath(String objClass, String objID){
		driver.findElement(By.xpath("//"+objClass+"[contains(@resource-id,'"+objID+"')]")).click();

	}
	public void selectByXpathContentDescription(String objClass, String contentDescription){
		driver.findElement(By.xpath("//"+objClass+"[contains(@content-desc,'"+contentDescription+"')]")).click();

	}

	public void inputtextByXpath(String objClass, String objID, String inputText){
		driver.findElement(By.xpath("//"+objClass+"[contains(@resource-id,'"+objID+"')]")).sendKeys(inputText);

	}

	public void scrollToObjName (String objName){
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+objName+"\").instance(0))");
	}

	public void waitUntilObjisLoaded(String objClass, String objID){
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//"+objClass+"[contains(@resource-id,'"+objID+"')]")));
	}
	public void waitTime(){
		try{
			Thread.sleep(2000);
		}catch(InterruptedException e){
			System.out.println("Interrupted: " + e.getLocalizedMessage());
		}
	}
	public void waitTime(int seconds){
		seconds *= 1000;
		try{
			Thread.sleep(seconds);
		}catch(InterruptedException e){
			System.out.println("Interrupted: " + e.getLocalizedMessage());
		}
	}

	public void showSideTray(){
		try{
//			TouchAction touch = new TouchAction(driver);
//			touch.tap(12, 99).perform();
			driver.findElement(By.xpath("//android.widget.ImageView[contains(@resource-id,'up')]")).click();

			System.out.println("SIDE TRAY IS DISPLAYED");
		}catch(Exception e){
//			Screenshots.take("FAIL");
			System.out.println("showSideTray: " + e.getLocalizedMessage());
		}
	}

	public void hideSideTray(){
		try{
//			TouchAction touch = new TouchAction(driver);
//			touch.tap(8, 96).perform();
			driver.findElement(By.xpath("//android.widget.ImageView[contains(@resource-id,'up')]")).click();
			System.out.println("SIDE TRAY IS NOW HIDDEN");
		}catch(Exception e){
//			Screenshots.take("FAIL");
			System.out.println("hideSideTray: " + e.getLocalizedMessage());
		}
	}

//	public void scrolldownproductsview() {
//		try{
//			driver.swipe(5, screen.height - 10, 5, screen.height/2, 3000);
//			System.out.println("SWIPE UP");
//		}catch(Exception e){
//			Screenshots.take("FAIL");
//			System.out.println("Click Exception: " + e.getLocalizedMessage());
//			throw new AssertionError("Error Swiping Element. ");
//		}
//	}
//	public void scrollupproductsview() {
//		try{
//			driver.swipe(5, 5, 5, screen.height-10, 1000);
//			System.out.println("SWIPE DOWN");
//		}catch(Exception e){
//			Screenshots.take("FAIL");
//			System.out.println("Click Exception: " + e.getLocalizedMessage());
//			throw new AssertionError("Error Swiping Element. ");
//		}
//	}

	public void captureScreen (String testStatus, int ScreenshotNumber, String testNumber, String pageName){
//		Screenshots.take(String.format(testNumber + " - " + ScreenshotNumber + " - " + pageName + " - " + testStatus));
	}


	public void goToMenuItem(String menuObjName) {
		try{
			showSideTray();
			driver.findElement(By.name(menuObjName)).click();
			System.out.println(menuObjName + " PAGE DISPLAYED");
		}catch(Exception e){
			System.out.println("ERROR LOADING " + menuObjName + " PAGE.");
		}
		driver.hideKeyboard();
	}


}

