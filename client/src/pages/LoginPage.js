import M from "materialize-css";
import { useState,useEffect } from "react";
import {useNavigate} from 'react-router-dom';
import {realizarLogin} from '../API/AutenticacaoService';


const LoginPage=()=>{
    const [usuario,setUsuario] = useState({login:"",senha:""});
    const navigate = useNavigate();

    const handleSubmit = async (e)=>{
        e.preventDefault();
        try{
            const token = await realizarLogin(usuario);
            localStorage.setItem("token",token);
            if(token){
            navigate("/home");
            }
        }catch(error){
            console.log("Erro de autentiacação:",error);
        }
    }

    useEffect(()=>{
        M.AutoInit();
    },[]);

    return(
        <div className="row">
        <div className="col s12 m6 offset-m3">
          <h3 className="center">Faça login</h3>
          <form className="col s12" onSubmit={handleSubmit}>
            <div className="row center-align"> 
              <div className="input-field col s12">
                <input id="login" type="text" className="validate" onChange={(e)=>setUsuario({...usuario,login:e.target.value})}/>
                <label htmlFor="login">Login</label>
              </div>
            </div>
            <div className="row center-align">
              <div className="input-field col s12">
                <input id="senha" type="password" className="validate" onChange={(e)=>setUsuario({...usuario,senha:e.target.value})}/>
                <label htmlFor="senha">Senha</label>
              </div>
            </div>
            <div className="row center-align">
              <button className="btn waves-effect waves-light" type="submit" name="action">Entrar</button>
            </div>
          </form>
        </div>
      </div>
    )
}

export default LoginPage;