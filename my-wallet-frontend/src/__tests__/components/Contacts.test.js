import { Provider } from 'react-redux';
import { BrowserRouter as Router } from 'react-router-dom';
import configureStore from 'redux-mock-store';
import Contacts from '../../components/Contacts';
import { getAllByText, getByText, render, screen } from '@testing-library/react';

const mockStore = configureStore([]);
const TestComp = ({ store }) => <Provider store={store}><Router><Contacts /></Router></Provider>;

describe('Contacts', () => {
    test('renders Contacts component with initial state', async () => {
        // const store = mockStore({
        //     user: {
        //         contacts: [],
        //     },
        // });

        // const { getByText, getByLabelText } = render(
        //     <Provider store={store}>
        //         <Contacts />
        //     </Provider>
        // );

        // expect(getByText('Contacts')).toBeInTheDocument();

        // // Search input should be present
        // expect(getByLabelText('Search contact')).toBeInTheDocument();

        // // Add Contact button should be present
        // expect(getByText('Add Contact')).toBeInTheDocument();

        // // Since contacts array is empty initially, the list should be empty
        // await waitFor(() => {
        //     expect(document.querySelectorAll('ul li').length).toBe(0);
        // });
    });

});
