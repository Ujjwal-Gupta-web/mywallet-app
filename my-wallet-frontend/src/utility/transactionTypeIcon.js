import { HiPlusCircle, HiRefresh } from "react-icons/hi";
import { IoMdGift } from "react-icons/io";
import { MdCallReceived } from "react-icons/md";
import { MdCallMade } from "react-icons/md";
import { TbPigMoney } from "react-icons/tb";

export const transactionTypeIcon=(transactionType)=>{
    switch(transactionType){
        case  "MONEY_RECEIVED": return MdCallReceived;
        break;
        case "MONEY_SENT":return MdCallMade;
        break;
        case "CASHBACK":return IoMdGift;
        break;
        case "MONEY_ADDED":return HiPlusCircle;
        break;
        case "MONEY_WITHDRAWN":return HiRefresh;
        break;
    }
    return TbPigMoney;
}