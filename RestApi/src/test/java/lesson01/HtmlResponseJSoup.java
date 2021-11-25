package lesson01;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class HtmlResponseJSoup {

    private Document doc;
    private Element logo;
    private final String expectedWidth = "250";
    private final String expectedHeight = "200";
    private final String expectedCategory = "All Categories";

    @BeforeClass
    public void openBrowser() throws IOException {
        doc = Jsoup.connect("https://www.ebay.com/").get();
        logo = doc.getElementById("gh-logo");
    }


    @Test
    public void test01_verifyLogoWidtht() {
        Assert.assertEquals(logo.attr("width"), expectedWidth);
    }

    @Test
    public void test02_verifyLogoHeight() {
        Assert.assertEquals(logo.attr("height"), expectedHeight);
    }

    @Test
    public void test03_verifyDropDown() {
        List<Element> select = doc.getElementsByAttributeValue("id", "gh-cat");
        String selected = select.get(0).text();
        System.out.println(selected);
        Assert.assertEquals(selected, expectedCategory);

    }

    @AfterClass
    public void closeBrowser() {
    }

}
