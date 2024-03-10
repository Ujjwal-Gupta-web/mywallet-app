import { Button, Card, Label, TextInput } from 'flowbite-react';
import { HiArrowCircleLeft, HiArrowCircleRight, HiArrowRight, HiCheck, HiLockClosed, HiMail } from 'react-icons/hi';
import User from "../controllers/user"
import { useState } from 'react';
import toast from 'react-hot-toast';
import { useNavigate } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { auth, getAccountStatement } from '../store/userSlice';

const Login = () => {
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const [user, setUser] = useState({ username: '', password: '' });
    const handleChange = (e) => {
        setUser({ ...user, [e.target.name]: e.target.value })
    }
    const handleLogin = async () => {
        console.log(user)
        const res = await User.login(user);
        if (res.tag) {
            toast.success(res.message);
            localStorage.setItem("token", res.data.token);
            dispatch(auth(true))
        }
        else {
            toast.error(res.message)
            setUser({ username: '', password: '' });
        }
    }
    return (<div>

        <Card className="max-w-sm mx-auto">

            <h3 className='text-xl'>Login</h3>

            {/* USERNAME */}
            <>
                <div className="max-w-md ">
                    <div className="mb-2 block">
                        <Label htmlFor="username" value="Your email (username)" />
                    </div>
                    <TextInput id="username" type="email" rightIcon={HiMail} placeholder="name@flowbite.com" required
                        value={user.username}
                        name="username" onChange={(e) => handleChange(e)}
                    />
                </div>
            </>

            {/* ENTER PASSWORD */}
            <>
                <div className="max-w-md ">
                    <div className="mb-2 block">
                        <Label htmlFor="password" value="Your password" />
                    </div>
                    <TextInput id="password" type="password" rightIcon={HiLockClosed} placeholder="" required
                        value={user.password}
                        name="password" onChange={(e) => handleChange(e)}
                    />
                </div>
            </>

            <Button color='gray'
                onClick={handleLogin}
            >Login<span className='mx-3'><HiCheck /></span></Button>

        </Card >
    </div>

    );
}

export default Login