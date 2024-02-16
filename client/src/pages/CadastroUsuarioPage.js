
function CadastroUsuarioPage(){
    return(
        <div class="row">
        <div class="col s12 m6 offset-m3">
          <h3 class="center">Adicionar novo usuário</h3>
          <form class="col s12">
            <div class="row center-align"> 
              <div class="input-field col s12">
                <input id="titulo" type="text" class="validate" />
                <label htmlFor="titulo">Título</label>
              </div>
            </div>
            <div class="input-field col s12">
                <textarea id="decricao" class="materialize-textarea"></textarea>
                <label for="descricao">Descrição</label>
            </div>
            <div class="row center-align">
              <button class="btn waves-effect waves-light" type="submit" name="action">Adicionar</button>
            </div>
          </form>
        </div>
      </div>
    )
}

export default CadastroUsuarioPage;