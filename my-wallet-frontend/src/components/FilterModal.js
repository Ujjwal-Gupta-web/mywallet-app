import { Button, Datepicker, Dropdown, Label, ListGroup, Modal, Tabs, TextInput } from 'flowbite-react';
import { useState } from 'react';
import { HiSearch } from 'react-icons/hi';
import { isDateInRange } from '../utility/isDateInRange';

const FilterModal = ({ type, openModal, setOpenModal, original, dispTransactions, setDispTransactions }) => {

    const [filterObj, setFilterObj] = useState({});
    const transactions = original;

    const handleFilterTransactionType = () => {
        setOpenModal(false)
        if (filterObj.transactionType) {
            setDispTransactions(dispTransactions.filter(transaction => transaction.transactionType === filterObj.transactionType))
        }
    }
    const handleFilterAmount = () => {
        setOpenModal(false)
        if (filterObj.mini && filterObj.maxi) {
            setDispTransactions(dispTransactions.filter(transaction => transaction.amount >= filterObj.mini && transaction.amount <= filterObj.maxi));
        }
    }
    const handleFilterDate = () => {
        console.log(filterObj)
        setOpenModal(false)
        if (filterObj.start && filterObj.end) {
            setDispTransactions(dispTransactions.filter(transaction => isDateInRange(filterObj.start, filterObj.end, transaction.timestamp)))
        }
    }

    const handleRemoveFilter = () => {
        setOpenModal(false); setFilterObj({});
        setDispTransactions(transactions);
    }

    return (
        <>
            <Modal show={openModal} className="w-100 h-[100%]" onClose={() => setOpenModal(false)} popup>
                <Modal.Header />
                <Modal.Body>
                    <div className='flex items-center justify-between'>
                        <h3 className="mb-5 text-lg font-normal text-gray-500 dark:text-gray-400">
                            Filter
                        </h3>
                        <Button color="gray" onClick={handleRemoveFilter}>
                            Remove All filters
                        </Button>
                    </div>
                    <div className='my-3'>
                        <Tabs aria-label="Default tabs" style="default">
                            {type === "CASHBACK" || <Tabs.Item active title="Type" >
                                <Dropdown color='light' label={filterObj?.transactionType ? filterObj?.transactionType : "Select Transaction Type"} dismissOnClick={false}>
                                    <Dropdown.Item onClick={() => setFilterObj({ ...filterObj, transactionType: "MONEY_RECEIVED" })}>MONEY_RECEIVED</Dropdown.Item>
                                    <Dropdown.Item onClick={() => setFilterObj({ ...filterObj, transactionType: "MONEY_SENT" })}>MONEY_SENT</Dropdown.Item>
                                    <Dropdown.Item onClick={() => setFilterObj({ ...filterObj, transactionType: "CASHBACK" })}>CASHBACK</Dropdown.Item>
                                    <Dropdown.Item onClick={() => setFilterObj({ ...filterObj, transactionType: "MONEY_ADDED" })}>MONEY_ADDED</Dropdown.Item>
                                    <Dropdown.Item onClick={() => setFilterObj({ ...filterObj, transactionType: "MONEY_WITHDRAWN" })}>MONEY_WITHDRAWN</Dropdown.Item>
                                </Dropdown>
                                <div className="text-center mt-5">
                                    <div className="flex justify-center gap-4">
                                        <Button color="dark" onClick={handleFilterTransactionType}>
                                            Apply
                                        </Button>
                                        <Button color="gray" onClick={() => { setOpenModal(false); setFilterObj({}); }}>
                                            Close
                                        </Button>
                                    </div>
                                </div>
                            </Tabs.Item>}
                            <Tabs.Item title="Date" >
                                <div className="max-w-md ">
                                    <div className="mb-2 block">
                                        <Label htmlFor="from" value="From" />
                                    </div>
                                    <input type="date" id="from" name="from" onChange={(e) => setFilterObj({ ...filterObj, start: e.target.value })} />
                                </div>
                                <div className="max-w-md my-3">
                                    <div className="mb-2 block">
                                        <Label htmlFor="to" value="To" />
                                    </div>
                                    <input type="date" id="to" name="to" onChange={(e) => setFilterObj({ ...filterObj, end: e.target.value })} />
                                </div>
                                <div className="text-center mt-5">
                                    <div className="flex justify-center gap-4">
                                        <Button color="dark" onClick={handleFilterDate}>
                                            {"Apply"}
                                        </Button>
                                        <Button color="gray" onClick={() => { setOpenModal(false); setFilterObj({}); }}>
                                            Close
                                        </Button>
                                    </div>
                                </div>
                            </Tabs.Item>
                            <Tabs.Item title="Amount" >
                                <div className="max-w-md ">
                                    <div className="mb-2 block">
                                        <Label htmlFor="min_amount" value="Min Amount" />
                                    </div>
                                    <TextInput id="min_amount" name='min_amount' type="text" placeholder="" onChange={(e) => setFilterObj({ ...filterObj, mini: e.target.value })} required />
                                </div>
                                <div className="max-w-md ">
                                    <div className="mb-2 block">
                                        <Label htmlFor="max_amount" value="Max Amount" />
                                    </div>
                                    <TextInput id="max_amount" name='max_amount' type="text" placeholder="" onChange={(e) => setFilterObj({ ...filterObj, maxi: e.target.value })} required />
                                </div>
                                <div className="text-center mt-5">
                                    <div className="flex justify-center gap-4">
                                        <Button color="dark" onClick={handleFilterAmount}>
                                            {"Apply"}
                                        </Button>
                                        <Button color="gray" onClick={() => { setOpenModal(false); setFilterObj({}); }}>
                                            Close
                                        </Button>
                                    </div>
                                </div>
                            </Tabs.Item>
                        </Tabs>
                    </div>


                </Modal.Body>
            </Modal>
        </>
    )
}

export default FilterModal




