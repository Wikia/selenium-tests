package com.wikia.webdriver.testcases.mercurytests.curatedcontenttests;

import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.api.CuratedContent;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.curatedcontent.CuratedContentPageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @ownership: Content X-Wing
 */
@Test(groups = {"MercuryCuratedEditorTests", "MercuryCuratedContentTests", "Mercury"})
public class EditorTests extends NewTestTemplate {

  @BeforeMethod(alwaysRun = true)
  public void prepareTest() {
    wikiURL = urlBuilder.getUrlForWiki(MercuryWikis.MERCURY_EMPTY_CC_EDITOR);
    new CuratedContent().clear();
  }

  @Test(groups = "MercuryCuratedEditorTests_001")
  public void MercuryCuratedEditorTest_001_addAndSaveItemToFeaturedContent() {

  }

  @Test(groups = "MercuryCuratedEditorTests_002")
  public void MercuryCuratedEditorTest_002_addAndSaveSection() {

  }

  @Test(groups = "MercuryCuratedEditorTests_003")
  public void MercuryCuratedEditorTest_003_addAndSaveItemToSection() {

  }

  @Test(groups = "MercuryCuratedEditorTests_004")
  public void MercuryCuratedEditorTest_004_addAndSaveItemToOptionalSection() {

  }
}
