package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChooseEmailFromPage {

    public ChooseEmailFromPage(WebDriver driver){ PageFactory.initElements(driver, this); }

/*
Definim elementele necesare pentru a apela email-ul dorit si de unde o sa copiem valorile de care avem
nevoie pentru a apela si accesa adresa de email unde trebuie sa facem verificarile solicitate.
 */

// Apelam folderul "Adrese_email" din INBOX, acolo unde ajung email-urile cautate
    @FindBy(xpath="//*[@id=\"folderPaneDroppableContainer\"]/div[3]/div/div/div[2]/div/div[1]/div")
    //*[@id="folderPaneDroppableContainer"]/div[3]/div/div/div[2]/div/div[1]
    private static WebElement clickAdrese_email;
    public static WebElement getClickAdrese_email() { return clickAdrese_email; }

//  Accesam email-ul cautat pentru a copia datele de care avem nevoie pt accesul la a doua adresa de email

    @FindBy(xpath = "//*[@id=\"AQAAABocLwwBAAAAGhwzeAAAAAA=\"]/div/div/div[2]/div/div[1]")
    //*[@id="AQAAAA41thgBAAAADjW2mwAAAAA="]/div/div/div[2]
    private static WebElement clickToTheEmail;
    public static WebElement getClickToTheEmail() { return clickToTheEmail; }


//------------------------- pt ca a aparut pe pagina Microsoft 365 trebuie sa-l inchidem --------------------------------

// Apelam sigla de "Box cu 9 puncte" din pagian de Microsoft 365
    @FindBy(xpath="//*[@id=\"5099F2E5-AD99-4BF2-BF82-DF525FFD0BE2\"]")
    private static WebElement clickBox;
    public static WebElement getClickBox() { return clickBox; }

// Apelam sigla de "Oultlook" din pagian de Microsoft 365
    @FindBy(id="O365_AppTile_Mail")
//   xpaht="//*[@id="O365_AppTile_Mail"]"
    private static WebElement clickOutlook;
    public static WebElement getClickOutlook() { return clickOutlook; }

}


