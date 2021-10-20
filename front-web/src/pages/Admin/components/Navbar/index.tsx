import { isAllowedByRole } from 'core/utils/auth';
import React from 'react';
import { NavLink } from 'react-router-dom';
import './styles.scss';

const Navbar = () => (
    <nav className="admin-nav">
        <ul>
            <li>
                {isAllowedByRole(['ROLE_ADMIN', 'ROLE_OPERATOR']) && (
                    <NavLink to="/admin/timesheet" className="admin-nav-item">
                        TIMESHEETS
                    </NavLink>
                )}
            </li>
            <li>
                {isAllowedByRole(['ROLE_ADMIN']) && (
                    <NavLink to="/admin/cliente" className="admin-nav-item">
                        CLIENTES
                    </NavLink>
                )}
            </li>
            <li>
                {isAllowedByRole(['ROLE_ADMIN']) && (
                    <NavLink to="/admin/advogado" className="admin-nav-item">
                        ADVOGADOS
                    </NavLink>
                )}
            </li>
            <li>
                {isAllowedByRole(['ROLE_ADMIN']) && (
                    <NavLink to="/admin/relatorio" className="admin-nav-item">
                        RELATÓRIOS
                    </NavLink>
                )}
            </li>
            <li>
                {isAllowedByRole(['ROLE_ADMIN']) && (
                    <NavLink to="/admin/caso" className="admin-nav-item">
                        CONTRATOS
                    </NavLink>
                )}
            </li>
            <li>
                {isAllowedByRole(['ROLE_ADMIN']) && (
                    <NavLink to="/admin/categoria" className="admin-nav-item">
                        CATEGORIAS
                    </NavLink>
                )}
            </li>

            <li>
                {isAllowedByRole(['ROLE_ADMIN']) && (
                    <NavLink to="/admin/usuario" className="admin-nav-item">
                        USUÁRIOS
                    </NavLink>
                )}
            </li>
        </ul>
    </nav>
)

export default Navbar;