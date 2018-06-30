package ru.train.rest;

import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;

public class RestTests {

  Helper hp = new Helper();

  @Test
  public void testCreateIssue() throws IOException {
    Set<Issue> oldIssues = hp.getIssues();
    Issue newIssue = new Issue().withSubject("my test issue 1").withDescription("my test description 1");
    int issueId = hp.createIssue(newIssue);
    Set<Issue> newIssues = hp.getIssues();
    oldIssues.add(newIssue.withId(issueId));
    System.out.println("oldIssues = " + oldIssues);
    System.out.println("newIssues = " + newIssues);
    assertEquals(newIssues, oldIssues);
  }

  @Test
  public void testGetAllIssues() throws IOException {
    Set<Issue> set = hp.getIssues();
    System.out.println("Issues = " + set);
  }

  @Test
  public void testGetSomeIssueState() throws IOException {
    int issueId = 7;
    Set<Issue> set = hp.getIssues();
    System.out.println("Issues = " + set);
    Set<Issue> collect = set.stream().filter((Issue i) -> i.getId() == issueId).collect(Collectors.toSet());
    System.out.println("collected = " + collect);
    System.out.println("state = " + collect.iterator().next().getState());
  }

  @Test
  public void testChangeIssueState() throws IOException {
    int newState = 1;
    int oldState = 0; // if exist
    Set<Issue> set = hp.getIssues();
    Set<Issue> toChangeState = set.stream().filter((Issue i) -> Integer.valueOf(i.getState()) == oldState).collect(Collectors.toSet());
    System.out.println("toChangeState = " + toChangeState);
    hp.changeIssueState(toChangeState.iterator().next().getId(), newState);
    Set<Issue> newSet = hp.getIssues();
    Set<Issue> collect = newSet.stream().filter((Issue i) -> i.getId() == toChangeState.iterator().next().getId()).collect(Collectors.toSet());
    assertEquals(collect.iterator().next().getState(), String.valueOf(newState));
    System.out.println("newSet = " + newSet);
  }
}
