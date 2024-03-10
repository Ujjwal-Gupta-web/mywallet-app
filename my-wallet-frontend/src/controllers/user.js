import { BASE_URL } from "../utility/contants";


const User = {
    login: async (obj) => {
        const res = await fetch(`${BASE_URL}/user/login`, {
            method: "POST",
            body: JSON.stringify(obj),
            headers: {
                "Content-Type": "application/json",
            },
        });
        const ans = await res.json();
        return ans;
    },
    signup: async (obj) => {
        const res = await fetch(`${BASE_URL}/user/signup`, {
            method: "POST",
            body: JSON.stringify(obj),
            headers: {
                "Content-Type": "application/json",
            },
        });
        const ans = await res.json();
        return ans;
    },
    deleteUser: async () => {
        const res = await fetch(`${BASE_URL}/user/delete`, {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json",
                "Authorization": localStorage.getItem("token"),
            },
        });
        const ans = await res.json();
        return ans;
    },
    changePassword: async () => {
        const res = await fetch(`${BASE_URL}/user/changePassword`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
        });
        const ans = await res.json();
        return ans;
    }
}

export default User;