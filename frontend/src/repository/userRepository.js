import axiosInstance from "../axios/axios";

const userRepository= {
    login : async(data) =>{
        return await axiosInstance("/user/login", data);
    },
    register :async(data)=>{
        return await axiosInstance("/user/register", data);
    },

};

export default userRepository;