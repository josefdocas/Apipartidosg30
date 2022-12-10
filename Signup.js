import axios from "axios"
import { useState } from "react"
import { Alert, Col, Container, Row } from "react-bootstrap"
import { Link, useNavigate } from "react-router-dom"
import SignupFormulario from "../components/SignupFormulario"
import { SIGNUP_POST_ENDPOINT } from "../connections/endpoints"

const Signup= ()=>{

    const navegar= useNavigate()
    const [errores, setErrores]= useState({})

    const registro= ({nombre, email, username, password})=>{

        const error= {}
        setErrores(error)

        axios.post(SIGNUP_POST_ENDPOINT, {nombre, email, username, password},
            {Headers: {"Accept": "application/json", "Content-Type": "application/json"}}    
        )
        .then(respuesta=>{
            navegar("/signin")
        })
        .catch(err=>{
            setErrores({crear: err.respuesta.data.message})

        })
    }
    
    return(
        <Container className="mt-3 mb-3">
            <Row className="justify-content-md-center">
                <Col sm="12" md="8" lg="6">
                    <h3 className="text-center">Registro Usuario</h3>
                    <div>
                        {errores.crear && <Alert variant="danger"> {errores.crear} </Alert>}
                        <SignupFormulario callback={registro} errores={errores}/>
                        <div className="mt-3">
                            <Link to={"/signin"}> ¿Ya tienes una cuenta? Iniciar sesión aqui</Link>
                        </div>
                    </div>
                </Col>
            </Row>
        </Container>
    )
}

export {Signup}