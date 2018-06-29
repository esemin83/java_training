package ru.stqa.train.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.train.mantis.model.Issue;
import ru.stqa.train.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class SoapTests extends TestBase{

  @Test
  public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
    skipIfNotFixed(9);
    Set<Project> projects = app.api().getProjects();
    System.out.println("projects.length = " + projects.size());
    for(Project pr: projects){
      System.out.println("pr.getName() = " + pr.getName());
    }
    System.out.println("\n" + "testGetProjects done" + "\n");
  }

  @Test
  public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
    Set<Project> projects = app.api().getProjects();
    Issue issue = new Issue()
            .withSummary("Summary 1").withDescription("Description 1").withProject(projects.iterator().next());
    Issue createdIssue =  app.api().createIssue(issue);
    assertEquals(issue.getSummary(), createdIssue.getSummary());
    System.out.println("\n" + "testCreateIssue done" + "\n");
  }
}
