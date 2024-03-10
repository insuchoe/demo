import axios from 'axios';
import queryString from 'query-string';
import { useState, useEffect } from 'react';
import Card from './Card';
import Pagination from './Pagination';
// 1. UseHistory Version
// 1.1 v5 under useHistory
// 1.2 v5 up    useNavigate
// import { useHistory } from  'react-router';
import { useNavigate, useParams } from 'react-router-dom';
import LoadingSpinner from '../components/LoadingSpinner';
import { bool, string } from 'prop-types';

const BlogList = ({ isAdmin }) => {
   const navigate = useNavigate();
   const [data, setData] = useState([]);
   const [loading, setLoading] = useState(true);
   const { page } = useParams();
   const pageLimit = 3;
   const PAGE_COUNT = Math.ceil(page/pageLimit);
   const getPosts = () => {

      // db.json
      // request url contains parameters
      const request = `http://localhost:3001/posts?_page=${page ? page : 1}&_limit=${pageLimit}`;
      // db.json 
      // apply data to component 
      axios.get(request).then((res) => {
         setData(res.data);
         setLoading(false);
      });
   }

   const deleteBlog = (event, id) => {
      event.stopPropagation();

      // request
      const request = `http://localhost:3001/posts${id}`

      // delete by request
      axios.delete(request).then(() => {
         // unbinding data by id on component
         setData(prevData => prevData.filter(post => post.id !== id));
      });
   };

   useEffect(() => {
      getPosts();
   }, [page ]);

   if (loading) return <LoadingSpinner />

   if (data.length === 0) {
      return (<div>No blog posts found</div>)
   }
   const renderBlogList = () => {
      return data.map(post => {
         return (
            <Card key={post.id}
               title={post.title}
               onClick={() =>
                  navigate(`/blogs/${post.id}`)
               }
            >
               {isAdmin ?
                  (<div>
                     <button
                        className='btn btn-danger btn-sm'
                        onClick={(e) => deleteBlog(e, post.id)}>
                        Delete
                     </button>
                  </div>) : null}
            </Card>
         );
      });
   }

   return (
      <div>
         {renderBlogList()}
         <Pagination page={page} pageCount={PAGE_COUNT} />

      </div>
   )
};

BlogList.propTypes = {
   isAdmin: bool
};
BlogList.defaultProps = {
   isAdmin: false
};


export default BlogList;