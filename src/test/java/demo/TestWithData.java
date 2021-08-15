package demo;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestWithData {

    @Test(dataProvider = "numeroProvider")
    public void Suma(int numero){
        Assert.assertTrue(numero > 0);
        System.out.println("Numero: " + numero);
    }

    @DataProvider(name = "numeroProvider")
    public Object[][] methodNumeroProvider(){
        return new Object[][]{
                {5}, {6}, {-6}
        };
    }
}


/**
 *
 * Nombre Correo    Apellido
 * carolina   carobv2096@hotmail.com  Benavides
 * carolina carobv2096@...  Venegas
 *
 *
 * Numero
 * 5
 * 6
 * */