import React from 'react';
import { render, fireEvent, waitFor } from '@testing-library/react';
import User from "../../controllers/user";
import Email from '../../controllers/email';
import Signup from '../../pages/Signup';
import { Provider } from 'react-redux';
import { BrowserRouter as Router } from 'react-router-dom';
import configureStore from 'redux-mock-store';

const mockStore = configureStore([]);

jest.mock('../../controllers/user');
jest.mock('../../controllers/email');

describe('Signup component', () => {
    let store;

    beforeEach(() => {
        store = mockStore({
            user: {
                isAuth: false,
            },
        });
    });

    it('should render Signup component properly', () => {
        const { getByText,getAllByText, getByLabelText } = render(<Provider store={store}>
            <Router>
                <Signup />
            </Router>
        </Provider>);

        expect(getAllByText("Signup").length).toBe(2);
        expect(getByLabelText('Your email (username)')).toBeInTheDocument();
        expect(getByLabelText('OTP')).toBeInTheDocument();
        expect(getByLabelText('Your password')).toBeInTheDocument();
    });


});
