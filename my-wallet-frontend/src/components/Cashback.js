import React, { useEffect, useState } from 'react'
import CashbackBanner from './CashbackBanner'
import TransactionCard from './TransactionCard'
import { useSelector } from 'react-redux'
import FilterModal from './FilterModal'

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
        <div onClick={() => setOpenModal(!openModal)}>FILTER</div>
        {dispTransactions?.map(transaction =>
          <TransactionCard key={transaction.transactionId} transaction={transaction} />
        )}
      </div>
    </div>);
}

export default Cashback