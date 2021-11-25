package BookShop;

import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class StorePage {

    @FindBy(how = How.XPATH, using = "//div[@id='container']/div[@class='product']")
    private List<WebElement> books;

    @FindBy(how = How.XPATH, using = "//div[@id='auth']/a[@href='/admin']")
    private WebElement admin_page;

    public int getBooksSize() {
        return books.size();
    }

    public void navigateToAdminPage()
    {
        admin_page.click();
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
    }


}
