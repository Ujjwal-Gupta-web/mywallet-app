import React from 'react';
import { render, fireEvent } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import Header from '../../components/Header';
import { Provider } from 'react-redux';
import configureStore from 'redux-mock-store';


const mockStore = configureStore([]);

describe('Header component', () => {
    let store;

    beforeEach(() => {
        store = mockStore({
            user: {
                isAuth: false,
            },
        });
    });

    test('renders the header with Login and Signup links when user is not authenticated', () => {
        const { getByText } = render(
            <Provider store={store}>
                <Header />
            </Provider>
        );

        expect(getByText('MyWallet')).toBeInTheDocument();
        expect(getByText('Login')).toBeInTheDocument();
        expect(getByText('Signup')).toBeInTheDocument();
    });

    test('renders the header with Logout link when user is authenticated', () => {
        store = mockStore({
            user: {
                isAuth: true,
            },
        });

        const { getByText } = render(
            <Provider store={store}>
                <Header />
            </Provider>
        );

        expect(getByText('MyWallet')).toBeInTheDocument();
        expect(getByText('Logout')).toBeInTheDocument();
    });

    test('clicking Logout calls the handleLogout function', () => {
        store = mockStore({
            user: {
                isAuth: true,
            },
        });

        const { getByText } = render(
            <Provider store={store}>
                <Header />
            </Provider>
        );

        fireEvent.click(getByText('Logout'));
        const actions = store.getActions();
    
        expect(actions).toEqual([{ type: 'user/auth', payload: false }]);
        expect(window.location.pathname).toBe('/');
        // expect(window.localStorage.removeItem).toHaveBeenCalledTimes(1);
    });

    test('clicking on MyWallet should redirect to "/"', () => {
        const { getByText } = render(
            <Provider store={store}>
                <Header />
            </Provider>
        );

        fireEvent.click(getByText('MyWallet'));
        expect(window.location.pathname).toBe('/');
    });
});
