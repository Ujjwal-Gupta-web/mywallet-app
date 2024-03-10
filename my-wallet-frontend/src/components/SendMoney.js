import { Button, Card, Label, TextInput } from 'flowbite-react';
import { useState } from 'react';
import { HiArrowCircleLeft, HiArrowCircleRight, HiArrowRight, HiCash, HiCheck, HiLockClosed, HiMail } from 'react-icons/hi';
import { useDispatch } from 'react-redux';
import { addTransactionAction } from '../store/actions';
import Loading from './Loading';

const SendMoney = () => {

    const dispatch=useDispatch();
    const [transaction,setTransaction]=useState({"transactionWith":"","transactionType":"MONEY_SENT","amount":0})
    const [isClicked,setIsClicked]=useState(false);

    const handleChange=(e)=>{
        setTransaction({...transaction,[e.target.name]:e.target.value})
    }
    const handleSendMoney=async()=>{
        setIsClicked(true);
        dispatch(addTransactionAction(transaction))
        setTransaction({"transactionWith":"","transactionType":"MONEY_SENT","amount":0});
        setIsClicked(false);
    }

    return (<div>
        <Card className="max-w-sm mx-auto">

            <h3 className='text-xl'>Send Money</h3>

            {/* to */}
            <>
                <div className="max-w-md ">
                    <div className="mb-2 block">
                        <Label htmlFor="username" value="Receiver's username" />
                    </div>
                    <TextInput id="username" type="email" rightIcon={HiMail} placeholder="name@flowbite.com" required 
                    // defaultValue={""}
                    value={transaction.transactionWith}
                    name="transactionWith"
                    onChange={handleChange}
                    />
                </div>
            </>

            {/* ENTER AMOUNT */}
            <>
                <div className="max-w-md ">
                    <div className="mb-2 block">
                        <Label htmlFor="amount" value="Enter Amount" />
                    </div>
                    <TextInput id="amount" type="number" rightIcon={HiCash} placeholder="" required 
                    //    defaultValue={0}
                       value={transaction.amount}
                    name="amount"
                    onChange={handleChange}
                    />
                </div>
            </>

                <Button outline color='gray'
                onClick={handleSendMoney}
                disabled={isClicked}
                >Send Money</Button>

        </Card >
    </div>

    );
}

export default SendMoney