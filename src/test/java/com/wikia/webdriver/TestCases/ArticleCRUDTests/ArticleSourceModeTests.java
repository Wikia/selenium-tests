package com.wikia.webdriver.TestCases.ArticleCRUDTests;

import java.util.List;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.VideoContent;
import com.wikia.webdriver.Common.Properties.Properties;
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
import com.wikia.webdriver.PageObjectsFactory.PageObject.Article.EditMode.SourceEditModePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginPageObject;


/**
 * @author Karol 'kkarolk' Kujawiak
 */
public class ArticleSourceModeTests extends NewTestTemplate{

	String pageName;

	@Test(groups={"RTE_extended","RTE_extended_001"})
	public void RTE_001_Bold(){
		SourceEditModePageObject source = new SourceEditModePageObject(driver);
		pageName = PageContent.articleNamePrefix+source.getTimeStamp();
		source.openWikiPage();
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		source.createNewArticleSource(pageName, 1);
		source.clearSource();
		source.clickBold();
		source.checkSourceContent("'''Bold text'''");
		source.submitArticle();
	}

	@Test(groups={"RTE_extended","RTE_extended_002"})
	public void RTE_002_Italic(){
		SourceEditModePageObject source = new SourceEditModePageObject(driver);
		pageName = PageContent.articleNamePrefix+source.getTimeStamp();
		source.openWikiPage();
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		source.createNewArticleSource(pageName, 1);
		source.clearSource();
		source.clickItalic();
		source.checkSourceContent("''Italic text''");
		source.submitArticle();
	}

	@Test(groups={"RTE_extended","RTE_extended_003"})
	public void RTE_003_InternalLink(){
		SourceEditModePageObject source = new SourceEditModePageObject(driver);
		pageName = PageContent.articleNamePrefix+source.getTimeStamp();
		source.openWikiPage();
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		source.createNewArticleSource(pageName, 1);
		source.clearSource();
		source.clickInternalLink();
		source.checkSourceContent("[[Link title]]");
		source.submitArticle();
	}

	@Test(groups={"RTE_extended","RTE_extended_004"})
	public void RTE_004_ExternalLink(){
		SourceEditModePageObject source = new SourceEditModePageObject(driver);
		pageName = PageContent.articleNamePrefix+source.getTimeStamp();
		source.openWikiPage();
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		source.createNewArticleSource(pageName, 1);
		source.clearSource();
		source.clickExternalLink();
		source.checkSourceContent("[http://www.example.com link title]");
		source.submitArticle();
	}

	@Test(groups={"RTE_extended","RTE_extended_005"})
	public void RTE_005_HeadLine(){
		SourceEditModePageObject source = new SourceEditModePageObject(driver);
		pageName = PageContent.articleNamePrefix+source.getTimeStamp();
		source.openWikiPage();
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		source.createNewArticleSource(pageName, 1);
		source.clearSource();
		source.clickLvl2Headline();
		source.checkSourceContent("\n== Headline text ==\n");
		source.submitArticle();
	}

	@Test(groups={"RTE_extended","RTE_extended_006"})
	public void RTE_006_EmbedFile(){
		SourceEditModePageObject source = new SourceEditModePageObject(driver);
		pageName = PageContent.articleNamePrefix+source.getTimeStamp();
		source.openWikiPage();
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		source.createNewArticleSource(pageName, 1);
		source.clearSource();
		source.clickEmbedFile();
		source.checkSourceContent("[[File:Example.jpg]]");
		source.submitArticle();
	}

	@Test(groups={"RTE_extended","RTE_extended_007"})
	public void RTE_007_EmbedMedia(){
		SourceEditModePageObject source = new SourceEditModePageObject(driver);
		pageName = PageContent.articleNamePrefix+source.getTimeStamp();
		source.openWikiPage();
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		source.createNewArticleSource(pageName, 1);
		source.clearSource();
		source.clickEmbedMedia();
		source.checkSourceContent("[[Media:Example.ogg]]");
		source.submitArticle();
	}

	@Test(groups={"RTE_extended","RTE_extended_008"})
	public void RTE_008_Math(){
		SourceEditModePageObject source = new SourceEditModePageObject(driver);
		pageName = PageContent.articleNamePrefix+source.getTimeStamp();
		source.openWikiPage();
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		source.createNewArticleSource(pageName, 1);
		source.clearSource();
		source.clickMath();
		source.checkSourceContent("<math>Insert formula here</math>");
		source.submitArticle();
	}

