import React from 'react';
import { useHistory } from 'react-router-dom'; // Importa useHistory para la navegación
import { 
  IonPage, IonHeader, IonToolbar, IonTitle, IonContent, IonCard, IonCardHeader, 
  IonCardTitle, IonItem, IonLabel, IonInput, IonButton 
} from '@ionic/react';

const Profesor: React.FC = () => {
  const history = useHistory(); // Hook para manejar navegación

  return (
    <IonPage>
      <IonHeader>
        <IonToolbar color="primary">
          <IonTitle>Profesor</IonTitle>
        </IonToolbar>
      </IonHeader>
      <IonContent className="ion-padding">
        <IonCard>
          <IonCardHeader>
            <IonCardTitle>Profesor</IonCardTitle>
          </IonCardHeader>

          {/* Campos del formulario */}
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

          {/* Botones */}
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

export default Profesor;
