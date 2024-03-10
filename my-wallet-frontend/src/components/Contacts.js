import { Button, Card, Label, TextInput } from 'flowbite-react';
import { HiArrowCircleLeft, HiArrowCircleRight, HiArrowRight, HiCheck, HiLockClosed, HiMail, HiPlus, HiSearch, HiX } from 'react-icons/hi';
import AddContact from './AddContact';
import { useDebugValue, useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { getContactsAction } from '../store/actions';

const Contacts = () => {
    const [isAddContactActive, setIsAddContactActive] = useState(false);
    const dispatch = useDispatch();
    const contacts = useSelector(state => state.user.contacts)
    const [dispContact, setDispContacts] = useState([]);
    useEffect(() => {
        dispatch(getContactsAction());
    }, [dispatch]);
    useEffect(() => {
        setDispContacts(contacts)
    }, [contacts])

    const handleSearch = (e) => {
        setDispContacts(contacts.filter(contact => contact.includes(e.target.value)));
    }

    return (
        <div>
            <Card className="max-w-sm mx-auto">

                <h3 className='text-xl'>Contacts</h3>

                {/* Search */}
                <>
                    <div className="max-w-md ">
                        <div className="mb-2 block">
                            <Label htmlFor="search" value="Search contact" />
                        </div>
                        <TextInput id="search" type="text" rightIcon={HiSearch} placeholder="name@flowbite.com" required
                            onChange={handleSearch}
                        />
                    </div>
                </>

                <Button
                    onClick={() => setIsAddContactActive(!isAddContactActive)}
                    color='gray'>
                    {isAddContactActive ?
                        <>Hide Add Contact<span className='mx-3'><HiX /></span></>
                        :
                        <>Add Contact<span className='mx-3'><HiPlus /></span></>
                    }
                </Button>

            </Card >
            {isAddContactActive && <AddContact />}
            <Card className="max-w-sm mx-auto my-3">
                <div className="flow-root">
                    <ul className="divide-y divide-gray-200 dark:divide-gray-700">
                        {dispContact?.map(
                            contact =>
                                <li key={contact} className="py-3 sm:py-4">
                                    <div className="flex items-center space-x-4">
                                        <div className="min-w-0 flex-1">
                                            <p className="truncate text-sm font-medium text-gray-900 dark:text-white">{contact}</p>
                                        </div>
                                    </div>
                                </li>
                        )}


                    </ul>
                </div>
            </Card>
        </div>
    )
}

export default Contacts





