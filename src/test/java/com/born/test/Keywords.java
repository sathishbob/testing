﻿package com.born.test;
import static com.born.test.DriverScript.APP_LOGS;
import static com.born.test.DriverScript.CONFIG;
import static com.born.test.DriverScript.OR;
import static com.born.test.DriverScript.currentTestCaseName;
import static com.born.test.DriverScript.currentTestDataSetID;
import static com.born.test.DriverScript.currentTestSuiteXLS;
import static com.born.test.GetOSName.OsUtils.isWindows;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;


public class Keywords {

    public WebDriver driver;
    public DesiredCapabilities capability;


	public String selectdroplist(String object, String data) {
		APP_LOGS.debug("select the dfpauseropdown value");
		try {

			Select oSelect = new Select(driver.findElement(By.xpath(OR.getProperty(object))));
			List <WebElement> elementCount = oSelect.getOptions();
			int iSize = elementCount.size();
			APP_LOGS.debug(iSize);
			for(int i =0; i<iSize ; i++){
				String sValue = elementCount.get(i).getText();
				if(sValue.equalsIgnoreCase(data)){
						oSelect.selectByIndex(i);
						break;
						}
				
				}
			
		} catch (Exception e) {
			return Constants.KEYWORD_FAIL
					+ " - cannot select the dropdown value" + e.getMessage();

		}
		return Constants.KEYWORD_PASS;

	}
	
	public String selectstate(String object, String data) {
		APP_LOGS.debug("select the dropdown value");
		try {

			Select oSelect = new Select(driver.findElement(By.xpath(OR.getProperty(object))));
			List <WebElement> elementCount = oSelect.getOptions();
			int iSize = elementCount.size();
			APP_LOGS.debug(iSize);
			for(int i =0; i<iSize ; i++){
				String sValue = elementCount.get(i).getText();
						if(sValue.equalsIgnoreCase("California")){
						oSelect.selectByIndex(i);
						break;
						}
				
				}
			
		} catch (Exception e) {
			return Constants.KEYWORD_FAIL
					+ " - cannot select the dropdown value" + e.getMessage();

		}
		return Constants.KEYWORD_PASS;

	}
	public String alertaccept(String object, String data) {
		APP_LOGS.debug("Alert present ");
		try {

			new WebDriverWait(driver, 30)
	        .ignoring(NoAlertPresentException.class)
	        .until(ExpectedConditions.alertIsPresent());

	    Alert al = driver.switchTo().alert();
	    al.accept();
			
		} catch (Exception e) {
			return Constants.KEYWORD_FAIL
					+ " - Alert not present in webpage" + e.getMessage();

		}
		return Constants.KEYWORD_PASS;

	}
	
	public String iselementpresent(String object, String data) {
		APP_LOGS.debug("Verifying the Element in webpage");
		try {

			if (driver.findElements(By.xpath(OR.getProperty(object))).size() != 0)
				return Constants.KEYWORD_PASS
						+ " -- Element is present on the webpage ";
			else
				return Constants.KEYWORD_FAIL
						+ " -- Element is not present please check ";
		} catch (Exception e) {
			return Constants.KEYWORD_FAIL + " Element not found "
					+ e.getMessage();
		}

	}

	

	public String langtextverify(String object, String data) {
		APP_LOGS.debug("Verifying the text");
		try {
			String actual = driver
					.findElement(By.xpath(OR.getProperty(object)))
					.getAttribute("placeholder");
			String expected = data;

			if (actual.equals(expected))
				return Constants.KEYWORD_PASS + " -- text verified " + actual
						+ " -- " + expected;
			else
				return Constants.KEYWORD_FAIL + " -- text not verified "
						+ actual + " -- " + expected;
		} catch (Exception e) {
			return Constants.KEYWORD_FAIL + " Object not found "
					+ e.getMessage();
		}

	}
	
	

	
	public String shippingstate(String object, String data) {
		APP_LOGS.debug("Selecting Random item from dropdown");
		try {

			Select droplist = new Select(driver.findElement(By.xpath("//select[@id='billing:country_id']")));
			
			droplist.selectByVisibleText("New York");

		} catch (Exception e) {
			return Constants.KEYWORD_FAIL
					+ " - cannot select the dropdown random" + e.getMessage();

		}
		return Constants.KEYWORD_PASS;

	}



	public String getCurrentURL(String object, String data) {
		APP_LOGS.debug("Checking the Current URL of the page");
		try {

			String URL = driver.getCurrentUrl();
			if (URL.equals(data)) {
				return Constants.KEYWORD_PASS;
			}

			else if (URL.contains(data)) {
				return Constants.KEYWORD_PASS;
			} else {
				return Constants.KEYWORD_FAIL;
			}

		} catch (Exception e) {
			return Constants.KEYWORD_FAIL
					+ " -Current URL is not fetched properly " + e.getMessage();

		}
	}

	public String DefaultVariant(String object, String data) {
		APP_LOGS.debug("Selecting dropdownvalue");
		try {
			WebElement myAccountDropList = driver.findElement(By.xpath(OR
					.getProperty(object)));
			Thread.sleep(1000L);

			Select sel = new Select(myAccountDropList);
			String VariantTxt = sel.getFirstSelectedOption().getText();
			if (VariantTxt.startsWith("BOX")) {
				return Constants.KEYWORD_PASS;
			}

		} catch (Exception e) {
			return Constants.KEYWORD_FAIL + " - Could not select from list. "
					+ e.getMessage();

		}

		return Constants.KEYWORD_PASS;
	}

	public String SortByAscending(String object, String data) {
		APP_LOGS.debug("Verifying the SortBy-Ascending");
		try {

			List<WebElement> products_Webelement = new LinkedList<WebElement>();
			APP_LOGS.debug("Storing the products into the linkedlist");
			products_Webelement = driver.findElements(By.xpath(OR
					.getProperty(object)));

			LinkedList<String> product_names = new LinkedList<String>();
			for (int i = 0; i < products_Webelement.size(); i++) {
				String s = products_Webelement.get(i).getAttribute("alt");
				product_names.add(s.toLowerCase().trim());

			}

			boolean result = chkalphabetical_order_ascending(product_names);

			if (result == true) {
				return Constants.KEYWORD_PASS;
			}

			else {

				return Constants.KEYWORD_FAIL + " - SortBy Ascending Failed";
			}
		} catch (Exception e) {
			return Constants.KEYWORD_FAIL + " - Could not select from list. "
					+ e.getMessage();

		}
	}

	private static boolean chkalphabetical_order_ascending(
			LinkedList<String> product_names) {
		String previous = ""; // empty string

		for (final String current : product_names) {
			if (current.compareTo(previous) < 0)
				return false;
			previous = current;
		}
		return true;
	}

	public String SortByDescending(String object, String data) {
		APP_LOGS.debug("Verifying the SortBy-Descending");
		try {

			List<WebElement> products_Webelement = new LinkedList<WebElement>();
			APP_LOGS.debug("Storing the products into the linkedlist");
			products_Webelement = driver.findElements(By.xpath(OR
					.getProperty(object)));

			LinkedList<String> product_names = new LinkedList<String>();
			for (int i = 0; i < products_Webelement.size(); i++) {
				String s = products_Webelement.get(i).getAttribute("alt");
				product_names.add(s.toLowerCase().trim());

			}

			boolean result = chkalphabetical_order_descending(product_names);

			if (result == true) {
				return Constants.KEYWORD_PASS;
			}

			else {

				return Constants.KEYWORD_FAIL + " - SortBy Descending Failed";
			}
		} catch (Exception e) {
			return Constants.KEYWORD_FAIL + " - Could not select from list. "
					+ e.getMessage();

		}
	}

	private static boolean chkalphabetical_order_descending(
			LinkedList<String> product_names) {
		String previous = ""; // empty string

		for (final String current : product_names) {
			if (current.compareTo(previous) > 0)
				return true;
			previous = current;
		}
		return false;
	}

