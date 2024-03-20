import { Button, Card, Label, Tabs, TextInput } from 'flowbite-react';
import { HiArrowCircleLeft, HiArrowCircleRight, HiArrowRight, HiCash, HiCheck, HiLockClosed, HiMail, HiPlus, HiRefresh } from 'react-icons/hi';

import { Banner, BannerCollapseButton } from 'flowbite-react';
import { HiX } from 'react-icons/hi';
import { MdPercent } from 'react-icons/md';
import CashbackBanner from './CashbackBanner';
import { useState } from 'react';
import { useDispatch } from 'react-redux';
import { addTransactionAction } from '../store/actions';

const Wallet = () => {
    const dispatch = useDispatch();
    const [addAmount, setAddAmount] = useState(0);
    const [withdrawAmount, setWithdrawAmount] = useState(0);

    const handleAddMoney = async () => {
        const newamount = addAmount;
        setAddAmount(0);
        const transaction = {
            "transactionType": "MONEY_ADDED",
            "transactionWith": "SELF",
            "amount": newamount
        }
        dispatch(addTransactionAction(transaction));
    }
    const handleWithdrawMoney = async () => {
        const newamount = withdrawAmount;
        setWithdrawAmount(0);
        const transaction = {
            "transactionType": "MONEY_WITHDRAWN",
            "transactionWith": "SELF",
            "amount": newamount
        }
        dispatch(addTransactionAction(transaction));
    }

    return (<div>
        <CashbackBanner />
        <Tabs aria-label="Tabs with underline" style="underline">
            <Tabs.Item active title="Add Money" icon={HiPlus}>
                <Card className="max-w-sm mx-auto">

                    <h3 className='text-xl'>Add Money</h3>

                    {/* ENTER AMOUNT */}
                    <>
                        <div className="max-w-md ">
                            <div className="mb-2 block">
                                <Label htmlFor="amount" value="Enter Amount to be Added (₹)" />
                            </div>
                            <TextInput id="amount" type="number" rightIcon={HiCash} placeholder="" required
                                value={addAmount}
                                onChange={(e) => setAddAmount(e.target.value)}
                            />
                        </div>
                    </>

                    <Button outline color='gray'
                        onClick={handleAddMoney}
                    >Add</Button>

                </Card >
            </Tabs.Item>

            <Tabs.Item title="Withdraw Money" icon={HiRefresh}>
                <Card className="max-w-sm mx-auto">

                    <h3 className='text-xl'>Withdraw Money</h3>
                    {/* ENTER AMOUNT */}
                    <>
                        <div className="max-w-md ">
                            <div className="mb-2 block">
                                <Label htmlFor="amount" value="Enter Amount to be withdrawn (₹)" />
                            </div>
                            <TextInput id="amount" type="number" rightIcon={HiCash} placeholder="" required
                                value={withdrawAmount}
                                onChange={(e) => setWithdrawAmount(e.target.value)}
                            />
                        </div>
                    </>

                    <Button outline color='gray'
                        onClick={handleWithdrawMoney}
                    >Withdraw</Button>

                </Card >
            </Tabs.Item>
        </Tabs>


    </div>

    );
}

export default Wallet





