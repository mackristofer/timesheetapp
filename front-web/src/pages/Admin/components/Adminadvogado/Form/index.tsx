import { Advogado, Categoria } from 'core/objetos/objetos';
import { makePrivateRequest } from 'core/utils/request';
import React, { useEffect } from 'react';
import { useState } from 'react';
import { Controller, useForm } from 'react-hook-form';
import { useHistory, useParams } from 'react-router-dom';
import Select from 'react-select';
import { toast } from 'react-toastify';
import BaseForm from '../../BaseForm';
import './styles.scss';

type ParamTypes = {
    advId: string;
}
const Form = () => {

    const { register, handleSubmit, control, setValue, errors } = useForm<Advogado>();
    const [categoria, setCategoria] = useState<Categoria[]>();
    const { advId } = useParams<ParamTypes>();
    const history = useHistory();
    const isEditing = advId !== 'create';

    useEffect(() => {
        if (isEditing) {
            makePrivateRequest({ url: `/advogado/${advId}` })
                .then(response => {
                    setValue('nome', response.data.nome);
                    setValue('cpf', response.data.cpf);
                    setValue('endereco', response.data.endereco);
                    setValue('complemento', response.data.complemento);
                    setValue('bairro', response.data.bairro);
                    setValue('cidade', response.data.cidade);
                    setValue('uf', response.data.uf);
                    setValue('cep', response.data.cep);
                    setValue('telefone', response.data.telefone);
                    setValue('categoria', response.data.categoria);
                });
        }
    }, [advId, isEditing, setValue]);

    useEffect(() => {
        makePrivateRequest({ url: '/categoria' })
            .then(response => setCategoria(response.data));

    }, []);

    const onSubmit = (data: Advogado) => {
        //console.log(data)
        makePrivateRequest({
            method: isEditing ? 'PUT' : 'POST',
            url: isEditing ? `/advogado/${advId}` : '/advogado',
            data
        }).then(() => {
            toast.dark('Advogado cadastrado com sucesso!');
            history.push('/admin/advogado');
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
                                    placeholder="Nome do advogado"
                                    ref={register({ required: "Campo deve ser preenchido." })}
                                    name="nome"
                                />
                                {errors.nome && (
                                    <div className="invalid-feedback d-block">
                                        {errors.nome.message}
                                    </div>
                                )}
                            </div>
                        </div>
                        <div className="col-4">
                            <div className="form-outline">
                                <input
                                    type="text"
                                    className="form-control"
                                    placeholder="CNPJ/CPF/OAB"
                                    ref={register({ required: "Campo deve ser preenchido." })}
                                    name="cpf"
                                />
                                {errors.cpf && (
                                    <div className="invalid-feedback d-block">
                                        {errors.cpf.message}
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
                        <div className="col-6">
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

                            <Controller
                                as={Select}
                                name="categoria"
                                rules={{required: true}}
                                control={control}
                                options={categoria}
                                getOptionLabel={(option: Categoria) => option.descricao}
                                getOptionValue={(option: Categoria) => String(option.id)}
                                isClearable={true}
                                isSearchable={true}
                                placeholder="Categoria"
                            />
                                {errors.categoria && (
                                    <div className="invalid-feedback d-block">
                                       Campo deve ser preenchido.
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