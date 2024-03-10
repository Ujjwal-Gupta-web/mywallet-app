import toast from "react-hot-toast";
import { Contact } from "../controllers/contact";
import { setContacts, addContact, setAccountStatement, setLoading } from "./userSlice";
import AccountStatement from "../controllers/accountStatement";

export const getContactsAction = () => async (dispatch) => {
    try {
        const res = await Contact.getContacts() // Fetch contacts from API
        let contacts = [];
        if (res.tag) {
            contacts = res.data.contactList;
        }
        dispatch(setContacts(contacts)); // Dispatch action to set contacts in state
    } catch (error) {
        console.error("Error fetching contacts:", error);
    }
};

export const addContactAction = (contact) => async (dispatch) => {
    try {
        const res = await Contact.addContact({ contact }) // Fetch contacts from API
        if (res.tag === true) {
            toast.success(res.message)
            dispatch(addContact(contact)); // Dispatch action to set contacts in state
        }
        else {
            toast.error(res.message)
        }
    } catch (error) {
        console.error("Error adding contacts:", error);
    }
};

export const getAccountStatementAction = () => async (dispatch) => {
    try {
        const res = await AccountStatement.getAccountStatement(); // Fetch contacts from API
        if (res.tag === true) {
            toast.success(res.message)
            dispatch(setAccountStatement(res.data)); // Dispatch action to set contacts in state
        }
        else {
            toast.error(res.message)
        }
    } catch (error) {
        console.error("Error adding contacts:", error);
    }
};

export const addTransactionAction = (transaction) => async (dispatch) => {
    try {
        const toastId = toast.loading('Loading...');
        if (transaction.amount <= 0) { toast.error("Transaction Amount should be greater than 0.", { id: toastId }); return; }
        const res = await AccountStatement.addTransaction(transaction) // Fetch contacts from API
        if (res.tag === true) {
            toast.success(res.message, { id: toastId })
            dispatch(setAccountStatement(res.data)); // Dispatch action to set contacts in state
        }
        else {
            toast.error(res.message, { id: toastId })
        }
    } catch (error) {
        console.error("Error adding contacts:", error);
    }
};

