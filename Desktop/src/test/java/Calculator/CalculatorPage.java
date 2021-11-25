package Calculator;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CalculatorPage {

    @FindBy(how = How.XPATH, using = "//*[@AutomationId='num0Button']")
    public WebElement zero;

    @FindBy(how = How.XPATH, using = "//*[@AutomationId='num1Button']")
    public WebElement one;

    @FindBy(how = How.XPATH, using = "//*[@AutomationId='num2Button']")
    public WebElement two;

    @FindBy(how = How.XPATH, using = "//*[@AutomationId='num3Button']")
    public WebElement three;

    @FindBy(how = How.XPATH, using = "//*[@AutomationId='num4Button']")
    public WebElement four;

    @FindBy(how = How.XPATH, using = "//*[@AutomationId='num5Button']")
    public WebElement five;

    @FindBy(how = How.XPATH, using = "//*[@AutomationId='num6Button']")
    public WebElement six;

    @FindBy(how = How.XPATH, using = "//*[@AutomationId='num7Button']")
    public WebElement seven;

    @FindBy(how = How.XPATH, using = "//*[@AutomationId='num8Button']")
    public WebElement eight;

    @FindBy(how = How.XPATH, using = "//*[@AutomationId='num9Button']")
    public WebElement nine;


    @FindBy(how = How.XPATH, using = "//*[@AutomationId='plusButton']")
    private WebElement add;

    @FindBy(how = How.XPATH, using = "//*[@AutomationId='minusButton']")
    private WebElement subtract;

    @FindBy(how = How.XPATH, using = "//*[@AutomationId='divideButton']")
    private WebElement divide;

    @FindBy(how = How.XPATH, using = "//*[@AutomationId='multiplyButton']")
    private WebElement multiply;

    @FindBy(how = How.XPATH, using = "//*[@AutomationId='equalButton']")
    private WebElement equal;

    @FindBy(how = How.XPATH, using = "//*[@AutomationId='clearButton']")
    private WebElement clear;

    @FindBy(how = How.XPATH, using = "//*[@AutomationId='CalculatorResults']")
    private WebElement result;

    public String getResult() {
        String temp=result.getText();
        String[] extractNumber=temp.split(" ");
        return extractNumber[extractNumber.length-1];

    }


    public void add(WebElement x, WebElement y) {
        x.click();
        add.click();
        y.click();
        equal.click();
    }

    public void clear() {
        clear.click();
    }

    public void subtract(WebElement x, WebElement y) {
        x.click();
        subtract.click();
        y.click();
        equal.click();
    }

    public void multiply(WebElement x, WebElement y) {
        x.click();
        multiply.click();
        y.click();
        equal.click();
    }

    public void divide(WebElement x, WebElement y) {
        x.click();
        divide.click();
        y.click();
        equal.click();
    }


}
