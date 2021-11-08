package lesson05;

import org.testng.ITestContext ;
import org.testng.ITestListener ;
import org.testng.ITestResult ;


public class AutomationListeners implements ITestListener
{
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
    }

    public void onTestFailure(ITestResult test) {
        System.out.println("---------------------- Test "  + test.getName() + " Failed ------------------");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
        // TODO Auto-generated method stub
    }

    public void onTestSkipped(ITestResult test) {
        // TODO Auto-generated method stub
    }
}
