import React from 'react';
import { render, fireEvent, getByAltText, queryByText } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import { Provider } from 'react-redux';
import configureStore from 'redux-mock-store';
import UserHome from '../../pages/User';
import { BrowserRouter as Router } from 'react-router-dom';
import { getAccountStatementAction } from '../../store/actions';
import { useDispatch, useSelector } from 'react-redux';

const mockStore = configureStore([]);
jest.mock('react-redux', () => ({
    ...jest.requireActual('react-redux'),
    useDispatch: jest.fn(),
  }));

describe('User Page', () => {
    let store;
    let dispatch;

    beforeEach(() => {
        store = mockStore({
            user: {
                isAuth: true,
                balance:100.0
            },
        });
        store.dispatch = jest.fn();
        dispatch = jest.fn();
        useDispatch.mockReturnValue(dispatch);
    });
    test('renders without crashing', () => {
        render(
          <Provider store={store}>
            <Router>
              <UserHome />
            </Router>
          </Provider>
        );
      });
    
      test('acount details fetched', async() => {
        
        const {getByText,queryByText}=render(
          <Provider store={store}>
            <Router>
              <UserHome />
            </Router>
          </Provider>
        );

        expect(dispatch).toHaveBeenCalledTimes(1);
        const showBalanceBtn=getByText("Show Balance");
        fireEvent.click(showBalanceBtn);
        
        expect(getByText("Available Balance : ₹100")).toBeInTheDocument();    
        fireEvent.click(showBalanceBtn);
        
        expect(queryByText("Available Balance : ₹100")).toBeNull();
      });

});