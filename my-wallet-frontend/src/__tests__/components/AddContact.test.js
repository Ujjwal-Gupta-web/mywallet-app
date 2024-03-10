import React from 'react';
import { render, fireEvent, waitFor } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import { Provider, useDispatch } from 'react-redux';
import { BrowserRouter as Router } from 'react-router-dom';
import configureStore from 'redux-mock-store';
import AddContact from '../../components/AddContact';
import { addContactAction } from '../../store/actions';

jest.mock('react-redux', () => ({
    ...jest.requireActual('react-redux'),
    useDispatch: jest.fn(),
}));

const mockStore = configureStore([]);
const TestComp = ({ store }) => <Provider store={store}><Router><AddContact /></Router></Provider>

describe('Add Contact', () => {
    let store;
    let dispatchMock;

    beforeEach(() => {
        store = mockStore({
            user: {
                isAuth: true,
                contacts: [],
            },
        });
        dispatchMock = jest.fn();
        useDispatch.mockReturnValue(dispatchMock);
    });

    test('renders without crashing', () => {
        render(<TestComp store={store} />);
    });

    test('Add contact flow', async () => {
        const { getByText, getByLabelText } = render(<TestComp store={store} />);

        expect(getByText("Add contact")).toBeInTheDocument();
        fireEvent.change(getByLabelText('Add contact'), { target: { value: 'testUser@example.com' } });
        fireEvent.click(getByText('Add'));
        await waitFor(() => {
            expect(dispatchMock).toHaveBeenCalledTimes(1);
        })
    });
})
