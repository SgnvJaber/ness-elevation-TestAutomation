package lesson08;

import org.testng.annotations.*;


public class MyTestNG2 {
    @BeforeClass(alwaysRun=true)
    public void beforeClass() {
        System.out.println("TestNG2>Before Class Annotation");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("TestNG2>Before Method Annotation");
    }

    @Test(groups = {"regression"})
    public void test01() {
        System.out.println("TestNG2>Test01 Annotation");
    }

    @Test(groups = {"sanity"})
    public void test02() {
        System.out.println("TestNG2>Test02 Annotation");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("TestNG2>After Method Annotation");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("TestNG2>After Class Annotation");
    }


}
