import React from 'react';
import { useHistory } from 'react-router-dom';
import { 
  IonPage, IonHeader, IonToolbar, IonTitle, IonContent, IonButton, IonGrid, IonRow, IonCol 
} from '@ionic/react';

const Inicio: React.FC = () => {
  const history = useHistory();

  return (
    <IonPage>
      <IonHeader>
        <IonToolbar color="primary">
          <IonTitle>Pantalla de Inicio</IonTitle>
        </IonToolbar>
      </IonHeader>
      <IonContent className="ion-padding">
        <IonGrid>
          <IonRow className="ion-justify-content-center">
            <IonCol size="12" className="ion-text-center">
              <IonButton expand="block" color="tertiary" onClick={() => history.push('/profesor')}>
                Ir a Profesor
              </IonButton>
            </IonCol>
            <IonCol size="12" className="ion-text-center">
              <IonButton expand="block" color="success" onClick={() => history.push('/estudiante')}>
                Ir a Estudiante
              </IonButton>
            </IonCol>
            <IonCol size="12" className="ion-text-center">
              <IonButton expand="block" color="warning" onClick={() => history.push('/proveedor')}>
                Ir a Proveedor
              </IonButton>
            </IonCol>
            <IonCol size="12" className="ion-text-center">
              <IonButton expand="block" color="danger" onClick={() => history.push('/cliente')}>
                Ir a Cliente
              </IonButton>
            </IonCol>
          </IonRow>
        </IonGrid>
      </IonContent>
    </IonPage>
  );
};

export default Inicio;
