package Edit.SauceDemo;

import org.testng.annotations.Test;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AsignacionClase4 {
	
	String url = "http://automationpractice.pl";
	
	@Test
	public void generarUsuario() {
		
		WebDriver driver = new ChromeDriver();
		driver.navigate().to(url);
		driver.manage().window().maximize();
		
		driver.findElement(By.linkText("Sign in")).click();
		
		WebDriverWait espera = new WebDriverWait (driver, Duration.ofSeconds(3));
		espera.until(ExpectedConditions.visibilityOfElementLocated(By.id("email_create")));
		
		//Inicio del proceso de Registro
		
		driver.findElement(By.id("email_create")).sendKeys("prueba1@testing.com");
		
		driver.findElement(By.name("SubmitCreate")).click();
		
		espera.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#id_gender1")));

		//Comienza a completarse el formulario
		
		driver.findElement(By.cssSelector("#id_gender1")).click();
		driver.findElement(By.xpath("//input[@id='customer_firstname']")).sendKeys("Marcelo");
		driver.findElement(By.id("customer_lastname")).sendKeys("Simon");
		driver.findElement(By.name("passwd")).sendKeys("abc123");
		
		//Fecha de nacimiento (14-ene-1986)
		
		Select listaDia = new Select(driver.findElement(By.name("days")));
		listaDia.selectByVisibleText("14 ");
		
		Select listaMes = new Select(driver.findElement(By.id("months")));
		listaMes.selectByIndex(1);
		
		Select listaAno = new Select(driver.findElement(By.xpath("//select[@id='years']")));
		listaAno.selectByValue("1986");
		
		//Hago check solamente en el segundo checkbox
		
		driver.findElement(By.name("optin")).click();
		
		//Submit
		
		driver.findElement(By.id("submitAccount")).click();
		
	}

}
