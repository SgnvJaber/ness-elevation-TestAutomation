package Calculator;


import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class CalculatorMain {
    private WindowsDriver driver;
    private DesiredCapabilities capabilities;
    String calcApp = "Microsoft.WindowsCalculator_8wekyb3d8bbwe!App";
    private CalculatorPage operations;

    @BeforeClass
    public void setup() throws IOException {
        capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", calcApp);
        driver = new WindowsDriver(new URL("http://127.0.0.1:4723"), capabilities);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        operations = PageFactory.initElements(driver, CalculatorPage.class);
    }

    @Test
    public void test01_verifyAdd() {
        operations.add(operations.one, operations.two);
        System.out.println("Add result:" + operations.getResult());
        Assert.assertEquals(operations.getResult(), "3");
    }

    @Test
    public void test02_verifySubtract() {
        operations.subtract(operations.one, operations.two);
        System.out.println("Subtract result:" + operations.getResult());
        Assert.assertEquals(operations.getResult(), "-1");

    }


    @Test
    public void test01_verifyDivide() {
        operations.divide(operations.one, operations.two);
        System.out.println("Divide result:" + operations.getResult());
        Assert.assertEquals(operations.getResult(), "0.5");

    }


    @Test
    public void test01_verifyMultiply() {
        operations.multiply(operations.one, operations.two);
        System.out.println("Multiply result:" + operations.getResult());
        Assert.assertEquals(operations.getResult(), "2");

    }


    @AfterClass
    public void closeSession() {
        driver.quit();
    }

}
