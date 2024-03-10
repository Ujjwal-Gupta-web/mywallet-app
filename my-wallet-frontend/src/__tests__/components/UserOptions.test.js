import React from 'react';
import { render, fireEvent } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect'; // for better assertions
import UserOptions from '../../components/UserOptions';
import { Provider } from 'react-redux';
import { BrowserRouter as Router } from 'react-router-dom';
import appStore from "../../store/appStore"
import configureStore from 'redux-mock-store';

const mockStore = configureStore([]);
const store = mockStore({
    user: {
      isAuth: true,
    },
  });
const TestComp = () => <Provider store={store}><Router><UserOptions /></Router></Provider>

describe('UserOptions', () => {
    test('renders without crashing', () => {
        render(<TestComp />);
    });

    test('clicking Send Money renders to /user/send-money', () => {
        const { getByText } = render(<TestComp />);
        const sendMoneyLink = getByText('Send Money');
        fireEvent.click(sendMoneyLink);
        expect(window.location.pathname).toBe('/user/send-money');

    });
    test('clicking Contacts renders to /user/contacts', () => {
        const { getByText } = render(<TestComp />);
        const contactsLink = getByText('Contacts');
        fireEvent.click(contactsLink);
        expect(window.location.pathname).toBe('/user/contacts');
    });

    test('clicking Transaction History renders to /user/transaction-history', () => {
        const { getByText } = render(<TestComp />);
        const historyLink = getByText('Transaction History');
        fireEvent.click(historyLink);
        expect(window.location.pathname).toBe('/user/transaction-history');
    });

    test('clicking Wallet renders to /user/wallet', () => {
        const { getByText } = render(<TestComp />);
        const walletLink = getByText('Wallet');
        fireEvent.click(walletLink);
        expect(window.location.pathname).toBe('/user/wallet');
    });

    test('clicking Cashbacks renders to /user/cashback', () => {
        const { getByText } = render(<TestComp />);
        const cashbacksLink = getByText('Cashbacks');
        fireEvent.click(cashbacksLink);
        expect(window.location.pathname).toBe('/user/cashback');
    });

    test('clicking AccountSettings renders to /user/account-settings', () => {
        const { getByText } = render(<TestComp />);
        const accountSettingsLink = getByText('AccountSettings');
        fireEvent.click(accountSettingsLink);
        expect(window.location.pathname).toBe('/user/account-settings');
    });
});
