import { Provider } from 'react-redux';
import { BrowserRouter as Router } from 'react-router-dom';
import configureStore from 'redux-mock-store';
import SendMoney from '../../components/SendMoney';
import { fireEvent, getAllByText, getByText, render, screen, waitFor } from '@testing-library/react';
import { useDispatch, useSelector } from 'react-redux';
import { addTransactionAction } from '../../store/actions';

const mockStore = configureStore([]);
jest.mock('react-redux', () => ({
    ...jest.requireActual('react-redux'),
    useDispatch: jest.fn(),
  }));
jest.mock("../../store/actions",()=>({
    addTransactionAction:jest.fn()
}))

describe('SendMoney', () => {

    let store;
    let dispatch;

    beforeEach(() => {
        store = mockStore({
            user: {
                isAuth: true,
                balance:100.50
            },
        });
        store.dispatch = jest.fn();
        dispatch = jest.fn();
        useDispatch.mockReturnValue(dispatch);
    });

    test('render properly', () => {
        const {getByText,getAllByText,getByLabelText} = render(
          <Provider store={store}>
            <SendMoney />
          </Provider>
        );
        const sendMoney=getAllByText("Send Money");
        const receiverField=getByLabelText("Receiver's username"); 
        const amountField=getByLabelText("Enter Amount (₹)"); 
        expect(sendMoney.length).toBe(2);
        expect(receiverField).toBeInTheDocument();
        expect(amountField).toBeInTheDocument();
      });

      test('send money success', async() => {
        const {getByText,getAllByText,getByLabelText} = render(
          <Provider store={store}>
            <SendMoney />
          </Provider>
        );
        const receiverField=getByLabelText("Receiver's username"); 
        const amountField=getByLabelText("Enter Amount (₹)"); 
        const sendMoneyBtn=getAllByText("Send Money")[1];
        fireEvent.change(receiverField,{target:{value:"testuser@example.com"}})
        fireEvent.change(amountField,{target:{value:10}})  
        fireEvent.click(sendMoneyBtn);
        await waitFor(()=>{
            expect(addTransactionAction).toHaveBeenCalled();      
        })
      });


});
