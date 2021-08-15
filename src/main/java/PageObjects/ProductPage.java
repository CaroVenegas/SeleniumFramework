package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage{

    //elementos
    public String ProductTitleSelector = "//h1[text()='<name>']";
    public By ProductQuantityInputSelector = By.id("input-quantity");
    public By AddButtonSelector = By.id("button-cart");
    public By AlertSuccess = By.cssSelector(".alert-success");

    public ProductPage(WebDriver _driver){
        super(_driver);
    }

    public boolean isTitleDisplayed(String name){
        return driver.findElement(By.xpath(ProductTitleSelector.replace("<name>", name))).isDisplayed();
    }

    public void SetQuantity(int quantity){
        driver.findElement(ProductQuantityInputSelector).clear();
        driver.findElement(ProductQuantityInputSelector).sendKeys("" + quantity);
    }

    public void clickAddButton(){
        driver.findElement(AddButtonSelector).click();
    }

    public boolean isAlertSuccessDisplayed(){
        return driver.findElement(AlertSuccess).isDisplayed();
    }

}