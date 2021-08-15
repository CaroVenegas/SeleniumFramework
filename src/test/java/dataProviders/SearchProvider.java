package dataProviders;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;
import pojo.SearchData;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class SearchProvider {
    @DataProvider(name = "getSearchData")
    private Object[][] getSearchData(){
        return new Object[][]{
                {new SearchData("macbook", 3)},
                {new SearchData("Star Wars", 0)}
        };
    }

    @DataProvider(name = "getSearchData1")
    private Object[][] getSearchData1(){
        return new Object[][]{
                {new SearchData("macbook", 3)}
        };
    }

    @DataProvider(name = "getSearchDataFromJson")
    private Object[][] getSearchDataFromJson() throws FileNotFoundException {
        JsonElement jsonData = new JsonParser().parse(new FileReader("src/test/resources/testData/search.json"));
        JsonElement dataSet = jsonData.getAsJsonObject().get("dataSet");
        List<SearchData> testData = new Gson().fromJson(dataSet, new TypeToken<List<SearchData>>() {}.getType());
        Object[][] returnValue = new Object[testData.size()][1];
        int index = 0;
        for (Object[] each : returnValue) {
            each[0] = testData.get(index++);
        }
        return returnValue;
    }
}