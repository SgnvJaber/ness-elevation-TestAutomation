package lesson09.lessonExtra;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class LocateSvgPage {


    @FindBy(how = How.XPATH, using = "//label//*[name()='svg']//*[name()='path']")
    private List<WebElement> vCounter;

    public int getVSize() {
        return vCounter.size();
    }

}
