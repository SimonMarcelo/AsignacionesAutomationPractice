package Edit.SauceDemo;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class Asignacion5 {

	String url = "http://saucedemo.com";
	WebDriver driver;
	File pantalla;
	String rutaEvidencia = ".\\Evidencias\\";

	@BeforeSuite
	public void abrirNavegador() throws IOException {
		driver = new ChromeDriver();
		driver.navigate().to(url);
		driver.manage().window().maximize();

	}

	@Test(description = "Login en SauceDemo", priority = 1)
	public void iniciarSesion() throws IOException {
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.name("password")).sendKeys("secret_sauce");

		// Evidencia #1
		pantalla = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(pantalla, new File(rutaEvidencia + "01_formularioLoginCompleto.jpg"));

		driver.findElement(By.xpath("//input[@id='login-button']")).click();
	}

	@Test(description = "Simulación de compra", priority = 2)
	public void anadirProductoYConfirmarOrden() throws IOException {

		driver.findElement(By.cssSelector("#add-to-cart-sauce-labs-backpack")).click();

		// Evidencia #2
		pantalla = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(pantalla, new File(rutaEvidencia + "02_productoAgregado.jpg"));

		driver.findElement(By.className("shopping_cart_link")).click();

		// Evidencia #3
		pantalla = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(pantalla, new File(rutaEvidencia + "03_carritoDeCompras.jpg"));
		driver.findElement(By.id("checkout")).click();

		// Comienza a completar el formulario
		Faker faker = new Faker();
		driver.findElement(By.cssSelector("#first-name")).sendKeys(faker.name().firstName());
		driver.findElement(By.xpath("//input[@id='last-name']")).sendKeys(faker.name().lastName());
		driver.findElement(By.id("postal-code")).sendKeys(faker.address().zipCode());

		// Evidencia #4
		pantalla = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(pantalla, new File(rutaEvidencia + "04_formularioCompleto.jpg"));

		driver.findElement(By.name("continue")).click();
		driver.findElement(By.className("cart_button")).click();

		// Espera
		WebDriverWait espera = new WebDriverWait(driver, Duration.ofSeconds(3));
		espera.until(ExpectedConditions.visibilityOfElementLocated(By.className("complete-text")));

		// Evidencia #5
		pantalla = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(pantalla, new File(rutaEvidencia + "05_odenConfirmada.jpg"));

		String urlEsperada = "https://www.saucedemo.com/checkout-complete.html";
		String tituloEsperado = "Swag Labs";
		String urlActual = driver.getCurrentUrl();
		String tituloActual = driver.getTitle();

		Assert.assertEquals(urlActual, urlEsperada);
		Assert.assertTrue(tituloActual.equals(tituloEsperado));
	}

	@AfterSuite
	public void cerrarNavegador() {
		driver.close();
	}
}
