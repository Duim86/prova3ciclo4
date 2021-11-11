import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom'
import DetalhesContato from './pages/DetalhesContato';

test('renders learn react link', () => {
  render(<DetalhesContato />);
  const linkElement = screen.getByText(/nome:/i);
  console.log(linkElement)
  expect(linkElement).toBeInTheDocument();
});
