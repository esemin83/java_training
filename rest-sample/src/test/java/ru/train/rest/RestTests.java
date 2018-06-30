package ru.train.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;

public class RestTests {

  @Test
  public void testCreateIssue() throws IOException {
    Set<Issue> oldIssues = getIssues();
    Issue newIssue = new Issue().withSubject("my test issue 1").withDescription("my test description 1");
    int issueId = createIssue(newIssue);
    Set<Issue> newIssues = getIssues();
    oldIssues.add(newIssue.withId(issueId));
    System.out.println("oldIssues = " + oldIssues);
    System.out.println("newIssues = " + newIssues);
    assertEquals(newIssues, oldIssues);
  }

  @Test
  public void testGetAllIssues() throws IOException {
    Set<Issue> set = getIssues();
    System.out.println("Issues = " + set);
  }

  @Test
  public void testGetSomeIssueState() throws IOException {
    int issueId = 7;
    Set<Issue> set = getIssues();
    System.out.println("Issues = " + set);
    Set<Issue> collect = set.stream().filter((Issue i) -> i.getId() == issueId).collect(Collectors.toSet());
    System.out.println("collected = " + collect);
    System.out.println("state = " + collect.iterator().next().getState());
  }

  @Test
  public void testChangeIssueState() throws IOException {
    int newState = 1;
    int oldState = 0; // if exist
    Set<Issue> set = getIssues();
    Set<Issue> toChangeState = set.stream().filter((Issue i) -> Integer.valueOf(i.getState()) == oldState).collect(Collectors.toSet());
    System.out.println("toChangeState = " + toChangeState);
    changeIssueState(toChangeState.iterator().next().getId(), newState);
    Set<Issue> newSet = getIssues();
    Set<Issue> collect = newSet.stream().filter((Issue i) -> i.getId() == toChangeState.iterator().next().getId()).collect(Collectors.toSet());
    assertEquals(collect.iterator().next().getState(), String.valueOf(newState));
    System.out.println("newSet = " + newSet);
  }

  private Set<Issue> getIssues() throws IOException {
    String value = getExecutor().execute(Request.Get("http://bugify.stqa.ru/api/issues.json")).returnContent().asString();
    JsonElement parsed = new JsonParser().parse(value);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {}.getType());
  }

  private Executor getExecutor() {
    return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
  }

  private int createIssue(Issue newIssue) throws IOException {
    String value = getExecutor().execute(Request.Post("http://bugify.stqa.ru/api/issues.json")
            .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                      new BasicNameValuePair("description", newIssue.getDescription()))).returnContent().asString();
    JsonElement parsed = new JsonParser().parse(value);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();
  }

  private void changeIssueState(int issueId, int state) throws IOException {
    getExecutor().execute(Request.Post(String.format("http://bugify.stqa.ru/api/issues/%s.json", issueId))
            .bodyForm(new BasicNameValuePair("method", "update"),
                    new BasicNameValuePair("issue[state]", String.format("%s", state)))).returnContent();
  }
}
