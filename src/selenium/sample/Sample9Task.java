package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.support.PageFactory;
import selenium.pages.LoadingColor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.StaleElementReferenceException;



public class Sample9Task {
    WebDriver driver;
    static LoadingColor loadingColor;

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/examples/loading_color");
    }

    @After
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void loadGreenSleep() throws Exception {
//         TODO:
//         * 1) click on start loading green button
        driver.findElement(By.id("start_green")).click();
//         * 2) check that button does not appear,
//         * but loading text is seen instead   "Loading green..."
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertTrue(driver.findElement(By.id("loading_green")).isDisplayed());
        assertEquals("Loading green...",driver.findElement(By.id("loading_green")).getText());

        Thread.sleep(5000);
//         * 3) check that both button and loading text is not seen,
//         * success is seen instead "Green Loaded"
        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());
        assertTrue(driver.findElement(By.id("finish_green")).isDisplayed());
        assertEquals("Green Loaded",driver.findElement(By.id("finish_green")).getText());

    }

    @Test
    public void loadGreenSleepWithPageObjects() throws Exception {

        loadingColor = PageFactory.initElements(driver, LoadingColor.class);
            //driver.findElement(By.id("start_green")).click();
        loadingColor.clickStartGreen();
            //assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertFalse(loadingColor.isStartGreenDisplayed());
            //assertTrue(driver.findElement(By.id("loading_green")).isDisplayed());
        assertTrue(loadingColor.isLoadingGreenDisplayed());
            //assertEquals("Loading green...",driver.findElement(By.id("loading_green")).getText());
        assertEquals("Loading green...",loadingColor.getTextLoadingGreen());
        Thread.sleep(5000);
            //assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertFalse(loadingColor.isStartGreenDisplayed());
            //assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());
        assertFalse(loadingColor.isLoadingGreenDisplayed());
            //assertTrue(driver.findElement(By.id("finish_green")).isDisplayed());
        assertTrue(loadingColor.isFinishGreenDisplayed());
            //assertEquals("Green Loaded",driver.findElement(By.id("finish_green")).getText());
        assertEquals("Green Loaded",loadingColor.getTextFinishGreen());

    }





    @Test
    public void loadGreenImplicit() throws Exception {
//         TODO:
//         * 1) click on start loading green button
//         * 2) check that button does not appear,
//         * but loading text is seen instead   "Loading green..."
//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"

        driver.findElement(By.id("start_green")).click();

        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertTrue(driver.findElement(By.id("loading_green")).isDisplayed());
        assertEquals("Loading green...",driver.findElement(By.id("loading_green")).getText());

        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        driver.findElement(By.id("finish_green"));

                // implicity reikia deti bet kur, kur laukiama ivykio
                // jei bus padetas pradzioje, tai veiks visa koda (siuo atveju tinka)

        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());
        assertTrue(driver.findElement(By.id("finish_green")).isDisplayed());
        assertEquals("Green Loaded",driver.findElement(By.id("finish_green")).getText());

    }

    @Test
    public void loadGreenExplicitWait() throws Exception {
//         TODO:
//         * 1) click on start loading green button
//         * 2) check that button does not appear,
//         * but loading text is seen instead   "Loading green..."
//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"
        WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, 10).ignoring(StaleElementReferenceException.class);

        driver.findElement(By.id("start_green")).click();

        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertTrue(driver.findElement(By.id("loading_green")).isDisplayed());
        assertEquals("Loading green...",driver.findElement(By.id("loading_green")).getText());

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("finish_green")));
                // explicity reikia deti ten, kur reikia sulaukti ivykio rezultato


        assertFalse(driver.findElement(By.id("start_green")).isDisplayed());
        assertFalse(driver.findElement(By.id("loading_green")).isDisplayed());
        assertTrue(driver.findElement(By.id("finish_green")).isDisplayed());
        assertEquals("Green Loaded",driver.findElement(By.id("finish_green")).getText());


    }

    @Test
    public void loadGreenAndBlueBonus() {
        /* TODO:
         * 0) wait until button to load green and blue appears
         * 1) click on start loading green and blue button
         * 2) check that button does not appear, but loading text is seen instead for green
         * 3) check that button does not appear, but loading text is seen instead for green and blue
         * 4) check that button and loading green does not appear,
         * 		but loading text is seen instead for blue and success for green is seen
         * 5) check that both button and loading text is not seen, success is seen instead
         */
    }

}