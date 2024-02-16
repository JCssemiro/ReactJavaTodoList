
function EditarUsuarioPage(){

    return(
        <div className="row">
        <div className="col s12 m6 offset-m3">
          <h3 className="center">Editor de Tarefa</h3>
          <form className="col s12">
            <div className="row center-align"> 
              <div className="input-field col s12">
                <input id="login" type="text" className="validate" />
                <label htmlFor="login">Login</label>
              </div>
            </div>
            <div class="input-field col s12">
                <input id="senha" type="text" className="validate" />
                <label htmlFor="senha">Senha</label>
            </div>

            <div className="row center-align">
              <button className="btn waves-effect waves-light" type="submit" name="action">Adicionar</button>
            </div>
          </form>
        </div>
      </div>
    )
}

export default EditarUsuarioPage;