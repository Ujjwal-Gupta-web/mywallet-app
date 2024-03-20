import React, { useEffect, useState } from 'react'
import CashbackBanner from './CashbackBanner'
import TransactionCard from './TransactionCard'
import { useSelector } from 'react-redux'
import FilterModal from './FilterModal'
import RecordNotFound from './RecordNotFound'

const Cashback = () => {
  const transactions = useSelector(state => state.user.transactions);
  const [dispTransactions, setDispTransactions] = useState([]);
  const [openModal, setOpenModal] = useState(false);
  useEffect(() => {
    setDispTransactions(transactions.filter(transaction => transaction.transactionType === "CASHBACK"));
  }, [transactions])
  return (
    <div>
      <CashbackBanner />
      <FilterModal type={"CASHBACK"} openModal={openModal} original={transactions} setOpenModal={setOpenModal} dispTransactions={dispTransactions} setDispTransactions={setDispTransactions} />
      <div className='flex flex-col items-center justify-center'>
        <div onClick={() => setOpenModal(!openModal)} className='border border-lg rounded-md p-3 cursor-pointer'>FILTER</div>
        {dispTransactions?.length > 0 ?
          dispTransactions.map(transaction =>
            <TransactionCard key={transaction.transactionId} transaction={transaction} />
          ) :
          <RecordNotFound />
        }
      </div>
    </div>);
}

export default Cashback