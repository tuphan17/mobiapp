package edu.tacoma.uw.projecttcss450;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class QuaterTest {

    @Test
    public void testQuaterConstructor() {
        assertNotNull(new Quater("summer2014", "TCSS101", "TCSS141", "TCSS142"));
    }

    @Test
    public void testsetYear() {
        Quater test = new Quater("summer2014", "TCSS101", "TCSS141", "TCSS142");
        test.setYear("Fall2014");
        assertTrue(test.getYear() == "Fall2014");
    }

    @Test
    public void testsetCourse1() {
        Quater test = new Quater("summer2014", "TCSS101", "TCSS141", "TCSS142");
        test.setCourse1("TCSS342");
        assertTrue(test.getCourse1() == "TCSS342");
    }

    @Test
    public void testsetCourse2() {
        Quater test = new Quater("summer2014", "TCSS101", "TCSS141", "TCSS142");
        test.setCourse2("TCSS342");
        assertTrue(test.getCourse2() == "TCSS342");
    }

    @Test
    public void testsetCourse3() {
        Quater test = new Quater("summer2014", "TCSS101", "TCSS141", "TCSS142");
        test.setCourse3("TCSS342");
        assertTrue(test.getCourse3() == "TCSS342");
    }
}
