package lesson05;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.fail;

public class Support {
    private final static int N = 20;
    private final static int start_index = 3;

    public static void verifyElements(WebDriver driver) {
        try {
            WebElement container = driver.findElement(By.cssSelector("ul[class='ms-list']"));
            String[] actual_elements = container.getText().split("\n");
            String[] expected_elements = new String[N - start_index + 1];
            for (int i = start_index; i <= N; i++) {
                expected_elements[i - start_index] = ("elem " + i);
            }
            Assert.assertEquals(actual_elements, expected_elements);
        } catch (Exception e) {
            fail("Test Failed: " + e);

        } catch (AssertionError e) {
            fail("Test failed!" + e);
        }
    }
}
