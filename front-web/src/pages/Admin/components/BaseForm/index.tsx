import React from 'react';
import { useHistory } from 'react-router-dom';
import './styles.scss';

type Props = {
    children:React.ReactNode;
}

const BaseForm = ({ children }: Props) => {
    const history = useHistory();

    const handleCancel = () => {
        history.push('./');
    }

    return(
        <div className="admin-base-form">
            {children}
            <div className="base-form-actions">
                <button
                className="btn btn-outline-danger border-radius-10 mr-3"
                onClick={handleCancel}
                >
                    CANCELAR
                </button>
                <button className="btn btn-outline-secondary border-radius-10 mr-3"
                >
                    SALVAR
                </button>

            </div>
        </div>
    )
}

export default BaseForm;