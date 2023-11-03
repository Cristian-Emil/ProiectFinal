package tests;

import drivers.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.AccessEmailFromPage;
import pageObjects.ChooseAccount;
import pageObjects.ChooseEmailFromPage;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

import static utils.EmailAndTestsData.*;

public class LoginAndCheckData_cu_10teste extends BaseTest{

/*
 Cerinta proiectului este :
 - se acceseaza pagina de email definita in clasa EmailAndTestsData la lina unde se specifica si este precizat APP_URL1
 - se utilizeaza adresa de email de la linia cu numele USERNAME1
 - se utilizeaza parola de la linia cu numele PASSWORD1
 - dupa accesul in primul cont se cauta email-ul cu adresa si parola pentru al doilea email
 - se citesc valorile  si se inchide browserul
 - se deschide un nou broser si se acceseaza noul cont unde se verifica numarul de email-uri necitite din fiecare fisier
 - se inchide si acest al doilea email si se iese din pagina de web.

 Pentru a rula programul folosim "@Test" care este o adnotare a fremawork-ului Junit si TestNG, cu el marcam ca aceasta
 este o metoda de testarer utilizata cand se foloseste Selenium WebDriver sau Selenium WebElement. Acestea sunt folosite
 pentru a automatiza testele pe aplicatia web. Adnotarea @Test ajuta la organizarea si rularea testelor in mod automat.
 Selenium WebDriver - este componenta centrala a framework-ului Selenium. Ea este o biblioteca care permite interactiunea
 cu diverse browsere web precum Google Chrome, Mozilla Firefox si altele. Utilizand WebDriver puteti controla si automatiza
 actiunile in browser cum ar fi navigarea pe pagini, completarea formularelor, facand clic pe elementele ce dorim sa le
 apelam, extragerea informatiilor din pafgina si multe alte optiuni.
 WebElement este o clasa din Selenium care reprezinta un element dintr-o pagina web. Acesta este folosit pentru a
 interactiona cu elementele de pe pagina, cum ar fi butoane, link-uri, elemente de tip lista, casete de text, etc.
 */

