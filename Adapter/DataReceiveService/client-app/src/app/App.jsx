import "../fake-db";
import "../styles/_app.scss";
import React from "react";
import { Provider } from "react-redux";
import { Router } from "react-router-dom";
import EgretTheme from "./EgretLayout/EgretTheme/EgretTheme";
import AppContext from "./appContext";
import history from "history.js";

import routes from "./RootRoutes";
import { Store } from "./redux/Store";
import Auth from "./auth/Auth";
import EgretLayout from "./EgretLayout";
import AuthGuard from "./auth/AuthGuard";
import "../styles/nprogress.css";
import { loadProgressBar } from "axios-progress-bar";


loadProgressBar();

const App = () => {
  return (
    <AppContext.Provider value={{ routes }}>
      <Provider store={Store}>
        <EgretTheme>
          <Auth>
            <Router history={history}>
              <AuthGuard>
                <EgretLayout />
              </AuthGuard>
            </Router>
          </Auth>
        </EgretTheme>
      </Provider>
    </AppContext.Provider>
  );
};

export default App;
