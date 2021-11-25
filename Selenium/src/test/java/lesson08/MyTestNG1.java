package lesson08;

import org.testng.annotations.*;


public class MyTestNG1 {
    @BeforeClass(alwaysRun=true)
    public void beforeClass() {
        System.out.println("TestNG1>Before Class Annotation");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("TestNG1>Before Method Annotation");
    }

    @Test(groups = {"sanity" })
    public void test01() {
        System.out.println("TestNG1>Test01 Annotation");
    }

    @Test(groups = {"regression" })
    public void test02() {
        System.out.println("TestNG1>Test02 Annotation");
    }

    @Test(groups = {"sanity" })
    public void test03() {
        System.out.println("TestNG1/test03>Test01 Annotation");
    }

    @Test(groups = {"sanity" })
    public void test04() {
        System.out.println("TestNG1/test04>Test01 Annotation");
    }

    @Test(groups = {"regression" })
    public void test05() {
        System.out.println("TestNG1/test05>Test01 Annotation");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("TestNG1>After Method Annotation");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("TestNG1>After Class Annotation");
    }


}
