import { Button } from 'flowbite-react'
import React from 'react'
import { Link } from 'react-router-dom'

const Home = () => {
    return (
        <div>

            <div className='flex items-center justify-between p-3 flex-wrap-reverse'>
                <div className='m-3 flex-col align-center justify-start'>
                    <h3 className='text-2xl'>
                        Send money from your own e-wallet with ease..
                    </h3>
                    <div className='flex items-center justify-start my-5'>
                        <Link className='flex items-center justify-between' to="/login">
                            <Button outline className="border border-blue-400 bg-blue-200">
                                Login
                            </Button>
                        </Link>
                        <Link className='flex items-center justify-between' to="/signup">
                            <Button outline className="border border-blue-400 bg-blue-200 mx-3">
                                Signup
                            </Button>
                        </Link>
                    </div>

                </div>
                <div className='m-3'>
                    <img src="https://img.freepik.com/free-vector/mobile-bank-users-transferring-money-currency-conversion-tiny-people-online-payment-cartoon-illustration_74855-14454.jpg?size=626&ext=jpg" />
                </div>
            </div>

            <div className='flex items-center justify-between p-3 flex-wrap'>
                <div className='m-3'>
                    <img src="https://img.freepik.com/free-vector/cashback-concept-with-smartphone_23-2148496115.jpg?size=626&ext=jpg" />
                </div>
                <div className='m-3'>
                    <h3 className='text-2xl'>
                        Get exciting rewards
                    </h3>
                    <h4 className='text-xl my-3'>
                        Get 5% cashback up 1000 on first recharge!!!
                        <br />
                        For 1000, get 100 cashback 😊
                    </h4>

                </div>
            </div>

            <div className='flex items-center justify-between p-3 flex-wrap-reverse'>
                <div className='m-3'>
                    <h3 className='text-2xl'>
                        User friendly and secure
                    </h3>

                </div>
                <div className='m-3'>
                    <img src="https://img.freepik.com/premium-vector/online-payment-cartoon-little-woman-sitting-wallet-with-banknotes-transferring-cash-into-credit-card-smartphone_102902-6991.jpg?size=626&ext=jpg" />
                </div>
            </div>

        </div>

    )
}

export default Home