	public String ElementNotPresent(String object, String data) {
		APP_LOGS.debug("Checking that element is not present");
		try {

			List<WebElement> ls = driver.findElements(By.xpath(OR
					.getProperty(object)));
			if (ls.isEmpty() == false) {
				return Constants.KEYWORD_FAIL;
			}

			else {
				return Constants.KEYWORD_PASS;
			}
		} catch (Exception e) {
			return Constants.KEYWORD_FAIL
					+ " - Exception thrown while checking for element not present "
					+ e.getMessage();

		}
	}

	public String Footer_Social(String object, String data) {
		APP_LOGS.debug("Handling multiple windows in the footer");
		try {
			driver.manage().deleteAllCookies();
			String mainWindowHandle = driver.getWindowHandle();
			System.out.println("Main Window Handle: "
					+ driver.getWindowHandle());
			driver.findElement(By.xpath(OR.getProperty(object))).click();
			Set<String> window = driver.getWindowHandles();
			window.remove(mainWindowHandle);

			Iterator<String> iterator = window.iterator();
			while (iterator.hasNext()) {
				String popupHandle = iterator.next();

				if (!popupHandle.equals(mainWindowHandle)) {

					driver.switchTo().window(popupHandle).toString();
					driver.manage().timeouts()
							.implicitlyWait(30, TimeUnit.SECONDS);

					driver.manage().deleteAllCookies();
					driver.close();
				}
			}
			// Back to main window

			driver.switchTo().window(mainWindowHandle);

		} catch (Exception e) {
			return Constants.KEYWORD_FAIL
					+ "Unable to handle windows, See log4j report for more info"
					+ e.getMessage();
		}
		return Constants.KEYWORD_PASS;

	}
	
	public String SearchProduct(String object, String data) {

		APP_LOGS.debug("Handling Search in Optavia");
		try {

			driver.findElement(By.xpath(OR.getProperty(object))).click();
			Thread.sleep(10000);
			driver.findElement(By.xpath(".//input[@id='input_SearchBox']"))
					.sendKeys(data);
			Thread.sleep(10000);
			driver.findElement(By.xpath(OR.getProperty(object))).click();

			/*
			 * Robot r = new Robot(); r.keyPress(KeyEvent.VK_ENTER);
			 * r.keyRelease(KeyEvent.VK_ENTER); Thread.sleep(20000);
			 */
		}

		catch (Exception e) {
			return Constants.KEYWORD_FAIL + "Unable to handle the search"
					+ e.getMessage();
		}
		return Constants.KEYWORD_PASS;

	}



	// Added : Jayakumar
	// Keyword added to select list values
	public String selectDropDownByVisibleText(String object, String data) {
		APP_LOGS.debug("select the dropdown value");
		try {

			Select oSelect = new Select(driver.findElement(By.xpath(OR.getProperty(object))));
			List <WebElement> elementCount = oSelect.getOptions();
			int iSize = elementCount.size();
			APP_LOGS.debug("Size of the List: "+ iSize);
			for(int i =0; i<iSize ; i++){
				String sValue = elementCount.get(i).getText().trim();
				System.out.println(sValue);
				if(sValue.equalsIgnoreCase(data)){
						//oSelect.selectByVisibleText(sValue);
					elementCount.get(i).click();
						APP_LOGS.debug("Selected Value: "+ sValue);
					//	oSelect.selectByIndex(i);
						break;
						}
				
				}
			
		} catch (Exception e) {
			return Constants.KEYWORD_FAIL
					+ " - cannot select the dropdown value" + e.getMessage();

		}
		return Constants.KEYWORD_PASS;

	}

	private String trim(String data) {
		// TODO Auto-generated method stub
		return null;
	}



		// Added : Jayakumar
		// Keyword added to have explicit wait
		public String WaitTillElementPresent(String object, String data) {
			//APP_LOGS.debug("select the dropdown value");
			try {
				new WebDriverWait(driver, 120).until(ExpectedConditions.presenceOfElementLocated(By.xpath(OR.getProperty(object))));
				
			} catch (Exception e) {
				return Constants.KEYWORD_FAIL
						+ " Wait Failed" + e.getMessage();

			}
			return Constants.KEYWORD_PASS;

		}
		
		// Added : Jayakumar
				// Keyword added to check if the checkbox is selected
		public String IsSelected(String object, String data) {
			//APP_LOGS.debug("select the dropdown value");
			try {
				if ( !driver.findElement(By.xpath(OR.getProperty(object))).isSelected() )
				{
				     driver.findElement(By.xpath(OR.getProperty(object))).click();
				}

		 } catch (Exception e) {
				return Constants.KEYWORD_FAIL
						+ " Click Failed" + e.getMessage();

			}
			return Constants.KEYWORD_PASS;

		
		}	
		
		public String ContainsText(String object, String data) {
			APP_LOGS.debug("Verifying the text");
			try {
				String actual = driver
						.findElement(By.xpath(OR.getProperty(object))).getText();
				String expected = data;

				if (actual.contains(expected))
					return Constants.KEYWORD_PASS + " -- text verified " + actual
							+ " -- " + expected;
				else
					return Constants.KEYWORD_FAIL + " -- text not verified "
							+ actual + " -- " + expected;
			} catch (Exception e) {
				return Constants.KEYWORD_FAIL + " Object not found "
						+ e.getMessage();
			}}
		public String switchToDefaultFrame(String object, String data) {
			APP_LOGS.debug("Switching perticular frame");

			try {
				driver.switchTo().defaultContent();
			} catch (Exception e) {
				return Constants.KEYWORD_FAIL + " Unable to switch to default frame "
						+ e.getMessage();

			}
			return Constants.KEYWORD_PASS;

		}
		public String ClickEnter(String object, String data) {
			APP_LOGS.debug("Clicking on OK button");

			try {
				 Robot rb = new Robot();
				 rb.keyPress(KeyEvent.VK_ENTER);
			} catch (Exception e) {
				return Constants.KEYWORD_FAIL + " Unable to Click on OK button"
						+ e.getMessage();

			}
			return Constants.KEYWORD_PASS;

		}
		
		
	/*	public void captureScreenshotNew(String filename, String keyword_execution_result) throws IOException{
	        // take screen shots
	        if(CONFIG.getProperty("screenshot_everystep").equals("Y")){
	            // capturescreen
	        	Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver);
				ImageIO.write(screenshot.getImage(),"JPG",new File(System.getProperty("user.dir") +"//screenshots//"+filename+".jpg"));
	        }else if (keyword_execution_result.startsWith(Constants.KEYWORD_FAIL) && CONFIG.getProperty("screenshot_error").equals("Y") ){
			 	Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver);
				ImageIO.write(screenshot.getImage(),"JPG",new File(System.getProperty("user.dir") +"//screenshots//"+filename+".jpg"));
				
	       
	    }
	       }	*/	
		
