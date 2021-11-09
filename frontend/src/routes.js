import {Routes, Route} from 'react-router-dom';
import DetalhesContato from './pages/DetalhesContato';
import Landing from './pages/Landing';

function MainRoutes() {
  return (
  <Routes>
      <Route path="/" element={<Landing />} />
      {/* <Route path="/contato/novo" element={<NovoContato />} />
      <Route path={`/contato/editar/:id`} element={<EditarContato />} /> */}
      <Route path={`/contato/detalhes/:id`} element={<DetalhesContato />} />
  </Routes>
  );
}

export default MainRoutes;