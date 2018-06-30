package ru.stqa.train.addressbook.appmanager;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import ru.stqa.train.addressbook.model.Issue;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

public class BugifyRestHelper {

  private final ApplicationManager app;

  public BugifyRestHelper(ApplicationManager app) {
    this.app = app;
  }

  private Executor getExecutor() {
    return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
  }

  public Set<Issue> getIssues() throws IOException {
    String value = getExecutor().execute(Request.Get("http://bugify.stqa.ru/api/issues.json")).returnContent().asString();
    JsonElement parsed = new JsonParser().parse(value);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {}.getType());
  }

  public boolean isIssueOpen(int issueId) throws IOException {
    Set<Issue> set = getIssues();
    Set<Issue> collect = set.stream().filter((Issue i) -> i.getId() == issueId).collect(Collectors.toSet());
    if(collect.size() == 0){
      return true;
    }
    return false;
  }
}
