import React from 'react';
import { render, fireEvent, waitFor, getByTestId } from '@testing-library/react';
import Signup from '../../pages/Signup';
import { Provider } from 'react-redux';
import { BrowserRouter as Router } from 'react-router-dom';
import configureStore from 'redux-mock-store';
import User from '../../controllers/user';
import Email from '../../controllers/email';
import { act } from 'react-dom/test-utils';


const mockStore = configureStore([]);
jest.mock('../../controllers/user', () => ({
    signup: jest.fn(),
}));

jest.mock('../../controllers/email', () => ({
    sendOtp: jest.fn(),
}));

describe('Signup component', () => {
    let store;

    beforeEach(() => {
        store = mockStore({
            user: {
                isAuth: false,
            },
        });
    });

    it('snapshot testing for signup', () => {
        const container = render(<Provider store={store}>
            <Router>
                <Signup />
            </Router>
        </Provider>);

        expect(container).toMatchSnapshot();
    });

    it('should render Signup component properly', () => {
        const { getByText, getAllByText, getByLabelText } = render(<Provider store={store}>
            <Router>
                <Signup />
            </Router>
        </Provider>);

        expect(getAllByText("Signup").length).toBe(2);
        expect(getByLabelText('Your email (username)')).toBeInTheDocument();
        expect(getByLabelText('OTP')).toBeInTheDocument();
        expect(getByLabelText('Your password')).toBeInTheDocument();
    });

    it('Singup flow', async() => {
        const { getByText, getAllByText,getByRole, getByLabelText } = render(<Provider store={store}>
            <Router>
                <Signup />
            </Router>
        </Provider>);

        const emailInput = getByLabelText('Your email (username)');
        const otpInput = getByLabelText('OTP');
        const passwordInput = getByLabelText('Your password');

        fireEvent.change(emailInput, { target: { value: 'test@example.com' } });
        fireEvent.change(otpInput, { target: { value: '123456' } });
        fireEvent.change(passwordInput, { target: { value: 'password123' } });

        expect(emailInput.value).toBe('test@example.com');
        expect(otpInput.value).toBe('123456');
        expect(passwordInput.value).toBe('password123');

        const sendOtpBtn=getByText("Get OTP");
        const verifyOtpBtn=getByText("Verify OTP");
        const signupBtn=getByRole('button', { name: /Signup/i });

        // await act(async () => {
        //     fireEvent.click(sendOtpBtn);
        //     await waitFor(() => {
        //         expect(User.signup()).toHaveBeenCalledTimes(1);
        //     });
        // });

    });

});
