export type EstadoEspacio = 'DISPONIBLE' | 'OCUPADO' | 'RESERVADO';

export interface Espacio {
  id?: number; // heredado de ABaseEntity
  estado: EstadoEspacio;
  status: true
}
