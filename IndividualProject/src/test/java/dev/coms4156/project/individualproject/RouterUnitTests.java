package dev.coms4156.project.individualproject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

/**
 * This class test constructing Route Controller object by providing necessary parameters,
 * After creating the object, it will be compared with the manually written r
 * RouteController object. If information match, there will be no error.
 */
@SpringBootTest
public class RouterUnitTests {
  public RouteController testrouteController;

  /** The test router instance used for testing. */
  @BeforeEach
  public void setupRouteControllerForTesting() {
    IndividualProjectApplication app = new IndividualProjectApplication();
    app.run(new String[]{"setup"});
    testrouteController = new RouteController();
  }

  @Test
  public void indexTest() {
    String expected = 
        "Welcome, in order to make an API call direct your browser or Postman to an endpoint "
        + "\n\n This can be done using the following format: \n\n http:127.0.0"
        + ".1:8080/endpoint?arg=value";
    Assertions.assertEquals(expected, testrouteController.index());
  }

  @Test
  public void retrieveDepartmentTestNotFound() {
    Assertions.assertEquals(
        HttpStatus.NOT_FOUND,
        testrouteController.retrieveDepartment("ABC").getStatusCode());
  }

  @Test
  public void retrieveDepartmentTestSuccess() {
    String[] depcode = { "COMS", "ECON", "IEOR", "CHEM", "PHYS", "ELEN", "PSYC" };
    for (int i = 0; i < depcode.length; i++) {
      Assertions.assertEquals(
          HttpStatus.OK,
          testrouteController.retrieveDepartment(depcode[i]).getStatusCode());
    }
  }

  @Test
  public void retrieveCourseTestSuccess() {
    Assertions.assertEquals(
         HttpStatus.OK,
         testrouteController.retrieveCourse("COMS", 4156).getStatusCode());
    Assertions.assertEquals(
         HttpStatus.OK,
         testrouteController.retrieveCourse("ECON", 4840).getStatusCode());
    Assertions.assertEquals(
         HttpStatus.NOT_FOUND,
         testrouteController.retrieveCourse("ABC", 4156).getStatusCode());
  }

  @Test
  public void retrieveCourseTestNotFound() {
    Assertions.assertEquals(
        HttpStatus.NOT_FOUND,
        testrouteController.retrieveCourse("COMS", 9999).getStatusCode());
  }

  @Test
  public void isCourseFullTest() {
    Assertions.assertEquals(
        HttpStatus.OK,
        testrouteController.isCourseFull("COMS", 4156).getStatusCode());
    Assertions.assertEquals(
        HttpStatus.OK,
        testrouteController.isCourseFull("ECON", 4840).getStatusCode());
    Assertions.assertEquals(
        HttpStatus.NOT_FOUND,
        testrouteController.isCourseFull("ABC", 4156).getStatusCode());
  }

  @Test
  public void changeCourseTeacherTest() {
    Assertions.assertEquals(HttpStatus.OK, testrouteController
        .changeCourseTeacher("COMS", 1004, "Aditi Chowdhuri").getStatusCode());
    Assertions.assertEquals("Attributed was updated successfully.",
        testrouteController.changeCourseTeacher("COMS", 1004, "Aditi Chowdhuri").getBody());

    Assertions.assertTrue(testrouteController.retrieveCourse("COMS", 1004).getBody().toString()
        .contains("Aditi Chowdhuri"));
  }
        
  @Test
  public void changeCourseLocationTest() {
    Assertions.assertEquals(HttpStatus.OK, testrouteController
        .changeCourseLocation("COMS", 1004, "401 Mudd").getStatusCode());
    Assertions.assertEquals("Attributed was updated successfully.", 
        testrouteController.changeCourseLocation("COMS", 1004, "401 Mudd").getBody());

    Assertions.assertTrue(testrouteController.findCourseLocation("COMS", 1004)
        .getBody().toString().contains("401 Mudd"));
  }
        
  @Test
  public void changeCourseTimeTest() {
    Assertions.assertEquals(HttpStatus.OK, testrouteController
        .changeCourseTime("COMS", 1004, "10:10-11:30").getStatusCode());
    Assertions.assertEquals("Attributed was updated successfully.", 
        testrouteController.changeCourseTime("COMS", 1004, "10:10-11:30").getBody());

    Assertions.assertTrue(testrouteController.findCourseTime("COMS", 1004)
        .getBody().toString().contains("10:10-11:30"));
  }

