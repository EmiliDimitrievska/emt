import axiosInstance from "../axios/axios";

const countryRepository = {
  findAll: async () => {
    return await axiosInstance.get("/countries");
  },
  findById: async (id) => {
    return await axiosInstance.get(`/countries/${id}`);
  }, 
};

export default countryRepository;
