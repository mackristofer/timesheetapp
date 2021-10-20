import { ClienteResponse } from 'core/objetos/objetos';
import { makePrivateRequest } from 'core/utils/request';
import React from 'react';
import { useEffect } from 'react';
import { useState } from 'react';
import { useHistory } from 'react-router-dom';
import { toast } from 'react-toastify';
import Card from '../../Card';

const List = () => {
    
    const history = useHistory();

    const [cliente, setCliente] = useState<ClienteResponse[]>();
    const [del, setDel] = useState(false);

    useEffect(() => {
        makePrivateRequest({url:'/cliente'})
        .then(response => setCliente(response.data));
    }, [del]);


    const handleCreate = () =>{
        history.push('/admin/cliente/create');
    }

    const onRemove = (clienteId: number | undefined) => {
        makePrivateRequest({url: `/cliente/${clienteId}`, method: 'DELETE'})
        .then(() => {
            toast.dark('Cliente excluido com sucesso!');
            history.push('/admin/cliente');
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
                {cliente?.map(c => (
                    <Card destino="/admin/cliente"
                    id={c.id}
                    titulo={c.razao}
                    subtitulo={c.cnpjCpf}
                    descricao={c.contato}
                    onRemove={onRemove}
                    />
                ))}
            </div>
        </div>

    );
}

export default List;