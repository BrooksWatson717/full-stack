import React, { Component } from 'react';
import './App.css';
import Home from './Home';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import CustomerList from './CustomerList';
import CustomerEdit from './CustomerEdit';

function App() {
    return (
      <div className ="wrapper">
        <BrowserRouter>
          <Switch>
            <Route path='/' exact={true} component={Home}/>
            <Route path='/customers' exact={true} component={CustomerList}/>
            <Route path='/customers/:id' component={CustomerEdit}/>
          </Switch>
        </BrowserRouter>
        </div>
    )
}

export default App;