import { BASE_URL } from "../utility/contants";

export const Contact={
    getContacts:async ()=>{
        const res = await fetch(`${BASE_URL}/contact/getContactsByUsername`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                "Authorization":localStorage.getItem("token")
            },
        });
        const ans = await res.json();
        return ans;
    },
    addContact:async(obj)=>{
        const res = await fetch(`${BASE_URL}/contact/addContact`, {
            method: "POST",
            body:JSON.stringify(obj),
            headers: {
                "Content-Type": "application/json",
                "Authorization":localStorage.getItem("token")
            },
        });
        const ans = await res.json();
        return ans;
    }
}
