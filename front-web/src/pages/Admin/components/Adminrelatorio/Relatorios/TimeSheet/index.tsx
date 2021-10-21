import { makePrivateRequest } from 'core/utils/request';
import React from 'react';
import { useEffect } from 'react';
import { useState } from 'react';
import { useParams } from 'react-router-dom';
import './styles.scss';

type DataResponse = {
    nomeCliente: string;
    valorTotalNota: number;
    casoByAdv: [
        {
            nome: string;
            totalHoraCaso: string;
            valorTotalCaso: number;
            sheetByAdv: [
                {
                    nomeAdv: string;
                    horaF: string;
                    timeSheet: [
                        {
                            id: number;
                            data: string;
                            tempo: string;
                            descricao: string;
                        }
                    ]
                }
            ]
        }
    ]
}


type UrlParamTypes = {
    cliId: string;
    dtIn: string;
    dtOut: string;
}

const RelTimeSheet = () => {

    const [r, setR] = useState<DataResponse>();

    const { cliId, dtIn, dtOut } = useParams<UrlParamTypes>();

    useEffect(() => {
        makePrivateRequest({ url: `/relatorio/${cliId}/${dtIn}/${dtOut}` })
            .then(response => setR(response.data));

    }, [cliId, dtIn, dtOut]);

    const formataData = (dtf: String) => {
        const [year, month, day] = dtf.split('-')
        return `${day}/${month}/${year}`
    }

    return (
        <div className="container">
            <div>
                <h1 className="title-nome-cli">{r?.nomeCliente}</h1>                 
                <div>
                    {r?.casoByAdv.map(x => (             
                        <>
                            <div className="title-caso"><p>CASO: {x.nome}</p></div>
                            <div>
                                <p>{x.sheetByAdv.map(y => (
                                    <>
                                        <div className="title-advogado"><p>NOME DO ADVOGADO: {y.nomeAdv}</p></div>

                                        {y.timeSheet.map(z => (
                                            <>
                                                <div className="card-timesheet">
                                                    <div>
                                                        <p>DATA: {formataData(z.data)}</p>
                                                        <p>DESCRICAO: {z.descricao}</p>
                                                    </div>
                                                    <p>TEMPO: {z.tempo}</p>

                                                </div>
                                            </>
                                        ))}
                                        <div className="hora-total-adv"><p>Horas total do Advogado: {y.horaF}</p></div>
                                    </>
                                ))}</p>
                            </div>
                            <div className="card-total-hora-inside"><p>Horas total do caso: {x.totalHoraCaso}</p></div>
                            <div className="valor-total-nota"><p>Valor total do caso: {new Intl.NumberFormat('pt-BR', { style: 'currency', currency: 'BRL' }).format(x.valorTotalCaso)}</p></div>
                        </>
                        
                    ))}
 {r?.valorTotalNota && <div className="valor-total-nota"><p>Valor total da nota: {new Intl.NumberFormat('pt-BR', { style: 'currency', currency: 'BRL' }).format(r.valorTotalNota)}</p></div>}
                </div>
                
            </div>
        </div>
    )
}
export default RelTimeSheet;