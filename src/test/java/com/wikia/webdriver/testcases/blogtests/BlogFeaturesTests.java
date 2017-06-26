package com.wikia.webdriver.testcases.blogtests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.VideoContent;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
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
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialCreatePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.blog.BlogPage;

@Test(groups = "BlogFeaturesTests")
@Execute(asUser = User.BLOGS)
public class BlogFeaturesTests extends NewTestTemplate {

  @Test
  public void UserCanAddGalleryOnBlogPost() {
    SpecialCreatePage createPage = new WikiBasePageObject().openSpecialCreateBlogPage(wikiURL);
    String blogPostTitle = PageContent.BLOG_POST_NAME_PREFIX + createPage.getTimeStamp();
    VisualEditModePageObject blogEdit = createPage.populateTitleField(blogPostTitle);
    GalleryBuilderComponentObject galleryBuiler = blogEdit.clickGalleryButton();
    AddPhotoComponentObject galleryAddPhoto = galleryBuiler.clickAddPhoto();
    galleryAddPhoto.search("image");
    galleryAddPhoto.choosePhotos(4);
    galleryAddPhoto.clickSelect();
    galleryBuiler.adjustPosition(PositionsGallery.CENTER);
    galleryBuiler.adjustColumns("2");
    galleryBuiler.adjustSpacing(SpacingGallery.SMALL);
    galleryBuiler.adjustOrientation(Orientation.LANDSCAPE);
    galleryBuiler.clickFinish();
    blogEdit.verifyGallery();
    BlogPage blogPage = blogEdit.submitBlog();
    blogPage.verifyGallery();
  }

  @Test
  public void UserCanAddSlideShowOnBlogPost() {
    WikiBasePageObject base = new WikiBasePageObject();
    SpecialCreatePage createPage = base.openSpecialCreateBlogPage(wikiURL);
    String blogPostTitle = PageContent.BLOG_POST_NAME_PREFIX + createPage.getTimeStamp();
    VisualEditModePageObject blogEdit = createPage.populateTitleField(blogPostTitle);
    SlideshowBuilderComponentObject slideshowBuilder = blogEdit.clickSlideshowButton();
    AddPhotoComponentObject slideshowAddPhoto = slideshowBuilder.clickAddPhoto();
    slideshowAddPhoto.search("image");
    slideshowAddPhoto.choosePhotos(4);
    slideshowAddPhoto.clickSelect();
    slideshowBuilder.adjustPosition(Positions.CENTER);
    slideshowBuilder.clickFinish();
    blogEdit.verifySlideshow();
    BlogPage blogPage = blogEdit.submitBlog();
    blogPage.verifySlideshow();
  }

  @Test
  public void UserCanAddSliderOnBlogPost() {
    WikiBasePageObject base = new WikiBasePageObject();
    SpecialCreatePage createPage = base.openSpecialCreateBlogPage(wikiURL);
    String blogPostTitle = PageContent.BLOG_POST_NAME_PREFIX + createPage.getTimeStamp();
    VisualEditModePageObject blogEdit = createPage.populateTitleField(blogPostTitle);
    SliderBuilderComponentObject sliderBuilder = blogEdit.clickSliderButton();
    sliderBuilder.selectMenuPosition(MenuPositions.VERTICAL);
    AddPhotoComponentObject sliderAddPhoto = sliderBuilder.clickAddPhoto();
    sliderAddPhoto.search("image");
    sliderAddPhoto.choosePhotos(4);
    sliderAddPhoto.clickSelect();
    sliderBuilder.clickFinish();
    blogEdit.verifySlider();
    BlogPage blogPage = blogEdit.submitBlog();
    blogPage.verifySlider();
  }

  @Test
  @Execute(asUser = User.BLOGS, onWikia = "mobileregressiontesting")
  public void UserCanAddVideoOnBlogPost() {
    WikiBasePageObject base = new WikiBasePageObject();
    SpecialCreatePage createPage = base.openSpecialCreateBlogPage(wikiURL);
    String blogPostTitle = PageContent.BLOG_POST_NAME_PREFIX + createPage.getTimeStamp();
    VisualEditModePageObject blogEdit = createPage.populateTitleField(blogPostTitle);
    VetAddVideoComponentObject vetAddVideo = blogEdit.clickVideoButton();
    VetOptionsComponentObject vetOptions =
        vetAddVideo.addVideoByUrl(VideoContent.YOUTUBE_VIDEO_URL);
    vetOptions.setCaption(PageContent.CAPTION);
    vetOptions.submit();
    blogEdit.verifyVideo();
    BlogPage blogPage = blogEdit.submitBlog();
    blogPage.verifyVideo();
  }

  @Test
  public void UserCanAddImageOnBlogPost() {
    WikiBasePageObject base = new WikiBasePageObject();
    SpecialCreatePage createPage = base.openSpecialCreateBlogPage(wikiURL);
    String blogPostTitle = PageContent.BLOG_POST_NAME_PREFIX + createPage.getTimeStamp();
    VisualEditModePageObject blogEdit = createPage.populateTitleField(blogPostTitle);
    PhotoAddComponentObject photoAddPhoto = blogEdit.clickPhotoButton();
    PhotoOptionsComponentObject photoOptions = photoAddPhoto.addPhotoFromWiki("image", 1);
    photoOptions.setCaption(PageContent.CAPTION);
    photoOptions.clickAddPhoto();
    blogEdit.verifyPhoto();
    BlogPage blogPage = blogEdit.submitBlog();
    blogPage.verifyPhoto();
  }
}
