import { useLocation,useNavigate } from "react-router-dom";
import {useState} from 'react';
import { editarTarefa } from "../API/TarefasService";

function EditarTarefaPage(){
    const navigate = useNavigate();
    const location = useLocation();
    const {tarefa} = location.state || {};
    const [titulo,setTitulo] = useState(tarefa ? tarefa.titulo : "");
    const [descricao,setDescricao] = useState(tarefa ? tarefa.descricao : "");
    const [concluido,setConcluido] = useState(tarefa ? tarefa.concluido : false);
    const [tarefaEditada,setTarefaEditada] = useState({id: tarefa.id,titulo:titulo,descricao:descricao,concluido:concluido,criadoEm:tarefa.criadoEm,atualizadoEm:tarefa.atualizadoEm});
    const handleSubmit = async (e)=>{
        e.preventDefault();
        setTarefaEditada({
            id:tarefa.id,
            titulo:titulo,
            descricao:descricao,
            concluido:concluido,
            criadoEm:tarefa.criadoEm,
            atualizadoEm:tarefa.atualizadoEm
        })
        editarTarefa(tarefaEditada);
        navigate("/home");
    }

    const handleClickCheckbox = ()=>{
        setConcluido(!concluido);
        console.log(concluido);
    }

    return(
        <div className="row">
        <div className="col s12 m6 offset-m3">
          <h3 className="center">Editor de Tarefa</h3>
          <form className="col s12" onSubmit={handleSubmit}>
            <div className="row center-align"> 
              <div className="input-field col s12">
                <input id="titulo" type="text" className="validate" value={titulo} onChange={(e)=>{setTitulo(e.target.value);setTarefaEditada({...tarefaEditada,titulo:e.target.value})}}/>
                <label htmlFor="titulo">Título</label>
              </div>
            </div>
            <div class="input-field col s12">
                <textarea id="decricao" class="materialize-textarea" value={descricao} onChange={(e)=>{setDescricao(e.target.value);setTarefaEditada({...tarefaEditada,descricao:e.target.value})}}></textarea>
                <label for="descricao">Descrição</label>
            </div>
            <p>
                <label >
                    <input type="checkbox" checked={concluido} onClick={handleClickCheckbox} onChange={(e)=>{setTarefaEditada({...tarefaEditada,concluido:e.target.checked})}}/>
                    <span>Concluído</span>
                </label>
            </p>

            <div className="row center-align">
              <button className="btn waves-effect waves-light" type="submit" name="action">Salvar</button>
            </div>
          </form>
        </div>
      </div>
    )
}

export default EditarTarefaPage;