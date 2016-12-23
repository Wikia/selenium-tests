package com.wikia.webdriver.common.remote.operations;

import com.wikia.webdriver.common.core.helpers.User;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.json.JSONObject;


class DeleteRemoteOperation extends BaseRemoteOperation implements RemoteOperation {

  DeleteRemoteOperation(User user) {
    super(user);
  }

  @Override
  public String execute(final String url) {
    return super.execute(new HttpDelete(url));
  }

  public String execute(final String url, final JSONObject jsonObject) {
    return super.execute(new HttpDeleteWithEntity(url), jsonObject);
  }

  public static class HttpDeleteWithEntity extends HttpPost {

    public HttpDeleteWithEntity(String url) {
      super(url);
    }

    @Override
    public String getMethod() {
      return HttpDelete.METHOD_NAME;
    }
  }

}
