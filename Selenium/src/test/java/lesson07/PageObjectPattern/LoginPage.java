package lesson07.PageObjectPattern;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {

    @FindBy(how = How.NAME, using = "username2")
    private WebElement txt_username;
    @FindBy(how = How.NAME, using = "password2")
    private WebElement txt_password;
    @FindBy(how = How.ID, using = "submit")
    private WebElement txt_submit;
    public void sign_in(String username,String password)
    {
        txt_username.sendKeys(username);
        txt_password.sendKeys(password);
        txt_submit.click();
    }

}
