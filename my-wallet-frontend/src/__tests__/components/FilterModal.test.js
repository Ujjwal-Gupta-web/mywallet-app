import React from 'react';
import { render, fireEvent, screen, getAllByText, queryByText, waitFor, getByLabelText } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import { Provider } from 'react-redux';
import configureStore from 'redux-mock-store';
import FilterModal from '../../components/FilterModal';
import { mockTransactions } from '../../data/mockTransactions';

const mockStore = configureStore([]);
const store = mockStore();
const setDispTransactions = jest.fn();
const setOpenModal = jest.fn();

describe('Filter Modal component', () => {

    test('render everything properly', () => {
        const { getByText, queryByText } = render(
            <Provider store={store}>
                <FilterModal
                    type
                    openModal={true}
                    setOpenModal={setOpenModal}
                    original={mockTransactions}
                    dispTransactions={mockTransactions}
                    setDispTransactions={setDispTransactions}
                />
            </Provider>
        );
        // screen.debug()  
        expect(queryByText("Filter")).toBeInTheDocument();
        expect(queryByText("Remove All filters")).toBeInTheDocument();
        expect(queryByText("Type")).toBeInTheDocument();
        expect(queryByText("Date")).toBeInTheDocument();
        expect(queryByText("Amount")).toBeInTheDocument();
    });

    test("openModal false", () => {
        const { getByText, queryByText } = render(
            <Provider store={store}>
                <FilterModal
                    openModal={false}
                    setOpenModal={setOpenModal}
                    original={mockTransactions}
                    dispTransactions={mockTransactions}
                    setDispTransactions={setDispTransactions}
                />
            </Provider>
        );

        expect(queryByText("Filter")).toBeNull();
        expect(queryByText("Remove All filters")).toBeNull();
        expect(queryByText("Type")).toBeNull();
        expect(queryByText("Date")).toBeNull();
        expect(queryByText("Amount")).toBeNull();
    })

    test("render modal for type=cashback", () => {
        const { getByText, queryByText } = render(
            <Provider store={store}>
                <FilterModal
                    type="CASHBACK"
                    openModal={true}
                    setOpenModal={setOpenModal}
                    original={mockTransactions}
                    dispTransactions={mockTransactions}
                    setDispTransactions={setDispTransactions}
                />
            </Provider>
        );
        expect(queryByText("Filter")).toBeInTheDocument();
        expect(queryByText("Remove All filters")).toBeInTheDocument();
        expect(queryByText("Type")).toBeNull();
        expect(queryByText("Date")).toBeInTheDocument();
        expect(queryByText("Amount")).toBeInTheDocument();

    })

    test("check filter by transaction type", async () => {
        const { getByText, getAllByText, queryByText } = render(
            <Provider store={store}>
                <FilterModal
                    openModal={true}
                    setOpenModal={setOpenModal}
                    original={mockTransactions}
                    dispTransactions={mockTransactions}
                    setDispTransactions={setDispTransactions}
                />
            </Provider>
        );
        const transactionDropdown = getByText('Select Transaction Type');
        expect(transactionDropdown).toBeInTheDocument();
        fireEvent.click(transactionDropdown);
        await waitFor(() => {
            expect(getByText("MONEY_RECEIVED")).toBeInTheDocument();
            expect(getByText("MONEY_SENT")).toBeInTheDocument();
            expect(getByText("CASHBACK")).toBeInTheDocument();
            expect(getByText("MONEY_ADDED")).toBeInTheDocument();
            expect(getByText("MONEY_WITHDRAWN")).toBeInTheDocument();
        })
        fireEvent.click(getByText("MONEY_RECEIVED"));
        fireEvent.click(getAllByText('Apply')[0])

        await waitFor(() => {
            expect(setDispTransactions).toHaveBeenCalledTimes(1)
            expect(setDispTransactions).toHaveBeenCalledWith([])
        })
    })

    test("check filter by amount", async () => {
        const { getByText, getAllByText, queryByText,getByLabelText } = render(
            <Provider store={store}>
                <FilterModal
                    openModal={true}
                    setOpenModal={setOpenModal}
                    original={mockTransactions}
                    dispTransactions={mockTransactions}
                    setDispTransactions={setDispTransactions}
                />
            </Provider>
        );
        const amountTab = getByText('Amount');
        fireEvent.click(amountTab);
        await waitFor(()=>{
            fireEvent.change(getByLabelText("Min Amount"), { target: { value: 5 } })
            fireEvent.change(getByLabelText("Max Amount"), { target: { value: 10 } })
        })        
        fireEvent.click(getAllByText('Apply')[2])

        await waitFor(() => {
            expect(setDispTransactions).toHaveBeenCalledTimes(1)
            // expect(setDispTransactions).toHaveBeenCalledWith([{
            //     "transactionId": "4cce7ade-789d-4191-a0ad-78013ef7de60",
            //     "transactionType": "MONEY_ADDED",
            //     "transactionWith": "SELF",
            //     "timestamp": "2024-02-27T21:19:56.273+00:00",
            //     "amount": 10
            // },
            // {
            //     "transactionId": "4a13c0ce-bab2-4c04-a202-72cc6852e2bd",
            //     "transactionType": "MONEY_ADDED",
            //     "transactionWith": "SELF",
            //     "timestamp": "2024-02-27T21:18:15.112+00:00",
            //     "amount": 0
            // }])
        })
    })

    test("check filter by date", async () => {
        const { getByText, getAllByText, queryByText,getByLabelText } = render(
            <Provider store={store}>
                <FilterModal
                    openModal={true}
                    setOpenModal={setOpenModal}
                    original={mockTransactions}
                    dispTransactions={mockTransactions}
                    setDispTransactions={setDispTransactions}
                />
            </Provider>
        );
        const dateTab = getByText('Amount');
        fireEvent.click(dateTab);
        await waitFor(()=>{
            fireEvent.change(getByLabelText("From"), { target: { value: "2024-03-02" } })
            fireEvent.change(getByLabelText("To"), { target: { value: "2024-03-18" } })
        })        
        fireEvent.click(getAllByText('Apply')[1])

        await waitFor(() => {
            expect(setDispTransactions).toHaveBeenCalledTimes(1)
        })
    })

});
