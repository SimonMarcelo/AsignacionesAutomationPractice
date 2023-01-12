package Edit.SauceDemo;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Asignacion2 {

	//defino la variable url
	String url = "https://www.saucedemo.com/"; 
	
	//defino una funcion para generar pausas dentro del test
	public void esperarEnSegundos(int seg) {
		try {
			Thread.sleep(seg*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void iniciarSesionEnSauceDemoConChrome() {

		//Defino el navegador a utilizar (Chrome)
		WebDriver navegador = new ChromeDriver();
		
		//Accedo al sitio definido como variable (url)
		navegador.get(url);
		
		//Defino input Usuario y escribo el texto correspondiente
		WebElement inputUsername = navegador.findElement(By.id("user-name")); 
		inputUsername.sendKeys("standard_user"); 
		
		//Defino input Password y escribo el texto correspondiente
		WebElement inputPassword = navegador.findElement(By.id("password")); 
		inputPassword.sendKeys("secret_sauce"); 
		
		//Defino el botón Login y hago click sobre él.
		WebElement botonLogin = navegador.findElement(By.id("login-button")); 
		botonLogin.click(); 
		
		//Cierro el navegador
		navegador.close();
		
	}
	
	@Test
	public void iniciarYCerrarSesionEnSauceDemoConChrome() {

		//Defino el navegador a utilizar (Chrome)
		WebDriver navegador = new ChromeDriver();
		
		//Accedo al sitio definido como variable (url)
		navegador.get(url);
		
		//Defino input Usuario y escribo el texto correspondiente
		WebElement inputUsername = navegador.findElement(By.id("user-name")); 
		inputUsername.sendKeys("standard_user"); 
		
		//Defino input Password y escribo el texto correspondiente
		WebElement inputPassword = navegador.findElement(By.id("password")); 
		inputPassword.sendKeys("secret_sauce"); 
		
		//Defino el botón Login y hago click sobre él.
		WebElement botonLogin = navegador.findElement(By.id("login-button")); 
		botonLogin.click(); 
		
		esperarEnSegundos(4); //agrego una pausa de 4 segundos
		
		//Hago click sobre el botón del menú del navegador
		WebElement botonMenu = navegador.findElement(By.id("react-burger-menu-btn")); //defino boton Login
		botonMenu.click(); 

		esperarEnSegundos(1); //agrego una pausa de 1 segundo
		
		//Hago click sobre el botón Logout
		WebElement botonLogout = navegador.findElement(By.id("logout_sidebar_link")); //defino boton Login
		botonLogout.click(); 
		
		esperarEnSegundos(1); //agrego una pausa de 1 segundo
		
		//Cierro el navegador
		navegador.close();
		
	}
}
