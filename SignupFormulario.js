import { useState } from 'react';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';

const SignupFormulario= ({callback, errores})=>{

    const [nombre, setNombre]= useState("")
    const [email, setEmail]= useState("")
    const [username, setUsername]= useState("")
    const [password, setPassword]= useState("")

    const enviarFormulario= (e)=>{
        e.preventDefault()
        callback({nombre, email, username, password})
    }

  return (
    <Form onSubmit={enviarFormulario}>
      <Form.Group className="mb-3" controlId="nombre">
        <Form.Label>Nombre</Form.Label>
        <Form.Control type="text" placeholder="Ingrese su nombre" value={nombre} onChange={e=> setNombre(e.target.value)} isInvalid={errores.nombre} />

        <Form.Control.Feedback type='invalid'>
            {errores.nombre}
        </Form.Control.Feedback>

      </Form.Group>
      <Form.Group className="mb-3" controlId="email">
        <Form.Label>Email</Form.Label>
        <Form.Control type="email" placeholder="Ingrese su email" value={email} onChange={e=> setEmail(e.target.value)} isInvalid={errores.email} />

        <Form.Control.Feedback type='invalid'>
            {errores.email}
        </Form.Control.Feedback>

      </Form.Group>
      <Form.Group className="mb-3" controlId="username">
        <Form.Label>Username</Form.Label>
        <Form.Control type="text" placeholder="Ingrese su Usuario" value={username} onChange={e=> setUsername(e.target.value)} isInvalid={errores.username} />

        <Form.Control.Feedback type='invalid'>
            {errores.username}
        </Form.Control.Feedback>

      </Form.Group>
      <Form.Group className="mb-3" controlId="password">
        <Form.Label>Contraseña</Form.Label>
        <Form.Control type="password" placeholder="Ingrese su Contraseña" value={password} onChange={e=> setPassword(e.target.value)} isInvalid={errores.password} />
        
        <Form.Control.Feedback type='invalid'>
            {errores.password}
        </Form.Control.Feedback>

      </Form.Group>

      <Button variant="primary" type="submit" className='mt-3'>
        Crear Usuario
      </Button>
    </Form>
  );
}

export default SignupFormulario;