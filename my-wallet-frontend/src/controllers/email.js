import { BASE_URL } from "../utility/contants";

export const Email={
    sendOtp: async (email) => {
        const res = await fetch(`${BASE_URL}/email/sendOTP/${email}`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
            },
        });
        const ans = await res.json();
        return ans;
    }
}