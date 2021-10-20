import { Caso } from 'core/objetos/objetos';
import { makePrivateRequest } from 'core/utils/request';
import React, { useEffect, useState } from 'react';
import { useHistory } from 'react-router-dom';
import { toast } from 'react-toastify';
import Card from '../../Card';

const List = () => {
    const history = useHistory();

    const [caso, setCaso] = useState<Caso[]>();
    const [del, setDel] = useState(false);

    useEffect(() => {
        makePrivateRequest({ url: '/caso' })
            .then(response => setCaso(response.data));
    }, [del]);

    const handleCreate = () => {
        history.push('/admin/caso/create');
    }

    const onRemove = (casoId: number | undefined) => {
        makePrivateRequest({ url: `/caso/${casoId}`, method: 'DELETE' })
            .then(() => {
                toast.dark('Timesheet excluido com sucesso!');
                history.push('/admin/caso');
                setDel(!del);
            }).catch(() => {
                toast.error('Erro ao excluir!')
            })
    }
    return (

        <div className="admin-timesheet-list">
            <button className="btn btn-primary btn-lg" onClick={handleCreate}>
                ADICIONAR
            </button>
            <div className="admin-list-container">
                {caso?.map(c => (
                    <Card
                        destino="/admin/caso"
                        id={c.id}
                        titulo={c.descricao}
                        subtitulo={new Intl.NumberFormat('pt-BR', {style: 'currency', currency: 'BRL'}).format(c.valor)}
                        onRemove={onRemove}
                    />
                ))}

            </div>
        </div>

    );
}

export default List;