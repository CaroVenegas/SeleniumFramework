package selenium;

import PageObjects.*;
import PageObjects.BaseClass;
import dataProviders.SearchProvider;
import dataProviders.UsersProvider;
import io.qameta.allure.Description;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.*;
import pojo.UserAccount;

public class TestAccount extends BaseClass {
    public static final String ERROR_EMAIL_AND_PASSWORD_INVALID_MESSAGE = "warning: no match for e-mail address and/or password.";

    //elements
    public By logOutButtonLocator = By.linkText("Logout");
    public By alertMessageLocator = By.xpath("//div[contains(@class, 'alert-danger')]");

    @Description("Validate test login was successful")
    @Test(description = "Test Login Success")
    public void Test_Login_Successful(){
        String username = "carobv2096@ucreativa.com";
        String password = "hondaeg92";

        //Go To Login Page
        headerPage().clickOnMyAccount();
        headerPage().clickOnLoginButton();

        /*
        EJEMPLO DE LISTAS Y WEBELEMENTS SOLOS
        WebElement WishList = driver.findElement(By.linkText("Wish List"));
        ArrayList<> WishListList = driver.findElements(By.linkText("Wish List"));
        */

        //Llenar formulario
        loginPage().EnterEmail(username);
        loginPage().EnterPassword(password);
        loginPage().ClickSubmitButton();

        WebElement logOutButton = driver.findElement(logOutButtonLocator);
        Assert.assertTrue(logOutButton.isDisplayed());
    }

    @Description("Validate that the login is working with non valid credentials")
    @Test(description = "Test Login Not Success")
    public void Test_Login_Unsuccessful(){
        String username = "carobv2096@ucreativa.com";
        String password = "hondaeg92";
        String expectedMessage = "warning: no match for e-mail address and/or password.";

        loginPage().GoTo();
        loginPage().login(username, password);

        WebElement alertMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-danger')]"));
        Assert.assertEquals(expectedMessage.toLowerCase(), alertMessage.getText().toLowerCase().trim());
    }

    @Test (dataProvider = "getUsersData", dataProviderClass = UsersProvider.class)
    public void Test_Login_With_Data(UserAccount testUser){
        LoginPage loginPage = new LoginPage(driver);

        loginPage.GoTo();
        loginPage.login(testUser.getEmail(), testUser.getPassword());

        if(testUser.isValidAccount())
            Assert.assertTrue(driver.findElement(logOutButtonLocator).isDisplayed());
        else
            Assert.assertEquals(ERROR_EMAIL_AND_PASSWORD_INVALID_MESSAGE.toLowerCase(), driver.findElement(alertMessageLocator).getText().toLowerCase().trim());
    }

    @Test
    public void Test_Create_New_Account(){
        //SETUP
        String firstName = "carolina";
        String lastName = "benavides";
        String email = Utils.generateRandomEmail();
        String telephone = "85271911";
        String password = "hondaeg92";
        String expectedMessage = "Your Account Has Been Created!";

        //RUN

        registerPage().GoTo();
        registerPage().FillForm(firstName, lastName, email, telephone, password);

        //VALIDATION
        Assert.assertEquals(registerPage().GetConfirmationMessage(), expectedMessage);
    }

    @Test
    public void Test_Duplicated_Email(){

    }
    @Description("Check the price of a product in different currencies.")
    @Test(dataProvider = "getProductPricesDataFromJson", dataProviderClass = ProductPricesProvider.class)
    public void Test_Different_Currency_Values(ProductsPrices testProduct, int quantity) {

        HomePage homePage =new HomePage(driver);
        homePage.searchforProduct(testProduct.getProduct());
        ProductPage productPage= new ProductPage(driver);
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        String name = homePage.selectFirstProductAndGetName();
        productPage.SetQuantity(quantity);
        productPage.clickAddButton();
        headerPage().clickOnCartButton();
        headerPage().changeCurrencyToDollar();
        String dollarTotalAmount = shoppingCartPage.catchProductPriceOnShoppingCart();
        headerPage().changeCurrencyToEuro();
        String euroTotalAmount =shoppingCartPage.catchProductPriceOnShoppingCart();
        String poundTotalAmount=shoppingCartPage.catchProductPriceOnShoppingCart();

        double dolar = Utils.returnDouble(dollarTotalAmount);
        double dollar = Utils.returnDouble(dollarTotalAmount);
        double euro = Utils.returnDouble(euroTotalAmount);
        double pound = Utils.returnDouble(poundTotalAmount);
        Assert.assertEquals(dolar,testProduct.getDolarsPrice());
        Assert.assertEquals(dollar,testProduct.getDolarsPrice());
        Assert.assertEquals(euro,testProduct.getEuroPrice());
        Assert.assertEquals(pound,testProduct.getPoundsPrice());
    }

    private class ProductsPrices {
        public double getDolarsPrice() {
            return 0;
        }

        public double getEuroPrice() {
            return 0;
        }

        public double getPoundsPrice() {
            return 0;
        }

        public void getProduct() {
        }
    }

    private class ProductPricesProvider {
    }
}

