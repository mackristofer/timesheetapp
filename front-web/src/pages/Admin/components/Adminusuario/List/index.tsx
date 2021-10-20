import { Usuario } from 'core/objetos/objetos';
import { makePrivateRequest } from 'core/utils/request';
import React, { useEffect, useState } from 'react';
import { useHistory } from 'react-router-dom';
import { toast } from 'react-toastify';
import Card from '../../Card';

const List = () => {
    const history = useHistory();

    const [usuario, setUsuario] = useState<Usuario[]>();
    const [del, setDel] = useState(false);

    useEffect(() => {
        makePrivateRequest({url:'/usuario'})
        .then(response => setUsuario(response.data));
    }, [del]);
    
    const handleCreate = () =>{
        history.push('/admin/usuario/create');
    }

    const onRemove = (usuarioId: number | undefined) => {
        makePrivateRequest({url: `/usuario/${usuarioId}`, method: 'DELETE'})
        .then(() => {
            toast.dark('Usuario excluido com sucesso!');
            history.push('/admin/usuario');
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
                {usuario?.map(u => (
                    <Card
                    destino="/admin/usuario"
                    id={u.id}
                    titulo={u.usuario}
                    subtitulo={u.advogado.nome}
                    onRemove={onRemove} 
                    />
                ))}
            </div>
        </div>

    );
}

export default List;