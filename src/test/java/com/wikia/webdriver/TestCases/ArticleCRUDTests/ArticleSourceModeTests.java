package com.wikia.webdriver.TestCases.ArticleCRUDTests;

import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.VideoContent;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.AddPhoto.AddPhotoComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Gallery.GalleryBuilderComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Gallery.GalleryBuilderComponentObject.Orientation;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Gallery.GalleryBuilderComponentObject.PositionsGallery;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Gallery.GalleryBuilderComponentObject.SpacingGallery;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Photo.PhotoAddComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Photo.PhotoOptionsComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Slider.SliderBuilderComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Slider.SliderBuilderComponentObject.MenuPositions;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Slideshow.SlideshowBuilderComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Slideshow.SlideshowBuilderComponentObject.Positions;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetAddVideoComponentObject;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Vet.VetOptionsComponentObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.ArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.SourceEditModePageObject;


/**
 * @author Karol 'kkarolk' Kujawiak
 * @ownership Contribution
 */
public class ArticleSourceModeTests extends NewTestTemplate{

	Credentials credentials = config.getCredentials();
	WikiBasePageObject base;

	@BeforeMethod(alwaysRun = true)
	public void setup_VEPreferred() {
		base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
	}

	@Test(groups={"RTE_extended","RTE_extended_001"})
	public void RTE_001_Bold(){
		String articleName = PageContent.articleNamePrefix+base.getTimeStamp();
		ArticlePageObject article = base.openArticleByName(wikiURL, articleName);
		SourceEditModePageObject source = article.openCurrectArticleSourceMode();
		source.clickBold();
		source.checkSourceContent("'''Bold text'''");
		source.submitArticle();
	}

	@Test(groups={"RTE_extended","RTE_extended_002"})
	public void RTE_002_Italic(){
		String articleName = PageContent.articleNamePrefix+base.getTimeStamp();
		ArticlePageObject article = base.openArticleByName(wikiURL, articleName);
		SourceEditModePageObject source = article.openCurrectArticleSourceMode();
		source.clickItalic();
		source.checkSourceContent("''Italic text''");
		source.submitArticle();
	}

	@Test(groups={"RTE_extended","RTE_extended_003"})
	public void RTE_003_InternalLink(){
		String articleName = PageContent.articleNamePrefix+base.getTimeStamp();
		ArticlePageObject article = base.openArticleByName(wikiURL, articleName);
		SourceEditModePageObject source = article.openCurrectArticleSourceMode();
		source.clickInternalLink();
		source.checkSourceContent("[[Link title]]");
		source.submitArticle();
	}

	@Test(groups={"RTE_extended","RTE_extended_004"})
	public void RTE_004_ExternalLink(){
		String articleName = PageContent.articleNamePrefix+base.getTimeStamp();
		ArticlePageObject article = base.openArticleByName(wikiURL, articleName);
		SourceEditModePageObject source = article.openCurrectArticleSourceMode();
		source.clickExternalLink();
		source.checkSourceContent("[http://www.example.com link title]");
		source.submitArticle();
	}

	@Test(groups={"RTE_extended","RTE_extended_005"})
	public void RTE_005_HeadLine(){
		String articleName = PageContent.articleNamePrefix+base.getTimeStamp();
		ArticlePageObject article = base.openArticleByName(wikiURL, articleName);
		SourceEditModePageObject source = article.openCurrectArticleSourceMode();
		source.clickLvl2Headline();
		source.checkSourceContent("\n== Headline text ==\n");
		source.submitArticle();
	}

	@Test(groups={"RTE_extended","RTE_extended_006"})
	public void RTE_006_EmbedFile(){
		String articleName = PageContent.articleNamePrefix+base.getTimeStamp();
		ArticlePageObject article = base.openArticleByName(wikiURL, articleName);
		SourceEditModePageObject source = article.openCurrectArticleSourceMode();
		source.clickEmbedFile();
		source.checkSourceContent("[[File:Example.jpg]]");
		source.submitArticle();
	}

	@Test(groups={"RTE_extended","RTE_extended_007"})
	public void RTE_007_EmbedMedia(){
		String articleName = PageContent.articleNamePrefix+base.getTimeStamp();
		ArticlePageObject article = base.openArticleByName(wikiURL, articleName);
		SourceEditModePageObject source = article.openCurrectArticleSourceMode();
		source.clickEmbedMedia();
		source.checkSourceContent("[[Media:Example.ogg]]");
		source.submitArticle();
	}

