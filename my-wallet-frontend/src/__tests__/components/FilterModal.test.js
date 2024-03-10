import React from 'react';
import { render, fireEvent, screen, getAllByText } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import { Provider } from 'react-redux';
import configureStore from 'redux-mock-store';
import FilterModal from '../../components/FilterModal';
import { mockTransactions } from '../../data/mockTransactions';

const mockStore = configureStore([]);
const store = mockStore();
describe('Filter Modal component', () => {

    test('render everything properly', () => {
        const { getByText } = render(
            <Provider store={store}>
                <FilterModal
                    type
                    openModal={true}
                    setOpenModal={jest.fn}
                    original={mockTransactions}
                    dispTransactions={mockTransactions}
                    setDispTransactions={jest.fn}
                />
            </Provider>
        );
        // expect(getByText(/Filter/i)).toBeInTheDocument();
        // expect(getByText(/Remove All Filters/i)).toBeInTheDocument();
        // expect(getByText(/Apply/i)).toBeInTheDocument();
        // expect(getByText(/Close/i)).toBeInTheDocument();
    });

    test("if filter open works", () => {
        const { getByText } = render(
            <Provider store={store}>
            <FilterModal
                type
                openModal={false}
                setOpenModal={jest.fn}
                original={mockTransactions}
                dispTransactions={mockTransactions}
                setDispTransactions={jest.fn}
            />
        </Provider>
        );

        // const applyBtn=getAllByText("Apply");
        // expect(applyBtn.size()).toBe(3)
    })
});
