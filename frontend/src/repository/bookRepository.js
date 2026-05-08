import axiosInstance from "../axios/axios";

const bookRepository = {
  findAll: async () => {
    return await axiosInstance.get("/books");
  },
  findById: async (id) => {
    return await axiosInstance.get(`/books/${id}`);
  },
  update: async(id, data) =>{
    return await axiosInstance.put(`/books/update/${id}`, data)
  },

  create: async(data) =>{
    return await axiosInstance.post("/books", data)
  },
  delete: async(id)=>{
    return await axiosInstance.delete(`/books/delete/${id}`)
  }
};

export default bookRepository;