		public String SortByPrice(String object, String data) {
			APP_LOGS.debug("Checking Sort By - Price");

			try {
				
				String FirstVal= driver.findElement(By.xpath(OR.getProperty("first_product_price"))).getText();
				String SecondVal= driver.findElement(By.xpath(OR.getProperty("second_product_price"))).getText();
				if (Integer.parseInt(FirstVal)<Integer.parseInt(SecondVal)){
					APP_LOGS.debug("Products Sorted");
				}else
				{APP_LOGS.debug("Products Sort - Failed");}
				
				} catch (Exception e) {
				return Constants.KEYWORD_FAIL + " Products Sort - Failed"
						+ e.getMessage();

			}
			return Constants.KEYWORD_PASS;

		}
		
	
    public String navigate(String object,String data){
        APP_LOGS.debug("Navigating to URL");
        try{
            driver.get(data);
        }catch(Exception e){
            return Constants.KEYWORD_FAIL+" -- Not able to navigate";
        }
        return Constants.KEYWORD_PASS;
    }

    
   
    
    public String enablehidden(String object,String data){
        APP_LOGS.debug("Enabling Hidden elements ");
        try{
        	
        	    WebElement Element= driver.findElement(By.xpath(OR.getProperty(object)));
        	    JavascriptExecutor executor = (JavascriptExecutor)driver;
        	    executor.executeScript("arguments[0].click();", Element);
            
        }catch(Exception e){
            return Constants.KEYWORD_FAIL+" -- Not able to enable the button"+e.getMessage();
        }

        return Constants.KEYWORD_PASS;
    }

    
    public String clickLink(String object,String data){
        APP_LOGS.debug("Clicking on link ");
        try{
            driver.findElement(By.xpath(OR.getProperty(object))).click();
            Thread.sleep(1000L);
        }catch(Exception e){
            return Constants.KEYWORD_FAIL+" -- Not able to click on link"+e.getMessage();
        }

        return Constants.KEYWORD_PASS;
    }

    public String clickLinkCss(String object,String data){
        APP_LOGS.debug("Clicking on link ");
        try{
            driver.findElement(By.cssSelector(OR.getProperty(object))).click();
        }catch(Exception e){
            return Constants.KEYWORD_FAIL+" -- Not able to click on link"+e.getMessage();
        }

        return Constants.KEYWORD_PASS;
    }

    public String clickLink_linkText(String object,String data){
        APP_LOGS.debug("Clicking on link ");
        driver.findElement(By.linkText(OR.getProperty(object))).click();

        return Constants.KEYWORD_PASS;
    }



    public  String verifyLinkText(String object,String data){
        APP_LOGS.debug("Verifying link Text");
        try{
            String actual=driver.findElement(By.xpath(OR.getProperty(object))).getText();
            String expected=data;

            if(actual.equals(expected))
                return Constants.KEYWORD_PASS;
            else
                return Constants.KEYWORD_FAIL+" -- Link text not verified";

        }catch(Exception e){
            return Constants.KEYWORD_FAIL+" -- Link text not verified"+e.getMessage();

        }

    }


    public  String clickButton(String object,String data){
        APP_LOGS.debug("Clicking on Button");
        try{
            driver.findElement(By.xpath(OR.getProperty(object))).click();
        }catch(Exception e){
            return Constants.KEYWORD_FAIL+" -- Not able to click on Button"+e.getMessage();
        }


        return Constants.KEYWORD_PASS;
    }

    public  String clickButtonCss(String object,String data){
        APP_LOGS.debug("Clicking on Button");
        try{
            driver.findElement(By.cssSelector(OR.getProperty(object))).click();
        }catch(Exception e){
            return Constants.KEYWORD_FAIL+" -- Not able to click on Button"+e.getMessage();
        }


        return Constants.KEYWORD_PASS;
    }

    public  String verifyButtonText(String object,String data){
        APP_LOGS.debug("Verifying the button text");
        try{
            String actual=driver.findElement(By.xpath(OR.getProperty(object))).getText();
            String expected=data;

            if(actual.equals(expected))
                return Constants.KEYWORD_PASS;
            else
                return Constants.KEYWORD_FAIL+" -- Button text not verified "+actual+" -- "+expected;
        }catch(Exception e){
            return Constants.KEYWORD_FAIL+" Object not found "+e.getMessage();
        }
    }
    
   

    public  String selectList(String object, String data){
        APP_LOGS.debug("Selecting from list");
        try{
            if(!data.equals(Constants.RANDOM_VALUE)){
                driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(data);
            }else{
                // logic to find a random value in list
                WebElement droplist= driver.findElement(By.xpath(OR.getProperty(object)));
                List<WebElement> droplist_cotents = droplist.findElements(By.tagName("option"));
                Random num = new Random();
                int index=num.nextInt(droplist_cotents.size());
                String selectedVal=droplist_cotents.get(index).getText();

                driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(selectedVal);
            }
        }catch(Exception e){
            return Constants.KEYWORD_FAIL +" - Could not select from list. "+ e.getMessage();

        }

        return Constants.KEYWORD_PASS;
    }

    public String verifyAllListElements(String object, String data){
        APP_LOGS.debug("Verifying the selection of the list");
        try{
            WebElement droplist= driver.findElement(By.xpath(OR.getProperty(object)));
            List<WebElement> droplist_cotents = droplist.findElements(By.tagName("option"));

            // extract the expected values from OR. properties
            String temp=data;
            String allElements[]=temp.split(",");
            // check if size of array == size if list
            if(allElements.length != droplist_cotents.size())
                return Constants.KEYWORD_FAIL +"- size of lists do not match";

            for(int i=0;i<droplist_cotents.size();i++){
                if(!allElements[i].equals(droplist_cotents.get(i).getText())){
                    return Constants.KEYWORD_FAIL +"- Element not found - "+allElements[i];
                }
            }
        }catch(Exception e){
            return Constants.KEYWORD_FAIL +" - Could not select from list. "+ e.getMessage();

        }


        return Constants.KEYWORD_PASS;
    }

    public  String verifyListSelection(String object,String data){
        APP_LOGS.debug("Verifying all the list elements");
        try{
            String expectedVal=data;
            //System.out.println(driver.findElement(By.xpath(OR.getProperty(object))).getText());
            WebElement droplist= driver.findElement(By.xpath(OR.getProperty(object)));
            List<WebElement> droplist_cotents =droplist.findElements(By.tagName("option"));
            String actualVal=null;
            for(int i=0;i<droplist_cotents.size();i++){
                String selected_status=droplist_cotents.get(i).getAttribute("selected");
                if(selected_status!=null)
                    actualVal = droplist_cotents.get(i).getText();
            }

            if(!actualVal.equals(expectedVal))
                return Constants.KEYWORD_FAIL + "Value not in list - "+expectedVal;

        }catch(Exception e){
            return Constants.KEYWORD_FAIL +" - Could not find list. "+ e.getMessage();

        }
        return Constants.KEYWORD_PASS;

    }

    public  String selectRadio(String object, String data){
        APP_LOGS.debug("Selecting a radio button");
        try{
            String temp[]=object.split(Constants.DATA_SPLIT);
            driver.findElement(By.xpath(OR.getProperty(temp[0])+data+OR.getProperty(temp[1]))).click();
        }catch(Exception e){
            return Constants.KEYWORD_FAIL +"- Not able to find radio button";

        }

        return Constants.KEYWORD_PASS;

    }

    public  String verifyRadioSelected(String object, String data){
        APP_LOGS.debug("Verify Radio Selected");
        try{
            String temp[]=object.split(Constants.DATA_SPLIT);
            String checked=driver.findElement(By.xpath(OR.getProperty(temp[0])+data+OR.getProperty(temp[1]))).getAttribute("checked");
            if(checked==null)
                return Constants.KEYWORD_FAIL+"- Radio not selected";


        }catch(Exception e){
            return Constants.KEYWORD_FAIL +"- Not able to find radio button";

        }

        return Constants.KEYWORD_PASS;

    }


    public  String checkCheckBox(String object,String data){
        APP_LOGS.debug("Checking checkbox");
        try{
            // true or null
            String checked=driver.findElement(By.xpath(OR.getProperty(object))).getAttribute("checked");
            if(checked==null)// checkbox is unchecked
                driver.findElement(By.xpath(OR.getProperty(object))).click();
        }catch(Exception e){
            return Constants.KEYWORD_FAIL+" - Could not find checkbo";
        }
        return Constants.KEYWORD_PASS;

    }

