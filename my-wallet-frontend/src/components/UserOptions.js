import { Card } from 'flowbite-react'
import React from 'react'
import { HiCash, HiOutlineCash } from 'react-icons/hi'
import { IoWalletOutline } from "react-icons/io5";
import { MdHistory, MdWallet } from "react-icons/md";
import { IoMdGift } from "react-icons/io";
import { IoSettingsOutline } from "react-icons/io5";
import { RiContactsLine } from "react-icons/ri";
import { Link } from 'react-router-dom';
const UserOptions = () => {
    return (
        <div className='flex flex-col items-center justify-center'>
            <Link to="/user/send-money" className='w-full max-w-sm m-3'>
                <Card>
                    <div className="flex flex-col items-center">
                        <HiOutlineCash />
                        <h5 className="text-xl font-medium text-gray-900 dark:text-white"> Send Money</h5>
                    </div>
                </Card>
            </Link>
            <Link to="/user/contacts" className='w-full max-w-sm m-3'>
                <Card>
                    <div className="flex flex-col items-center">
                        <RiContactsLine />
                        <h5 className="text-xl font-medium text-gray-900 dark:text-white"> Contacts</h5>
                    </div>
                </Card>
            </Link>
            <Link to="/user/transaction-history" className='w-full max-w-sm m-3'>
                <Card>
                    <div className="flex flex-col items-center">
                        <MdHistory />
                        <h5 className="text-xl font-medium text-gray-900 dark:text-white"> Transaction History</h5>
                    </div>
                </Card>
            </Link>
            <Link to="/user/wallet" className='w-full max-w-sm m-3'>
                <Card>
                    <div className="flex flex-col items-center">
                        <MdWallet />
                        <h5 className="text-xl font-medium text-gray-900 dark:text-white"> Wallet</h5>
                    </div>
                </Card>
            </Link>
            <Link to="/user/cashback" className='w-full max-w-sm m-3'>
                <Card>
                    <div className="flex flex-col items-center">
                        <IoMdGift />
                        <h5 className="text-xl font-medium text-gray-900 dark:text-white"> Cashbacks</h5>
                    </div>
                </Card>
            </Link>
            <Link to="/user/account-settings" className='w-full max-w-sm m-3'>
                <Card>
                    <div className="flex flex-col items-center">
                        <IoSettingsOutline />
                        <h5 className="text-xl font-medium text-gray-900 dark:text-white"> AccountSettings</h5>
                    </div>
                </Card>
            </Link>

        </div>
    )
}

export default UserOptions