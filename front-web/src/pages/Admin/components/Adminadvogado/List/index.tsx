import { Advogado } from 'core/objetos/objetos';
import { makePrivateRequest } from 'core/utils/request';
import React, { useEffect, useState } from 'react';
import { useHistory } from 'react-router-dom';
import { toast } from 'react-toastify';
import Card from '../../Card';

const List = () => {
    const history = useHistory();

    const [advogado, setAdvogado] = useState<Advogado[]>();
    const [del, setDel] = useState(false);

    useEffect(() => {
        makePrivateRequest({url:'/advogado'})
        .then(response => setAdvogado(response.data));
    }, [del]);
    
    const handleCreate = () =>{
        history.push('/admin/advogado/create');
    }

    const onRemove = (advId: number | undefined) => {
        makePrivateRequest({url: `/advogado/${advId}`, method: 'DELETE'})
        .then(() => {
            toast.dark('Timesheet excluido com sucesso!');
            history.push('/admin/advogado');
            setDel(!del);
        }).catch(() => {
            toast.error('Erro ao excluir!')
        })
    }
    return(

        <div className="admin-timesheet-list">
            <button className="btn btn-primary btn-lg" onClick={handleCreate}>
            ADICIONAR
            </button>
            <div className="admin-list-container">
                {advogado?.map(a => (
                    <Card destino="/admin/advogado"
                    id={a.id}
                    titulo={a.nome}
                    subtitulo={a.cpf}
                    descricao={a.categoria.descricao}
                    data={a.telefone}
                    onRemove={onRemove}
                    />
                ))}

            </div>
        </div>

    );
}

export default List;