import React, { useState } from "react";
// Importa React y useState para manejar estado local

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
// Importa componentes de Ionic React y hook useIonViewWillEnter para efectos al entrar en la vista

import { useHistory, useParams } from "react-router-dom";
// Importa hooks para navegación y obtener parámetros de ruta

import ComponentButtons from "../components/componentButtons";
// Importa barra de navegación fija en footer

import ApiService from "../Service/ApiService";
// Importa servicio para llamadas al backend

import { Registro } from "../types/Registro";
// Importa la interfaz Registro

import { TarifaType } from "../types/Tarifa";
// Importa la interfaz TarifaType

import "./Registro.css";
// Importa estilos específicos

interface RouteParams {
  id?: string; // Parámetro id opcional en la ruta
}

const RegistroPage: React.FC = () => {
  const { id } = useParams<RouteParams>();
  const history = useHistory();

  // Estados para campos del formulario y datos cargados
  const [espacio, setEspacio] = useState<string>("");
  const [placa, setPlaca] = useState<string>("");
  const [horaIngreso, setHoraIngreso] = useState<string>("");
  const [tarifa, setTarifa] = useState<string>("");
  const [espaciosDisponibles, setEspaciosDisponibles] = useState<number[]>([]);
  const [tarifas, setTarifas] = useState<TarifaType[]>([]);
  const [mostrarSelector, setMostrarSelector] = useState(!id);
  const [toast, setToast] = useState<{ show: boolean; msg: string }>({ show: false, msg: "" });

  const handleGuardar = async () => {
    // Valida que todos los campos estén llenos
    if (!espacio || !placa || !horaIngreso || !tarifa) {
      setToast({ show: true, msg: "Todos los campos son obligatorios." });
      return;
    }

    const fechaActual = new Date().toISOString().split("T")[0]; // YYYY-MM-DD
    const horaCompleta = `${fechaActual}T${horaIngreso}:00`;

    // Construye el objeto Registro con tarifa como objeto { id: number }
    const registro: Registro = {
      id: id ? parseInt(id) : undefined,
      espacio: { id: parseInt(espacio) },
      placaVehiculo: placa,
      horaIngreso: horaCompleta,
      tarifa: { id: parseInt(tarifa) },
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

  useIonViewWillEnter(() => {
    // Carga datos cada vez que se entra en la vista

    const loadEspacios = async () => {
      try {
        const res = await ApiService.getEspacios();
        const disponibles = res.data.data
          .filter((e: any) => e.estado === "DISPONIBLE")
          .map((e: any) => e.id)
          .sort((a: number, b: number) => a - b);
        setEspaciosDisponibles(disponibles);
      } catch (error) {
        console.error("Error cargando espacios", error);
      }
    };

    const loadTarifas = async () => {
      try {
        const res = await ApiService.getTarifas();
        setTarifas(res.data.data || []);
      } catch (error) {
        console.error("Error cargando tarifas", error);
      }
    };

    const loadRegistro = async () => {
      if (id) {
        // Si se edita un registro existente
        try {
          const res = await ApiService.getRegistroById(parseInt(id));
          const registroExistente = res.data.data;
          if (registroExistente) {
            setEspacio(registroExistente.espacio.id.toString());
            setPlaca(registroExistente.placaVehiculo || "");
            const horaPartes = registroExistente.horaIngreso?.split("T");
            const horaSolo = horaPartes?.[1]?.substring(0, 5) || "";
            setHoraIngreso(horaSolo);
            setTarifa(registroExistente.tarifa?.id.toString() || "");
            setMostrarSelector(false);
          }
        } catch (error) {
          console.error("Error al cargar el registro", error);
        }
      } else {
        // Nuevo registro: espacio, hora y selector
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
      await loadTarifas();
    };

    loadRegistro();
  });

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
                  <IonSelect
                    value={espacio}
                    placeholder="Elige un espacio"
                    onIonChange={(e) => setEspacio(e.detail.value)}
                  >
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
                <IonInput
                  placeholder="Ej: ABC123"
                  value={placa}
                  onIonChange={(e) => setPlaca(e.detail.value!)}
                />
              </IonItem>

              <IonItem className="registro-item">
                <IonLabel position="stacked">Hora de ingreso:</IonLabel>
                <IonInput
                  type="time"
                  value={horaIngreso}
                  onIonChange={(e) => setHoraIngreso(e.detail.value!)}
                />
              </IonItem>

              <IonItem className="registro-item">
                <IonLabel position="stacked">Tarifa:</IonLabel>
                <IonSelect
                  value={tarifa}
                  placeholder="Elige una tarifa"
                  onIonChange={(e) => setTarifa(e.detail.value)}
                >
                  {tarifas.map((t) => (
                    <IonSelectOption key={t.id} value={t.id?.toString()}>
                      {t.tipoVehiculo} - {t.unidadTiempo} - ${t.valor}
                    </IonSelectOption>
                  ))}
                </IonSelect>
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

        <IonToast
          isOpen={toast.show}
          message={toast.msg}
          duration={1500}
          onDidDismiss={() => setToast({ show: false, msg: "" })}
        />
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