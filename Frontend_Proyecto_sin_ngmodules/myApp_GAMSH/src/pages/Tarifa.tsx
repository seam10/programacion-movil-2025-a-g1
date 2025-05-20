import React, { useState, useEffect } from "react";
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
  IonSelect,
  IonSelectOption,
  IonInput,
  IonButton,
  IonAlert,
  IonList,
  IonIcon,
  IonToast,
  IonFooter,
} from "@ionic/react";
import { pencilOutline, trashOutline } from "ionicons/icons";
import ComponentButtons from "../components/componentButtons";
import ApiService from "../Service/ApiService";
import { TarifaType } from "../types/Tarifa";
import "./Tarifa.css";

const Tarifa: React.FC = () => {
  const [tipo, setTipo] = useState("");
  const [unidad, setUnidad] = useState("");
  const [valor, setValor] = useState<number | string>("");
  const [tarifas, setTarifas] = useState<TarifaType[]>([]);
  const [editId, setEditId] = useState<number | null>(null);
  const [toastMsg, setToastMsg] = useState("");
  const [showToast, setShowToast] = useState(false);
  const [showDeleteAlert, setShowDeleteAlert] = useState(false);
  const [deleteId, setDeleteId] = useState<number | null>(null);

  const loadTarifas = () => {
    ApiService.getTarifas()
      .then((response) => {
        const activas = response.data.data?.filter((t: TarifaType) => t.status) ?? [];
        setTarifas(activas);
      })
      .catch((err) => console.error(err));
  };

  useEffect(() => {
    loadTarifas();
  }, []);

  const handleGuardar = () => {
    if (!tipo || !unidad || !String(valor).trim()) return;

    const tarifaData: TarifaType = {
      tipoVehiculo: tipo,
      unidadTiempo: unidad,
      valor: Number(valor),
      status: true,
    };

    if (editId != null) {
      ApiService.actualizarTarifa(editId, tarifaData)
        .then(() => {
          const updated = tarifas.map((t) =>
            t.id === editId ? { ...tarifaData, id: editId } : t
          );
          setTarifas(updated);
          setToastMsg("Tarifa actualizada");
          resetForm();
          setShowToast(true);
        })
        .catch((err) => console.error(err));
    } else {
      ApiService.crearTarifa(tarifaData)
        .then((response) => {
          const nuevaTarifa = response.data.data ?? response.data;
          setTarifas((prev) => Array.isArray(prev) ? [...prev, nuevaTarifa] : [nuevaTarifa]);
          setToastMsg("Tarifa agregada");
          resetForm();
          setShowToast(true);
        })
        .catch((err) => console.error(err));
    }
  };

  const startEdit = (t: TarifaType) => {
    setTipo(t.tipoVehiculo);
    setUnidad(t.unidadTiempo);
    setValor(t.valor);
    setEditId(t.id || null);
    window.scrollTo({ top: 0, behavior: "smooth" });
  };

  const confirmDelete = (id: number) => {
    setDeleteId(id);
    setShowDeleteAlert(true);
  };

  const handleDelete = () => {
    if (deleteId != null) {
      ApiService.eliminarTarifa(deleteId)
        .then(() => {
          setTarifas(tarifas.filter((t) => t.id !== deleteId));
          setToastMsg("Tarifa eliminada");
          setShowToast(true);
          loadTarifas();
        })
        .catch((err) => console.error(err));
    }
    setShowDeleteAlert(false);
    setDeleteId(null);
  };

  const resetForm = () => {
    setTipo("");
    setUnidad("");
    setValor("");
    setEditId(null);
  };

  return (
    <IonPage>
      <IonHeader translucent>
        <IonToolbar>
          <IonTitle>Gestión de Tarifas</IonTitle>
        </IonToolbar>
      </IonHeader>

      <IonContent fullscreen className="tarifa-content">
        <IonCard className="tarifa-card">
          <IonCardHeader>
            <IonCardTitle className="tarifa-card-title">
              {editId != null ? "Editar Tarifa" : "Agregar Tarifa"}
            </IonCardTitle>
          </IonCardHeader>
          <IonCardContent>
            <IonItem>
              <IonLabel position="stacked">Tipo de Vehículo</IonLabel>
              <IonSelect value={tipo} placeholder="Seleccionar tipo" onIonChange={(e) => setTipo(e.detail.value!)}>
                <IonSelectOption value="auto">Auto</IonSelectOption>
                <IonSelectOption value="moto">Moto</IonSelectOption>
                <IonSelectOption value="camion">Camión</IonSelectOption>
                <IonSelectOption value="camioneta">Camioneta</IonSelectOption>
              </IonSelect>
            </IonItem>
            <IonItem>
              <IonLabel position="stacked">Unidad de Tiempo</IonLabel>
              <IonSelect value={unidad} placeholder="Ej: por hora" onIonChange={(e) => setUnidad(e.detail.value!)}>
                <IonSelectOption value="minuto">Minuto</IonSelectOption>
                <IonSelectOption value="hora">Hora</IonSelectOption>
                <IonSelectOption value="dia">Día</IonSelectOption>
              </IonSelect>
            </IonItem>
            <IonItem>
              <IonLabel position="stacked">Valor ($)</IonLabel>
              <IonInput type="number" value={valor} placeholder="Ej: 500" onIonInput={(e) => setValor(e.detail.value!)} />
            </IonItem>
            <IonButton expand="block" onClick={handleGuardar}>
              {editId != null ? "Guardar cambios" : "Agregar tarifa"}
            </IonButton>
          </IonCardContent>
        </IonCard>

        {tarifas.length > 0 && (
          <IonList>
            {tarifas.map((t) => (
              <IonItem key={t.id} lines="full">
                <div className="tarifa-info">
                  <strong>{t.tipoVehiculo.toUpperCase()}</strong> — {t.unidadTiempo} — ${t.valor}
                </div>
                <IonButton slot="end" fill="clear" onClick={() => startEdit(t)}>
                  <IonIcon icon={pencilOutline} />
                </IonButton>
                <IonButton slot="end" fill="clear" color="danger" onClick={() => confirmDelete(t.id!)}>
                  <IonIcon icon={trashOutline} />
                </IonButton>
              </IonItem>
            ))}
          </IonList>
        )}
      </IonContent>

      <IonFooter>
        <ComponentButtons />
      </IonFooter>

      <IonAlert
        isOpen={showDeleteAlert}
        header="Confirmar eliminación"
        message="¿Deseas eliminar esta tarifa?"
        buttons={[
          { text: "Cancelar", role: "cancel", handler: () => setShowDeleteAlert(false) },
          { text: "Eliminar", handler: handleDelete },
        ]}
      />

      <IonToast isOpen={showToast} message={toastMsg} duration={1500} onDidDismiss={() => setShowToast(false)} />
    </IonPage>
  );
};

export default Tarifa;
