package com.wikia.webdriver.common.logging;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.util.List;
import java.util.stream.Collectors;

import static org.testng.internal.Utils.escapeHtml;


public class VelocityWrapper {

  private static final String LAST_LOG_ROW_TEMPLATE_PATH = "./src/test/java/com/wikia/webdriver/common/velocitytemplates/lastLogRow.vm";
  private static final String LOG_ROW_TEMPLATE_PATH = "./src/test/java/com/wikia/webdriver/common/velocitytemplates/logRow.vm";
  private static final String LOG_ROW_WITH_LINK_TEMPLATE_PATH = "./src/test/java/com/wikia/webdriver/common/velocitytemplates/logRowWithLink.vm";
  private static final String IMAGE_TEMPLATE_PATH = "./src/test/java/com/wikia/webdriver/common/velocitytemplates/image.vm";
  private static final String ERROR_LOG_ROW_TEMPLATE_PATH = "./src/test/java/com/wikia/webdriver/common/velocitytemplates/errorLogRow.vm";
  private static final String LOG_ROW_WITH_SCREENSHOT_TEMPLATE_PATH = "./src/test/java/com/wikia/webdriver/common/velocitytemplates/logRowWithScreenshot.vm";
  private static final String LINK_TEMPLATE_PATH = "./src/test/java/com/wikia/webdriver/common/velocitytemplates/link.vm";
  private static final String FIRST_LOG_ROW_TEMPLATE_PATH = "./src/test/java/com/wikia/webdriver/common/velocitytemplates/firstLogRow.vm";
  private static final String BUTTON_TEMPLATE_PATH = "./src/test/java/com/wikia/webdriver/common/velocitytemplates/button.vm";
  private static final String HEADER_TEMPLATE_PATH = "./src/test/java/com/wikia/webdriver/common/velocitytemplates/header.vm";


  private VelocityWrapper() {
    throw new IllegalAccessError("Utility class");
  }

  static String fillLogRow(List<String> classList, String command, String description) {
    VelocityEngine ve = new VelocityEngine();
    StringBuilder builder = new StringBuilder();

    Template t =
        ve.getTemplate(LOG_ROW_TEMPLATE_PATH);
    VelocityContext context = new VelocityContext();

    context.put("className", "\"" + classList.stream().collect(Collectors.joining(" ")) + "\"");
    context.put("command", command);
    context.put("description", description);
    StringWriter writer = new StringWriter();
    t.merge(context, writer);
    builder.append(writer.toString());
    return builder.toString();
  }

  static String fillLastLogRow() {
    VelocityEngine ve = new VelocityEngine();
    StringBuilder builder = new StringBuilder();

    Template t =
        ve.getTemplate(LAST_LOG_ROW_TEMPLATE_PATH);
    VelocityContext context = new VelocityContext();

    StringWriter writer = new StringWriter();
    t.merge(context, writer);
    builder.append(writer.toString());
    return builder.toString();
  }

  public static String fillLogRowWithLink(String link, String label) {
    VelocityEngine ve = new VelocityEngine();
    StringBuilder builder = new StringBuilder();

    Template t =
        ve.getTemplate(LOG_ROW_WITH_LINK_TEMPLATE_PATH);
    VelocityContext context = new VelocityContext();
    context.put("link", link);
    context.put("label", label);
    StringWriter writer = new StringWriter();
    t.merge(context, writer);
    builder.append(writer.toString());
    return builder.toString();
  }

  static String fillImage(String imageAsBase64) {
    VelocityEngine ve = new VelocityEngine();
    StringBuilder builder = new StringBuilder();

    Template t =
        ve.getTemplate(IMAGE_TEMPLATE_PATH);
    VelocityContext context = new VelocityContext();

    context.put("imageAsBase64", escapeHtml(imageAsBase64));
    StringWriter writer = new StringWriter();
    t.merge(context, writer);
    builder.append(writer.toString());
    return builder.toString();
  }

  static String fillErrorLogRow(List<String> classList, String command, long imageCounter) {
    VelocityEngine ve = new VelocityEngine();
    StringBuilder builder = new StringBuilder();

    Template t = ve.getTemplate(ERROR_LOG_ROW_TEMPLATE_PATH);
    VelocityContext context = new VelocityContext();
    context.put("className", "\"" + classList.stream().collect(Collectors.joining(" ")) + "\"");
    context.put("command", command);
    context.put("imageCounter", String.valueOf(imageCounter));
    StringWriter writer = new StringWriter();
    t.merge(context, writer);
    builder.append(writer.toString());
    return builder.toString();
  }

  static String fillLogRowWithScreenshot(List<String> classList, String command, String description, long imageCounter) {
    VelocityEngine ve = new VelocityEngine();
    StringBuilder builder = new StringBuilder();

    Template t = ve.getTemplate(LOG_ROW_WITH_SCREENSHOT_TEMPLATE_PATH);
    VelocityContext context = new VelocityContext();
    context.put("className", "\"" + classList.stream().collect(Collectors.joining(" ")) + "\"");
    context.put("command", command);
    context.put("description", description);
    context.put("imageCounter", String.valueOf(imageCounter));
    StringWriter writer = new StringWriter();
    t.merge(context, writer);
    builder.append(writer.toString());
    return builder.toString();
  }

  static String fillLink(String link, String label) {
    VelocityEngine ve = new VelocityEngine();
    StringBuilder builder = new StringBuilder();

    Template t =
        ve.getTemplate(LINK_TEMPLATE_PATH);
    VelocityContext context = new VelocityContext();

    context.put("link", link);
    context.put("label", label);
    StringWriter writer = new StringWriter();
    t.merge(context, writer);
    builder.append(writer.toString());
    return builder.toString();
  }

  static String fillFirstLogRow(String className, String testName,
                                String command, String description) {
    VelocityEngine ve = new VelocityEngine();
    StringBuilder builder = new StringBuilder();

    Template t =
        ve.getTemplate(FIRST_LOG_ROW_TEMPLATE_PATH);
    VelocityContext context = new VelocityContext();

    context.put("className", className);
    context.put("testName", testName);
    context.put("command", command);
    context.put("description", description);
    StringWriter writer = new StringWriter();
    t.merge(context, writer);
    builder.append(writer.toString());
    return builder.toString();
  }

  static String fillButton(String id, String label) {
    VelocityEngine ve = new VelocityEngine();
    StringBuilder builder = new StringBuilder();

    Template t =
        ve.getTemplate(BUTTON_TEMPLATE_PATH);
    VelocityContext context = new VelocityContext();

    context.put("id", id);
    context.put("label", label);
    StringWriter writer = new StringWriter();
    t.merge(context, writer);
    builder.append(writer.toString());
    return builder.toString();
  }

  static String fillHeader(String date, String polishDate, String browser, String os,
                           String testingEnvironmentUrl, String testingEnvironment,
                           String testedVersion) {
    VelocityEngine ve = new VelocityEngine();
    StringBuilder builder = new StringBuilder();

    Template t =
        ve.getTemplate(HEADER_TEMPLATE_PATH);
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
