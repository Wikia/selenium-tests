package com.wikia.webdriver.common.remote.operations;

import com.jayway.jsonpath.*;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.remote.Discussions;
import com.wikia.webdriver.common.remote.RemoteException;
import com.wikia.webdriver.common.remote.context.CreateCategoryContext;
import com.wikia.webdriver.common.remote.operations.http.GetRemoteOperation;
import com.wikia.webdriver.elements.mercury.components.discussions.common.category.CategoryPill;

import java.util.ArrayList;

public class Categories {

  public static final String GET_CATEGORIES_URL_SUFFIX = "%s/forums";
  private final GetRemoteOperation remoteOperation;

  Categories(User user) {
    remoteOperation = new GetRemoteOperation(user);
  }

  public ArrayList<CategoryPill.Data> execute(final CreateCategoryContext context) {
    String response = null;
    try {
      response = remoteOperation.execute(buildUrl(context));
    } catch(RemoteException e) {
      PageObjectLogging.logError("error: ", e);
    }
    return getCategories(response, context);
  }

  private ArrayList<CategoryPill.Data> getCategories(String response, CreateCategoryContext ctxt) {
    Object json = Configuration.defaultConfiguration().jsonProvider().parse(response);
    int len = JsonPath.read(json, "$._embedded.doc:forum.length()");
    ArrayList<CategoryPill.Data> categories = new ArrayList<>();
    for (int i = 0; i < len; i++) {
      String id = JsonPath.read(json, "$._embedded.doc:forum[" + i + "].id");
      String name = JsonPath.read(json, "$._embedded.doc:forum[" + i + "].name");
      if (!id.equals(ctxt.getSiteId())) {
        categories.add(new CategoryPill.Data(id, name));
      }
    }
    return categories;
  }

  private String buildUrl(final CreateCategoryContext context) {
    return Discussions.service(String.format(GET_CATEGORIES_URL_SUFFIX, context.getSiteId()));
  }
}
