package org.exoplatform.selenium.platform.wiki.sniff;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.exoplatform.selenium.platform.EXO_TAG_IDENTIFIER;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

/**
 * @author khanhnt
 *
 */

public class Wiki_BasicAction_ManagePage {
	WebDriver firefoxDriver;
	WebDriver chromeDriver;
	final String LOG_PATH = "/home/khanhnt/workspace/exoplatform/log/";
	final String SERVER_PATH="http://localhost:8080/portal/intranet/home";

	// WebDriver ieDriver;

	public Wiki_BasicAction_ManagePage() {
		super();
		// TODO Auto-generated constructor stub
		System.setProperty("webdriver.chrome.driver",
				"/home/khanhnt/working/env/chromedriver");
		this.firefoxDriver = new FirefoxDriver();
		//this.chromeDriver = new ChromeDriver();
	}

	private void Wiki_BasicAction_ManagePage_LogFileCreate(String fileName,
			String content) {
		try {
			PrintWriter writer = new PrintWriter(this.LOG_PATH + fileName,
					"UTF8");
			writer.println(content);
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		Wiki_BasicAction_ManagePage wiki = new Wiki_BasicAction_ManagePage();
		//wiki.test01_AddPage_AutoSaveWhenAddingPage();
		wiki.test02_AddPage_AutoSaveWhenAddingPageFromTemplate();
	}
	 public void waitForPageLoaded(WebDriver driver) {

	     ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
	        public Boolean apply(WebDriver driver) {
	          return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
	        }
	      };

	     Wait<WebDriver> wait = new WebDriverWait(driver,5);
	      try {
	              wait.until(expectation);
	      } catch(Throwable error) {
	    	  //assertFalse("Timeout waiting for Page Load Request to complete.",true);
	      }
	 } 

