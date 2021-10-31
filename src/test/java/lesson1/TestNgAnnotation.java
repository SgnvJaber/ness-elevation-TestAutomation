package lesson1;

import org.testng.annotations.*;

public class TestNgAnnotation {
    @BeforeClass
    public void beforeClass() {
        System.out.println("Before Class Annotation");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Before Method Annotation");
    }

    @Test
    public void test01() {
        System.out.println("Test01 Annotation");
    }

    @Test
    public void test02() {
        System.out.println("Test02 Annotation");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("After Method Annotation");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("After Class Annotation");
    }


}