    public String unCheckCheckBox(String object,String data){
        APP_LOGS.debug("Unchecking checkBox");
        try{
            String checked=driver.findElement(By.xpath(OR.getProperty(object))).getAttribute("checked");
            if(checked!=null)
                driver.findElement(By.xpath(OR.getProperty(object))).click();
        }catch(Exception e){
            return Constants.KEYWORD_FAIL+" - Could not find checkbox";
        }
        return Constants.KEYWORD_PASS;

    }


    public  String verifyCheckBoxSelected(String object,String data){
        APP_LOGS.debug("Verifying checkbox selected");
        try{
            String checked=driver.findElement(By.xpath(OR.getProperty(object))).getAttribute("checked");
            if(checked!=null)
                return Constants.KEYWORD_PASS;
            else
                return Constants.KEYWORD_FAIL + " - Not selected";

        }catch(Exception e){
            return Constants.KEYWORD_FAIL+" - Could not find checkbox";

        }


    }


    public String verifyText(String object, String data){
        APP_LOGS.debug("Verifying the text");
        try{
            String actual=driver.findElement(By.xpath(OR.getProperty(object))).getText();
            String expected=data;

            if(actual.equalsIgnoreCase(expected))
                return Constants.KEYWORD_PASS;
            else
                return Constants.KEYWORD_FAIL+" -- text not verified "+actual+" -- "+expected;
        }catch(Exception e){
            return Constants.KEYWORD_FAIL+" Object not found "+e.getMessage();
        }

    }

    public  String writeInInput(String object,String data){
        APP_LOGS.debug("Writing in text box");

        try{
            driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(data);
        }catch(Exception e){
            return Constants.KEYWORD_FAIL+" Unable to write "+e.getMessage();

        }
        return Constants.KEYWORD_PASS;

    }

    public  String writeInInputCss(String object,String data){
        APP_LOGS.debug("Writing in text box");

        try{
            driver.findElement(By.cssSelector(OR.getProperty(object))).sendKeys(data);
        }catch(Exception e){
            return Constants.KEYWORD_FAIL+" Unable to write "+e.getMessage();

        }
        return Constants.KEYWORD_PASS;

    }

    public  String verifyTextinInput(String object,String data){
        APP_LOGS.debug("Verifying the text in input box");
        try{
            String actual = driver.findElement(By.xpath(OR.getProperty(object))).getAttribute("value");
            String expected=data;

            if(actual.equals(expected)){
                return Constants.KEYWORD_PASS;
            }else{
                return Constants.KEYWORD_FAIL+" Not matching ";
            }

        }catch(Exception e){
            return Constants.KEYWORD_FAIL+" Unable to find input box "+e.getMessage();

        }
    }

    public  String clickImage(){
        APP_LOGS.debug("Clicking the image");

        return Constants.KEYWORD_PASS;
    }

    public  String verifyFileName(){
        APP_LOGS.debug("Verifying inage filename");

        return Constants.KEYWORD_PASS;
    }


    public  String verifyTitle(String object, String data){
        APP_LOGS.debug("Verifying title");
        try{
            String actualTitle= driver.getTitle();
            String expectedTitle=data;
            if(actualTitle.equals(expectedTitle))
                return Constants.KEYWORD_PASS;
            else
                return Constants.KEYWORD_FAIL+" -- Title not verified "+expectedTitle+" -- "+actualTitle;
        }catch(Exception e){
            return Constants.KEYWORD_FAIL+" Error in retrieving title";
        }
    }

    public String exist(String object,String data){
        APP_LOGS.debug("Checking existance of element");
        try{
            driver.findElement(By.xpath(OR.getProperty(object)));
        }catch(Exception e){
            return Constants.KEYWORD_FAIL+" Object doest not exist";
        }


        return Constants.KEYWORD_PASS;
    }

    public  String click(String object,String data){
        APP_LOGS.debug("Clicking on any element");
        try{
            driver.findElement(By.xpath(OR.getProperty(object))).click();
        }catch(Exception e){
            return Constants.KEYWORD_FAIL+" Not able to click";
        }
        return Constants.KEYWORD_PASS;
    }

    public  String clickCss(String object,String data){
        APP_LOGS.debug("Clicking on any element");
        try{
            driver.findElement(By.cssSelector(OR.getProperty(object))).click();
        }catch(Exception e){
            return Constants.KEYWORD_FAIL+" Not able to click";
        }
        return Constants.KEYWORD_PASS;
    }

    public  String synchronize(String object,String data){
        APP_LOGS.debug("Waiting for page to load");
        ((JavascriptExecutor) driver).executeScript(
                "function pageloadingtime()"+
                        "{"+
                        "return 'Page has completely loaded'"+
                        "}"+
                        "return (window.onload=pageloadingtime());");

        return Constants.KEYWORD_PASS;
    }

    public  String waitForElementVisibility(String object,String data){
        APP_LOGS.debug("Waiting for an element to be visible");
        int start=0;
        int time=(int)Double.parseDouble(data);
        try{
            while(time == start){
                if(driver.findElements(By.xpath(OR.getProperty(object))).size() == 0){
                    Thread.sleep(1000L);
                    start++;
                }else{
                    break;
                }
            }
        }catch(Exception e){
            return Constants.KEYWORD_FAIL+"Unable to close browser. Check if its open"+e.getMessage();
        }
        return Constants.KEYWORD_PASS;
    }

    public  String closeBrowser(String object, String data){
        APP_LOGS.debug("Closing the browser");
        try{
            driver.close();
        }catch(Exception e){
            return Constants.KEYWORD_FAIL+"Unable to close browser. Check if its open"+e.getMessage();
        }
        return Constants.KEYWORD_PASS;

    }

    public  String deleteAllCookies(String object, String data){
        APP_LOGS.debug("Deleting all the Browser cookies");
        try{
            driver.manage().deleteAllCookies();
            driver.navigate().refresh();
        }catch(Exception e){
            return Constants.KEYWORD_FAIL+"Unable delete all the cookies from Browser"+e.getMessage();
        }
        return Constants.KEYWORD_PASS;

    }



    public  String quitBrowser(String object, String data){
        APP_LOGS.debug("Closing the browser");
        try{
            driver.quit();
        }catch(Exception e){
            return Constants.KEYWORD_FAIL+"Unable to close browser. Check if its open"+e.getMessage();
        }
        return Constants.KEYWORD_PASS;

    }

    public String pause(String object, String data) throws NumberFormatException, InterruptedException{
        long time = (long)Double.parseDouble(object);
        Thread.sleep(time*1000L);
        return Constants.KEYWORD_PASS;
    }
    public String explicitwait(String object, String data) {
    	APP_LOGS.debug("Waiting for the element to be visible");
    	try{
    		WebDriverWait wait=new WebDriverWait(driver,30);
    		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
    	}catch(Exception e)
    	{
    		return Constants.KEYWORD_FAIL+"Element is not visible even after explicitwwait"+e.getMessage();
    	}
		
		return Constants.KEYWORD_PASS;
	}


    /************************APPLICATION SPECIFIC KEYWORDS********************************/
  
    
   
    public  String countryselect(String object, String data){
        APP_LOGS.debug("Selecting dropdownvalue");
        try {
            WebElement myAccountDropList = driver.findElement(By.xpath(OR.getProperty(object)));
                Thread.sleep(1000L);
           
            Select cuntry = new Select(myAccountDropList);
            cuntry.selectByVisibleText(data);

        }catch(Exception e){
            return Constants.KEYWORD_FAIL +" - Could not select from list. "+ e.getMessage();

        }

        return Constants.KEYWORD_PASS;
    }


    public  String selectCreditCard(String object, String data){
        APP_LOGS.debug("Selecting Credit Card");

        int num = Integer.parseInt(data);

        try {
            List<WebElement> selectSize = driver.findElements(By.xpath(OR.getProperty(object)));
            selectSize.get(num).click();

        }catch(Exception e){
            return Constants.KEYWORD_FAIL +" - Could not select from list. "+ e.getMessage();

        }

        return Constants.KEYWORD_PASS;
    }

