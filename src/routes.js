import HomePage from './pages/HomePage';
import CreatePage from './pages/CreatePage';
import EditPage from './pages/EditPage';
import ListPage from './pages/ListPage';
import AdminPage from './pages/AdminPage';
import ShowPage from './pages/ShowPage';
import Card from './components/Card';

const routes = [
  {
    path: '/card',
    element: Card
  },
  {
    path: '/',
    element: HomePage
  },
  {
    path: '/blogs',
    element: ListPage
  },
  {
    path: '/admin',
    element: AdminPage
  },
  {
    path: '/blogs/create',
    element: CreatePage
  },
  {
    path: '/blogs/:id/edit',
    element: EditPage
  },
  {
    path: '/blogs/:id',
    element: ShowPage
  }
];

export default routes;