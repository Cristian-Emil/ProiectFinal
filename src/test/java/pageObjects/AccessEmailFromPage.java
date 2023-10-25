package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccessEmailFromPage {

    public AccessEmailFromPage(WebDriver driver){ PageFactory.initElements(driver, this); }

// Accesam pagina de email din site-ul Outlook unde definim butoanele de SignIn, Next si SignInAccount

    @FindBy(id ="mectrl_main_trigger" )
    private static WebElement signInButtonPage;
    public static WebElement getSignInButtonPage() { return signInButtonPage; }

//----------------------------------------------------------------------------------------------------------------------
    @FindBy(id="idSIButton9")
    private static WebElement next;
    public static WebElement getNext() { return next; }

// ---------------------------------------------------------------------------------------------------------------------

    @FindBy(id ="idSIButton9")
    private static WebElement signInAccount;
    public static WebElement getSignInAccount() { return signInAccount; }

}
