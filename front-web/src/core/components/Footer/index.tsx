import React from 'react';
import {ReactComponent as LogoM3} from 'core/assets/images/logom3.svg';
import './styles.scss';

const Footer = () => (
<footer>
    <div className="row bg-primary footer-container">
    <div className="footer-content">
    <h4>www.m3tech.com.br</h4>
    <h4>suporte@m3tech.com.br</h4>
    <h4>(31)98444-0521</h4>
    </div>
    <div className="footer-logo-container">
        <LogoM3 className="footer-logo"/>
    </div>
    </div>
</footer>
);

export default Footer;