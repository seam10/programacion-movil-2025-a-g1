import React, { useState, useEffect } from "react";  
// Importa React y dos hooks:  
// - useState para declarar y actualizar estado local  
// - useEffect para ejecutar código (efectos secundarios) cuando el componente se monta o cambian dependencias

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
// Importa múltiples componentes de Ionic React:
// - IonPage: contenedor principal de la página  
// - IonHeader, IonFooter: cabecera y pie de página  
// - IonToolbar: barra de herramientas (header/footer)  
// - IonTitle: título dentro de la toolbar  
// - IonContent: área de contenido desplazable  
// - IonCard y subcomponentes: tarjetas para agrupar UI  
// - IonItem, IonLabel, IonInput: filas de formulario con etiqueta e input  
// - IonSelect, IonSelectOption: desplegable de opciones  
// - IonButton: botón de acción  
// - IonAlert: diálogo modal de alerta  
// - IonList: lista de ítems  
// - IonIcon: iconos de Ionicons  
// - IonToast: mensaje emergente breve

import { pencilOutline, trashOutline } from "ionicons/icons";  
// Importa dos iconos (editar y eliminar) desde la librería ionicons

import ComponentButtons from "../components/componentButtons";  
// Importa un componente personalizado que muestra botones de navegación fija en el footer

import ApiService from "../Service/ApiService";  
// Importa el servicio con las funciones que hacen llamadas HTTP al backend

import { TarifaType } from "../types/Tarifa";  
// Importa la definición TypeScript de la interfaz TarifaType

import "./Tarifa.css";  
// Importa los estilos CSS específicos para esta página

