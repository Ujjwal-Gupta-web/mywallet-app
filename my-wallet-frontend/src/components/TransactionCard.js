import { Card } from 'flowbite-react'
import React from 'react'
import { transactionTypeIcon } from '../utility/transactionTypeIcon'
import { formatDate } from '../utility/formatDate';

const TransactionCard = ({ transaction }) => {
    const Icon = transactionTypeIcon(transaction.transactionType);
    return (
        <Card className="max-w-sm w-full my-1">
            <div className='flex items-start justify-between'>
                <div>
                    <h5 className="text-[0.7rem] text-gray-700 dark:text-gray-400">
                        {transaction.transactionId}

                    </h5>
                </div>
            </div>
            <hr />
            <div className='flex items-start justify-between'>
                <div>
                    <Icon className='h-9 w-9' />
                </div>
                <div>
                    <h5 className="text-sm font-bold tracking-tight text-gray-900 dark:text-white">
                        {transaction.transactionType}
                    </h5>
                    <h5 className="text-xs text-gray-700 dark:text-gray-400">
                        {transaction.transactionWith}
                    </h5>
                </div>
                <div>
                    <h5 className="text-sm font-bold tracking-tight text-gray-900 dark:text-white">
                    ₹{transaction.amount}
                    </h5>
                </div>
            </div>
            <hr />
            <div>
                <div>
                    <h5 className="text-[0.7rem] text-gray-700 dark:text-gray-400">
                        {formatDate(transaction.timestamp)}
                    </h5>
                </div>


            </div>

        </Card>
    )
}

export default TransactionCard