import React from 'react';
import { render, fireEvent, waitFor, getAllByAltText, getAllByTestId, getAllByText } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect'; // for better assertions
import { Provider, useDispatch } from 'react-redux';
import { BrowserRouter as Router } from 'react-router-dom';
import configureStore from 'redux-mock-store';
import Wallet from '../../components/Wallet';
import { addTransaction } from '../../store/userSlice';

const mockStore = configureStore([]);
const TestComp = ({ store }) => <Provider store={store}><Router><Wallet /></Router></Provider>

jest.mock('react-redux', () => ({
    ...jest.requireActual('react-redux'),
    useDispatch: jest.fn(),
}));

describe('Wallet', () => {

    let store;
    let dispatchMock;


    beforeEach(() => {
        store = mockStore({
            user: {
                isAuth: true,
                transactions: [],
                balance: 100.0,
                isCashbackAvailable: false,
            },
        });
        dispatchMock = jest.fn();
        useDispatch.mockReturnValue(dispatchMock);
    });

    test('renders without crashing', () => {
        render(<TestComp store={store} />);
    });

    test('Add amount flow', async() => {
        const {getByText,getByLabelText}=render(<TestComp store={store} />);
        const addBtn=getByText("Add");
        fireEvent.change(getByLabelText('Enter Amount to be Added (₹)'), { target: { value: '50.0' } });
        fireEvent.click(addBtn);
        const transaction = {
            "transactionType": "MONEY_ADDED",
            "transactionWith": "SELF",
            "amount": 50.0
        }

        await waitFor(()=>{
            expect(dispatchMock).toHaveBeenCalledTimes(1)
        })
    });

    test('Withdraw amount flow', async() => {
        const {getByText,getAllByText,getByLabelText}=render(<TestComp store={store} />);
        const withdrawTags=getAllByText(/Withdraw/);
        const withdrawBtn=withdrawTags[0];
        fireEvent.click(withdrawBtn);
        fireEvent.change(getByLabelText('Enter Amount to be withdrawn (₹)'), { target: { value: '50.0' } });
        fireEvent.click(withdrawTags[2]);
        const transaction = {
            "transactionType": "MONEY_WITHDRAWN",
            "transactionWith": "SELF",
            "amount": 50.0
        }

        await waitFor(()=>{
            expect(dispatchMock).toHaveBeenCalledTimes(1)
        })
    });    

    test("input fields",()=>{
        const {getByText,getAllByText,getByLabelText}=render(<TestComp store={store} />);
        fireEvent.change(getByLabelText('Enter Amount to be Added (₹)'), { target: { value: '50.0' } });
        expect(getByLabelText('Enter Amount to be Added (₹)').value).toBe("50.0")   

        fireEvent.change(getByLabelText('Enter Amount to be withdrawn (₹)'), { target: { value: '50.0' } });
        expect(getByLabelText('Enter Amount to be withdrawn (₹)').value).toBe("50.0")   
    })

});