    public  String enter(String object, String data){
        APP_LOGS.debug("Going back one page");
        try{
        	Robot r = new Robot();
        	r.keyPress(KeyEvent.VK_ENTER);
        }catch(Exception e){
            return Constants.KEYWORD_FAIL+"Unable to click, Enter Key "+e.getMessage();
        }
        return Constants.KEYWORD_PASS;

    }

    public  String tab(String object, String data){
        APP_LOGS.debug("Going back one page");
        try{
            driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(Keys.TAB);
        }catch(Exception e){
            return Constants.KEYWORD_FAIL+"Unable to go back, Check if its open"+e.getMessage();
        }
        return Constants.KEYWORD_PASS;

    }

    public  String enterCss(String object, String data){
        APP_LOGS.debug("Going back one page");
        try{
            driver.findElement(By.cssSelector(OR.getProperty(object))).sendKeys(Keys.ENTER);
        }catch(Exception e){
            return Constants.KEYWORD_FAIL+"Unable to go back, Check if its open"+e.getMessage();
        }
        return Constants.KEYWORD_PASS;

    }


    public String windowHandler(String object, String data){
        APP_LOGS.debug("Handling multiple windows");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        try{
            String mainWindowHandle=driver.getWindowHandle();
            driver.findElement(By.xpath(OR.getProperty(object))).click();
            Set<String> window = driver.getWindowHandles();
            Iterator<String> iterator= window.iterator();

            while(iterator.hasNext())
            {
                String popupHandle=iterator.next().toString();
                if(!popupHandle.contains(mainWindowHandle))
                {
                	
                    driver.switchTo().window(popupHandle);
                   
                }
            }

            // Back to main window
            // driver.switchTo().window(mainWindowHandle);

        }catch(Exception e){
            return Constants.KEYWORD_FAIL+"Unable to handle windows, See log4j report for more info"+e.getMessage();
        }
        return Constants.KEYWORD_PASS;

    }

    public  String click_JS(String object,String data){
        APP_LOGS.debug("Clicking on any element using JavaScript");
        try {
            ((JavascriptExecutor)driver).executeScript("document.getElementById('"+object+"').click()");
        } catch (Exception e) {
            return Constants.KEYWORD_FAIL+"Unable to click, Check if its open"+e.getMessage();
        }
        return Constants.KEYWORD_PASS;
    }

    public  String writeInInput_JS(String object,String data){
        APP_LOGS.debug("Writing in text box using JavaScript");
        try {
            ((JavascriptExecutor)driver).executeScript("document.getElementById('"+object+"').value='"+data+"'");
        } catch (Exception e) {
            return Constants.KEYWORD_FAIL+"Unable to write, Check if its open"+e.getMessage();
        }
        return Constants.KEYWORD_PASS;
    }

    // << Go back one page
    public  String goBack(String object, String data){
        APP_LOGS.debug("Going back one page");
        try{
            driver.navigate().back();
        }catch(Exception e){
            return Constants.KEYWORD_FAIL+"Unable to go back, Check if its open"+e.getMessage();
        }
        return Constants.KEYWORD_PASS;

    }

    // >> Go to forward one page
    public  String goForward(String object, String data){
        APP_LOGS.debug("Going back one page");
        try{
            driver.navigate().forward();
        }catch(Exception e){
            return Constants.KEYWORD_FAIL+"Unable to go back, Check if its open"+e.getMessage();
        }
        return Constants.KEYWORD_PASS;

    }

    // Verify list of items after clicking on drop-down list like Newborn, Shoes etc.
    public  String verifyAllItems(String object,String data){
        APP_LOGS.debug("Verifying link Text");
        try{
            for (int i = 0; i <=150; i++) {
                List<WebElement> gridBoxes = driver.findElements(By.className("events-div"));
                System.out.println("Number of boxes " + gridBoxes.size());
                // Pick random link box
                Random gridnum = new Random();
                int grids = gridnum.nextInt(gridBoxes.size());
                WebElement grid = gridBoxes.get(grids);
                //WebElement box = link_boxes.get(0);
                List<WebElement> ItemBoxes = grid.findElements(By.className("product-image"));
                System.out.println("Total links are -- " + ItemBoxes.size());
                // Pick random item
                Random itemnum = new Random();
                int items = itemnum.nextInt(ItemBoxes.size());
                WebElement item = ItemBoxes.get(items);
                item.click();
                Thread.sleep(4000L);
                System.out.println(driver.getTitle());
                driver.navigate().back();

            }
        }catch(Exception e){
            return Constants.KEYWORD_FAIL+" -- Link text not verified"+e.getMessage();

        }
        return Constants.KEYWORD_PASS;
    }

    // Credit Card functionality at the CheckOut
    public  String selectExpMonth(String object, String data){
        APP_LOGS.debug("Selecting Expiration Month");
        try {
            WebElement states = driver.findElement(By.xpath(OR.getProperty(object)));
            List<WebElement> state = states.findElements(By.tagName("option"));
            Random num = new Random();
            int index=num.nextInt(state.size());
            state.get(index).click();
        }catch(Exception e){
            return Constants.KEYWORD_FAIL +" - Could not select from list. "+ e.getMessage();

        }

        return Constants.KEYWORD_PASS;
    }
    // Credit Card functionality at the CheckOut
    public  String selectExpYear(String object, String data){
        APP_LOGS.debug("Selecting Expiration Year");
        try {
            WebElement states = driver.findElement(By.xpath(OR.getProperty(object)));
            List<WebElement> state = states.findElements(By.tagName("option"));
            Random num = new Random();
            int index=num.nextInt(state.size());
            state.get(index).click();
        }catch(Exception e){
            return Constants.KEYWORD_FAIL +" - Could not select from list. "+ e.getMessage();

        }

        return Constants.KEYWORD_PASS;
    }
    // Credit Card functionality at the CheckOut
    public  String selectAmex(String object, String data){
        APP_LOGS.debug("Selecting American Express Card");
        try {
            WebElement size = driver.findElement(By.xpath(OR.getProperty(object)));
            List<WebElement> selectSize = size.findElements(By.tagName("option"));
            selectSize.get(0).click();

        }catch(Exception e){
            return Constants.KEYWORD_FAIL +" - Could not select from list. "+ e.getMessage();

        }

        return Constants.KEYWORD_PASS;
    }
    // Credit Card functionality at the CheckOut
    public  String selectVisa(String object, String data){
        APP_LOGS.debug("Selecting Visa Credit Card");
        try {
            WebElement size = driver.findElement(By.xpath(OR.getProperty(object)));
            List<WebElement> selectSize = size.findElements(By.tagName("option"));
            selectSize.get(1).click();

        }catch(Exception e){
            return Constants.KEYWORD_FAIL +" - Could not select from list. "+ e.getMessage();

        }

        return Constants.KEYWORD_PASS;
    }
    // Credit Card functionality at the CheckOut
    public  String selectMasterCard(String object, String data){
        APP_LOGS.debug("Selecting MasterCard");
        try {
            WebElement size = driver.findElement(By.xpath(OR.getProperty(object)));
            List<WebElement> selectSize = size.findElements(By.tagName("option"));
            selectSize.get(2).click();

        }catch(Exception e){
            return Constants.KEYWORD_FAIL +" - Could not select from list. "+ e.getMessage();

        }

        return Constants.KEYWORD_PASS;
    }

    public  String selectRandomState(String object, String data){
        APP_LOGS.debug("Selecting Random States");
        try {
            WebElement states = driver.findElement(By.xpath(OR.getProperty(object)));
            List<WebElement> state = states.findElements(By.tagName("option"));
            Random num = new Random();
            int index=num.nextInt(state.size());
            state.get(index++).click();
        }catch(Exception e){
            return Constants.KEYWORD_FAIL +" - Could not select from list. "+ e.getMessage();

        }

        return Constants.KEYWORD_PASS;
    }

