package BookShop;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class BookShopMain {
    private WebDriver driver;
    private StorePage store;
    private AdminPage admin_page;
    private OrdersPage orders;


    private final String url = "http://localhost:8080/";
    private final int expectedBooksSize = 33;
    public static RequestSpecification httpRequest;
    public Response response;
    private JsonPath jp;
    private String orders_json_url = "";
    private int numberOfBooks;

    @BeforeClass
    public void start() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
        store = PageFactory.initElements(driver, StorePage.class);
        admin_page = PageFactory.initElements(driver, AdminPage.class);
        orders = PageFactory.initElements(driver, OrdersPage.class);
        RestAssured.baseURI = url;
        httpRequest = RestAssured.given();
        numberOfBooks = store.getBooksSize();
        navigateToOrdersPage();
    }

    @Test
    public void test01_verifyBooksSize() {
        Assert.assertEquals(numberOfBooks, expectedBooksSize);
    }

    @Test
    public void test02_verifyCurrentPageProductsPrice() {
        Assert.assertEquals(orders.getTotalPricesFromCurrentPage(), getCurrentPageTotalByJSON(1));
    }

    @Test
    public void test03_verifyAllPageProductsPrice() {
        double totalByJson = getAllPagesTotalByJSON(orders.getNumberOfPages());
        double totalBySelenium = getAllPagesTotalBySelenium(orders.getNumberOfPages());
        System.out.println(totalByJson);
        System.out.println(totalBySelenium);
        Assert.assertEquals(totalByJson, totalBySelenium);
    }


    private void navigateToOrdersPage() {

        store.navigateToAdminPage();
        admin_page.getOrders();
        orders.navigateToJson();
        orders_json_url = driver.getCurrentUrl();
        driver.navigate().back();
    }


    private double getAllPagesTotalBySelenium(int number_of_pages) {
        double final_total = 0;
        int temp = number_of_pages;
        while (temp > 0) {
            final_total += orders.getTotalPricesFromCurrentPage();
            if (temp == 1) {
                break;
            }
            orders.moveToNextPage();
            temp--;
        }
        return final_total;
    }


    private double getAllPagesTotalByJSON(int number_of_pages) {
        int temp = number_of_pages;
        double final_total = 0;
        while (temp > 0) {
            final_total += getCurrentPageTotalByJSON(temp);
            temp--;
        }
        return final_total;
    }


    private double getCurrentPageTotalByJSON(int pageNumber) {
        response = httpRequest.get(orders_json_url + "?page=" + Integer.toString(pageNumber));
        jp = response.jsonPath();
        List<String> prices = jp.getList("total_price");
        double total = 0;
        for (String price : prices) {
            total += Double.parseDouble(price);

        }
        return total;
    }


    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }
}
