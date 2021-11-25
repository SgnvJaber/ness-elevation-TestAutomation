package lesson09.DataTables;

import lesson07.Recorder.MonteScreenRecorder;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.lang.reflect.Method;


public class AutomationListenersWithRecorder implements ITestListener {
    public void onStart(ITestContext execution) {
        System.out.println("---------------------- Starting Execution ------------------");
    }

    public void onFinish(ITestContext execution) {
        System.out.println("---------------------- Ending Execution ------------------");
    }

    public void onTestStart(ITestResult test) {
        System.out.println("---------------------- Test: " + test.getName() + " Started ------------------");
    }

    public void onTestSuccess(ITestResult test) {
        System.out.println("---------------------- Test: " + test.getName() + " Passed ------------------");
        try {
            MonteScreenRecorder.stopRecord();
        } catch (Exception e) {
            e.printStackTrace();
        }

        File file = new File("./test-recordings/" + test.getName() + ".avi");
        if (file.delete()) {
            System.out.println("Recorded Screen Cast File Deleted Successfully");
        } else {
            System.out.println("Failed to Delete the Recorded Screen Cast File");
        }
    }

    public void onTestFailure(ITestResult test) {
        System.out.println("---------------------- Test " + test.getName() + " Failed ------------------");
        try {
            MonteScreenRecorder.stopRecord();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
        // TODO Auto-generated method stub
    }

    public void onTestSkipped(ITestResult test) {
        // TODO Auto-generated method stub
    }
}
