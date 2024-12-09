import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GoogleTitleTest {

    WebDriver driver;

    @BeforeClass
    public void setUp() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Headless mod
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        // Chrome tarayıcısını başlat
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
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
        driver.findElement(By.name("btnK")).click();

        Assert.assertEquals(driver.getTitle(), "selenium - Google Search", "ayfa başlığı beklenen ile eşleşmiyor!");
    }

    @AfterClass
    public void tearDown() {
        // Tarayıcıyı kapat
        if (driver != null) {
            driver.quit();
        }
    }
}