    public  String selectEvent(String object, String data){
        APP_LOGS.debug("Selecting Random event");
        try {
            WebElement eventList = driver.findElement(By.xpath("//*[@id='events-live']/ul"));
            List<WebElement> events = eventList.findElements(By.xpath("//*[@class='event-link']"));

            int time = Integer.parseInt(object);

            if (events.size() == 0) {
                APP_LOGS.debug("There's no events on the page");

            }else{
                events.get(time).click();
            }

        }catch(Exception e){
            return Constants.KEYWORD_FAIL +" - Could not select from list. "+ e.getMessage();

        }

        return Constants.KEYWORD_PASS;
    }
    
    
    

    public  String selectRandomEvent(String object, String data){
        APP_LOGS.debug("Selecting Random event");
        try {
            List<WebElement> events = driver.findElements(By.xpath("//*[@id='events-live']/ul/li[not(@style)]/a"));
            Random num = new Random();
            int index=num.nextInt(events.size());
            WebElement menu = events.get(index);
            Actions builder = new Actions(driver);
            builder.moveToElement(menu).build().perform();
            menu.click();


        }catch(Exception e){
            return Constants.KEYWORD_FAIL +" - Could not select from list. "+ e.getMessage();

        }

        return Constants.KEYWORD_PASS;
    }

    public  String selectProduct(String object, String data){
        APP_LOGS.debug("Selecting Random Product");

        int time = Integer.parseInt(object);

        try {
            List<WebElement> items = driver.findElements(By.xpath("//*[@class='product-image']"));
            if (items.size() == 0) {
                APP_LOGS.debug("There's no product on the page");
                driver.navigate().back();
                selectRandomEvent(object, data);
                selectRandomProduct(object, data);

            }else{
                items.get(time).click();
            }
        }catch(Exception e){
            return Constants.KEYWORD_FAIL +" - Could not select from list. "+ e.getMessage();

        }

        return Constants.KEYWORD_PASS;
    }

    public  String selectRandomProduct(String object, String data){
        APP_LOGS.debug("Selecting Random Product");
        try {
            List<WebElement> items = driver.findElements(By.xpath("//div[@id='grid-view']/div/div/div/a"));
            Random num = new Random();
            int index=num.nextInt(items.size());
            items.get(index).click();

        }catch(Exception e){
            return Constants.KEYWORD_FAIL +" - Could not select from list. "+ e.getMessage();

        }

        return Constants.KEYWORD_PASS;
    }

    public  String selectRandomColor(String object, String data){
        APP_LOGS.debug("Selecting Random Color");
        try {
            WebElement color = driver.findElement(By.xpath("//*[@id='attribute85']"));
            List<WebElement> selectColor = color.findElements(By.tagName("option"));
            selectColor.get(1).click();

        }catch(Exception e){
            return Constants.KEYWORD_FAIL +" - Could not select from list. "+ e.getMessage();

        }

        return Constants.KEYWORD_PASS;
    }

    public  String selectRandomSize(String object, String data){
        APP_LOGS.debug("Selecting Random Size");
        try {
            WebElement size = driver.findElement(By.xpath("//select[@id='Size'][@name='size']"));
            List<WebElement> selectSize = size.findElements(By.tagName("option"));
            selectSize.get(1).click();

        }catch(Exception e){
            return Constants.KEYWORD_FAIL +" - Could not select from list. "+ e.getMessage();

        }

        return Constants.KEYWORD_PASS;
    }


    public  String chrodKeys(String object,String data){
        APP_LOGS.debug("Selecting in text box");

        try{
            driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(Keys.chord(data));
        }catch(Exception e){
            return Constants.KEYWORD_FAIL+" Unable to select "+e.getMessage();

        }
        return Constants.KEYWORD_PASS;

    }

    public  String chrodKeysCss(String object,String data){
        APP_LOGS.debug("Selecting in text box");

        try{
            driver.findElement(By.cssSelector(OR.getProperty(object))).sendKeys(Keys.chord(data));
        }catch(Exception e){
            return Constants.KEYWORD_FAIL+" Unable to select "+e.getMessage();

        }
        return Constants.KEYWORD_PASS;

    }

    public  String clearInputText(String object,String data){
        APP_LOGS.debug("Clearing input text box");

        try{
            driver.findElement(By.xpath(OR.getProperty(object))).clear();
        }catch(Exception e){
            return Constants.KEYWORD_FAIL+" Unable to clear input text "+e.getMessage();

        }
        return Constants.KEYWORD_PASS;

    }

    public  String clearInputText_css(String object,String data){
        APP_LOGS.debug("Clearing input text box");

        try{
            driver.findElement(By.cssSelector(OR.getProperty(object))).clear();
        }catch(Exception e){
            return Constants.KEYWORD_FAIL+" Unable to clear input text "+e.getMessage();

        }
        return Constants.KEYWORD_PASS;

    }

    public  String mouseHover(String object, String data){
        APP_LOGS.debug("Mouse Hovering to the element");
        try{

            Thread.sleep(3000L);
            WebElement menu = driver.findElement(By.xpath(OR.getProperty(object)));
            Thread.sleep(2000L);
            Actions builder = new Actions(driver);
            builder.moveToElement(menu).clickAndHold().perform();
            Thread.sleep(2000L);
        }catch(Exception e){
            return Constants.KEYWORD_FAIL+"Unable to mouse hover"+e.getMessage();
        }
        return Constants.KEYWORD_PASS;

    }

    public  String mouseHoverCss(String object, String data){
        APP_LOGS.debug("Mouse Hovering to the element");
        try{

            Thread.sleep(3000L);
            WebElement menu = driver.findElement(By.cssSelector(OR.getProperty(object)));
            Actions builder = new Actions(driver);
            builder.moveToElement(menu).build().perform();

        }catch(Exception e){
            return Constants.KEYWORD_FAIL+"Unable to mouse hover"+e.getMessage();
        }
        return Constants.KEYWORD_PASS;

    }

    public  String doubleClick(String object, String data){
        APP_LOGS.debug("Double click on the element");
        try{

            Thread.sleep(3000L);
            WebElement menu = driver.findElement(By.xpath(OR.getProperty(object)));
            Actions builder = new Actions(driver);
            builder.doubleClick(menu).build().perform();

        }catch(Exception e){
            return Constants.KEYWORD_FAIL+"Unable to mouse hover"+e.getMessage();
        }
        return Constants.KEYWORD_PASS;

    }

/*    public String validateLogin(String object, String data){
        // object of the current test XLS
        // name of my current test case
        System.out.println("xxxxxxxxxxxxxxxxxxxxx");
        String data_flag = currentTestSuiteXLS.getCellData(currentTestCaseName, "Data_correctness",currentTestDataSetID );

        try{

            String expected=driver.findElement(By.xpath(OR.getProperty("myAccount_link"))).getText();
            String unexpected=driver.findElement(By.cssSelector(OR.getProperty("login_signIn_button_css"))).getText();


            if(expected.equals(null))
                if(!data_flag.equals(Constants.POSITIVE_DATA))
                    return Constants.KEYWORD_PASS;
                else
                    return Constants.KEYWORD_FAIL;


        }catch(Exception e){

        }


        // check for page title
        if(data_flag.equals(Constants.POSITIVE_DATA))
            return Constants.KEYWORD_PASS;
        else
            return Constants.KEYWORD_FAIL;
    }*/


/*	public String validateLogin(String object, String data){
    // object of the current test XLS
    // name of my current test case
        System.out.println("xxxxxxxxxxxxxxxxxxxxx");
        String data_flag=currentTestSuiteXLS.getCellData(currentTestCaseName, "Data_correctness",currentTestDataSetID );
        while(driver.findElements(By.xpath(OR.getProperty("image_login_process"))).size() !=0 ){
            try{
                String visiblity=driver.findElement(By.xpath(OR.getProperty("image_login_process"))).getAttribute("style");
                System.out.println("System Processing request - "+ visiblity);
                if(visiblity.indexOf("hidden") != -1){
                    // error message on screen
                    // YOUR WORK
                    String actualErrMsg=driver.findElement(By.xpath(OR.getProperty("error_login"))).getText();
                    //String expected=OR;
                    if(data_flag.equals(Constants.POSITIVE_DATA))
                     return Constants.KEYWORD_FAIL;
                    else
                     return Constants.KEYWORD_PASS;
                }

            }catch(Exception e){

            }
        }

        // check for page title
        if(data_flag.equals(Constants.POSITIVE_DATA))
             return Constants.KEYWORD_PASS;
        else
             return Constants.KEYWORD_FAIL;
    }
*/



