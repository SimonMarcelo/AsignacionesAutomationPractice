package Edit.SauceDemo;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utilities.CapturaEvidencia;
import Utilities.DatosExcel;

public class Asignacion6 {

	String url = "http://saucedemo.com";
	WebDriver driver;
	File pantalla;
	String rutaEvidencia = ".\\EvidenciasAsignacion6\\";
	String nombreDocumento = "EvidenciaAsignacion6.docx";

	@BeforeSuite
	public void abrirPagina() throws InvalidFormatException, IOException, InterruptedException {
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		CapturaEvidencia.escribirTituloEnDocumento(rutaEvidencia + nombreDocumento,
		"Documento de Evidencias SauceDemo", 18);
	}

	@Test(dataProvider = "Datos Orden")
	public void login(String usuario, String password, String nombre, String apellido, String codigoPostal) throws InvalidFormatException, IOException, InterruptedException {

		//EVIDENCIA#1
		CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencia + "img.jpg", rutaEvidencia + nombreDocumento, "Paso 1 - Página Inicial - Usuario: " + usuario + " - Contraseña: " + password);

		// LOGIN
		driver.findElement(By.id("user-name")).sendKeys(usuario);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.cssSelector("#login-button")).click();

		if (driver.getCurrentUrl().equalsIgnoreCase("https://www.saucedemo.com/")) { // LOGUEO INCORRECTO

			driver.get(url);

		} else { // LOGUEO CORRECTO

			driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
			driver.findElement(By.name("add-to-cart-sauce-labs-bike-light")).click();
			
			// EVIDENCIA#2
			CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencia + "img.jpg", rutaEvidencia + nombreDocumento, "Paso 2 - Elementos Seleccionados - Usuario: " + usuario + " - Contraseña: " + password);

			driver.findElement(By.className("shopping_cart_link")).click();


			WebDriverWait espera = new WebDriverWait(driver, Duration.ofSeconds(3));
			espera.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='checkout']")));

			// click en Checkout
			driver.findElement(By.xpath("//button[@id='checkout']")).click();

			// Rellenado del formulario de compra con variables en SauceDemo
			driver.findElement(By.id("first-name")).sendKeys(nombre);
			driver.findElement(By.cssSelector("#last-name")).sendKeys(apellido);
			driver.findElement(By.xpath("//input[@id='postal-code']")).sendKeys(codigoPostal);

			// EVIDENCIA#3
			CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencia + "img.jpg", rutaEvidencia + nombreDocumento, "Paso 3 - Formulario Completo - Usuario: " + usuario + " - Contraseña: " + password);

			// click en CONTINUE
			driver.findElement(By.id("continue")).click();

			// EVIDENCIA#4
			CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencia + "img.jpg", rutaEvidencia + nombreDocumento, "Paso 4 - Orden Completa - Usuario: " + usuario + " - Contraseña: " + password);

			// click en FINISH
			driver.findElement(By.cssSelector("#finish")).click();

			// EVIDENCIA#5
			CapturaEvidencia.capturarPantallaEnDocumento(driver, rutaEvidencia + "img.jpg", rutaEvidencia + nombreDocumento, "Paso 5 - Orden Finalizada - Usuario: " + usuario + " - Contraseña: " + password);

			driver.findElement(By.id("back-to-products")).click();

			// LOGOUT
			driver.findElement(By.xpath("//button[@id='react-burger-menu-btn']")).click();
			espera.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout_sidebar_link")));

			driver.findElement(By.id("logout_sidebar_link")).click();
		}

	}

	@DataProvider(name = "Datos Orden")
	public Object[][] leerDatosOrden() throws Exception {
		return DatosExcel.leerExcel(".\\Datos\\Datos_Orden.xlsx", "Hoja1");
	}

	@AfterSuite
	public void cerrarNavegador() {
		driver.close();
	}

}
