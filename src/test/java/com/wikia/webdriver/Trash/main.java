package com.wikia.webdriver.Trash;

import java.io.IOException;
import java.io.StringWriter;

import org.apache.http.client.ClientProtocolException;

import com.googlecode.jatl.Html;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Templates.TestTemplate;



public class main extends TestTemplate{

	/**
	 * @param args
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static void main(String[] args) throws ClientProtocolException, IOException {
//		Car mercedes = new Car();
////		Car bmw = new Car();
////
////		mercedes.setNumberOfDoors(5);
////		mercedes.getNumberOfDoors();
////		mercedes.setNumberOfWheels(4);
////		mercedes.getNumberOfWheels();
////		mercedes.setNumberOfWindows(4);
////		mercedes.getNumberOfWindows();
////		mercedes.driveForward();
////		mercedes.driveBackward();
////
////
////		bmw.setNumberOfDoors(3);
////		bmw.getNumberOfDoors();
//
//		mercedes.ifelse(0);
//
//		Properties.setProperties();
//
//		DefaultHttpClient httpclient = new DefaultHttpClient();
//
//		HttpPost httpPost = new HttpPost("http://mediawiki119.wikia.com/api.php");
//		List <NameValuePair> nvps = new ArrayList<NameValuePair>();
//
//		nvps.add(new BasicNameValuePair("action", "login"));
//		nvps.add(new BasicNameValuePair("format", "xml"));
//		nvps.add(new BasicNameValuePair("lgname", Properties.userName));
//		nvps.add(new BasicNameValuePair("lgpassword", Properties.password));
//
//		httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
//
//		HttpResponse response = httpclient.execute(httpPost);
//        HttpEntity entity = response.getEntity();
//        String xmlResponse = EntityUtils.toString(entity);
//        String[] xmlResponseArr = xmlResponse.split("\"");
//        String token = xmlResponseArr[5];
//
//
//        List <NameValuePair> nvps2 = new ArrayList<NameValuePair>();
//
//        nvps2.add(new BasicNameValuePair("action", "login"));
//		nvps2.add(new BasicNameValuePair("format", "xml"));
//		nvps2.add(new BasicNameValuePair("lgname", Properties.userName));
//		nvps2.add(new BasicNameValuePair("lgpassword", Properties.password));
//		nvps2.add(new BasicNameValuePair("lgtoken", token));
//
//		httpPost.setEntity(new UrlEncodedFormEntity(nvps2, HTTP.UTF_8));
//
//		response = httpclient.execute(httpPost);
//        entity = response.getEntity();
//        xmlResponse = EntityUtils.toString(entity);
//        xmlResponseArr = xmlResponse.split("\"");
//
//        File file = new File("."+File.separator+
//				"src"+File.separator+
//				"test"+File.separator+
//				"resources"+File.separator+
//				"ChromeDriver"+File.separator+
//				"chromedriver.exe");
//			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
//		WebDriver d = new ChromeDriver();
//        d.get("http://mediawiki119.wikia.com/wiki/");
//
//        System.out.println(xmlResponseArr[13]);
//        System.out.println(xmlResponseArr[7]);
//        System.out.println(xmlResponseArr[5]);
//        System.out.println(xmlResponseArr[9]);
//
//        Cookie c1 = new Cookie(xmlResponseArr[11]+"_session", xmlResponseArr[13]);
//        Cookie c2 = new Cookie(xmlResponseArr[11]+"UserName", xmlResponseArr[7]);
//        Cookie c3 = new Cookie(xmlResponseArr[11]+"UserID", xmlResponseArr[5]);
//        Cookie c4 = new Cookie(xmlResponseArr[11]+"Token", xmlResponseArr[9]);
////
//
//        d.manage().addCookie(c1);
//        d.manage().addCookie(c2);
//        d.manage().addCookie(c3);
//        d.manage().addCookie(c4);
//

	//head
		String httpEquiv = "content type";
		String content = "text/html";
		String charset = "UTF-8";
	//styles
		String tableStyle = "table {margin:0 auto;}";
		String tdStyle = "td {border-top: 1px solid grey;}";
		String tdFirstChildStyle = "td:first-child {width:200px;}";
		String tdSecondChilgStyle = "td:nth-child(2) {width:660px;}";
		String tdThirdChildStyle = "td:nth-child(3) {width:100px;}";
		String trSucces= "tr.success {color:black;background-color:#CCFFCC;}";
		String trError = "tr.error {color:black;background-color:#FFCCCC;}";
		String trStep = "tr.step {color:white;background:grey}";
		String nl = "\n";
	//scripts
		//jquery
		String scriptType = "text/javascript";
		String jQyeryScriptSource = "http://code.jquery.com/jquery-1.8.2.min.js";
		//custom wikia qa scripts
		String wikiaScriptSource = "./src/test/resources/script.js";

	//test information paragraphs
		String pDate = "asd";
		String pBrowser = "sdf";
		String pOS = "dfg";
		String pScreenRes = "fgh";
		String pTestEnv = "ghj";
		String pTestEnv2 = "hjk";
		String pTestVersion = "jkl";
	//buttons
		//hide low level action button
		String hllButtonID = "hideLowLevel";
		String hllButtonText = "hide low level actions";
		//show low level action button
		String sllButtonID = "showLowLevel";
		String sllButtonText = "show low level actions";
	//onStart table
		String testClassName = "asd";
		String testName = "sdf";



		//test information paragraphs

//			"<p>Date: " + dateFormat.format(date) + " UTC</p>" +
//			"<p>Browser: " + Global.BROWSER + "</p>" +
//			"<p>OS: " + System.getProperty("os.name") + "</p>" +
//			"<p>Screen resolution: " + dim.width + "x"+dim.height+"</p>" +
//			"<p>Testing environment: "+ Global.DOMAIN+"</p>" +
//			"<p>Testing environment: "+ Global.LIVE_DOMAIN+"</p>" +
//			"<p>Tested version: "+ Global.WIKI_VERSION+"</p>" +


// onStart  | done
		StringWriter writer = new StringWriter();
		Html builder = new Html(writer);
		builder.html();
			builder.style().text(nl+tableStyle+nl+tdFirstChildStyle+nl+tdSecondChilgStyle+nl+tdThirdChildStyle+nl+trSucces+nl+trError+nl+trStep).end();
			builder.head();
				builder.meta().httpEquiv(httpEquiv).content(content).charset(charset);
				//TODO: check if you can extract below style to previous styles
				builder.style().text(nl+tdStyle).end();
			builder.end();
			builder.body();
				builder.script().type(scriptType).src(jQyeryScriptSource).end();
				builder.p().text(pDate).end();
				builder.p().text(pBrowser).end();
				builder.p().text(pOS).end();
				builder.p().text(pScreenRes).end();
				builder.p().text(pTestEnv).end();
				builder.p().text(pTestEnv2).end();
				builder.p().text(pTestVersion).end();
				builder.div().id("toc").end();
				builder.button().id(hllButtonID).text(hllButtonText).end();
				builder.button().id(sllButtonID).text(sllButtonText).end();
				builder.script().src(wikiaScriptSource).end();
// onTestStart | done
				builder.h1().text("Class: ");
					builder.em().text(testClassName+"." +testName).end();
				builder.end();
				builder.table(); //open table (will not be closed till onTestSucces)
				builder.tr().classAttr("step");
					builder.td().text("&nbsp").end();
					builder.td().h1().em().text(testName).end().end().end();
					builder.td().br().text("&nbsp").end(); //TODO: sprawdz czy dziala &nbsp zamiast &nbsp; Jesli tak wywal nbsp do zmiennej
				builder.end();
// log without driver (private) | done
				String className = "";
				builder.tr().classAttr(className+"lowLevelAction");
					builder.td().text("description").end();
					builder.td().text("command").end();
					builder.td().br().text("&nbsp").end();
				builder.end();
// log with driver (private) | done
				builder.tr().classAttr(className+"lowLevelAction");
					builder.td().text("description").end();
					builder.td().text("command").end();
					builder.td();
						builder.br().a().href("screenshots/screenshot"+"imageCounter"+".png").text("screenshot").end();
						builder.br().a().href("screenshots/screenshot"+"imageCounter"+".html").text("HTML Source").end();
					builder.end();
				builder.end();
// onTestSuccess | done
				builder.tr().classAttr("step");
					builder.td().text("&nbsp").end();
					builder.td().text("STOP LOGGING METHOD");
						builder.div().style("text-align:center");
							builder.a().style("color:blue");
								builder.b().text("BACK TO MENU").end();
							builder.end();
						builder.end();
					builder.end();
					builder.td().br().text("&nbsp").end();
				builder.end();
				builder.end(); // end </table>
// onTestFailure | done
				builder.tr().classAttr("error");
					builder.td().text("error").end();
					builder.td().text("exception").end();
					builder.td();
						builder.br().a().href("screenshots/screenshot"+"imageCounter"+".png").text("screenshot").end();
						builder.br().a().href("screenshots/screenshot"+"imageCounter"+".html").text("HTML Source").end();
						builder.end();
					builder.end();
				builder.end();
// afterChangeValueOf | done
				builder.tr().classAttr("class name + loweLevel");
					builder.td().text("ChangeValueOfField").end();
					builder.td().text("lastfindby").end();
					builder.td().br().text("&nbsp").end();
				builder.end();
// afterClickOn | done
				builder.tr().classAttr("class name + loweLevel");
					builder.td().text("click").end();
					builder.td().text("lastfindby").end();
					builder.td().br().text("&nbsp").end();
				builder.end();
// afterNavigateTo | done
				builder.tr().classAttr("class name + loweLevel");
					builder.td().text("Navigate to").end();
					builder.td().text("url").end();
					builder.td().br().text("&nbsp").end();
				builder.end();
// logJSError| done
				builder.tr().classAttr("error");
					builder.td().text("click").end();
					builder.td().text("VarError").end();
					builder.td().br().text("&nbsp").end();
				builder.end();
//onFinish | done
			builder.end();  // end </body>
		builder.end(); // end </html>

		System.out.println(writer.getBuffer().toString());
//		api.php ? action=login & lgname=Bob & lgpassword=secret
		Assertion.assertStringContains("asddddfg", "dddda");
		Assertion.assertStringContains("asddddfg", "dddd");
	}

}
