import { Button, Card, Label, TextInput } from 'flowbite-react';
import { HiArrowCircleLeft, HiArrowCircleRight, HiArrowRight, HiCheck, HiLockClosed, HiLogout, HiMail, HiTrash, HiUser } from 'react-icons/hi';
import { useDispatch, useSelector } from 'react-redux';
import { auth } from '../store/userSlice';
import { useNavigate } from 'react-router-dom';
import DeleteAccountModal from './DeleteAccountModal';
import { useEffect, useState } from 'react';

const AccountSettings = () => {
    const [username,setUsername]=useState("");
    const [openModal, setOpenModal] = useState(false);
    const dispatch = useDispatch();
    const handleLogout = () => {
        dispatch(auth(false));
        window.location.href = "/"
    }
    return (<div>
        <DeleteAccountModal openModal={openModal} setOpenModal={setOpenModal} />
        <Card className="max-w-sm mx-auto">
            <h3 className='text-xl'>Account Settings</h3>
            <Button color='default'
            >{localStorage.getItem("username")}<span className='mx-3'><HiUser /></span></Button>

            <Button color='gray'
                // onClick={()=>alert("TODO")}
                disabled
            >Change Password<span className='mx-3'><HiLockClosed /></span></Button>
            <Button color='red'
                onClick={()=>setOpenModal(!openModal)}
            >Delete Account<span className='mx-3'><HiTrash /></span></Button>
            <br /> <hr />
            <Button color='gray'
                onClick={handleLogout}
            >Logout<span className='mx-3'><HiLogout /></span></Button>

        </Card >
    </div>

    );
}

export default AccountSettings