	/**
	 * Created by khanhnt at Nov 12, 2013
	 */
	public void test01_AddPage_AutoSaveWhenAddingPage() {
		
		EXO_TAG_IDENTIFIER.login(this.firefoxDriver);
	    
		//Get left navigations
		WebElement uiCompanyNavigations = this.firefoxDriver.
				findElement(By.className("uiCompanyNavigations"));
		
		//Click on Wiki
		uiCompanyNavigations.findElement(By.linkText("Wiki")).click();
		
		//Wait for wiki page load
		this.waitForPageLoaded(this.firefoxDriver);
		
		//Get wiki page control area
		WebElement uiWikiPageContentArea  = this.firefoxDriver.findElement(By.id("UIWikiPageControlArea"));	
		
		//Get page tool bar of wiki page control area 
		WebElement uiWikiPageControlArea_PageToolBar = uiWikiPageContentArea.
				findElement(By.id("UIWikiPageControlArea_PageToolBar"));
		
		//Get trees buttons Edit, Add Page and More
		List<WebElement> buttons = uiWikiPageControlArea_PageToolBar.findElements(By.tagName("div"));
		
		//Loop trees buttons to find Add Page
		for (WebElement button : buttons) {
			if(button.getText().equalsIgnoreCase("Add Page")){
				button.click();
				
				//Click on Add Page drop menu
				uiWikiPageControlArea_PageToolBar.findElement(By.className("uiIconAddPage")).click();
				
				//Wait for wiki edit page load
				this.waitForPageLoaded(this.firefoxDriver);

				//Add title
				this.firefoxDriver.findElement(By.className("uiColsLeftsEditForm"))
				.findElement(By.id("UIWikiPageTitleControlForm_PageEditForm"))
				.findElement(By.id("titleInput")).sendKeys("test01_AddPage_AutoSaveWhenAddingPage title");
				
				//Add content
				this.firefoxDriver.findElement(By.className("uiColsLeftsEditForm"))
				.findElement(By.id("Markup")).sendKeys("test01_AddPage_AutoSaveWhenAddingPage conten");
				
			
				//wait 30 seconds
				ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			        public Boolean apply(WebDriver driver) {
			        	return false;	        	
			        }
			      };

			     Wait<WebDriver> wait = new WebDriverWait(this.firefoxDriver,30);
			      try {
			              wait.until(expectation);
			      } catch(Throwable error) {
			      }
				
			    //Get message 
				String msg= this.firefoxDriver.findElement(By.className("btn-toolbar"))
			      .findElement(By.className("uiWikiPageEditForm_MessageArea")).getText();
			     
				if(msg.contains("Draft saved at "))
					System.out.println("Test 01 pass");
				else
					System.out.println("Test 01 not done");
				break;
			}
		}

	}

	/**
	 * Created by khanhnt at Nov 12, 2013
	 */
	public void test02_AddPage_AutoSaveWhenAddingPageFromTemplate() {
EXO_TAG_IDENTIFIER.login(this.firefoxDriver);
	    
		//Get left navigations
		WebElement uiCompanyNavigations = this.firefoxDriver.
				findElement(By.className("uiCompanyNavigations"));
		
		//Click on Wiki
		uiCompanyNavigations.findElement(By.linkText("Wiki")).click();
		
		//Wait for wiki page load
		this.waitForPageLoaded(this.firefoxDriver);
		
		//Get wiki page control area
		WebElement uiWikiPageContentArea  = this.firefoxDriver.findElement(By.id("UIWikiPageControlArea"));	
		
		//Get page tool bar of wiki page control area 
		WebElement uiWikiPageControlArea_PageToolBar = uiWikiPageContentArea.
				findElement(By.id("UIWikiPageControlArea_PageToolBar"));
		
		//Get three buttons Edit, Add Page and More
		List<WebElement> buttons = uiWikiPageControlArea_PageToolBar.findElements(By.tagName("div"));
		
		for (WebElement button : buttons) {
			if(button.getText().equalsIgnoreCase("Add Page")){
				
				button.click();
				
				uiWikiPageControlArea_PageToolBar.findElement
						(By.className("uiIconAddPageFromTemplate")).click();
				
				this.waitForPageLoaded(this.firefoxDriver);

				for (String handle : this.firefoxDriver.getWindowHandles()) {
					this.firefoxDriver.switchTo().window(handle);
					
					//Get  Wiki Popup template Form
					WebElement UIWikiSelectTemplateForm = this.firefoxDriver.
							findElement(By.id("UIWikiSelectTemplateForm"));
					
					if(UIWikiSelectTemplateForm !=null){
						//Get list of radio button
						List<WebElement> radios = UIWikiSelectTemplateForm.
								findElement(By.id("UIWikiTemplateGrid"))
						.findElements(By.className("uiRadio"));
						
						//Loop radios to choice expected radio
						for (int i=0;i<radios.size();i++) {
							
							WebElement radio = radios.get(i);
							try{
								WebElement realRadio = radio.findElement(By.tagName("input"));
						       if(realRadio.getAttribute("value").equalsIgnoreCase("Two-Column_Layout")){
						    	   realRadio.click();
						    	   
						    	   //Get Select button
								   List<WebElement> divs= UIWikiSelectTemplateForm.findElements(By.tagName("div"));
								   for (WebElement div : divs) {
									   
										if(div.getAttribute("class").equalsIgnoreCase("uiAction uiActionBorder")){
											
											div.findElement(By.linkText("Select")).click();
											
											this.waitForPageLoaded(this.firefoxDriver);
											
											//Add title
											this.firefoxDriver.findElement(By.className("uiColsLeftsEditForm"))
											.findElement(By.id("UIWikiPageTitleControlForm_PageEditForm"))
											.findElement(By.id("titleInput")).sendKeys("test01_AddPage_AutoSaveWhenAddingPage title");
											
											//Add content
											this.firefoxDriver.findElement(By.className("uiColsLeftsEditForm"))
											.findElement(By.id("Markup")).sendKeys("test01_AddPage_AutoSaveWhenAddingPage conten");
											
											ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
										        public Boolean apply(WebDriver driver) {
										        	return false;	        	
										        }
										      };

										     Wait<WebDriver> wait = new WebDriverWait(this.firefoxDriver,30);
										      try {
										              wait.until(expectation);
										      } catch(Throwable error) {
										      }
											
										    
											String msg= this.firefoxDriver.findElement(By.className("btn-toolbar"))
										      .findElement(By.className("uiWikiPageEditForm_MessageArea")).getText();
										     
											if(msg.contains("Draft saved at "))
												System.out.println("Test 02 pass");
											else
												System.out.println("Test 02 not done");
										     
											return;
										}
									}
								}
							}catch(Exception e){
								e.printStackTrace();
							}
						}
					}
				}
				
			}
		}
	}

	
	/**
	 * Created by khanhnt at Nov 12, 2013
	 */
	public void test03_AddPage_CreatePageFromTemplate() {
	}

	/**
	 * Created by khanhnt at Nov 12, 2013
	 */
	public void test04_AddPage_CreatePageUsingRichTextEditor() {
	}

	/**
	 * Created by khanhnt at Nov 12, 2013
	 */
	public void test05_AddPage_CreatePageUsingSourceEditor() {
	}

	/**
	 * Created by khanhnt at Nov 12, 2013
	 */
	public void test06_AddPage_PreviewTemplateWhenAddingNewPageFromTemplate() {
	}

	/**
	 * Created by khanhnt at Nov 12, 2013
	 */
	public void test07_AddPage_ResumeADraftWithSaveAsNormal() {
	}

	/**
	 * Created by khanhnt at Nov 12, 2013
	 */
	public void test08_DeletePage_DeleteADraft() {
	}

	/**
	 * Created by khanhnt at Nov 12, 2013
	 */
	public void test09_DeletePage_DeletePageWithRichTextEditor() {
	}

	/**
	 * Created by khanhnt at Nov 12, 2013
	 */
	public void test10_DeletePage_DeletePageWithSourceEditor() {
	}

	/**
	 * Created by khanhnt at Nov 12, 2013
	 */
	public void test11_DeletePage_DeletePageWithTemplateLayout() {
	}

	/**
	 * Created by khanhnt at Nov 12, 2013
	 */
	public void test12_EditPage_AutoSaveWhenEditingPage() {
	}

	/**
	 * Created by khanhnt at Nov 12, 2013
	 */
	public void test13_EditPage_EditPageWithRichTextEditor() {
	}

	/**
	 * Created by khanhnt at Nov 12, 2013
	 */
	public void test14_EditPage_EditPageWithTemplateLayout() {
	}

	/**
	 * Created by khanhnt at Nov 12, 2013
	 */
	public void test15_EditPage_EditPageWhenPublishActivityIsChecked() {
	}

	/**
	 * Created by khanhnt at Nov 12, 2013
	 */
	public void test16_EditPage_EditPageWhenPublishActivityIsNotChecked() {
	}

	/**
	 * Created by khanhnt at Nov 12, 2013
	 */
	public void test17_EditPage_EditPageWithSourceEditor() {
	}

	/**
	 * Created by khanhnt at Nov 12, 2013
	 */
	public void test18_EditPage_EditParagraphInPage() {
	}

	public void test19_Rename_RenamePage() {
	}

}
