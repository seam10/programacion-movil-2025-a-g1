package com.BackPM.BackPM;
// Define el paquete base de la aplicación, coincide con la estructura de carpetas

// Importa la clase principal para arrancar aplicaciones Spring Boot
import org.springframework.boot.SpringApplication;
// Importa la anotación que habilita la configuración automática de Spring Boot
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Importa anotaciones de Swagger/OpenAPI para documentar la API
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
    // Define metadatos generales de la API para OpenAPI/Swagger
	info = @Info(
        title = "BackendPM API",   // Título que se mostrará en la documentación
        version = "v1"             // Versión de la API
    ),
	servers = {
        // Lista de servidores donde está desplegada la API
		@Server(
            url = "https://n9cdkf0m-9000.use.devtunnels.ms"
        )
	}
)

@SpringBootApplication
// Habilita configuración automática, escaneo de componentes y resolución de beans
public class BackendPmApplication {

    public static void main(String[] args) {
        // Método principal que arranca la aplicación Spring Boot
        SpringApplication.run(
            BackendPmApplication.class,  // Clase de configuración principal
            args                         // Argumentos de línea de comandos
        );
    }

}
