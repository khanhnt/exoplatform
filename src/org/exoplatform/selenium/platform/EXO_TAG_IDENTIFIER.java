/**
 * Created by khanhnt at 11:45:37 AM Nov 12, 2013 
 * 
 */
package org.exoplatform.selenium.platform;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author khanhnt
 *
 */
public class EXO_TAG_IDENTIFIER {
	
	public static final String URL_BASE="http://localhost:8080/portal";
	//User profile
	public static final String LOCALHOST_USERNAME="khanhnt82";
	public static final String LOCALHOST_PASSWORD="tkhanh144";
	
	//For login page
	public static final String LOGIN_USERNAME_TEXTBOX_ByName="username";
	public static final String LOGIN_PASSWORD_TEXTBOX_ByName="password";
	
	public static final String LOGIN_SIGNIN_BUTTON_ByClass="button";
	
	//For Wiki Page
	public static final String WIKI_SPACE_ByClass="spaceIcon avatarMini";
	public static final String WIKI_JOINSPACE_ByClass="joinSpace";
	public static final String WIKI_SPACEWIKI_ById="wiki";
	public static final String WIKI_SPACEWIKI_ADDPAGE_ByClass="dropdown uiActionWithLabel";
	public static final String WIKI_SPACEWIKI_ADDPAGE_ADDBLANKPAGE_ByClass="uiIconAddPage";
	public static final String WIKI_SPACEWIKI_ADDPAGE_ADDPAGEFROMTEMPLATE_ByClass="uiIconAddPageFromTemplate";
	
	public static final String WIKI_UICompanyNavigations_ByClassName="uiCompanyNavigations";
	public static final String WIKI_UIWikiPageControlArea_ById="UIWikiPageControlArea";

	
	public static void login(WebDriver driver){
		driver.get(URL_BASE);
		
		WebElement username = driver.
				findElement(By.name(EXO_TAG_IDENTIFIER.LOGIN_USERNAME_TEXTBOX_ByName));
		WebElement password = driver.
				findElement(By.name(EXO_TAG_IDENTIFIER.LOGIN_PASSWORD_TEXTBOX_ByName));
		
		if(username!=null && password!=null){
			username.sendKeys(EXO_TAG_IDENTIFIER.LOCALHOST_USERNAME);
			password.sendKeys(EXO_TAG_IDENTIFIER.LOCALHOST_PASSWORD);
			
			WebElement signin = driver.
					findElement(By.className(EXO_TAG_IDENTIFIER.LOGIN_SIGNIN_BUTTON_ByClass));
			signin.click();
		}
	}

	

}