  @Test
  public void getMajorCtFromDeptTest() {
    Assertions.assertEquals(
        HttpStatus.OK,
        testrouteController.getMajorCtFromDept("COMS").getStatusCode());
    Assertions.assertEquals(
        "There are: 2700 majors in the department",
        testrouteController.getMajorCtFromDept("COMS").getBody());
    Assertions.assertEquals(
        HttpStatus.OK,
        testrouteController.getMajorCtFromDept("ECON").getStatusCode());
    Assertions.assertEquals(
        HttpStatus.NOT_FOUND,
        testrouteController.getMajorCtFromDept("ABC").getStatusCode());
  }

  @Test
  public void identifyDeptChairTest() {
    Assertions.assertEquals(
        HttpStatus.OK,
        testrouteController.identifyDeptChair("COMS").getStatusCode());
    Assertions.assertEquals(
        "Luca Carloni is the department chair.",
        testrouteController.identifyDeptChair("COMS").getBody());
    Assertions.assertEquals(
        HttpStatus.OK,
        testrouteController.identifyDeptChair("ECON").getStatusCode());
    Assertions.assertEquals(
        HttpStatus.NOT_FOUND,
        testrouteController.identifyDeptChair("ABC").getStatusCode());
  }

  @Test
  public void findCourseLocationTest() {
    Assertions.assertEquals(
        HttpStatus.OK,
        testrouteController.findCourseLocation("COMS", 4156).getStatusCode());
    Assertions.assertEquals(
        "501 NWC is where the course is located.",
        testrouteController.findCourseLocation("COMS", 4156).getBody());
    Assertions.assertEquals(
        HttpStatus.NOT_FOUND,
        testrouteController.findCourseLocation("ABC", 4156).getStatusCode());
  }

  @Test
  public void findCourseInstructorTest() {
    Assertions.assertEquals(
        HttpStatus.OK,
        testrouteController.findCourseInstructor("COMS", 4156).getStatusCode());
    Assertions.assertEquals(
        "Gail Kaiser is instructor for the course.",
        testrouteController.findCourseInstructor("COMS", 4156).getBody());
    Assertions.assertEquals(
        HttpStatus.NOT_FOUND,
        testrouteController.findCourseInstructor("ABC", 4156).getStatusCode());
  }

  @Test
  public void findCourseTimeTest() {
    Assertions.assertEquals(
        HttpStatus.OK,
        testrouteController.findCourseTime("COMS", 4156).getStatusCode());
    Assertions.assertEquals(
        "The course meets at: 10:10-11:25.",
        testrouteController.findCourseTime("COMS", 4156).getBody());
    Assertions.assertEquals(
        HttpStatus.NOT_FOUND,
        testrouteController.findCourseTime("ABC", 4156).getStatusCode());
  }

  @Test
  public void addMajorToDeptTest() {
    Assertions.assertEquals(
        HttpStatus.OK,
        testrouteController.addMajorToDept("COMS").getStatusCode());
    Assertions.assertEquals(
        HttpStatus.NOT_FOUND,
        testrouteController.addMajorToDept("ABC").getStatusCode());
  }

  @Test
  public void removeMajorFromDeptTest() {
    Assertions.assertEquals(
        HttpStatus.OK,
        testrouteController.removeMajorFromDept("COMS").getStatusCode());
    Assertions.assertEquals(
        HttpStatus.NOT_FOUND,
        testrouteController.removeMajorFromDept("ABC").getStatusCode());
  }

  @Test
  public void dropStudentTest() {
    Assertions.assertEquals(
        HttpStatus.OK,
        testrouteController.dropStudent("COMS", 4156).getStatusCode());
    Assertions.assertEquals(
        "Student has been dropped.",
        testrouteController.dropStudent("COMS", 4156).getBody());
    Assertions.assertEquals(
        HttpStatus.NOT_FOUND,
        testrouteController.dropStudent("ABC", 4156).getStatusCode());
  }

  @Test
  public void setEnrollmentCountTest() {
    Assertions.assertEquals(
        HttpStatus.OK,
        testrouteController.setEnrollmentCount("COMS", 4156, 10).getStatusCode());
    Assertions.assertEquals(
        "Attributed was updated successfully.",
        testrouteController.setEnrollmentCount("COMS", 4156, 50).getBody());
    Assertions.assertEquals(
        HttpStatus.NOT_FOUND,
        testrouteController.setEnrollmentCount("ABC", 4156, 100).getStatusCode());
  }

}