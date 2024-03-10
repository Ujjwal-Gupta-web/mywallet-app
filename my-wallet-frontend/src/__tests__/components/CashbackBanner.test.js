import { Provider } from 'react-redux';
import { BrowserRouter as Router } from 'react-router-dom';
import configureStore from 'redux-mock-store';
import CashbackBanner from '../../components/CashbackBanner';
import { getAllByText, getByText, render, screen } from '@testing-library/react';

const mockStore = configureStore([]);
const TestComp = ({ store }) => <Provider store={store}><Router><CashbackBanner /></Router></Provider>;

describe('CashbackBanner', () => {

    test('renders without crashing', () => {
        const store = mockStore({
            user: {
                transactions: [],
                isCashbackAvailable: true,
            }
        });
        render(<TestComp store={store} />);
    });

    test("render cashback if is isCashbackAvailable: true", () => {
        const store = mockStore({
            user: {
                transactions: [],
                isCashbackAvailable: true,
            }
        });
        const { getByText } = render(<TestComp store={store} />)
        const cashbackElement = getByText(/CASHBACK AVAILABLE/i);
        const cashbackStatement = getByText(/5% of amount cahsback upto 1000/);
        expect(cashbackElement).toBeInTheDocument();
        expect(cashbackStatement).toBeInTheDocument();
    });

    test("1% cashback if money_sent transactions > 0", () => {
        const store = mockStore({
            user: {
                transactions: [{ transactionType: "MONEY_SENT" }, { transactionType: "MONEY_WITHDRAWN" }, { transactionType: "MONEY_SENT" }],
                isCashbackAvailable: true,
            }
        });
        const { getByText } = render(<TestComp store={store} />)
        const cashbackElement = getByText(/CASHBACK AVAILABLE/i);
        const cashbackStatement = getByText(/1% of amount cahsback upto 100/);
        expect(cashbackElement).toBeInTheDocument();
        expect(cashbackStatement).toBeInTheDocument();
    });

});
