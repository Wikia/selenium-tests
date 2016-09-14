package com.wikia.webdriver.testcases.articlecrudtests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.wikipage.editmode.WikiArticleEditMode;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ArticleRTETest extends NewTestTemplate {

  private static final int WIKI_TEXTS_PER_CYCLE = 300;

  static public String[] createWikitexts() {
    return new String[]{
        // new lines
        "\n1a",
        "\n\n1b",
        "\n\n\n1c",
        "\n\n\n\n1d",
        "\n\n\n\n\n1e",
        "\n\n\n\n\n\n1f",
        "\n\n\n\n\n\n\n1g",
        "\n\n\n\n\n\n\n\n1h",
        "\n\n\n\n\n\n\n\n\n1i",
        "\n\n\n\n\n\n\n\n\n\n1j",
        "1a",
        "1\nb",
        "1\n\nc",
        "1\n\n\nd\n\n\nx",
        "1\n\n\n\ne\n\n3",
        "1\n\n\n\n\nf\n3",
        "1\n\n\n\n\n\ng\n3",
        "1\n\n\n\n\n\n\nh\n3",
        "1\n\n\n\n\n\n\n\ni\n3",
        "1\n\n\n\n\n\n\n\n\nj\n3",
        "1\n\n\n\n\n\n\n\n\n\nk\n\n\n3",
        "1\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nl\n\n\n\n\n\n3",
        "1\n\n\n\n\n\n\nm\n\n\n\n\n\n3\n\n\n\n4\n\n\n\n\n5\n6\n\n\n\n\n\n\n\n\n\n7\n8\n\n9",
        "1 \na",
        "1  \nb",
        "1   \nc",
        "1    \nd",
        "1 b\n3 4\n\n5 6\n\n\ny 8",
        // lists
        "#1\n#2\n#3",
        "*1\n*2\n*3",
        "#1\n##2\n##3\n###4",
        "*1\n**2\n**3\n***4",
        "#1\n#*2\n#**5\n#**3\n#4",
        "#1\n##1\n##2\n###1\n###*1\n###*2\n###**345\n###**567\n###**#1\n###**#2\n###**##123123\n###**#3\n###**123\n###*3\n###2\n##3\n#2\n#3\n#4\n#5",
        "\n#1\n*2\n\n#3\n\n\n*4\n\n*5\n#6",
        "\n\n*1\n*2\n*3",
        "\n\n\n*1\n*2\n*3",
        "*1\n*2\n*a\n#a\n#b\n#c",
        "*1\n*2\n*b\n\n#a\n#b\n#c",
        "*1\n*2\n*c\n\n\n#a\n#b\n#c",
        "*1\n*2\n*d\n\n\n\n#a\n#b\n#c",
        "*1\n*2\n*e\n\n\n\n\n#a\n#b\n#c",
        "\n\n*1\n*2",
        "\n\n\n*1\n\n*2",
        "\n\n\n\n*1\n\n\n*2",
        "*1\n\n\n\n*2",
        "\n\n\n\n\n\n\n\n\n*1\n\n\n\n\n*2",
        "#1\n#a",
        "#1\n\n#b",
        "#1\n\n\n#c",
        "#1\n\n\n\n#d",
        "#1\n\n\n\n\n#e",
        "1\n*2\n*a\n4",
        "1\n\n*2\n*b\n\n4",
        "\n1\n\n*2\n\n\n*c\n\n4",
        "1\n#2\n#3\na",
        "1\n\n#2\n#3\n\nb",
        "\n1\n\n#2\n\n\n#3\n\nc",
        "\n1\n\n#2\n\n\n*5\n\n\n#3\n\nd",
        // headlines
        "=1=\n= 1=\n=1 =\n= 1 =\n=  1  =\n==1==\n== 1==\n==1 ==\n== 1 ==\n==  1  ==\n===1===\n=== 1===\n===1 ===\n=== 1 ===\n===  1  ===\n====1====\n==== 1====\n====1 ====\n==== 1 ====\n====  1  ====\n=====1=====\n===== 1=====\n=====1 =====\n===== 1 =====\n=====  1  =====\n======1======\n====== 1======\n======1 ======\n====== 1 ======\n======  1  ======",
        "* 1a\n* 2\n*  3\n*     10\n*      11\n*      12\n*  4\n*   5\n*   6\n*    7\n*    8\n*     9",
        "# 1b\n# 2\n#  3\n#     10\n#      11\n#      12\n#  4\n#   5\n#   6\n#    7\n#    8\n#     9",
        "\n=1a=",
        "\n\n=1b=",
        "\n\n\n=1c=",
        "\n\n\n\n=1d=",
        "\n\n\n\n\n=1e=",
        "\n\n\n\n\n\n=1f=",
        "\n\n\n\n\n\n\n=1g=",
        "\n\n\n\n=1=\na\nb\n\n\n=2=\n\na\n\nb\n==     3     ==\n*a\n*b\n#c\n#d\n1\n*1\n\n1\n=== 4===\n=5 =\n=== 6 ===\n1 2 3 4\n a\n2\n\n3\n\n\n\n\n\n a",
        // tables
        "{|\n|123\n|}",
        "\n{|\n|123\n|}",
        "\n\n{|\n|123\n|}",
        "\n\n\n{|\n|123\n|}",
        "{|\n|123\n|}\n{|\n|123\n|}",
        "{|\n|123\n|}\n\n{|\n|123\n|}",
        "{|\n|123\n|}\n\n\n{|\n|123\n|}",
        "{|\n|123\n|}\n\n\n\n{|\n|123\n|}",
        "{|\n|123||456\n|-\n|abc\n|def\n|}",
        "{|\n!abc\n!def\n|-\n!123!!456\n|-\n|abc\n|def\n|}",
        "{| border=\"1\"\n|foo\n|}",
        "{|\n|123\n{|\n|123\n|}\n{|\n|123\n|}\n|}",
        "{|\n|\n{|\n|\n{|\n|123\n|}\n|}\n|}",
        "{|\n|\n123\n|}",
        "{|\n|\n*123\n|}",
        "{|\n|\n==123==\n|}",
        "{|\n|\n 123\n|}",
        "{|\n|123\n456\n|}",
        "{| border=\"1\"\n|+foo\n|- style=\"color: red;\"\n|123\n| align=\"right\"|456\n|}",
        "{| style=\"color: red;\" class=\"table\"\n|+ align=\"bottom\" style=\"color:#66f\"|foo\n|-\n|bar\n|}",
        "{| style=\"color: blue\"\n|123\n|}\n\n{|style=\"color: #f00;border:solid 1px green\"\n|123\n|}",
        "{|\n|+foo\n|-\n!bar\n|-\n|123\n|}",
        "{|\n|Test\n{|\n|Test\n|}\n|}",
        "foo\n{|\n|bar\n|}\nfoo",
        "foo\n\n{|\n|bar\n|}\n\n\nfoo",
        "*foo\n{|\n|bar\n|}\n foo",
        "=foo=\n{|\n|bar\n|}\n\nfoo",
        "{| class=\"foo\"\n|-\n|\n|\n|}\n\n{| class=\"bar\"\n|\n|\n|}\n\n{| class=\"foo\"\n|-\n|\n|\n|}",
        "{|\n|-\n|\n|\n|}",
        "{|\n|\n|\n|}",
        "{|\n|-\n|\n|\n|}",
        "{|\n|-\n!\n!\n|-\n|\n|\n|}",
        "{|\n!\n!\n|-\n|\n|\n|}",
        // BugId: 11235
        "{|\n|[[link]]\ntext1\n|}",
        "{|\n|[[link]] text1\ntext2\n|}",
        "{|\n|[[link]] text1\ntext2\ntext3\n|}",
        "{|\n|[[link]] text1\ntext2\n|text3\n|}",
        "{|\n|text1 [[link]]\ntext2\n|}",
        "{|\n|text1 [[link]]\ntext2\ntext3\n|}",
        "{|\n|text1 [[link]]\ntext2\n|text3\n|}",
        "{|\n|text1 [[link]] text2\ntext3\n|}",
        "{|\n|text1 [[link]] text2\ntext3\ntext4\n|}",
        "{|\n|text1 [[link]] text2\ntext3\n|text4\n|}",
        // BugId:95911
        "{|\n|-\n|\nfirst\n\nsecond\n|}",
        "{|\n|-\n| '''Text'''\n\nText\n|}",
        "{|\n|-\n|\nfirst\n\nsecond\n\nthird\n|}",
        // BugId:98729
        "{|\n|\n----\nfoo\n|\n\n----\nfoo\n|}",
        // links
        "123 [[bar]] 456",
        "abc [[foo|bar]] def",
        "[[:Category:foo]] 123",
        "[[Foo]]s 123",
        "[[#Foo]] 123", // causes complex code edgecase
        "[[/foo]] 123",
        "[[foo & bar]]",
        "[[Tower|Towers of Wizardry]]",
        "[[Tower]]s",
        "[[File:6.jpg]] 123",
        "[[File:6.jpg|thumb|caption]] 123",
        "[[File:6.jpg|center]] 123",
        "[[File:6.jpg|frame]] 123",
        "[[File:6.jpg|120px]] 123",
        "[[File:IAmBrokenImageLink]] 123 [[File:IAmBrokenImageLink|thumb]] 123 [[File:IAmBrokenImageLink|120px|bla bla bla]] 123",
        "[[File:Wiki.png|thumb|caption [[test]] ''aaa'' '''bar''']]",
        "[[File:B0rken.png|thumb|caption [[test]] ''aaa'' '''bar''']]",
        "[[Video:Video.png|caption [[foo]] ''aaa'' '''bar''']]",
        "[[Video:B0rken.png|caption [[foo]] ''aaa'' '''bar''']]",
        "[http://wp.pl]\n\n[http://wp.pl foo]\n\n[mailto:info@example.org email me]\n\n[http://wp.pl]\n\n[mailto:info@example.org?Subject=URL%20Encoded%20Subject&body=Body%20Text info]",
        "[http://wp.pl]\n\nhttp://onet.pl\n\n[http://wp.pl foo]",
        "[[Bart|<span style='color:#1A2BBB'>foo</span>]]",
        "[[Bart|<span style=\"color:#1A2BBB\">foo</span>]]",
        "<span style=\"font-family: 'comic sans ms'; color:#666\">foo</span>",
        "''i'' '''b''' - '''''bi'' b''' - '''''bi''' i''",
        // templates
        "{{123}}",
        "inline template {{123}}",
        "* {{123}} {{abc}}\n*{{456}}\n**{{789}}\n# {{123}} {{abc}}\n#{{456}}\n##{{789}}\n: {{123}} {{abc}}\n:{{456}}\n::{{789}}\n\n\n::*: {{foo}}\n:::# {{bar}}",
        "{{123|\nfoo=bar}}",
        "{|\n|+caption\n|-\n| 123\n|}",
        "{|\n| style=\"color:blue\"| 123\n| style=\"color:red\"  |456\n|}",
        "<b  style=\"color: red\">a''b''\n</b>",
        "foo<br />bar<b style=\"color: #f55;\">foo</b> '''bar'''",
        "<strike>123</strike>\n\n456",
        "\n<ul>\n<li>foo</li>\n<li>foo</li><li>foo</li>\n</ul>\n\n123\n\n\n<ul><li>foo</li></ul>",
        "table <staff/>",
        "<staff/> table",
        "123<br />456\n\n123\n<br />\n456\n\n123<br />\n456\n\n123\n<br />456",
        ":  123\n;456\n\n: 789\n\n\n\n;foo\n\n\n:abc\n;def",
        ":1\n::2\n:::3\n::::4\nabc\n\n::d",
        ":::*1\n:::**2\n:::**3",
        ":::*1\n:::**2\n:::#* 3\n:::#*#4",
        ":::*:#foo\n:::#  bar",
        "::*:#foo\n:::# bar",
        ":One\n:* Two\n:**Three",
        ";ReverseParser rox\n;*foo\n;*bar",
        // categories
        "\n[[Category:a]]\n[[Category:b]] [[Category:c]] [[Category:d]] [[Category:e]]\n\n* [[Category:f]]\n**  [[Category:g]]h\n*** i[[Category:j]]\n{|\n|[[Category:1]]\n|  [[Category:2]]\n|-\n|a[[Category:3]]\n|a\n[[Category:4]]b\n|}\n [[Category:5]] abc",
        "__TOC__\n\n{{PAGENAME}}\n\n__NOTOC__",
        ":: __TOC__\n\n{|\n|\n\n {{PAGENAME}}\n| __NOTOC__\n|}",
        "<includeonly>abc\n</includeonly>\n\n<includeonly>abc</includeonly>\n\n<includeonly>\nabc</includeonly>",
        "{{localurl:fullpagename}}\n\n{{#language:pl}}",
        "I'm, you're\n\"ąźół\"",
        "\n\n\n<includeonly>abc</includeonly>\n\n\n<includeonly>abc</includeonly>\n*<includeonly>abc</includeonly>\n* <includeonly>abc</includeonly>\n*  <includeonly>abc</includeonly>\n*   <includeonly>abc</includeonly>\n**<includeonly>abc</includeonly>\n***<includeonly>abc</includeonly>\n\n#<includeonly>abc</includeonly>\n# <includeonly>abc</includeonly>\n#  <includeonly>abc</includeonly>\n#   <includeonly>abc</includeonly>\n##<includeonly>abc</includeonly>\n <includeonly>abc</includeonly>\n  <includeonly>abc</includeonly>\n   <includeonly>abc</includeonly>\n<includeonly abc='def' ghi>abc</includeonly>",
        "\n\n\n~~~\n\n\n~~~\n*~~~\n* ~~~\n*  ~~~\n*   ~~~\n**~~~\n***~~~\n\n#~~~\n# ~~~\n#  ~~~\n#   ~~~\n##~~~\n ~~~\n  ~~~~\n   ~~~~\n~~~~",
        "[a]\n[[a]\n[[[a]\n[[[[a]\n[[[[[a]\n[[[[[[a]\n[[[[[[[a]\n[a]]\n[a]]]\n[a]]]]\n[a]]]]]\n[a]]]]]]\n[a]]]]]]]\n[a]\n[[a]]\n[[[a]]]\n[[[[a]]]]\n[[[[[a]]]]]\n[[[[[[a]]]]]]\n[[[[[[[a]]]]]]]\n[[[[[[[[a]]]]]]]]\n[[[[[[[[[a]]]]]]]]]",
        "[[Sideshow_Bob_Roberts[[Sideshow_Bob_Roberts[[Sideshow_Bob_Roberts]]]]]][[Sideshow Bob Roberts[[Sideshow Bob Roberts[[Sideshow Bob Roberts]]]]]]",
        "<te&st>a&b&c</te<&>st> &amp; &nbsp; &lt; &gt; : &#58; &#123; - 123 &#x5f;",
        "<noinclude>&#91;brackets&#93;&nbsp;&amp;&nbsp;non-breaking&nbsp;spaces</noinclude>\n\n<noinclude>[brackets] & non-breaking spaces</noinclude>",
        "« foo",
        // utf magic
        "ÀàĄąÒòỪừỲỳŹź",
        "(義) (誠) (涅 ネム) foo",
        "foo ? ;-) bar",
        "bar „foo“ ;)",
        // HTML entities
        "[[&]]\n\n[[&amp;]]\n\n[[foo & bar]]es\n\n[[Flip & Flap]]\n\n[[Flip & Flap|and &amp; entity]]\n\n[[Flip &amp; Entity]]\n\nfoo & bar\n\nfoo &amp; entity\n\n[[foo|&amp;]]\n\n[[foo|Caption with &amp; entity]]",
        "[[/foo]]\n\n[[/foo/]]\n\n[[/foo bar]]\n\n[[/foo bar/]]",
        "[[RTE_test_page/foo|foo]]\n\n[[/foo/]]\n\n[[RTE_test_page/foo|bar]]\n\n[[RTE_test_page/foo]]",
        // div
        "\n<div>123</div>\n\n<div>456</div>\n\n\n<div>789</div>",
        "<div>123</div>\n\n<div>456</div>\n\n\n\n<div>\n\n\n789</div>",
        "<div>\n\n{|\n|123\n|}\n</div>",
        "<div>\n\n\n pre\n</div>",
        "<div>\n* 123\n</div>\n\n<div>\n# 123\n</div>\n\n<div>\n: 123\n</div>",
        "<div>\n<span>foo</span>\n</div>\n\n<div>\n\n<span>foo</span>\n</div>\n\n\n<div><span>foo</span></div>",
        "<div>\n\n\n{|\n|123\n|}\n\n\n</div>",
        "{|\n|<div class=\"foo\"> </div>\n{|\n|bar\n|}\n|}",
        "<div>\n== foo ==\n\nbar\n</div>",
        "<div>123</div>",
        "<div>\n123\n</div>",
        "<div>\n\n123\n</div>",
        "<div>\n\n\n123\n</div>",
        "<div>\n<ul>\n<li>foo</li></ul>\n</div>",
        "<div>\n\n<ul>\n<li>foo</li></ul>\n</div>",
        "<div>\n\n\n<ul>\n<li>foo</li></ul>\n</div>",
        "<div>one\n\ntwo\n\nthree</div>",
        "<div>1\n\n2\n\n3\n\n4\n\n5\n\n6</div>",
        "<div>one\n\ntwo\n\n----\n\nthree\n\nfour</div>",
        "<div>\n\n<h2>foo</h2>\n\n{|\n|bar\n|}\n</div>",
        "<div><div><span>foo</span></div>\n<!-- bar -->\n</div>",
        "<div><div><span>foo</span></div>\n\n<!-- bar -->\n</div>",
        // BugId:96210
        //"<div style='clear:both;'></div>",
        // aligned paragraphs
        "<p style=\"text-align:right\">123</p>\n\n\n\n456",
        "<p style=\"text-align:right;\">123</p>\n\n\n\n456",
        "<p style=\"text-align: center; margin-left:40px;font-size: 150%;\">foo</p>",
        "<p style=\"text-align:center; font-size: 150%\">foo</p>",
        "<p style=\"text-indent:4em\">foo</p>",
        "<p style=\"text-align: center; height: 3em;\">&#160;</p>",
        "<p style=\"text-align: center; height: 3em;\">123&#160;456</p>",
        "<p class=\"mainpage-button\">Habitats</p>",  //BugId: 47994
        "foo<p>bar</p>",
        "foo\n<p>bar</p>",
        "foo\n\n<p>bar</p>",
        "foo\n\n\n<p>bar</p>",
        "<p>foo</p><p>bar</p>",
        "foo\n\n<!--123  -->\n\nbar",
        "abc\n\n\n<!--123\n456  \n789 \n-->",
        "{|\n!  foo\n|-\n|  bar\n|}",
        "foo\n\nhttp://images3.wikia.nocookie.net/kirkburn/images/a/a9/Example.jpg\n\nbar",
        "<br /><br />123",
        "<br />\n<br />\n\nfoo\n\n<br /><br />\n<br />\n<br />",
        "123\n\n<br style=\"clear:both\" />\n\n== 456 ==",
        "* a\n* b\n*: c\n*: d\n*: e\n** f\n**: g\n**: h",
        "* abc\n** def\n*# ghi\n\n* abc\n** def\n\n*# ghi",
        "== test ==\n*  \n** foo\n** bar\n*** 123\n*",
        "<div>foo\n*bar\n</div>\n\n<div>foo\n{|\n|123\n|}\n</div>",
        "<div>123\n\n{|\n|123\n|}\n</div>",
        "<div><span>foo</span>\n{|\n|bar\n|}\n</div>",
        "<div><span>foo</span>\n\n{|\n|bar\n|}\n</div>",
        "123\n----\n456\n\n789\n\n----\n\nabc",
        // tables
        "{|\n|foo [[bar]]\n|foo '''bar'''\n|}",
        "{|\n| foo &rarr; bar\n| 123 &nbsp; 456\n|-\n| abc &mdash;\n| def &nbsp;\n|}",
        "{|\n|\n----\nfoo\n|\n\n----\nfoo\n|}",
        "[[File:Foo.ogg]]",
        "[[Media:Wiki.png]]\n\n[[File:Wiki.png]]\n\n[[Media:Foo.png]]",
        "[[Image:Placeholder]]",
        "[[Image:Placeholder|thumb|200px|foo caption bar]]",
        "[[Video:SomethingNotExisting]]",
        "{|\n! Text !! Text !! Text\n|-\n|  foo   || bar|| 123\n|-\n|1\n| 2\n|  3\n|}",
        "{|\n|''foo''\n* bar\n|}",
        "{|\n|[[foo]]\n* bar\n|}",
        "{|\n|''foo'' bar\n* list item\n|}",
        "{|\n|[[foo]]<br />bar\n|}",
        "{|\n|[[foo]]\n<br />bar\n|}",
        "{|\n|  foo ||    || bar  ||\n|}",
        "<gallery caption=\"Sample\" widths=\"200px\" heights=100 perrow=\"3\" captionalign=\"right\">\nSpiderpig.jpg\n</gallery>",
        "{{{text}}}hgjhgjgh{{{text|test}}}\nasas",
        "asdasdasdasd\n\n{{{text|Hmmm... This is a test}}}\nasdasd",
        // task BugID 24543, test for BugID: 22872
        "==heading==\n*item 1\n*item 2",
        "==heading==\n\n*item 1\n*item 2",
        "==heading==\n\n\n*item 1\n*item 2",
        "==heading==\n\n\n\n*item 1\n*item 2",
        // BugID: 11537
        "<div>\n<h2>Test</h2>\n* Test\n</div>",
        "<div>\n<h2>Test</h2>\n: Test\n</div>",
    };
  }

  @DataProvider(parallel = false, name = "wikiTextsProvider")
  public Iterator<Object[]> wikiTextsProvider() throws Exception {
    String[] wikiTexts = ArticleRTETest.createWikitexts();
    List al = new ArrayList();
    int startPos = 0;
    int endPos = WIKI_TEXTS_PER_CYCLE;
    while (endPos <= wikiTexts.length) {
      al.add(new Object[]{Arrays.copyOfRange(wikiTexts, startPos, endPos)});
      startPos = endPos;
      endPos += WIKI_TEXTS_PER_CYCLE;
    }
    if (startPos < wikiTexts.length) {
      al.add(new Object[]{Arrays.copyOfRange(wikiTexts, startPos, wikiTexts.length)});
    }

    return al.iterator();
  }

  @Test(dataProvider = "wikiTextsProvider", groups = {"RTE"})
  public void ArticleRTETest_001(String[] wikiTexts) {
    WikiArticleEditMode edit = new WikiArticleEditMode();
    edit.editArticleByName("RTE_test_page", wikiURL);
    edit.clickOnSourceButton();
    for (String wikitext : wikiTexts) {
      String tmp1;
      String tmp2;
      edit.clearSource();

      WebElement e = driver.findElement(By.cssSelector(".cke_source"));
      e.sendKeys(wikitext);

      edit.clickOnVisualButton();
      edit.clickOnSourceButton();

      e = driver.findElement(By.cssSelector(".cke_source"));
      ;
      if (Assertion.assertStringContains(e.getAttribute("value"), wikitext)) {
        tmp1 = e.getAttribute("value").replace("<", "&lt");
        tmp1.replace(">", "&gt");
        PageObjectLogging
            .log("checking value passed", "<pre>" + e.getAttribute("value") + "</pre>", true);
      } else {
        tmp1 = e.getAttribute("value").replace("<", "&lt;");
        tmp1 = tmp1.replace(">", "&gt;");
        tmp1 = tmp1.replace(" ", "&nbsp;");
        tmp2 = wikitext.replace("<", "&lt;");
        tmp2 = tmp2.replace(">", "&gt;");
        tmp2 = tmp2.replace(" ", "&nbsp;");

        PageObjectLogging.log("checking value failed", "should be: <pre>" + tmp2 + "</pre>", false);
        PageObjectLogging
            .log("checking value failed", "result is: <pre>" + tmp1 + "</pre>", false, driver);
      }
    }

    edit.clickOnVisualButton();
    edit.clickOnPublishButton();
  }
}
