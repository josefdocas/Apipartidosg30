import { useEffect, useState } from "react"
import { Alert, Col, Container, Row } from "react-bootstrap"
import { useDispatch, useSelector } from "react-redux"
import { Link, useNavigate } from "react-router-dom"
import SigninFormulario from "../components/SigninFormulario"
import { autenticacion } from "../connections/usuarioAcciones"


const Signin= ()=>{

    const navegar= useNavigate()
    const [errores, setErrores]= useState({})
    const enviarAccion= useDispatch()
    const conectado= useSelector(estado=> estado.conectado)

    useEffect(()=>{
        if(conectado){
            navegar("/")
        }
    })

    const login= ({username, password})=>{

        const error= {}
        setErrores(error)

        enviarAccion(autenticacion({username, password}))
        .then(respuesta=>{
            navegar("/")
        })
        .catch(err=>{
            setErrores({ingresar: "No se puede iniciar la sesión con las credenciales digitadas"})
        })
    }
    
    return(
        <Container className="mt-3 mb-3">
            <Row className="justify-content-md-center">
                <Col sm="12" md="8" lg="6">
                    <h3 className="text-center">Iniciar Sesión</h3>
                    <div>
                        {errores.ingresar && <Alert variant="danger"> {errores.ingresar} </Alert>}
                        <SigninFormulario callback={login} errores={errores}/>
                        <div className="mt-3">
                            <Link to={"/signup"}> ¿No tienes una cuenta? Registrate aqui</Link>
                        </div>
                    </div>
                </Col>
            </Row>
        </Container>
    )
}

export {Signin}