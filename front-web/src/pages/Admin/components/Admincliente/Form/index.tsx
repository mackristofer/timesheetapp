import { Cliente } from 'core/objetos/objetos';
import { makePrivateRequest } from 'core/utils/request';
import React, { useEffect } from 'react';
import { useForm } from 'react-hook-form';
import { useHistory, useParams } from 'react-router-dom';
import { toast } from 'react-toastify';
import BaseForm from '../../BaseForm';
import './styles.scss';

type ParamTypes = {
    clienteId: string;
}

const Form = () => {

    const { register, handleSubmit, setValue, errors } = useForm<Cliente>();
    const { clienteId } = useParams<ParamTypes>();
    const history = useHistory();
    const isEditing = clienteId !== 'create';

    useEffect(() => {
        if (isEditing) {
            makePrivateRequest({ url: `/cliente/${clienteId}` })
                .then(response => {
                    setValue('razao', response.data.razao);
                    setValue('cnpjCpf', response.data.cnpjCpf);
                    setValue('endereco', response.data.endereco);
                    setValue('complemento', response.data.complemento);
                    setValue('bairro', response.data.bairro);
                    setValue('cidade', response.data.cidade);
                    setValue('uf', response.data.uf);
                    setValue('cep', response.data.cep);
                    setValue('telefone', response.data.telefone);
                    setValue('contato', response.data.contato);
                    setValue('obs', response.data.obs);
                });
        }
    }, [clienteId, isEditing, setValue]);


    const onSubmit = (data: Cliente) => {
        makePrivateRequest({
            method: isEditing ? 'PUT' : 'POST',
            url: isEditing ? `/cliente/${clienteId}` : '/cliente',
            data
        }).then(() => {
            toast.dark('Timesheet cadastrado com sucesso!');
            history.push('/admin/cliente');
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
                                    placeholder="Nome do cliente"
                                    ref={register({ required: "Campo deve ser preenchido." })}
                                    name="razao"
                                />
                                {errors.razao && (
                                    <div className="invalid-feedback d-block">
                                        {errors.razao.message}
                                    </div>
                                )}
                            </div>
                        </div>
                        <div className="col-4">
                            <div className="form-outline">
                                <input
                                    type="text"
                                    className="form-control"
                                    placeholder="CNPJ/CPF"
                                    ref={register({ required: "Campo deve ser preenchido." })}
                                    name="cnpjCpf"
                                />
                                {errors.cnpjCpf && (
                                    <div className="invalid-feedback d-block">
                                        {errors.cnpjCpf.message}
                                    </div>
                                )}
                            </div>
                        </div>
                    </div>
                    <div className="row mb-4">
                        <div className="col-6">
                            <div className="form-outline">
                                <input type="text"
                                    className="form-control"
                                    placeholder="Endereço"
                                    ref={register}
                                    name="endereco"
                                />
                            </div>
                        </div>
                        <div className="col-3">
                            <div className="form-outline">
                                <input
                                    type="text"
                                    className="form-control"
                                    placeholder="Complemento"
                                    ref={register}
                                    name="complemento"
                                />
                            </div>
                        </div>
                        <div className="col-3">
                            <div className="form-outline">
                                <input
                                    type="text"
                                    className="form-control"
                                    placeholder="Bairro"
                                    ref={register}
                                    name="bairro"
                                />
                            </div>
                        </div>
                    </div>
                    <div className="row mb-4">
                        <div className="col-6">
                            <div className="form-outline">
                                <input type="text"
                                    className="form-control"
                                    placeholder="Cidade"
                                    ref={register}
                                    name="cidade"
                                />
                            </div>
                        </div>
                        <div className="col-3">
                            <div className="form-outline">
                                <input
                                    type="text"
                                    className="form-control"
                                    placeholder="UF"
                                    ref={register}
                                    name="uf"
                                />
                            </div>
                        </div>
                        <div className="col-3">
                            <div className="form-outline">
                                <input
                                    type="text"
                                    className="form-control"
                                    placeholder="CEP"
                                    ref={register}
                                    name="cep"
                                />
                            </div>
                        </div>
                    </div>
                    <div className="row mb-4">
                        <div className="col-3">
                            <div className="form-outline">
                                <input type="text"
                                    className="form-control"
                                    placeholder="Telefone"
                                    ref={register}
                                    name="telefone"
                                />
                            </div>
                        </div>
                        <div className="col-6">
                            <div className="form-outline">
                                <input
                                    type="text"
                                    className="form-control"
                                    placeholder="Contato"
                                    ref={register}
                                    name="contato"
                                />
                            </div>
                        </div>
                    </div>
                    <div className="form-outline">
                        <textarea
                            cols={95}
                            rows={10}
                            placeholder="Observação"
                            ref={register}
                            name="obs"
                        />
                    </div>
                </div>
            </BaseForm>
        </form>
    )
}

export default Form;