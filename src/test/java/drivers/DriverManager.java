package drivers;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class DriverManager {

//------------------ Aici incepe noua cale de access pentru chromedriver.exe versiunea 116 - 118 -----------------------
    public static ChromeDriver getDriver() {
        System.setProperty("webdriver.chrome.driver",
                "C:/Users/crist/IdeaProjects/- driver_Selenium-Maven/ChromeDriver/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(options);            // initilizare

//  maximizam ecranul
        driver.manage().window().maximize();

//  setam un wait global (implicit) de 5 secunde
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return driver;

    }
}
