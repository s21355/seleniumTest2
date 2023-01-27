package seleniumTest2;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;


public class FirstAndThird {
    private static WebDriver driver;

    @BeforeAll
    public static void setUpDriver(){
        System.setProperty("webdriver.msedge.driver", "resources/msedgedriver");
        EdgeOptions options= new EdgeOptions();
        options.addArguments("start-maximized", "--headless");
        driver = new EdgeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @BeforeEach
    public void setUp() throws Exception {
        driver.get("https://www.bing.com/");
    }

    @AfterAll
    public static void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void testFirstLink()  {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Wikipedia");

        //Dla wyszukiwania można użyć metody submit(), a w innych przypadkach zamiast click() możemy użyć sendKeys(Keys.ENTER)
        searchBox.submit();

        //W przypadku gdy danego elementu nie można wyszukać na stornie można wykorzystać NoSuchElementException
        try{
            WebElement firstResult = driver.findElement(By.className("b_topTitle"));
            firstResult.click();
            Assertions.assertNotNull(firstResult);
        }catch(NoSuchElementException e){
            System.out.println("Element nie znaleziony: " + e.getMessage());
        }
    }

    @Test
    public void testThirdLink()  {
        WebElement searchBox = driver.findElement(By.id("sb_form_q"));
        searchBox.sendKeys("Wikipedia");
        searchBox.submit();

        WebElement acceptBox = driver.findElement(By.cssSelector("#bnp_btn_accept"));
        acceptBox.click();

        WebElement thirdResult = driver.findElement(By.xpath("//ol/li[3]/div[1]/div/h2/a"));
        thirdResult.click();

        Assertions.assertNotNull(thirdResult);
    }
}
