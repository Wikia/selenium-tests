# Selenium Tests
This repository contains [Selenium](http://seleniumhq.org/) tests for the [Wikia App](https://github.com/Wikia/app).

## Dependencies

1. Make sure you have [Java](http://www.java.com/) and [Maven](http://maven.apache.org/) installed on your system, if not follow the vendor instructions for installing them on your operating system.
2. In order to run tests in CHROME browser make sure you have [chromedriver](http://code.google.com/p/chromedriver/downloads/list) and it is accessibile
    * in Linux OS family it would be in /usr/bin/ for example
    * in Windows OS family you should export your chromedriver localization to system path
3. On Mac, make sure your $JAVA_HOME is set, e.g. a line like this in your .profile:

    export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.7.0_51.jdk/Contents/Home

## Adding Tests
Please remember to keep this repository up to date as the tests become worthless otherwise. A good rule of thumb is to add a new test that covers whatever bug you're fixing so we can track regressions on it. Also, make sure to code your tests in a cross-operating system compliant manor as it's valid to run them in Windows, Mac or Linux environments.

## Running Tests

The following steps should get you set up for running Selenium tests locally on your machine:

1. Clone this repository to your local machine (not your devbox).
2. Clone selenium-config repository to the same folder (eg. myfolder/selenium-tests and myfolder/selenium-config)
3. Create symlinks for `/selenium-config/config.xml` and `/selenium-config/captcha.txt` (note the leading slash) that point to these files in your [selenium-config](https://github.com/Wikia/selenium-config) repository.
4. Open up a terminal and navigate to the root directory of the repository.

Using Maven, you may now run any tests you want on whatever database and domain you want:

    mvn clean test -Dbrowser=<browser> -Denv=<environment> -Dwiki-name=<wiki> -Dgroups=<group> -DtestSuite=<your_custom_suite.xml>

It is also possible to run mobile tests on real devices (Currently only Android is supported). In case of running tests on real devices additional parameters are supported:

1. -Dplatform - determine if IOS or ANDROID should be used
2. -Dplatform-version - get device's uuid from mobile config for provided platform version
3. -Ddevice-id - get device with provided uuid

If both platform-version and device-id are provided device with provided uuid would be chosen.

### Example commands

All commands must be run from the `selenium-tests` directory cloned during setup process above

##### Running tests on preview for mediawiki119 wikia

    mvn clean test -Dbrowser=FF -Denv=preview -Dwiki-name=mediawiki119 -Dgroups=Login

##### Running tests on production for mediawiki119 wikia

    mvn clean test -Dbrowser=FF -Denv=prod -Dwiki-name=mediawiki119 -Dgroups=Login

##### Running tests on devbox for muppet wikia

    mvn clean test -Dbrowser=CHROME -Denv=dev-karol -Dwiki-name=muppet -Dgroups=Login

##### Running tests on devbox for muppet wikia on a Chrome pretending to be an Android device

    mvn clean test -Dbrowser=CHROMEMOBILE -Denv=dev-karol -Dwiki-name=muppet -Dgroups=Login

##### Running tests on devbox for muppet wikia on a Chrome pretending to be an Android device forcing Mercury skin

    mvn clean test -Dbrowser=CHROMEMOBILEMERCURY -Denv=dev-karol -Dwiki-name=muppet -Dgroups=Login

If everything goes right it should log in as a QATestsUser.

##### Running tests on real device

    mvn clean test -Dplatform=ANDROID -Ddevice-id=0243e69a8ec7e8de -Dgroups=MobileLogin

    mvn clean test -Dplatform=ANDROID -Dplatform-version=4.4 -Dgroups=MobileLogin

### Parameters

The following are valid test parameters:

* `-Dbrowser` - Which browser to use, for example "CHROME"
* `-Dbase-address` - (Deprecated) The base URL to run in the browser, for example "http://yourname.wikia-dev.com/"
* `-Denv` - The environment on which the test(s) should be run in, for example "prod", "preview", "dev-<name>", "sandbox-<number>"
* `-Dwiki-name` - The wiki where the test(s) should be run on, for example "mediawiki119", "muppet"
* `-Dgroups` - (Optional) Which test groups to run, for example "Chat". Optional. Uses all tests if omitted
* `-Dlive-domain` - (Optional) The base URL to run in the browser, for example "http://www.wikia.com/". Only required for Hubs tests

### Browsers

The following are valid for use in the `-Dbrowser` parameter:

* FF
* CHROME
* IE
* GHOST (phantomjs)

### Groups

The following are valid for use in the `-Dgroups` parameter:

* ArticleCRUDUser
* ArticleCRUDAnon
* ArticleComments
* ArticleEditDropdown
* ArticleActionsAdmin
* ArticleFeaturesCRUDAnon
* ArticleFeaturesCRUDUser
* ArticleTOCTests
* AvatarTest
* BlogTests
* BlogFeaturesTests
* BlogCommentsTests
* CategoriesTestsArticle
* CategoriesTestsArticleEditMode
* Chat
* CNW_lang
* CNW
* EditAccountTest
* FollowArticle
* FollowBlog
* FollowPhoto
* FollowVideo
* ForumBoardTests
* ForumEditMode
* ForumThreadTests
* ForumNotificationsTests
* ForgottenPassword
* ForcedLogin
* Hubs
* ImageServing
* ImageStorageTests
* InteractiveMaps
* Login
* Media
* MessageWall
* MessageWallFeatures
* MessageWallNotificationsFollowersMessageTests
* MessageWallNotificationsFollowersResponseTests
* MessageWallNotificationsOwnerTests
* MessageWallNotificationsThreadCreatorTests
* MessageWallNotificationsThreadParticipantTests
* MultiWikiFinder
* RTE
* RTE_extended
* CrossWikiSearch
* IntraWikiSearch
* IntraWikiSearchExactMatch
* SignUp
* SpecialVideo
* ThemeDesigner
* Toolbar
* Top_10_list_Tests
* UsersAndRights
* VetAddVideo
* VetModalAlignment
* VetModalCaption
* VetModalWidth
* VetProvidersRV
* VetProvidersArticle
* VideoSuggestions
* VideoArticlePlacehoder
* WamPageTests
* WikiActivity

## Reading Logs

Most tests write logs and take screenshots while they run which provide further information about what happened if they failed. These files reside in the `./logs` directory and can be viewed locally in your browser by navigating to that folder.

