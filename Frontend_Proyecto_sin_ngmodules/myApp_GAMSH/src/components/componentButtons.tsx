import React from "react";
import { IonButton, IonIcon } from "@ionic/react";
import {
  homeOutline,
  clipboardOutline,
  pricetagOutline,
  logOutOutline
} from "ionicons/icons";
import "./componentButtons.css";

const ComponentButtons: React.FC = () => {
  return (
    <div className="crud-buttons">
      <IonButton className="btn home" size="small" routerLink="/espacio">
        <IonIcon icon={homeOutline} slot="start" />
        Inicio
      </IonButton>

      <IonButton className="btn register" size="small" routerLink="/registro">
        <IonIcon icon={clipboardOutline} slot="start" />
        Registro
      </IonButton>

      <IonButton className="btn tariff" size="small" routerLink="/tarifa">
        <IonIcon icon={pricetagOutline} slot="start" />
        Tarifa
      </IonButton>

      <IonButton className="btn exit" size="small" routerLink="/salida">
        <IonIcon icon={logOutOutline} slot="start" />
        Salida
      </IonButton>
    </div>
  );
};

export default ComponentButtons;
