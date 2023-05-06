import React, { useEffect, useState } from "react";
import { useParams } from 'react-router-dom';
import { toast, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import "./HomePage.css";
import { ToastComponent } from "../components/ToastComponent";

const HomePage = (props) => {
  const { userId } = useParams();


  useEffect(() => {
    let url = `http://localhost:8080/post/${userId}`;
    const sse = new EventSource(url);

    sse.addEventListener("post-list-event", (event) => {

      const data = JSON.parse(event.data);
      console.log(data);
      const [first] = data;
      const {userId,entityId,notificationName,notificationType} = first;

      toast.success(`${userId} - ${notificationName} - ${notificationType}`, {
        position: "top-right",
        autoClose: 3000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true
      });
      
    });

    sse.onerror = () => {
      sse.close();
    };
    return () => {
      sse.close();
    };
  }, []);


  return (
    <div className="homePage">
       <ToastContainer
          position="top-right"
          autoClose={5000}
          hideProgressBar={false}
          newestOnTop={false}
          closeOnClick
          rtl={false}
          toastStyle={{ color: "white", fontSize: "1rem", fontWeight: 800 }}
          pauseOnFocusLoss={false}
          draggable
          pauseOnHover
        />
      <h1>Anupam React Notification App</h1>
  
    </div>
  );
};

export default HomePage;
