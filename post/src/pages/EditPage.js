import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

const EditPage = () => {
  const { id } = useParams();
  // db.json posts key data
  const [data, setData] = useState([]);
  // db.json new data before patching data
  const [newData, setNewData] = useState([]);

  const navigate = useNavigate();

  // db.json get posts key data by id
  const getDataById = () => {
    axios.get(`http://localhost:3001/posts/${id}`)
      .then((response) => { setData(response.data) })
      .catch((error) => console.error(error));
  };

  // db.json patch posts data
  const onPatch = () => {
    axios.patch(`http://localhost:3001/posts/${id}`, newData)
      .then((response) => {
        // db.json 
        // 1. bring last data 
        // 2. apply last data to component
        setData(response.data);
        // ShowPage.js navigate blog page
        navigate(`/blogs/${id}`);
      })
      .catch((error) => console.error(error));
  };


  const formatDateToString = (dateObject) => {
    return new Date(dateObject).toLocaleDateString();
  }

  useEffect(() => {
    getDataById();
  }, [id]);

  return (
    <div>
      <div className="d-flex">
        <h1 className="flex-grow-1">
          {data.title}
        </h1>
        <div>
          <button className="btn btn-primary"
            onClick={onPatch}>
            edit
          </button>
        </div>
      </div>
      <small className="text-muted">
        created : {formatDateToString(data.createdAt)}
        {data.patchedDate ?
          `changed: ${formatDateToString(data.patchedDate)}` :
          null}
      </small>
      <hr />
      <textarea className="form-control"
        value={newData.body ? newData.body : data.body}
        onChange={(event) => {
          // EditPage.js current textarea value inputed 
          const newBody = event.target.value;
          let { body, ...rest } = data;
          body = newBody;

          // EditPage.js new data before updated
          const newData = { body: newBody, ...rest };

          // EditPage.js apply new data 
          setNewData(newData);

          ;
        }}>
      </textarea>
    </div>
  );
};

export default EditPage