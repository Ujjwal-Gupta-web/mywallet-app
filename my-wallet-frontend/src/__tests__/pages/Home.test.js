import React from 'react';
import { render, fireEvent } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import { Provider } from 'react-redux';
import configureStore from 'redux-mock-store';
import Home from '../../pages/Home';
import { BrowserRouter as Router } from 'react-router-dom';


const mockStore = configureStore([]);

describe('Home component', () => {
    let store;

    beforeEach(() => {
        store = mockStore({
            user: {
                isAuth: false,
            },
        });
    });

    test('renders properly', () => {
        const { getByText } = render(
            <Provider store={store}>
                <Router>
                    <Home />
                </Router>
            </Provider>
        );

        expect(getByText('Send money from your own e-wallet with ease..')).toBeInTheDocument();
        expect(getByText('Login')).toBeInTheDocument();
        expect(getByText('Signup')).toBeInTheDocument();
        expect(getByText('Get exciting rewards')).toBeInTheDocument();
        expect(getByText('User friendly and secure')).toBeInTheDocument();
    });
    test('redirects to /login when Login is clicked', () => {
        const { getByText } = render(
            <Provider store={store}>
                <Router>
                    <Home />
                </Router>
            </Provider>
        );
        const loginBtn=getByText('Login');
        fireEvent.click(loginBtn)
        expect(window.location.pathname).toBe("/login");
    });
    test('redirects to /signup when Signup is clicked', () => {
        const { getByText } = render(
            <Provider store={store}>
                <Router>
                    <Home />
                </Router>
            </Provider>
        );
        const signupBtn=getByText('Signup');
        fireEvent.click(signupBtn)
        expect(window.location.pathname).toBe("/signup");
    });
    
});
