package org.exoplatform.selenium.platform.wiki.sniff;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.exoplatform.selenium.platform.EXO_TAG_IDENTIFIER;
import org.exoplatform.selenium.platform.wiki.WikiUtil;
import org.exoplatform.selenium.platform.wiki.WikiUtil.PopupItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

/**
 * @author khanhnt
 *
 */

public class Wiki_BasicAction_ManagePage {
	
	final String LOG_PATH = "/home/khanhnt/workspace/exoplatform/log/";
	final String SERVER_PATH="http://localhost:8080/portal/intranet/home";

	// WebDriver ieDriver;

	public Wiki_BasicAction_ManagePage() {
		super();
		// TODO Auto-generated constructor stub
		System.setProperty("webdriver.chrome.driver",
				"/home/khanhnt/working/env/chromedriver");
		//driver = new driver();
		
		
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
		WebDriver driver = new FirefoxDriver();
		WebDriver chromeDriver;
		Wiki_BasicAction_ManagePage wiki = new Wiki_BasicAction_ManagePage();
		EXO_TAG_IDENTIFIER.login(driver);
		//wiki.test01_AddPage_AutoSaveWhenAddingPage(driver);
		//wiki.test02_AddPage_AutoSaveWhenAddingPageFromTemplate(driver);
		//System.out.println("#################Done test 02####################");
		//wiki.test03_AddPage_CreatePageFromTemplate(driver);
		//System.out.println("#################Done test 03####################");
		//wiki.test04_AddPage_CreatePageUsingRichTextEditor(driver);
		//System.out.println("#################Done test 04####################");
		//wiki.test05_AddPage_CreatePageUsingSourceEditor(driver);
		//wiki.test06_AddPage_PreviewTemplateWhenAddingNewPageFromTemplate(driver);
		//wiki.test07_AddPage_ResumeADraftWithSaveAsNormal(driver);
		//wiki.test08_DeletePage_DeleteADraft(driver);
		wiki.test09_DeletePage_DeletePageWithRichTextEditor(driver);
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
	public void test01_AddPage_AutoSaveWhenAddingPage(WebDriver driver) {
		    
		WikiUtil.navigateToWikiHome(driver);
		
		WikiUtil.pause(5000);
		
		WikiUtil.addBlankPage_WikiHome(driver);
		
		WikiUtil.pause(5000);
		

		//Add title
		WikiUtil.changeTitle_WikiPage_WikiHome(driver, "test01_AddPage_AutoSaveWhenAddingPage title");
		//Add content
		WikiUtil.changeContent_WikiPage_WikiHome(driver, "test01_AddPage_AutoSaveWhenAddingPage conten");
		
		WikiUtil.pause(30000);
		
		
		 //Get message 
		String msg= driver.findElement(By.className("btn-toolbar"))
	      .findElement(By.className("uiWikiPageEditForm_MessageArea")).getText();
	     
		if(msg.contains("Draft saved at "))
			System.out.println("Test 01 pass");
		else
			System.out.println("Test 01 not done");

	}

	/**
	 * Created by khanhnt at Nov 12, 2013
	 */
	public void test02_AddPage_AutoSaveWhenAddingPageFromTemplate(WebDriver driver) {
		
	    
		WikiUtil.navigateToWikiHome(driver);
				
		WikiUtil.pause(5000);	
		
		WikiUtil.addPage_FromTemplate_WikiHome(driver,"Two-Column_Layout");
		
		WikiUtil.pause(5000);
		
		//Add title
		WikiUtil.changeTitle_WikiPage_WikiHome(driver, "test01_AddPage_AutoSaveWhenAddingPage title");
		//Add content
		WikiUtil.changeContent_WikiPage_WikiHome(driver, "test01_AddPage_AutoSaveWhenAddingPage conten");
		
		WikiUtil.pause(30000);
		
		String msg= driver.findElement(By.className("btn-toolbar"))
			      .findElement(By.className("uiWikiPageEditForm_MessageArea")).getText();
			     
		if(msg.contains("Draft saved at "))
			System.out.println("Test 02 pass");
		else
			System.out.println("Test 02 not done");
		
	}

	
	/**
	 * Created by khanhnt at Nov 12, 2013
	 */
	public void test03_AddPage_CreatePageFromTemplate(WebDriver driver) {
		//EXO_TAG_IDENTIFIER.login(driver);
	    
		WikiUtil.navigateToWikiHome(driver);
				
		//Wait for wiki page load
		WikiUtil.pause(5000);
		
		WikiUtil.addPage_FromTemplate_WikiHome(driver,"Two-Column_Layout");
		
		//Wait for wiki page load
		WikiUtil.pause(5000);
				
		WikiUtil.saveWikiPage_WikiHome(driver);

	}

	/**
	 * Created by khanhnt at Nov 12, 2013
	 */
	public void test04_AddPage_CreatePageUsingRichTextEditor(WebDriver driver) {	    
		WikiUtil.navigateToWikiHome(driver);				

		WikiUtil.pause(5000);
		
		WikiUtil.addBlankPage_WikiHome(driver);
		
		WikiUtil.pause(5000);
		
		WikiUtil.getButton_WikiPageEditForm(driver, "Rich Text").click();		

		WikiUtil.pause(5000);
		
		/*WikiUtil.getMenuBarItem_WikiRichTextEditor(driver, "Link").click();

		WikiUtil.pause(5000);
		
		//create a link to wiki home
		WikiUtil.addLink_RichTextEditor_WikiPage(
				driver,
				PopupItem.WIKI_PAGE_SELECT_PAGE_IN_ALL_PAGES,
				"WikiHome",
				"testLabel",
				"Wiki Home",
				false);*/
		//Add image 
		
		WikiUtil.getMenuBarItem_WikiRichTextEditor(driver, "Image").click();

		WikiUtil.pause(5000);
		
		WikiUtil.addImage_RichTextEditor_WikiPage(driver, PopupItem.ATTACHED_IMAGE_UPLOAD_NEW_CURRENT_PAGE);
	}

	/**
	 * Created by khanhnt at Nov 12, 2013
	 */
	public void test05_AddPage_CreatePageUsingSourceEditor(WebDriver driver) {
		WikiUtil.navigateToWikiHome(driver);
	
	
		//Wait for wiki page load
		WikiUtil.pause(5000);
		
		WikiUtil.addBlankPage_WikiHome(driver);
		
		WikiUtil.pause(5000);
		
		//Add title
		WikiUtil.changeTitle_WikiPage_WikiHome(driver, "test05_AddPage_CreatePageUsingSourceEditor title");
		//Add content
		WikiUtil.changeContent_WikiPage_WikiHome(driver, "test05_AddPage_CreatePageUsingSourceEditor content");
		
		//Wait for wiki page load
		WikiUtil.pause(5000);
				
		WikiUtil.saveWikiPage_WikiHome(driver);
	}

	/**
	 * Created by khanhnt at Nov 12, 2013
	 */
	public void test06_AddPage_PreviewTemplateWhenAddingNewPageFromTemplate(WebDriver driver) {
		  
				WikiUtil.navigateToWikiHome(driver);
						
				//Wait for wiki page load
				WikiUtil.pause(5000);
				
				WikiUtil.previewTemplate(driver);
	}
	/**
	 * Created by khanhnt at Nov 12, 2013
	 */
	public void test07_AddPage_ResumeADraftWithSaveAsNormal(WebDriver driver) {
		WikiUtil.navigateToWikiHome(driver);
		
		WikiUtil.pause(5000);
		
		WikiUtil.addBlankPage_WikiHome(driver);
		
		WikiUtil.pause(5000);
		
		//Add title
		WikiUtil.changeTitle_WikiPage_WikiHome(driver, "test07_AddPage_AutoSaveWhenAddingPage title");
		//Add content
		WikiUtil.changeContent_WikiPage_WikiHome(driver, "test07_AddPage_AutoSaveWhenAddingPage conten");
		
		WikiUtil.pause(30000);
		driver.close();
		
		WebDriver tmpDriver = new FirefoxDriver();
		EXO_TAG_IDENTIFIER.login(tmpDriver);
		WikiUtil.navigateToWikiHome(tmpDriver);
		
		WikiUtil.pause(5000);
		
		WikiUtil.openDraffPage(tmpDriver,"test07_AddPage_AutoSaveWhenAddingPage title");
		
		WikiUtil.pause(5000);
		
		//Add title
		WikiUtil.changeTitle_WikiPage_WikiHome(tmpDriver, "New test07_AddPage_AutoSaveWhenAddingPage title");
		//Add content
		WikiUtil.changeContent_WikiPage_WikiHome(tmpDriver, "New test07_AddPage_AutoSaveWhenAddingPage conten");
		
		WikiUtil.saveWikiPage_WikiHome(tmpDriver);
		
	}

	/**
	 * Created by khanhnt at Nov 12, 2013
	 */
	public void test08_DeletePage_DeleteADraft(WebDriver driver) {
		
		WikiUtil.navigateToWikiHome(driver);
		
		WikiUtil.pause(5000);
		
		WikiUtil.deleteDraffPage(driver,"test07_AddPage_AutoSaveWhenAddingPage title");
		
		WikiUtil.pause(5000);
	}

	/**
	 * Created by khanhnt at Nov 12, 2013
	 */
	public void test09_DeletePage_DeletePageWithRichTextEditor(WebDriver driver) {
		WikiUtil.navigateToWikiHome(driver);
		
		WikiUtil.pause(5000);
		
		WikiUtil.deleteExistingWikiPage(driver,"Two-Column Layout");
		
		
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
