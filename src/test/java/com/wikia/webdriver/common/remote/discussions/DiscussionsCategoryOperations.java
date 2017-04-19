package com.wikia.webdriver.common.remote.discussions;

import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.remote.discussions.context.CategoryContext;
import com.wikia.webdriver.common.remote.discussions.context.CreateCategoryContext;
import com.wikia.webdriver.elements.mercury.components.discussions.common.category.CategoryPill;
import lombok.AllArgsConstructor;
import java.util.List;

@AllArgsConstructor(staticName = "using")
public class DiscussionsCategoryOperations {

  private final User user;

  public CategoryPill.Data createCategory(String categoryName, String siteId) {
    return createCategory(CreateCategoryContext.defaultContextUsing(siteId, categoryName));
  }

  public CategoryPill.Data createCategory(CreateCategoryContext context) {
    return new CreateCategory(user).execute(context);
  }

  public void deleteCategory(final String siteId, CategoryPill.Data data) {
    deleteCategory(CategoryContext.defaultContextUsing(siteId, data));
  }

  public void deleteCategory(CategoryContext context) {
    new DeleteCategory(user).execute(context);
  }

  public List<CategoryPill.Data> getCategoriesFromSite(final String siteId, User user) {
    return new Categories(user).execute(CreateCategoryContext.defaultContextUsing(siteId, ""));
  }

  public void deleteAllCategories(final String siteId, User user) {
    List<CategoryPill.Data> categories = getCategoriesFromSite(siteId, user);
    for (CategoryPill.Data category : categories) {
      PageObjectLogging.logInfo("Deleting category: " + category.getName());
      deleteCategory(siteId, category);
    }
  }

}
