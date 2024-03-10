import { Button, Navbar } from 'flowbite-react';
import { useDispatch, useSelector } from 'react-redux';
import { auth } from '../store/userSlice';

const Header = () => {
    const isAuth = useSelector(state => state.user.isAuth);
    const dispatch = useDispatch();
    const handleLogout = () => {
        dispatch(auth(false));
        window.location.href = "/"
    }
    return (
        <Navbar fluid rounded className='bg-blue-400'>
            <Navbar.Brand onClick={() => window.location.href = "/"}>
                <img src="https://images.vexels.com/media/users/3/263260/isolated/preview/fd56d6553b8ad0b4ffb08c5515f131da-wallet-with-dollar-bills.png" className="mr-3 h-6 sm:h-9" alt="note-down-logo" />
                <span className="self-center whitespace-nowrap text-xl font-semibold dark:text-white">MyWallet</span>
            </Navbar.Brand>
            <Navbar.Toggle className='text-dark' />
            {isAuth ?
                <Navbar.Collapse>
                    <Navbar.Link className='text-dark cursor-pointer' onClick={handleLogout}>Logout</Navbar.Link>
                </Navbar.Collapse>
                :
                <Navbar.Collapse>
                    <Navbar.Link className='text-dark cursor-pointer' href="/login">Login</Navbar.Link>
                    <Navbar.Link className='text-dark cursor-pointer' href="/signup">Signup</Navbar.Link>
                </Navbar.Collapse>
            }

        </Navbar>
    )
}

export default Header