import moment from 'moment';
import { Badge, Button, NavLink } from 'react-bootstrap';
import Card from 'react-bootstrap/Card';
import { Link } from 'react-router-dom';
import { EliminarPartidoBoton } from './EliminarPartidoBoton';

function PartidoCard({partido, botones}) {

  return (
    <Card className='mt-3 mb-3'>
      <Card.Header className= "mi-card"> 
            {partido.jugado ? <Badge className='mi-badge-jugado'>Jugado</Badge> : <Badge className='mi-badge-pendiente'>Pendiente</Badge>}
            {botones ? 
            <div>
              <Button variant='primary' size="sm" className="me-2" to={`editarpartido/${partido.idPartido}`}> Editar </Button>
              <EliminarPartidoBoton id={partido.idPartido} />
            </div> : "" }
      </Card.Header>
      <Card.Body>
            <Card.Title>
                <Link to={`/partido/${partido.idPartido}`}>
                    {partido.equipoEntityLocal.nombre} vs {partido.equipoEntityVisitante.nombre}
                </Link>
            </Card.Title>
            <Card.Text>
                Fecha: {moment (partido.fecha).format('D[/]MM[/]YYYY')} 
            </Card.Text>
            <Card.Text>
                Creado por: {partido.usuarioEntity.nombre}, el dia {moment(partido.creado).fromNow()} 
            </Card.Text>
      </Card.Body>
    </Card> 
  );
}

export {PartidoCard}; 