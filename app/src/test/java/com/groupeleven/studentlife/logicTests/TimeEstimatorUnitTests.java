package com.groupeleven.studentlife.logicTests;

import com.groupeleven.studentlife.logic.TimeEstimator;
import com.groupeleven.studentlife.domainSpecificObjects.Task;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TimeEstimatorUnitTests {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void readingWords(){
        TimeEstimator te = new TimeEstimator(4, 40);
        assertEquals("Should take 26 minutes to read 2000 words", 26, te.getTimeEstimate(new Task("Read COMP 3010 What is a DNS Amplification Attack", 0, null, null, 0, "Reading", 2000, "words")));
    }

    @Test
    public void readingPages(){
        TimeEstimator te = new TimeEstimator(4, 40);
        assertEquals("Should take 60 minutes to read 12 pages", 60, te.getTimeEstimate(new Task("Read COMP 3430 Multi-level feedback queues", 0, null, null, 0, "Reading", 12, "pages")));
    }

    @Test
    public void homeworkDays(){
        TimeEstimator te = new TimeEstimator(4, 40);
        assertEquals("Should take 257 minutes to finish this homework", 257, te.getTimeEstimate(new Task("COMP 3430 Lab 3", 0, null, null, 0, "Homework", 5, "days")));
    }

    @Test
    public void homeworkWeeks(){
        TimeEstimator te = new TimeEstimator(4, 40);
        assertEquals("Should take 1080 minutes to finish this homework", 1080, te.getTimeEstimate(new Task("COMP 3350 Iteration 1", 0, null, null, 0, "Homework", 3, "weeks")));
    }

    @Test
    public void LectureMinutes(){
        TimeEstimator te = new TimeEstimator(4, 40);
        assertEquals("Should take 30 minutes to watch this lecture", 30, te.getTimeEstimate(new Task("Watch B+Trees lecture", 0, null, null, 0, "Lecture", 30, "minutes")));
    }

    @Test
    public void LectureHours(){
        TimeEstimator te = new TimeEstimator(4, 40);
        assertEquals("Should take 180 minutes to complete", 180, te.getTimeEstimate(new Task("Watch week 5 lecture COMP 3010", 0, null, null, 0, "Lecture", 3, "hours")));
    }
}