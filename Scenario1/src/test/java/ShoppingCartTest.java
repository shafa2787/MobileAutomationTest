import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class ShoppingCartTest {
    private AppiumDriver<MobileElement> driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "android");
        capabilities.setCapability("udid", "emulator-5554");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("deviceName", "local");
        capabilities.setCapability("appPackage", "com.nopstation.nopcommerce.nopstationcart");
        capabilities.setCapability("appActivity", "com.bs.ecommerce.main.SplashScreenActivity");

        URL appiumUrl = new URL("http://localhost:4723/wd/hub");
        driver = new AndroidDriver<>(appiumUrl, capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testAddProductToShoppingCart() {
        // Test steps
        driver.findElement(By.id("OUR_CATEGORIES")).click(); // Click on "OUR CATEGORIES"
        driver.findElement(By.id("electronics_category")).click(); // Click on "electronics"
        driver.findElement(By.id("mattress_bedroom_product")).click(); // Click on "Mattress Bedroom"
        for (int i = 0; i < 2; i++) {
            driver.findElement(By.id("plus_button")).click(); // Increase quantity by 2
        }
        driver.findElement(By.id("add_to_cart_button")).click(); // Click on "Add to Cart"

        // Add assertions or verification steps if needed
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
