import React from 'react';
import { act, render, screen } from '@testing-library/react';
import App from './App';
import AddBook from './components/AddBook';
import userEvent from '@testing-library/user-event';

test('renders "Add a book" heading', () => {
  render(<App />);
  const linkElement = screen.getByText(/Add a book/i);
  expect(linkElement).toBeInTheDocument();
});

describe('test book add page funcionality', () => {
  test('does page render normaly', () => {
    render(<AddBook />)
    const linkElement = screen.getByText(/Add a book/i);
    expect(linkElement).toBeInTheDocument();
  });

  test('inputing invalid isbn code will result in "Invalid ISBN" showing up near the input box', () => {
    const { container } = render(<AddBook />)
    const isbnInputBox = container.getElementsByClassName("MuiInputBase-input")[0]
    userEvent.type(isbnInputBox, "asdfgh")
    expect(isbnInputBox).toHaveValue("asdfgh")
    const errorElement = screen.getByText(/Invalid ISBN/i);
    expect(errorElement).toBeInTheDocument();
  })

  test('inputing valid isbn code will result in "Invalid ISBN" not showing up near the input box', () => {
    const { container } = render(<AddBook />)
    const isbnInputBox = container.getElementsByClassName("MuiInputBase-input")[0]
    userEvent.type(isbnInputBox, "9780141365442")
    expect(isbnInputBox).toHaveValue("9780141365442")
    const errorElement = screen.queryByText(/Invalid ISBN/i)
    expect(errorElement).toBeNull()
  })

  test('pressing submit while isbn is invalid will not call fetch request', () => {
    global.fetch = jest.fn(() =>
      Promise.resolve({
        json: () => Promise.resolve({}),
      })
    ) as jest.Mock;

    const { container } = render(<AddBook />)
    const isbnInputBox = container.getElementsByClassName("MuiInputBase-input")[0]
    userEvent.type(isbnInputBox, "asdfgh")
    expect(isbnInputBox).toHaveValue("asdfgh")
    const submitButton = container.getElementsByClassName("MuiButtonBase-root")[0]
    userEvent.click(submitButton)
    expect(fetch).not.toBeCalled();
  })

  test('pressing submit while isbn is valid will call fetch request', () => {
    global.fetch = jest.fn(() =>
      Promise.resolve({
        json: () => Promise.resolve({}),
      })
    ) as jest.Mock;

    const { container } = render(<AddBook />)
    const isbnInputBox = container.getElementsByClassName("MuiInputBase-input")[0]
    userEvent.type(isbnInputBox, "9780141365442")
    expect(isbnInputBox).toHaveValue("9780141365442")
    const submitButton = container.getElementsByClassName("MuiButtonBase-root")[0]
    userEvent.click(submitButton)
    const errorElement = screen.getByText(/Loading.../i);
    expect(errorElement).toBeInTheDocument();
    expect(global.fetch).toBeCalled();
  })

  test('isbn fetch request brings up success page if successful', async () => {
    const foxJSON = { "bookName": "Fantastic Mr. Fox", "pageCount": 96, "publisher": "Puffin", "writtenBy": [{ "id": "/authors/OL34184A", "authorName": "Dahl, Roald." }], "isbn": "9780140328721" }
    const temp = { ok: true, json: () => { return foxJSON } }
    global.fetch = jest.fn(() =>
      Promise.resolve(temp)
    ) as jest.Mock;

    const { container } = render(<App />)
    await act(async () => {
      const isbnInputBox = container.getElementsByClassName("MuiInputBase-input")[0]
      userEvent.type(isbnInputBox, "9780140328721")
      expect(isbnInputBox).toHaveValue("9780140328721")
      const submitButton = container.getElementsByClassName("MuiButtonBase-root")[0]
      userEvent.click(submitButton)
      await new Promise((r) => setTimeout(r, 4000));
      const asdf = null;
    })
    const successElement = screen.getByText(/Book Added!/i);
    expect(successElement).toBeInTheDocument();
    const foxElement = screen.getByText(/Fantastic Mr. Fox/i);
    expect(foxElement).toBeInTheDocument();

  })

  test('isbn fetch request brings up fail page if not successful', async () => {
    const temp = { ok: false, json: () => { return {} } }
    global.fetch = jest.fn(() =>
      Promise.resolve(temp)
    ) as jest.Mock;

    const { container } = render(<App />)
    await act(async () => {
      const isbnInputBox = container.getElementsByClassName("MuiInputBase-input")[0]
      userEvent.type(isbnInputBox, "9780140328721")
      expect(isbnInputBox).toHaveValue("9780140328721")
      const submitButton = container.getElementsByClassName("MuiButtonBase-root")[0]
      userEvent.click(submitButton)
      await new Promise((r) => setTimeout(r, 4000));
      const asdf = null;
    })
    const failElement = screen.getByText(/Failed to save/i);
    expect(failElement).toBeInTheDocument();

  })
})
