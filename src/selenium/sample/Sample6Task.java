package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import static org.junit.Assert.assertEquals;

public class Sample6Task {
    WebDriver driver;

    // method which is being run before each test
    @Before
    public void startingTests() throws Exception {
        // from Sample 1:
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        // declaration above:
        driver = new ChromeDriver();
        //open page:
        driver.get("https://kristinek.github.io/site/examples/locators");
    }

    // method which is being run after each test
    @After
    public void endingTests() throws Exception {
        driver.close();
    }

    @Test
    public void findElementByXPath() throws Exception {
//         TODO:
//        2 ways to find text: "Heading 2 text":
        WebElement element = driver.findElement(By.xpath("//*[@id=\"heading_2\"]"));
        System.out.println(" text is: " + element.getText());

        String heading2 = "Heading 2 text";
        element = driver.findElement(By.xpath("//h2[contains(text(),'" + heading2 +"')]"));
        System.out.println(" text is: " + element.getText());

        //        1-2 ways to find text: "Test Text 1"
        element = driver.findElement(By.xpath("//*[@id=\"test1\"]/p[1]"));
        System.out.println(" text is: " + element.getText());
//        1-2 ways to find text: "Test Text 2"
        element = driver.findElement(By.xpath("//*[@id=\"test1\"]/p[2]"));  // sita galima parasyti dar kitaip, nes turi kita class pavadinima
        System.out.println(" text is: " + element.getText());


//        1-2 ways to find text: "Test Text 3"
//        1-2 ways to find text: "Test Text 4"
//        1-2 ways to find text: "Test Text 5"
//        1-2 ways to find text: "This is also a button"
        element = driver.findElement(By.xpath("//*[@id=\"buttonId\"]"));
        System.out.println(" text is: " + element.getAttribute("value"));
    }

    @Test
    public void findElementByCssName() throws Exception {
//         TODO:
//        1-2 ways to find text: "Heading 2 text"
        String text = "Heading 2 text";
        WebElement element = driver.findElement(By.cssSelector("#heading_2"));   // also i can write (By.cssSelector("#heading_2") to more specify element
        System.out.println(" text by css is: " + element.getText());
        assertEquals(text,element.getText());

//        1-2 ways to find text: "Test Text 1"
        text ="Test Text 1";
        element = driver.findElement(By.cssSelector("#test1 > p.test"));
        System.out.println(" text by css is: " + element.getText());
        assertEquals(text,element.getText());

//        1-2 ways to find text: "Test Text 2"
        element = driver.findElement(By.cssSelector(".twotest"));
        System.out.println(" text by css is: " + element.getText());
//        1-2 ways to find text: "Test Text 3"
        element = driver.findElement(By.cssSelector("#test3 > p:nth-child(1)"));
        System.out.println(" text by css is: " + element.getText());
        element = driver.findElements(By.cssSelector("#test3 > .test")).get(0);
        System.out.println(" text by css is: " + element.getText());
//        1-2 ways to find text: "This is also a button"
        element = driver.findElement(By.cssSelector("#buttonId"));
        System.out.println(" text by css is: " + element.getAttribute("value"));

        element = driver.findElement(By.cssSelector("[name=\"randomButton2\"]"));
        System.out.println(" text by css is: " + element.getAttribute("value"));
    }
}
