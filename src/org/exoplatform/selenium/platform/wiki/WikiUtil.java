/**
 * Created by khanhnt at 3:32:07 PM Nov 13, 2013 
 * 
 */
package org.exoplatform.selenium.platform.wiki;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

/**
 * @author khanhnt
 *
 */
public class WikiUtil {

	private static WebElement getLeftNavigation(WebDriver driver) {
		WebElement UIPortal = driver.findElement(By.id("UIPortal"));
		WebElement NavigationBody = UIPortal.findElement(By
				.id("NavigationBody"));
		WebElement LeftNavigation = NavigationBody.findElement(By
				.id("LeftNavigation"));
		return LeftNavigation;
	}

	private static WebElement getRightPageBody(WebDriver driver) {
		WebElement UIPortal = driver.findElement(By.id("UIPortal"));
		WebElement NavigationBody = UIPortal.findElement(By
				.id("NavigationBody"));
		WebElement UIPage = NavigationBody.findElement(By.id("UIPage"));
		return UIPage;
	}

	//############################# Start Element on UIWikiUpperArea ###############################//
	/**
	 * Created by khanhnt at Nov 15, 2013
	 * @param driver
	 * @return
	 */
	private static WebElement getUIWikiUpperArea(WebDriver driver) {
		try {
			WebElement pageBody = getRightPageBody(driver);
			WebElement UIWikiUpperArea = pageBody.findElement(By
					.id("UIWikiUpperArea"));
			return UIWikiUpperArea;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	//UIWikiToolBar_Browse_
	
	private static WebElement getBrowserToolBar(WebDriver driver){
		return getUIWikiUpperArea(driver).findElement(By.id("UIWikiToolBar_Browse_")).findElement(By.tagName("div"));
	}
	//My draff item
	/**
	 * Get My Draff Item.
	 * Created by khanhnt at Nov 15, 2013.
	 * @param driver
	 * @return
	 */
	private static WebElement getMyDraffItem(WebDriver driver){
		return getBrowserToolBar(driver).findElements(By.tagName("li")).get(0);
	}
	 /**
	  * Get Wiki Setting Item.
	 * Created by khanhnt at Nov 15, 2013
	 * @param driver
	 * @return
	 */
	private static WebElement getWikiSettingItem(WebDriver driver){
		return getBrowserToolBar(driver).findElements(By.tagName("li")).get(1);
	}
	//############################# End Element on UIWikiUpperArea ###############################//
	
	//############################## Begin Element on UIWikiMiddleArea ############################//
	private static WebElement getUIWikiMiddleArea(WebDriver driver) {
		try {
			WebElement pageBody = getRightPageBody(driver);
			WebElement UIWikiUpperArea = pageBody.findElement(By
					.id("UIWikiMiddleArea"));
			return UIWikiUpperArea;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	///-------------------------- Begin Element uiLeftContainerArea ----------------------------///
	///-------------------------- End Element uiLeftContainerArea ------------------------------///
	
	///-------------------------- Begin Element uiRightContainerArea ----------------------------///
	private static WebElement getuiRightContainerArea(WebDriver driver){
		return getUIWikiMiddleArea(driver).findElement(By.className("uiRightContainerArea"));
	}
	
	///-------------------------- End Element uiRightContainerArea ------------------------------///

	//############################## End Element on UIWikiMiddleArea ############################//
	
	private static WebElement getItem_LeftNavigationPortlet(WebDriver driver,
			String linkText) {
		try {
			WebElement LeftNavigation = getLeftNavigation(driver);
			WebElement LeftNavigationPortlet = LeftNavigation.findElement(By
					.id("LeftNavigationPortlet"));
			WebElement uiCompanyNavigations = LeftNavigationPortlet
					.findElement(By.className("uiCompanyNavigations"));
			WebElement item = uiCompanyNavigations.findElement(By
					.linkText(linkText));
			return item;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void navigateToWikiHome(WebDriver driver) {
		getItem_LeftNavigationPortlet(driver, "Wiki").click();
	}

	/**
	 * Created by khanhnt at Nov 14, 2013 Get Text box of Wiki Page to edit
	 * 
	 * @param driver
	 * @return
	 */
	private static WebElement getUIWikiPageTitleControlForm(WebDriver driver) {
		try {
			WebElement middleArea = getUIWikiMiddleArea(driver);
			WebElement uiWikiPageEditForm = middleArea.findElement(By
					.id("uiWikiPageEditForm"));
			WebElement title = uiWikiPageEditForm.findElement(
					By.id("UIWikiPageTitleControlForm_PageEditForm"))
					.findElement(By.id("titleInput"));
			return title;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Created by khanhnt at Nov 14, 2013. Get the buttons : Rich Text or Source
	 * Editor, Preview, Help, Save, Cancel just below of title text box
	 * 
	 * @param driver
	 * @param name
	 *            : Rich Text or Source Editor or Preview or Help or Save or
	 *            Cancel
	 * @return: WebElement of button
	 */
	public static WebElement getButton_WikiPageEditForm(WebDriver driver,
			String name) {
		try {
			WebElement middleArea = getUIWikiMiddleArea(driver);
			WebElement uiWikiPageEditForm = middleArea.findElement(By
					.id("UIWikiPageEditForm"));
			WebElement actionContainer = uiWikiPageEditForm.findElement(By
					.className("actionContainer"));
			List<WebElement> buttons = actionContainer.findElements(By
					.tagName("button"));
			for (WebElement button : buttons) {
				if (button.getText().equalsIgnoreCase(name))
					return button;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * Created by khanhnt at Nov 14, 2013 Get item on menu bar of wiki rich text
	 * editor: Link, Image, Table, Macro
	 * 
	 * @param driver
	 * @param name
	 *            : Link, Image, Table, Macro
	 * @return WebElement of this item
	 */
	public static WebElement getMenuBarItem_WikiRichTextEditor(
			WebDriver driver, String name) {
		try {
			WebElement middleArea = getUIWikiMiddleArea(driver);
			WebElement uiWikiPageEditForm = middleArea.findElement(By
					.id("UIWikiPageEditForm"));
			WebElement xRichTextEditor = uiWikiPageEditForm.findElement(By
					.className("xRichTextEditor"));
			List<WebElement> divs = xRichTextEditor.findElements(By
					.tagName("div"));

			for (WebElement div : divs) {
				if (div.getAttribute("class").equalsIgnoreCase(
						"gwt-MenuBar gwt-MenuBar-horizontal")) {
					List<WebElement> tds = div.findElements(By.tagName("td"));
					for (WebElement td : tds) {
						WebElement item = td.findElement(By.tagName("div"));
						if (item.getText().equalsIgnoreCase(name))
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
	 * Created by khanhnt at Nov 14, 2013 Get rich text area of Wiki Rich text
	 * editor
	 * 
	 * @param driver
	 * @return
	 */
	public static WebElement getRichTextArea_WikiRichTextEditor(WebDriver driver) {
		try {
			WebElement middleArea = getUIWikiMiddleArea(driver);
			WebElement uiWikiPageEditForm = middleArea.findElement(By
					.id("UIWikiPageEditForm"));
			WebElement xRichTextEditor = uiWikiPageEditForm.findElement(By
					.className("xRichTextEditor"));
			WebElement richTextArea = xRichTextEditor.findElement(By
					.tagName("iframe"));
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
	 * 
	 * @param driver
	 * @param name
	 * @return
	 */
	public static WebElement getToolBarItem_WikiRichTextEditorx(
			WebDriver driver, String name) {
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
	 * 
	 * @param driver
	 * @param title
	 */
	public static void changeTitle_WikiPage_WikiHome(WebDriver driver,
			String title) {
		driver.findElement(By.className("uiColsLeftsEditForm"))
				.findElement(By.id("UIWikiPageTitleControlForm_PageEditForm"))
				.findElement(By.id("titleInput")).sendKeys(title);
	}

	/**
	 * Created by khanhnt at Nov 14, 2013
	 * 
	 * @param driver
	 * @param content
	 */
	public static void changeContent_WikiPage_WikiHome(WebDriver driver,
			String content) {
		// Add content
		driver.findElement(By.className("uiColsLeftsEditForm"))
				.findElement(By.id("Markup")).sendKeys(content);
	}

	/**
	 * Created by khanhnt at Nov 14, 2013
	 * 
	 * @param driver
	 */
	public static void addBlankPage_WikiHome(WebDriver driver) {
		WebElement addPage = WikiUtil.getButton_WikiPageToolBar_WikiHome(
				driver, "Add Page");
		addPage.click();

		WebElement uiWikiPageContentArea = driver.findElement(By
				.id("UIWikiPageControlArea"));
		uiWikiPageContentArea
				.findElement(By.id("UIWikiPageControlArea_PageToolBar"))
				.findElement(By.className("uiIconAddPage")).click();
	}

	public static void clickOn_AddPageFromTemplate(WebDriver driver){
		WebElement addPage = WikiUtil.getButton_WikiPageToolBar_WikiHome(
				driver, "Add Page");
		addPage.click();

		pause(5000);

		WebElement uiWikiPageContentArea = driver.findElement(By
				.id("UIWikiPageControlArea"));
		uiWikiPageContentArea
				.findElement(By.id("UIWikiPageControlArea_PageToolBar"))
				.findElement(By.className("uiIconAddPageFromTemplate")).click();
		pause(5000);
	}
	
	/**
	 * Created by khanhnt at Nov 14, 2013
	 * 
	 * @param driver
	 * @param temName
	 */
	public static void addPage_FromTemplate_WikiHome(WebDriver driver,
			String temName) {
		clickOn_AddPageFromTemplate(driver);
		selectTemplateForm(driver, temName);
	}
	
	public static void previewTemplate(WebDriver driver){
		clickOn_AddPageFromTemplate(driver);
		getPreview_PopupWikiSelectTemplateForm(driver).click();
	}
	/**
	 * Created by khanhnt at Nov 14, 2013
	 * 
	 * @param driver
	 */
	public static void saveWikiPage_WikiHome(WebDriver driver) {
		WebElement uiColsLeftsEditForm = driver.findElement(By
				.className("uiColsLeftsEditForm"));
		WebElement UISubmitToolBarUpper = uiColsLeftsEditForm.findElement(By
				.id("UISubmitToolBarUpper"));
		WebElement UISubmitToolBarUpper_SavePage_ = UISubmitToolBarUpper
				.findElement(By.id("UISubmitToolBarUpper_SavePage_"));
		UISubmitToolBarUpper_SavePage_.click();

		// Click on Save Button of Bottom Tool Bar. May be Exception, actual
		// Selenium
		// cannot scroll down the view to this button. -> scroll down manually
		// or maximize browser
		/*
		 * WebElement UISubmitToolBarBottom =
		 * uiColsLeftsEditForm.findElement(By.id("UISubmitToolBarBottom"));
		 * WebElement UISubmitToolBarBottom_SavePage_ =
		 * UISubmitToolBarBottom.findElement
		 * (By.id("UISubmitToolBarBottom_SavePage_"));
		 * UISubmitToolBarBottom_SavePage_.click();
		 */
	}

	/**
	 * Created by khanhnt at Nov 14, 2013
	 * 
	 * @param driver
	 * @param temName
	 */
	public static void selectTemplateForm(WebDriver driver, String temName) {
		try {
			getRadio_PopupWikiSelectTemplateForm(driver, temName).click();
			getButton_PopupWikiSelectTemplateForm(driver, "Select").click();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Created by khanhnt at Nov 14, 2013 Get Select or Cancel button of Popup
	 * wiki select template form
	 * 
	 * @param driver
	 * @param btnName
	 *            : Select or Cancel
	 * @return
	 */
	public static WebElement getButton_PopupWikiSelectTemplateForm(
			WebDriver driver, String btnName) {
		try {
			List<WebElement> divs = driver.findElement(
					By.id("UIWikiSelectTemplateForm")).findElements(
					By.tagName("div"));

			for (WebElement div : divs) {
				if (div.getAttribute("class").equalsIgnoreCase(
						"uiAction uiActionBorder"))
					return div.findElement(By.linkText(btnName));
			}
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return null;
	}

	public static WebElement getPreview_PopupWikiSelectTemplateForm(
			WebDriver driver){
		try {
			List<WebElement> radios = driver
					.findElement(By.id("UIWikiSelectTemplateForm"))
					.findElement(By.id("UIWikiTemplateGrid"))
					.findElements(By.className("uiIconPreview"));

			return radios.get(0);
			/*for (int i = 0; i < radios.size(); i++) {
				WebElement radio = radios.get(i);
				WebElement realRadio = radio.findElement(By.tagName("input"));
				if (realRadio.getAttribute("value").equalsIgnoreCase(
						"Two-Column_Layout"))
					return realRadio;
			}*/
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	
		
	}
	/**
	 * Created by khanhnt at Nov 14, 2013
	 * 
	 * @param driver
	 * @param temName
	 * @return
	 */
	public static WebElement getRadio_PopupWikiSelectTemplateForm(
			WebDriver driver, String temName) {
		try {
			List<WebElement> radios = driver
					.findElement(By.id("UIWikiSelectTemplateForm"))
					.findElement(By.id("UIWikiTemplateGrid"))
					.findElements(By.className("uiRadio"));

			for (int i = 0; i < radios.size(); i++) {
				WebElement radio = radios.get(i);
				WebElement realRadio = radio.findElement(By.tagName("input"));
				if (realRadio.getAttribute("value").equalsIgnoreCase(
						"Two-Column_Layout"))
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
	 * Created by khanhnt at Nov 13, 2013. Get one of three buttons in Tool bar,
	 * Edit, Add Page or More
	 * 
	 * @param driver
	 * @param name
	 *            (Edit or Add Page or More)
	 * @return
	 */
	public static WebElement getButton_WikiPageToolBar_WikiHome(
			WebDriver driver, String name) {
		try {
			WebElement middleArea = getUIWikiMiddleArea(driver);
			WebElement UIWikiPageControlArea = middleArea.findElement(By
					.id("UIWikiPageControlArea_PageToolBar"));
			List<WebElement> divs = UIWikiPageControlArea.findElements(By
					.tagName("div"));
			for (WebElement div : divs) {
				if (div.getText().equalsIgnoreCase(name))
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

	public static enum PopupItem {
		WIKI_PAGE_ADD_NEW_IN_CURRENT_SPACE, 
		WIKI_PAGE_SELECT_PAGE_IN_ALL_PAGES,
		WIKI_PAGE_SEARCH_PAGE, 
		ATTACHED_FILE_CURRENT_PAGE_UPLOAD_NEW_FILE, 
		ATTACHED_FILE_CURRENT_PAGE_SELECT_FILE, 
		ATTACHED_FILE_ALL_PAGE_SELECT_FILE, WEB_PAGE, 
		EMAIL_ADDRESS,
		ATTACHED_IMAGE_UPLOAD_NEW_CURRENT_PAGE,
		ATTACHED_IMAGE_SELECT_ONE_CURRENT_PAGE,
		ATTACHED_IMAGE_UPLOAD_NEW_ALL_PAGES,
		ATTACHED_IMAGE_SELECT_ONE_ALL_PAGES,
		EXTERNAL_IMAGE
	}
	

	public static void addImage_RichTextEditor_WikiPage(WebDriver driver, PopupItem addType){
	
		clickOnPopupItem(driver,addType);
		driver.switchTo().window(driver.getWindowHandle());
	
		switch (addType) {
		case ATTACHED_IMAGE_UPLOAD_NEW_CURRENT_PAGE:
			Actions builder = new Actions(driver);
			getMenuBarItem_AddLinkImagePopup(driver, "Current page").click();
			
			WebElement xNewImagePreview = getListBoxImage_AddImagePopup(driver).
					findElement(By.className("xNewImagePreview")).findElement(By.tagName("div"));
			builder.doubleClick(xNewImagePreview);
			builder.perform();
			
			break;
		case ATTACHED_IMAGE_SELECT_ONE_CURRENT_PAGE:
			
			//builder.doubleClick(onElement)
			break;
		case ATTACHED_IMAGE_UPLOAD_NEW_ALL_PAGES:
			break;
		case ATTACHED_IMAGE_SELECT_ONE_ALL_PAGES:
			break;
		case EXTERNAL_IMAGE:
			break;

		default:
			break;
		}
	}
	/**
	 * Add link menu of rich text editor.
	 * Created by khanhnt at Nov 14, 2013.
	 * @param driver
	 * @param linkType: using LinkType to determine
	 * @param linkContent: link content
	 */
	public static void addLink_RichTextEditor_WikiPage(WebDriver driver,
			PopupItem linkType, String linkContent,String label, String tooltip,Boolean isInNewWindows) {

		clickOnPopupItem(driver, linkType);	
		driver.switchTo().window(driver.getWindowHandle());

		
		switch (linkType) {
		case WIKI_PAGE_ADD_NEW_IN_CURRENT_SPACE:
			break;
		case WIKI_PAGE_SELECT_PAGE_IN_ALL_PAGES:
			WebElement allPages = getMenuBarItem_AddLinkImagePopup(driver,
					"All Pages");
			
			if (allPages != null) {
				allPages.click();
				pause(1000);
				selectItem_AllPages_AddLinkPopup(driver,linkContent);
				clickOn_SelectButton_AddLinkPopup(driver);
				pause(500);
				configLinkParameter_AddLinkPopup(driver,label, tooltip, isInNewWindows);
				clickOn_CreateLinkButton_EditLinkParameter_AddLinkPopup(driver);
			} else {
				System.out.println("Cannot get All Pages Element");
			}
			break;
		case WIKI_PAGE_SEARCH_PAGE:
			break;
		case ATTACHED_FILE_CURRENT_PAGE_UPLOAD_NEW_FILE:
			break;
		case ATTACHED_FILE_CURRENT_PAGE_SELECT_FILE:
			break;
		case ATTACHED_FILE_ALL_PAGE_SELECT_FILE:
			break;
		case WEB_PAGE:
			break;
		case EMAIL_ADDRESS:
			break;
		default:
			break;
		}
	}

	/**
	 * Created by khanhnt at Nov 14, 2013. Must switch window before calling
	 * this function
	 * 
	 * @param driver
	 * @return a MenuBar, with a list of td objects
	 */
	private static WebElement getMenuBar_AddLinkImagePopup(WebDriver driver) {
		return driver.findElement(By.className("xDialogBody")).findElement(
				By.className("gwt-TabBar"));

	}
	
	private static WebElement getListBoxImage_AddImagePopup(WebDriver driver){
		return driver.findElement(By.className("xDialogBody")).
				findElement(By.className("xListBox"));
	}
	private static WebElement getExplorerForm_Add_LinkPopup(WebDriver driver){
		return driver.findElement(By.className("xDialogBody")).
				findElement(By.className("xExplorer"));
	}
	private static WebElement getDialogFooter_AddLinkPopup(WebDriver driver){
		return driver.findElement(By.className("xDialogFooter"));
	}
	private static WebElement getLinkConfigForm_AddLinkPopup(WebDriver driver){
		return driver.findElement(By.className("xDialogBody")).
				findElement(By.className("xLinkConfig"));
	}
	
	private static void configLinkParameter_AddLinkPopup(WebDriver driver, 
			String label,String tooltip,Boolean isInNewWindows){
		List<WebElement> inputs = getLinkConfigForm_AddLinkPopup(driver).findElements(By.tagName("input"));
		
		for (WebElement input : inputs) {
			if(input.getAttribute("type").equalsIgnoreCase("text")&&
					input.getAttribute("title").equalsIgnoreCase("Type the label of the created link."))
				input.sendKeys(label);
			else if(input.getAttribute("type").equalsIgnoreCase("text")&&
					input.getAttribute("title").equalsIgnoreCase("Type the tooltip of the created link,"
					+ " which appears when mouse is over the link."))
				input.sendKeys(tooltip);
			else if(input.getAttribute("type").equalsIgnoreCase("checkbox")){
				if(isInNewWindows)
					input.click();
			}
				
		}
	}
	
	public static void clickOn_SelectButton_AddLinkPopup(WebDriver driver){
		getDialogFooter_AddLinkPopup(driver).findElement(By.tagName("button")).click();
	}
	
	public static void clickOn_CreateLinkButton_EditLinkParameter_AddLinkPopup(WebDriver driver){
		List<WebElement> buttons = getDialogFooter_AddLinkPopup(driver).findElements(By.tagName("button"));
		
		for (WebElement button : buttons) {
			if(button.getText().equalsIgnoreCase("Create Link"))
				button.click();
		}
	}
	
	private static void selectItem_AllPages_AddLinkPopup(WebDriver driver, String itemName){
		WebElement explorer=getExplorerForm_Add_LinkPopup(driver);
		List<WebElement> divs = explorer.findElements(By.tagName("div"));
		
		for (WebElement div : divs) {
			if(div.getText().equalsIgnoreCase(itemName))
				 div.click();
		}
	}
	
	private static WebElement getMenuBarItem_AddLinkImagePopup(WebDriver driver,
			String name) {
		WebElement mneBar = getMenuBar_AddLinkImagePopup(driver);
		List<WebElement> tds = mneBar.findElements(By.tagName("td"));
		for (WebElement td : tds) {
			if (td.getText().equalsIgnoreCase(name))
				return td.findElement(By.tagName("div"));
		}

		return null;
	}

	private static void clickOnPopupItem(WebDriver driver, PopupItem linkType) {
		WebElement menuPopupMiddle = driver.findElement(By
				.className("menuPopupMiddle"));
		List<WebElement> trs = menuPopupMiddle.findElements(By.tagName("tr"));
		String name = "";
		switch (linkType) {
		case WIKI_PAGE_ADD_NEW_IN_CURRENT_SPACE:
		case WIKI_PAGE_SELECT_PAGE_IN_ALL_PAGES:			
		case WIKI_PAGE_SEARCH_PAGE:
			name = "Wiki Page...";
			break;
		case ATTACHED_FILE_CURRENT_PAGE_UPLOAD_NEW_FILE:
		case ATTACHED_FILE_CURRENT_PAGE_SELECT_FILE:
		case ATTACHED_FILE_ALL_PAGE_SELECT_FILE:
			name = "Attached File...";
			break;
		case WEB_PAGE:
			name = "Web Page...";
			break;
		case EMAIL_ADDRESS:
			name = "Email Address...";
			break;
		case	ATTACHED_IMAGE_UPLOAD_NEW_CURRENT_PAGE:
		case	ATTACHED_IMAGE_SELECT_ONE_CURRENT_PAGE:
		case	ATTACHED_IMAGE_UPLOAD_NEW_ALL_PAGES:
		case	ATTACHED_IMAGE_SELECT_ONE_ALL_PAGES:
			name = "Attached Image...";
			break;
		case EXTERNAL_IMAGE:
			name = "External Image...";
			break;
		default:
			break;
		}
		for (WebElement tr : trs) {
			if (tr.getText().equalsIgnoreCase(name)) {
				tr.findElement(By.tagName("div")).click();
				pause(5000);
				break;
			}
		}
	}
}
