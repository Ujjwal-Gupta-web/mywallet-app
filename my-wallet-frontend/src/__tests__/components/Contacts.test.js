import { Provider } from 'react-redux';
import { BrowserRouter as Router } from 'react-router-dom';
import configureStore from 'redux-mock-store';
import Contacts from '../../components/Contacts';
import { fireEvent, getAllByText, getByText, render, screen, waitFor } from '@testing-library/react';
import { useDispatch, useSelector } from 'react-redux';
import { getContactsAction } from '../../store/actions';


const mockStore = configureStore([]);
jest.mock('react-redux', () => ({
    ...jest.requireActual('react-redux'),
    useDispatch: jest.fn(),
  }));
jest.mock("../../store/actions",()=>({
    getContactsAction:jest.fn()
}))

describe('Contacts', () => {

    let store;
    let dispatch;

    beforeEach(() => {
        store = mockStore({
            user: {
                isAuth: true,
                contacts:["abc@exmaple.com","chg@google.com","xyz@abc.com","qwertyx@yahoo.com",] 
            },
        });
        store.dispatch = jest.fn();
        dispatch = jest.fn();
        useDispatch.mockReturnValue(dispatch);
    });

    test('getContactAction works on render', () => {
        render(
          <Provider store={store}>
            <Contacts />
          </Provider>
        );
    
        expect(getContactsAction).toHaveBeenCalledTimes(1);
      });

      test('toggle add contact section', () => {
        const {getByText,queryByText}=render(
          <Provider store={store}>
            <Contacts />
          </Provider>
        );
    
        const showAddContactBtn=queryByText("Add Contact");
        expect(showAddContactBtn).toBeInTheDocument();
        fireEvent.click(showAddContactBtn);

        const hideAddContactBtn=queryByText("Hide Add Contact");
        expect(hideAddContactBtn).toBeInTheDocument();
        fireEvent.click(hideAddContactBtn);

        expect(showAddContactBtn).toBeInTheDocument();
        expect(queryByText("Hide Add Contact")).toBeNull();
    
      });

      test('search contacts', async() => {
        const {getByText,queryByText,queryAllByText,getByLabelText}=render(
          <Provider store={store}>
            <Contacts />
          </Provider>
        );
    
        const searchField=getByLabelText(/Search contact/);
        expect(searchField).toBeInTheDocument();
        fireEvent.change(searchField,{target:{value:"google"}})
        await waitFor(()=>{
            expect(queryAllByText(/google/).length).toBe(1)
        })

        fireEvent.change(searchField,{target:{value:".com"}})
        await waitFor(()=>{
            expect(queryAllByText(/.com/).length).toBe(4)
        })

        fireEvent.change(searchField,{target:{value:"x"}})
        await waitFor(()=>{
            expect(queryAllByText(/x/).length).toBe(3)
        })

    });

});
