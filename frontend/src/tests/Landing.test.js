import {render, screen} from '@testing-library/react'
import '@testing-library/jest-dom'
import Landing from '../pages/Landing'
import { act } from 'react-dom/test-utils'
import api from '../services/api'
 

describe('Landing', () => {
  test('should get contacts on loading page', async () => {
    const data = [{
      id: 13,
      nome: "Arrascaeta",
      email: "arrascaeta@flamengo.com",
      telefone: "41-5555555"
    }, 
    {
      id: 14,
      nome: "Gabigol",
      email: "gabigol@flamengo.com",
      telefone: "41-5555565"
    }];
    const axiosGetSpy = jest.spyOn(api, 'get').mockResolvedValueOnce({ data });
    await act(async () => {
      render(<Landing />);
    });
    expect(axiosGetSpy).toBeCalledWith('/contato');
    axiosGetSpy.mockRestore();
  })

  test("Expected to find a button with text 'Novo Contato' and href to '/contato/novo'", () => {
    render(<Landing />);
    expect(screen.getByText(/novo contato/i).closest('a')).toHaveAttribute('href', '/contato/novo')
  });

})

