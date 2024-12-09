import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GoogleTitleTest {

    WebDriver driver;

    @BeforeClass
    public void setUp() {

        // Chrome tarayıcısını başlat
        driver = new ChromeDriver();
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
    }

    @AfterClass
    public void tearDown() {
        // Tarayıcıyı kapat
        if (driver != null) {
            driver.quit();
        }
    }
}
