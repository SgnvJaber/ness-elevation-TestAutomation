package lesson09.ZapTest;

import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LoginPage {

    @FindBy(how = How.XPATH, using = "//input[@name='username']")
    private WebElement username_input_field;

    @FindBy(how = How.XPATH, using = "//input[@name='password']")
    private WebElement password_input_field;

    @FindBy(how = How.XPATH, using = "//button[@class='btn btn-default']")
    private WebElement login_btn;

    @FindBy(how = How.XPATH, using = "//div[@class='alert alert-warning']")
    private WebElement credentials_box;


    public void signIn(String username, String password) {
        username_input_field.sendKeys(username);
        password_input_field.sendKeys(password);
        login_btn.click();

    }

    public String getUsername() {
        return getCredentials("Username");
    }

    public String getPassword() {
        return getCredentials("Password");
    }


    private String getCredentials(String type) {
        String[] temp = credentials_box.getText().split(type + ": ");
        if (type == "Username") {
            return temp[1].substring(0, temp[1].indexOf('\n'));
        } else if (type == "Password") {
            return temp[1];
        }

        return "";
    }


}