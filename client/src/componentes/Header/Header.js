import { useLocation } from "react-router-dom";
const Header = ()=>{

    const location = useLocation();
    const caminho = location.pathname;

    return(
        <nav>
        <div class="nav-wrapper">
          <a class="center brand-logo">Lista de Tarefas</a>
          {caminho !== "/" ? 
          (
          <ul id="nav-mobile" class="right hide-on-med-and-down">
            <li><a href="home">Tarefas</a></li>
            <li><a href="adicionarTarefa">Adicionar Tarefa</a></li>
            <li><a href="usuarios">Usuários</a></li>
            <li><a href="adicionarUsuario">Adicionar Usuário</a></li>
          </ul>) : ("")
         }

        </div>
      </nav>
    )

}

export default Header;