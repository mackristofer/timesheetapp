import { Categoria } from 'core/objetos/objetos';
import { makePrivateRequest } from 'core/utils/request';
import React, { useEffect, useState } from 'react';
import { useHistory } from 'react-router-dom';
import { toast } from 'react-toastify';
import Card from '../../Card';

const List = () => {
    const history = useHistory();

    const [categoria, setCategoria] = useState<Categoria[]>();
    const [del, setDel] = useState(false);

    useEffect(() => {
        makePrivateRequest({url:'/categoria'})
        .then(response => setCategoria(response.data));
    }, [del]);
    
    const handleCreate = () =>{
        history.push('/admin/categoria/create');
    }

    const onRemove = (categoriaId: number | undefined) => {
        makePrivateRequest({url: `/categoria/${categoriaId}`, method: 'DELETE'})
        .then(() => {
            toast.dark('Timesheet excluido com sucesso!');
            history.push('/admin/categoria');
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
                {categoria?.map(c => (
                    <Card
                    destino="/admin/categoria"
                    id={c.id}
                    titulo={c.descricao}
                    subtitulo={new Intl.NumberFormat('pt-BR', {style: 'currency', currency: 'BRL'}).format(c.valorHora)}
                    onRemove={onRemove} 
                    />
                ))}
            </div>
        </div>

    );
}

export default List;