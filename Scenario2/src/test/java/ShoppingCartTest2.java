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

public class PlaceOrderTest {
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
    public void testPlaceOrderAsGuest() {
        // Go to shopping cart by clicking top cart icon
        driver.findElement(By.id("top_cart_icon")).click();

        // Click checkout button from shopping cart page
        driver.findElement(By.id("checkout_button")).click();

        // Select checkout as guest from shopping cart page
        driver.findElement(By.id("checkout_as_guest_button")).click();

        // Input all the details in checkout billing details page and click continue
        // Assuming Mike's details are already filled in the fields
        driver.findElement(By.id("billing_continue_button")).click();

        // Select "Next Day Air" as shipping method and click continue
        driver.findElement(By.id("next_day_air_shipping")).click();
        driver.findElement(By.id("shipping_continue_button")).click();

        // Select "Check/Money Order" as payment method and click continue
        driver.findElement(By.id("check_money_order_payment")).click();
        driver.findElement(By.id("payment_continue_button")).click();

        // Click next button on payment information page
        driver.findElement(By.id("next_button_payment_info")).click();

        // Click confirm button to place the order
        driver.findElement(By.id("confirm_button")).click();

        // Verify order place successfully with popup message "Your order has been successfully processed!"
        String confirmationMessage = driver.findElement(By.id("confirmation_message")).getText();
        assert confirmationMessage.equals("Your order has been successfully processed!") : "Order placement failed.";
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