    public String verifySearchResults(String object,String data){
        APP_LOGS.debug("Verifying the Search Results");
        try{
            data=data.toLowerCase();
            for(int i=3;i<=5;i++){
                String text=driver.findElement(By.xpath(OR.getProperty("search_result_heading_start")+i+OR.getProperty("search_result_heading_end"))).getText().toLowerCase();
                if(text.indexOf(data) == -1){
                    return Constants.KEYWORD_FAIL+" Got the text - "+text;
                }
            }

        }catch(Exception e){
            return Constants.KEYWORD_FAIL+"Error -->"+e.getMessage();
        }

        return Constants.KEYWORD_PASS;


    }

   
    
    
    public  String switchToFrame(String object,String data){
        APP_LOGS.debug("Switching perticular frame");

        try{
        	driver.switchTo().frame(driver.findElement(By.id(OR.getProperty(object))));
        }catch(Exception e){
            return Constants.KEYWORD_FAIL+" Unable to switch farme "+e.getMessage();

        }
        return Constants.KEYWORD_PASS;

    }

    
       // not a keyword

    public void captureScreenshot(String filename, String keyword_execution_result) throws IOException{
        // take screen shots
        if(CONFIG.getProperty("screenshot_everystep").equals("Y")){
            // capturescreen

            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") +"//screenshots//"+filename+".jpg"));

        }else if (keyword_execution_result.startsWith(Constants.KEYWORD_FAIL) && CONFIG.getProperty("screenshot_error").equals("Y") ){
            // capture screenshot
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") +"//screenshots//"+filename+".jpg"));
        }
       
    }
    
    //BBTKeywords
    public String postcodeselection(String object, String data) {
		APP_LOGS.debug("Selecting a postcode");
		try {
			driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(data);
			driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(
					Keys.SPACE);
			Thread.sleep(2000);
			driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(
					Keys.DOWN);
			Thread.sleep(2000);
			driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(
					Keys.ENTER);
			Thread.sleep(2000);
			driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(
					Keys.ENTER);
		} catch (Exception e) {
			return Constants.KEYWORD_FAIL
					+ "- Not able to select postcode functionality";

		}
		return Constants.KEYWORD_PASS;

	}

	/*public String authenticationhandle(String object, String data){
		APP_LOGS.debug("Authentication popup Handling");
		try {
			APP_LOGS.debug("Clicking on link ");
	            driver.findElement(By.xpath(OR.getProperty(object))).click();
	            Thread.sleep(1000L);
	            
			pause(object, "5");
			Screen screen = new Screen();
			String username = "BBTAdmin";
			String password = "88BigBu$";

			Pattern user = new Pattern(
					"D:/BBT_Automation_Freamwork/BBT_Automation_Framework/Authentications/usernamebbt.PNG");
			Pattern pass = new Pattern(
					"D:/BBT_Automation_Freamwork/BBT_Automation_Framework/Authentications/passwordbbt.PNG");
			screen.type(user, username);
			screen.type(Key.TAB);
			screen.type(pass, password);
			screen.type(Key.ENTER);

		} catch (Exception e) {
			return Constants.KEYWORD_FAIL + "Authentication got failed"
					+ e.getMessage();

		}
		return Constants.KEYWORD_PASS;

	}
*/
	//washingsquire
	public String washingsquire(String object, String data) {
		APP_LOGS.debug("Refreshing the Browser");
		try {

			Screen screen = new Screen();

			Pattern wsquire = new Pattern(
					"D:/BBT_Automation_Freamwork/BBT_Automation_Framework/Authentications/wsquire.PNG");

			screen.click(wsquire);

			Thread.sleep(8000);

		} catch (Exception e) {
			return Constants.KEYWORD_FAIL
					+ "Unable refresh the Browser and GFS is down"
					+ e.getMessage();

		}
		return Constants.KEYWORD_PASS;

	}
	
	
	public String quickadd1daytour(String object, String data) {
		APP_LOGS.debug("Refreshing the Browser");
		try {

			Screen screen = new Screen();

			Pattern onedaytour = new Pattern(
					"D:/BBT_Automation_Freamwork/BBT_Automation_Framework/Authentications/quickbook1daytour.PNG");

			screen.click(onedaytour);

			Thread.sleep(8000);

		} catch (Exception e) {
			return Constants.KEYWORD_FAIL
					+ "Unable refresh the Browser and GFS is down"
					+ e.getMessage();

		}
		return Constants.KEYWORD_PASS;

	}
	public String seealltickets(String object, String data) {
		APP_LOGS.debug("Refreshing the Browser");
		try {

			Screen screen = new Screen();

			Pattern seeallticket = new Pattern(
					"D:/BBT_Automation_Freamwork/BBT_Automation_Framework/Authentications/SeeallTickets.PNG");

			screen.click(seeallticket);

			Thread.sleep(2000);

		} catch (Exception e) {
			return Constants.KEYWORD_FAIL
					+ "Unable refresh the Browser and GFS is down"
					+ e.getMessage();

		}
		return Constants.KEYWORD_PASS;

	}