    /*
    Pentru a putea vizualiza modul in care programul lucreaza dupa fiecare operatie am inserat un  "Thread.sleep" ce specifica

     */
    @Test
    public void testLogInAdminVailidCredentiual1() throws InterruptedException {

        AccessEmailFromPage accesEmailFromPage1 = PageFactory.initElements(driver, AccessEmailFromPage.class);
//        Thread.sleep(1000);
        AccessEmailFromPage.getSignInButtonPage().click();
//        Thread.sleep(1000);

// Ne focalizam pe pagina pe care o deschidem, cu ajutorul liniilor de mai jos

        String parentWindow = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
//        Thread.sleep(1000);

        for (String window : windowHandles) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

/*
 Apelam si intram pe pagina de OUTLOOK unde inseram valorile definite in "ChooseAccount" si "BaseTest"
 ATENTIE : nu uitati ca in BaseTest avem definita apelarea valorilor din EmailAndTestData , clasa unde
 sunt stocate valorile pentru accesarea primului email
 */


        ChooseAccount chooseAccount = PageFactory.initElements(driver, ChooseAccount.class);
//        Thread.sleep(1000);

        ChooseAccount.getUserName().sendKeys(USERNAME1);
//        Thread.sleep(1000);
        AccessEmailFromPage.getNext().click();
//        Thread.sleep(1000);

        ChooseAccount.getPassword().sendKeys(PASSWORD1);
//        Thread.sleep(1000);
        AccessEmailFromPage.getSignInAccount().click();
//        Thread.sleep(1000);

        ChooseAccount.getNoButton().click();
//        Thread.sleep(1000);

// --- A aparut pagian de Microsoft 365 si trebuie s-o inchidem
//        ChooseEmailFromPage.getClickBox().click();
//        Thread.sleep(1000);
//
//        ChooseEmailFromPage.getClickOutlook().click();
//        Thread.sleep(1000);

/*
 In clasa ChooseEmailFromPage avem definite elementele pentru selectarea si accesarea email-urilor
  din contul apelat
 */

//-------------------- se continua pe pagina de Outlook.com pemntru a rula programul -----------------------------------

        ChooseEmailFromPage chooseEmailFromPage = PageFactory.initElements(driver, ChooseEmailFromPage.class);
//        Thread.sleep(1000);

// Ne ducem pe fisierul "Adrese_email" si il accesam pentru a cauta si ajunge la email-ul dorit

        ChooseEmailFromPage.getClickAdrese_email().click();
//        Thread.sleep(1000);

// Facem click pe email-ul dorit si intram in corpul acestuia pentru a vedea si prelua valorile existente

        ChooseEmailFromPage.getClickToTheEmail().click();
//        Thread.sleep(1000);

/*
Pentru a gasi si apela elementele pe care le dorim o sa folosim urmatoarele comenzi:
 WebElement = cu aceasta putem accesa valorile cautate si le putem copia. Selenium WebElement este utilizat
 pentru a interactiona cu elementele din pagina web

 driver.findElement = este o apelare de metoda folosita pentru a cauta un element pe pagina web. Ea cauta un
 singur element

 Folosim 2 metode de localizare a elementelor web, By.cssSelector si By.xpath ca sa vedem care este diferenta
 dintre ele in utilizarea curenta. Metoda By.id a fost utilizata in celelalte clase unde sunt definite elementele
 de pe pagina de email deci nu are rost sa o folosim si aici.
 */

        WebElement dataPackage1 = driver.findElement(By.cssSelector("#UniqueMessageBody > div > div > div > div:nth-child(3) > div:nth-child(4)"));
//        WebElement dataPackage1 = driver.findElement(By.xpath("//*[@id=\"UniqueMessageBody\"]/div/div/div/div[3]/div[4]"));
//        Thread.sleep(1000);
        WebElement dataPackage2 = driver.findElement(By.cssSelector("#UniqueMessageBody > div > div > div > div:nth-child(3) > div:nth-child(5)"));
//        WebElement dataPackage2 = driver.findElement(By.xpath("//*[@id=\"UniqueMessageBody\"]/div/div/div/div[3]/div[5]"));
//        Thread.sleep(1000);

// Tiparim cele 2 texte/valori copiate ca sa vedem valorile accesate :
        System.out.println(dataPackage1.getText());
        System.out.println(dataPackage2.getText());

        String Readable1 = dataPackage1.getText();
        String testconsola1 = Readable1;
        String text1 = testconsola1.split(":")[0];
        String code1 = testconsola1.split(": ")[1];
        System.out.println(text1);
        System.out.println(code1);

        String Readable2 = dataPackage2.getText();
        String testconsola2 = Readable2;
        String text2 = testconsola2.split(":")[0];
        String code2 = testconsola2.split(": ")[1];
        System.out.println(text2);
        System.out.println(code2);

/*
    Tiparim un rand liber ca sa vedem unde este diferenta dintre secventele de program si unde trecem
    la al doilea email
*/
        System.out.println("  ");

        String USERNAME2 = code1;
        String PASSWORD2 = code2;

/*  Pt ca sa utilizam acceasi pagina de OUTLOOK se iese de pe pagina si se inchide browserul
    Pentru a iesi din brouse apelam SignOutAccount si inchidem pagina
*/
//        Thread.sleep(2000);

        WebElement QuitAccount1 = driver.findElement(By.id("O365_MainLink_Me"));
//        WebElement QuitAccount = driver.findElement(By.cssSelector("#O365_MainLink_Me"));
//        WebElement QuitAccount = driver.findElement(By.xpath("//*[@id="O365_MainLink_Me"]"));
        QuitAccount1.click();
//        Thread.sleep(1500);

        WebElement SignOutPage1 = driver.findElement(By.id("mectrl_body_signOut"));
//        WebElement SignOutPage = driver.findElement(By.cssSelector("#mectrl_body_signOut"));
//        WebElement SignOutPage = driver.findElement(By.xpath("//*[@id=\"mectrl_body_signOut\"]"));
        SignOutPage1.click();
//        Thread.sleep(1500);

//  Apelam functiile close si quit ca sa avem convingerea ca am inchis corectc browserul accesat.
        driver.close();
        driver.quit();
//        Thread.sleep(1500);

//  Pentru verificare ca am apelat valorile care trebuie, tiparim noul username si noua parola:
        System.out.println("Valorile de mai jos se utilizeaza pentru USERNAME si PAROLA cand accesam noul cont");
        System.out.println(USERNAME2);
        System.out.println(PASSWORD2);
        System.out.println(" ");

//  Reluam procesul de accesare al paginii de OUTLOOK si introducem noile date preluate din email-ul anterior:

        driver = DriverManager.getDriver();
        driver.get(APP_URL2);

        AccessEmailFromPage accesEmailFromPage2 = PageFactory.initElements(driver, AccessEmailFromPage.class);
//        Thread.sleep(1000);
        AccessEmailFromPage.getSignInButtonPage().click();
//        Thread.sleep(1000);

// Ne focalizam pe noua pagina pe care o deschidem, cum am facut si prima data

        parentWindow = driver.getWindowHandle();
        windowHandles = driver.getWindowHandles();
//        Thread.sleep(1000);

        for (String window : windowHandles) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

        chooseAccount = PageFactory.initElements(driver, ChooseAccount.class);
//        Thread.sleep(1000);

        ChooseAccount.getUserName().sendKeys(USERNAME2);
//        Thread.sleep(1000);
        AccessEmailFromPage.getNext().click();
//        Thread.sleep(1000);

        ChooseAccount.getPassword().sendKeys(PASSWORD2);
//        Thread.sleep(1000);
        AccessEmailFromPage.getSignInAccount().click();
//        Thread.sleep(1000);

        ChooseAccount.getNoButton().click();
//        Thread.sleep(1000);

/*
 Pentru diverse verificari utilizam comanda ASSER. In acest caz folosim SoftAssert pentru a nu intrerupe rularea programul
 in cazul unei erori aparute in procesul de verificare.
 Cu comanda softAssert facem verificarile de care avem nevoie. Deci trebuie sa instantiem o comanda si apoi utilizam
 WebElement ca sa apelam datele de pe pagina web.
 Se verifica toate fisierele din email si se afiseaza numarul de email-uri necitite pentru fiecare din ele, daca exista.
 */

        SoftAssert softAssert = new SoftAssert();

        WebElement message1 = driver.findElement(By.cssSelector("#folderPaneDroppableContainer > div.e4_J1 > div > div > div:nth-child(1) > div"));
        softAssert.assertEquals(message1.getText(), "Inbox", "Test 1 - elementul nu exista, test invalid");
//        Thread.sleep(1000);

        WebElement message2 = driver.findElement(By.cssSelector("#folderPaneDroppableContainer > div.e4_J1 > div > div > div:nth-child(3)"));
        softAssert.assertEquals(message2.getText(), "Junk Email", "Test 2 - elementul nu exista, test invalid");
//        Thread.sleep(1000);

        WebElement message3 = driver.findElement(By.cssSelector("#folderPaneDroppableContainer > div.e4_J1 > div > div > div:nth-child(4)"));
        softAssert.assertEquals(message3.getText(), "Drafts", "Test 3 - elementul nu exista, test invalid");
//       Thread.sleep(1000);

        WebElement message4 = driver.findElement(By.cssSelector("#folderPaneDroppableContainer > div.e4_J1 > div > div > div:nth-child(5)"));
        softAssert.assertEquals(message4.getText(), "Sent Items", "Test 4 - elementul nu exista, test invalid");
//        Thread.sleep(1000);

        WebElement message5 = driver.findElement(By.cssSelector("#folderPaneDroppableContainer > div.e4_J1 > div > div > div:nth-child(6)"));
        softAssert.assertEquals(message5.getText(), "Deleted Items", "Test 5 - elementul nu exista, test invalid");
//        Thread.sleep(1000);

        WebElement message6 = driver.findElement(By.cssSelector("#folderPaneDroppableContainer > div.e4_J1 > div > div > div:nth-child(7)"));
        softAssert.assertEquals(message6.getText(), "Archive", "Test 6 - elementul nu exista, test invalid");
//        Thread.sleep(1000);

        WebElement message7 = driver.findElement(By.cssSelector("#folderPaneDroppableContainer > div.e4_J1 > div > div > div:nth-child(8)"));
        softAssert.assertEquals(message7.getText(), "Archive", "Test 7 - elementul nu exista, test invalid");
//        Thread.sleep(1000);

        WebElement message8 = driver.findElement(By.cssSelector("#folderPaneDroppableContainer > div.e4_J1 > div > div > div:nth-child(9)"));
        softAssert.assertEquals(message8.getText(), "ConversationHistory", "Test 8 - elementul nu exista, test invalid");
//        Thread.sleep(1500);


//Am separat partea de afisare la consola (au fost scoase in afara liniilor ASSERT ) ca sa vizualizam mai bine modul de lucru:

        System.out.println(message1.getText());
        System.out.println("  ");
        System.out.println(message2.getText());
        System.out.println(" ");
        System.out.println(message3.getText());
        System.out.println(" ");
        System.out.println(message4.getText());
        System.out.println(" ");
        System.out.println(message5.getText());
        System.out.println(" ");
        System.out.println(message6.getText());
        System.out.println(" ");
        System.out.println(message7.getText());
        System.out.println(" ");
        System.out.println(message8.getText());

        System.out.println(" ");
        System.out.println("Atribuim valorile ce trebuie sa fie inserate in documentul HTML sau TXT. \n");

        String Folder_Inbox = message1.getText();
        String Folder_JunkEmails = message2.getText();
        String Folder_Drafts = message3.getText();
        String Folder_SentItems = message4.getText();
        String Folder_DeleteItems = message5.getText();
        String Folder_Archive = message6.getText();
        String Folder_Notes = message7.getText();
        String Folder_ConversationHistory = message8.getText();

// Punem un sleep pentru a lasa programul sa ruleze complet dupa care se inchide si iesim din browser

//        Thread.sleep(1000);

        WebElement QuitAccount2 = driver.findElement(By.xpath("//*[@id=\"O365_MainLink_Me\"]"));
        QuitAccount2.click();
//        Thread.sleep(1000);

        WebElement SignOutPage2 = driver.findElement(By.xpath("//*[@id=\"mectrl_body_signOut\"]"));
        SignOutPage2.click();
//        Thread.sleep(1000);

/*
Tiparim/afisam rezultatele testului pe care le obțineți din variabilele selectate
 */

        System.out.println(" ");
        System.out.println("Tiparim valorile ce trebuie sa fie inserate in documentul HTML. \n");

        System.out.println(Folder_Inbox);
        System.out.println(Folder_JunkEmails);
        System.out.println(Folder_Drafts);
        System.out.println(Folder_SentItems);
        System.out.println(Folder_DeleteItems);
        System.out.println(Folder_Archive);
        System.out.println(Folder_Notes);
        System.out.println(Folder_ConversationHistory);

// ---------- Din acest punct incepem sa facem TESTELE necesare pt a ajunge la numarul de 10, numar cerut in proiect ----
// Accesam calea către fișierul HTML_X unde dorim sa inseram valorile

        String filePath1 = "Raport_Test1.html";
         try {
// Se deschide fișierul HTML1 specificat mai sus pentru scrierea valorilor atribuite
            FileWriter fileWriter1 = new FileWriter(filePath1);
// Scriem conținutul fisierului HTML1 cu rezultatele valide
            fileWriter1.write("<html><head><title>Rezultate teste realizate in email</title></head><body>");
//            fileWriter1.write("<h1>Valorile copiate si tiparite in raport sunt : </h1>");
            fileWriter1.write("<p>" + Folder_Inbox + "</p>");
            fileWriter1.write("</body></html>");
// Închideți fișierul
            fileWriter1.close();
// Confirmam ca valorile au fost inserate in fisierul "Raport_Test1"
            System.out.println("Valorile copiate au fost scrise in fisierul - " + filePath1);
        } catch (IOException e) {
            e.printStackTrace();
        }


        String filePath2 = "Raport_Test2.html";
        try {
// Se deschide fișierul HTML2 specificat mai sus pentru scrierea valorilor atribuite
            FileWriter fileWriter2 = new FileWriter(filePath2);
// Scriem conținutul fisierului HTML2 cu rezultatele valide
            fileWriter2.write("<html><head><title>Rezultate teste realizate in email</title></head><body>");
//            fileWriter2.write("<h1>Valorile copiate si tiparite in raport sunt : </h1>");
            fileWriter2.write("<p>" + Folder_JunkEmails + "</p>");
            fileWriter2.write("</body></html>");
// Închideți fișierul
            fileWriter2.close();
// Confirmam ca valorile au fost inserate in fisierul "Raport_Test2"
            System.out.println("Valorile copiate au fost scrise in fisierul - " + filePath2);
        } catch (IOException e) {
            e.printStackTrace();
        }


        String filePath3 = "Raport_Test3.html";
        try {
// Se deschide fișierul HTML3 specificat mai sus pentru scrierea valorilor atribuite
            FileWriter fileWriter3 = new FileWriter(filePath3);
// Scriem conținutul fisierului HTML3 cu rezultatele valide
            fileWriter3.write("<html><head><title>Rezultate teste realizate in email</title></head><body>");
//            fileWriter3.write("<h1>Valorile copiate si tiparite in raport sunt : </h1>");
            fileWriter3.write("<p>" + Folder_Drafts + "</p>");
            fileWriter3.write("</body></html>");
// Închideți fișierul
            fileWriter3.close();
// Confirmam ca valorile au fost inserate in fisierul "Raport_Test3"
            System.out.println("Valorile copiate au fost scrise in fisierul - " + filePath3);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String filePath4 = "Raport_Test4.html";
        try {
// Se deschide fișierul HTML4 specificat mai sus pentru scrierea valorilor atribuite
            FileWriter fileWriter4 = new FileWriter(filePath4);
// Scriem conținutul fisierului HTML4 cu rezultatele valide
            fileWriter4.write("<html><head><title>Rezultate teste realizate in email</title></head><body>");
//            fileWriter4.write("<h1>Valorile copiate si tiparite in raport sunt : </h1>");
            fileWriter4.write("<p>" + Folder_SentItems + "</p>");
            fileWriter4.write("</body></html>");
// Închideți fișierul
            fileWriter4.close();
// Confirmam ca valorile au fost inserate in fisierul "Raport_Test4"
            System.out.println("Valorile copiate au fost scrise in fisierul - " + filePath4);
        } catch (IOException e) {
            e.printStackTrace();
        }


        String filePath5 = "Raport_Test5.html";
        try {
// Se deschide fișierul HTML5 specificat mai sus pentru scrierea valorilor atribuite
            FileWriter fileWriter5 = new FileWriter(filePath5);
// Scriem conținutul fisierului HTML5 cu rezultatele valide
            fileWriter5.write("<html><head><title>Rezultate teste realizate in email</title></head><body>");
//            fileWriter5.write("<h1>Valorile copiate si tiparite in raport sunt : </h1>");
            fileWriter5.write("<p>" + Folder_DeleteItems + "</p>");
            fileWriter5.write("</body></html>");
// Închideți fișierul
            fileWriter5.close();
// Confirmam ca valorile au fost inserate in fisierul "Raport_Test5"
            System.out.println("Valorile copiate au fost scrise in fisierul - " + filePath5);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String filePath6 = "Raport_Test6.html";
        try {
// Se deschide fișierul HTML6 specificat mai sus pentru scrierea valorilor atribuite
            FileWriter fileWriter6 = new FileWriter(filePath6);
// Scriem conținutul fisierului HTML5 cu rezultatele valide
            fileWriter6.write("<html><head><title>Rezultate teste realizate in email</title></head><body>");
//            fileWriter6.write("<h1>Valorile copiate si tiparite in raport sunt : </h1>");
            fileWriter6.write("<p>" + Folder_Archive + "</p>");
            fileWriter6.write("</body></html>");
// Închideți fișierul
            fileWriter6.close();
// Confirmam ca valorile au fost inserate in fisierul "Raport_Test6"
            System.out.println("Valorile copiate au fost scrise in fisierul - " + filePath6);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String filePath7 = "Raport_Test7.html";
        try {
// Se deschide fișierul HTML7 specificat mai sus pentru scrierea valorilor atribuite
            FileWriter fileWriter7 = new FileWriter(filePath7);
// Scriem conținutul fisierului HTML7 cu rezultatele valide
            fileWriter7.write("<html><head><title>Rezultate teste realizate in email</title></head><body>");
//            fileWriter7.write("<h1>Valorile copiate si tiparite in raport sunt : </h1>");
            fileWriter7.write("<p>" + Folder_Notes + "</p>");
            fileWriter7.write("</body></html>");
// Închideți fișierul
            fileWriter7.close();
// Confirmam ca valorile au fost inserate in fisierul "Raport_Test7"
            System.out.println("Valorile copiate au fost scrise in fisierul - " + filePath7);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String filePath8 = "Raport_Test8.html";
        try {
// Se deschide fișierul HTML8 specificat mai sus pentru scrierea valorilor atribuite
            FileWriter fileWriter8 = new FileWriter(filePath8);
// Scriem conținutul fisierului HTML8 cu rezultatele valide
            fileWriter8.write("<html><head><title>Rezultate teste realizate in email</title></head><body>");
//            fileWriter8.write("<h1>Valorile copiate si tiparite in raport sunt : </h1>");
            fileWriter8.write("<p>" + Folder_ConversationHistory + "</p>");
            fileWriter8.write("</body></html>");
// Închideți fișierul
            fileWriter8.close();
// Confirmam ca valorile au fost inserate in fisierul "Raport_Test8"
            System.out.println("Valorile copiate au fost scrise in fisierul - " + filePath8);
        } catch (IOException e) {
            e.printStackTrace();
        }

/*
Apelam functia quit, cum am precizat mai sus ca sa avem siguranta ca am inchis browserele accesate  si am golim m
emoria computerului.
 */
        driver.close();
        driver.quit();
    }

    @Test
    public void testLogInAdminVailidCredentiual2() {

// Accesam fisierul "Raport_Test1" si-l rulam pt a afisa in pagina Chrome valorile citite
        File html_file1 = new File("Raport_Test1.html");

        try {
// Verifică dacă Desktop suporta accesarea acestei pagini de HTML si poate sa prezinte valorile dorite
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(html_file1.toURI());
            } else {
                System.out.println("Trebuie sa deschideți manual fișierul HTML în Chrome pt. ca avem o eroare");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver.close();
        driver.quit();
    }

    @Test
    public void testLogInAdminVailidCredentiual3() {

// Accesam fisierul "Raport_Test2" si-l rulam pt a afisa in pagina Chrome valorile citite
        File html_file2 = new File("Raport_Test2.html");

        try {
// Verifică dacă Desktop suporta accesarea acestei pagini de HTML si poate sa prezinte valorile dorite
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(html_file2.toURI());
            } else {
                System.out.println("Trebuie sa deschideți manual fișierul HTML în Chrome pt. ca avem o eroare");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver.close();
        driver.quit();
    }

    @Test
    public void testLogInAdminVailidCredentiual4() {

// Accesam fisierul "Raport_Test2" si-l rulam pt a afisa in pagina Chrome valorile citite
        File html_file3 = new File("Raport_Test3.html");

        try {
// Verifică dacă Desktop suporta accesarea acestei pagini de HTML si poate sa prezinte valorile dorite
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(html_file3.toURI());
            } else {
                System.out.println("Trebuie sa deschideți manual fișierul HTML în Chrome pt. ca avem o eroare");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver.close();
        driver.quit();
    }

    @Test
    public void testLogInAdminVailidCredentiual5() {

// Accesam fisierul "Raport_Test2" si-l rulam pt a afisa in pagina Chrome valorile citite
        File html_file4 = new File("Raport_Test4.html");

        try {
// Verifică dacă Desktop suporta accesarea acestei pagini de HTML si poate sa prezinte valorile dorite
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(html_file4.toURI());
            } else {
                System.out.println("Trebuie sa deschideți manual fișierul HTML în Chrome pt. ca avem o eroare");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver.close();
        driver.quit();
    }

    @Test
    public void testLogInAdminVailidCredentiual6() {

// Accesam fisierul "Raport_Test5" si-l rulam pt a afisa in pagina Chrome valorile citite
        File html_file5 = new File("Raport_Test5.html");

        try {
// Verifică dacă Desktop suporta accesarea acestei pagini de HTML si poate sa prezinte valorile dorite
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(html_file5.toURI());
            } else {
                System.out.println("Trebuie sa deschideți manual fișierul HTML în Chrome pt. ca avem o eroare");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver.close();
        driver.quit();
    }

    @Test
    public void testLogInAdminVailidCredentiual7() {

// Accesam fisierul "Raport_Test6" si-l rulam pt a afisa in pagina Chrome valorile citite
        File html_file6 = new File("Raport_Test6.html");

        try {
// Verifică dacă Desktop suporta accesarea acestei pagini de HTML si poate sa prezinte valorile dorite
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(html_file6.toURI());
            } else {
                System.out.println("Trebuie sa deschideți manual fișierul HTML în Chrome pt. ca avem o eroare");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver.close();
        driver.quit();
    }

    @Test
    public void testLogInAdminVailidCredentiual8() {

// Accesam fisierul "Raport_Test7" si-l rulam pt a afisa in pagina Chrome valorile citite
        File html_file7 = new File("Raport_Test7.html");

        try {
// Verifică dacă Desktop suporta accesarea acestei pagini de HTML si poate sa prezinte valorile dorite
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(html_file7.toURI());
            } else {
                System.out.println("Trebuie sa deschideți manual fișierul HTML în Chrome pt. ca avem o eroare");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver.close();
        driver.quit();
    }

    @Test
    public void testLogInAdminVailidCredentiual9() {

// Accesam fisierul "Raport_Test8" si-l rulam pt a afisa in pagina Chrome valorile citite
        File html_file8 = new File("Raport_Test8.html");

        try {
// Verifică dacă Desktop suporta accesarea acestei pagini de HTML si poate sa prezinte valorile dorite
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(html_file8.toURI());
            } else {
                System.out.println("Trebuie sa deschideți manual fișierul HTML în Chrome pt. ca avem o eroare");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver.close();
        driver.quit();
    }

    @Test
    public void testLogInAdminVailidCredentiual10() throws IOException {

        String Raport1 = new String(Files.readAllBytes(Paths.get("Raport_Test1.html")));
        String Raport2 = new String(Files.readAllBytes(Paths.get("Raport_Test2.html")));
        String Raport3 = new String(Files.readAllBytes(Paths.get("Raport_Test3.html")));
        String Raport4 = new String(Files.readAllBytes(Paths.get("Raport_Test4.html")));
        String Raport5 = new String(Files.readAllBytes(Paths.get("Raport_Test5.html")));
        String Raport6 = new String(Files.readAllBytes(Paths.get("Raport_Test6.html")));
        String Raport7 = new String(Files.readAllBytes(Paths.get("Raport_Test7.html")));
        String Raport8 = new String(Files.readAllBytes(Paths.get("Raport_Test8.html")));

// Accesam calea către fișierul HTML unde dorim sa inseram valorile
        String filePath = "RaportComplet_Teste.html";

        try {
// Se deschide fișierul HTML specificat mai sus pentru scrierea valorilor atribuite
            FileWriter fileWriter = new FileWriter(filePath);

// Scriem conținutul fisierului HTML cu rezultatele valide
            fileWriter.write("<html><head><title>Rezultate teste realizate in email</title></head><body>");
            fileWriter.write("<h1>Valorile copiate si tiparite in raportul complet sunt : </h1>");
            fileWriter.write("<p>" + Raport1 + "</p>");
            fileWriter.write("<p>" + Raport2 + "</p>");
            fileWriter.write("<p>" + Raport3 + "</p>");
            fileWriter.write("<p>" + Raport4 + "</p>");
            fileWriter.write("<p>" + Raport5 + "</p>");
            fileWriter.write("<p>" + Raport6 + "</p>");
            fileWriter.write("<p>" + Raport7 + "</p>");
            fileWriter.write("<p>" + Raport8 + "</p>");
            fileWriter.write("</body></html>");

// Închideți fișierul
            fileWriter.close();

// Confirmam ca valorile au fost inserate in fisierul "RaportComplet_Teste"
            System.out.println("Valorile copiate au fost scrise in fisierul - " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

// Accesam fisierul "RaportComplet_Teste" si-l rulam pt a afisa in pagina Chrome valorile citite
        File html_file = new File("RaportComplet_teste.html");

        try {
// Verifică dacă Desktop suporta accesarea acestei pagini de HTML si poate sa prezinte valorile dorite
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(html_file.toURI());
            } else {
                System.out.println("Trebuie sa deschideți manual fișierul HTML în Chrome pt. ca avem o eroare");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver.close();
        driver.quit();
    }

}
