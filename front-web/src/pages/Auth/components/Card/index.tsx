
import { saveSessionData } from 'core/utils/auth';
import { makeLogin } from 'core/utils/request';
import React, { useState } from 'react';
import { useForm } from 'react-hook-form';
import { useHistory, useLocation } from 'react-router-dom';
import './styles.scss';

type FormState = {
    username: string;
    password: string;
}

type LocationState = {
    from: string;
}


const AuthCard = () => {

    const { register, handleSubmit, errors } = useForm<FormState>();

    const [hasError, setHasError] = useState(false);

    const history = useHistory();

    const location = useLocation<LocationState>();

    const { from } = location.state || { from: { pathname: "/dashboard" } };

    const onSubmit = (data: FormState) => {
        makeLogin(data)
            .then(response => {
                saveSessionData(response.data);
                history.replace(from);

            })
            .catch(() => {
                setHasError(true);
            });
    };

    return (

        <div className="card-base">
            <h1 className="card-base-title">
                login
            </h1>
            {hasError && (
                <div className="alert alert-danger mt-5">
                    Usuário ou senha invalidos.
                </div>
            )}
            <form className="login-form" onSubmit={handleSubmit(onSubmit)}>
                <input
                    type="text"
                    className="form-control mb-3"
                    placeholder="Usuario"
                    ref={register({ required: "Campo obrigatorio." })}
                    name="username"
                />
                {errors.username && (
                    <div className="invalid-feedback d-block mb-3">
                        {errors.username.message}
                    </div>
                )}
                <div>
                    <input
                        type="password"
                        className="form-control mb-3"
                        placeholder="Senha"
                        ref={register({ required: "Campo obrigatorio." })}
                        name="password"
                    />
                    {errors.password && (
                        <div className="invalid-feedback d-block">
                            {errors.password.message}
                        </div>
                    )}
                </div>

                <div className="button-form">
                    <button className="btn btn-primary mr-5">LOGIN</button>
                    <button className="btn btn-secondary">LIMPAR</button>
                </div>
            </form>

        </div>
    )
}
export default AuthCard;