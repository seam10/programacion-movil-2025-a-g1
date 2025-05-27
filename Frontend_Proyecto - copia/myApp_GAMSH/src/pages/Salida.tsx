import React, { useEffect, useState } from "react";
import {
  IonPage,
  IonHeader,
  IonToolbar,
  IonTitle,
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
  IonAlert,
  IonFooter,
  IonToolbar as IonFooterToolbar,
} from "@ionic/react";
import { useHistory } from "react-router-dom";
import ComponentButtons from "../components/componentButtons";
import ApiService from "../Service/ApiService";
import { SalidaType } from "../types/Salida";

const Salida: React.FC = () => {
  const history = useHistory();
  const [espaciosOcupados, setEspaciosOcupados] = useState<any[]>([]);
  const [espacioId, setEspacioId] = useState<string>("");
  const [registro, setRegistro] = useState<any | null>(null);
  const [horaSalida, setHoraSalida] = useState<string>("");
  const [showAlert, setShowAlert] = useState(false);
  const [alertMensaje, setAlertMensaje] = useState<string>("");

  useEffect(() => {
    ApiService.getEspacios()
      .then((res) => {
        const ocupados = res.data.data.filter((e: any) => e.estado === "OCUPADO");
        setEspaciosOcupados(ocupados);
      })
      .catch(console.error);

    const ahora = new Date();
    const hora = ahora.toTimeString().slice(0, 5);
    setHoraSalida(hora);
  }, []);

  useEffect(() => {
    if (espacioId) {
      ApiService.getRegistros()
        .then((res) => {
          const registrosEspacio = res.data.data.filter((r: any) => r.espacio.id === parseInt(espacioId));
          if (registrosEspacio.length > 0) {
            const ultimoRegistro = registrosEspacio[registrosEspacio.length - 1];
            setRegistro(ultimoRegistro);
          } else {
            setRegistro(null);
          }
        })
        .catch(console.error);
    } else {
      setRegistro(null);
    }
  }, [espacioId]);

  const handleRegistrarSalida = async () => {
    if (!registro) {
      setAlertMensaje("Debe seleccionar un espacio con un registro válido.");
      setShowAlert(true);
      return;
    }

    if (!registro.tarifa || !registro.tarifa.id) {
      setAlertMensaje("No se encontró tarifa asociada al registro. No se puede registrar la salida.");
      setShowAlert(true);
      return;
    }

    const espacioNumerico = parseInt(espacioId);
    if (isNaN(espacioNumerico)) {
      setAlertMensaje("Espacio inválido.");
      setShowAlert(true);
      return;
    }

    const salidaData: SalidaType = {
      registro: { id: registro.id },
      espacio: { id: espacioNumerico },
      tarifa: { id: registro.tarifa.id },
      horaSalida: new Date().toISOString(),
      totalCalculado: 0, // O el cálculo que hagas si tienes
      status: true,
    };

    try {
      await ApiService.crearSalida(salidaData);
      await ApiService.actualizarEspacio(espacioNumerico, { estado: "DISPONIBLE", status: true });
      setAlertMensaje("✅ Salida registrada correctamente");
      setShowAlert(true);
    } catch (error) {
      console.error(error);
      setAlertMensaje("Error al registrar la salida.");
      setShowAlert(true);
    }
  };

  const onDismiss = () => {
    setShowAlert(false);
    if (alertMensaje === "✅ Salida registrada correctamente") {
      history.push("/espacio");
    }
  };

  return (
    <IonPage>
      <IonHeader>
        <IonToolbar>
          <IonTitle>Registrar Salida</IonTitle>
        </IonToolbar>
      </IonHeader>
      <IonContent className="ion-padding">
        <IonCard>
          <IonCardHeader>
            <IonCardTitle>Selecciona un Espacio Ocupado</IonCardTitle>
          </IonCardHeader>
          <IonCardContent>
            <IonItem>
              <IonLabel position="stacked">Espacio</IonLabel>
              <IonSelect
                value={espacioId}
                placeholder="Selecciona un espacio"
                onIonChange={(e) => setEspacioId(e.detail.value)}
              >
                {espaciosOcupados.map((e: any) => (
                  <IonSelectOption key={e.id} value={e.id.toString()}>
                    Espacio {e.id}
                  </IonSelectOption>
                ))}
              </IonSelect>
            </IonItem>

            {registro && (
              <>
                <IonItem>
                  <IonLabel position="stacked">Placa</IonLabel>
                  <IonInput value={registro.placaVehiculo} readonly />
                </IonItem>
                <IonItem>
                  <IonLabel position="stacked">Hora de Ingreso</IonLabel>
                  <IonInput value={registro.horaIngreso.split("T")[1].substring(0, 5)} readonly />
                </IonItem>
                <IonItem>
                  <IonLabel position="stacked">Hora de Salida</IonLabel>
                  <IonInput
                    type="time"
                    value={horaSalida}
                    onIonInput={(e) => setHoraSalida(e.detail.value!)}
                  />
                </IonItem>
                <IonButton expand="block" onClick={handleRegistrarSalida}>
                  REGISTRAR SALIDA
                </IonButton>
              </>
            )}
          </IonCardContent>
        </IonCard>
        <IonAlert
          isOpen={showAlert}
          header={alertMensaje.startsWith("✅") ? "Éxito" : "Error"}
          message={alertMensaje}
          buttons={[{ text: "OK", handler: onDismiss }]}
        />
      </IonContent>
      <IonFooter>
        <IonFooterToolbar>
          <ComponentButtons />
        </IonFooterToolbar>
      </IonFooter>
    </IonPage>
  );
};

export default Salida;