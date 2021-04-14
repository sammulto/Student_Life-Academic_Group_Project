package com.groupeleven.studentlife.UnitTests;

import com.groupeleven.studentlife.domainSpecificObjects.ITaskObject;
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
        assertEquals("Should take 13 minutes to read 2000 words", 13, te.getTimeEstimate(new Task("Read COMP 3010 What is a DNS Amplification Attack", ITaskObject.Priority.HIGH, null, null, 0, "Reading", 2000, "words")));
    }

    @Test
    public void readingPages(){
        TimeEstimator te = new TimeEstimator(4, 40);
        assertEquals("Should take 24 minutes to read 12 pages", 24, te.getTimeEstimate(new Task("Read COMP 3430 Multi-level feedback queues", ITaskObject.Priority.HIGH, null, null, 0, "Reading", 12, "pages")));
    }

    @Test
    public void homeworkDays(){
        TimeEstimator te = new TimeEstimator(4, 40);
        assertEquals("Should take 257 minutes to finish this homework", 257, te.getTimeEstimate(new Task("COMP 3430 Lab 3", ITaskObject.Priority.HIGH, null, null, 0, "Homework", 5, "days")));
    }

    @Test
    public void homeworkWeeks(){
        TimeEstimator te = new TimeEstimator(4, 40);
        assertEquals("Should take 1080 minutes to finish this homework", 1080, te.getTimeEstimate(new Task("COMP 3350 Iteration 1", ITaskObject.Priority.HIGH, null, null, 0, "Homework", 3, "weeks")));
    }

    @Test
    public void lectureMinutes(){
        TimeEstimator te = new TimeEstimator(4, 40);
        assertEquals("Should take 30 minutes to watch this lecture", 30, te.getTimeEstimate(new Task("Watch B+Trees lecture", ITaskObject.Priority.HIGH, null, null, 0, "Lecture", 30, "minutes")));
    }

    @Test
    public void lectureHours(){
        TimeEstimator te = new TimeEstimator(4, 40);
        assertEquals("Should take 180 minutes to complete", 180, te.getTimeEstimate(new Task("Watch week 5 lecture COMP 3010", ITaskObject.Priority.HIGH, null, null, 0, "Lecture", 3, "hours")));
    }

    @Test
    public void labs(){
        TimeEstimator te = new TimeEstimator(4, 40);
        assertEquals("Should take 60 minutes to complete", 75, te.getTimeEstimate(new Task("Complete COMP 3430 lab", ITaskObject.Priority.HIGH, null, null, 0, "Lab", 1, "labs")));
    }

    @Test
    public void termPaper(){
        TimeEstimator te = new TimeEstimator(4, 40);
        assertEquals("Should take 4800 minutes to complete", 4800, te.getTimeEstimate(new Task("Reasearch and write COMP 3010 paper", ITaskObject.Priority.HIGH, null, null, 0, "Term Paper", 10, "pages")));
    }

    @Test
    public void studyingWeeks(){
        TimeEstimator te = new TimeEstimator(4, 40);
        assertEquals("Should take 300 minutes", 300, te.getTimeEstimate(new Task("Study for COMP 3430 test", ITaskObject.Priority.HIGH, null, null, 0, "Studying", 5, "Weeks")));
    }

    @Test
    public void studyingModules(){
        TimeEstimator te = new TimeEstimator(4, 40);
        assertEquals("Should take 240 minutes", 240, te.getTimeEstimate(new Task("Study for COMP 4380 test", ITaskObject.Priority.HIGH, null, null, 0, "Studying", 8, "Modules")));
    }

    @Test
    public void studyingFlashcards(){
        TimeEstimator te = new TimeEstimator(4, 40);
        assertEquals("Should take 100 minutes", 100, te.getTimeEstimate(new Task("Study for COMP 4380 test", ITaskObject.Priority.HIGH, null, null, 0, "Studying", 100, "Flashcards")));
    }

    @Test
    public void studyingWords(){
        TimeEstimator te = new TimeEstimator(4, 40);
        assertEquals("Should take 13 minutes to read 2000 words", 13, te.getTimeEstimate(new Task("Read COMP 3010 What is a DNS Amplification Attack", ITaskObject.Priority.HIGH, null, null, 0, "Studying", 2000, "words")));
    }

    @Test
    public void studyingPages(){
        TimeEstimator te = new TimeEstimator(4, 40);
        assertEquals("Should take 24 minutes to read 12 pages", 24, te.getTimeEstimate(new Task("Read COMP 3430 Multi-level feedback queues", ITaskObject.Priority.HIGH, null, null, 0, "Studying", 12, "pages")));
    }

}