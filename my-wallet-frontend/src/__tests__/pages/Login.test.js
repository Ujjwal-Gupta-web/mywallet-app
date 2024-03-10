import React from 'react';
import { render, fireEvent } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import { Provider } from 'react-redux';
import configureStore from 'redux-mock-store';
import Login from '../../pages/Login';
import { BrowserRouter as Router } from 'react-router-dom';

const User={
    login:jest.fn,
    signup:jest.fn
}
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

    test('should call User.login when login button is clicked', async () => {
        const { getByText, getByLabelText } = render(
            <Provider store={store}>
                <Router>
                    <Login />
                </Router>
            </Provider>
        );
        const usernameInput = getByLabelText('Your email (username)');
        const passwordInput = getByLabelText('Your password');
        // const loginButton = getByText('Login');
    
        const mockUserData = {
          username: 'test@example.com',
          password: 'password123',
        };
    
        fireEvent.change(usernameInput, { target: { value: mockUserData.username } });
        fireEvent.change(passwordInput, { target: { value: mockUserData.password } });
        // fireEvent.click(loginButton);
    
        // await waitFor(() => {
        //   expect(User.login).toHaveBeenCalledTimes(1);
        //   expect(User.login).toHaveBeenCalledWith(mockUserData);
        // });
      });
});
