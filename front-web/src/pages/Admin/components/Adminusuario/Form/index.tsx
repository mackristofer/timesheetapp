import { AdvogadoTs, Roles, Usuario } from 'core/objetos/objetos';
import { makePrivateRequest } from 'core/utils/request';
import React, { useEffect, useState } from 'react';
import { Controller, useForm } from 'react-hook-form';
import { useHistory, useParams } from 'react-router-dom';
import Select from 'react-select';
import { toast } from 'react-toastify';
import BaseForm from '../../BaseForm';
import './styles.scss';

type ParamTypes = {
    usuarioId: string;
}
const Form = () => {

    const { register, handleSubmit, control, setValue, errors } = useForm<Usuario>();
    const [advogado, setAdvogado] = useState<AdvogadoTs[]>([]);
    const [roles, setRoles] = useState<Roles[]>([]);
    const { usuarioId } = useParams<ParamTypes>();
    const history = useHistory();
    const isEditing = usuarioId !== 'create';

    useEffect(() => {
        makePrivateRequest({ url: '/advogado/tsa' })
            .then(response => setAdvogado(response.data));

    }, []);

    useEffect(() => {
        makePrivateRequest({ url: '/role' })
            .then(response => setRoles(response.data));

    }, []);

    useEffect(() => {
        if (isEditing) {
            makePrivateRequest({ url: `/usuario/${usuarioId}` })
                .then(response => {
                    setValue('usuario', response.data.usuario);
                    setValue('advogado', response.data.advogado);
                    setValue('roles', response.data.roles);
                });
        }
    }, [usuarioId, isEditing, setValue]);

    const onSubmit = (data: Usuario) => {
        console.log(data)
        makePrivateRequest({
            method: isEditing ? 'PUT' : 'POST',
            url: isEditing ? `/usuario/${usuarioId}` : '/usuario',
            data
        }).then(() => {
            toast.dark('Usuário cadastrado com sucesso!');
            history.push('/admin/usuario');
        }).catch(() => {
            toast.error('Erro ao cadastrar!')
        })
    }

    return (
        <form onSubmit={handleSubmit(onSubmit)}>
            <BaseForm>
                <div className="container">
                    <div className="row mb-4">
                        <div className="col-4">
                            <div className="form-outline">
                                <input type="text"
                                    className="form-control"
                                    placeholder="Nome do usuário"
                                    ref={register({ required: "Campo deve ser preenchido." })}
                                    name="usuario"
                                />
                                {errors.usuario && (
                                    <div className="invalid-feedback d-block">
                                        {errors.usuario.message}
                                    </div>
                                )}
                            </div>
                        </div>
                    </div>
                    <div className="row mb-4">
                        <div className="col-4">
                            <div className="form-outline">
                                <input type="password"
                                    className="form-control"
                                    placeholder="Senha"
                                    ref={register({ required: "Campo deve ser preenchido." })}
                                    name="password"
                                />
                                {errors.password && (
                                    <div className="invalid-feedback d-block">
                                        {errors.password.message}
                                    </div>
                                )}
                            </div>
                        </div>
                    </div>
                    <div className="row mb-4">
                        <div className="col-6">
                            <Controller
                                as={Select}
                                name="advogado"
                                rules={{ required: true }}
                                control={control}
                                options={advogado}
                                getOptionLabel={(option: AdvogadoTs) => option.nome}
                                getOptionValue={(option: AdvogadoTs) => String(option.id)}
                                isClearable={true}
                                isSearchable={true}
                                placeholder="Nome do advogado"
                            />
                            {errors.advogado && (
                                <div className="invalid-feedback d-block">
                                    Campo deve ser preenchido
                                </div>
                            )}
                        </div>
                    </div>

                    <div className="row mb-4">
                        <div className="col-6">
                            <Controller
                                as={Select}
                                name="roles"
                                rules={{ required: true }}
                                control={control}
                                options={roles}
                                getOptionLabel={(option: Roles) => option.authority}
                                getOptionValue={(option: Roles) => String(option.id)}
                                isMulti
                                isClearable={true}
                                isSearchable={true}
                                placeholder="Perfil de acesso"
                            />
                            {errors.roles && (
                                <div className="invalid-feedback d-block">
                                    Campo deve ser preenchido
                                </div>
                            )}
                        </div>
                    </div>
                </div>
            </BaseForm>
        </form>
    )
}

export default Form;