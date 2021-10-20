import PrivateRoute from 'core/components/Routes/PrivateRoutes';
import React from 'react';
import { Switch } from 'react-router-dom';
import Admadvogado from './components/Adminadvogado';
import Admcaso from './components/Admincaso';
import Admcategoria from './components/Admincategoria';
import Admcliente from './components/Admincliente';
import Admrelatorio from './components/Adminrelatorio';
import RelTimeSheet from './components/Adminrelatorio/Relatorios/TimeSheet';
import Admtimesheet from './components/Admintimesheet';
import Admusuario from './components/Adminusuario';
import Navbar from './components/Navbar';
import './styles.scss';


const Admin = () => (
    <div className="admin-container">
        <Navbar />
        <div className="admin-content">
            <Switch>
                <PrivateRoute path="/admin/timesheet">
                    <Admtimesheet />
                </PrivateRoute>
                <PrivateRoute path="/admin/cliente" allowedRoutes={['ROLE_ADMIN']}>
                    <Admcliente />
                </PrivateRoute>
                <PrivateRoute path="/admin/advogado" allowedRoutes={['ROLE_ADMIN']}>
                    <Admadvogado />
                </PrivateRoute>
                <PrivateRoute path="/admin/caso" allowedRoutes={['ROLE_ADMIN']}>
                    <Admcaso />
                </PrivateRoute>
                <PrivateRoute path="/admin/categoria" allowedRoutes={['ROLE_ADMIN']}>
                    <Admcategoria />
                </PrivateRoute>
                <PrivateRoute path="/admin/usuario" allowedRoutes={['ROLE_ADMIN']}>
                    <Admusuario />
                </PrivateRoute>
                <PrivateRoute path="/admin/relatorio" allowedRoutes={['ROLE_ADMIN']}>
                    <Admrelatorio />
                </PrivateRoute>
                <PrivateRoute path="/admin/rel/cli/:cliId/:dtIn/:dtOut" allowedRoutes={['ROLE_ADMIN']}>
                    <RelTimeSheet />
                </PrivateRoute>
            </Switch>
        </div>
    </div>
);

export default Admin;