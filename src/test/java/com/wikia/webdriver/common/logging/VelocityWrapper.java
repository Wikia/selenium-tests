package com.wikia.webdriver.common.logging;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by szymonczerwinski on 01.03.2017.
 */
public class VelocityWrapper {

  StringBuilder builder;
  VelocityEngine ve;

  public VelocityWrapper(){
    builder = new StringBuilder();
    ve = new VelocityEngine();
    ve.init();
  }
  public static String appendLogRow(List<String> classList, String command, String description) {
    VelocityEngine ve = new VelocityEngine();
    StringBuilder builder = new StringBuilder();

    Template t =
        ve.getTemplate("./src/test/java/com/wikia/webdriver/common/velocitytemplates/logRow.vm");
    VelocityContext context = new VelocityContext();

    context.put("className", "\"" + classList.stream().collect(Collectors.joining(" ")) + "\"");
    context.put("command", command);
    context.put("escapedDescription", description);
    StringWriter writer = new StringWriter();
    t.merge(context, writer);
    builder.append(writer.toString());
    return builder.toString();
//    CommonUtils.appendTextToFile(logPath, builder.toString());
  }

  public static String appendLastLogRow() {
    VelocityEngine ve = new VelocityEngine();
    StringBuilder builder = new StringBuilder();

    Template t =
        ve.getTemplate("./src/test/java/com/wikia/webdriver/common/velocitytemplates/lastLogRow.vm");
    VelocityContext context = new VelocityContext();

    StringWriter writer = new StringWriter();
    t.merge(context, writer);
    builder.append(writer.toString());
    return builder.toString();
//    CommonUtils.appendTextToFile(logPath, builder.toString());
  }

  public static String appendLogRowWithLink(String link, String label) {
    VelocityEngine ve = new VelocityEngine();
    StringBuilder builder = new StringBuilder();

    Template t =
        ve.getTemplate("./src/test/java/com/wikia/webdriver/common/velocitytemplates/logRowWithLink.vm");
    VelocityContext context = new VelocityContext();
    context.put("link", link);
    context.put("label", label);
    StringWriter writer = new StringWriter();
    t.merge(context, writer);
    builder.append(writer.toString());
    return builder.toString();
//    CommonUtils.appendTextToFile(logPath, builder.toString());
  }

  public static String appendImage(String imageAsBase64) {
    VelocityEngine ve = new VelocityEngine();
    StringBuilder builder = new StringBuilder();

    Template t =
        ve.getTemplate("./src/test/java/com/wikia/webdriver/common/velocitytemplates/image.vm");
    VelocityContext context = new VelocityContext();

    context.put("imageAsBase64", imageAsBase64);
    StringWriter writer = new StringWriter();
    t.merge(context, writer);
    builder.append(writer.toString());
    return builder.toString();
  }

  public static String appendErrorLogRow(List<String> classList, String command, String imageCounter) {
    VelocityEngine ve = new VelocityEngine();
    StringBuilder builder = new StringBuilder();

    Template t =
        ve.getTemplate(
            "./src/test/java/com/wikia/webdriver/common/velocitytemplates/errorLogRow.vm");
    VelocityContext context = new VelocityContext();
    context.put("className", "\"" + classList.stream().collect(Collectors.joining(" ")) + "\"");
    context.put("command", command);
    context.put("imageCounter", imageCounter);
    StringWriter writer = new StringWriter();
    t.merge(context, writer);
    builder.append(writer.toString());
    return builder.toString();
//    CommonUtils.appendTextToFile(logPath, builder.toString());
  }

  public static String appendLink(String link, String label) {
    VelocityEngine ve = new VelocityEngine();
    StringBuilder builder = new StringBuilder();

    Template t =
        ve.getTemplate("./src/test/java/com/wikia/webdriver/common/velocitytemplates/link.vm");
    VelocityContext context = new VelocityContext();

    context.put("link", link);
    context.put("label", label);
    StringWriter writer = new StringWriter();
    t.merge(context, writer);
    builder.append(writer.toString());
    return builder.toString();
  }

  public static String appendFirstLogRow(List<String> classList, String className, String testName,
                                         String command, String description) {
    VelocityEngine ve = new VelocityEngine();
    StringBuilder builder = new StringBuilder();

    Template t =
        ve.getTemplate("./src/test/java/com/wikia/webdriver/common/velocitytemplates/firstLogRow.vm");
    VelocityContext context = new VelocityContext();

    context.put("className", "\"" + classList.stream().collect(Collectors.joining(" ")) + "\"");
    context.put("testName", testName);
    context.put("command", command);
    context.put("description", description);
    StringWriter writer = new StringWriter();
    t.merge(context, writer);
    builder.append(writer.toString());
    return builder.toString();
  }

  public static String appendButton(String id, String label) {
    VelocityEngine ve = new VelocityEngine();
    StringBuilder builder = new StringBuilder();

    Template t =
        ve.getTemplate("./src/test/java/com/wikia/webdriver/common/velocitytemplates/button.vm");
    VelocityContext context = new VelocityContext();

    context.put("id", id);
    context.put("label", label);
    StringWriter writer = new StringWriter();
    t.merge(context, writer);
    builder.append(writer.toString());
    return builder.toString();
  }

  public static String appendHeader(String date, String polishDate, String browser, String os,
                                    String testingEnvironmentUrl, String testingEnvironment,
                                    String testedVersion) {
    VelocityEngine ve = new VelocityEngine();
    StringBuilder builder = new StringBuilder();

    Template t =
        ve.getTemplate("./src/test/java/com/wikia/webdriver/common/velocitytemplates/header.vm");
    VelocityContext context = new VelocityContext();

    context.put("date", date);
    context.put("polishDate", polishDate);
    context.put("browser", browser);
    context.put("os", os);
    context.put("testingEnvironmentUrl", testingEnvironmentUrl);
    context.put("testingEnvironment", testingEnvironment);
    context.put("testedVersion", testedVersion);
    StringWriter writer = new StringWriter();
    t.merge(context, writer);
    builder.append(writer.toString());
    return builder.toString();
  }
}
