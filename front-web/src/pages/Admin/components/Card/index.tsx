import React from 'react';
import { Link } from 'react-router-dom';
import './styles.scss';

type Props = {
    destino?:string;
    id?:number;
    titulo?:string | number;
    subtitulo?:string | number;
    data?:string;
    descricao?:string;
    onRemove: (id:number|undefined) => void;
}
const Card = ({destino, id, titulo, subtitulo, data, descricao, onRemove}: Props) => {
    return (
        <div className="card-base admin-card-timesheet">
            <div className="row">
                <div className="col-8 py-3 pl-3">
                    <p>{titulo}</p>
                    <p>{subtitulo}</p>
                    <p>{data}</p>
                    <p>{descricao}</p>
                </div>
                <div className="col-3 pt-4 pr-2">
                <Link 
                to={`${destino}/${id}`}
                type="button" className="btn btn-outline-secondary btn-block mb-3">EDITAR</Link>
                <button type="button" className="btn btn-outline-danger btn-block"
                onClick={() => onRemove(id)}>EXCLUIR</button>
                </div>
            </div>
        </div>
    )
}

export default Card;