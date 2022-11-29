import axios from "axios"
import { useEffect, useState } from "react"
import { Col, Container, Row } from "react-bootstrap"
import { PartidoCard } from "../components/PartidoCard"
import { PARTIDOSCREADOS_GET_ENDPOINT } from "../connections/endpoints"

const PartidosCreados=()=>{

    const [partidos, setPartidos]= useState([])
    const [buscando, setBuscando]= useState(true)

    useEffect(()=>{
        axios.get(PARTIDOSCREADOS_GET_ENDPOINT)
        .then(respuesta=>{
            setPartidos(respuesta.data)
            setBuscando(false)
        })
        .catch(err=>{
            console.error(err)
            setBuscando(false)
        })
    }, [])
    


    return(
        <Container className="mt-3 mb-3">
            <Row className="justify-content-md-center">
                <Col sm="12" md="8" lg="6">
                    <h3 className="text-center">Partidos Creados</h3>
                    <div>
                        {buscando ? "Cargando..." : (partidos.length === 0 && "No hay partidos disponibles")}
                        {partidos.map(partido=> <PartidoCard key={partido.idPartido} partido={partido} />)}
                    </div>
                </Col>
            </Row>
        </Container>
    )
}

export {PartidosCreados}