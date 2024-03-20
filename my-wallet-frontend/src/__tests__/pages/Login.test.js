import React from 'react';
import { render, fireEvent, waitFor } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import { Provider } from 'react-redux';
import configureStore from 'redux-mock-store';
import Login from '../../pages/Login';
import { BrowserRouter as Router } from 'react-router-dom';
import User from '../../controllers/user';
import { act } from 'react-dom/test-utils';

jest.mock('../../controllers/user', () => ({
    login: jest.fn(),
}));

const mockStore = configureStore([]);

describe('Login component', () => {
    let store;

    beforeEach(() => {
        store = mockStore({
            user: {
                isAuth: false,
            },
        });
    });

    test('renders properly', () => {
        const { getByText, getByLabelText } = render(
            <Provider store={store}>
                <Router>
                    <Login />
                </Router>
            </Provider>
        );
        
        expect(getByLabelText('Your email (username)')).toBeInTheDocument();
        expect(getByLabelText('Your password')).toBeInTheDocument();
    });

    test('Login feilds work', () => {
        const { getByText, getByLabelText } = render(
            <Provider store={store}>
                <Router>
                    <Login />
                </Router>
            </Provider>
        );
        const username=getByLabelText('Your email (username)');
        const password=getByLabelText('Your password');
        fireEvent.change(username, { target: { value: 'test@example.com' } });
        fireEvent.change(password, { target: { value: '123456' } });

        expect(username.value).toBe("test@example.com")
        expect(password.value).toBe("123456")
    });

    test('Login flow success', async () => {
        User.login.mockResolvedValueOnce({
            tag: true,
            message: 'Login successful',
            data: { token: 'mocked_token' },
        });
        const { getByText,getAllByText, getByLabelText } = render(
            <Provider store={store}>
                <Router>
                    <Login />
                </Router>
            </Provider>
        );
        const usernameInput = getByLabelText('Your email (username)');
        const passwordInput = getByLabelText('Your password');
        const loginButton = getAllByText('Login')[1];
    
        const mockUserData = {
          username: 'test@example.com',
          password: 'password123',
        };
    
        fireEvent.change(usernameInput, { target: { value: mockUserData.username } });
        fireEvent.change(passwordInput, { target: { value: mockUserData.password } });
        fireEvent.click(loginButton);
    
        await waitFor(() => {
          expect(User.login).toHaveBeenCalledTimes(1);
          expect(User.login).toHaveBeenCalledWith(mockUserData);
        });
      });

      test('Login flow fail', async () => {
        User.login.mockResolvedValueOnce({
            tag: false,
            message: 'USER NOT FOUND',
        });
        const { getByText,getAllByText, getByLabelText } = render(
            <Provider store={store}>
                <Router>
                    <Login />
                </Router>
            </Provider>
        );
        const usernameInput = getByLabelText('Your email (username)');
        const passwordInput = getByLabelText('Your password');
        const loginButton = getAllByText('Login')[1];
    
        const mockUserData = {
          username: 'test@example.com',
          password: 'password123',
        };
    
        await act(async () => {
            fireEvent.change(usernameInput, { target: { value: mockUserData.username } });
            fireEvent.change(passwordInput, { target: { value: mockUserData.password } });
            fireEvent.click(loginButton);
            await waitFor(() => {
                expect(User.login).toHaveBeenCalledTimes(1);
                expect(User.login).toHaveBeenCalledWith(mockUserData);
            });
        });
      });
});
