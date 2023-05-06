import React from "react";
import { Toast } from "react-bootstrap";

export const ToastComponent = (props) => {
  const { notif } = props;

  return (
    <Toast
      style={{
        color: "white",
        fontSize: "20px",
        borderRadius: 8,
        backgroundColor: "#2E68FF",
      }}
      key={notif.userId}
      autohide={true}
      delay={3500}
      onClose={() => {
        console.log("YYYY")
        // dispatch(removeFromToastList({ notif }));
      }}
    >
      <Toast.Body>{notif.notificationName}{notif.notificationType}</Toast.Body>
    </Toast>
  );
};
