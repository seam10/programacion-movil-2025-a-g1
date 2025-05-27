package com.BackPM.BackPM.config;

// Importación necesaria para ejecutar lógica al arrancar la aplicación
import org.springframework.boot.CommandLineRunner;
// Importación para declarar esta clase como configuración de Spring
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// Permite acceder a propiedades de entorno (aunque en este ejemplo no se usa directamente)
import org.springframework.core.env.Environment;

// Para obtener conexiones JDBC desde el pool de DataSource
import javax.sql.DataSource;
// Clases JDBC para ejecutar sentencias SQL
import java.sql.Connection;
import java.sql.Statement;

@Configuration // Marca la clase como contenedor de beans de configuración de Spring
public class DatabaseInitializer {

    // Constructor inyectando Environment (puede usarse para leer propiedades)
    public DatabaseInitializer(Environment environment) {
        // Por ahora no se usa el objeto environment, pero queda disponible si se necesita
    }

    @Bean // Declara que este método provee un bean gestionado por Spring
    CommandLineRunner initDatabase(DataSource dataSource) {
        // CommandLineRunner se ejecuta justo después de iniciar el contexto de Spring
        return args -> {
            // Definimos el nombre de la base de datos a crear o usar
            String dbName = "BackMovil";

            // try-with-resources cierra automáticamente Connection y Statement al finalizar
            try (Connection connection = dataSource.getConnection();
                 Statement statement = connection.createStatement()) {

                // Consulta para verificar si ya existe una base de datos con ese nombre
                String checkDb =
                    "SELECT 1 FROM pg_database WHERE datname = '" + dbName + "';";
                var resultSet = statement.executeQuery(checkDb);

                if (!resultSet.next()) {
                    // Si no hay resultados, la base de datos no existe -> crearla
                    statement.executeUpdate("CREATE DATABASE \"" + dbName + "\";");
                    System.out.println("✅ Base de datos creada: " + dbName);
                } else {
                    // Si ya existe, informar al usuario
                    System.out.println("🔎 La base de datos ya existe: " + dbName);
                }

                // Construimos la URL de conexión apuntando a la base de datos creada
                String newDbUrl = "jdbc:postgresql://localhost:5432/" + dbName;
                // Actualizamos la propiedad de Spring para que use la nueva URL
                System.setProperty("spring.datasource.url", newDbUrl);

                // Mostrar en consola la URL final de conexión
                System.out.println("🔗 Conectado a: " + newDbUrl);

            } catch (Exception e) {
                // En caso de error, imprimir la traza y lanzar una RuntimeException
                e.printStackTrace();
                throw new RuntimeException(
                    "❌ Error al crear o conectar a la base de datos", e);
            }
        };
    }
}
// Este código es un ejemplo de cómo inicializar una base de datos PostgreSQL al arrancar una aplicación Spring Boot.
// Se encarga de verificar si la base de datos existe y, si no, crearla.