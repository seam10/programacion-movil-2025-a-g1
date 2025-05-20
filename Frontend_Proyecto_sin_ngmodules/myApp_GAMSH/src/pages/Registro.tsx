import React, { useState, useEffect } from "react";
import {
  IonPage,
  IonContent,
  IonCard,
  IonCardHeader,
  IonCardTitle,
  IonCardContent,
  IonItem,
  IonLabel,
  IonInput,
  IonButton,
  IonSelect,
  IonSelectOption,
  IonToast,
  IonFooter,
  IonToolbar,
  useIonViewWillEnter,
} from "@ionic/react";
import { useHistory, useParams } from "react-router-dom";
import ComponentButtons from "../components/componentButtons";
import ApiService from "../Service/ApiService";
import { Registro } from "../types/Registro";
import "./Registro.css";

interface RouteParams {
  id?: string;
}

const RegistroPage: React.FC = () => {
  const { id } = useParams<RouteParams>();
  const history = useHistory();

  const [espacio, setEspacio] = useState<string>("");
  const [placa, setPlaca] = useState<string>("");
  const [horaIngreso, setHoraIngreso] = useState<string>("");
  const [espaciosDisponibles, setEspaciosDisponibles] = useState<number[]>([]);
  const [mostrarSelector, setMostrarSelector] = useState(!id);
  const [toast, setToast] = useState<{ show: boolean; msg: string }>({ show: false, msg: "" });

  useIonViewWillEnter(() => {
    const loadEspacios = async () => {
      try {
        const res = await ApiService.getEspacios();
        const disponibles = res.data.data
          .filter((e: any) => e.estado === "DISPONIBLE")
          .map((e: any) => e.id)
          .sort((a: number, b: number) => a - b);  // ✅ Ordena los espacios
        setEspaciosDisponibles(disponibles);
      } catch (error) {
        console.error("Error cargando espacios", error);
      }
    };

    const loadRegistro = async () => {
      if (id) {
        try {
          const res = await ApiService.getRegistroById(parseInt(id));
          const registroExistente = res.data.data;
          if (registroExistente) {
            setEspacio(registroExistente.espacio.id.toString());
            setPlaca(registroExistente.placaVehiculo || "");
            const horaPartes = registroExistente.horaIngreso?.split("T");
            const horaSolo = horaPartes?.[1]?.substring(0, 5) || "";
            setHoraIngreso(horaSolo);
            setMostrarSelector(false);
          }
        } catch (error) {
          console.error("Error al cargar el registro", error);
        }
      } else {
        const selected = localStorage.getItem("espacioSeleccionado");
        if (selected) {
          setEspacio(selected);
          setMostrarSelector(false);
          localStorage.removeItem("espacioSeleccionado");
        } else {
          setMostrarSelector(true);
        }

        const ahora = new Date();
        const hora = ahora.toTimeString().slice(0, 5);
        setHoraIngreso(hora);
      }

      await loadEspacios();
    };

    loadRegistro();
  });

  const handleGuardar = async () => {
    if (!espacio || !placa || !horaIngreso) {
      setToast({ show: true, msg: "Todos los campos son obligatorios." });
      return;
    }

    const fechaActual = new Date().toISOString().split("T")[0];
    const horaCompleta = `${fechaActual}T${horaIngreso}:00`;

    const registro: Registro = {
      id: id ? parseInt(id) : undefined,
      espacio: { id: parseInt(espacio) },
      placaVehiculo: placa,
      horaIngreso: horaCompleta,
      status: true,
    };

    try {
      if (id) {
        await ApiService.actualizarRegistro(parseInt(id), registro);
        setToast({ show: true, msg: "Registro actualizado con éxito" });
      } else {
        await ApiService.guardarRegistro(registro);
        setToast({ show: true, msg: "Registro guardado con éxito" });
      }
      history.push("/espacio");
    } catch (error) {
      setToast({ show: true, msg: "Error al guardar el registro" });
    }
  };

  const handleEliminar = async () => {
    if (!id) return;
    try {
      await ApiService.eliminarRegistro(parseInt(id));
      await ApiService.actualizarEspacio(parseInt(espacio), { estado: "DISPONIBLE", status: true });
      setToast({ show: true, msg: "Registro eliminado y espacio liberado." });
      history.push("/espacio");
    } catch (error) {
      setToast({ show: true, msg: "Error al eliminar el registro." });
    }
  };

  return (
    <IonPage>
      <div className="registro-header-fixed">
        <IonCard className="registro-header-card">
          <IonCardHeader>
            <IonCardTitle>{id ? "Editar Ingreso" : "Nuevo Ingreso"}</IonCardTitle>
          </IonCardHeader>
        </IonCard>
      </div>

      <IonContent fullscreen className="registro-content">
        <div className="registro-content-padding">
          <IonCard className="registro-card">
            <IonCardHeader>
              <IonCardTitle>{id ? "Editar Ingreso" : "Nuevo Ingreso"}</IonCardTitle>
            </IonCardHeader>
            <IonCardContent>
              <IonItem className="registro-item">
                <IonLabel position="stacked">Espacio:</IonLabel>
                {mostrarSelector ? (
                  <IonSelect value={espacio} placeholder="Elige un espacio" onIonChange={(e) => setEspacio(e.detail.value)}>
                    {espaciosDisponibles.map((num) => (
                      <IonSelectOption key={num} value={num.toString()}>
                        Espacio {num}
                      </IonSelectOption>
                    ))}
                  </IonSelect>
                ) : (
                  <IonLabel className="ion-padding-start">Espacio {espacio}</IonLabel>
                )}
              </IonItem>

              <IonItem className="registro-item">
                <IonLabel position="stacked">Placa del vehículo:</IonLabel>
                <IonInput placeholder="Ej: ABC123" value={placa} onIonChange={(e) => setPlaca(e.detail.value!)} />
              </IonItem>

              <IonItem className="registro-item">
                <IonLabel position="stacked">Hora de ingreso:</IonLabel>
                <IonInput type="time" value={horaIngreso} onIonChange={(e) => setHoraIngreso(e.detail.value!)} />
              </IonItem>

              <IonButton expand="block" className="registro-guardar" onClick={handleGuardar}>
                GUARDAR
              </IonButton>

              {id && (
                <IonButton expand="block" color="danger" onClick={handleEliminar}>
                  ELIMINAR
                </IonButton>
              )}
            </IonCardContent>
          </IonCard>
        </div>

        <IonToast isOpen={toast.show} message={toast.msg} duration={1500} onDidDismiss={() => setToast({ show: false, msg: "" })} />
      </IonContent>

      <IonFooter>
        <IonToolbar className="footer-toolbar">
          <ComponentButtons />
        </IonToolbar>
      </IonFooter>
    </IonPage>
  );
};

export default RegistroPage;
