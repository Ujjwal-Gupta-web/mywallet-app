import React, { useEffect } from 'react'
import Header from './components/Header';
import Home from './pages/Home';
import Signup from './pages/Signup';
import Login from './pages/Login';
import User from './pages/User';
import UserOptions from './components/UserOptions';
import SendMoney from './components/SendMoney';
import Wallet from './components/Wallet';
import TransactionHistory from './components/TransactionHistory';
import Cashback from './components/Cashback';
import AccountSettings from './components/AccountSettings';
import Contacts from './components/Contacts';
import { useDispatch, useSelector } from 'react-redux';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { getAccountStatement } from './store/userSlice';


const AppRouter = () => {
    const isAuth = useSelector(state => state.user.isAuth);
    const loading=useSelector(state=>state.user.loading);
    return (
        <div>
            <Header />
            <div className='p-3'>
                <Router>
                    {!isAuth
                        ?
                        <Routes>
                            <Route exact path="*" element={<Home />} />
                            <Route exact path="/signup" element={<Signup />} />
                            <Route exact path="/login" element={<Login />} />
                        </Routes>
                        :
                        <User>
                            <Routes>
                                <Route exact path="*" element={<UserOptions />} />
                                <Route exact path="/user/send-money" element={<SendMoney />} />
                                <Route exact path="/user/wallet" element={<Wallet />} />
                                <Route exact path="/user/transaction-history" element={<TransactionHistory />} />
                                <Route exact path="/user/cashback" element={<Cashback />} />
                                <Route exact path="/user/account-settings" element={<AccountSettings />} />
                                <Route exact path="/user/contacts" element={<Contacts />} />
                            </Routes>
                        </User>
                    }
                </Router>
            </div>

        </div>
    )
}

export default AppRouter