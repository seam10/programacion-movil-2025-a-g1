import React from "react";
// Importa React para definir componentes funcionales

import { IonButton, IonIcon } from "@ionic/react";
// Importa componentes de Ionic: IonButton para botones e IonIcon para íconos

import {
  homeOutline,
  clipboardOutline,
  pricetagOutline,
  logOutOutline
} from "ionicons/icons";
// Importa los íconos específicos desde la librería Ionicons

import "./componentButtons.css";
// Importa los estilos CSS específicos para este componente

// Componente funcional que agrupa los botones de navegación CRUD
const ComponentButtons: React.FC = () => {
  return (
    // Contenedor padre con clase que aplica layout flex y espaciado
    <div className="crud-buttons">
      
      {/* Botón "Inicio" que navega a la ruta /espacio */}
      <IonButton
        className="btn home"    // Aplica estilos generales y color azul
        size="small"             // Tamaño pequeño de botón
        routerLink="/espacio"    // Ruta de Ionic Router al hacer clic
      >
        {/* Ícono al inicio del texto */}
        <IonIcon icon={homeOutline} slot="start" />
        Inicio
      </IonButton>

      {/* Botón "Registro" que navega a /registro */}
      <IonButton
        className="btn register"
        size="small"
        routerLink="/registro"
      >
        <IonIcon icon={clipboardOutline} slot="start" />
        Registro
      </IonButton>

      {/* Botón "Tarifa" que navega a /tarifa */}
      <IonButton
        className="btn tariff"
        size="small"
        routerLink="/tarifa"
      >
        <IonIcon icon={pricetagOutline} slot="start" />
        Tarifa
      </IonButton>

      {/* Botón "Salida" que navega a /salida */}
      <IonButton
        className="btn exit"
        size="small"
        routerLink="/salida"
      >
        <IonIcon icon={logOutOutline} slot="start" />
        Salida
      </IonButton>

    </div>
  );
};

export default ComponentButtons;
// Exporta el componente para usarlo en otras partes de la aplicación
// El componente se puede importar y usar en otras partes de la aplicación, como en un layout o una página específica.
// Esto permite que el componente sea reutilizable y modular, siguiendo las mejores prácticas de desarrollo en React.