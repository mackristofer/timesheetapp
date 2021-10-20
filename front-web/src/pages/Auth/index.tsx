import React from 'react';
import { ReactComponent as LoginIcon } from 'core/assets/images/login.svg';
import './styles.scss';
import AuthCard from './components/Card';
import { Route } from 'react-router-dom';
const Auth = () => (
    
    <div className="auth-container">

 

        <div className="auth-content">
            <div>
                <LoginIcon className="auth-login-img" />
            </div>
            <div className="auth-form">
                <Route path="/auth/login">
                <AuthCard />
                </Route>
            </div>
        </div>

    </div>
 
);

export default Auth;