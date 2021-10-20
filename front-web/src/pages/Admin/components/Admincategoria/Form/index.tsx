import { Categoria } from 'core/objetos/objetos';
import { makePrivateRequest } from 'core/utils/request';
import React, { useEffect } from 'react';
import { useForm } from 'react-hook-form';
import { useHistory, useParams } from 'react-router-dom';
import { toast } from 'react-toastify';
import BaseForm from '../../BaseForm';
import './styles.scss';

type ParamTypes = {
    categoriaId: string;
}
const Form = () => {

    const { register, handleSubmit, setValue, errors } = useForm<Categoria>();
    const { categoriaId } = useParams<ParamTypes>();
    const history = useHistory();
    const isEditing = categoriaId !== 'create';

    useEffect(() => {
        if (isEditing) {
            makePrivateRequest({ url: `/categoria/${categoriaId}` })
                .then(response => {
                    setValue('descricao', response.data.descricao);
                    setValue('valorHora', response.data.valorHora);
                });
        }
    }, [categoriaId, isEditing, setValue]);

    const onSubmit = (data: Categoria) => {
        //console.log(data)
        makePrivateRequest({
            method: isEditing ? 'PUT' : 'POST',
            url: isEditing ? `/categoria/${categoriaId}` : '/categoria',
            data
        }).then(() => {
            toast.dark('Timesheet cadastrado com sucesso!');
            history.push('/admin/categoria');
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
                        <div className="col-3">
                            <div className="form-outline">
                                <input
                                    type="text"
                                    className="form-control"
                                    placeholder="Valor"
                                    ref={register({ required: "Campo nao pode ser nulo." })}
                                    name="valorHora"
                                />
                                {errors.valorHora && (
                                    <div className="invalid-feedback d-block">
                                        {errors.valorHora.message}
                                    </div>
                                )}
                            </div>
                        </div>
                    </div>
                </div>
            </BaseForm>
        </form>
    )
}

export default Form;