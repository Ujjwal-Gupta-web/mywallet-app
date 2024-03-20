import React from 'react';
import { render, fireEvent, screen } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import { Provider } from 'react-redux';
import configureStore from 'redux-mock-store';
import { mockTransactions } from '../../data/mockTransactions';
import Cashback from '../../components/Cashback';

const mockStore = configureStore([]);
const store = mockStore({
    user: {
        transactions: mockTransactions.filter((transaction)=>transaction.transactionType==="CASHBACK")
    }
});

describe('Cashback component', () => {

    test('render everything properly', () => {
        const { getByText } = render(
            <Provider store={store}>
                <Cashback />
            </Provider>
        );
        expect(getByText(/FILTER/)).toBeInTheDocument();
    })

});
