import { Button, Card, Label, TextInput } from 'flowbite-react';
import { useState } from 'react';
import { HiArrowCircleLeft, HiArrowCircleRight, HiArrowRight, HiCheck, HiLockClosed, HiMail } from 'react-icons/hi';
import User from "../controllers/user"
import { Email } from "../controllers/email"
import toast from 'react-hot-toast';

const Signup = () => {
    const [user, setUser] = useState({ username: "", password: "" });
    const [isClicked, setIsClicked] = useState(false);
    const [otp, setOtp] = useState("")

    const getOTP = async () => {
        setIsClicked(true)
        const toastId = toast.loading("Please Wait....")
        const res = await Email.sendOtp(user.username);
        if (res.tag) {
            toast.success(res.message, { id: toastId });
            localStorage.setItem("OTP", res.data);
        }
        else toast.error(res.message, { id: toastId })
        setIsClicked(false)
    }

    const verifyOTP = (e) => {
        setIsClicked(true)
        const toastId = toast.loading("Please Wait....")
        const actualOtp = localStorage.getItem("OTP");
        if (actualOtp === otp) {
            toast.success("OTP verification success, please proceed immediately");
            localStorage.removeItem("OTP")
        }
        else toast.error("OTP not valid")
        setIsClicked(false)
    }

    const handleSignup = async () => {
        setIsClicked(true)
        const toastId = toast.loading("Please Wait....")
        const res = await User.signup(user);
        if (res.tag) {
            toast.success(res.message, { id: toastId });
            window.location.href = "/login"
        }
        else toast.error(res.message, { id: toastId })
        setIsClicked(true)
    }

    const handleChange = (e) => {
        setUser({ ...user, [e.target.name]: e.target.value });
    }

    return (<div>

        <Card className="max-w-sm mx-auto">

            <h3 className='text-xl'>Signup</h3>

            {/* USERNAME */}
            <>
                <div className="max-w-md ">
                    <div className="mb-2 block">
                        <Label htmlFor="username" value="Your email (username)" />
                    </div>
                    <TextInput id="username" type="email" rightIcon={HiMail} placeholder="name@flowbite.com" required
                        name="username"
                        value={user.username}
                        onChange={handleChange}
                    />
                </div>
                <Button color='gray'>Get OTP <span className='mx-3'
                    onClick={getOTP}
                    disabled={isClicked}
                ><HiArrowRight /></span></Button>
            </>

            {/* VERIFY OTP */}
            <>
                <div className="max-w-md">
                    <div className="mb-2 block">
                        <Label htmlFor="otp" value="OTP" />
                    </div>
                    <TextInput id="otp" type="tel" required
                        value={otp}
                        onChange={(e) => setOtp(e.target.value)}
                    />
                </div>
                <Button color='gray'
                    onClick={verifyOTP}
                    disabled={isClicked}
                >Verify OTP <span className='mx-3'><HiArrowRight /></span></Button>
            </>


            {/* ENTER PASSWORD */}
            <>
                <div className="max-w-md ">
                    <div className="mb-2 block">
                        <Label htmlFor="password" value="Your password" />
                    </div>
                    <TextInput id="password" type="password" rightIcon={HiLockClosed} placeholder="" required
                        name='password'
                        value={user.password}
                        onChange={handleChange}
                    />
                </div>
                <Button color='gray'
                    onClick={handleSignup}
                    disabled={isClicked}
                >Signup<span className='mx-3'><HiCheck /></span></Button>
            </>


        </Card >
    </div>

    );
}

export default Signup
