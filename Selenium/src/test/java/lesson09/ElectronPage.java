package lesson09;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class ElectronPage {
    @FindBy(how = How.XPATH, using = "//nav/div/h5")
    private List<WebElement> menu;

    public int getMenuSize()
    {
        return menu.size();
    }
    public void printMenu()
    {
        for(WebElement tab:menu)
        {
            System.out.println(tab.getText());
        }
    }



}
