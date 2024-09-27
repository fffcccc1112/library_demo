import axios from "axios";

const request = axios.create({
    baseURL: 'localhost:8088/'
})

export default request