import './core/assets/styles/custom.scss';
import './app.scss';
import React from 'react';
import Routes from 'Routes';
import {ToastContainer} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const App = () => (
    <>
<Routes />
<ToastContainer />
</>
)
export default App;
