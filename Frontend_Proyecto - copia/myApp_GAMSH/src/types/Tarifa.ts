export interface TarifaType {
  id?: number;            // ID opcional de la tarifa, asignado por la base de datos (heredado de ABaseEntity)
  tipoVehiculo: string;   // Tipo de vehículo al que aplica la tarifa (por ejemplo, "auto", "moto")
  unidadTiempo: string;   // Unidad de tiempo para el cobro (por ejemplo, "minuto", "hora", "día")
  valor: number;          // Monto numérico que se cobra por la unidad de tiempo indicada
  status: boolean;        // Indicador de estado: true si la tarifa está activa, false si está deshabilitada
}
