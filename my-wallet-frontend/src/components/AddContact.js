import { Button, Card, Label, TextInput } from 'flowbite-react';
import { useState } from 'react';
import { HiArrowCircleLeft, HiArrowCircleRight, HiArrowRight, HiCheck, HiLockClosed, HiMail, HiPlus, HiSearch, HiUserAdd } from 'react-icons/hi';
import { useDispatch } from 'react-redux';
import { addContactAction } from '../store/actions';

const AddContact = () => {
    const [contact,setContact]=useState("");
    const dispatch=useDispatch();

    const handleAddContact=()=>{
        const newContact=contact;
        setContact("")
        dispatch(addContactAction(newContact));
    }

    return (<div className='my-3'>
        <Card className="max-w-sm mx-auto">
            {/* Add */}
            <>
                <div className="max-w-md ">
                    <div className="mb-2 block">
                        <Label htmlFor="contact" value="Add contact" />
                    </div>
                    <TextInput id="contact" type="text" rightIcon={HiUserAdd} placeholder="name@flowbite.com" required 
                    defaultValue={""}
                    onChange={(e)=>setContact(e.target.value)}
                    />
                </div>
            </>
            <Button color='gray'
            onClick={handleAddContact}
            >Add <span className='mx-3'><HiUserAdd /></span></Button>
        </Card>
    </div>
    )
}

export default AddContact