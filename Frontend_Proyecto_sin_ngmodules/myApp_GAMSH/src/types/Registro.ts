export interface Registro {
  id?: number;
  espacio: {
    id: number;
  };
  placaVehiculo: string;
  horaIngreso: string; // LocalDateTime en formato ISO
   status?: boolean;    // Opcional según tu backend
} 