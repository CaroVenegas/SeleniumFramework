package selenium;

import PageObjects.BaseClass;
import PageObjects.SearchResultsPage;
import dataProviders.SearchProvider;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pojo.SearchData;

public class TestSearch extends BaseClass {

    @Test
    @Parameters({"searchCriteria", "expectedResult"})
    public void Validate_Search(@Optional("macbook") String searchCriteria, @Optional("3") String expectedResult){
        int results = Integer.parseInt(expectedResult);

        WebElement searchInput = driver.findElement(By.name("search"));
        searchInput.sendKeys(searchCriteria, Keys.ENTER);

        Assert.assertTrue(driver.getCurrentUrl().contains("search="+searchCriteria));

        // Assert.assertEquals(results.size(), expectedResult,
        //        String.format("Expecting %s results, but got %s", expectedResult, results.size()));

        Assert.assertEquals(getResults(), results,
                "Expecting " + expectedResult + " results, but got " + getResults());
    }

    @Test
    public void Test_Empty_Results(){
        String searchCriteria = "Star Wars";
        int expectedResult = 0;

        WebElement searchInput = driver.findElement(By.name("search"));
        searchInput.sendKeys(searchCriteria, Keys.ENTER);

        //Assert.assertTrue(driver.getCurrentUrl().contains("search="+searchCriteria));

        //Assert.assertEquals(results.size(), expectedResult,
        //        String.format("Expecting %s results, but got %s", expectedResult, results.size()));

        Assert.assertEquals(getResults(), expectedResult,
                "Expecting " + expectedResult + " results, but got " + getResults());
    }

    public int getResults(){
        return driver.findElements(By.cssSelector(".product-thumb")).size();
    }

    @Test (dataProvider = "getSearchDataFromJson", dataProviderClass = SearchProvider.class)
    public void Test_Search_WithData(SearchData testData){
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        WebElement searchInput = driver.findElement(By.name("search"));
        searchInput.sendKeys(testData.getSearchCriteria());

        driver.findElement(By.xpath("//div[@id='search']/span/button")).click();

        if(testData.getExpectedResults() > 0){
            Assert.assertEquals(searchResultsPage.getResultsCount(), testData.getExpectedResults());
        }
        else{
            Assert.assertTrue(searchResultsPage.isNoResultsVisible());
        }
    }

    /*
    @Attachment(value = "TestData", type = "text/plain", fileExtension = ".txt")
    public byte[] PrintTestData(){
        try {
            //File file = new File();
            //file.
            //return "Search Criteria used: " + tesData[0] + ", Expected results: " + tesData[1];
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }*/

    /**
     * String = "Juan"
     *
     * String[] = ["Juan", "Pablo", "Piedra"]
     *
     *
     * String[][]
     * Nombre Apellido Correo
     * Juaun    Piedra  juan@piedra
     *
     * */
}