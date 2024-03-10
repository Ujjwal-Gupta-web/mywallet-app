import { BASE_URL } from "../utility/contants";


const AccountStatement = {
    getAccountStatement: async () => {
        const res = await fetch(`${BASE_URL}/account/getAccountStatementByUsername`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                "Authorization": localStorage.getItem("token")
            },
        });
        const ans = await res.json();
        return ans;
    },
    addTransaction: async (obj) => {
        const res = await fetch(`${BASE_URL}/account/addTransaction`, {
            method: "POST",
            body: JSON.stringify(obj),
            headers: {
                "Content-Type": "application/json",
                "Authorization": localStorage.getItem("token")
            },
        });
        const ans = await res.json();
        return ans;
    },
    getTransactionByTransactionType: async (obj) => {
        const res = await fetch(`${BASE_URL}/account/getTransactionsByTransactionType/${obj.transactionType}`, {
            method: "POST",
            body: JSON.stringify(obj),
            headers: {
                "Content-Type": "application/json",
                "Authorization": localStorage.getItem("token")
            },
        });
        const ans = await res.json();
        return ans;
    }
}

export default AccountStatement;