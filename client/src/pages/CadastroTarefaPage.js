import {useState} from 'react';
import { useNavigate } from 'react-router-dom';
import { cadastrarTarefa } from '../API/TarefasService';

function CadastroTarefaPage(){
    const navigate = useNavigate();
    const [novaTarefa,setNovaTarefa] = useState({
        titulo: "",
        descricao:""
    })

async function handleSubmit(e){
    e.preventDefault();
    await cadastrarTarefa(novaTarefa);
    navigate("/home");
}

    return(
        <div class="row">
        <div class="col s12 m6 offset-m3">
          <h3 class="center">Adicionar nova tarefa</h3>
          <form class="col s12" onSubmit={handleSubmit}>
            <div class="row center-align"> 
              <div class="input-field col s12">
                <input id="titulo" type="text" class="validate" onChange={(e)=>{setNovaTarefa({titulo:e.target.value,descricao:novaTarefa.descricao})}}/>
                <label htmlFor="titulo">Título</label>
              </div>
            </div>
            <div class="input-field col s12">
                <textarea id="decricao" class="materialize-textarea" onChange={(e)=>{setNovaTarefa({titulo:novaTarefa.titulo,descricao:e.target.value})}}></textarea>
                <label for="descricao">Descrição</label>
            </div>
            <div className="row center-align">
              <button className="btn waves-effect waves-light" type="submit" name="action">Adicionar</button>
            </div>
          </form>
        </div>
      </div>

    )

}

export default CadastroTarefaPage;