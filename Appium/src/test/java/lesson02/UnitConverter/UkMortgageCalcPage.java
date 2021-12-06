package lesson02.UnitConverter;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class UkMortgageCalcPage {
    private AppiumDriver driver;

    public UkMortgageCalcPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(3)), this);
    }

    @AndroidFindBy(id = "arg1")
    private WebElement txtBox;
    @AndroidFindBy(id = "button_convert")
    private WebElement btnConvert;
    @AndroidFindBy(id = "question_and_answer_text")
    private WebElement fieldResult;

    public String getConvertResult(String value) {
        txtBox.click();
        txtBox.sendKeys(value);
        btnConvert.click();
        return fieldResult.getText().split("= ")[1].split("℉")[0];
    }
}