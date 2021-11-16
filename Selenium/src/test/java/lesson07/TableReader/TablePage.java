package lesson07.TableReader;


import com.opencsv.CSVWriter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TablePage {

    @FindBy(how = How.XPATH, using = "//table[@id='customers']")
    private WebElement table;

    @FindBy(how = How.XPATH, using = "//table[@id='customers']/tbody/tr/th")
    private List<WebElement> header;

    @FindBy(how = How.XPATH, using = "//table[@id='customers']/tbody/tr")
    private List<WebElement> rows;

    public int getRowsSize() {
        return rows.size();
    }

    public int getColsSize() {
        return header.size();
    }

    public List<String> getEuropeCountries() {
        int length = rows.size();
        int country_index = header.size() - 1;
        List<String> europeCountries = new ArrayList<>();

        for (int i = 1; i < length; i++) {
            String country = rows.get(i).findElements(By.tagName("td")).get(country_index).getText();
            europeCountries.add(country);
        }
        //Remove Countries that don't match the list
        europeCountries.remove("Mexico");
        europeCountries.remove("Canada");
        return europeCountries;
    }

    public void printEuropeCountries() {
        List<String> list = getEuropeCountries();
        for (String country : getEuropeCountries()) {
            System.out.println(country);
        }
    }


    //Source Code:https://www.geeksforgeeks.org/writing-a-csv-file-in-java-using-opencsv/
    public void writeToCSV() {
        // first create file object for file placed at location
        // specified by filepath
        File file = new File("./cells.csv");
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);
            // adding header to csv
            String[] head = new String[header.size()];
            for (int i = 0; i < header.size(); i++) {
                head[i] = header.get(i).getText();
            }
            writer.writeNext(head);

            // adding data to csv
            for (int i = 1; i < rows.size(); i++) {
                String[] data = new String[header.size()];
                for (int j = 0; j < header.size(); j++) {
                    data[j] = rows.get(i).findElements(By.tagName("td")).get(j).getText();
                }
                writer.writeNext(data);
            }
            // closing writer connection
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
