import React, { useEffect, useState } from 'react'
import TransactionCard from './TransactionCard'
import { useSelector } from 'react-redux'
import FilterModal from './FilterModal';

const TransactionHistory = () => {
    const transactions = useSelector(state => state.user.transactions);
    const [dispTransactions,setDispTransactions]=useState([]);
    const [openModal,setOpenModal]=useState(false);
    useEffect(()=>{
        setDispTransactions(transactions);
    },[transactions])
    return (<>
    <FilterModal openModal={openModal} original={transactions} setOpenModal={setOpenModal} dispTransactions={dispTransactions} setDispTransactions={setDispTransactions}/>
        <div className='flex flex-col items-center justify-center'>
            <div onClick={()=>setOpenModal(!openModal)}>FILTER</div>
            {dispTransactions?.map(transaction => <TransactionCard key={transaction.transactionId} transaction={transaction} />)}
        </div>
    </>

    )
}

export default TransactionHistory