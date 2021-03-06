import {Routes, Route} from 'react-router-dom';
import DetalhesContato from './pages/DetalhesContato';
import EditarContato from './pages/EditarContato';
import Landing from './pages/Landing';
import NovoContato from './pages/NovoContato';

function MainRoutes() {
  return (
  <Routes>
      <Route path="/" element={<Landing />} />
      <Route path="/contato/novo" element={<NovoContato />} />
      <Route path={`/contato/editar/:id`} element={<EditarContato />} />
      <Route path={`/contato/detalhes/:id`} element={<DetalhesContato />} />
  </Routes>
  );
}

export default MainRoutes;