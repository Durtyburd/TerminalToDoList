import React from "react";
import ReactDOM from "react-dom";

function ListItems(props) {
  return (
    <li>
      <button onClick={() => props.delete(props.title)}>x</button>
      {props.title}
    </li>
  );
}

export { ListItems };
