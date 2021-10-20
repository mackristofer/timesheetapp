import React from 'react';
import { Route, Switch } from 'react-router-dom';
import Form from './Form';
import List from './List';

import './styles.scss';

const Admusuario= () => {
    return(
        <div>
            <Switch>
                <Route path="/admin/usuario" exact>
                    <List />
                </Route>
                <Route path="/admin/usuario/:usuarioId">
                <Form />
                </Route>
            </Switch>
        </div>
    );
}
export default Admusuario;