export interface SalidaType {
  id?: number;
  registro: { id: number };        // Referencia al registro del vehículo
  espacio: { id: number };         // Referencia al espacio
  tarifa: { id: number };          // Referencia a la tarifa aplicada
  horaSalida: string;              // Hora de salida en formato ISO string
  totalCalculado: number;          // Total calculado
  status?: boolean;                // Estado opcional si lo manejas en el backend
}