	@Test(groups={"RTE_extended","RTE_extended_009"})
	public void RTE_009_Nowiki(){
		SourceEditModePageObject source = new SourceEditModePageObject(driver);
		pageName = PageContent.articleNamePrefix+source.getTimeStamp();
		source.openWikiPage();
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		source.createNewArticleSource(pageName, 1);
		source.clearSource();
		source.clickNowiki();
		source.checkSourceContent("<nowiki>Insert non-formatted text here</nowiki>");
		source.submitArticle();
	}

	@Test(groups={"RTE_extended","RTE_extended_010"})
	public void RTE_010_Signature(){
		SourceEditModePageObject source = new SourceEditModePageObject(driver);
		pageName = PageContent.articleNamePrefix+source.getTimeStamp();
		source.openWikiPage();
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		source.createNewArticleSource(pageName, 1);
		source.clearSource();
		source.clickSignature();
		source.checkSourceContent("--~~~~");
		source.submitArticle();
	}

	@Test(groups={"RTE_extended","RTE_extended_011"})
	public void RTE_011_HLine(){
		SourceEditModePageObject source = new SourceEditModePageObject(driver);
		pageName = PageContent.articleNamePrefix+source.getTimeStamp();
		source.openWikiPage();
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		source.createNewArticleSource(pageName, 1);
		source.clearSource();
		source.clickHorizontalLine();
		source.checkSourceContent("\n----\n");
		source.submitArticle();
	}

	@Test(groups={"RTE_extended","RTE_extended_012"})
	public void RTE_012_Photo(){
		SourceEditModePageObject source = new SourceEditModePageObject(driver);
		pageName = PageContent.articleNamePrefix+source.getTimeStamp();
		source.openWikiPage();
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		source.createNewArticleSource(pageName, 1);
		source.clearSource();
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
		SourceEditModePageObject source = new SourceEditModePageObject(driver);
		pageName = PageContent.articleNamePrefix+source.getTimeStamp();
		source.openWikiPage();
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		source.createNewArticleSource(pageName, 1);
		source.clearSource();
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
		SourceEditModePageObject source = new SourceEditModePageObject(driver);
		pageName = PageContent.articleNamePrefix+source.getTimeStamp();
		source.openWikiPage();
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		source.createNewArticleSource(pageName, 1);
		source.clearSource();
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
		SourceEditModePageObject source = new SourceEditModePageObject(driver);
		pageName = PageContent.articleNamePrefix+source.getTimeStamp();
		source.openWikiPage();
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		source.createNewArticleSource(pageName, 1);
		source.clearSource();
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

	@Test(groups={"RTE_extended","RTE_extended_016"})
	public void RTE_016_Video(){
		SourceEditModePageObject source = new SourceEditModePageObject(driver);
		pageName = PageContent.articleNamePrefix+source.getTimeStamp();
		source.openWikiPage();
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		source.createNewArticleSource(pageName, 1);
		source.clearSource();
		VetAddVideoComponentObject vetAddingVideo = source.clickAddVideo();
		VetOptionsComponentObject vetOptions = vetAddingVideo.addVideoByUrl(VideoContent.youtubeVideoURL);
		vetOptions.setCaption(PageContent.caption);
		vetOptions.submit();
		source.checkSourceVideoContent("[["+VideoContent.youtubeVideoWikiText+PageContent.caption+"]]");
		source.submitArticle();
	}


	@Test(groups={"RTE_extended","RTE_extended_017"})
	public void RTE_017_MoreMainTools(){
		SourceEditModePageObject source = new SourceEditModePageObject(driver);
		pageName = PageContent.articleNamePrefix+source.getTimeStamp();
		source.openWikiPage();
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		source.createNewArticleSource(pageName, 1);
		source.checkMainTools();
		source.submitArticle();
	}

	@Test(groups={"RTE_extended","RTE_extended_018"})
	public void RTE_018_MoreWikiMarkupTools(){
		SourceEditModePageObject source = new SourceEditModePageObject(driver);
		pageName = PageContent.articleNamePrefix+source.getTimeStamp();
		source.openWikiPage();
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		source.createNewArticleSource(pageName, 1);
		source.checkWikiMarkupTools();
		source.submitArticle();
	}

	@Test(groups={"RTE_extended","RTE_extended_019"})
	public void RTE_019_MoreSympolsTools(){
		SourceEditModePageObject source = new SourceEditModePageObject(driver);
		pageName = PageContent.articleNamePrefix+source.getTimeStamp();
		source.openWikiPage();
		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.logInCookie(Properties.userNameStaff, Properties.passwordStaff);
		source.createNewArticleSource(pageName, 1);
		source.checkSymbolsTools();
		source.submitArticle();
	}
}
