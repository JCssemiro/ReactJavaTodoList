import axios from 'axios';

const serviceApi = axios.create({baseURL: "http://localhost:8080",headers:{Authentication:localStorage.getItem('token')}});


export async function listarTodosUsuarios() {
    try {
      const response = await serviceApi.get(`/usuario`);
      return response.data;
    } catch (error) {
      throw new Error(error.response.data);
    }
  }
  
export async function listarUsuarioPorId(id) {
try {
    const response = await serviceApi.get(`/usuario/${id}`);
    return response.data;
} catch (error) {
    throw new Error(error.response.data);
}
}

export async function cadastrarUsuario(usuario) {
try {
    const response = await serviceApi.post(`/usuario`, usuario);
    return response.data;
} catch (error) {
    throw new Error(error.response.data);
}
}

export async function editarUsuario(usuario) {
try {
    const response = await serviceApi.put(`/usuario`, usuario);
    return response.data;
} catch (error) {
    throw new Error(error.response.data);
}
}

export async function deletarUsuario(id) {
try {
    await serviceApi.delete(`/usuario/${id}`);
} catch (error) {
    throw new Error(error.response.data);
}
}