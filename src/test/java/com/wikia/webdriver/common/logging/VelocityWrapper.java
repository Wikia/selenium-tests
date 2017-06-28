package com.wikia.webdriver.common.logging;

import static org.testng.internal.Utils.escapeHtml;

import java.io.StringWriter;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public class VelocityWrapper {

  private static final String LAST_LOG_ROW_TEMPLATE_PATH = "lastLogRow.vm";
  private static final String LOG_ROW_TEMPLATE_PATH = "logRow.vm";
  private static final String LOG_ROW_WITH_LINK_TEMPLATE_PATH = "logRowWithLink.vm";
  private static final String IMAGE_TEMPLATE_PATH = "image.vm";
  private static final String ERROR_LOG_ROW_TEMPLATE_PATH = "errorLogRow.vm";
  private static final String LOG_ROW_WITH_SCREENSHOT_TEMPLATE_PATH = "logRowWithScreenshot.vm";
  private static final String LINK_TEMPLATE_PATH = "link.vm";
  private static final String FIRST_LOG_ROW_TEMPLATE_PATH = "firstLogRow.vm";
  private static final String BUTTON_TEMPLATE_PATH = "button.vm";
  private static final String HEADER_TEMPLATE_PATH = "header.vm";
  private static final String ERROR_LOG_ROW_WO_SCREENSHOT_AND_SOURCE_TEMPLATE_PATH =
      "errorLogRowWoScreenshotAndSource.vm";
  private static VelocityEngine velocityEngine = getVelocityEngine();

  private VelocityWrapper() {
    throw new IllegalAccessError("Utility class");
  }

  static String fillLogRow(List<String> classList, String command, String description) {
    StringBuilder builder = new StringBuilder();

    Template t = velocityEngine.getTemplate(LOG_ROW_TEMPLATE_PATH);
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
    StringBuilder builder = new StringBuilder();

    Template t = velocityEngine.getTemplate(LAST_LOG_ROW_TEMPLATE_PATH);
    VelocityContext context = new VelocityContext();

    StringWriter writer = new StringWriter();
    t.merge(context, writer);
    builder.append(writer.toString());
    return builder.toString();
  }

  public static String fillLogRowWithLink(String link, String label) {
    StringBuilder builder = new StringBuilder();

    Template t = velocityEngine.getTemplate(LOG_ROW_WITH_LINK_TEMPLATE_PATH);
    VelocityContext context = new VelocityContext();
    context.put("link", link);
    context.put("label", label);
    StringWriter writer = new StringWriter();
    t.merge(context, writer);
    builder.append(writer.toString());
    return builder.toString();
  }

  static String fillImage(String imageAsBase64) {
    StringBuilder builder = new StringBuilder();

    Template t = velocityEngine.getTemplate(IMAGE_TEMPLATE_PATH);
    VelocityContext context = new VelocityContext();

    context.put("imageAsBase64", escapeHtml(imageAsBase64));
    StringWriter writer = new StringWriter();
    t.merge(context, writer);
    builder.append(writer.toString());
    return builder.toString();
  }

  static String fillErrorLogRow(List<String> classList, String command, long imageCounter) {
    StringBuilder builder = new StringBuilder();

    Template t = velocityEngine.getTemplate(ERROR_LOG_ROW_TEMPLATE_PATH);
    VelocityContext context = new VelocityContext();
    context.put("className", "\"" + classList.stream().collect(Collectors.joining(" ")) + "\"");
    context.put("command", command);
    context.put("imageCounter", String.valueOf(imageCounter));
    StringWriter writer = new StringWriter();
    t.merge(context, writer);
    builder.append(writer.toString());
    return builder.toString();
  }

  static String fillErrorLogRowWoScreenshotAndSource(List<String> classList, String command) {
    StringBuilder builder = new StringBuilder();

    Template t = velocityEngine.getTemplate(ERROR_LOG_ROW_WO_SCREENSHOT_AND_SOURCE_TEMPLATE_PATH);
    VelocityContext context = new VelocityContext();
    context.put("className", "\"" + classList.stream().collect(Collectors.joining(" ")) + "\"");
    context.put("command", command);
    StringWriter writer = new StringWriter();
    t.merge(context, writer);
    builder.append(writer.toString());
    return builder.toString();
  }

  static String fillLogRowWithScreenshot(List<String> classList, String command, String description,
      long imageCounter) {
    StringBuilder builder = new StringBuilder();

    Template t = velocityEngine.getTemplate(LOG_ROW_WITH_SCREENSHOT_TEMPLATE_PATH);
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
    StringBuilder builder = new StringBuilder();

    Template t = velocityEngine.getTemplate(LINK_TEMPLATE_PATH);
    VelocityContext context = new VelocityContext();

    context.put("link", link);
    context.put("label", label);
    StringWriter writer = new StringWriter();
    t.merge(context, writer);
    builder.append(writer.toString());
    return builder.toString();
  }

  static String fillFirstLogRow(String className, String testName, String command,
      String description) {
    StringBuilder builder = new StringBuilder();

    Template t = velocityEngine.getTemplate(FIRST_LOG_ROW_TEMPLATE_PATH);
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
    StringBuilder builder = new StringBuilder();

    Template t = velocityEngine.getTemplate(BUTTON_TEMPLATE_PATH);
    VelocityContext context = new VelocityContext();

    context.put("id", id);
    context.put("label", label);
    StringWriter writer = new StringWriter();
    t.merge(context, writer);
    builder.append(writer.toString());
    return builder.toString();
  }

  static String fillHeader(String date, String polishDate, String browser, String os,
      String testingEnvironmentUrl, String testingEnvironment, String testedVersion) {
    StringBuilder builder = new StringBuilder();

    Template t = velocityEngine.getTemplate(HEADER_TEMPLATE_PATH);
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

  private static VelocityEngine getVelocityEngine() {

    VelocityEngine ve = new VelocityEngine();
    Properties props = new Properties();
    String path = ClassLoader.getSystemResource("velocitytemplates").getPath();
    props.put("file.resource.loader.path", path);
    ve.init(props);
    return ve;
  }
}
