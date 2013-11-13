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
public class WikiUtil {
	
	
	
	public static WebElement getLeftNavigation(WebDriver driver){
		WebElement UIPortal = driver.findElement(By.id("UIPortal"));
		WebElement NavigationBody = UIPortal.findElement(By.id("NavigationBody"));
		WebElement LeftNavigation = NavigationBody.findElement(By.id("LeftNavigation"));
		return LeftNavigation;
	}
	
	public static WebElement getRightPageBody(WebDriver driver){
		WebElement UIPortal = driver.findElement(By.id("UIPortal"));
		WebElement NavigationBody = UIPortal.findElement(By.id("NavigationBody"));
		WebElement UIPage = NavigationBody.findElement(By.id("UIPage"));
		return UIPage;
	}
	
	public static WebElement getUIWikiUpperArea(WebDriver driver){
		try{
			WebElement pageBody = getRightPageBody(driver);
			WebElement UIWikiUpperArea = pageBody.findElement(By.id("UIWikiUpperArea"));
			return UIWikiUpperArea;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	public static WebElement getUIWikiMiddleArea(WebDriver driver){
		try{
			WebElement pageBody = getRightPageBody(driver);
			WebElement UIWikiUpperArea = pageBody.findElement(By.id("UIWikiMiddleArea"));
			return UIWikiUpperArea;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static WebElement getItem_LeftNavigationPortlet(WebDriver driver, String linkText){
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
	
	public static WebElement getButtonOfWikiPageEditForm(WebDriver driver, String name){
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
	
	public static WebElement getWikiRichTextEditorMenuBarItem(WebDriver driver, String name){
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

	public static WebElement getWikiRichTextEditorRichTextArea(WebDriver driver){
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
	
	public static WebElement getWikiRichTextEditorxToolBarItem(WebDriver driver, String name){
		try {
			
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
	public static WebElement getUIWikiPageControlArea_PageToolBarItem_WikiHome(WebDriver driver, String name){
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
}
