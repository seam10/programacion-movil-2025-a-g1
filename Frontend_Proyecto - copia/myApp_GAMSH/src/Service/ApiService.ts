import axios, { AxiosInstance, AxiosResponse } from 'axios';
// Importa la librería axios para hacer llamadas HTTP,
// y los tipos AxiosInstance y AxiosResponse de TypeScript.

import { Registro } from '../types/Registro';
// Importa la definición de tipo para los objetos Registro.


// URL base de la API; intenta leer la variable de entorno VITE_API_BASE_URL,
// o usa por defecto la dirección de tu túnel DevTunnels.
const API_BASE_URL = import.meta.env.VITE_API_BASE_URL
  || 'https://n9cdkf0m-9000.use.devtunnels.ms/api';

class ApiService {
  private axiosInstance: AxiosInstance;
  // Declara una propiedad privada que contendrá la instancia de Axios configurada.

  constructor() {
    this.axiosInstance = axios.create({
      baseURL: API_BASE_URL,
      // Establece la URL base para todas las peticiones HTTP.
      headers: {
        'Content-Type': 'application/json',
        // Define que los cuerpos de las peticiones serán JSON.
      },
    });
  }

  // -------------------- Espacio --------------------

  resetEspacios(): Promise<AxiosResponse<any>> {
    // Método para resetear todos los espacios (PUT /espacios/reset).
    return this.axiosInstance.put('/espacios/reset');
  }

  getEspacios(): Promise<AxiosResponse<any>> {
    // GET /espacios: obtiene la lista de todos los espacios.
    return this.axiosInstance.get('/espacios');
  }

  getEspacioById(id: number): Promise<AxiosResponse<any>> {
    // GET /espacios/{id}: obtiene un espacio específico por su ID.
    return this.axiosInstance.get(`/espacios/${id}`);
  }

  crearEspacio(data: any): Promise<AxiosResponse<any>> {
    // POST /espacios: crea un nuevo espacio, enviando los datos en el body.
    return this.axiosInstance.post('/espacios', data);
  }

  actualizarEspacio(id: number, data: any): Promise<AxiosResponse<any>> {
    // PUT /espacios/{id}: actualiza el espacio con ID dado.
    return this.axiosInstance.put(`/espacios/${id}`, data);
  }

  eliminarEspacio(id: number): Promise<AxiosResponse<any>> {
    // DELETE /espacios/{id}: elimina el espacio con ID dado.
    return this.axiosInstance.delete(`/espacios/${id}`);
  }

  // -------------------- Registro --------------------

  getRegistros(): Promise<AxiosResponse<any>> {
    // GET /registros: obtiene todos los registros.
    return this.axiosInstance.get('/registros');
  }

  getRegistroById(id: number): Promise<AxiosResponse<any>> {
    // GET /registros/{id}: obtiene un registro por su ID.
    return this.axiosInstance.get(`/registros/${id}`);
  }

  crearRegistro(data: Registro): Promise<AxiosResponse<any>> {
    // POST /registros: crea un nuevo registro con la estructura Registro.
    return this.axiosInstance.post('/registros', data);
  }

  actualizarRegistro(id: number, data: Registro): Promise<AxiosResponse<any>> {
    // PUT /registros/{id}: actualiza un registro existente.
    return this.axiosInstance.put(`/registros/${id}`, data);
  }

  eliminarRegistro(id: number): Promise<AxiosResponse<any>> {
    // DELETE /registros/{id}: elimina un registro por su ID.
    return this.axiosInstance.delete(`/registros/${id}`);
  }

  // Si tu backend tiene un endpoint POST /registros/guardar:
  guardarRegistro(data: Registro): Promise<AxiosResponse<any>> {
    // POST /registros/guardar: guarda un registro en un endpoint personalizado.
    return this.axiosInstance.post('/registros/guardar', data);
  }

  // -------------------- Tarifa --------------------

  getTarifas(): Promise<AxiosResponse<any>> {
    // GET /tarifas: obtiene todas las tarifas.
    return this.axiosInstance.get('/tarifas');
  }

  getTarifaById(id: number): Promise<AxiosResponse<any>> {
    // GET /tarifas/{id}: obtiene una tarifa por su ID.
    return this.axiosInstance.get(`/tarifas/${id}`);
  }

  crearTarifa(data: any): Promise<AxiosResponse<any>> {
    // POST /tarifas: crea una nueva tarifa.
    return this.axiosInstance.post('/tarifas', data);
  }

  actualizarTarifa(id: number, data: any): Promise<AxiosResponse<any>> {
    // PUT /tarifas/{id}: actualiza la tarifa con ID dado.
    return this.axiosInstance.put(`/tarifas/${id}`, data);
  }

  eliminarTarifa(id: number): Promise<AxiosResponse<any>> {
    // DELETE /tarifas/{id}: elimina la tarifa con ID dado.
    return this.axiosInstance.delete(`/tarifas/${id}`);
  }

  // -------------------- Salida --------------------

  getSalidas(): Promise<AxiosResponse<any>> {
    // GET /salidas: obtiene todas las salidas.
    return this.axiosInstance.get('/salidas');
  }

  getSalidaById(id: number): Promise<AxiosResponse<any>> {
    // GET /salidas/{id}: obtiene una salida por su ID.
    return this.axiosInstance.get(`/salidas/${id}`);
  }

  crearSalida(data: any): Promise<AxiosResponse<any>> {
    // POST /salidas: registra una nueva salida.
    return this.axiosInstance.post('/salidas', data);
  }

  actualizarSalida(id: number, data: any): Promise<AxiosResponse<any>> {
    // PUT /salidas/{id}: actualiza una salida existente.
    return this.axiosInstance.put(`/salidas/${id}`, data);
  }

  eliminarSalida(id: number): Promise<AxiosResponse<any>> {
    // DELETE /salidas/{id}: elimina una salida por su ID.
    return this.axiosInstance.delete(`/salidas/${id}`);
  }
}

// Exporta una única instancia de ApiService para usarla en toda la app
export default new ApiService();
// Esto permite que la misma configuración de Axios se reutilice en diferentes partes de la aplicación.
// Así, no es necesario crear una nueva instancia cada vez que se necesite hacer una llamada a la API.