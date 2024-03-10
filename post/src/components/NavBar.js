import { Link, NavLink } from "react-router-dom";
// 네비게이션
const NavBar = () => {
   return (

      // 1. Home Page
      // 2.1 Admin Page
      // 2.2 Blogs Page 
      <nav className="navbar navbar-dark bg-dark">
         <div className="container">
            <Link className="navbar-brand"
               to="/">
               Home
            </Link>
            <ul style={{
               flexDirection: "row"
            }} className="navbar-nav"
            >
               <li className="nav-item me-2">
                  <NavLink className="nav-link"
                     activeClassName="active"
                     aria-current="page"
                     to="/admin">
                     Admin
                  </NavLink>
               </li>
               <li className="nav-item">
                  <NavLink className="nav-link"
                     activeClassName="active"
                     aria-current="page"
                     to="/blogs">
                     Blogs
                  </NavLink>
               </li>
            </ul>
         </div>
      </nav>
   );
};
export default NavBar;