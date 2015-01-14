package com.wikia.webdriver.testcases.articlecrudtests;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.VideoContent;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.addphoto.AddPhotoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.gallery.GalleryBuilderComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.gallery.GalleryBuilderComponentObject.Orientation;
import com.wikia.webdriver.pageobjectsfactory.componentobject.gallery.GalleryBuilderComponentObject.PositionsGallery;
import com.wikia.webdriver.pageobjectsfactory.componentobject.gallery.GalleryBuilderComponentObject.SpacingGallery;
import com.wikia.webdriver.pageobjectsfactory.componentobject.photo.PhotoAddComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.photo.PhotoOptionsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.slider.SliderBuilderComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.slider.SliderBuilderComponentObject.MenuPositions;
import com.wikia.webdriver.pageobjectsfactory.componentobject.slideshow.SlideshowBuilderComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.slideshow.SlideshowBuilderComponentObject.Positions;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetAddVideoComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.vet.VetOptionsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.SourceEditModePageObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;


/**
 * @author Karol 'kkarolk' Kujawiak
 * @ownership Contribution
 */
public class ArticleSourceModeTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();
	WikiBasePageObject base;

	@BeforeMethod(alwaysRun = true)
	public void setup_VEPreferred() {
		base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
	}

	@Test(groups = {"RTE_extended", "RTE_extended_001"})
	public void RTE_001_Bold() {
		String articleName = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
		ArticlePageObject article = base.openArticleByName(wikiURL, articleName);
		SourceEditModePageObject source = article.openCurrectArticleSourceMode();
		source.clickBold();
		source.checkSourceContent("'''Bold text'''");
		source.submitArticle();
	}

	@Test(groups = {"RTE_extended", "RTE_extended_002"})
	public void RTE_002_Italic() {
		String articleName = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
		ArticlePageObject article = base.openArticleByName(wikiURL, articleName);
		SourceEditModePageObject source = article.openCurrectArticleSourceMode();
		source.clickItalic();
		source.checkSourceContent("''Italic text''");
		source.submitArticle();
	}

	@Test(groups = {"RTE_extended", "RTE_extended_003"})
	public void RTE_003_InternalLink() {
		String articleName = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
		ArticlePageObject article = base.openArticleByName(wikiURL, articleName);
		SourceEditModePageObject source = article.openCurrectArticleSourceMode();
		source.clickInternalLink();
		source.checkSourceContent("[[Link title]]");
		source.submitArticle();
	}

	@Test(groups = {"RTE_extended", "RTE_extended_004"})
	public void RTE_004_ExternalLink() {
		String articleName = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
		ArticlePageObject article = base.openArticleByName(wikiURL, articleName);
		SourceEditModePageObject source = article.openCurrectArticleSourceMode();
		source.clickExternalLink();
		source.checkSourceContent("[http://www.example.com link title]");
		source.submitArticle();
	}

	@Test(groups = {"RTE_extended", "RTE_extended_005"})
	public void RTE_005_HeadLine() {
		String articleName = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
		ArticlePageObject article = base.openArticleByName(wikiURL, articleName);
		SourceEditModePageObject source = article.openCurrectArticleSourceMode();
		source.clickLvl2Headline();
		source.checkSourceContent("\n== Headline text ==\n");
		source.submitArticle();
	}

	@Test(groups = {"RTE_extended", "RTE_extended_006"})
	public void RTE_006_EmbedFile() {
		String articleName = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
		ArticlePageObject article = base.openArticleByName(wikiURL, articleName);
		SourceEditModePageObject source = article.openCurrectArticleSourceMode();
		source.clickEmbedFile();
		source.checkSourceContent("[[File:Example.jpg]]");
		source.submitArticle();
	}

	@Test(groups = {"RTE_extended", "RTE_extended_007"})
	public void RTE_007_EmbedMedia() {
		String articleName = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
		ArticlePageObject article = base.openArticleByName(wikiURL, articleName);
		SourceEditModePageObject source = article.openCurrectArticleSourceMode();
		source.clickEmbedMedia();
		source.checkSourceContent("[[Media:Example.ogg]]");
		source.submitArticle();
	}

	@Test(groups = {"RTE_extended", "RTE_extended_008"})
	public void RTE_008_Math() {
		String articleName = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
		ArticlePageObject article = base.openArticleByName(wikiURL, articleName);
		SourceEditModePageObject source = article.openCurrectArticleSourceMode();
		source.clickMath();
		source.checkSourceContent("<math>Insert formula here</math>");
		source.submitArticle();
	}

	@Test(groups = {"RTE_extended", "RTE_extended_009"})
	public void RTE_009_Nowiki() {
		String articleName = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
		ArticlePageObject article = base.openArticleByName(wikiURL, articleName);
		SourceEditModePageObject source = article.openCurrectArticleSourceMode();
		source.clickNowiki();
		source.checkSourceContent("<nowiki>Insert non-formatted text here</nowiki>");
		source.submitArticle();
	}

	@Test(groups = {"RTE_extended", "RTE_extended_010"})
	public void RTE_010_Signature() {
		String articleName = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
		ArticlePageObject article = base.openArticleByName(wikiURL, articleName);
		SourceEditModePageObject source = article.openCurrectArticleSourceMode();
		source.clickSignature();
		source.checkSourceContent("--~~~~");
		source.submitArticle();
	}

	@Test(groups = {"RTE_extended", "RTE_extended_011"})
	public void RTE_011_HLine() {
		String articleName = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
		ArticlePageObject article = base.openArticleByName(wikiURL, articleName);
		SourceEditModePageObject source = article.openCurrectArticleSourceMode();
		source.clickHorizontalLine();
		source.checkSourceContent("\n----\n");
		source.submitArticle();
	}

	@Test(groups = {"RTE_extended", "RTE_extended_012"})
	public void RTE_012_Photo() {
		String articleName = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
		ArticlePageObject article = base.openArticleByName(wikiURL, articleName);
		SourceEditModePageObject source = article.openCurrectArticleSourceMode();
		PhotoAddComponentObject photoAddPhoto = source.clickAddPhoto();
		PhotoOptionsComponentObject photoOptions = photoAddPhoto.addPhotoFromWiki(PageContent.ARTICLESOURCEMODEFILE);
		photoOptions.setCaption(PageContent.CAPTION);
		photoOptions.clickAddPhoto();
		String photoName = photoAddPhoto.getPhotoName();
		source.checkSourceContent(String.format(PageContent.WIKI_TEXT_PHOTO.replace("%photoName%", photoName), PageContent.CAPTION));
		source.submitArticle();
	}

	@Test(groups = {"RTE_extended", "RTE_extended_013"})
	public void RTE_013_Slideshow() {
		String articleName = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
		ArticlePageObject article = base.openArticleByName(wikiURL, articleName);
		SourceEditModePageObject source = article.openCurrectArticleSourceMode();
		source.clickAddGallery();
		source.verifyComponentSelector();
		SlideshowBuilderComponentObject slideshowBuilder = (SlideshowBuilderComponentObject) source.addComponent("slideshow");
		AddPhotoComponentObject slideshowAddPhoto = slideshowBuilder.clickAddPhoto();
		slideshowAddPhoto.search("image");
		List<String> photoNames = slideshowAddPhoto.choosePhotos(4);
		slideshowAddPhoto.clickSelect();
		slideshowBuilder.adjustPosition(Positions.CENTER);
		slideshowBuilder.clickFinish();
		source.checkSourceContent("<gallery type=\"slideshow\" position=\"center\">\n" + photoNames.get(0) + "\n" + photoNames.get(1) + "\n" + photoNames.get(2) + "\n" + photoNames.get(3) + "\n</gallery>");
		source.submitArticle();
	}

	@Test(groups = {"RTE_extended", "RTE_extended_014"})
	public void RTE_014_Gallery() {
		String articleName = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
		ArticlePageObject article = base.openArticleByName(wikiURL, articleName);
		SourceEditModePageObject source = article.openCurrectArticleSourceMode();
		source.clickAddGallery();
		source.verifyComponentSelector();
		GalleryBuilderComponentObject galleryBuiler = (GalleryBuilderComponentObject) source.addComponent("gallery");
		AddPhotoComponentObject galleryAddPhoto = galleryBuiler.clickAddPhoto();
		galleryAddPhoto.search("image");
		List<String> photoNames = galleryAddPhoto.choosePhotos(4);
		galleryAddPhoto.clickSelect();
		galleryBuiler.adjustPosition(PositionsGallery.CENTER);
		galleryBuiler.adjustColumns("2");
		galleryBuiler.adjustSpacing(SpacingGallery.SMALL);
		galleryBuiler.adjustOrientation(Orientation.LANDSCAPE);
		galleryBuiler.clickFinish();
		source.checkSourceContent("<gallery position=\"center\" columns=\"2\" spacing=\"small\">\n" + photoNames.get(0) + "\n" + photoNames.get(1) + "\n" + photoNames.get(2) + "\n" + photoNames.get(3) + "\n</gallery>");
		source.submitArticle();
	}

	@Test(groups = {"RTE_extended", "RTE_extended_015"})
	public void RTE_015_Slider() {
		String articleName = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
		ArticlePageObject article = base.openArticleByName(wikiURL, articleName);
		SourceEditModePageObject source = article.openCurrectArticleSourceMode();
		source.clickAddGallery();
		source.verifyComponentSelector();
		SliderBuilderComponentObject sliderBuilder = (SliderBuilderComponentObject) source.addComponent("slider");
		sliderBuilder.selectMenuPosition(MenuPositions.VERTICAL);
		AddPhotoComponentObject sliderAddPhoto = sliderBuilder.clickAddPhoto();
		sliderAddPhoto.search("image");
		List<String> photoNames = sliderAddPhoto.choosePhotos(4);
		sliderAddPhoto.clickSelect();
		sliderBuilder.clickFinish();
		source.checkSourceContent("<gallery type=\"slider\" orientation=\"right\">\n" + photoNames.get(0) + "\n" + photoNames.get(1) + "\n" + photoNames.get(2) + "\n" + photoNames.get(3) + "\n</gallery>");
		source.submitArticle();
	}

	@Test(groups = {"RTE_extended", "RTE_extended_016", "Media"})
	public void RTE_016_Video() {
		String articleName = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
		ArticlePageObject article = base.openArticleByName(wikiURL, articleName);
		SourceEditModePageObject source = article.openCurrectArticleSourceMode();
		VetAddVideoComponentObject vetAddingVideo = source.clickAddVideo();
		VetOptionsComponentObject vetOptions = vetAddingVideo.addVideoByUrl(VideoContent.YOUTUBE_VIDEO_URL);
		vetOptions.setCaption(PageContent.CAPTION);
		vetOptions.submit();
		source.checkSourceVideoContent("[[" + VideoContent.YOUTUBE_VIDEO_WIKITEXT + PageContent.CAPTION + "]]");
		source.submitArticle();
	}


	@Test(groups = {"RTE_extended", "RTE_extended_017"})
	public void RTE_017_MoreMainTools() {
		String articleName = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
		ArticlePageObject article = base.openArticleByName(wikiURL, articleName);
		SourceEditModePageObject source = article.openCurrectArticleSourceMode();
		source.checkMainTools();
		source.submitArticle();
	}

	@Test(groups = {"RTE_extended", "RTE_extended_018"})
	public void RTE_018_MoreWikiMarkupTools() {
		String articleName = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
		ArticlePageObject article = base.openArticleByName(wikiURL, articleName);
		SourceEditModePageObject source = article.openCurrectArticleSourceMode();
		source.checkWikiMarkupTools();
		source.submitArticle();
	}

	@Test(groups = {"RTE_extended", "RTE_extended_019"})
	public void RTE_019_MoreSympolsTools() {
		String articleName = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
		ArticlePageObject article = base.openArticleByName(wikiURL, articleName);
		SourceEditModePageObject source = article.openCurrectArticleSourceMode();
		source.checkSymbolsTools();
		source.submitArticle();
	}
}
