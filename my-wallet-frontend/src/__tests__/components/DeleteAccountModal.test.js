import React from 'react';
import { render, fireEvent } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect'; // for better assertions
import DeleteAccountModal from '../../components/DeleteAccountModal';
import { Provider } from 'react-redux';
import { BrowserRouter as Router } from 'react-router-dom';
import configureStore from 'redux-mock-store';

const mockStore = configureStore([]);
const store = mockStore();

const setOpenModal = jest.fn();
const TestComp = () => <Provider store={store}><Router><DeleteAccountModal openModal={true} setOpenModal={setOpenModal} /></Router></Provider>

describe('DeleteAccountModal', () => {
    test('renders without crashing', () => {
        render(<TestComp />);
    });

    test('clicking on No, cancel removes the modal', () => {
        const { getByText, getByTestId } = render(<TestComp />);
        const cancelButton = getByText('No, cancel');
        fireEvent.click(cancelButton);
        expect(setOpenModal).toHaveBeenCalledTimes(1);
        expect(cancelButton).toBeInTheDocument(false);
    });

    test('clicking on Yes calls delete function', () => {
        const { getByText, getByTestId } = render(<TestComp />);
        const deleteButton = getByText(/Yes/);
        fireEvent.click(deleteButton);
        expect(window.location.pathname).toBe('/');
    });

});
