package dev.coms4156.project.individualproject;

import org.junit.jupiter.api.Assertions;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * This class test constructing department object by providing necessary
 * parameters,
 * After creating the object, it will be compared with the manually written
 * department
 * object. If information match, there will be no error.
 */

@SpringBootTest
@ContextConfiguration
public class DepartmentUnitTests {

  /** The test department instance used for testing. */

  public Department testDepartment;
  private Course coms4156;

  @BeforeEach
  public void setupDepartmentForTesting() {
    coms4156 = new Course("Gail Kaiser",
        "417 IAB", "11:40-12:55", 120);
    coms4156.setEnrolledStudentCount(109);
    HashMap<String, Course> courses = new HashMap<>();
    courses.put("4156", coms4156);
    testDepartment = new Department("COMS", courses, "Shih-Fu Chang", 500);
  }

  @Test
  public void getNumberOfMajorsTest() {
    Assertions.assertEquals(500, testDepartment.getNumberOfMajors());
  }

  @Test
  public void getDepartmentChairTest() {
    Assertions.assertEquals("Shih-Fu Chang", testDepartment.getDepartmentChair());
  }

  @Test
  public void getCourseSelectionTest() {
    Assertions.assertEquals("Shih-Fu Chang", testDepartment.getDepartmentChair());
  }

  @Test
  public void addPersonToMajorTest() {
    testDepartment.addPersonToMajor();
    Assertions.assertEquals(501, testDepartment.getNumberOfMajors());
    testDepartment.dropPersonFromMajor();
  }

  @Test
  public void dropPersonFromMajorTest() {
    testDepartment.dropPersonFromMajor();
    Assertions.assertEquals(499, testDepartment.getNumberOfMajors());
    testDepartment.addPersonToMajor();
  }

  @Test
  public void addCourseTest() {
    Course newCourse = new Course("Aditi Chowdhuri", "401 Mudd", "10:10-11:30", 311);
    testDepartment.addCourse("4157", newCourse);
    Assertions.assertEquals(newCourse, testDepartment.getCourseSelection().get("4157"));
  }

  @Test
  public void createCourseTest() {
    testDepartment.createCourse("4157", "Aditi Chowdhuri", "401 Mudd", "10:10-11:30", 100);
    Course course = testDepartment.getCourseSelection().get("4157");
    Assertions.assertEquals("Aditi Chowdhuri", course.getInstructorName());
    Assertions.assertEquals("401 Mudd", course.getCourseLocation());
    Assertions.assertEquals("10:10-11:30", course.getCourseTimeSlot());
    Assertions.assertFalse(course.isCourseFull());
  }

  public void toStringTest() {
    String expected = "COMS 4156: \n"
            + "Instructor: Gail Kaiser; Location: 417 IAB; Time: 11:40-12:55\n"
            + "COMS 4157: \n"
            + "Instructor: Aditi Chowdhuri; Location: 401 Mudd; Time: 10:10-11:30\n";
    System.out.println(testDepartment.toString());
    Assertions.assertEquals(expected, testDepartment.toString());
  }

}