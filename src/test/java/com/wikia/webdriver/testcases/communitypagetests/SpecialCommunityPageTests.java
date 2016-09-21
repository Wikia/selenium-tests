package com.wikia.webdriver.testcases.communitypagetests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.communitypage.SpecialCommunity;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;


@Test(groups = "CommunityPageTests")
public class SpecialCommunityPageTests extends NewTestTemplate {

  @Execute(onWikia = "muppet", asUser = User.ANONYMOUS, mockAds = "true")
  public void verifyAnonEditingForbiddenFlow() {
    SpecialCommunity page = new SpecialCommunity();
    // click the first link from any card
    WebElement link = page.open().getLinkFromCards();
    String url = getEditUrl(link.getText(), "veaction=edit");
    link.click();
    // should be at register page
    page.loginAs(User.USER);

    // we are back to link
    Assertion.assertEquals(url, page.getCurrentUrl());
  }

  @Execute(onWikia = "mediawiki119", asUser = User.ANONYMOUS, mockAds = "true")
  public void verifyAnonEditingFlow() {
    SpecialCommunity page = new SpecialCommunity();
    WebElement link = page.open().getLinkFromCards();
    String url = getEditUrl(link.getText(), "veaction=edit");

    link.click();

    Assertion.assertEquals(url, page.getCurrentUrl());
  }

  @Execute(onWikia = "muppet", asUser = User.USER, mockAds = "true")
  public void verifyUserEditingFlow() {
    SpecialCommunity page = new SpecialCommunity();
    WebElement link = page.open().getLinkFromCards();
    String url = getEditUrl(link.getText(), "action=edit");

    link.click();

    Assertion.assertEquals(url, page.getCurrentUrl());
  }

  private String getEditUrl(String page, String query) {
    return urlBuilder.appendQueryStringToURL(
        urlBuilder.getUrlForPage(String.format("/wiki/%s", page.replace(" ", "_"))),
        query);
  }

}
