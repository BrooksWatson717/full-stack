import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';

class CustomerList extends Component {

    constructor(props) {
        super(props);
        this.state = {customers: []};
        this.remove = this.remove.bind(this);
    }

    async componentDidMount() {
        const response  = await fetch('/customers');
        const body = await response.json();
        this.setState({customers: body._embedded.customerModelList});
    }

async remove(id) {
    await fetch(`/customers/${id}`, {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    }).then(() => {
        let updatedCustomers = [...this.state.customers].filter(i => i.id !== id);
        this.setState({customers: updatedCustomers});
    });
}

render() {
    const {customers, isLoading} = this.state;

    if (isLoading) {
        return <p>Loading...</p>;
    }

    const customerGrid = 
    
    customers.map(customer => {
        return <tr key={customer.id}>
            <td style={{whiteSpace: 'nowrap'}}>{customer.name}</td>
            <td>{customer.email}</td>
            <td>
                <ButtonGroup>
                    <Button size="sm" color="primary" tag={Link} to={"/customers/" + customer.id}>Edit</Button>
                    <Button size="sm" color="danger" onClick={() => this.remove(customer.id)}>Delete</Button>
                </ButtonGroup>
            </td>
        </tr>
    });

    return (
        <div>
            <AppNavbar/>
            <Container fluid>
                <div className="float-right">
                    <Button color="success" tag={Link} to="/customers/new">Add Customer</Button>
                </div>
                <h3>Customers</h3>
                <Table className="mt-4">
                    <thead>
                    <tr>
                        <th width="30%">Name</th>
                        <th width="30%">Email</th>
                        <th width="40%">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    {customerGrid}
                    </tbody>
                </Table>
            </Container>
        </div>
    );
}
}

export default CustomerList;