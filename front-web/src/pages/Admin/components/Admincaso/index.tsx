import React from 'react';
import { Route, Switch } from 'react-router-dom';
import Form from './Form';
import List from './List';

import './styles.scss';

const Admcaso= () => {
    return(
        <div>
            <Switch>
                <Route path="/admin/caso" exact>
                    <List />
                </Route>
                <Route path="/admin/caso/:casoId">
                <Form />
                </Route>
            </Switch>
        </div>
    );
}
export default Admcaso;