package BookShop;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class OrdersPage {

    @FindBy(how = How.XPATH, using = "//div[@class='download_links']/a[@href='/admin/orders.json']")
    private WebElement json_file;


    @FindBy(how = How.XPATH, using = "//span[@class='next']/a")
    private WebElement next_page_btn;

    @FindBy(how = How.XPATH, using = "//span[@class='last']/a")
    private WebElement last_page_btn;

    @FindBy(how = How.XPATH, using = "//tbody/tr/td[@class='col col-total']")
    private List <WebElement> prices;

    public int getNumberOfPages()
    {
        String link=last_page_btn.getAttribute("href");
        String[] words=link.split("=");
        int numberOfPages=Integer.parseInt(words[words.length-1]);
        return numberOfPages;
    }

    public void moveToNextPage()
    {
       next_page_btn.click();
    }



    public void printPrices()
    {
        for(WebElement price:prices)
        {
            System.out.println(price.getText());
        }
    }

    public double getTotalPricesFromCurrentPage()
    {
        double total=0;
        for(WebElement price:prices)
        {
            //Cutting the $ from the price
           total+=Double.parseDouble(price.getText().substring(1));
        }
        return total;
    }

    public void navigateToJson()
    {
        json_file.click();
    }


}
