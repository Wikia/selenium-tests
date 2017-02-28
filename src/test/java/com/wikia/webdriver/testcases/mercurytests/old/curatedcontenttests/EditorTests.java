package com.wikia.webdriver.testcases.mercurytests.old.curatedcontenttests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.contentpatterns.MercuryPaths;
import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.api.CuratedContent;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.skin.Skin;
import com.wikia.webdriver.common.skin.SkinHelper;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.mercury.old.curatedcontent.CuratedContentPageObject;
import com.wikia.webdriver.elements.mercury.old.curatedcontent.CuratedMainPagePageObject;
import com.wikia.webdriver.elements.mercury.old.curatedcontent.EditorHomePageObject;
import com.wikia.webdriver.elements.mercury.old.curatedcontent.curatededitorform.CategoryFormPageObject;
import com.wikia.webdriver.elements.mercury.old.curatedcontent.curatededitorform.ItemFormPageObject;
import com.wikia.webdriver.elements.mercury.old.curatedcontent.curatededitorform.SectionFormPageObject;
import com.wikia.webdriver.elements.mercury.old.curatedcontent.curatededitorform.SectionItemListPageObject;
import com.wikia.webdriver.elements.mercury.old.curatedcontent.imageupload.CroppingToolPageObject;
import com.wikia.webdriver.elements.mercury.old.curatedcontent.imageupload.SearchForImagePageObject;
import com.wikia.webdriver.elements.mercury.old.curatedcontent.imageupload.UploadImageModalComponentObject;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
@Test(groups = "Mercury_CuratedEditor")
@Execute(
    onWikia = MercuryWikis.MERCURY_EMPTY_CC_EDITOR,
    asUser = User.STAFF
)
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
)
public class EditorTests extends NewTestTemplate {

  private static final String ITEM_DISPLAY_NAME = "Templates";
  private static final String ITEM_PAGE_NAME = "Category:Templates";
  private static final String SECTION_DISPLAY_NAME = "New Section";
  private static final String SEARCH_IMAGE_QUERY = "U";

  private CuratedMainPagePageObject curatedMainPage;
  private CuratedContentPageObject curatedContent;
  private SectionFormPageObject section;
  private SectionItemListPageObject sectionItem;
  private CategoryFormPageObject category;
  private EditorHomePageObject editor;
  private ItemFormPageObject itemForm;
  private UploadImageModalComponentObject uploadImage;
  private SearchForImagePageObject search;
  private CroppingToolPageObject croppingTool;
  private Navigate navigate;

  private void init() {
    this.curatedMainPage = new CuratedMainPagePageObject(driver);
    this.curatedContent = new CuratedContentPageObject(driver);
    this.section = new SectionFormPageObject(driver);
    this.sectionItem = new SectionItemListPageObject(driver);
    this.category = new CategoryFormPageObject(driver);
    this.editor = new EditorHomePageObject(driver);
    this.itemForm = new ItemFormPageObject(driver);
    this.uploadImage = new UploadImageModalComponentObject(driver);
    this.search = new SearchForImagePageObject(driver);
    this.croppingTool = new CroppingToolPageObject(driver);
    this.navigate = new Navigate();
  }

  @BeforeMethod(alwaysRun = true)
  public void beforeMethod() {
    new CuratedContent().clear();
  }

  @AfterClass(alwaysRun = true)
  public void afterClass() {
    new CuratedContent().clear();
  }

  @Test(groups = "MercuryCuratedEditorTest_001")
  public void MercuryCuratedEditorTest_001_addAndSaveItemToFeaturedContent() {
    init();

    navigate.toPage(MercurySubpages.ECC_MAIN_PAGE);
    Assertion.assertTrue(new SkinHelper(driver).isSkin(Skin.MOBILE_WIKI));

    Boolean result = curatedMainPage.isFeaturedContentVisible();

    Assertion.assertFalse(result, MercuryMessages.VISIBLE_MSG);

    navigate.toPage(MercuryPaths.ROOT_MAIN_EDIT);
    Assertion.assertTrue(new SkinHelper(driver).isSkin(Skin.MERCURY));

    editor.clickAddFeaturedContent();
    itemForm.typeDisplayName(ITEM_DISPLAY_NAME);
    itemForm.typePageName(ITEM_PAGE_NAME);
    itemForm.clickOnImage();
    uploadImage.clickSearchForImageButton();
    search.type(SEARCH_IMAGE_QUERY);
    search.clickOnImage(0);
    croppingTool.clickDoneButton();

    itemForm.waitForDeleteButtonToBeVisible();
    itemForm.clickDone();

    editor.waitForAddCategoryButtonToBeVisible();
    editor.publish();

    Assertion.assertTrue(new SkinHelper(driver).isSkin(Skin.MOBILE_WIKI));
    result = curatedMainPage.isFeaturedContentVisible();

    PageObjectLogging.log(
        "Featured Content",
        MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG,
        result
    );
  }