const Tarifa: React.FC = () => {  
  // Define el componente funcional Tarifa

  const [tipo, setTipo] = useState("");  
  // Estado para el tipo de vehículo; inicialmente cadena vacía

  const [unidad, setUnidad] = useState("");  
  // Estado para la unidad de tiempo (minuto, hora, día)

  const [valor, setValor] = useState<number | string>("");  
  // Estado para el valor numérico de la tarifa; puede ser número o cadena

  const [tarifas, setTarifas] = useState<TarifaType[]>([]);  
  // Estado que almacena el array de tarifas cargadas desde el backend

  const [editId, setEditId] = useState<number | null>(null);  
  // Estado que indica el ID de la tarifa en edición; null si es creación nueva

  const [toastMsg, setToastMsg] = useState("");  
  // Estado para el mensaje que mostrará el Toast

  const [showToast, setShowToast] = useState(false);  
  // Estado booleano para controlar la visibilidad del Toast

  const [showDeleteAlert, setShowDeleteAlert] = useState(false);  
  // Estado booleano para controlar la alerta de confirmación de borrado

  const [deleteId, setDeleteId] = useState<number | null>(null);  
  // Estado que guarda el ID de la tarifa a eliminar

  const loadTarifas = () => {  
    // Función que obtiene las tarifas activas del backend
    ApiService.getTarifas()  
      // Llama al endpoint GET /tarifas
      .then((response) => {
        // Filtra solo las que tengan status=true
        const activas = response.data.data?.filter((t: TarifaType) => t.status) ?? [];
        setTarifas(activas);  
        // Guarda las tarifas activas en el estado
      })
      .catch((err) => console.error(err));  
      // Muestra errores en consola si la llamada falla
  };

  useEffect(() => {
    loadTarifas();  
    // Al montar el componente, carga las tarifas una vez
  }, []);  
  // Dependencia vacía: solo se ejecuta al montar

  const handleGuardar = () => {  
    // Función que guarda o actualiza una tarifa según editId
    if (!tipo || !unidad || !String(valor).trim()) return;  
    // Si faltan campos obligatorios, no hace nada

    const tarifaData: TarifaType = {
      tipoVehiculo: tipo,
      unidadTiempo: unidad,
      valor: Number(valor),
      status: true,
    };  
    // Prepara el objeto con los datos ingresados

    if (editId != null) {
      // Modo edición: editId contiene un número
      ApiService.actualizarTarifa(editId, tarifaData)
        .then(() => {
          // Actualiza la lista localmente para reflejar el cambio
          const updated = tarifas.map((t) =>
            t.id === editId ? { ...tarifaData, id: editId } : t
          );
          setTarifas(updated);
          setToastMsg("Tarifa actualizada");
          resetForm();  
          // Limpia el formulario
          setShowToast(true);  
          // Muestra el toast de éxito
        })
        .catch((err) => console.error(err));
    } else {
      // Modo creación: editId es null
      ApiService.crearTarifa(tarifaData)
        .then((response) => {
          // Toma la tarifa recién creada desde la respuesta
          const nuevaTarifa = response.data.data ?? response.data;
          setTarifas((prev) =>
            Array.isArray(prev) ? [...prev, nuevaTarifa] : [nuevaTarifa]
          );
          setToastMsg("Tarifa agregada");
          resetForm();
          setShowToast(true);
        })
        .catch((err) => console.error(err));
    }
  };

  const startEdit = (t: TarifaType) => {
    // Al hacer click en editar, carga los campos en el formulario
    setTipo(t.tipoVehiculo);
    setUnidad(t.unidadTiempo);
    setValor(t.valor);
    setEditId(t.id || null);
    window.scrollTo({ top: 0, behavior: "smooth" });  
    // Desplaza la ventana al top para mostrar el formulario
  };

  const confirmDelete = (id: number) => {
    // Al hacer click en eliminar, guarda el ID y muestra la alerta
    setDeleteId(id);
    setShowDeleteAlert(true);
  };

  const handleDelete = () => {
    // Si el usuario confirma borrado, llama al servicio y actualiza la lista
    if (deleteId != null) {
      ApiService.eliminarTarifa(deleteId)
        .then(() => {
          setTarifas(tarifas.filter((t) => t.id !== deleteId));
          setToastMsg("Tarifa eliminada");
          setShowToast(true);
          loadTarifas();  
          // Vuelve a cargar para asegurar consistencia
        })
        .catch((err) => console.error(err));
    }
    setShowDeleteAlert(false);
    setDeleteId(null);
  };

  const resetForm = () => {
    // Limpia los campos del formulario y sale de modo edición
    setTipo("");
    setUnidad("");
    setValor("");
    setEditId(null);
  };

  return (
    <IonPage>
      <IonHeader translucent>
        {/* Cabecera fija con título */}
        <IonToolbar>
          <IonTitle>Gestión de Tarifas</IonTitle>
        </IonToolbar>
      </IonHeader>

      <IonContent fullscreen className="tarifa-content">
        {/* Contenido con padding inferior para el footer */}
        <IonCard className="tarifa-card">
          <IonCardHeader>
            <IonCardTitle className="tarifa-card-title">
              {editId != null ? "Editar Tarifa" : "Agregar Tarifa"}
              {/* Título dinámico según modo */}
            </IonCardTitle>
          </IonCardHeader>
          <IonCardContent>
            {/* Formulario de tipo de vehículo */}
            <IonItem className="tarifa-item">
              <IonLabel position="stacked">Tipo de Vehículo</IonLabel>
              <IonSelect
                value={tipo}
                placeholder="Seleccionar tipo"
                onIonChange={(e) => setTipo(e.detail.value!)}
              >
                <IonSelectOption value="auto">Auto</IonSelectOption>
                <IonSelectOption value="moto">Moto</IonSelectOption>
                <IonSelectOption value="camion">Camión</IonSelectOption>
                <IonSelectOption value="camioneta">Camioneta</IonSelectOption>
              </IonSelect>
            </IonItem>

            {/* Formulario de unidad de tiempo */}
            <IonItem className="tarifa-item">
              <IonLabel position="stacked">Unidad de Tiempo</IonLabel>
              <IonSelect
                value={unidad}
                placeholder="Ej: por hora"
                onIonChange={(e) => setUnidad(e.detail.value!)}
              >
                <IonSelectOption value="minuto">Minuto</IonSelectOption>
                <IonSelectOption value="hora">Hora</IonSelectOption>
                <IonSelectOption value="dia">Día</IonSelectOption>
              </IonSelect>
            </IonItem>

            {/* Formulario de valor numérico */}
            <IonItem className="tarifa-item">
              <IonLabel position="stacked">Valor ($)</IonLabel>
              <IonInput
                type="number"
                value={valor}
                placeholder="Ej: 500"
                onIonInput={(e) => setValor(e.detail.value!)}
              />
            </IonItem>

            {/* Botón para guardar o actualizar */}
            <IonButton expand="block" onClick={handleGuardar} className="tarifa-guardar">
              {editId != null ? "Guardar cambios" : "Agregar tarifa"}
            </IonButton>
          </IonCardContent>
        </IonCard>

        {/* Lista de tarifas existentes */}
        {tarifas.length > 0 && (
          <IonList className="tarifa-list">
            {tarifas.map((t) => (
              <IonItem key={t.id} lines="full" className="tarifa-item">
                <div className="tarifa-info">
                  <strong>{t.tipoVehiculo.toUpperCase()}</strong> — {t.unidadTiempo} — ${t.valor}
                </div>
                {/* Botón de editar */}
                <IonButton slot="end" fill="clear" onClick={() => startEdit(t)}>
                  <IonIcon icon={pencilOutline} />
                </IonButton>
                {/* Botón de eliminar */}
                <IonButton slot="end" fill="clear" color="danger" onClick={() => confirmDelete(t.id!)}>
                  <IonIcon icon={trashOutline} />
                </IonButton>
              </IonItem>
            ))}
          </IonList>
        )}
      </IonContent>

      <IonFooter>
        {/* Footer con botones de navegación */}
        <ComponentButtons />
      </IonFooter>

      {/* Alerta para confirmar eliminación */}
      <IonAlert
        isOpen={showDeleteAlert}
        header="Confirmar eliminación"
        message="¿Deseas eliminar esta tarifa?"
        buttons={[
          { text: "Cancelar", role: "cancel", handler: () => setShowDeleteAlert(false) },
          { text: "Eliminar", handler: handleDelete },
        ]}
      />

      {/* Toast para notificaciones breves */}
      <IonToast
        isOpen={showToast}
        message={toastMsg}
        duration={1500}
        onDidDismiss={() => setShowToast(false)}
      />
    </IonPage>
  );
};

export default Tarifa;  
// Exporta el componente para que pueda usarse en la aplicación
// El componente Tarifa permite gestionar tarifas de vehículos, incluyendo agregar, editar y eliminar tarifas.
// Utiliza Ionic para la interfaz y un servicio API para la persistencia de datos.