import axios from 'axios';

const AutenticacaoAPI = axios.create({baseURL:"http://localhost:8080"});

async function realizarLogin(usuario){
    try{
        const response = await AutenticacaoAPI.post("/login",usuario);
        return response.data.token;
    }catch(error){
        console.log(error);
    }
}

async function verificaToken(){
    try{
        const response = await AutenticacaoAPI.get("/tarefa",{headers:{Authorization: localStorage.getItem('token')}});
        return response.status;
        
    }catch(error){
        return 403;
    }
}

export {
    realizarLogin,
    verificaToken
}