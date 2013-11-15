/**
* Created by khanhnt at 3:32:52 PM Nov 15, 2013 
* Copyright (C) 2009 eXo Platform SAS.
*
* This is free software; you can redistribute it and/or modify it
* under the terms of the GNU Lesser General Public License as
* published by the Free Software Foundation; either version 2.1 of
* the License, or (at your option) any later version.
*
* This software is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
* Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public
* License along with this software; if not, write to the Free
* Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
* 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.exoplatform.selenium.platform.home;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author khanhnt
 *
 */
public class HomeUtil {

	
	//######################################Begin UIUserActivitiesDisplay ###########################//
	private static WebElement getUIUserActivitiesDisplay(WebDriver driver){
		return driver.findElement(By.id("UIUserActivitiesDisplay"));
	}
	
	private static WebElement getUIActivitiesLoader(WebDriver driver){
		return getUIUserActivitiesDisplay(driver).findElement(By.id("UIActivitiesLoader"));
	}
	private static List<WebElement> getListActivityContainer(WebDriver driver){
		
		List<WebElement> l = getUIActivitiesLoader(driver).
				findElement(By.tagName("div")).
				findElements(By.tagName("form"));
		System.out.println("l.size() " + l.size());
		return l;
	}
	private static WebElement getNewestActivityForm(WebDriver  driver){
		return getListActivityContainer(driver).get(0);
	}
	//######################################End UIUserActivitiesDisplay ###########################//

	//###################################Business method##########################################//
	
	/**
	 * Get newest content comment of newest activity.
	 * Created by khanhnt at Nov 15, 2013
	 * @param driver
	 * @return
	 */
	public static String getNewestContentCommentPublish(WebDriver driver){
		List<WebElement> cmtLists=getNewestActivityForm(driver).
			findElement(By.className("commentList")).
			findElements(By.className("commentItem"));
			
			System.out.println("cmtLists.size() "+cmtLists.size());
			return cmtLists.get(cmtLists.size()-1).
			findElement(By.className("commentRight")).
			findElement(By.className("contentComment")).
			getText();
			 
	}
	
	public static void clickOn_LinkTitleNewestActivity(WebDriver driver){
		getNewestActivityForm(driver).findElement(By.className("linkTitle")).click();
	}
	
	
	//##################################End Business method######################################//
}
