import React from 'react';
import { useHistory } from 'react-router-dom'; // Importa useHistory para la navegación
import { 
  IonPage, IonHeader, IonToolbar, IonTitle, IonContent, IonCard, IonCardHeader, 
  IonCardTitle, IonItem, IonLabel, IonInput, IonButton 
} from '@ionic/react';

const Estudiante: React.FC = () => {
  const history = useHistory(); // Hook para manejar navegación


  return (
    <IonPage>
      <IonHeader>
        <IonToolbar color="primary">
          <IonTitle>Estudiante</IonTitle>
        </IonToolbar>
      </IonHeader>
      <IonContent className="ion-padding">
        <IonCard>
          <IonCardHeader>
            <IonCardTitle>Estudiante</IonCardTitle>
          </IonCardHeader>
          <IonItem>
            <IonLabel position="floating">Nombre</IonLabel>
            <IonInput type="text" required />
          </IonItem>
          <IonItem>
            <IonLabel position="floating">Apellido</IonLabel>
            <IonInput type="text" required />
          </IonItem>
          <IonItem>
            <IonLabel position="floating">Edad</IonLabel>
            <IonInput type="number" required />
          </IonItem>
          <IonItem>
            <IonLabel position="floating">Correo</IonLabel>
            <IonInput type="email" required />
          </IonItem>
          <div className="ion-text-center ion-margin-top">
            <IonButton color="success">Agregar</IonButton>
            <IonButton color="warning">Modificar</IonButton>
            <IonButton color="danger">Eliminar</IonButton>
            <IonButton color="primary">Consultar</IonButton>
            <IonButton expand="block" color="medium" onClick={() => history.push('/Home')}>
                      Regresar a Home
                    </IonButton>
          </div>
        </IonCard>
      </IonContent>
    </IonPage>
  );
};

export default Estudiante;
