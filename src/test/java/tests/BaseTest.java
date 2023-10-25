package tests;

import drivers.DriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.EmailAndTestsData;

/*
 In aceasta clasa avem definite APP-URL -urile pe care le selectam in vedere accesului pe pagina de web
 Utilizam metodele "@BeforeMethod" si "@AfterMethod" pentru apelarea acestora. Ele sunt parte a unor framework-uri de
 testare, cum este TestNG (Test Next Generation) in Java.
 */

public class BaseTest extends DriverManager {

    ChromeDriver driver;

    @BeforeMethod
    public void before(){

        driver = DriverManager.getDriver();
        driver.get(EmailAndTestsData.APP_URL1);
        driver.get(EmailAndTestsData.APP_URL2);
    }

    @AfterMethod
    public void after(){
        if (driver == null){
            driver.quit();
        }
    }

}