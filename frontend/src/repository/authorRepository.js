import axiosInstance from "../axios/axios";

const authorRepository = {
  findAll: async () => {
    return await axiosInstance.get("/authors");
  },
  findById: async (id) => {
    return await axiosInstance.get(`/authors/${id}`);
  },

  findByName: async (name) => {
    return await axiosInstance.get(`/authors/search?name=${name}`);
  },
  delete: async (id) => {
    return await axiosInstance.delete(`/authors/delete/${id}`);
  },
  edit: async(id, data) =>{

    return await axiosInstance.put(`/authors/update/${id}`, data)
  },
  create: (data) => axiosInstance.post("/authors", data),
};

export default authorRepository;
