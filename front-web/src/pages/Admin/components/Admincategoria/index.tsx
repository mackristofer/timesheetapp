import React from 'react';
import { Route, Switch } from 'react-router-dom';
import Form from './Form';
import List from './List';

import './styles.scss';

const Admcategoria= () => {
    return(
        <div>
            <Switch>
                <Route path="/admin/categoria" exact>
                    <List />
                </Route>
                <Route path="/admin/categoria/:categoriaId">
                <Form />
                </Route>
            </Switch>
        </div>
    );
}
export default Admcategoria;