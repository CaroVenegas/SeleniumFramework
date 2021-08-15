package selenium;
import PageObjects.*;
import org.openqa.selenium.WebDriver;

public class PageObjectHandler {

    public WebDriver driver;

    private HeaderPage headerPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private SearchResultsPage searchResultsPage;
    private HomePage homePage;
    private ProductPage productPage;
    private ShoppingCartPage shoppingCartPage;

    public PageObjectHandler(){}

    public HeaderPage headerPage(){
        if(this.headerPage == null)
            headerPage = new HeaderPage(driver);
        return headerPage;
    }
    public LoginPage loginPage(){
        if(this.loginPage == null)
            loginPage = new LoginPage(driver);
        return loginPage;
    }
    public RegisterPage registerPage(){
        if(this.registerPage == null)
            registerPage = new RegisterPage(driver);
        return registerPage;
    }
    public SearchResultsPage searchResultsPage(){
        if(this.searchResultsPage == null)
            searchResultsPage = new SearchResultsPage(driver);
        return searchResultsPage;
    }
    public HomePage homePage(){
        if(this.homePage == null)
            homePage = new HomePage(driver);
        return homePage;
    }
    public ShoppingCartPage shoppingCartPage(){
        if(this.shoppingCartPage == null)
            shoppingCartPage = new ShoppingCartPage(driver);
        return shoppingCartPage;
    }
    public ProductPage productPage(){
        if(this.productPage == null)
            productPage = new ProductPage(driver);
        return productPage;
    }
}