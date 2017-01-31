package com.wikia.webdriver.common.remote.operations;

import com.jayway.jsonpath.Criteria;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.Filter;
import com.jayway.jsonpath.JsonPath;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.remote.Discussions;
import com.wikia.webdriver.common.remote.RemoteException;
import com.wikia.webdriver.common.remote.context.CategoryContext;
import com.wikia.webdriver.common.remote.context.CreateCategoryContext;
import com.wikia.webdriver.common.remote.operations.http.PostRemoteOperation;
import com.wikia.webdriver.elements.mercury.components.Category;
import com.wikia.webdriver.elements.mercury.components.discussions.common.category.CategoryPill;
import org.json.JSONObject;

import java.util.List;

public class Categories {

  public static final String GET_CATEGORIES_URL_SUFFIX = "%s/forums";


  private final PostRemoteOperation remoteOperation;

  Categories(User user) {
    remoteOperation = new PostRemoteOperation(user);
  }

  public void execute(final CreateCategoryContext context) {
    JSONObject jsonObject = new JSONObject();

    String response = null;
    try {
      response = remoteOperation.execute(buildUrl(context), jsonObject);
    } catch(RemoteException e) {
      System.out.println(e.toString());
      PageObjectLogging.logError("error: ", e);
    }

    DocumentContext json = JsonPath.parse(response);
    Filter fne = Filter.filter(Criteria.where("id").ne(context.getSiteId()));
    List<String> categories = json.read("$._embedded.doc:forum.[*]");
    for(String s : categories) {
      System.out.println(s);
    }
  }

  private String buildUrl(final CreateCategoryContext context) {
    return Discussions.service(String.format(GET_CATEGORIES_URL_SUFFIX, context.getSiteId()));
  }
}
