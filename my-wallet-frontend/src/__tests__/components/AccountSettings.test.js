import React from 'react';
import { render, fireEvent, waitFor } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect'; // for better assertions
import AccountSettings from '../../components/AccountSettings';
import { Provider, useDispatch } from 'react-redux';
import { BrowserRouter as Router } from 'react-router-dom';
import configureStore from 'redux-mock-store';
import { auth } from '../../store/userSlice';


const mockStore = configureStore([]);
const TestComp = ({ store }) => <Provider store={store}><Router><AccountSettings /></Router></Provider>

jest.mock('react-redux', () => ({
  ...jest.requireActual('react-redux'),
  useDispatch: jest.fn(),
}));

describe('AccountSettings', () => {

  let store;
  let dispatchMock;


  beforeEach(() => {
    store = mockStore({
      user: {
        isAuth: true,
      },
    });
    dispatchMock = jest.fn();
    useDispatch.mockReturnValue(dispatchMock);
  });

  test('renders without crashing', () => {
    render(<TestComp store={store} />);
  });

  test('clicking "Delete Account" button opens modal', () => {
    const { getByText } = render(<TestComp store={store} />);
    const deleteAccountButton = getByText('Delete Account');
    fireEvent.click(deleteAccountButton);
    const modal = getByText('Are you sure you want to delete this Account?');
    expect(modal).toBeInTheDocument();
  });

  test('clicking "Logout" button dispatches auth action and redirects', async () => {
    const { getByText } = render(<TestComp store={store} />);
    const logoutButton = getByText('Logout');
    fireEvent.click(logoutButton);
    await waitFor(() => {
      expect(dispatchMock).toHaveBeenCalledWith(auth(false));
    });
  });

});
