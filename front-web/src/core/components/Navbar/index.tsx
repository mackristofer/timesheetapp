import React from 'react';
import { Link, NavLink, useLocation } from 'react-router-dom';
import { ReactComponent as Logo } from 'core/assets/images/logo.svg';
import './styles.scss';
import { getSessionData, logout } from 'core/utils/auth';
import { useState } from 'react';
import { useEffect } from 'react';

const Navbar = () => {

    const [currentUser, setCurrentUser] = useState('');

    const location = useLocation();

    useEffect(() => {
        const currentUserData = getSessionData();
        setCurrentUser(currentUserData.nome);

    }, [location])

    const handleLogout = (event: React.MouseEvent<HTMLAnchorElement, MouseEvent>) => {
        event.preventDefault();
        logout();
    }

    return (

        <nav className="row bg-primary main-nav">
            <div className="col-4">
                <Link to="/dashboard" >
                    <Logo />
                </Link>
            </div>
            <div className="col-6">
                <ul className="main-menu">
                    <li>
                        <NavLink to="/dashboard" className="link-menu">
                            DASHBOARD
                        </NavLink>
                    </li>
                    <li>
                        <Link to="/admin/timesheet/create" className="link-menu">
                            TIMESHEET
                        </Link>
                    </li>
                    <li>
                        <NavLink to="/admin" className="link-menu">
                            ADMIN
                        </NavLink>
                    </li>
                </ul>
            </div>

            <div className="col-2 text-right">
                {
                    currentUser && (
                        <>
                            {currentUser}
                            <a href="#logout"
                            className="link-menu active d-block"
                            onClick={handleLogout}
                            >
                                LOGOUT
                            </a>
                        </>

                    )
                }
            </div>

        </nav>
    )
}



export default Navbar;