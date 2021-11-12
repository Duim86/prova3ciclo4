import {Routes, Route} from 'react-router-dom';
import DetalhesContato from './pages/DetalhesContato';
import Landing from './pages/Landing';
import ContatoPage from './pages/ContatoPage';

function MainRoutes() {
  return (
  <Routes>
      <Route path="/" element={<Landing />} />
      <Route path="/contato/novo" element={<ContatoPage />} />
      <Route path={`/contato/editar/:id`} element={<ContatoPage />} />
      <Route path={`/contato/detalhes/:id`} element={<DetalhesContato />} />
  </Routes>
  );
}

export default MainRoutes;