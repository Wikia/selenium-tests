package com.wikia.webdriver.common.remote.discussions;

import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.common.remote.RemoteException;
import com.wikia.webdriver.common.remote.discussions.context.CreateCategoryContext;
import com.wikia.webdriver.common.remote.operations.http.GetRemoteOperation;
import com.wikia.webdriver.elements.communities.mobile.components.discussions.common.category.CategoryPill;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

import java.util.ArrayList;
import java.util.List;

public class Categories {

  private static final String GET_CATEGORIES_URL_SUFFIX = "%s/forums";
  private final GetRemoteOperation remoteOperation;

  Categories(User user) {
    remoteOperation = new GetRemoteOperation(user);
  }

  public List<CategoryPill.Data> execute(final CreateCategoryContext context) {
    try {
      return getCategories(remoteOperation.execute(buildUrl(context)), context);
    } catch (RemoteException e) {
      Log.logError("error: ", e);
      throw new CategoriesNotFetched("Could not fetch categories.", e);
    }
  }

  private List<CategoryPill.Data> getCategories(String response, CreateCategoryContext ctxt) {
    Object json = Configuration.defaultConfiguration().jsonProvider().parse(response);
    int len = JsonPath.read(json, "$._embedded.doc:forum.length()");
    List<CategoryPill.Data> categories = new ArrayList<>();
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
    return DiscussionsClient.service(String.format(GET_CATEGORIES_URL_SUFFIX, context.getSiteId()));
  }

  private class CategoriesNotFetched extends RuntimeException {

    private CategoriesNotFetched(String message, RemoteException cause) {
      super(message, cause);
    }
  }
}