	@Test(groups={"RTE_extended","RTE_extended_008"})
	public void RTE_008_Math(){
		String articleName = PageContent.articleNamePrefix+base.getTimeStamp();
		ArticlePageObject article = base.openArticleByName(wikiURL, articleName);
		SourceEditModePageObject source = article.openCurrectArticleSourceMode();
		source.clickMath();
		source.checkSourceContent("<math>Insert formula here</math>");
		source.submitArticle();
	}

	@Test(groups={"RTE_extended","RTE_extended_009"})
	public void RTE_009_Nowiki(){
		String articleName = PageContent.articleNamePrefix+base.getTimeStamp();
		ArticlePageObject article = base.openArticleByName(wikiURL, articleName);
		SourceEditModePageObject source = article.openCurrectArticleSourceMode();
		source.clickNowiki();
		source.checkSourceContent("<nowiki>Insert non-formatted text here</nowiki>");
		source.submitArticle();
	}

	@Test(groups={"RTE_extended","RTE_extended_010"})
	public void RTE_010_Signature(){
		String articleName = PageContent.articleNamePrefix+base.getTimeStamp();
		ArticlePageObject article = base.openArticleByName(wikiURL, articleName);
		SourceEditModePageObject source = article.openCurrectArticleSourceMode();
		source.clickSignature();
		source.checkSourceContent("--~~~~");
		source.submitArticle();
	}

	@Test(groups={"RTE_extended","RTE_extended_011"})
	public void RTE_011_HLine(){
		String articleName = PageContent.articleNamePrefix+base.getTimeStamp();
		ArticlePageObject article = base.openArticleByName(wikiURL, articleName);
		SourceEditModePageObject source = article.openCurrectArticleSourceMode();
		source.clickHorizontalLine();
		source.checkSourceContent("\n----\n");
		source.submitArticle();
	}

	@Test(groups={"RTE_extended","RTE_extended_012"})
	public void RTE_012_Photo(){
		String articleName = PageContent.articleNamePrefix+base.getTimeStamp();
		ArticlePageObject article = base.openArticleByName(wikiURL, articleName);
		SourceEditModePageObject source = article.openCurrectArticleSourceMode();
		PhotoAddComponentObject photoAddPhoto = source.clickAddPhoto();
		PhotoOptionsComponentObject photoOptions = photoAddPhoto.addPhotoFromWiki("image", 1);
		photoOptions.setCaption(PageContent.caption);
		photoOptions.clickAddPhoto();
		String photoName = photoAddPhoto.getPhotoName();
		source.checkSourceContent(String.format(PageContent.wikiTextPhoto.replace("%photoName%", photoName), PageContent.caption));
		source.submitArticle();
	}

	@Test(groups={"RTE_extended","RTE_extended_013"})
	public void RTE_013_Slideshow(){
		String articleName = PageContent.articleNamePrefix+base.getTimeStamp();
		ArticlePageObject article = base.openArticleByName(wikiURL, articleName);
		SourceEditModePageObject source = article.openCurrectArticleSourceMode();
		source.clickAddGallery();
		source.verifyComponentSelector();
		SlideshowBuilderComponentObject slideshowBuilder = (SlideshowBuilderComponentObject)source.addComponent("slideshow");
		AddPhotoComponentObject slideshowAddPhoto = slideshowBuilder.clickAddPhoto();
		slideshowAddPhoto.search("image");
		List<String> photoNames = slideshowAddPhoto.choosePhotos(4);
		slideshowAddPhoto.clickSelect();
		slideshowBuilder.adjustPosition(Positions.Center);
		slideshowBuilder.clickFinish();
		source.checkSourceContent("<gallery type=\"slideshow\" position=\"center\">\n"+photoNames.get(0)+"\n"+photoNames.get(1)+"\n"+photoNames.get(2)+"\n"+photoNames.get(3)+"\n</gallery>");
		source.submitArticle();
	}

