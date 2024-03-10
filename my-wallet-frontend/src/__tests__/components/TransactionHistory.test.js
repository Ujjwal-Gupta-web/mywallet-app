import React from 'react';
import { render, fireEvent, screen } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import { Provider } from 'react-redux';
import configureStore from 'redux-mock-store';
import TransactionHistory from '../../components/TransactionHistory';
import { mockTransactions } from '../../data/mockTransactions';

const mockStore = configureStore([]);
const store = mockStore({
    user: {
        transactions: mockTransactions
    }
});

describe('Transaction History component', () => {

    test('render everything properly', () => {
        const { getByText } = render(
            <Provider store={store}>
                <TransactionHistory />
            </Provider>
        );
        expect(getByText(/FILTER/)).toBeInTheDocument();
    });

    test("if filter open works", () => {
        const { getByText } = render(
            <Provider store={store}>
                <TransactionHistory />
            </Provider>
        );
        const filterBtn = getByText(/FILTER/);
        fireEvent.click(filterBtn);
        const btnInFilterModal = getByText(/Remove All Filter/i)
        expect(btnInFilterModal).toBeInTheDocument();
    })
});
