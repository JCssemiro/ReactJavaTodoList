import {Route,Routes } from 'react-router-dom';
import Home from '../../pages/Home.js';
import UsuariosPage from '../../pages/UsuariosPage.js';
import CadastroTarefaPage from '../../pages/CadastroTarefaPage.js';
import CadastroUsuarioPage from '../../pages/CadastroUsuarioPage.js';
import EditarTarefaPage from '../../pages/EditarTarefaPage.js';
import EditarUsuarioPage from '../../pages/EditarUsuarioPage.js';
import LoginPage from '../../pages/LoginPage.js';
import { verificaToken } from '../../API/AutenticacaoService.js';
import {useState,useEffect} from 'react'

function RotaProtegida(){
    const [autenticado,setAutenticado] = useState(false);

    useEffect(()=>{
        async function verificarAutenticacao() {
            try {
                const res = await verificaToken();
                setAutenticado(res === 200);
            } catch (error) {
                setAutenticado(false);
            }
        }
        verificarAutenticacao();
    },[]);

    if (autenticado) {
        return (
            <Routes>
                <Route path="/" element={<LoginPage />} />
                <Route path="/home" element={<Home />} />
                <Route path="/usuarios" element={<UsuariosPage />} />
                <Route path="/adicionarUsuario" element={<CadastroUsuarioPage />} />
                <Route path="/adicionarTarefa" element={<CadastroTarefaPage />} />
                <Route path="/editarTarefa" element={<EditarTarefaPage />} />
                <Route path="/editarUsuario" element={<EditarUsuarioPage />} />
            </Routes>
        );
    } else {

        return (
            <Routes>
                <Route path="/" element={<LoginPage />} />
            </Routes>
        );
    }

}
export default RotaProtegida;