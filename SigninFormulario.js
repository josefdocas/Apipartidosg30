import { useState } from 'react';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';

const SigninFormulario= ({callback, errores})=>{

    const [username, setUsername]= useState("")
    const [password, setPassword]= useState("")

    const enviarFormulario= (e)=>{
        e.preventDefault()
        callback({username, password})
    }

  return (
    <Form onSubmit={enviarFormulario}>
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
        Iniciar Sesión
      </Button>
    </Form>
  );
}

export default SigninFormulario;