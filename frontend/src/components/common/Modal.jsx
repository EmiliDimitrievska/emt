import React from "react";
import "./Modal.css"
/* 

const Modal = ({ children, onClose }) => {
const [modal, setModal]=useState(false);

  return (

    <div style={overlayStyle} onClick={onClose}></div>
  )
} */
/* export default Modal; */
const overlayStyle = {
  position: "fixed",
  top: 0,
  left: 0,
  width: "100%",
  height: "100%",
  backgroundColor: "rgba(0,0,0,0.5)",
  display: "flex",
  justifyContent: "center",
  alignItems: "center",
};


const modalStyle = {
  background: "white",
  padding: "20px",
  borderRadius: "8px",
  minWidth: "300px",
  position: "relative",
};

const Modal = ({ children, onClose }) => {
  return (
    <div style={overlayStyle} onClick={onClose}>
      <div style={modalStyle} onClick={(e) => e.stopPropagation()}>
        <div className="modal-header">
          <button className="modal-close" onClick={onClose}>
            ×
          </button>
        </div>
        {children}
      </div>
    </div>
  );
};

export default Modal;