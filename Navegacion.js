import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';
import { NavLink } from 'react-router-dom';

function Navegacion() {
  return (
    <Navbar bg="dark" variant='dark' expand="lg">
      <Container>
        <Navbar.Brand as={NavLink} to={"/"}>Partidos UNAB</Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
        <Nav className="me-auto">
          <Nav.Link as={NavLink} to={"/crearpartido"}>Crear partido</Nav.Link>
        </Nav>
          <Nav className="ms-auto">
            <Nav.Link as={NavLink} to={"/signup"}>Registrarse</Nav.Link>
            <Nav.Link as={NavLink} to={"/signin"}>Iniciar sesión</Nav.Link>
            <NavDropdown title="Username" id="basic-nav-dropdown">
              <NavDropdown.Item as={NavLink} to={"/mispartidos"}>Mis Partidos</NavDropdown.Item>
              <NavDropdown.Divider />
              <NavDropdown.Item href="#">Cerrar Sesion</NavDropdown.Item>
            </NavDropdown>
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export {Navegacion};