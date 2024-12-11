package google;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class GoogleTitleTest {

    WebDriver driver;

    @BeforeClass
    public void setUp() {

        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless"); // Headless mod
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920x1080");
        //options.addArguments("--lang=");
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("intl.accept_languages", "en-GB");
        options.setExperimentalOption("prefs", prefs);
        // Chrome tarayıcısını başlat
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void verifyGoogleTitle() {
        // Google ana sayfasını aç
        driver.get("https://www.google.com");

        // Sayfa başlığını al ve doğrula
        String actualTitle = driver.getTitle();
        String expectedTitle = "Google";

        Assert.assertEquals(actualTitle, expectedTitle, "Sayfa başlığı beklenen ile eşleşmiyor!");

        driver.findElement(By.name("q")).sendKeys("Selenium");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.name("btnK"))).click();

        wait.until(ExpectedConditions.textToBe(By.cssSelector("[role='list'] a:first-of-type div:first-of-type"), "Tümü"));
        Assert.assertEquals(driver.getTitle(), "Selenium - Google'da Ara", "Sayfa başlığı beklenen ile eşleşmiyor!");
    }

    @AfterClass
    public void tearDown() {
        // Tarayıcıyı kapat
        if (driver != null) {
            driver.quit();
        }
    }
}
