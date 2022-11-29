import moment from 'moment';
import { Badge } from 'react-bootstrap';
import Card from 'react-bootstrap/Card';
import { Link } from 'react-router-dom';

function PartidoCard({partido}) {

  return (
    <Card className='mt-5 mb-5'>
      <Card.Header>
            {partido.jugado ? <Badge className='mi-badge-jugado'>Jugado</Badge> : <Badge className='mi-badge-pendiente'>Pendiente</Badge>}


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