  @Test(groups = "MercuryCuratedEditorTest_002")
  public void MercuryCuratedEditorTest_002_addAndSaveSection() {
    init();

    navigate.toPage(MercurySubpages.ECC_MAIN_PAGE);
    Assertion.assertTrue(new SkinHelper(driver).isSkin(Skin.MOBILE_WIKI));

    Boolean result = curatedMainPage.isCuratedContentVisible();

    Assertion.assertFalse(result, MercuryMessages.VISIBLE_MSG);

    navigate.toPage(MercuryPaths.ROOT_MAIN_EDIT);
    Assertion.assertTrue(new SkinHelper(driver).isSkin(Skin.MERCURY));

    editor.clickAddSection();
    section.typeDisplayName(SECTION_DISPLAY_NAME);
    section.clickOnImage();
    uploadImage.clickSearchForImageButton();
    search.type(SEARCH_IMAGE_QUERY);
    search.clickOnImage(0);
    croppingTool.clickDoneButton();
    section.clickDone();

    sectionItem.clickAddCategory();
    category.typeDisplayName(ITEM_DISPLAY_NAME);
    category.typePageName(ITEM_PAGE_NAME);
    category.clickOnImage();
    uploadImage.clickSearchForImageButton();
    search.type(SEARCH_IMAGE_QUERY);
    search.clickOnImage(0);
    croppingTool.clickDoneButton();
    category.clickDone();
    sectionItem.verifyItem(ITEM_DISPLAY_NAME);

    sectionItem.waitForAddCategoryButtonToBeVisible();
    sectionItem.clickDone();

    editor.waitForAddCategoryButtonToBeVisible();
    editor.publish();

    Assertion.assertTrue(new SkinHelper(driver).isSkin(Skin.MOBILE_WIKI));
    result = curatedMainPage.isCuratedContentVisible();

    PageObjectLogging.log(
        "Curated Content",
        MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG,
        result
    );

    curatedContent.clickOnCuratedContentElementByIndex(0);

    Assertion.assertNumber(
        curatedContent.getCuratedContentItemsNumber(),
        1,
        "If error says that 3 elements were found - it means getList API returned cached response - ticket created: XW-1281"
    );
  }

  @Test(groups = "MercuryCuratedEditorTest_003")
  public void MercuryCuratedEditorTest_003_addAndSaveItemToOptionalSection() {
    init();

    navigate.toPage(MercurySubpages.ECC_MAIN_PAGE);
    Assertion.assertTrue(new SkinHelper(driver).isSkin(Skin.MOBILE_WIKI));

    Boolean result = curatedMainPage.isCuratedContentVisible();

    Assertion.assertFalse(result, MercuryMessages.VISIBLE_MSG);

    navigate.toPage(MercuryPaths.ROOT_MAIN_EDIT);
    Assertion.assertTrue(new SkinHelper(driver).isSkin(Skin.MERCURY));

    editor.clickAddCategory();
    itemForm.typeDisplayName(ITEM_DISPLAY_NAME);
    itemForm.typePageName(ITEM_PAGE_NAME);
    itemForm.clickOnImage();
    uploadImage.clickSearchForImageButton();
    search.type(SEARCH_IMAGE_QUERY);
    search.clickOnImage(0);
    croppingTool.clickDoneButton();

    itemForm.waitForDeleteButtonToBeVisible();
    itemForm.clickDone();

    editor.waitForAddCategoryButtonToBeVisible();
    editor.publish();

    Assertion.assertTrue(new SkinHelper(driver).isSkin(Skin.MOBILE_WIKI));
    result = curatedMainPage.isCuratedContentVisible();

    PageObjectLogging.log(
        "Curated Content",
        MercuryMessages.VISIBLE_MSG,
        MercuryMessages.INVISIBLE_MSG,
        result
    );
  }
}
