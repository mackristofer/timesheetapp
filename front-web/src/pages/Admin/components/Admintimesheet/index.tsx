import React from 'react';
import { Route, Switch } from 'react-router-dom';
import Form from './Form';
import List from './List';

import './styles.scss';

const Admtimesheet = () => {
    return(
        <div>
            <Switch>
                <Route path="/admin/timesheet" exact>
                    <List />
                </Route>
                <Route path="/admin/timesheet/:timesheetId">
                <Form />
                </Route>
            </Switch>
        </div>
    );
}
export default Admtimesheet;