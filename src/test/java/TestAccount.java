import io.qameta.allure.Description;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestAccount extends BaseClass{

    @Description("Validate that the login is working with non valid credentials")
    @Test(description = "Test Login Success")
    public void Test_Longin_Successful(){
        String username = "carolina.benavides,venegas@ucrativa.com";
        String password = "asdf";

        //Go To Login Page
        driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/a/span[1]")).click();
        driver.findElement(By.linkText("Login")).click();

         /*
        EJEMPLO DE LISTAS Y WEBELEMENTS SOLOS
        WebElement WishList = driver.findElement(By.linkText("Wish List"));
        ArrayList<> WishListList = driver.findElements(By.linkText("Wish List"));
        */

        //Llenar formulario
        driver.findElement(By.name("email")).sendKeys("username");
        driver.findElement(By.name("password")).sendKeys("password");
        driver.findElement(By.cssSelector("[value='Login']")).click();

        WebElement logOutButton = driver.findElement(By.linkText("Logout"));
        Assert.assertTrue(logOutButton.isDisplayed());
    }

    @Description("Validate that the login is working with non valid credentials")
    @Test(description = "Test Login Not Success")
    public void Test_Login_Unsuccessful(){
        String username = "carolina.benavides.venegas@ucreativa.com";
        String password = "asdf";
        String expectedMessage = "warning: no match for e-mail address and/or password.";

        //Go To Login Page
        driver.findElement(By.xpath("//*[@id=\"top-links\"]/ul/li[2]/a/span[1]")).click();
        driver.findElement(By.linkText("Login")).click();

        //Llenar formulario
        driver.findElement(By.name("email")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.cssSelector("[value='Login']")).click();

        WebElement alertMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-danger')]"));
        Assert.assertEquals(expectedMessage.toLowerCase(), alertMessage.getText().toLowerCase().trim());
    }


}




