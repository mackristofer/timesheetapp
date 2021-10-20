import React, { useEffect, useState } from 'react';
import { AdvogadoTs, CasoTs, ClienteTs, TimeSheet } from 'core/objetos/objetos';
import { getSessionData, isAllowedByRole, isAuthenticated } from 'core/utils/auth';
import { makePrivateRequest } from 'core/utils/request';
import { useForm, Controller } from 'react-hook-form';
import Select from 'react-select';
import BaseForm from '../../BaseForm';
import './styles.scss';
import { useHistory, useParams } from 'react-router-dom';
import { toast } from 'react-toastify';

type ParamTypes = {
    timesheetId: string;
}

const Form = () => {
    const { register, handleSubmit, control, setValue, errors } = useForm<TimeSheet>();
    const [cliente, setCliente] = useState<ClienteTs[]>([]);
    const [advogado, setAdvogado] = useState<AdvogadoTs[]>([]);
    const [cliSelect, setCliSelect] = useState<CasoTs>();
    const [cobranca, setCobranca] = useState<Number>(-1);
    const { timesheetId } = useParams<ParamTypes>();
    const history = useHistory();
    const isEditing = timesheetId !== 'create';

    useEffect(() => {
        makePrivateRequest({ url: '/cliente/tsc' })
            .then(response => setCliente(response.data));

    }, []);

    useEffect(() => {
        if (isAuthenticated() && isAllowedByRole(['ROLE_ADMIN'])) {
            makePrivateRequest({ url: '/advogado/tsa' })
                .then(response => setAdvogado(response.data));
        } else if (isAuthenticated() && !isAllowedByRole(['ROLE_ADMIN'])) {
            const adv = { id: getSessionData().id, nome: getSessionData().nome };
            setAdvogado([adv]);
        }
    }, []);

    useEffect(() => {
        if (isEditing) {
            makePrivateRequest({ url: `/timesheet/${timesheetId}` })
                .then(response => {
                    setCobranca(response.data.cobranca);
                    setValue('caso', response.data.caso);
                    setValue('advogado', response.data.advogado);
                    setValue('data', response.data.data);
                    setValue('tempo', response.data.tempo);
                    setValue('descricao', response.data.descricao);
                });
        }
    }, [timesheetId, isEditing, setValue]);

    const handleChange = (valor: any) => {
        if (valor != null) {
            setCliSelect(valor.casos);
        }

    }

    const onChangeValue = (e: any) => {
        setCobranca(Number(e.target.value))
    }

    const onSubmit = (data: TimeSheet) => {
        //console.log(data)
        makePrivateRequest({
            method: isEditing ? 'PUT' : 'POST',
            url: isEditing ? `/timesheet/${timesheetId}` : '/timesheet',
            data
        }).then(() => {
            toast.dark('Timesheet cadastrado com sucesso!');
            history.push('/admin/timesheet');
        }).catch(() => {
            toast.error('Erro ao cadastrar!')
        })
    }

    return (
        <form onSubmit={handleSubmit(onSubmit)}>
            <BaseForm>

                <div className="container">
                    <div className="row mb-4">
                        <div className="col">

                            <Select
                                name="cliente"
                                onChange={handleChange}
                                options={cliente}
                                getOptionLabel={(option: ClienteTs) => option.razao}
                                getOptionValue={(option: ClienteTs) => String(option.id)}
                                isClearable={true}
                                isSearchable={true}
                                placeholder="Selecione um cliente"

                            />
                        </div>
                        <div className="col">
                            <Controller
                                as={Select}
                                name="caso"
                                control={control}
                                rules={{ required: true }}
                                options={cliSelect}
                                getOptionLabel={(option: CasoTs) => option.descricao}
                                getOptionValue={(option: CasoTs) => String(option.id)}
                                isClearable={true}
                                isSearchable={true}
                                placeholder="Selecione um caso"
                            />
                            {errors.caso && (
                                <div className="invalid-feedback d-block">
                                    O caso deve ser preenchido.
                                </div>
                            )}
                        </div>
                    </div>
                    <div className="row mb-4">
                        <div className="col-6">
                            <Controller
                                as={Select}
                                name="advogado"
                                control={control}
                                rules={{ required: true }}
                                options={advogado}
                                getOptionLabel={(option: AdvogadoTs) => option.nome}
                                getOptionValue={(option: AdvogadoTs) => String(option.id)}
                                isClearable={true}
                                isSearchable={true}
                                placeholder="Selecione um advogado."
                            />
                            {errors.advogado && (
                                <div className="invalid-feedback d-block">
                                    O advogado deve ser preenchido.
                                </div>
                            )}

                        </div>
                        <div className="col-sm">
                            <div className="form-outline">
                                <input type="date"
                                    className="form-control"
                                    placeholder="Data"
                                    ref={register({ required: "Campo deve ser preenchido." })}
                                    name="data"
                                />
                                {errors.data && (
                                    <div className="invalid-feedback d-block">
                                        {errors.data.message}
                                    </div>
                                )}
                            </div>
                        </div>
                        <div className="col-sm">
                            <div className="form-outline">
                                <input
                                    type="text"
                                    className="form-control"
                                    placeholder="Tempo"
                                    ref={register({ required: "Campo deve ser preenchido." })}
                                    name="tempo"
                                />
                                {errors.tempo && (
                                    <div className="invalid-feedback d-block">
                                        {errors.tempo.message}
                                    </div>
                                )}
                            </div>
                        </div>
                    </div>
                    <div className="form-outline">
                        <textarea

                            ref={register({ required: "Campo deve ser preenchido." })}
                            name="descricao"
                            cols={95}
                            rows={10}
                            placeholder="Descricao"
                        />
                        {errors.descricao && (
                            <div className="invalid-feedback d-block">
                                {errors.descricao.message}
                            </div>
                        )}
                    </div>

                    {isAllowedByRole(["ROLE_ADMIN"]) && (
                        <div className="row mr-2 mt-4">
                            <label className="ml-2 mr-2">Cobrança:</label>
                            <div className="form-check mr-2">
                                {!isEditing ? (
                                    <input
                                        type="radio"
                                        className="form-check-input"
                                        value={1}
                                        ref={register({ required: true })}
                                        name="cobranca"
                                        defaultChecked
                                    />
                                ) : (
                                    <input
                                        type="radio"
                                        className="form-check-input"
                                        value={1}
                                        ref={register({ required: true })}
                                        name="cobranca"
                                        checked={cobranca === 1}
                                        onChange={onChangeValue}
                                    />
                                )}

                                <label className="form-check-label">Sim</label>
                            </div>
                            <div className="form-check">
                                <input
                                    type="radio"
                                    className="form-check-input"
                                    value={0}
                                    ref={register({ required: true })}
                                    name="cobranca"
                                    checked={cobranca === 0}
                                    onChange={onChangeValue}
                                />
                                <label className="form-check-label">Nao</label>
                            </div>
                            {errors.data && (
                                <div className="invalid-feedback d-block">
                                    Informar se é para cobrança ou não
                                </div>
                            )}
                        </div>
                    )}
                </div>

            </BaseForm>
        </form>

    )
}

export default Form;