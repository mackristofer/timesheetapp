import Footer from 'core/components/Footer';
import Navbar from 'core/components/Navbar';
import PrivateRoute from 'core/components/Routes/PrivateRoutes';
import { isAuthenticated } from 'core/utils/auth';
import history from 'core/utils/history';
import Admin from 'pages/Admin';
import Auth from 'pages/Auth';
import Dashborad from 'pages/Dashboard';
import Relatorio from 'pages/Relatorio';
import React from 'react';
import { Redirect, Route, Router, Switch } from 'react-router-dom';

const Routes = () => {

    return (

        <Router history={history}>
            <Navbar />

            <Switch>
                {!isAuthenticated() ? (<Route path='/auth/login'>
                    <Auth />
                </Route>) : (<Redirect from="/auth/login" to="/dashboard" exact />)}
                <Redirect from="/" to="/dashboard" exact />
                <PrivateRoute path="/dashboard">
                    <Dashborad />
                </PrivateRoute>
                <PrivateRoute path="/timesheet">
                    <h1>TIMESHEET</h1>
                </PrivateRoute>
                <PrivateRoute path="/relatorio" allowedRoutes={['ROLE_ADMIN']}>
                    <Relatorio />
                </PrivateRoute>
                <Redirect from="/admin" to="/admin/timesheet" exact />
                <PrivateRoute path="/admin">
                    <Admin />
                </PrivateRoute>
            </Switch>
            <Footer />
        </Router>

    )

};

export default Routes;