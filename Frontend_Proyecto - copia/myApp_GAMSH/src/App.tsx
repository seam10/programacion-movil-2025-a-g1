import { Redirect, Route } from 'react-router-dom';  
// Importa componentes de React Router:  
// - Route para definir rutas y los componentes que renderizan  
// - Redirect para redirigir de una ruta a otra

import { IonApp, IonRouterOutlet, setupIonicReact } from '@ionic/react';  
// Importa de Ionic React:  
// - IonApp: contenedor raíz de la aplicación  
// - IonRouterOutlet: área donde se renderizan las rutas de Ionic  
// - setupIonicReact: inicializa la configuración interna de Ionic

import { IonReactRouter } from '@ionic/react-router';  
// Importa el envoltorio de React Router adaptado a Ionic

import Espacio from './pages/Espacio';     // pantalla de espacios  
import Registro from './pages/Registro';   // pantalla de registro  
import Tarifa from './pages/Tarifa';       // pantalla de tarifas  
import Salida from './pages/Salida';       // pantalla de salida  
// Importa los componentes de página que corresponden a cada sección de la app

/* Importación de estilos base de Ionic (necesarios para el framework) */
import '@ionic/react/css/core.css';  
import '@ionic/react/css/normalize.css';  
import '@ionic/react/css/structure.css';  
import '@ionic/react/css/typography.css';  
/* Importación de utilidades de estilo opcionales de Ionic */
import '@ionic/react/css/padding.css';  
import '@ionic/react/css/float-elements.css';  
import '@ionic/react/css/text-alignment.css';  
import '@ionic/react/css/text-transformation.css';  
import '@ionic/react/css/flex-utils.css';  
import '@ionic/react/css/display.css';  

import './theme/variables.css';  
// Importa variables de tema (colores, tamaños, etc.) definidas por el proyecto

setupIonicReact();  
// Llama a la función para inicializar internamente Ionic React antes de usar componentes

const App: React.FC = () => (  
  // Define el componente funcional raíz de la aplicación
  <IonApp>  
    {/* Contenedor principal de Ionic */}
    <IonReactRouter>  
      {/* Envoltorio que integra React Router con Ionic */}
      <IonRouterOutlet>  
        {/* Área de renderizado de rutas */}
        <Route exact path="/espacio">  
          {/* Ruta para /espacio */}
          <Espacio />  
          {/* Renderiza el componente Espacio */}
        </Route>
        <Route exact path="/registro/:id">  
          {/* Ruta para editar un registro con parámetro id */}
          <Registro />  
        </Route>
        <Route exact path="/registro">  
          {/* Ruta para crear un nuevo registro */}
          <Registro />  
        </Route>
        <Route exact path="/salida">  
          {/* Ruta para la pantalla de salida */}
          <Salida />  
        </Route>
        <Route exact path="/tarifa">  
          {/* Ruta para la pantalla de gestión de tarifas */}
          <Tarifa />  
        </Route>
        <Route exact path="/">  
          {/* Ruta raíz */}
          <Redirect to="/espacio" />  
          {/* Redirige automáticamente a /espacio */}
        </Route>
      </IonRouterOutlet>
    </IonReactRouter>
  </IonApp>
);

export default App;  
// Exporta el componente App como el default para usarlo en index.tsx u otros módulos
