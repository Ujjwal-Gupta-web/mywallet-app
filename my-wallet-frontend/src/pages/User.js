import { Button } from "flowbite-react"
import { useEffect, useState } from "react"
import { HiArrowLeft, HiBackspace } from "react-icons/hi"
import { useDispatch, useSelector } from "react-redux"
import { Link } from "react-router-dom"
import { convertToNumberString } from "../utility/convertToNumberString"
import { getAccountStatementAction } from "../store/actions"

const UserHome = ({ children }) => {
    const balance=useSelector(state=>state.user.balance)
    const [showBalance, setShowBalance] = useState(false);
    const dispatch=useDispatch();
    useEffect(()=>{
        dispatch(getAccountStatementAction())
    },[dispatch])
    return (
        <div>
            <div className='flex items-center justify-between'>
                <div>
                    <Link to="/">
                        <Button color='gray'><span className='mr-3'><HiArrowLeft /></span>Back </Button>
                    </Link>
                </div>
                <div>
                    <Button color='gray' onClick={() => setShowBalance(!showBalance)}>
                        {showBalance ? <>Available Balance : ₹{convertToNumberString(balance)}</> : <>Show Balance</>}
                    </Button>
                </div>
            </div>
            <div className="m-5">
                {children}
            </div>
        </div>
    )
}

export default UserHome



