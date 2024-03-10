import { Banner, BannerCollapseButton } from 'flowbite-react'
import React, { useEffect, useState } from 'react'
import { HiX } from 'react-icons/hi'
import { MdPercent } from 'react-icons/md'
import { useSelector } from 'react-redux'


const CashbackBanner = () => {

    const transactions = useSelector(state => state.user.transactions);
    const isCasbackAvailable = useSelector(state => state.user.isCashbackAvailable);
    const [moneySentTransactions, setMoneySentTransactions] = useState(0);
    useEffect(() => {
        setMoneySentTransactions(transactions.filter(transaction => transaction.transactionType === "MONEY_SENT").length)
    }, [transactions])
    return (<>
        {
            isCasbackAvailable ?
                <Banner>
                    <div className="flex w-full justify-between border-t border-gray-200 bg-gray-50 p-4 dark:border-gray-600 dark:bg-gray-700">
                        <div className="mx-auto flex items-center">
                            <p className="flex items-center text-sm font-normal text-dark dark:text-gray-400">
                                <span className="mr-3 inline-flex h-6 w-6 items-center justify-center rounded-full bg-gray-200 p-1 dark:bg-gray-600">
                                    <MdPercent className="h-4 w-4" />
                                </span>
                                <span className="[&_p]:inline">
                                    CASHBACK AVAILABLE : {moneySentTransactions > 0 ? "1% of amount cahsback upto 100" : "5% of amount cahsback upto 1000"}
                                </span>
                            </p>
                        </div>
                        <BannerCollapseButton color="gray" className="border-0 bg-transparent text-gray-500 dark:text-gray-400">
                            <HiX className="h-4 w-4" />
                        </BannerCollapseButton>
                    </div>
                </Banner> : <></>
        }
    </>
    )
}

export default CashbackBanner