package ru.train.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.Set;

public class Helper {

  public Set<Issue> getIssues() throws IOException {
    String value = getExecutor().execute(Request.Get("http://bugify.stqa.ru/api/issues.json")).returnContent().asString();
    JsonElement parsed = new JsonParser().parse(value);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {}.getType());
  }

  private Executor getExecutor() {
    return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
  }

  public int createIssue(Issue newIssue) throws IOException {
    String value = getExecutor().execute(Request.Post("http://bugify.stqa.ru/api/issues.json")
            .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                    new BasicNameValuePair("description", newIssue.getDescription()))).returnContent().asString();
    JsonElement parsed = new JsonParser().parse(value);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();
  }

  public void changeIssueState(int issueId, int state) throws IOException {
    getExecutor().execute(Request.Post(String.format("http://bugify.stqa.ru/api/issues/%s.json", issueId))
            .bodyForm(new BasicNameValuePair("method", "update"),
                    new BasicNameValuePair("issue[state]", String.format("%s", state)))).returnContent();
  }
}
