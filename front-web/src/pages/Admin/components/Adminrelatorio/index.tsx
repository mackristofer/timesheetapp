import React, { useEffect } from 'react';
import Select from 'react-select';
import { useHistory } from 'react-router-dom';
import './styles.scss';
import { useState } from 'react';
import { ClienteTs } from 'core/objetos/objetos';
import { makePrivateRequest } from 'core/utils/request';


const Admrelatorio = () => {
    
    const history = useHistory();

    const [cliId, setCliId] = useState('');
    const [dtIn, setDtIn] = useState('');
    const [dtOut, setDtOut] = useState('');
    const [cliente, setCliente] = useState<ClienteTs[]>([]);

    useEffect(() => {
        makePrivateRequest({url:'/cliente/tsc'})
        .then(response => setCliente(response.data));

    }, []);

    const handleOnChangeDtIn = (event: React.ChangeEvent<HTMLInputElement>) => {
        setDtIn(event.target.value);
    }

    const handleOnChangeDtOut = (event: React.ChangeEvent<HTMLInputElement>) => {
        setDtOut(event.target.value);
    }

    const handleChangeCli = (event: any) => {
        if(event != null){
            setCliId(event.id);
        }
    }

    const handleClick = () => {       
        history.push(`/admin/rel/cli/${cliId}/${dtIn}/${dtOut}`);   
        }



    return (
        <div className="admin-base-form">
            <div className="row mb-4">

                <div className="col-12">
                    <Select isClearable={true} isSearchable={true} options={cliente} 
                            getOptionLabel={(option: ClienteTs) => option.razao}
                            getOptionValue={(option: ClienteTs) => String(option.id)}
                            onChange={handleChangeCli}
                            placeholder="Selecione o cliente" />

                </div>

            </div>
            <div className="row mb-4">

                <div className="col-sm">
                    <div className="form-outline">
                        <input type="date"
                            className="form-control"
                            placeholder="Data inicial"
                            onChange={handleOnChangeDtIn}
                        />
                    </div>
                </div>
                <div className="col-sm">
                    <div className="form-outline">
                        <input
                            type="date"
                            className="form-control"
                            placeholder="Data final"
                            onChange={handleOnChangeDtOut}
                        />
                    </div>
                </div>
            </div>
            <div className="base-form-actions">
                <button className="btn btn-outline-secondary border-radius-10 mr-3"
                onClick={handleClick}
                >
                    GERAR RELATÓRIO
            </button>
            </div>
        </div>
    );
}
export default Admrelatorio;