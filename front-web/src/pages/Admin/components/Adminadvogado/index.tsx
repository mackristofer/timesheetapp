import React from 'react';
import { Route, Switch } from 'react-router-dom';
import Form from './Form';
import List from './List';

import './styles.scss';

const Admadvogado= () => {
    return(
        <div>
            <Switch>
                <Route path="/admin/advogado" exact>
                    <List />
                </Route>
                <Route path="/admin/advogado/:advId">
                <Form />
                </Route>
            </Switch>
        </div>
    );
}
export default Admadvogado;