	@Test(groups={"RTE_extended","RTE_extended_014"})
	public void RTE_014_Gallery(){
		String articleName = PageContent.articleNamePrefix+base.getTimeStamp();
		ArticlePageObject article = base.openArticleByName(wikiURL, articleName);
		SourceEditModePageObject source = article.openCurrectArticleSourceMode();
		source.clickAddGallery();
		source.verifyComponentSelector();
		GalleryBuilderComponentObject galleryBuiler = (GalleryBuilderComponentObject) source.addComponent("gallery");
		AddPhotoComponentObject galleryAddPhoto = galleryBuiler.clickAddPhoto();
		galleryAddPhoto.search("image");
		List<String> photoNames = galleryAddPhoto.choosePhotos(4);
		galleryAddPhoto.clickSelect();
		galleryBuiler.adjustPosition(PositionsGallery.center);
		galleryBuiler.adjustColumns("2");
		galleryBuiler.adjustSpacing(SpacingGallery.small);
		galleryBuiler.adjustOrientation(Orientation.landscape);
		galleryBuiler.clickFinish();
		source.checkSourceContent("<gallery position=\"center\" columns=\"2\" spacing=\"small\">\n"+photoNames.get(0)+"\n"+photoNames.get(1)+"\n"+photoNames.get(2)+"\n"+photoNames.get(3)+"\n</gallery>");
		source.submitArticle();
	}

	@Test(groups={"RTE_extended","RTE_extended_015"})
	public void RTE_015_Slider(){
		String articleName = PageContent.articleNamePrefix+base.getTimeStamp();
		ArticlePageObject article = base.openArticleByName(wikiURL, articleName);
		SourceEditModePageObject source = article.openCurrectArticleSourceMode();
		source.clickAddGallery();
		source.verifyComponentSelector();
		SliderBuilderComponentObject sliderBuilder = (SliderBuilderComponentObject)source.addComponent("slider");
		sliderBuilder.selectMenuPosition(MenuPositions.Vertical);
		AddPhotoComponentObject sliderAddPhoto = sliderBuilder.clickAddPhoto();
		sliderAddPhoto.search("image");
		List<String> photoNames = sliderAddPhoto.choosePhotos(4);
		sliderAddPhoto.clickSelect();
		sliderBuilder.clickFinish();
		source.checkSourceContent("<gallery type=\"slider\" orientation=\"right\">\n"+photoNames.get(0)+"\n"+photoNames.get(1)+"\n"+photoNames.get(2)+"\n"+photoNames.get(3)+"\n</gallery>");
		source.submitArticle();
	}

	@Test(groups={"RTE_extended","RTE_extended_016", "Media"})
	public void RTE_016_Video(){
		String articleName = PageContent.articleNamePrefix+base.getTimeStamp();
		ArticlePageObject article = base.openArticleByName(wikiURL, articleName);
		SourceEditModePageObject source = article.openCurrectArticleSourceMode();
		VetAddVideoComponentObject vetAddingVideo = source.clickAddVideo();
		VetOptionsComponentObject vetOptions = vetAddingVideo.addVideoByUrl(VideoContent.youtubeVideoURL);
		vetOptions.setCaption(PageContent.caption);
		vetOptions.submit();
		source.checkSourceVideoContent("[["+VideoContent.youtubeVideoWikiText+PageContent.caption+"]]");
		source.submitArticle();
	}


	@Test(groups={"RTE_extended","RTE_extended_017"})
	public void RTE_017_MoreMainTools(){
		String articleName = PageContent.articleNamePrefix+base.getTimeStamp();
		ArticlePageObject article = base.openArticleByName(wikiURL, articleName);
		SourceEditModePageObject source = article.openCurrectArticleSourceMode();
		source.checkMainTools();
		source.submitArticle();
	}

	@Test(groups={"RTE_extended","RTE_extended_018"})
	public void RTE_018_MoreWikiMarkupTools(){
		String articleName = PageContent.articleNamePrefix+base.getTimeStamp();
		ArticlePageObject article = base.openArticleByName(wikiURL, articleName);
		SourceEditModePageObject source = article.openCurrectArticleSourceMode();
		source.checkWikiMarkupTools();
		source.submitArticle();
	}

	@Test(groups={"RTE_extended","RTE_extended_019"})
	public void RTE_019_MoreSympolsTools(){
		String articleName = PageContent.articleNamePrefix+base.getTimeStamp();
		ArticlePageObject article = base.openArticleByName(wikiURL, articleName);
		SourceEditModePageObject source = article.openCurrectArticleSourceMode();
		source.checkSymbolsTools();
		source.submitArticle();
	}
}
