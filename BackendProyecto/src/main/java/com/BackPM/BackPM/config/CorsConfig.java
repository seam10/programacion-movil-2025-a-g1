package com.BackPM.BackPM.config;

// Importaciones necesarias para la configuración de CORS en Spring
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.List;

@Configuration // Marca esta clase como una fuente de definiciones de beans para el contexto de Spring
public class CorsConfig {

    @Bean // Declara que el método devuelve un bean que se gestionará en el contenedor de Spring
    public CorsConfigurationSource corsConfigurationSource() {
        // Creamos una instancia de CorsConfiguration, que contiene las reglas de CORS
        CorsConfiguration configuration = new CorsConfiguration();
        
        // Permite cualquier origen (dominio) hacer peticiones a nuestra API
        configuration.setAllowedOrigins(List.of("*"));
        
        // Especifica los métodos HTTP permitidos: GET, POST, PUT, DELETE y OPTIONS
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        
        // Permite cualquier header en las solicitudes (por ejemplo Authorization, Content-Type, etc.)
        configuration.setAllowedHeaders(List.of("*"));
        
        // Indica si se permiten o no las credenciales (cookies, cabeceras de autenticación).
        // Para usar "*" en allowedOrigins debe ser false.
        configuration.setAllowCredentials(false);

        // UrlBasedCorsConfigurationSource aplica la configuración CORS a rutas específicas
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        
        // Asocia las reglas definidas arriba (“configuration”) a todos los endpoints (/**)
        source.registerCorsConfiguration("/**", configuration);
        
        // Devuelve el origen de configuración CORS para que Spring lo use
        return source;
    }
}
