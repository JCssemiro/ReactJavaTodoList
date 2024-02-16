import axios from 'axios';

const TarefasApi = axios.create({baseURL:"http://localhost:8080",headers:{Authorization:localStorage.getItem('token')}});

async function getTodasTarefas(){
    try{
    const response = await TarefasApi.get("/tarefa");
    return response.data;
    }catch(error){
        throw new Error(error.response.data);
    }
}

async function getTarefaPorId(id){
    try{
        const response = await TarefasApi.get(`/tarefa/${id}`);
        return response.data;
    }catch(error){
        throw new Error(error.response.data);
    }
}

async function cadastrarTarefa(tarefa){
    try {
      const response = await TarefasApi.post(`/tarefa`, tarefa);
      return response.data;
    } catch (error) {
      throw new Error(error.response.data);
    }
  }

 async function editarTarefa(tarefa){
    try {
      const response = await TarefasApi.put(`/tarefa`, tarefa);
      return response.data;
    } catch (error) {
      console.log(error);
      console.log(tarefa);
    }
  }
async function deletarTarefa(id){
    try {
      const response = await TarefasApi.delete(`/tarefa/${id}`);
      return response.status;
    } catch (error) {
      throw new Error(error.response.data);
    }
  }

  export {
    getTodasTarefas,
    getTarefaPorId,
    cadastrarTarefa,
    editarTarefa,
    deletarTarefa
  }