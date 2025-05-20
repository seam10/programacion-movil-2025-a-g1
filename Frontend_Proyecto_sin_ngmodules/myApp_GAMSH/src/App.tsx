import { Redirect, Route } from 'react-router-dom';
import { IonApp, IonRouterOutlet, setupIonicReact } from '@ionic/react';
import { IonReactRouter } from '@ionic/react-router';
import Espacio from './pages/Espacio'; // pantalla de espacios 
import Registro from './pages/Registro'; // pantalla de registro 
import Tarifa from './pages/Tarifa'; //  Pantalla de tarifas
import Salida from './pages/Salida';//pantalla de salida 

import '@ionic/react/css/core.css';
import '@ionic/react/css/normalize.css';
import '@ionic/react/css/structure.css';
import '@ionic/react/css/typography.css';
import '@ionic/react/css/padding.css';
import '@ionic/react/css/float-elements.css';
import '@ionic/react/css/text-alignment.css';
import '@ionic/react/css/text-transformation.css';
import '@ionic/react/css/flex-utils.css';
import '@ionic/react/css/display.css';
import './theme/variables.css';


setupIonicReact();

const App: React.FC = () => (
  <IonApp>
    <IonReactRouter>
      <IonRouterOutlet>
        <Route exact path="/espacio">
          <Espacio />
        </Route>
        <Route exact path="/registro/:id">
          <Registro />
        </Route>
        <Route exact path="/registro">
          <Registro />
        </Route>
        <Route exact path="/salida">
          <Salida />
        </Route>
        <Route exact path="/tarifa">
          <Tarifa />
        </Route>
        <Route exact path="/">
          <Redirect to="/espacio" />
        </Route>
      </IonRouterOutlet>
    </IonReactRouter>
  </IonApp>
);

export default App;
