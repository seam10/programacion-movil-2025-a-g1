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
  const [tarifas, setTarifas] = useState<any[]>([]);
  const [tarifaSeleccionada, setTarifaSeleccionada] = useState<any | null>(null);
  const [tarifaTotal, setTarifaTotal] = useState<number | null>(null);
  const [showAlert, setShowAlert] = useState(false);

  useEffect(() => {
    ApiService.getEspacios()
      .then((res) => {
        const ocupados = res.data.data.filter((e: any) => e.estado === "OCUPADO");
        setEspaciosOcupados(ocupados);
      })
      .catch(console.error);

    ApiService.getTarifas()
      .then((res) => {
        const tarifasActivas = res.data.data.filter((t: any) => t.status === true);
        setTarifas(tarifasActivas);
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
    }
  }, [espacioId]);

  const calcularTarifa = () => {
    if (!registro?.horaIngreso || !horaSalida || !tarifaSeleccionada) return null;

    const ingresoHora = registro.horaIngreso.split("T")[1].substring(0, 5);
    const [hIn, mIn] = ingresoHora.split(":").map(Number);
    const [hOut, mOut] = horaSalida.split(":").map(Number);

    let minutos = (hOut * 60 + mOut) - (hIn * 60 + mIn);
    if (minutos < 0) minutos += 24 * 60;

    let total = 0;
    const unidad = tarifaSeleccionada.unidadTiempo;
    const valor = tarifaSeleccionada.valor;

    if (unidad === "hora") {
      total = Math.ceil(minutos / 60) * valor;
    } else if (unidad === "minuto") {
      total = minutos * valor;
    } else if (unidad === "dia") {
      total = Math.ceil(minutos / 1440) * valor;
    }

    setTarifaTotal(total);
    return total;
  };

  const handleRegistrarSalida = async () => {
    const total = calcularTarifa();
    if (total == null || !registro) return;

    const salidaData: SalidaType = {
      registro: { id: registro.id },
      espacio: { id: parseInt(espacioId) },
      tarifa: { id: tarifaSeleccionada.id },
      horaSalida: new Date().toISOString(),
      totalCalculado: total,
      status: true,
    };

    try {
      await ApiService.crearSalida(salidaData);
      await ApiService.actualizarEspacio(parseInt(espacioId), { estado: "DISPONIBLE", status: true });
      setShowAlert(true);
    } catch (error) {
      console.error(error);
    }
  };

  const onDismiss = () => {
    history.push("/espacio");
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
              <IonSelect value={espacioId} placeholder="Selecciona un espacio" onIonChange={(e) => setEspacioId(e.detail.value)}>
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
                  <IonInput type="time" value={horaSalida} onIonInput={(e) => setHoraSalida(e.detail.value!)} />
                </IonItem>
                <IonItem>
                  <IonLabel position="stacked">Selecciona Tarifa</IonLabel>
                  <IonSelect value={tarifaSeleccionada} placeholder="Elige una tarifa" onIonChange={(e) => setTarifaSeleccionada(e.detail.value)}>
                    {tarifas.map((t: any) => (
                      <IonSelectOption key={t.id} value={t}>
                        {t.tipoVehiculo.toUpperCase()} - {t.unidadTiempo} - ${t.valor}
                      </IonSelectOption>
                    ))}
                  </IonSelect>
                </IonItem>
                <IonButton expand="block" onClick={handleRegistrarSalida}>
                  CALCULAR Y REGISTRAR SALIDA
                </IonButton>
              </>
            )}
          </IonCardContent>
        </IonCard>
        <IonAlert
          isOpen={showAlert}
          header="✅ Salida registrada"
          message={`Tarifa a pagar: $${tarifaTotal}`}
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
