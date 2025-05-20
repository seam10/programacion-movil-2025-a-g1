import React, { useState } from "react";
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
import { useHistory } from "react-router-dom";
import ComponentButtons from "../components/componentButtons";
import "./Espacio.css";
import ApiService from "../Service/ApiService";
import { Espacio as EspacioType } from "../types/Espacio";

interface EspacioState {
  id: number;
  disponible: boolean;
  placa?: string;
  hora?: string;
  registroId?: number;
}

const Espacio: React.FC = () => {
  const [espacios, setEspacios] = useState<EspacioState[]>([]);
  const [toast, setToast] = useState<{ show: boolean; msg: string }>({ show: false, msg: "" });
  const history = useHistory();

  useIonViewWillEnter(() => {
    cargarEspacios();
  });

  const cargarEspacios = async () => {
    try {
      const espaciosRes = await ApiService.getEspacios();
      const registrosRes = await ApiService.getRegistros();

      const espaciosBD: EspacioType[] = espaciosRes.data.data;
      const registrosBD = registrosRes.data.data;

      const espaciosTransformados: EspacioState[] = Array.from({ length: 12 }, (_, i) => {
        const id = i + 1;
        const espacio = espaciosBD.find((e) => e.id === id);

        // Selecciona el último registro (el más reciente) para este espacio
        const registrosEspacio = registrosBD.filter((r: any) => r.espacio.id === id);
        const ultimoRegistro = registrosEspacio.length > 0
          ? registrosEspacio.reduce((a: any, b: any) => new Date(a.horaIngreso) > new Date(b.horaIngreso) ? a : b)
          : null;

        return {
          id,
          disponible: espacio?.estado === "DISPONIBLE",
          placa: ultimoRegistro?.placaVehiculo || "-",
          hora: ultimoRegistro?.horaIngreso?.split("T")[1]?.substring(0, 5) || "-",
          registroId: ultimoRegistro?.id,
        };
      });

      setEspacios(espaciosTransformados);
    } catch (error) {
      console.error("Error cargando los espacios y registros desde el backend", error);
    }
  };

  const handleSeleccionarEspacio = (id: number, disponible: boolean, registroId?: number) => {
    localStorage.setItem("espacioSeleccionado", id.toString());
    if (disponible) {
      history.push({ pathname: "/registro", state: { fromEspacio: true } });
    } else {
      history.push(`/registro/${registroId}`);
    }
  };

  const resetEspacios = async () => {
    try {
      await ApiService.resetEspacios();
      setToast({ show: true, msg: "✅ Todos los espacios fueron marcados como DISPONIBLES." });
      cargarEspacios();
    } catch (error) {
      setToast({ show: true, msg: "❌ Error al intentar resetear los espacios." });
    }
  };

  const hayOcupados = espacios.some((e) => !e.disponible);

  return (
    <IonPage>
      <IonContent fullscreen className="espacio-content">
        <IonCard className="cliente-card">
          <IonCardHeader>
            <IonCardTitle className="cliente-card-title">PARQUEADERO GAMSH</IonCardTitle>
          </IonCardHeader>
          <IonCardContent>
            <h2 className="subtitle">Parqueadero visual</h2>
            <IonGrid>
              {[0, 3, 6, 9].map((row) => (
                <IonRow key={row}>
                  {espacios.slice(row, row + 3).map(({ id, disponible, placa, hora, registroId }) => (
                    <IonCol size="4" key={id}>
                      <IonButton
                        className="espacio-btn"
                        color={disponible ? "success" : "danger"}
                        expand="block"
                        onClick={() => handleSeleccionarEspacio(id, disponible, registroId)}
                      >
                        <div className="espacio-text">
                          ESPACIO {id}
                          <br />
                          {disponible ? "DISPONIBLE" : `${placa} · ${hora}`}
                        </div>
                      </IonButton>
                    </IonCol>
                  ))}
                </IonRow>
              ))}
            </IonGrid>
            <IonButton
              className="actualizar-btn"
              color="primary"
              expand="block"
              onClick={resetEspacios}
              disabled={hayOcupados}
            >
              ACTUALIZAR
            </IonButton>
          </IonCardContent>
        </IonCard>
      </IonContent>

      <IonFooter>
        <IonToolbar>
          <ComponentButtons />
        </IonToolbar>
      </IonFooter>

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
