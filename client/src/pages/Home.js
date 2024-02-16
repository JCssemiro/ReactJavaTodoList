import {useState,useEffect} from 'react';
import { useNavigate } from 'react-router-dom';
import { deletarTarefa, getTodasTarefas } from '../API/TarefasService';

function Home(){
    const [tarefas,setTarefas] = useState([]);
    const navigate = useNavigate();

    async function fetchTarefas(){
        const listaTarefas = await getTodasTarefas();
        setTarefas(listaTarefas);
    }

    useEffect(()=>{
        fetchTarefas();
    },[tarefas]);

    async function handleExcluirButton(id){
        await deletarTarefa(id);
        navigate("/home");
    }

    function handleEditarButton(tarefa){
        const state = {tarefa};
        navigate("/editarTarefa",{state});
    }
    return(
        <div class="container">
            <h2 class="center">Tarefas</h2>
        <table>
            <thead>
            <tr>
                <th>Título</th>
                <th>Descrição</th>
                <th>Criado Em</th>
                <th>Atualizado em</th>
                <th>Completo</th>
                <th>Ações</th>
            </tr>
            </thead>
            {tarefas.map(tarefa => (
                        <tr key={tarefa.id}>
                            <td>{tarefa.titulo}</td>
                            <td>{tarefa.descricao}</td>
                            <td>{tarefa.criadoEm}</td>
                            <td>{tarefa.atualizadoEm}</td>
                            <td>{tarefa.concluido ? 'Sim' : 'Não'}</td>
                            <td>
                                <button class="waves-effect waves-light btn" onClick={() => handleEditarButton(tarefa)}>Editar</button>
                                <button class="waves-effect waves-light red btn" onClick={() => handleExcluirButton(tarefa.id)}>Excluir</button>
                            </td>
                        </tr>
                    ))}
            <tbody>
                
                
            </tbody>
        </table>
        </div>
    )
}

export default Home;