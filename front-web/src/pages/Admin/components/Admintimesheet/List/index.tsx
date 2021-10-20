import { TimeSheetResponse } from 'core/objetos/objetos';
import { makePrivateRequest } from 'core/utils/request';
import React, { useEffect } from 'react';
import { useState } from 'react';
import { useHistory } from 'react-router-dom';
import Card from '../../Card';
import { toast } from 'react-toastify';
import { getSessionData, isAllowedByRole, isAuthenticated } from 'core/utils/auth';


const List = () => {
    const history = useHistory();

    const [timesheetresp, setTimesheetResp] = useState<TimeSheetResponse[]>();
    const [del, setDel] = useState(false);

    const formataData = (dtf: String) => {   
        const [year, month, day] =  dtf.split('-')
        return `${day}/${month}/${year}`
    }
    useEffect(() => {
        if (isAuthenticated() && isAllowedByRole(['ROLE_ADMIN'])) {
            makePrivateRequest({ url: '/timesheet' })
                .then(response => setTimesheetResp(response.data));
        } else if (isAuthenticated() && !isAllowedByRole(['ROLE_ADMIN'])){
            makePrivateRequest({ url: `/timesheet/byadv/${getSessionData().id}` })
                .then(response => setTimesheetResp(response.data));
        }
    }, [del]);

    const handleCreate = () => {
        history.push('/admin/timesheet/create');
    }

    const onRemove = (timesheetId: number | undefined) => {
        makePrivateRequest({ url: `/timesheet/${timesheetId}`, method: 'DELETE' })
            .then(() => {
                toast.dark('Timesheet excluido com sucesso!');
                history.push('/admin/timesheet');
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
                {timesheetresp?.map(t => (
                    <Card destino="/admin/timesheet"
                        id={t.id}
                        titulo={t.caso.descricao}
                        subtitulo={t.advogado.nome}
                        descricao={t.descricao}
                        data={formataData(t.data)}
                        onRemove={onRemove}
                    />
                ))}


            </div>
        </div>

    );
}

export default List;