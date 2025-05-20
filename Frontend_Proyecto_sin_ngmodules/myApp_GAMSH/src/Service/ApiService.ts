import axios, { AxiosInstance, AxiosResponse } from 'axios';
import { Registro } from '../types/Registro';

// Cambia esta URL si tu backend se despliega en otro lado
const API_BASE_URL = 'https://n9cdkf0m-9000.use.devtunnels.ms/api';

class ApiService {
  private axiosInstance: AxiosInstance;

  constructor() {
    this.axiosInstance = axios.create({
      baseURL: API_BASE_URL,
      headers: {
        'Content-Type': 'application/json',
      },
    }); 
  }

  // -------------------- Espacio --------------------

  resetEspacios(): Promise<AxiosResponse<any>> {
  return this.axiosInstance.put('/espacios/reset');
}

  getEspacios(): Promise<AxiosResponse<any>> {
    return this.axiosInstance.get('/espacios');
  }

  getEspacioById(id: number): Promise<AxiosResponse<any>> {
    return this.axiosInstance.get(`/espacios/${id}`);
  }

  crearEspacio(data: any): Promise<AxiosResponse<any>> {
    return this.axiosInstance.post('/espacios', data);
  }

  actualizarEspacio(id: number, data: any): Promise<AxiosResponse<any>> {
    return this.axiosInstance.put(`/espacios/${id}`, data);
  }

  eliminarEspacio(id: number): Promise<AxiosResponse<any>> {
    return this.axiosInstance.delete(`/espacios/${id}`);
  }

   // -------------------- Registro --------------------
  getRegistros(): Promise<AxiosResponse<any>> {
    return this.axiosInstance.get('/registros');
  }

  getRegistroById(id: number): Promise<AxiosResponse<any>> {
    return this.axiosInstance.get(`/registros/${id}`);
  }

  crearRegistro(data: Registro): Promise<AxiosResponse<any>> {
    return this.axiosInstance.post('/registros', data);
  }

  actualizarRegistro(id: number, data: Registro): Promise<AxiosResponse<any>> {
    return this.axiosInstance.put(`/registros/${id}`, data);
  }

  eliminarRegistro(id: number): Promise<AxiosResponse<any>> {
    return this.axiosInstance.delete(`/registros/${id}`);
  }

  // Si tu backend realmente tiene un endpoint /registros/guardar
  guardarRegistro(data: Registro): Promise<AxiosResponse<any>> {
    return this.axiosInstance.post('/registros/guardar', data);
  }

  // -------------------- Tarifa --------------------
  getTarifas(): Promise<AxiosResponse<any>> {
    return this.axiosInstance.get('/tarifas');
  }

  getTarifaById(id: number): Promise<AxiosResponse<any>> {
    return this.axiosInstance.get(`/tarifas/${id}`);
  }

  crearTarifa(data: any): Promise<AxiosResponse<any>> {
    return this.axiosInstance.post('/tarifas', data);
  }

  actualizarTarifa(id: number, data: any): Promise<AxiosResponse<any>> {
    return this.axiosInstance.put(`/tarifas/${id}`, data);
  }

  eliminarTarifa(id: number): Promise<AxiosResponse<any>> {
    return this.axiosInstance.delete(`/tarifas/${id}`);
  }

      // -------------------- Salida --------------------
  getSalidas(): Promise<AxiosResponse<any>> {
    return this.axiosInstance.get('/salidas');
  }

  getSalidaById(id: number): Promise<AxiosResponse<any>> {
    return this.axiosInstance.get(`/salidas/${id}`);
  }

  crearSalida(data: any): Promise<AxiosResponse<any>> {
    return this.axiosInstance.post('/salidas', data);
  }

  actualizarSalida(id: number, data: any): Promise<AxiosResponse<any>> {
    return this.axiosInstance.put(`/salidas/${id}`, data);
  }

  eliminarSalida(id: number): Promise<AxiosResponse<any>> {
    return this.axiosInstance.delete(`/salidas/${id}`);
  }


}

// Exportamos una instancia para que sea usada directamente
export default new ApiService();
