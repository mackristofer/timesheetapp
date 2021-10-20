import { Caso } from 'core/objetos/objetos';
import { makePrivateRequest } from 'core/utils/request';
import React, { useEffect, useState } from 'react';
import { Controller, useForm } from 'react-hook-form';
import { useHistory, useParams } from 'react-router-dom';
import Select from 'react-select';
import { toast } from 'react-toastify';
import BaseForm from '../../BaseForm';
import './styles.scss';

type ParamTypes = {
    casoId: string;
}

type ClienteResponse = {
    id: number;
    razao: string;
}
const Form = () => {

    const { register, handleSubmit, control, setValue, errors } = useForm<Caso>();
    const [cliente, setCliente] = useState<ClienteResponse[]>([]);
    const { casoId } = useParams<ParamTypes>();
    const history = useHistory();
    const isEditing = casoId !== 'create';

    useEffect(() => {
        makePrivateRequest({ url: '/cliente/tsc' })
            .then(response => setCliente(response.data));

    }, []);

    useEffect(() => {
        if (isEditing) {
            makePrivateRequest({ url: `/caso/${casoId}` })
                .then(response => {
                    setValue('descricao', response.data.descricao);
                    setValue('statusCobranca', response.data.statusCobranca);
                    setValue('valor', response.data.valor);
                    setValue('cliente', response.data.cliente);
                    setValue('tipoContrato', response.data.tipoContrato);
                    setValue('desconto', response.data.desconto);
                });
        }
    }, [casoId, isEditing, setValue]);

    const onSubmit = (data: Caso) => {
        console.log(data)
        makePrivateRequest({
            method: isEditing ? 'PUT' : 'POST',
            url: isEditing ? `/caso/${casoId}` : '/caso',
            data
        }).then(() => {
            toast.dark('Caso cadastrado com sucesso!');
            history.push('/admin/caso');
        }).catch(() => {
            toast.error('Erro ao cadastrar!')
        })
    }

    return (
        <form onSubmit={handleSubmit(onSubmit)}>
            <BaseForm>
                <div className="container">

                    <div className="row mb-4">
                        <div className="col-8">
                            <Controller
                                as={Select}
                                name="cliente"
                                rules={{ required: true }}
                                control={control}
                                isClearable={true}
                                isSearchable={true}
                                options={cliente}
                                getOptionLabel={(option: ClienteResponse) => option.razao}
                                getOptionValue={(option: ClienteResponse) => String(option.id)}
                                placeholder="Cliente"
                            />
                            {errors.cliente && (
                                <div className="invalid-feedback d-block">
                                    O cliente deve ser preenchido.
                                </div>
                            )}
                        </div>

                    </div>

                    <div className="row mb-4">
                        <div className="col-8">
                            <div className="form-outline">
                                <input type="text"
                                    className="form-control"
                                    placeholder="Descrição"
                                    ref={register({ required: "Campo deve ser preenchido." })}
                                    name="descricao"
                                />
                                {errors.descricao && (
                                    <div className="invalid-feedback d-block">
                                        {errors.descricao.message}
                                    </div>
                                )}
                            </div>
                        </div>
                    </div>

                    <div className="row mb-4">
                        <div className="col-4">
                            <div className="form-outline">
                                <input
                                    type="text"
                                    className="form-control"
                                    placeholder="Valor"
                                    ref={register({ required: "Campo não pode ser nulo" })}
                                    name="valor"
                                />
                                {errors.valor && (
                                    <div className="invalid-feedback d-block">
                                        {errors.valor.message}
                                    </div>
                                )}
                            </div>
                        </div>
                        <div className="col-4">
                            <div className="form-outline">
                                <input type="text"
                                    className="form-control"
                                    placeholder="Desconto"
                                    ref={register}
                                    name="desconto"
                                />
                            </div>
                        </div>
                    </div>

                    <div className="row mr-2 mt-4">
                        <label className="ml-2 mr-2">Tipo contrato:</label>
                        <div className="form-check mr-2">
                            <input
                                type="radio"
                                className="form-check-input"
                                value={1}
                                ref={register({ required: true })}
                                name="tipoContrato"
                            />
                            <label className="form-check-label">Fechado</label>
                        </div>
                        <div className="form-check">
                            <input
                                type="radio"
                                className="form-check-input"
                                value={0}

                                ref={register({ required: true })}

                                name="tipoContrato"
                            />
                            <label className="form-check-label">Hora</label>
                        </div>
                        {errors.tipoContrato && (
                            <div className="invalid-feedback d-block">
                                Informar o tipo de contrato.
                            </div>
                        )}
                    </div>

                </div>
            </BaseForm>
        </form>
    )
}

export default Form;