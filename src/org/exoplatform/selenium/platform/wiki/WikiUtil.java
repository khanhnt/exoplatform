/**
 * Created by khanhnt at 3:32:07 PM Nov 13, 2013 
 * 
 */
package org.exoplatform.selenium.platform.wiki;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author khanhnt
 *
 */
/**
 * @author khanhnt
 *
 */
public class WikiUtil {
	
	
	
	private static WebElement getLeftNavigation(WebDriver driver){
		WebElement UIPortal = driver.findElement(By.id("UIPortal"));
		WebElement NavigationBody = UIPortal.findElement(By.id("NavigationBody"));
		WebElement LeftNavigation = NavigationBody.findElement(By.id("LeftNavigation"));
		return LeftNavigation;
	}
	
	private static WebElement getRightPageBody(WebDriver driver){
		WebElement UIPortal = driver.findElement(By.id("UIPortal"));
		WebElement NavigationBody = UIPortal.findElement(By.id("NavigationBody"));
		WebElement UIPage = NavigationBody.findElement(By.id("UIPage"));
		return UIPage;
	}
	
	private static WebElement getUIWikiUpperArea(WebDriver driver){
		try{
			WebElement pageBody = getRightPageBody(driver);
			WebElement UIWikiUpperArea = pageBody.findElement(By.id("UIWikiUpperArea"));
			return UIWikiUpperArea;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	private static WebElement getUIWikiMiddleArea(WebDriver driver){
		try{
			WebElement pageBody = getRightPageBody(driver);
			WebElement UIWikiUpperArea = pageBody.findElement(By.id("UIWikiMiddleArea"));
			return UIWikiUpperArea;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	private static WebElement getItem_LeftNavigationPortlet(WebDriver driver, String linkText){
		try{
			WebElement LeftNavigation = getLeftNavigation(driver);
			WebElement LeftNavigationPortlet = LeftNavigation.findElement(By.id("LeftNavigationPortlet"));
			WebElement uiCompanyNavigations = LeftNavigationPortlet.findElement(By.className("uiCompanyNavigations"));
			WebElement item = uiCompanyNavigations.findElement(By.linkText(linkText));
			return item;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static void navigateToWikiHome(WebDriver driver){
		getItem_LeftNavigationPortlet(driver, "Wiki").click();
	}
	
	/**
	 * Created by khanhnt at Nov 14, 2013
	 * Get Text box of Wiki Page to edit
	 * @param driver
	 * @return
	 */
	public static WebElement getUIWikiPageTitleControlForm(WebDriver driver){
		try{
			WebElement middleArea = getUIWikiMiddleArea(driver);
			WebElement uiWikiPageEditForm = middleArea.findElement(By.id("uiWikiPageEditForm"));
			WebElement title = uiWikiPageEditForm.findElement(By.id("UIWikiPageTitleControlForm_PageEditForm"))
					.findElement(By.id("titleInput"));
			return title;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Created by khanhnt at Nov 14, 2013.
	 * Get the buttons : Rich Text or Source Editor, 
	 * Preview, Help, Save, Cancel
	 * just below of title text box
	 * @param driver
	 * @param name : Rich Text or Source Editor or Preview or Help or Save or Cancel
	 * @return: WebElement of button
	 */
	public static WebElement getButton_WikiPageEditForm(WebDriver driver, String name){
		try{
			WebElement middleArea = getUIWikiMiddleArea(driver);
			WebElement uiWikiPageEditForm = middleArea.findElement(By.id("UIWikiPageEditForm"));
			WebElement actionContainer = uiWikiPageEditForm.findElement(By.className("actionContainer"));
			List<WebElement> buttons = actionContainer.findElements(By.tagName("button"));
			for (WebElement button : buttons) {
				if(button.getText().equalsIgnoreCase(name))
					return button;
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	/**
	 * Created by khanhnt at Nov 14, 2013
	 * Get item on menu bar of wiki rich text editor:
	 * Link, Image, Table, Macro
	 * @param driver
	 * @param name : Link, Image, Table, Macro
	 * @return WebElement of this item
	 */
	public static WebElement getMenuBarItem_WikiRichTextEditor(WebDriver driver, String name){
		try {
			WebElement middleArea = getUIWikiMiddleArea(driver);
			WebElement uiWikiPageEditForm = middleArea.findElement(By.id("UIWikiPageEditForm"));
			WebElement xRichTextEditor = uiWikiPageEditForm.findElement(By.className("xRichTextEditor"));
			List<WebElement> divs = xRichTextEditor.findElements(By.tagName("div"));
			
			for (WebElement div : divs) {
				if(div.getAttribute("class").equalsIgnoreCase("gwt-MenuBar gwt-MenuBar-horizontal")){
					List<WebElement> tds = div.findElements(By.tagName("td"));					
					for (WebElement td : tds) {
						WebElement item = td.findElement(By.tagName("div"));						
						if(item.getText().equalsIgnoreCase(name))
							return item;
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * Created by khanhnt at Nov 14, 2013
	 * Get rich text area of Wiki Rich text editor
	 * @param driver
	 * @return
	 */
	public static WebElement getRichTextArea_WikiRichTextEditor(WebDriver driver){
		try {
			WebElement middleArea = getUIWikiMiddleArea(driver);
			WebElement uiWikiPageEditForm = middleArea.findElement(By.id("UIWikiPageEditForm"));
			WebElement xRichTextEditor = uiWikiPageEditForm.findElement(By.className("xRichTextEditor"));
			WebElement richTextArea = xRichTextEditor.findElement(By.tagName("iframe"));
			WebElement body = richTextArea.findElement(By.id("body"));
			return body;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Created by khanhnt at Nov 14, 2013
	 * @param driver
	 * @param name
	 * @return
	 */
	public static WebElement getToolBarItem_WikiRichTextEditorx(WebDriver driver, String name){
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	
	/**
	 * Created by khanhnt at Nov 14, 2013
	 * @param driver
	 * @param title
	 */
	public static void changeTitle_WikiPage_WikiHome(WebDriver driver, String title){
		driver.findElement(By.className("uiColsLeftsEditForm"))
		.findElement(By.id("UIWikiPageTitleControlForm_PageEditForm"))
		.findElement(By.id("titleInput")).sendKeys(title);
	}
	
	/**
	 * Created by khanhnt at Nov 14, 2013
	 * @param driver
	 * @param content
	 */
	public static void changeContent_WikiPage_WikiHome(WebDriver driver, String content) {
		//Add content
		driver.findElement(By.className("uiColsLeftsEditForm"))
				.findElement(By.id("Markup")).sendKeys(content);
	}
	/**
	 * Created by khanhnt at Nov 14, 2013
	 * @param driver
	 */
	public static void addBlankPage_WikiHome(WebDriver driver){
		WebElement addPage = WikiUtil.getButton_WikiPageToolBar_WikiHome(driver, "Add Page");		
		addPage.click();
		
		WebElement uiWikiPageContentArea  = driver.findElement(By.id("UIWikiPageControlArea"));	
		uiWikiPageContentArea.
				findElement(By.id("UIWikiPageControlArea_PageToolBar")).
				findElement(By.className("uiIconAddPage")).click();
	}
	
	/**
	 * Created by khanhnt at Nov 14, 2013
	 * @param driver
	 * @param temName
	 */
	public static void addPage_FromTemplate_WikiHome(WebDriver driver,String temName){
		WebElement addPage = WikiUtil.getButton_WikiPageToolBar_WikiHome(driver, "Add Page");		
		addPage.click();
		
		pause(5000);
		
		WebElement uiWikiPageContentArea  = driver.findElement(By.id("UIWikiPageControlArea"));	
		uiWikiPageContentArea.
				findElement(By.id("UIWikiPageControlArea_PageToolBar")).
				findElement(By.className("uiIconAddPageFromTemplate")).click();
		pause(5000);
		selectTemplateForm(driver, temName);
	}
	
	/**
	 * Created by khanhnt at Nov 14, 2013
	 * @param driver
	 */
	public static void saveWikiPage_WikiHome (WebDriver driver) {
		WebElement uiColsLeftsEditForm = driver.findElement(By.className("uiColsLeftsEditForm"));
		WebElement UISubmitToolBarUpper = uiColsLeftsEditForm.findElement(By.id("UISubmitToolBarUpper"));
		WebElement UISubmitToolBarUpper_SavePage_ = UISubmitToolBarUpper.findElement(By.id("UISubmitToolBarUpper_SavePage_"));
		UISubmitToolBarUpper_SavePage_.click();
		
		//Click on Save Button of Bottom Tool Bar. May be Exception, actual Selenium 
		//cannot scroll down the view to this button. -> scroll down manually or maximize browser
		/*WebElement UISubmitToolBarBottom = uiColsLeftsEditForm.findElement(By.id("UISubmitToolBarBottom"));			
		WebElement UISubmitToolBarBottom_SavePage_ = UISubmitToolBarBottom.findElement(By.id("UISubmitToolBarBottom_SavePage_"));													
		UISubmitToolBarBottom_SavePage_.click();*/
	}
	
	/**
	 * Created by khanhnt at Nov 14, 2013
	 * @param driver
	 * @param temName
	 */
	public static void selectTemplateForm(WebDriver driver,String temName){
		try {
			getRadio_PopupWikiSelectTemplateForm(driver, temName).click();
			getButton_PopupWikiSelectTemplateForm(driver, "Select").click();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * Created by khanhnt at Nov 14, 2013
	 * Get Select or Cancel button of 
	 * Popup wiki select template form
	 * @param driver
	 * @param btnName : Select or Cancel
	 * @return
	 */
	public static WebElement getButton_PopupWikiSelectTemplateForm(WebDriver driver,String btnName){
		try {
			List<WebElement> divs= driver.findElement(By.id("UIWikiSelectTemplateForm")).
					   findElements(By.tagName("div"));
			   
			   for (WebElement div : divs) {			   
					if(div.getAttribute("class").equalsIgnoreCase("uiAction uiActionBorder"))
						return div.findElement(By.linkText(btnName));
			   }
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return null;
	}
	
	/**
	 * Created by khanhnt at Nov 14, 2013
	 * @param driver
	 * @param temName
	 * @return
	 */
	public static WebElement getRadio_PopupWikiSelectTemplateForm(WebDriver driver,String temName){
		try {
			List<WebElement> radios = driver.
					findElement(By.id("UIWikiSelectTemplateForm")).
					findElement(By.id("UIWikiTemplateGrid"))
					.findElements(By.className("uiRadio"));
			
			for (int i=0;i<radios.size();i++) {				
				WebElement radio = radios.get(i);
				WebElement realRadio = radio.findElement(By.tagName("input"));
			    if(realRadio.getAttribute("value").equalsIgnoreCase("Two-Column_Layout"))
			    	return realRadio;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		return null;
	}
	/**
	 * Created by khanhnt at Nov 13, 2013. Get one of three buttons in Tool bar, Edit, Add Page or More
	 * @param driver
	 * @param name (Edit or Add Page or More)
	 * @return
	 */
	public static WebElement getButton_WikiPageToolBar_WikiHome(WebDriver driver, String name){
		try {
			WebElement middleArea = getUIWikiMiddleArea(driver);
			WebElement UIWikiPageControlArea = middleArea.findElement(By.id("UIWikiPageControlArea_PageToolBar"));
			List<WebElement> divs = UIWikiPageControlArea.findElements(By.tagName("div"));
			for (WebElement div : divs) {
				if(div.getText().equalsIgnoreCase(name))
					return div;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	 
	public static void pause(long timeInMillis) {
         try {
                 Thread.sleep(timeInMillis);
         } catch (InterruptedException e) {
                 e.printStackTrace();
         }
 }        
}
