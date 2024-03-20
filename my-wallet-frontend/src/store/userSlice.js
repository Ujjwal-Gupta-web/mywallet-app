import { createSlice, current } from "@reduxjs/toolkit";
import { Contact } from "../controllers/contact";
import AccountStatement from "../controllers/accountStatement";

const userSlice = createSlice({
  name: "user",
  initialState: {
    contacts: [],
    transactions: [],
    balance: 0.0,
    isCashbackAvailable:true,
    isAuth: localStorage.getItem("token") ? true : false,
    loading:false
  },
  reducers: {
    setContacts: (state, action) => {
      state.contacts=action.payload;
    },
    addContact: (state, action) => {
      console.log(action.payload)
      const newContact = action.payload;
      state.contacts=[...state.contacts,newContact];
    },
    setAccountStatement: (state, action) => {
     const accountStatement=action.payload;
     if(accountStatement){
       state.balance=accountStatement.balance;
       state.transactions=accountStatement.transactions.reverse();
       state.isCashbackAvailable=accountStatement.casbackAvailable;
     }
    },
    auth: (state, action) => {
      if (action.payload == false) {
        localStorage.removeItem("token");
      }
      state.isAuth = action.payload;
    },
    setLoading:(state,action)=>{
      state.loading=action.payload;
    }
  },
});

export const { setContacts, addContact, setAccountStatement, addTransaction, auth,setLoading } = userSlice.actions;

export default userSlice.reducer;