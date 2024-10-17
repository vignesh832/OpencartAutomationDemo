package BaseTestpack;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseTest {
	public static WebDriver driver;
	public Logger logging;
	public FileInputStream file;
	public Properties data;
	
	
	@Parameters({"browser","os"})
	@BeforeClass(groups={"Master","Sanity","Regression","DDT"})
	public void setup(String br,String os) throws IOException {
		//for getLogger method have to pass the class which we need to get the logs(not 100% sure).
		//logging object is ready to use.
		logging = LogManager.getLogger(this.getClass());
		
		switch(br.toLowerCase()) {
		case "chrome" : driver = new ChromeDriver(); break;
		case "edge" : driver = new EdgeDriver(); break;
		case "firefox" : System.out.println("firefox initiated!"); driver = new FirefoxDriver(); break;
		default : logging.warn("Driver initiation failed!"); 
		}
		
		logging.info(br+" Driver initiated!");
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		data= new Properties();
		file = new FileInputStream("./src/test/resources/data.properties");
		data.load(file);
	}
	@AfterClass(groups={"Master","Sanity","Regression","DDT"})
	public void flush() {
		driver.quit();
	}

	public String randomAlphabetic(int Count) {
		return RandomStringUtils.randomAlphabetic(Count);
	}
	public String randomNumeric(int Count) {
		return RandomStringUtils.randomNumeric(Count);
	}
	public String randomAlphaNumeric(int Count) {
		return RandomStringUtils.randomAlphanumeric(Count);
	}
	
	public String takeScreenshot(String name) {
		
		TakesScreenshot sc= (TakesScreenshot)driver;
		File ss=sc.getScreenshotAs(OutputType.FILE);
		
		String imgPath= System.getProperty("user.dir")+"/screenshots/"+name+"_"+ new SimpleDateFormat("yyyy.MM.dd,hh:mm:ss").format(new Date())+".png";
		File imgFile= new File(imgPath);
		ss.renameTo(imgFile);
		return imgPath;
	}
}
