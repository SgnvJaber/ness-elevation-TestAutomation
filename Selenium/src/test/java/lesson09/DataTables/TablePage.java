package lesson09.DataTables;

import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TablePage {

    @FindBy(how = How.XPATH, using = "//table[@id='example']/thead/tr/th")
    private List<WebElement> thead;

    @FindBy(how = How.XPATH, using = "//table[@id='example']/tbody/tr")
    private List<WebElement> tbody;

    @FindBy(how = How.XPATH, using = "//a[@data-dt-idx='1']")
    private WebElement first_page_btn;

    @FindBy(how = How.XPATH, using = "//a[@id='example_next']")
    private WebElement next_page_btn;

    //getAttribute("textContent") could be used for disabled data
    @FindBy(how = How.XPATH, using = "//*[@id='example']/tbody/tr/td[6]")
    private List<WebElement> salaries;

    @FindBy(how = How.XPATH, using = "//table[@id='example']/tbody/tr/td[1]")
    private List<WebElement> names;

    @FindBy(how = How.XPATH, using = "//table[@id='example']/tbody/tr/td[2]")
    private List<WebElement> positions;

    @FindBy(how = How.XPATH, using = "//table[@id='example']/tbody/tr/td[3]")
    private List<WebElement> offices;

    @FindBy(how = How.XPATH, using = "//table[@id='example']/tbody/tr/td[4]")
    private List<WebElement> ages;

    private double total = 0;
    private int numberOfPeople = 0;

    private List<Integer> list = new ArrayList<>();

    //Counting the body's visible row+the head row
    public int getRowSize() {
        return tbody.size() + 1;
    }

    public int getColSize() {
        //Note this will include the inner "Salary" too.
        return thead.size();
    }

    public void reset() {
        first_page_btn.click();
    }

    public void moveToNextPage() {
        next_page_btn.click();
    }


    public List<Integer> getPeopleAllPages(String city, int minAge, String flag) {
        list=new ArrayList<>();
        String status = next_page_btn.getAttribute("tabindex");
        getPeopleCurrentPage(city, minAge, flag);
        while (status.equals("0")) {
            moveToNextPage();
            getPeopleCurrentPage(city, minAge, flag);
            status = next_page_btn.getAttribute("tabindex");
        }
        return list;
    }

    public double getSoftwareAverageSalariesFromAllPages(String position) {
        double average = 0;
        String status = next_page_btn.getAttribute("tabindex");
        while (status.equals("0")) {
            average += sumSalariesByPosition(position);
            moveToNextPage();
            status = next_page_btn.getAttribute("tabindex");
        }
        //adding the last page
        average += sumSalariesByPosition(position);
        return average / numberOfPeople;
    }


    public double sumSalariesByPosition(String position) {
        Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
        total = 0;
        for (int i = 0; i < salaries.size(); i++) {
            if (positions.get(i).getText().equals(position)) {
                numberOfPeople++;
                //Cutting the $ from the price
                String salary = salaries.get(i).getAttribute("textContent").replaceAll("[^-?0-9]+", "");
                total += Double.parseDouble(salary);
            }
        }
        return total;
    }

    public void getPeopleCurrentPage(String city, int minAge, String flag) {

        for (int i = 0; i < tbody.size(); i++) {
            String name = names.get(i).getText();
            String age = ages.get(i).getText();
            String office = offices.get(i).getText();
            int ageInteger = Integer.parseInt(age);
            if (office.equals(city) && ageInteger > minAge) {
                list.add(ageInteger);
                if (flag.equals("print")) {
                    System.out.println("Name: " + name + " Age:" + age);
                }
            }
        }
    }


}
