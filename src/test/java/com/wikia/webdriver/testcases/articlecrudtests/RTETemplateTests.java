package com.wikia.webdriver.testcases.articlecrudtests;

import static org.assertj.core.api.Assertions.assertThat;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.helpers.ContentLoader;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.pages.InfoboxBuilderPage;
import com.wikia.webdriver.pageobjectsfactory.componentobject.rte.FeaturesModule;
import com.wikia.webdriver.pageobjectsfactory.componentobject.rte.InfoboxChoiceModal;
import com.wikia.webdriver.pageobjectsfactory.componentobject.rte.TemplateConfigurationModal;
import com.wikia.webdriver.pageobjectsfactory.pageobject.PortableInfobox;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.SourceEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.testcases.infoboxbuilder.Subhead;

import org.testng.annotations.Test;

import java.time.Instant;

@Execute(asUser = User.USER)
public class RTETemplateTests extends NewTestTemplate {
  private static final String PI_TEMPLATE_NAME = "Template:RTEPortableInfobox_";
  private static final String PI_TITLE_PARAM = "name";
  private static final String TEST_PI_TITLE = "karambadawaj";

  @Test(groups = {"RTETemplateTests_001"})
  public void insertExistingPortableInfoboxToArticle() {
    VisualEditModePageObject visualEditModePageObject = new VisualEditModePageObject();
    visualEditModePageObject.open();

    FeaturesModule featuresModule = visualEditModePageObject.getFeaturesModule();
    InfoboxChoiceModal choiceModal = featuresModule.openInfoboxChoiceModal();
    TemplateConfigurationModal templateConfigModal = choiceModal.selectInfoboxByName("Character");

    templateConfigModal.configureTemplateParameter(PI_TITLE_PARAM, TEST_PI_TITLE);
    templateConfigModal.clickOkButton();

    visualEditModePageObject.submitArticle();

    PortableInfobox portableInfobox = new PortableInfobox();

    assertThat(portableInfobox.getInfoboxTitle()).isEqualTo(TEST_PI_TITLE);
  }

  @Test(groups = {"RTETemplateTests_002"})
  public void createNewPortableInfoboxForArticleViaInfoboxBuilderFlow() {
    VisualEditModePageObject visualEditModePageObject = new VisualEditModePageObject();
    visualEditModePageObject.open();

    FeaturesModule featuresModule = visualEditModePageObject.getFeaturesModule();
    InfoboxChoiceModal choiceModal = featuresModule.openInfoboxChoiceModal();
    InfoboxBuilderPage infoboxBuilderModal = choiceModal.clickCreateNewInfoboxButton();

    infoboxBuilderModal.inBuilderFrame(() -> {
      Subhead subhead = new Subhead();
      subhead.clickSubheadTitle();

      infoboxBuilderModal
          .insertTemplateTitle(PI_TEMPLATE_NAME + Instant.now().toString())
          .clickPublishEditedTitleButton();
      infoboxBuilderModal.waitUntilEditTitleModalIsClosed();

      subhead.clickPublish();
    });

    TemplateConfigurationModal templateConfigModal = new TemplateConfigurationModal(driver);
    templateConfigModal.configureTemplateParameter("title1", TEST_PI_TITLE);
    templateConfigModal.clickOkButton();

    visualEditModePageObject.submitArticle();

    PortableInfobox portableInfobox = new PortableInfobox();

    assertThat(portableInfobox.getInfoboxTitle()).isEqualTo(TEST_PI_TITLE);
  }

  @Test(groups = {"RTETemplateTests_003"})
  public void editPageWithPortableInfobox() {
    String piTemplate = ContentLoader.loadWikiTextContent("Infobox2_Template");
    String piTemplateCall = ContentLoader.loadWikiTextContent("Infobox2_Invocation");
    ArticleContent articleContent = new ArticleContent();

    articleContent.push(piTemplate, PI_TEMPLATE_NAME);
    articleContent.push(piTemplateCall);

    VisualEditModePageObject visualEditModePageObject = new VisualEditModePageObject().open();

    assertThat(visualEditModePageObject.checkPortableInfoboxVisible()).isTrue();

    SourceEditModePageObject sourceEditModePageObject = visualEditModePageObject.clickSourceButton();

    assertThat(sourceEditModePageObject.getContent()).isEqualTo(piTemplateCall.trim());

    visualEditModePageObject = sourceEditModePageObject.clickVisualButton();

    assertThat(visualEditModePageObject.checkPortableInfoboxVisible()).isTrue();

    visualEditModePageObject.removePortableInfobox();

    assertThat(visualEditModePageObject.checkPortableInfoboxIsNotPresent()).isTrue();
  }
}
