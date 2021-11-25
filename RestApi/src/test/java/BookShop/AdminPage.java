package BookShop;

import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.concurrent.TimeUnit;

public class AdminPage {

    @FindBy(how = How.XPATH, using = "//li[@id='orders']/a[@href='/admin/orders']")
    private WebElement orders;


    public void getOrders() {
        orders.click();
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
    }


}
