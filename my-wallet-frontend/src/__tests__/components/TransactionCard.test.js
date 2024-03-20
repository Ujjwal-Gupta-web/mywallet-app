import React from 'react';
import { render, fireEvent } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import { Provider } from 'react-redux';
import configureStore from 'redux-mock-store';
import TransactionCard from '../../components/TransactionCard';
import { formatDate } from '../../utility/formatDate';
import { transactionTypeIcon } from '../../utility/transactionTypeIcon';
import { MdCallMade } from 'react-icons/md';

const mockStore = configureStore([]);
const store = mockStore();

describe('Transaction Card', () => {

    test('renders all details properly', () => {
        const transaction = {
            transactionId: "1234567890",
            transactionType: "MONEY_SENT",
            transactionWith: "abc@gmail.com",
            amount: 500.50,
            timestamp: "2024-02-28T17:16:15.649+00:00"
        }
        const { getByText } = render(
            <Provider store={store}>
                <TransactionCard transaction={transaction} />
            </Provider>
        );
        const Icon = transactionTypeIcon(transaction.transactionType);
        expect(getByText(transaction.transactionId)).toBeInTheDocument();
        expect(getByText(transaction.transactionType)).toBeInTheDocument();
        expect(getByText(transaction.transactionWith)).toBeInTheDocument();
        expect(getByText("₹"+transaction.amount)).toBeInTheDocument();
        expect(getByText(formatDate(transaction.timestamp))).toBeInTheDocument();
        expect(Icon).toBe(MdCallMade);

    });

});
