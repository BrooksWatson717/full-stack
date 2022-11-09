import React, { Component } from 'react';
import './App.css';
import Home from './Home';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import ClientList from './ClientList';
import ClientEdit from "./ClientEdit";

function App() {
    return (
      <div className ="wrapper">
        <BrowserRouter>
          <Switch>
            <Route path='/' exact={true} component={Home}/>
            <Route path='/clients' exact={true} component={ClientList}/>
            <Route path='/clients/:id' component={ClientEdit}/>
          </Switch>
        </BrowserRouter>
        </div>
    )
}

export default App;