	public String addticket(String object, String data) {
		APP_LOGS.debug("Refreshing the Browser");
		try {

			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,550)", "");

			Thread.sleep(4000);

			Screen screen = new Screen();

			Pattern addticket = new Pattern(
					"D:/BBT_Automation_Freamwork/BBT_Automation_Framework/Authentications/addticket1.PNG");

			screen.click(addticket);

			Thread.sleep(8000);

		} catch (Exception e) {
			return Constants.KEYWORD_FAIL
					+ "Unable refresh the Browser and GFS is down"
					+ e.getMessage();

		}
		return Constants.KEYWORD_PASS;

	}
	public String scroll(String object, String data) {
		APP_LOGS.debug("Refreshing the Browser");
		try {
			
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			
			
			jse.executeScript("window.scrollBy(0,250)", "");

		} catch (Exception e) {
			return Constants.KEYWORD_FAIL
					+ "Unable refresh the Browser and GFS is down"
					+ e.getMessage();

		}
		return Constants.KEYWORD_PASS;

	}

	public String scrolldown(String object, String data) {
		APP_LOGS.debug("Scrolling down");
		try {
			
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.scrollBy(0,850)", "");
			
			jse.executeScript("window.scrollBy(0,850)", "");
			
			jse.executeScript("window.scrollBy(0,450)", "");

		} catch (Exception e) {
			return Constants.KEYWORD_FAIL
					+ "Unable refresh the Browser and GFS is down"
					+ e.getMessage();

		}
		return Constants.KEYWORD_PASS;

	}
	public String scrollup(String object, String data) {
		APP_LOGS.debug("Refreshing the Browser");
		try {
			
			JavascriptExecutor jse = (JavascriptExecutor) driver;
		
			
			jse.executeScript("window.scrollBy(0,-450)", "");

		} catch (Exception e) {
			return Constants.KEYWORD_FAIL
					+ "Unable refresh the Browser and GFS is down"
					+ e.getMessage();

		}
		return Constants.KEYWORD_PASS;

	}

	public String refresh(String object, String data) {
		APP_LOGS.debug("Refreshing the Browser");
		try {
			driver.navigate().refresh();
		} catch (Exception e) {
			return Constants.KEYWORD_FAIL
					+ "Unable refresh the Browser and GFS is down"
					+ e.getMessage();

		}
		return Constants.KEYWORD_PASS;

	}

	public String textverify(String object, String data) {
		APP_LOGS.debug("Verifying the text");
		try {
			String actual = driver
					.findElement(By.xpath(OR.getProperty(object)))
					.getText();
			String expected = data;

			if (actual.equals(expected))
				return Constants.KEYWORD_PASS + " -- text verified " + actual
						+ " -- " + expected;
			else
				return Constants.KEYWORD_FAIL + " -- text not verified "
						+ actual + " -- " + expected;
		} catch (Exception e) {
			return Constants.KEYWORD_FAIL + " Object not found "
					+ e.getMessage();
		}

	}
	
	
	public void captureScreenshotNew(String filename, String keyword_execution_result) throws IOException{
        // take screen shots
		File dir = new File(System.getProperty("user.dir")+"//screenshots");
		dir.mkdir();
        if(CONFIG.getProperty("screenshot_everystep").equals("Y")){
            // capturescreen
        	Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(25)).takeScreenshot(driver);
			
			ImageIO.write(screenshot.getImage(),"JPG",new File(System.getProperty("user.dir") +"//screenshots//"+filename+".jpg"));
        }else if (keyword_execution_result.startsWith(Constants.KEYWORD_FAIL) && CONFIG.getProperty("screenshot_error").equals("Y") ){
		 	Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(25)).takeScreenshot(driver);
			ImageIO.write(screenshot.getImage(),"JPG",new File(System.getProperty("user.dir") +"//screenshots//"+filename+".jpg"));
			
       
    }
       }
	   
	public String openBrowser2(String object, String data) throws MalformedURLException 
	{  
	
		APP_LOGS.debug("Opening browser"); 
		if (data.equals("Mozilla"))
		{      
			driver = new FirefoxDriver();
			} 
		else if (data.equals("IE")) 
		{   // You may need to change the code here to start IE Driver
			driver = new InternetExplorerDriver();
			} 
	
		else if (data.equals("Chrome"))
		{   
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "/chromedriver.exe");   
			driver = new ChromeDriver();  
			} 
	
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();  
		long implicitWaitTime = Long.parseLong(CONFIG.getProperty("implicitwait"));  
		driver.manage().timeouts().implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);
		

		return Constants.KEYWORD_PASS;
		}
	

	public String switchToFrameByIndex(String object, String data) {
		APP_LOGS.debug("Switching perticular frame");

		try {
			driver.switchTo().frame(0);
		} catch (Exception e) {
			return Constants.KEYWORD_FAIL + " Unable to switch frame "
					+ e.getMessage();

		}
		return Constants.KEYWORD_PASS;

	}

	

	public String uniqueSelect(String object, String data) {
		APP_LOGS.debug("Clicking one element out of list");
		try {
			List<WebElement> stores = driver.findElements(By.xpath(OR
					.getProperty(object)));
			int count = stores.size();

			for (WebElement store : stores) {

				Actions act = new Actions(driver);
				act.moveToElement(store).click().build().perform();
				break;
			}
		} catch (Exception e) {
			return Constants.KEYWORD_FAIL
					+ " Not able to click element from list " + e.getMessage();

		}
		return Constants.KEYWORD_PASS;

	}


	 
	
		public String SelectfromList(String object, String data) {

			APP_LOGS.debug("Handling the Date picker");
			try {

				List<WebElement> allDates = driver.findElements(By.xpath(OR
						.getProperty(object)));
				APP_LOGS.debug("Loop through the all dates and select the one that matches");
				for (WebElement opt : allDates) {
					if (opt.getText().equalsIgnoreCase(data)) {
						opt.click();
						
					}
				}
			} catch (Exception e) {
				return Constants.KEYWORD_FAIL
						+ "Unable to handle the Calender control" + e.getMessage();
			}
			return Constants.KEYWORD_PASS;

		}
		
		
		
		
		public  String RegisterEmail(String object,String data)
		{
			APP_LOGS.debug("Entering randomly generated Email");
		try{
			WebElement email=driver.findElement(By.xpath(OR.getProperty(object)));
			email.sendKeys(randomEmail());
		}catch(Exception e){
			return Constants.KEYWORD_FAIL
					+ "Unable to handle the Calender control" + e.getMessage();
		}
	        
		return Constants.KEYWORD_PASS;
		}
		
	        private static String randomEmail() {
	            return  UUID.randomUUID().toString() + "@gmail.com";
	        }
	        
	        
	        public  String RandomString(String object,String data){
		        APP_LOGS.debug("Entering Random String");
		        try{
		         final int RANDOM_STRING_LENGTH = 6;
			
		driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(RandomStringUtils.randomAlphabetic(RANDOM_STRING_LENGTH));

		        }catch(Exception e){
		            return Constants.KEYWORD_FAIL +" - Random String not entered "+ e.getMessage();

		        }
		        return Constants.KEYWORD_PASS;

		    }
			
			public  String selectRandom(String object,String data){
		        APP_LOGS.debug("Selecting Random item from dropdown");
		        try{
		        
		        	 WebElement droplist= driver.findElement(By.xpath(OR.getProperty(object)));
		        	 Select sel=new Select(droplist);
		        	 List<WebElement> lst=sel.getOptions();
		        	 int count=lst.size();
		        	 Random num=new Random();
		        	 int isel=num.nextInt(count);
		        	 sel.selectByIndex(isel);
		        	 
		        
		        }catch(Exception e){
		            return Constants.KEYWORD_FAIL +" - cannot select the dropdown random"+ e.getMessage();

		        }
		        return Constants.KEYWORD_PASS;

		    }
			

			public String mouseHover1(String object, String data) {
				APP_LOGS.debug("Mouse Hovering to the element");
				try {

					Thread.sleep(3000L);
					WebElement menu = driver.findElement(By.xpath(OR
							.getProperty(object)));
					Thread.sleep(2000L);
					Actions builder = new Actions(driver);
					builder.moveToElement(menu).build().perform();
					Thread.sleep(2000L);
				} catch (Exception e) {
					return Constants.KEYWORD_FAIL + "Unable to mouse hover"
							+ e.getMessage();
				}
				return Constants.KEYWORD_PASS;

			}


			public String switchToFrameByName(String object, String data) {
				APP_LOGS.debug("Switching perticular frame");

				try {
					driver.switchTo().frame(
							driver.findElement(By.name(OR.getProperty(object))));
				} catch (Exception e) {
					return Constants.KEYWORD_FAIL + " Unable to switch frame "
							+ e.getMessage();

				}
				return Constants.KEYWORD_PASS;

			}

		public  String scrollToElement(String object,String data){
				        APP_LOGS.debug("Page scroll to a particular element");
				        try{
							WebElement element=driver.findElement(By.xpath(OR.getProperty(object)));
				        	 ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",element);
				        	 
				        
				        }catch(Exception e){
				            return Constants.KEYWORD_FAIL +" - cannot scroll the page to particular element"+ e.getMessage();

				        }
				        return Constants.KEYWORD_PASS;

				    }
		
		public String ArrowKeyDown(String object, String data) {
			  APP_LOGS.debug("Clicking Arrow Down key");
			  try {
			   Robot r = new Robot();
			   r.keyPress(KeyEvent.VK_DOWN);
			  } catch (Exception e) {
			   return Constants.KEYWORD_FAIL + "Unable to click, Arrow Down "
			     + e.getMessage();
			  }
			  return Constants.KEYWORD_PASS;

			 }
			        
			    }	


