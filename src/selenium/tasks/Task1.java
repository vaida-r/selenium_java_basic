package selenium.tasks;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.Locale;


import static org.junit.Assert.assertEquals;

public class Task1 {
    WebDriver driver;

    @Before
    public void openPage() {

        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        //System.setProperty("webdriver.chrome.driver","C:\\Users\\Vaida\\Desktop\\bselenium\\selenium_java_basic\\lib\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/enter_a_number");
    }

    @After
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void errorOnText() throws InterruptedException {
//        TODO
        String enterText = "kazkas";
//        enter a text instead of a number, check that correct error is seen
        WebElement number = driver.findElement(By.id("numb"));
        number.sendKeys(enterText);
        driver.findElement(By.tagName("button")).click();
        String errMessage = driver.findElement(By.id("ch1_error")).getText();
        assertEquals("Please enter a number", errMessage );

    }

    @Test
    public void errorOnNumberTooSmall() {
//        TODO
//        enter number which is too small (below 50), check that correct error is seen
        String enterText = "40";
//        enter a text instead of a number, check that correct error is seen
        WebElement number = driver.findElement(By.id("numb"));
        number.sendKeys(enterText);
        driver.findElement(By.tagName("button")).click();
        String errMessage = driver.findElement(By.id("ch1_error")).getText();
        assertEquals("Number is too small", errMessage );

    }

    @Test
    public void errorOnNumberTooBig() {

//        BUG: if I enter number 666 no errors where seen
//        TODO
//        enter number which is too big (above 100), check that correct error is seen
        String enterText = "250";
//        enter a text instead of a number, check that correct error is seen
        WebElement number = driver.findElement(By.id("numb"));
        number.sendKeys(enterText);
        driver.findElement(By.tagName("button")).click();
        String errMessage = driver.findElement(By.id("ch1_error")).getText();
        assertEquals("Number is too big", errMessage );

    }

    @Test
    public void correctSquareRootWithoutRemainder() {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 2 is square root of 4),
//        then and press submit and check that correct no error is seen and check that square root is calculated correctly
        String enterText = "64";
        int testNumber = 64;
//        enter a text instead of a number, check that correct error is seen

        WebElement number = driver.findElement(By.id("numb"));
        number.sendKeys(enterText);
        driver.findElement(By.tagName("button")).click();

        String expMessage = String.format(Locale.US, "Square root of %d is %.2f",testNumber,Math.sqrt(testNumber));

        Alert alert = driver.switchTo().alert();
        String actMessage = alert.getText();
        alert.accept();

        assertEquals(expMessage, actMessage );

    }



    @Test
    public void correctSquareRootWithRemainder() {
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 1.732.. is square root of 3) and press submit,
//        then check that correct no error is seen and check that square root is calculated correctly
        String enterText = "70";
        int testNumber = 70;
//        enter a text instead of a number, check that correct error is seen

        WebElement number = driver.findElement(By.id("numb"));
        number.sendKeys(enterText);
        driver.findElement(By.tagName("button")).click();

        String expMessage = String.format(Locale.US, "Square root of %d is %.2f",testNumber,Math.sqrt(testNumber));

        Alert alert = driver.switchTo().alert();
        String actMessage = alert.getText();
        alert.accept();

        assertEquals(expMessage, actMessage );

    }

    @Test
    public void correctSquareRootForAllAllowedNumbers() {       // Sergey's suggestion
//        TODO
//        enter a number between 50 and 100 digit in the input (square root of which doesn't have a remainder, e.g. 1.732.. is square root of 3) and press submit,
//        then check that correct no error is seen and check that square root is calculated correctly
        for (int i =50; i<=100; i++) {

            int testNumber = i;

            WebElement number = driver.findElement(By.id("numb"));
            number.sendKeys(String.valueOf(testNumber));
            driver.findElement(By.tagName("button")).click();

            String expMessage = String.format(Locale.US, "Square root of %d is %.2f", testNumber, Math.sqrt(testNumber));

            Alert alert = driver.switchTo().alert();
            String actMessage = alert.getText();
            alert.accept();

            assertEquals(expMessage, actMessage);

        }
    }





}
