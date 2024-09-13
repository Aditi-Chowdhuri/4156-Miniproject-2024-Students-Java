package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * Unit tests for the Course class.
 * <p>
 * This class contains tests to verify the working of the Course class.
 * Currently, it includes test cases for the toString method.
 * </p>
 */
@SpringBootTest
@ContextConfiguration
public class CourseUnitTests {

  

  /** The test course instance used for testing. */
  private Course testCourse;

  @BeforeEach
  public void setupCourseForTesting() {
    testCourse = new Course("Griffin Newbold", "417 IAB", "11:40-12:55", 3);
    testCourse.setEnrolledStudentCount(0); // Start with 0 enrolled students
  }

  @Test
  public void toStringTest() {
    String expectedResult = "\nInstructor: Griffin Newbold; Location: 417 IAB; Time: 11:40-12:55";
    assertEquals(expectedResult, testCourse.toString());
  }

  @Test
  public void enrollStudentSuccessTest() {
    Assertions.assertTrue(testCourse.enrollStudent(), "Student should be successfully enrolled.");
    Assertions.assertTrue(testCourse.enrollStudent(), "Student should be successfully enrolled.");
    Assertions.assertTrue(testCourse.enrollStudent(), "Student should be successfully enrolled.");
  }

  @Test
  public void enrollStudentFailureTest() {
    testCourse.enrollStudent();
    testCourse.enrollStudent();
    testCourse.enrollStudent();
    boolean result = testCourse.enrollStudent();
    Assertions.assertFalse(result, "Student should not be enrolled when course is full.");
  }

  @Test
  public void dropStudentSuccessTest() {
    testCourse.enrollStudent();
    testCourse.enrollStudent();
    boolean result = testCourse.dropStudent();
    Assertions.assertTrue(result, "Student should be successfully dropped.");
    Assertions.assertEquals(1, testCourse.getEnrolledStudentCount(), 
        "There should be 1 student enrolled");
  }

  @Test
  public void dropStudentFailureWhenNoStudentsTest() {
    boolean result = testCourse.dropStudent();
    Assertions.assertFalse(result, "Dropping a student should fail if no students are enrolled.");
  }

  @Test
  public void getCourseLocationTest() {
    String location = testCourse.getCourseLocation();
    Assertions.assertEquals("417 IAB", location, "Course location should be '417 IAB'");
  }

  @Test
  public void getInstructorNameTest() {
    String instructor = testCourse.getInstructorName();
    Assertions.assertEquals("Griffin Newbold", instructor, 
        "Instructor name should be 'Griffin Newbold'");
  }

  @Test
  public void getCourseTimeSlotTest() {
    String timeSlot = testCourse.getCourseTimeSlot();
    Assertions.assertEquals("11:40-12:55", timeSlot, "Course time slot should be '11:40-12:55'");
  }

  @Test
  public void isCourseFullTest() {
    Assertions.assertFalse(testCourse.isCourseFull(), "Course should not be full");

    testCourse.setEnrolledStudentCount(3);
    Assertions.assertTrue(testCourse.isCourseFull(), 
        "Course should be full when 3 students have enrolled.");

    testCourse.setEnrolledStudentCount(2);
    Assertions.assertFalse(testCourse.isCourseFull(), 
        "Course should not be full when count is less than capacity.");
  }

  @Test
  public void reassignInstructorTest() {
    Assertions.assertEquals("Griffin Newbold", testCourse.getInstructorName(), 
        "Initial instructor should be Griffin Newbold.");
    testCourse.reassignInstructor("Aditi Chowdhuri");
    Assertions.assertEquals("Aditi Chowdhuri", testCourse.getInstructorName(), 
        "Instructor should be reassigned to Aditi Chowdhuri.");
  }

  @Test
  public void reassignLocationTest() {
    Assertions.assertEquals("417 IAB", testCourse.getCourseLocation(), 
        "Initial location should be 417 IAB.");
    testCourse.reassignLocation("401 Mudd");
    Assertions.assertEquals("401 Mudd", testCourse.getCourseLocation(), 
        "Course location should be reassigned to 401 Mudd.");
  }

  @Test
  public void reassignTimeTest() {
    Assertions.assertEquals("11:40-12:55", testCourse.getCourseTimeSlot(), 
        "Initial time slot should be 11:40-12:55.");
    testCourse.reassignTime("10:10-11:30");
    Assertions.assertEquals("10:10-11:30", testCourse.getCourseTimeSlot(), 
        "Course time slot should be reassigned to 10:10-11:30");
  }

  @Test
  public void setEnrolledStudentCountTest() {
    testCourse.setEnrolledStudentCount(2);
    Assertions.assertEquals(2, testCourse.getEnrolledStudentCount(), 
        "The enrolled student count should be set to 2.");

    testCourse.setEnrolledStudentCount(3);
    Assertions.assertEquals(3, testCourse.getEnrolledStudentCount(), 
        "The enrolled student count should be set to 3 (capacity).");
  }

  @Test
  public void setEnrolledStudentCountInvalidTest() {
    testCourse.setEnrolledStudentCount(5);
    Assertions.assertEquals(5, testCourse.getEnrolledStudentCount(), 
        "The enrolled student count should be set to 5, although it's greater than the capacity");

    testCourse.setEnrolledStudentCount(-1);
    Assertions.assertEquals(-1, testCourse.getEnrolledStudentCount(), 
        "The enrolled student count should be set to -1, though it doesn't make logical sense");
  }

}