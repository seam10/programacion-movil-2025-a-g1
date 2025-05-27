// Define un tipo literal llamado EstadoEspacio que solo puede ser uno de estos tres valores
export type EstadoEspacio =
  | 'DISPONIBLE'  // El espacio está libre
  | 'OCUPADO'     // El espacio está actualmente en uso
  | 'RESERVADO';  // El espacio ha sido apartado para uso futuro

// Define la forma de un objeto Espacio tal como lo usa la aplicación
export interface Espacio {
  id?: number;          // ID opcional (generado por la base de datos, heredado de ABaseEntity)
  estado: EstadoEspacio; // Campo obligatorio que indica el estado actual del espacio
  status: true         // Campo booleano fijo en true para indicar que el registro está activo
}
