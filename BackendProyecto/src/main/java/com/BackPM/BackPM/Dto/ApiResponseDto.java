package com.BackPM.BackPM.Dto;

// Clase genérica para envolver las respuestas de la API con mensaje, datos y estado
public class ApiResponseDto<T> {

    // Indica si la operación fue exitosa (true) o fallida (false)
    private Boolean status;
    // Contiene los datos devueltos por la API (puede ser cualquier tipo T)
    private T data;
    // Mensaje informativo sobre el resultado de la operación
    private String message;

    /**
     * Constructor vacío necesario para frameworks de deserialización (Jackson, Gson, etc.)
     */
    public ApiResponseDto() {
    }

    /**
     * Constructor principal para crear la respuesta de la API con todos sus campos.
     *
     * @param message Texto descriptivo del resultado (por ejemplo: "Datos guardados")
     * @param data    Objeto devuelto por la API (puede ser entidad, lista, etc.)
     * @param status  Estado de la operación (true = éxito, false = error)
     */
    public ApiResponseDto(String message, T data, Boolean status) {
        this.message = message; // Asigna el mensaje de la respuesta
        this.data = data;       // Asigna los datos de la respuesta
        this.status = status;   // Asigna el estado de éxito/fracaso
    }

    /**
     * @return true si la operación fue exitosa; false en caso contrario
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * Permite establecer el estado de la operación.
     *
     * @param status true para éxito; false para error
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * @return los datos devueltos por la API (tipo genérico T)
     */
    public T getData() {
        return data;
    }

    /**
     * Permite establecer los datos de la respuesta.
     *
     * @param data Objeto de tipo T con la información a devolver
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * @return mensaje descriptivo sobre el resultado de la operación
     */
    public String getMessage() {
        return message;
    }

    /**
     * Permite establecer el mensaje descriptivo de la respuesta.
     *
     * @param message Texto explicativo o de error
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
//     * @param entity objeto JSON con los datos a guardar
//     * @return ResponseEntity con el resultado de la operación
//     */