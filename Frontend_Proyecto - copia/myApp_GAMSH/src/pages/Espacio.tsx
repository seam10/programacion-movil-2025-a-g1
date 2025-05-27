import React, { useState } from "react";  
// Importa React y el hook useState para manejo de estado local

import {
  IonPage,
  IonContent,
  IonCard,
  IonCardHeader,
  IonCardTitle,
  IonCardContent,
  IonButton,
  IonGrid,
  IonRow,
  IonCol,
  IonFooter,
  IonToolbar,
  useIonViewWillEnter,
  IonToast,
} from "@ionic/react";  
// Importa componentes y hooks de Ionic para construir la UI y manejar el ciclo de vida

import { useHistory } from "react-router-dom";  
// Importa useHistory para navegación programática entre rutas

import ComponentButtons from "../components/componentButtons";  
// Importa el componente de botones de navegación inferior

import "./Espacio.css";  
// Importa los estilos específicos de la pantalla de Espacio

import ApiService from "../Service/ApiService";  
// Importa el servicio que realiza las llamadas al backend

import { Espacio as EspacioType } from "../types/Espacio";  
// Importa el tipo TypeScript para los datos de Espacio

// Define la forma del estado para cada espacio
interface EspacioState {
  id: number;               // Identificador del espacio
  disponible: boolean;      // Indica si está libre
  placa?: string;           // Placa del último vehículo (si hay)
  hora?: string;            // Hora del último registro formateada
  registroId?: number;      // ID del registro correspondiente
}

const Espacio: React.FC = () => {
  // Estado con lista de espacios transformados
  const [espacios, setEspacios] = useState<EspacioState[]>([]);
  // Estado para controlar el toast (mensaje emergente)
  const [toast, setToast] = useState<{ show: boolean; msg: string }>({
    show: false,
    msg: ""
  });
  const history = useHistory();  
  // Hook de Ionic que se dispara cada vez que la vista entra en pantalla
  useIonViewWillEnter(() => {
    cargarEspacios();  // Carga los datos al mostrar la pantalla
  });

  // Función que obtiene espacios y registros, luego los transforma
  const cargarEspacios = async () => {
    try {
      // Llamada al servicio para traer los espacios
      const espaciosRes = await ApiService.getEspacios();
      // Llamada al servicio para traer los registros
      const registrosRes = await ApiService.getRegistros();

      // Datos crudos de espacios y registros
      const espaciosBD: EspacioType[] = espaciosRes.data.data;
      const registrosBD = registrosRes.data.data;

      // Construye un array de longitud 12 con la información deseada
      const espaciosTransformados: EspacioState[] = Array.from(
        { length: 12 },
        (_, i) => {
          const id = i + 1;
          // Encuentra el estado actual del espacio
          const espacio = espaciosBD.find((e) => e.id === id);
          // Filtra todos los registros de este espacio
          const registrosEspacio = registrosBD.filter(
            (r: any) => r.espacio.id === id
          );
          // Selecciona el registro más reciente si existe
          const ultimoRegistro =
            registrosEspacio.length > 0
              ? registrosEspacio.reduce((a: any, b: any) =>
                  new Date(a.horaIngreso) > new Date(b.horaIngreso) ? a : b
                )
              : null;

          return {
            id,
            disponible: espacio?.estado === "DISPONIBLE",
            placa: ultimoRegistro?.placaVehiculo || "-",
            hora:
              ultimoRegistro?.horaIngreso?.split("T")[1]?.substring(0, 5) ||
              "-",
            registroId: ultimoRegistro?.id,
          };
        }
      );

      setEspacios(espaciosTransformados);  
      // Actualiza el estado con los espacios listos para mostrar
    } catch (error) {
      console.error(
        "Error cargando los espacios y registros desde el backend",
        error
      );
    }
  };

  // Maneja la selección de un espacio: libre o para editar
  const handleSeleccionarEspacio = (
    id: number,
    disponible: boolean,
    registroId?: number
  ) => {
    localStorage.setItem("espacioSeleccionado", id.toString());
    if (disponible) {
      // Si está libre, navega a la pantalla de registro nuevo
      history.push({ pathname: "/registro", state: { fromEspacio: true } });
    } else {
      // Si no está libre, navega al registro existente
      history.push(`/registro/${registroId}`);
    }
  };

  // Función para resetear todos los espacios a DISPONIBLE
  const resetEspacios = async () => {
    try {
      await ApiService.resetEspacios();  
      // Muestra un toast de éxito
      setToast({ show: true, msg: "✅ Todos los espacios fueron marcados como DISPONIBLES." });
      cargarEspacios();  // Recarga los espacios
    } catch (error) {
      // Muestra un toast de error
      setToast({ show: true, msg: "❌ Error al intentar resetear los espacios." });
    }
  };

  // Booleano que indica si hay alguno ocupado, para deshabilitar el botón
  const hayOcupados = espacios.some((e) => !e.disponible);

  return (
    <IonPage>
      {/* Contenido principal de la página */}
      <IonContent fullscreen className="espacio-content">
        <IonCard className="cliente-card">
          <IonCardHeader>
            <IonCardTitle className="cliente-card-title">
              PARQUEADERO GAMSH
            </IonCardTitle>
          </IonCardHeader>
          <IonCardContent>
            <h2 className="subtitle">Parqueadero visual</h2>
            <IonGrid>
              {/* Crea filas de 3 espacios cada una */}
              {[0, 3, 6, 9].map((row) => (
                <IonRow key={row}>
                  {espacios.slice(row, row + 3).map(
                    ({ id, disponible, placa, hora, registroId }) => (
                      <IonCol size="4" key={id}>
                        <IonButton
                          className="espacio-btn"
                          color={disponible ? "success" : "danger"}
                          expand="block"
                          onClick={() =>
                            handleSeleccionarEspacio(id, disponible, registroId)
                          }
                        >
                          <div className="espacio-text">
                            ESPACIO {id}
                            <br />
                            {disponible
                              ? "DISPONIBLE"
                              : `${placa} · ${hora}`}
                          </div>
                        </IonButton>
                      </IonCol>
                    )
                  )}
                </IonRow>
              ))}
            </IonGrid>
            {/* Botón para resetear, deshabilitado si hay ocupados */}
            <IonButton
              className="actualizar-btn"
              color="primary"
              expand="block"
              onClick={resetEspacios}
              disabled={hayOcupados}>
              ACTUALIZAR
            </IonButton>
          </IonCardContent>
        </IonCard>
      </IonContent>

      {/* Footer fijo con botones de navegación */}
      <IonFooter>
        <IonToolbar>
          <ComponentButtons />
        </IonToolbar>
      </IonFooter>

      {/* Toast para mostrar mensajes breves al usuario */}
      <IonToast
        isOpen={toast.show}
        message={toast.msg}
        duration={1500}
        onDidDismiss={() => setToast({ show: false, msg: "" })}
      />
    </IonPage>
  );
};

export default Espacio;  
// Exporta el componente como default para usarlo en la app
