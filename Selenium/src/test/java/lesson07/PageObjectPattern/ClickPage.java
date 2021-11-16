package lesson07.PageObjectPattern;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ClickPage {
    @FindBy(how = How.XPATH, using = "//button[@type='button']")
    private WebElement txt_submit;

    public boolean isDisplayed() {
        return txt_submit.isDisplayed();
    }

    public void click() {
        txt_submit.click();
    }


}
